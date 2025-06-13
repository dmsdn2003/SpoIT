<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
Connection conn = null;
PreparedStatement pstmt = null;
String jdbc_driver = "com.mysql.cj.jdbc.Driver";
String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
try {
	Class.forName(jdbc_driver);
	conn = DriverManager.getConnection(jdbc_url, "root", "1234");
	String sql = "insert into jdbc_test values(?,?)";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, request.getParameter("username"));
	pstmt.setString(2, request.getParameter("email"));
	if (request.getParameter("username") != null) {
		pstmt.executeUpdate();
	}
} catch (Exception e) {
	System.out.println(e);
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JDBC TEST JSP</title>
</head>
<body>
	<div align="center">
		<H2>조회할 이름을 입력하세요.</H2>
		<HR>
		<form name=form1 method=post>
			등록이름 : <input type=TEXT name=username> 주소 : <input type=text
				name=email size=20> <input type=submit value="등록">
		</form>
		<HR>
	</div>
	#등록 목록
	<P>
		<%
		try {
			String sql = "select id, passwd, username, snum, depart, mobile, email from student";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				out.println(i + ": " + rs.getString(1) + ", " + rs.getString(2) + "<BR>");
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		%>
	
</body>
</html>