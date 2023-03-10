<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var msg = "${mag}"
	if(msg) {
		alert(msg);
	}
	location.href="<%=request.getContextPath()%>/";
</script>