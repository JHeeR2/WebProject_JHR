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
import utils.JSFunction;

@WebServlet("/user/UserInfoEdit.do")
public class UserInfoEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("user_id")== null) {
			//session 영역에 인증에 관련된 속성이 있는지 확인 후 경고창 띄움
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "LoginForm.jsp");
			return;
		}
		
		// 요청 파라미터 수집
		String userId = (String) session.getAttribute("user_id");
        String userPw = req.getParameter("user_pw");
        String userEmail = req.getParameter("user_email");
        String userNick = req.getParameter("user_nick");
        String userBirth = req.getParameter("user_birthday");
        String userGender = req.getParameter("user_gender");
        
        // DTO에 값 설정
        UserDTO user = new UserDTO();
        user.setId(userId);
        user.setPass(userPw);
        user.setEmail(userEmail);
        user.setNickname(userNick);
        user.setBirthday(userBirth);
        user.setGender(userGender);

        // DAO를 통해 DB 업데이트
        UserDAO dao = new UserDAO();
        int result = dao.updateUser(user);
        dao.close();
		
        if (result > 0) {
            resp.sendRedirect("UserInfoView.do");
        } else {
            req.setAttribute("EditErrMsg", "정보 수정에 실패하였습니다.");
            req.getRequestDispatcher("UserInfoEdit.jsp").forward(req, resp);
        }
		
	}
	
	
	

}
