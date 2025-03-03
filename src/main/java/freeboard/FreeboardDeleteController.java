package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/freeboard/FreeboardDelete.do")
public class FreeboardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id")== null) {
			//session 영역에 인증에 관련된 속성이 있는지 확인 후 경고창 띄움
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "../user/LoginForm.jsp");
			return;
		}
		
		//게시물 얻어오기 : 열람에서 사용한 메서드를 그대로 사용한다.
		String idx = req.getParameter("idx");
		FreeboardDAO dao = new FreeboardDAO();
		FreeboardDTO dto = dao.selectView(idx);
		
		/*작성자 본인 확인 : DTO에 저장된 아이디와 Session 영역에 저장된 아이디를
		비교하여 본인이 아니라면 경고창을 띄운다. */
		if (!dto.getUser_id().equals(session.getAttribute("user_id").toString())) {
			JSFunction.alertBack(resp, "작성자 본인만 삭제할 수 있습니다.");
			return;
		}
		
		
		//게시물 삭제
		int result = dao.deletePost(idx);
		dao.close();
		
		//삭제가 완료되면 목록으로 이동한다. 
		JSFunction.alertLocation(resp, "삭제되었습니다.", "../freeboard/FreeboardList.do");
		
		}

}
