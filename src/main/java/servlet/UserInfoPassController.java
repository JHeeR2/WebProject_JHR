package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import users.UserDAO;
import utils.JSFunction;

@WebServlet("/user/UserInfoPass.do")
public class UserInfoPassController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("UserInfoPass.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메개변수 저장
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		String pass = req.getParameter("user_pw");
		
		//비밀번호 확인
		UserDAO dao = new UserDAO();
		boolean confirmed = dao.confirmPassword(user_id, pass);
		dao.close();
		
		if (confirmed) {
			//비밀번호 일치
			resp.sendRedirect("UserInfoEdit.jsp");
			
		}else {
			JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
		}
		
	}

}
