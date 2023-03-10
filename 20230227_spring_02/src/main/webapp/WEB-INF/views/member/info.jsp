<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>내정보</h1>
<form id="frmInfo" method="get">
	<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password"placeholder="readonly"><br>
	<input value="${membervo.name }"  type="text" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" placeholder="readonly"><br>
	<button type="button" onclick="frmSubmit('update');">수정</button>
	<button type="button" onclick="frmSubmit('delete');">탈퇴</button>
</form>

<script>
	function frmSubmit(targetEle) {
		/* if($(targetEle).text() == '수정') {
			$("#frmInfo").attr("action","update");
		} else if($(targetEle).text() == '탈퇴') {
			$("#frmInfo").attr("action","delete");
		}  */
/* 			$("#frmInfo").attr("action",tergetEle);*/
		frmInfo.action = targetEle;
		$("#frmInfo").submit();
	}
</script>
</body>
</html>