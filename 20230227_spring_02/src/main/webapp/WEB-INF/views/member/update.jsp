<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<h1>회원정보수정</h1>
<form action="update" method="post">
	<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
	<input name="passwd" value="${membervo.passwd }" type="password"placeholder="pass"><br>
	<input name="name"  value="${membervo.name }"  type="text" readonly="readonly"><br>
	<input name="email" value="${membervo.email }" type="text" placeholder="email"><br>
	<button type="submit">제출</button>
</form>
</body>
</html>