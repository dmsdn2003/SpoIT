<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="ch09.*"%>
<!DOCTYPE html">
<jsp:useBean id="adao" class="ch09.AddrDAO" scope="application" />
<jsp:useBean id="avo" class="ch09.AddrDO" />
<jsp:setProperty name="avo" property="*" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addr list jsp</title>
</head>
<body>
	<div align="center">
		<H2>주소록</H2>
		<HR>
		<a href="addr_form.html">주소추가</a>
		<P>
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td>전화번호</td>
				<td>이메일</td>
				<td>성별</td>
			</tr>
			<%
			for (AddrDO ado : adao.getAddrList()) {
			%>
			<tr>
				<td><%=ado.getUsername()%></td>
				<td><%=ado.getTel()%></td>
				<td><%=ado.getEmail()%></td>
				<td><%=ado.getGender()%></td>
			</tr>
			<%
}
%>
		</table>
	</div>
</body>
</html>