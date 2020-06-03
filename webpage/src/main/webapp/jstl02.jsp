<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.bean.*,java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
	<style type="text/css">
		.row1{
			background-color:red; 
		}
		.row2{
			background-color: yellow;
		}
		
	</style>
</head>
<body>
		<%
			Emp emp = new Emp();
			emp.setName("zs");
			emp.setGendar("m");
			Emp emp2 = new Emp();
			emp2.setName("zs2");
			emp2.setGendar("m2");
			Emp emp3 = new Emp();
			emp3.setName("zs3");
			emp3.setGendar("m3");
			Emp emp4 = new Emp();
			emp4.setName("zs4");
			emp4.setGendar("m4");
			ArrayList<Emp> emps = new ArrayList<Emp>();
			emps.add(emp);
			emps.add(emp2);
			emps.add(emp3);
			emps.add(emp4);
			request.setAttribute("emps",emps);
		%>
		
		<table border="1">
			<tr>
				<td>姓名</td>
				<td>性别</td>
			</tr>
			<c:forEach var="emp" items="${emps }" varStatus="status">
				<tr class="row${status.index%2+1}">
					<td>${emp.name }</td>
					<td>${emp.gendar }</td>
				</tr>
			</c:forEach>
		</table>
		
</body>
</html>