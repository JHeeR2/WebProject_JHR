package fileupload;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import multiboard.MultiBoardDAO;
import multiboard.MultiBoardDTO;

@WebServlet("/multiboard/UploadProcess.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 100,
	maxRequestSize = 1024 * 1024 * 1000
)

public class UploadProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파일이 저장될 디렉토리의 물리적 경로를 얻어온다. 
		String saveDirectory = getServletContext().getRealPath("/Uploads");
		//파일 업로드 처리
		String originalFileName = FileUtil.uploadFile(req, saveDirectory);
		//서버에 저장된 파일명을 변경
		String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
		//업로드가 완료되면 파일 목록 페이지로 이동

		insertMyFile(req, originalFileName, savedFileName);
		
		resp.sendRedirect("MultiList.jsp");
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		//파일 외 폼값 받기
		//1. 세션에 저장된 아이디
		HttpSession session = req.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String ofile = req.getParameter("ofile");

		//DB에 입력하기 
		MultiBoardDTO dto = new MultiBoardDTO();
		dto.setUser_id(user_id);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		//DAO를 통해 데이터베이스에 반영
		MultiBoardDAO dao = new MultiBoardDAO();
		dao.insertFile(dto);
		dao.close();
	}
	

}
