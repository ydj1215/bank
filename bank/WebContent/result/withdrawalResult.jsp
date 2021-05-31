<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String result = (String)request.getAttribute("result");
		if(result == null)
		{
	%>
			${id }/${money }/${tMoney } 
	<%  }else{ //result가 null이 아니면
				out.print(result);
			 }
	%>
	<p>
	<%@ include file = "homeMenu.jsp" %>
	</p>
</body>
</html>