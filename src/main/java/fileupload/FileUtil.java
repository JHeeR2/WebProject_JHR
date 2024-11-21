package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	//파일 업로드 기능 구현
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException{
		Part part = req.getPart("ofile");
		
		/*
		Part 인스턴스에서 해더값을 통해 원본 파일명을 얻어온다. 차후
		업로드 테스트 시 콘솔에서 확인할 수 있다. 
		*/
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader="+partHeader);
		
		/*
		헤더값을 통해 얻어온 문자열에서 "filename="을 구분자로 split 한다.
		그러면 String 타입의 배열로 반환된다. 
		*/
		String[] phArr = partHeader.split("filename=");
		/*
		위 결과중 1번 인덱스의 값이 파일명이 된다. 여기서 더블쿼테이션을
		제거해야하므로 아래와 같이 replace()를 사용한다. 단 더블쿼테이션
		앞에 이스케이프 시퀀스 기호를 반드시 추가해야 한다. 
		*/
		String originalFileName = phArr[1].trim().replace("\"", "");
		
		/*
		전송된 파일이 있는 경우라면 서버의 디렉토리에 파일을 저장한다.
		File.separator : 운영체제(OS)마다 경로를 표시하는 기호가 다르므로
			해당 OS에 맞는 구분기호를 자동으로 추가해준다. 
		*/
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+ File.separator+originalFileName);
		}
		
		//저장된 원본파일명을 반환한다.
		return originalFileName;
		
	}
	
	//파일명 변경 기능
	/* 서버에 저장된 파일명이 한글인 경우 웹브라우저에서 깨짐 현상이
	발생할 수 있으므로 영문 혹은 숫자의 조합으로 변경하는 것이 안전하다. 
	*/
	public static String renameFile(String sDirectory, String fileName) {
		
		/*
		파일명에서 확장자를 잘라내기 위해 뒤에서부터 .이 있는 위치를 찾는다.
		파일명에는 2개 이상의 닷(.)을 사용할 수 있기 때문이다. 
		*/
		String ext = fileName.substring(fileName.lastIndexOf("."));
		/*
		날짜와 시간을 이용해서 파일명으로 사용할 문자열을 생성한다.
		"년월일_시분초999"와 같은 형태가 된다. 마지막의 대문자 S는
		밀리세컨즈 단위의 시간을 3자리로 반환한다. 
		*/
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		//생성된 파일명과 확장자를 결합한다. 
		String newFileName = now + ext;
		
		//원본파일명과 새로운 파일명을 이용해서 File 인스턴스를 생성한다. 
		File oldFile = new File(sDirectory+File.separator+fileName);
		File newFile = new File(sDirectory+File.separator+newFileName);
		//파일명을 새로운 이름으로 변경한다.
		oldFile.renameTo(newFile);
		//변경된 파일명을 반환한다. 
		return newFileName;
	}
	
	//multiple 속성이 부여되어 2개 이상의 파일을 업로드 처리 
	public static ArrayList<String> multipleFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException{
		
		//파일명 저장을 위한 List 계열의 컬렉션 생성
		ArrayList<String> listFileName = new ArrayList<>();
		
		//getParts() 메서드로 Part 인스턴스를 컬렉션의 형태로 생성
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			/* 전송된 폼값 중 파일이 아니라면 업로드의 대상이 아니므로
			반복문의 처음으로 이동한다. */
			if(!part.getName().equals("ofile"))
				continue;

			String partHeader = part.getHeader("content-disposition");
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory+ File.separator+originalFileName);
			}
			listFileName.add(originalFileName);
		}
		return listFileName;
	}
	
	
	
}
