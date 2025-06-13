<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>회원 탈퇴</h2>
<form action="/user.nhn?action=delete" method="post">
    <input type="text" name="userID" placeholder="아이디 입력" required>
    <input type="password" name="password" placeholder="비밀번호 입력" required>
    <button type="submit">회원탈퇴</button>
    <c:if test="${not empty error}">
        <div style="color:red">${error}</div>
    </c:if>
</form>