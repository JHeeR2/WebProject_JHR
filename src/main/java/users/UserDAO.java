package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.DBConnPool;

public class UserDAO extends DBConnPool {

	public UserDAO() {
		super();
	}
	
	//회원가입
	public int registerUser(UserDTO dto) {
		String query = "INSERT INTO users (id, pass, name, email, nickname, birthday, gender) VALUES (?,?,?,?,?,?,?)";
		int applyResult = 0;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getNickname());
			psmt.setString(6, dto.getBirthday());
			psmt.setString(7, dto.getGender());
			
			applyResult = psmt.executeUpdate();
		}
		catch (Exception e){
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		
		return applyResult;
	}
	
	// 회원 가입 과정 중 아이디, 이메일, 닉네임  중복 검사
	public boolean checkDuplicate(String column, String value) {
	    String query = "SELECT COUNT(*) FROM users WHERE " + column + " = ?";
	    try (PreparedStatement psmt = con.prepareStatement(query)) {
	        psmt.setString(1, value);
	        try (ResultSet rs = psmt.executeQuery()) {
	            return rs.next() && rs.getInt(1) > 0;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	//로그인 기능
	public UserDTO UserLogin(String uid, String upass) {
		UserDTO dto = null;
		String query = "SELECT * FROM users where id = ? and pass = ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setNickname(rs.getString("nickname"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//유저 정보 불러오기
	public UserDTO getUserInfo(String userId) {
		UserDTO userInfo = null;
		String query = "SELECT id, name, email, nickname, birthday, gender, regidate FROM users where id = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				userInfo = new UserDTO();
				userInfo.setId(rs.getString("id"));
				userInfo.setName(rs.getString("name"));
				userInfo.setEmail("email");
				userInfo.setNickname(rs.getString("nickname"));
				userInfo.setBirthday(rs.getString("birthday"));
				userInfo.setGender(rs.getString("gender"));
				userInfo.setRegidate(rs.getDate("regidate"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public boolean confirmPassword(String id, String pass) {
		boolean isCorr = true;
		String sql= "SELECT COUNT(*) from users where id=? and pass= ?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				isCorr = false;
			}
			
		} catch (Exception e) {
			isCorr = false;
			e.printStackTrace();
		}
		
		return isCorr;
		
	}
	
	

	
	@Override
	public void close() {
		super.close();
	}
	
	

}
