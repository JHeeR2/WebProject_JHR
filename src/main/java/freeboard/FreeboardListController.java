package freeboard;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/freeboard/FreeboardList.do")
public class FreeboardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DAO 인스턴스 생성
		FreeboardDAO freeboardDAO = new FreeboardDAO();
		//목록 출력을 위한 메서드 호출
		ArrayList<FreeboardDTO> fboardList = freeboardDAO.fboardListSelect();
		
		//리쿼스트 영역에 반환받은 List 저장
		req.setAttribute("fboardList", fboardList);
		
		//뷰로 포워드
		req.getRequestDispatcher("FreeboardList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
