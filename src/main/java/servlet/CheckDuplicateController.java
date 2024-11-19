package servlet;

import java.io.IOException;

import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import users.UserDAO;

@WebServlet("/user/checkDuplicate.do")
public class CheckDuplicateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인코딩 설정
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        // 파라미터 수집
        String column = req.getParameter("column");
        String value = req.getParameter("value");

        // 기본응답객체 설정
        JsonObject responseJson = new JsonObject();

        try {
            // column 값 검증 및 변환
            String dbColumn;
            switch(column) {
                case "id":
                    dbColumn = "id";  // DB의 실제 컬럼명으로 변환
                    break;
                case "email":
                    dbColumn = "email";
                    break;
                case "nickname":
                    dbColumn = "nickname";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column name");
            }

            UserDAO dao = new UserDAO();
            boolean isDuplicate = dao.checkDuplicate(dbColumn, value);

            if (isDuplicate) {
                responseJson.addProperty("status", "duplicate");
            } else {
                responseJson.addProperty("status", "available");
            }

        } catch (Exception e) {
            responseJson.addProperty("status", "error");
            responseJson.addProperty("message", e.getMessage());
        }

        resp.getWriter().write(responseJson.toString());
    }
}

