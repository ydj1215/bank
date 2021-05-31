<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${id } : logout!
	<%
		session.invalidate(); //session 객체를 없애기 때문에 아이디또한 없어진다.
	%>
	<p>
	<%@ include file = "home.jsp" %>
	</p>
</body>
</html>