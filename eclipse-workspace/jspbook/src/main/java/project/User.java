package project;

public class User {
	private String userID;
	private String password;
	private String userName;
	private String userEmail;
	private String userPhone;
	private java.sql.Timestamp userJoindate;
	
	// Getter & Setter
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public java.sql.Timestamp getUserJoindate() {
		return userJoindate;
	}
	public void setUserJoindate(java.sql.Timestamp userJoindate) {
		this.userJoindate = userJoindate;
	}
} 