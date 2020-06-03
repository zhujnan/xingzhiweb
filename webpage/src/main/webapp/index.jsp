<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
		<body>
				now time<%
							out.println(new Date());
						 %>
						<br/><!-- 换行标签 -->
						
				今天是:<%=new Date() %>	
				<%
					for(int i=0;i<5;i++){
						%>
						hello jsp<br/>
						<%
							}
						%>
		</body>
</html>