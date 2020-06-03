# JSP



### 1）什么是Jsp

java server page(java服务端页面技术)，sun公司制定的一种服务端动态页面生成技术的规范。

没有jsp之前使用servlet生成页面，如果页面比较复杂，代码过于繁琐，难以维护，复杂的页面使用Jsp编写。

### 2)如何写一个JSP

​	.jsp作为后缀，在文件当中添加html和java代码，编写完成后不需要编译，当客户端请求访问某个.jsp文件。服务器会自动将.jsp文件转换成一个.java文件（servlet）

### 3)jsp文件的组成

#### 		a.html(css、javascript):直接写在jsp文件中

#### 		b.java代码

​					第一种形式：Java代码片段

​									**语法：<% Java代码 %>**

​					第二种形式：jsp表达式

​									**语法：<%= Java表达式%>**														

#### 		c.指令

​					 所谓指令，告诉jsp引擎（负责将.jsp文件转换成.java文件，并在运行时，为jsp提供一些辅助支持的模块）,将在.jsp文件转换成.java文件时，做一些额外的处理。

​					语法：<% 指令名 属性名=属性值 %>

​					page指令	

​					pageEncoding:告诉jsp引擎，.jsp文件保存时的编码。		

​					contentType：等价于servlet中编写的,reponse.setContentType();

```jsp
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>\
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
```

#### 		d.隐含对象

​					在.jsp文件当中，不用声明和创建该对象，就可以直接使用的对象，原因是.jsp文件对应.java文件当中，已经自动生成了该对象的代码。

​				 out：输出

 				request:请求

​				 response:响应

#### 4)jsp文件如何转换成.java文件

​				html转换成jsp

​				放到service方法中，使用out.write()输出

​				

### 4)jstl与EL表达式是什么？

#### 			4.1jstl是什么？

​				java standard taglib(Java标准标签库)

#### 			4.2标签是什么？

​							sun公司指定的一种技术规范，用来将jsp页面中的java代码使用类似于Html 当中的标记来替换的一种技术。这样做的目的，为了使jsp页面更好维护以及更好的测试。

#### 		  4.3标签库的使用

​					1）需要添加2个Jar包：jstl.jar      standard.jar

​					2）在jsp文件当中，使用taglib指令导入标签

​					3）使用标签

```jsp
<c:if test=" " var="" scope=""></c:if>
                                  test属性：当值为true时，执行标签体的内容，false则不执行
                                   var属性：指定一个绑定名
                                  scope属性：指定一个绑定范围
```

```jsp
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

                        用于遍历集合,items属性：指定要遍历的集合。
                                    var属性：指定一个绑定名，.jsp引擎会从集合中取一个对象，绑定到pageContext对象上 
                                    varStatus属性：指定一个绑定名，对应的绑定值是一个java对象，封装了遍历时的一些信息，
                                    包括当前遍历的下标以及是第几次遍历
```



#### 		  4.4el表达式

​						sun指定的一种用于计算的一种规则，最早只能用于给标签的属性赋值，现在也可以直接输出。

#### 			4.5el表达式的基本语法

​						${el表达式}























