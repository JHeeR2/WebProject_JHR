package qna;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/qna/QEdit.do")
public class QuestionEditController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	//수정 페이지 진입
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../user/LoginForm.jsp");
			return;
		}
		
		//게시물 얻어오기 : '열람'에서 사용했던 메서드를 그대로 호출
		String idx = req.getParameter("idx");
		QuestionDAO dao = new QuestionDAO();
		QuestionDTO dto = dao.selectView(idx);
		
		//작성자 본인 확인 : DTO에 저장된 id와 로그인 아이디를 비교
		if(!dto.getUser_id().equals(session.getAttribute("user_id").toString())) {
			//작성자 본인이 아니라면 경고창을 띄운 후 뒤로 이동한다. 
			JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
			return;
		}
		
		//작성자 본인이라면 request 영역에 DTO를 저장한 후 포워드한다. 
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/qna/QEdit.jsp").forward(req, resp);
	}
	
	//수정처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = req.getSession();
		//로그인 전이라면 로그인 페이지로 이동한다. 
		if(session.getAttribute("user_id")==null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../user/LoginForm.jsp");
			
			return;
		}
		
		//작성자 본인 확인 : 수정폼의 hidden 속성으로 추가한 내용으로 비교
		if(!req.getParameter("id").equals(session.getAttribute("user_id").toString())) {
			//작성자 본인이 아니라면 경고창을 띄운 후 뒤로 이동한다. 
			JSFunction.alertBack(resp, "작성자 본인만 수정할 수 있습니다.");
			return;
		}
		
		// 수정 내용을 매개변수에서 얻어옴
		//수정할 게시물의 일련번호
		String idx = req.getParameter("idx");
		
		//사용자가 수정한 내용
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		
		//DTO에 저장
		QuestionDTO dto = new QuestionDTO();
		//파일을 제외한 나머지 폼값을 먼저 저장
		dto.setIdx(idx);
		//특히 아이디는 session에 저장된 내용으로 추가
		dto.setUser_id(session.getAttribute("user_id").toString()); 
		dto.setTitle(title);
		dto.setContent(content);
		
		//DB에 수정 내용 반영
		QuestionDAO dao = new QuestionDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		//성공 or 실패?
		if (result == 1) { //수정 성공
			//수정에 성공하면 '열람'페이지로 이동해서 수정된 내용을 확인한다.
			resp.sendRedirect("../qna/QView.do?idx="+idx);
		}
		else {	//수정 실패 : 경고창을 띄운다. 
			//글쓰기 페이지로 다시 돌아간다. 
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.", "../qna/QView.do?idx="+idx);
		}
		
	}

}
