<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>${board.boardNum } 게시글</h1>
<div>
	<p>${board.boardTitle }</p>
</div>	
<div>
	<p>${board.boardContent }</p>
</div>	

<form id="frmReply">
	<fieldset>
	<legend>답글작성</legend>
	<div><input type="text" name="boardTitle" ></div>
	<div><input type="text" name="boardContent" ></div>
	<input type="hidden" name="boardNum" value="${board.boardNum }">
	<button type="button" class="btn reply" >답글작성</button>
	</fieldset>
</form>

<hr>
<h4>댓글</h4>
<table border="1">
	<thead>
	
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${replyList }" var="reply">
				<tr>
					<td>${reply.boardNum }</td>
					<td><a href="<%=request.getContextPath()%>/board/read?boardNum=${reply.boardNum }">${reply.boardTitle }</a></td>
					<td>${reply.boardWriter }</td>
					<td>${reply.boardDate }</td>
					<td>${reply.boardReadcount }</td>
				</tr>
		</c:forEach>
	</tbody>
</table>

<script>
	$(".btn.reply").on("click", replyClickHandler);
	
	function replyClickHandler(){
		console.log(this);   // this 는 DOM 형태 
		console.log($(this));  // this 는 Jquery 형태 
		//$(this).parents("form")
		$.ajax({ url:"<%=request.getContextPath()%>/board/insertReplyAjax"
			, type:"post"
			, data: $("#frmReply").serialize()
	
			, dataType:"json" // secess 에 들어오는 데이터가 json 모양일 것이고 이것은 js object 로 변형해서 result 에 실어줌
			, success: function(result){ 
				console.log(result);
				console.log(result[0].boardDate);
			//	 $("#frmReply")[0].reset(); // 작성된 글 초기화
			frmReply.reset(); // 작성된 글 초기화 
				if(result.length > 0) {
					alert("댓글작성되었습니다.")
				} else {
					alert("댓글작성실패했습니다.")
				}
				// 답글 부분 화면 업데이트 
				displayReply(result);
			}
			, error: function(){}
		
		});
		
		
	} 
	
	function displayReply(result){
		console.log(result);
		console.log(result[0].boardDate);
		
		
		
		var htmlVal = '';
		for(i=0; i<result.length; i++) {
			var reply = result[i];
			html += '<tr>';
			html += '<td>'+reply.boardNum+'</td>';
			html += '<td><a href="<%=request.getContextPath()%>/board/read?boardNum='+reply.boardNum+'">'+reply.boardTitle+'</a></td>';
			html += '<td>'+reply.boardWriter+'</td>';
			html += '<td>'+reply.boardDate+'</td>';
			html += '<td>'+reply.boardReadcount+'</td>';
			html += '</tr>';
			
		}
		
		$("tbody").html(htmlVal);
		
		
		
		
		
		
		
		
		
		
	}
</script>


</body>
</html>