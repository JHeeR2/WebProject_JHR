package multiboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import freeboard.FreeboardDTO;
import jakarta.servlet.http.HttpSession;

public class MultiBoardDAO extends DBConnPool {
	public MultiBoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물 개수 반환 OK
	public int insertFile(MultiBoardDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO multiboard (idx, user_id, title, content, ofile, sfile) "
							+ " VALUES (seq_multib_num.nextval, ?, ?, ?, ?, ?)";
			
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
			
			
		}catch(Exception e){
			System.out.println("자료실 게시판 insert 중 예외 발생");
			System.out.println(e.toString());
		}
		return applyResult;
	}
	
	//자료게시판 파일 목록 반환하기 OK
	public List<MultiBoardDTO> selectList(Map<String, Object> map){
		//오라클에서 인출한 레코드를 저장하기 위한 List 생성
		List<MultiBoardDTO> mBoardLists = new Vector<MultiBoardDTO>();
		
		//레코드 인출을 위한 쿼리문 작성
		String query = "select * from multiboard "
						+ " inner join users "
							+ "on multiboard.user_id = users.id "
							+ "order by idx desc";
		
		try {
			stmt = con.prepareStatement(query);
			//쿼리문 실행 및 결과반환(ResultSet)
			rs = stmt.executeQuery(query);
			//ResultSet의 크기만큼 즉, 인출된 레코드의 갯수만큼 반복
			while(rs.next()) {
				//DTO 저장
				MultiBoardDTO dto = new MultiBoardDTO();
				
				dto.setIdx(rs.getString("idx"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setPostdate("postdate");
				dto.setOfile("ofile");
				dto.setSfile("sfile");
				

				
				//레코드가 저장된 DTO를 List에 갯수만큼 추가한다.
				mBoardLists.add(dto);
				
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		//마지막으로 인출한 레코드를 저장한 List를 반환한다.
		return mBoardLists;
	}
	
	//검색 조건에 맞는 개시물의 개수 반환하기 OK
		public int selectCount(Map<String, Object> map) {
			int totalCount = 0;
			String query = "select count(*) "
							+ " from multiboard "
							+ "    inner join users "
							+ "  on multiboard.user_id = users.id";
			if (map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField") + " "
						+ " Like '%"+map.get("searchWord")+ "%'";
			}
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				rs.next();
				totalCount = rs.getInt(1);
				
			}
			catch(Exception e){
				System.out.println("게시물 카운트 중 예외 발생");
				e.printStackTrace();
			}
			
			return totalCount;
		}
		
		public List<MultiBoardDTO> selectListPage(Map<String, Object> map) {
		    List<MultiBoardDTO> board = new Vector<MultiBoardDTO>();
		    
		    String query = "SELECT * FROM ( "
		        + "    SELECT "
		        + "        mb.idx, "
		        + "        mb.title, "
		        + "        mb.content, "
		        + "        mb.user_id, "
		        + "        u.nickname, "
		        + "        mb.downcount, "
		        + "        mb.visitcount, "
		        + "        mb.postdate, "
		        + "        mb.ofile, "
		        + "        mb.sfile, "
		        + "        ROW_NUMBER() OVER (ORDER BY mb.idx DESC) AS rNum "
		        + "    FROM multiboard mb "
		        + "    INNER JOIN users u ON mb.user_id = u.id ";
		    
		    // 검색어 입력 여부에 따라 WHERE 절 추가
		    if (map.get("searchWord") != null) {
		        String column = map.get("searchField").equals("nickname") 
		            ? "u." + map.get("searchField")
		            : "mb." + map.get("searchField");
		        
		        query += "    WHERE (" + column + " LIKE '%" + map.get("searchWord") + "%') ";
		    }
		    
		    query += ") WHERE rNum BETWEEN ? AND ?";
		    
		    try {
		        psmt = con.prepareStatement(query);
		        psmt.setString(1, map.get("start").toString());
		        psmt.setString(2, map.get("end").toString());
		        rs = psmt.executeQuery();
		        
		        while(rs.next()) {
		            MultiBoardDTO dto = new MultiBoardDTO();
		            
		            dto.setIdx(rs.getString("idx"));
		            dto.setTitle(rs.getString("title"));
		            dto.setContent(rs.getString("content"));
		            dto.setUser_id(rs.getString("user_id"));
		            dto.setNickname(rs.getString("nickname"));
		            dto.setDowncount(rs.getInt("downcount"));
		            dto.setVisitcount(rs.getInt("visitcount"));
		            dto.setPostdate(rs.getString("postdate"));
		            dto.setOfile(rs.getString("ofile"));
		            dto.setSfile(rs.getString("sfile"));
		            
		            board.add(dto);
		        }
		    }
		    catch (Exception e) {
		        System.out.println("게시물 조회 중 예외 발생");
		        e.printStackTrace();
		    }
		    
		    return board;
		}
	
		
		
	
	
	
	
	
	

}
