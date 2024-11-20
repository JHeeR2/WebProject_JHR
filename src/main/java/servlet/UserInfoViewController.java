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

@WebServlet("/user/UserInfoView.do")
public class UserInfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		
		UserDAO dao = new UserDAO();
		UserDTO user = dao.getUserInfo(userId);
		
		req.setAttribute("user", user);
		req.getRequestDispatcher("/user/UserInfoView.jsp").forward(req, resp);
		
		
	}
}
