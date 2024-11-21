package freeboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/freeboard/FreeboardWrite.do")
public class FreeboardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./FreeboardWrite.jsp").forward(req, resp);
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
		FreeboardDTO freeboardDTO = new FreeboardDTO();
		freeboardDTO.setUser_id(user_id);
		freeboardDTO.setTitle(title);
		freeboardDTO.setContent(content);
		
		
		//3. DAO로 전달하여 입력(insert)처리
		FreeboardDAO freeboardDAO = new FreeboardDAO();
		int result = freeboardDAO.writeInsert(freeboardDTO);
		if(result==1) {
			System.out.println("자료실 게시판 게시글 입력성공");
			resp.sendRedirect("list.do");
		}else {
			System.out.println("자료실 게시판 게시글 입력실패");
			resp.sendRedirect("write.do");
		}
		
		
	}
	

}
