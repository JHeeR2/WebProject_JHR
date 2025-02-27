package qna;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class QuestionDAO extends DBConnPool {
	public QuestionDAO() {
		super();
	}
	
	
	//자유게시판 게시글 작성하기
		public int writeInsert(QuestionDTO QuestionDTO) {
			int result = 0;
			String query = "insert into qboard (idx, user_id, title, content) "
					+ "values (seq_q_num.nextVal, ?, ?, ?)";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, QuestionDTO.getUser_id() );
				psmt.setString(2, QuestionDTO.getTitle());
				psmt.setString(3, QuestionDTO.getContent());
				
				result = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("QuestionDAO writeInsert 문제발생, 입력실패");
			}
			
			return result;
		}
		
		
		//검색 조건에 맞는 게시물 목록 반환
			public List<QuestionDTO> selectList(Map<String, Object> map){
				//오라클에서 인출한 레코드를 저장하기 위한 List 생성
				List<QuestionDTO> board = new Vector<QuestionDTO>();
				
				//레코드 인출을 위한 쿼리문 작성
				String query = "select *"
						+ " from qboard "
						+ " 	inner join users "
						+ "        on qboard.user_id = users.id "
						+ "order by idx desc";
				try {
					stmt = con.prepareStatement(query);
					//쿼리문 실행 및 결과반환(ResultSet)
					rs = stmt.executeQuery(query);
					//ResultSet의 크기만큼 즉, 인출된 레코드의 갯수만큼 반복
					while(rs.next()) {
						//DTO 저장
						QuestionDTO dto = new QuestionDTO();
						
						dto.setIdx(rs.getString("idx"));
						dto.setTitle(rs.getString("title"));
						dto.setContent(rs.getString("content"));
						dto.setUser_id(rs.getString("user_id"));
						dto.setNickname(rs.getString("nickname"));
						dto.setVisitcount(rs.getInt("visitcount"));
						dto.setPostdate("postdate");
						

						
						//레코드가 저장된 DTO를 List에 갯수만큼 추가한다.
						board.add(dto);
						
					}
				}
				catch (Exception e) {
					System.out.println("Question 게시물 조회 중 예외 발생");
					e.printStackTrace();
				}
				//마지막으로 인출한 레코드를 저장한 List를 반환한다.
				return board;
				
				
			}
			
			
			//검색 조건에 맞는 개시물의 개수 반환하기 OK
			public int selectCount(Map<String, Object> map) {
				int totalCount = 0;
				String query = "select count(*) "
								+ " from qboard "
								+ "    inner join users "
								+ "  on qboard.user_id = users.id";
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
					System.out.println("Question 게시물 카운트 중 예외 발생");
					e.printStackTrace();
				}
				
				return totalCount;
			}
			
		
			public List<QuestionDTO> selectListPage(Map<String, Object> map) {
			    List<QuestionDTO> board = new Vector<QuestionDTO>();
			    
			    String query = "SELECT * FROM ( "
			        + "    SELECT "
			        + "        q.idx, "
			        + "        q.title, "
			        + "        q.content, "
			        + "        q.user_id, "
			        + "        u.nickname, "
			        + "        q.visitcount, "
			        + "        q.postdate, "
			        + "        ROW_NUMBER() OVER (ORDER BY q.idx DESC) AS rNum "
			        + "    FROM qboard q "
			        + "    INNER JOIN users u ON q.user_id = u.id ";
			    
			    // 검색어 입력 여부에 따라 WHERE 절 추가
			    if (map.get("searchWord") != null) {
			        String column = map.get("searchField").equals("nickname") 
			            ? "u." + map.get("searchField")
			            : "q." + map.get("searchField");
			        
			        query += "    WHERE (" + column + " LIKE '%" + map.get("searchWord") + "%') ";
			    }
			    
			    query += ") WHERE rNum BETWEEN ? AND ?";
			    
			    try {
			        psmt = con.prepareStatement(query);
			        psmt.setString(1, map.get("start").toString());
			        psmt.setString(2, map.get("end").toString());
			        rs = psmt.executeQuery();
			        
			        while(rs.next()) {
			        	QuestionDTO dto = new QuestionDTO();
			            
			            dto.setIdx(rs.getString("idx"));
			            dto.setTitle(rs.getString("title"));
			            dto.setContent(rs.getString("content"));
			            dto.setUser_id(rs.getString("user_id"));
			            dto.setNickname(rs.getString("nickname"));
			            dto.setVisitcount(rs.getInt("visitcount"));
			            dto.setPostdate(rs.getString("postdate"));
			            
			            board.add(dto);
			        }
			    }
			    catch (Exception e) {
			        System.out.println("Question 게시물 조회 중 예외 발생");
			        e.printStackTrace();
			    }
			    
			    return board;
			}
					
			public QuestionDTO selectView(String idx) {
				//인출한 레코드를 저장하기 위해 DTO 생성
				QuestionDTO dto = new QuestionDTO(); //DTO 객체 생성
				//내부 조인(inner join)을 이용해서 쿼리문 작성. member 테이블의 name 컬럼까지 포함
				String query = "select "
						+ "    Q.*, U.nickname "
						+ "from qboard Q "
						+ "    inner join users U "
						+ "        on Q.user_id = U.id "
						+ " where idx = ? "; 
				try {
					psmt = con.prepareStatement(query); //쿼리문 준비
					psmt.setString(1, idx); //인파라미터 설정
					rs = psmt.executeQuery(); //쿼리문 실행
					
					//하나의 게시물을 인출하므로 if문으로 조건에 맞는 게시물이 있는지 확인
					if (rs.next()) { 
						// 결과를 DTO 객체에 저장 (member 테이블의 name까지 저장한다.)
						dto.setIdx(rs.getString("idx"));
						dto.setUser_id(rs.getString("user_id"));
						dto.setTitle(rs.getString("title"));
						dto.setContent(rs.getString("content"));
						dto.setPostdate(rs.getString("postdate"));
						dto.setVisitcount(rs.getInt("visitcount"));
						dto.setNickname(rs.getString("nickname"));
						
					}
					
				} catch (Exception e) {
					System.out.println("Question 게시물 상세보기 중 예외 발생");
					e.printStackTrace();
				}
				return dto; // 결과 반환
			}
				
			//주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
			public void updateVisitCount(String idx) {
				//visitcount 컬럼은 number 타입이므로 산술연산이 가능함
				//1을 더한 결과를 컬럼에 재반영하는 형식으로 update 쿼리문 작성
				String query = "UPDATE qboard "
								+ " SET visitcount = visitcount + 1 "
								+ " WHERE idx = ? ";
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, idx);
					/*
					쿼리실행시 주로 아래의 두가지 메서드를 사용한다.
					executeQuery() : select 계열의 쿼리문을 실행한다. 반환타입은 resultSet
					exectueUpdate() : insert, update, delete 계열의 쿼리문을 실행한다.
						반환타입은 int.
					만약 쿼리 실행 후 별도의 반환값이 필요하지 않다면 위 2개의 메서드 중 어떤 것을 
					사용해도 무방하다. 
					*/
					//psmt.executeQuery();
					int result = psmt.executeUpdate();
						
				} catch (Exception e) {
					System.out.println("Question게시물 조회수 증가 중 예외 발생");
					e.printStackTrace();
				}
			}
					
			public int deletePost(String idx) {
				int result = 0;
				try {
					String query = "DELETE FROM qboard where idx = ?";
					psmt = con.prepareStatement(query);
					psmt.setString(1, idx);
					result = psmt.executeUpdate();
				}
				catch (Exception e) {
					System.out.println("Question deletePost 자료실 게시물 삭제 중 예외 발생");
					e.printStackTrace();
				}
				return result;
			}
		
			//게시글 데이터를 받아 DB에 저장되어 있던 내용을 갱신합니다(파일 업로드 지원)
			public int updatePost(QuestionDTO dto) {
				int result = 0;
				try {
					//쿼리문 템플릿 준비. 회원제이므로 일련번호와 아이디까지 조건에 추가
					String query = "UPDATE qboard SET title=?, content=? WHERE idx=? and user_id=?";
					
					//쿼리문 준비 및 인파라미터 설정
					psmt = con.prepareStatement(query);
					psmt.setString(1, dto.getTitle());
					psmt.setString(2, dto.getContent());
					psmt.setString(3, dto.getIdx());
					psmt.setString(4, dto.getUser_id());
					
					//쿼리문 실행
					result = psmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("게시물 수정 중 예외 발생");
					e.printStackTrace();
				}
				
				return result;
			}
	
	
	
	
	
	
	
	
	

}
