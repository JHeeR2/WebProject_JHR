package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import users.UserDAO;
import users.UserDTO;

@WebServlet("/user/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//사용자 입력 값 받기
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pw");
		String userName = req.getParameter("user_name");
		String userEmail = req.getParameter("user_email");
		String userNick = req.getParameter("user_nick");
		String userBirth = req.getParameter("user_birthday");
		String userGender = req.getParameter("user_gender");
		
		
		// 생일이 비어 있으면 null 처리 
		/*Date birthday = null;
		if (userBirth != null && !userBirth.isEmpty()) {
			birthday = Date.valueOf(userBirth);
		}*/
		
		//성별이 비어 있으면 null 처리
		if (userGender.equals("성별 선택") || userGender.isEmpty() || userGender == null) {
			userGender = null;
		}
		
		//UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		
		//UserDAO의 checkIdDuplicate로 아이디 중복확인
		boolean idExists = dao.checkIdDuplicate(userId);
		if (idExists) {
			req.setAttribute("RegisterErrMsg", userId+"는 이미 사용중인 아이디입니다. 다른 아이디를 입력하세요.");
			req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
			return;
		}
		
		//UserDAO의 checkEmailDuplicate로 이메일 중복확인
		boolean emailExists = dao.checkEmailDuplicate(userEmail);
		if (emailExists) {
			req.setAttribute("RegisterErrMsg", userEmail+"는 이미 사용중인 이메일 입니다. 다른 이메일을 입력하세요.");
			req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
			return;
		}
		
		//UserDAO의 checkIdDuplicate로 닉네임 중복확인
		boolean nicknameExists = dao.checkNicknameDuplicate(userNick);
		if (nicknameExists) {
			req.setAttribute("RegisterErrMsg", userNick+"는 이미 사용중인 닉네임 입니다. 다른 닉네임을 입력하세요.");
			req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
			return;
		}
				
				
		//UserDTO 객체 생성 후 값 설정
		UserDTO newUser = new UserDTO();
		newUser.setId(userId);
		newUser.setPass(userPw);
		newUser.setName(userName);
		newUser.setEmail(userEmail);
		newUser.setNickname(userNick);
		newUser.setBirthday(userBirth);
		newUser.setGender(userGender);
		
		//UserDAO의 registerUser 메서드 호출, DB에 insert
		int result = dao.registerUser(newUser);
		dao.close();
		
		
		
		
		
		//회원가입 성공 여부에 따른 처리
		if (result >0) {
			//성공시 가입 축하 페이지로
			resp.sendRedirect("RegisterSuccess.jsp");
		} else {
			//실패시 다시 RegisterForm.jsp로
			req.setAttribute("RegisterErrMsg", "회원가입에 실패하였습니다. 다시 진행해주세요");
			req.getRequestDispatcher("RegisterForm.jsp").forward(req, resp);
		}
		
		
	}
	
	

}
