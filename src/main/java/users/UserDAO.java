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
	
	// 회원 가입 과정 중 아이디 중복 검사
	public boolean checkIdDuplicate(String userId) {
		String query = "Select count (*) from users where id = ?";
		try (PreparedStatement psmt = con.prepareStatement(query)) {
			psmt.setString(1, userId);
			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					// 중복된 아이디 존재, true 반환
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 회원 가입 과정 중 이메일 중복 검사
	public boolean checkEmailDuplicate(String userEmail) {
		String query = "Select count (*) from users where email = ?";
		try (PreparedStatement psmt = con.prepareStatement(query)) {
			psmt.setString(1, userEmail);
			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					// 중복된 이메일 존재, true 반환
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 회원 가입 과정 중 닉네임 중복 검사
	public boolean checkNicknameDuplicate(String userNickname) {
		String query = "Select count (*) from users where nickname = ?";
		try (PreparedStatement psmt = con.prepareStatement(query)) {
			psmt.setString(1, userNickname);
			rs = psmt.executeQuery();
			if (rs.next()) {
				// 중복된 닉네임 존재, true 반환
				return rs.getInt(1) > 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	
	//로그인 기능
	public UserDTO getUser(String uid, String upass) {
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
	
	@Override
	public void close() {
		super.close();
	}
	
	

}
