<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
		<%
			User user  = new User();
			user.setName("abc");
			user.setAge(22);
			IDCard card = new IDCard();
			user.setCard(card);
			card.setCardNo("100");
			user.setInterest(new String[]{"a","b"});
			request.setAttribute("user1",user);
		%>
		<!-- 1.Java代码 -->
		<%
			User user1 = (User)request.getAttribute("user1");
			out.println(user1.getName());
		%>
		<!-- 2.el表达式来取 -->
		
				<!-- 2.1基本类型 -->
						${user1.name}<br/>	
					  	${user1["name"] }<br/>	
						${user1.age }<br/>	
				<!-- 2.2引用类型 -->
						${user1.card.cardNo}<br/>	
						${ user1.interest[1]}<br/>
		<!-- 3.el表达式运算 -->	
			${1+2}<br/>			<!-- 数学运算 -->
			${"abc" eq "abc"} 	<!-- 关系运算 -->
		
</body>
</html>