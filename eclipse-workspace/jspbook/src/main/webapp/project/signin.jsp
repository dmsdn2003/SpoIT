<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 폼 만들기 예제</title>
<link rel="stylesheet" href="/jspbook/project/css/regform.css">
</head>
<body>
	<h2>회원 가입</h2>
	<hr>
	<div id="regform">
		<form name="form1" action="/user.nhn?action=signIn" method="post" onsubmit="return validateForm()">
			<label>ID</label><br>
			<input type="text" name="userID" size="40" required><br>

			<label>비밀번호</label><br>
			<input type="password" name="password" size="40" required><br>

			<label>비밀번호 재입력</label><br>
			<input type="password" name="rpassword" size="40" required><br>
			<span id="passwordError" class="error" style="display:none;">비밀번호가 일치하지 않습니다.</span>

			<label>이름</label><br>
			<input type="text" name="userName" size="40" required><br>

			<label>이메일</label><br>
			<input type="email" name="userEmail" size="40" required><br>

			<label>전화번호</label><br>
			<input type="text" name="userPhone" size="40" required><br>
			<hr>
			<button type="submit">가입</button>
		</form>
		<!-- JSTL을 이용한 에러 메시지 표시 예시 -->
		<c:if test="${not empty error}">
			<p style="color:red">${error}</p>
		</c:if>
	</div>
	<script src="/project/js/regform.js"></script>
</body>
</html>
