<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="ch09.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="avo" class="ch09.AddrDO" />
<jsp:setProperty name="avo" property="*" />
<jsp:useBean id="adao" class="ch09.AddrDAO" scope="application" />
<%
	adao.add(avo);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addr add jsp</title>
</head>
<body>
	<div align=center>
		<H2>등록 내용</H2>
		이름 :
		<jsp:getProperty property="username" name="avo" /><P>
			전화번호 :
			<%=avo.getTel()%>
		<P>
			이메일 :
			<%=avo.getEmail()%>
		<P>
			성별 :
			<%=avo.getGender()%>
		<HR>
		<a href="addr_list.jsp">목록 보기</a>
	</div>
</body>
</html>