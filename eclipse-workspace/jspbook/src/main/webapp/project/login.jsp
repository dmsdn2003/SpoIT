<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" href="/jspbook/project/css/login.css">
</head>
<body>
    <div class="logo-container">
        <img src="/jspbook/project/img/SpoIT_logo.png" alt="SpoIT Logo">
    </div>
    <div id="logform">
        <% 
        String error = (String)request.getAttribute("error");
        if(error != null) { 
        %>
            <div style="color: red; margin-bottom: 10px;">
                <%= error %>
            </div>
        <% } %>
        <form action="${pageContext.request.contextPath}/user.nhn" method="post">
            <input type="hidden" name="action" value="login">
            <label>ID</label><br>
            <input type="text" name="userID" required><br>
            <label>Password</label><br>
            <input type="password" name="userPassword" required><br>
            <hr>
            <button type="submit">로그인</button>
        </form>
        <div style="margin-top: 10px;">
            <a href="/jspbook/project/signin.jsp">회원가입</a>
        </div>
        <div style="margin-top: 10px;">
            <a href="/jspbook/project/deleteUser.jsp">회원탈퇴</a>
        </div>
    </div>
</body>
</html> 