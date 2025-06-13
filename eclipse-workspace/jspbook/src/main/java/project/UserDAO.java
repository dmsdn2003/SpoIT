package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/tukoreadb";

	// DB 연결을 가져오는 메서드
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"tukorea","1234");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 로그인 메서드
	public User login(String userID, String password) throws SQLException {
		Connection conn = open();
		User user = null;
		String sql = "select * from users where userID=? and password=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userID);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("userID"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserPhone(rs.getString("userPhone"));
				user.setUserJoindate(rs.getTimestamp("userJoinDate"));
			}
			rs.close();
		}
		return user;
	}

	// 회원가입 메서드
	public void signIn(User u) throws Exception {
		Connection conn = open();
		String sql = "insert into users(userID, password, userName, userEmail, userPhone, userJoinDate) values(?,?,?,?,?,CURRENT_TIMESTAMP());";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, u.getUserID());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getUserName());
			pstmt.setString(4, u.getUserEmail());
			pstmt.setString(5, u.getUserPhone());
			pstmt.executeUpdate();
		}
	}
	
	// 회원 삭제 메서드
	public void delUser(String userID) throws SQLException {
		Connection conn = open();
		String sql = "delete from users where userID=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userID);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("해당 사용자가 존재하지 않습니다.");
			}
		}
	}
} 