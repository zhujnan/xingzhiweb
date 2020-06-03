<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.bean.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
		<%
			Emp emp = new Emp();
			emp.setName("zs");
			emp.setGendar("m");
			request.setAttribute("e",emp);
		%>
		
		用户名：${e.name}<br/>
		性别：<c:if test="${e.gendar eq 'f'}" 
					var="rs" scope="request">
					男
			  </c:if>
			  <c:if test="${!rs}">
			  		女
			  </c:if><br/>
		性别2：
			  <c:choose>
			  		<c:when test="${e.gendar=='m'}">男</c:when>
			  		<c:otherwise>女</c:otherwise>
			  </c:choose>
</body>
</html>