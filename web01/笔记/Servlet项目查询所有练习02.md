**Servlet项目练习01——login功能实现**



### 一、项目演示

​	![](http://tu.eagleslab.com/images/2020/05/31/1c6bf96b52c3f7b540b6732511af9936.png)

### 二、项目说明

​			用户登陆成功之后进入到查询所有页面，将数据库中的信息查询出来然后再页面中显示。

![](http://tu.eagleslab.com/images/2020/05/31/2dad830e09ced74bec7db848d5b8619e.png)

​		在之前的案例当中我们采用<font color='red'>转发</font>来跳转到一个页面，这个页面仅仅只是一个普通的静态页面并不需要去访问后端服务。而我们此时需要访问后台服务拿到数据中的数据，这时候就需要采用一种新的方式<font color='red'>重定向</font>。



**转发与重定向的区别：**

>   		**<font color='red'>一句话，转发是服务器行为，重定向是客户端行为</font>。**为什么这样说呢，这就要看两个动作的工作流程：
>
> <font color='blue'>请求转发（RequestDispatcher）</font>的过程
>
>     			客户首先发送一个请求到服务器端，服务器端发现匹配的servlet，并指定它去执行，当这个servlet执行完之后，它要调用getRequestDispacther()方法，把请求转发给指定的test.jsp,整个流程都是在服务器端完成的，而且是在同一个请求里面完成的，因此servlet和jsp共享的是同一个request，在servlet里面放的所有东西，在jsp中都能取出来，因此，jsp能把结果getAttribute()出来，getAttribute()出来后执行完把结果返回给客户端。整个过程是一个请求，一个响应。
>
> <font color='blue'>重定向（sendRedirect）</font>的工作原理：
>
>     			客户发送一个请求到服务器，服务器匹配servlet，这都和请求转发一样，servlet处理完之后调用了sendRedirect()这个方法，这个方法是response的方法，所以，当这个servlet处理完之后，看到response.senRedirect()方法，立即向客户端返回这个响应，响应行告诉客户端你必须要再发送一个请求，去访问test.jsp，紧接着客户端受到这个请求后，立刻发出一个新的请求，去请求test.jsp,这里两个请求互不干扰，相互独立，在前面request里面setAttribute()的任何东西，在后面的request里面都获得不了。可见，在sendRedirect()里面是两个请求，两个响应。

​	

​			

### 三、项目开发

​		**本项目采用jsp+servlet+mysql员工管理系统，登陆功能的实现。项目整体采用MVC模式**

#### 3.1项目架构(后端)

在上个版本的基础上添加如下内容

![](http://tu.eagleslab.com/images/2020/05/31/dbea19314bc13e1b3f48ca8a7502996f.png)

#### 3.2添加jsp相关依赖(添加jar包)				

```xml
<dependencies>
		<dependency>
			    <groupId>taglibs</groupId>
			    <artifactId>standard</artifactId>
			    <version>1.1.2</version>
			</dependency>
    		<dependency>
			    <groupId>javax.servlet.jsp.jstl</groupId>
			    <artifactId>jstl</artifactId>
			    <version>1.2</version>
			</dependency>
    		  <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.web</groupId>
            <artifactId>jstl-impl</artifactId>
            <version>1.2</version>
        </dependency>
  </dependencies>
```

#### 3.3重构login登陆（使用重定向）

![](http://tu.eagleslab.com/images/2020/05/31/bf54eb0340a26a425050116699764093.png)

```java
package com.web01.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;

/**
 * 处理登录请求
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IUserDao userDao = new UserDaoImpl();
	
    public LoginAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hello,login");
		String userName = request.getParameter("username");
		String passWorld = request.getParameter("pwd");
		//"mayun".equals(userName)&&"123456".equals(passWorld)
		boolean bool = false;
		try {
			bool = userDao.login(userName,passWorld);
			System.out.println(bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bool) {
		  /*request.getRequestDispatcher("success.jsp").forward(request, response);*/
			response.sendRedirect("list.do");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

```

#### 3.4、查询所有

```java
package com.web01.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;

/**
 * Servlet implementation class ListAction
 */
public class ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();
    public ListAction() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Emp> emps = userDao.findAll();
		request.setAttribute("employees", emps);
		RequestDispatcher rd = request.getRequestDispatcher("emplist2.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

```

------

#### 4.1项目架构（前端）

**<font color='blue'>页面渲染</font>**

​	

[JSTL标签库]: https://www.cnblogs.com/hoobey/p/6700676.html

：

​    el表达式：

```java
<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="com.web01.dao.*,com.web01.util.*,java.util.*,java.text.SimpleDateFormat" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>员工列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<style type="text/css">
			.row1{
				color:#cccccc;
			}
			
			.row2{
				color:#yellow;
			}
		
		
		</style>
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
							<a href="#">员工信息管理系统</a>
						</h1>
					</div>
					<div id="navigation">
					
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						员工列表
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								姓名
							</td>
					
							<td>
								年龄
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach var="e" items="${employees}" varStatus="status">
							<tr>
								<td>
									${e.id}
								</td>
								<td>
									${e.name}
								</td>
								<td>
									${e.pwd}
								</td>
								<td>
									<a href="delete.do?id=${e.id}"  onclick="return confirm('是否确定删除')">删除</a>&nbsp;<a href="load.do?id=${e.id}">修改</a>
								</td>
							</tr>
						
						</c:forEach>	
						
					</table>
					<p>
						<input type="button" class="button" value="添加员工" onclick="location='<%=request.getContextPath() %>/addEmp.jsp'"/>
					</p>
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
```

