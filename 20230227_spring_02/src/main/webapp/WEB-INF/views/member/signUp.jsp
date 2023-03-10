<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<script>
	var msg= "${msg}";
	if(msg){
		alert(msg);
	}
</script>
<form action="signUp" method="post">
	<input type="text" name="id" placeholder="id"><br>
	<input type="password" name="passwd" placeholder="pass"><br>
	<input type="text" name="name" placeholder="name"><br>
	<input type="text" name="email" placeholder="email"><br>
	<input name="bbb" value="아무거나" type="text" placeholder="bbb"><br>
	<button type="submit">제출</button>
</form>
</body>
</html>