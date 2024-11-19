package servlet;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import users.UserDAO;

/*@WebServlet("/checkEmail.do")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userEmail = req.getParameter("user_email");
		UserDAO dao = new UserDAO();
		boolean isDuplicate = dao.checkEmailDuplicate(userEmail);
		
		JsonObject jsonResponse = new JsonObject();
		//이메일 중복시 1, 중복되지 않으면 0
		jsonResponse.addProperty("cnt", isDuplicate ? 1 : 0);
		resp.setContentType("application/json");
		resp.getWriter().write(jsonResponse.toString());
	}

}*/
