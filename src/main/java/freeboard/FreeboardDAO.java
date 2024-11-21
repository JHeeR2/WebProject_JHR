package freeboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class FreeboardDAO extends DBConnPool{
	
	public FreeboardDAO() {
		super();
	}
	
	//자유게시판 게시글 작성하기
	public int writeInsert(FreeboardDTO freeboardDTO) {
		int result = 0;
		String query = "insert into freeboard (idx, user_id, title, content) "
				+ "values (seq_free_num.nextVal, ?, ?, ?)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, freeboardDTO.getUser_id() );
			psmt.setString(2, freeboardDTO.getTitle());
			psmt.setString(3, freeboardDTO.getContent());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FreeboardDAO writeInsert 문제발생, 입력실패");
		}
		
		return result;
	}
	
	//검색 조건에 맞는 개시물의 개수 반환하기
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		String query = "select count(*) "
						+ " from freeboard "
						+ "    inner join users "
						+ "  on freeboard.user_id = users.id";
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
	
	//검색 조건에 맞는 게시물 목록 반환
		public List<FreeboardDTO> selectList(Map<String, Object> map){
			//오라클에서 인출한 레코드를 저장하기 위한 List 생성
			List<FreeboardDTO> board = new Vector<FreeboardDTO>();
			
			//레코드 인출을 위한 쿼리문 작성
			String query = "select idx, title, freeboard.user_id, users.nickname, postdate, visitcount "
					+ " from freeboard "
					+ " 	inner join users "
					+ "        on freeboard.user_id = users.id ";
			//검색어 입력 여부에 따라서 where절은 조건부로 추가됨
			if (map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField")
						+ " LIKE '%" + map.get("searchWord")+"%' ";
			}
			//일련번호의 내림차순으로 정렬한 후 인출한다. 
			query += " ORDER BY idx DESC ";
			//게시판은 항상 최근에 작성한 게시물이 상단에 노출되어야 한다.
			
			try {
				//PreparedStatement 인스턴스 생성
				psmt = con.prepareStatement(query);
				//쿼리문 실행 및 결과반환(ResultSet)
				rs = psmt.executeQuery();
				//ResultSet의 크기만큼 즉, 인출된 레코드의 갯수만큼 반복
				while(rs.next()) {
					//하나의 레코드를 저장하기 위해 DTO인스턴스 생성
					FreeboardDTO freeboardDTO = new FreeboardDTO();

					freeboardDTO.setIdx(rs.getString("idx"));
					freeboardDTO.setTitle(rs.getString("title"));
					freeboardDTO.setUser_id(rs.getString("user_id"));
					freeboardDTO.setUser_nick(rs.getString("nickname"));
					freeboardDTO.setPostdate(rs.getString("postdate"));
					freeboardDTO.setVisitcount(rs.getInt("visitcount"));
					
					//레코드가 저장된 DTO를 List에 갯수만큼 추가한다.
					board.add(freeboardDTO);
					
				}
			}
			catch (Exception e) {
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			//마지막으로 인출한 레코드를 저장한 List를 반환한다.
			return board;
		}
	
		public List<FreeboardDTO> selectListPage(Map<String, Object> map){
			List<FreeboardDTO> board = new Vector<FreeboardDTO>();
			String query = "SELECT * FROM ( "
						+"   SELECT Tb.*, ROWNUM rNum FROM ( "
						+"     SELECT * FROM mvcboard ";
			if (map.get("searchWord") != null) {
				query +="WHERE "+map.get("searchField")+" LIKE '%"+map.get("searchWord")+"%' ";
			}
			
			query += "         ORDER By idx DESC "
					+"         ) Tb"
					+"      )"
					+" WHERE rNum BETWEEN ? AND ?";
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();
				while(rs.next()) {
					FreeboardDTO dto = new FreeboardDTO();
					
					dto.setIdx(rs.getString(1));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setVisitcount(rs.getInt(9));
					
					board.add(dto);
				}
			
			} 
			catch (Exception e){
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			return board;

		}
	
	
	
	//검색 x 자유게시판 목록 출력을 위한 메서드
	public ArrayList<FreeboardDTO> fboardListSelect() {
		
		ArrayList<FreeboardDTO> fboardList = new ArrayList<FreeboardDTO>();
		
		String query = "select idx, title, freeboard.user_id, users.nickname, postdate, visitcount "
				+ " from freeboard "
				+ " 	inner join users "
				+ "        on freeboard.user_id = users.id "
				+ " order by idx desc";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				FreeboardDTO freeboardDTO = new FreeboardDTO();
				//DTO에 컬럼 지정하여 레코드 저장
				freeboardDTO.setIdx(rs.getString("idx"));
				freeboardDTO.setTitle(rs.getString("title"));
				freeboardDTO.setUser_id(rs.getString("user_id"));
				freeboardDTO.setUser_nick(rs.getString("nickname"));
				freeboardDTO.setPostdate(rs.getString("postdate"));
				freeboardDTO.setVisitcount(rs.getInt("visitcount"));
				
				fboardList.add(freeboardDTO);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("자유게시판 목록조회 에러발생");
		}
		
		return fboardList;
	}
	
	

	
	
	
	
	

}
