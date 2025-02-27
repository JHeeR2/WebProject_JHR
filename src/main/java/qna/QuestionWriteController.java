package qna;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/qna/QWrite.do")
public class QuestionWriteController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./QWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 세션에 저장된 아이디와 닉네임 + 폼값 받기
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String user_nick = (String) session.getAttribute("user_nick");
		
		//2. DTO에 저장하기
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setUser_id(user_id);
		questionDTO.setTitle(title);
		questionDTO.setContent(content);
		
		
		//3. DAO로 전달하여 입력(insert)처리
		QuestionDAO questionDAO = new QuestionDAO();
		int result = questionDAO.writeInsert(questionDTO);
		if(result==1) {
			System.out.println("자료실 게시판 게시글 입력성공");
			resp.sendRedirect("QList.do");
		}else {
			System.out.println("자료실 게시판 게시글 입력실패");
			resp.sendRedirect("Qwrite.do");
		}
		
		
	}
	

}
