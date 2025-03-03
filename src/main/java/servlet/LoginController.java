package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import users.UserDAO;
import users.UserDTO;

@WebServlet("/user/LoginForm.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("LoginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		
		UserDAO dao = new UserDAO();
		
		UserDTO user = dao.UserLogin(userId, userPw);
		
		dao.close();
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user_id", user.getId());
			session.setAttribute("user_nick", user.getNickname());
			resp.sendRedirect("../freeboard/FreeboardList.do");
			
		} else {
			req.setAttribute("LoginErrMsg", "아이디 또는 비밀번호를 확인해주세요");
			req.getRequestDispatcher("LoginForm.jsp").forward(req, resp);
		}
	}


}
