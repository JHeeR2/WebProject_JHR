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

@WebServlet("/user/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		
		UserDAO dao = new UserDAO();
		
		UserDTO user = dao.getUser(userId, userPw);
		
		dao.close();
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("UserId", user.getId());
			session.setAttribute("UserNick", user.getNickname());
			resp.sendRedirect("LoginSuccess.jsp");
			
		} else {
			req.setAttribute("LoginErrMsg", "아이디 또는 비밀번호를 확인해주세요");
			req.getRequestDispatcher("LoginForm.jsp").forward(req, resp);
		}
	}


}
