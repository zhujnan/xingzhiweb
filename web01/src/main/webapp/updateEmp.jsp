<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page import="com.web01.entity.*,java.util.*,java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>添加员工</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								<%
									Date date = new Date();
									SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
									out.println(fmt.format(date));
							 	%>	
							
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								员工信息管理系统
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						修改员工信息:
					</h1>
					
					 
					<form action="modify.do?id=${e.id}" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									ID:
								</td>
								<td valign="middle" align="left">
									${e.id}
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									姓名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" value="${e.name}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									pwd:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="salary" value="${e.pwd}"/>
								</td>
							</tr>
							
						</table>
						<p>
							<input type="submit" class="button" value="确认" />
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>