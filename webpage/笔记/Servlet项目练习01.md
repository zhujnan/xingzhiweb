**Servlet项目练习01——login功能实现**



### <span id="项目演示">一、项目演示

​	![](http://tu.eagleslab.com/images/2020/05/29/1877d6a79193097870cf7beb868ac8af.png)
</span>
### <span id="项目说明">二、项目说明

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如何设计一个程序的结构，这是一门专门的学问，叫做"架构模式"（architectural pattern），属于编程的方法论。MVC模式就是架构模式的一种，它不仅适用于开发软件，也适用于其他广泛的设计和组织工作比如**公司、政党、政府、医院、学校等等，这些组织都可以从MVC模式的角度，进行架构，由一个个执行特定功能、可重复使用的模块组成**。再比如一家商场，完全可以分成三部分。一部分是仓库，负责提供商品，这是"功能层"（或者"数据层"）；另一部分是零售铺面，负责销售商品，这是它的"外观层"；两者之间就是"机制层"，包括柜台和仓库之间一切互动的机制。那么什么是MVC模式呢？
</span>
**MVC是三个单词的首字母缩写，它们是Model（模型）、View（视图）和Controller（控制）。**

![](http://tu.eagleslab.com/images/2020/05/29/34f6e6765c22d13debcf5f1fb4d3df76.md.png)

这个模式认为，程序不论简单或复杂，从结构上看，都可以分成三层。

> 1）最上面的一层，是直接面向最终用户的"视图层"（View）。它是提供给用户的操作界面，是程序的外壳。
>
> 2）最底下的一层，是核心的"数据层"（Model），也就是程序需要操作的数据或信息。
>
> 3）中间的一层，就是"控制层"（Controller），它负责根据用户从"视图层"输入的指令，选取"数据层"中的数据，然后对其进行相应的操作，产生最终结果。

​		这三层是紧密联系在一起的，但又是互相独立的，每一层内部的变化不影响其他层。每一层都对外提供接口（Interface），供上面一层调用。这样一来，软件就可以实现模块化，修改外观或者变更数据都不用修改其他层，大大方便了维护和升级。

​			

### <span id="项目开发">三、项目开发</span>

**本项目采用jsp+servlet+mysql员工管理系统，登陆功能的实现。项目整体采用MVC模式**

#### <span id="3.1">3.1项目架构(后端)</span>

![](http://tu.eagleslab.com/images/2020/05/29/cba7d0f930dd5e83bc8b5e6ec8946d8d.png)

#### <span id="3.2">3.2添加依赖文件(添加jar包)</span>				

```xml
<dependencies>
			<dependency>
			     <groupId>org.apache.tomcat</groupId>
			     <artifactId>tomcat-servlet-api</artifactId>
			     <version>7.0.63</version>
			</dependency>
			<dependency>
			    <groupId>mysql</groupId>
			    <artifactId>mysql-connector-java</artifactId>
			    <version>5.1.25</version>
			</dependency>
			<!-- mysql8版本以上专用驱动包依赖 -->
			  <dependency>
		        <groupId>mysql</groupId>
		        <artifactId>mysql-connector-java</artifactId>
		        <version>8.0.13</version>
    		</dependency>
  </dependencies>
```

#### <span id="3.3">3.3添加jdbc工具类</span>

​		数据库连接属性文件

​		如果mysql数据库的版本为1.8以上，此时驱动为<font color='red'>com.mysql.cj.jdbc.Driver</font>，驱动jar包版本为<font color='red'>8.0.13</font>

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC
username=root
password=111111
 
```



```java
package com.web01.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接工具类
 * @author zhujn
 */
public class ConnectionUtils {
	private static String url;
	private static String dbUser;
	private static String dbPassword;
	private static String driver;
	//静态代码块在类加载的时候执行
	static {
		Properties prop = new Properties();
		try {
			prop.load(ConnectionUtils.class 
					//获取Class对象
					.getClassLoader() 
					//通过Class对象获取类加载器
					.getResourceAsStream("pro.properties"));
					//通过类装载器对象调用getResourceAsStream方法
					//将属性文件中的内容读入到prop对象中
			if(prop!=null) {
				url = prop.getProperty("url");
				driver = prop.getProperty("driver");
				dbUser = prop.getProperty("username");
				dbPassword = prop.getProperty("password");
				try {
					Class.forName(driver);
					//通过反射技术创建驱动类对象
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}		
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*public static void getParam(String fileName) throws IOException {
		Properties properties
			= new Properties();
		try {
			FileInputStream fis = 
					new FileInputStream(fileName);
			properties.load(fis);
			url = properties.getProperty("url");
			dbUser = properties.getProperty("username");
			dbPassword = properties.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	//获取连接对象
	public static Connection getConnection() throws IOException, SQLException {
		//getParam("src/main/java/pro.properties");
		Connection conn = null;
		return conn = DriverManager.getConnection(url,dbUser,dbPassword);
	}
	//关闭连接
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//关闭语句对象
	public static void closeStatement(Statement stmt) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//关闭语句对象
		public static void closeResultSet(ResultSet rest) {
			if(rest!=null) {
				try {
					rest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public static void main(String[] args) throws IOException, SQLException {
			System.out.println(getConnection() );
		}
}

```

#### <span id="3.4">3.4、用户查到之后，跳转到新的页面，使用转发来完成页面的跳转。</span>

```java
package com.web01.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;

/**
 * 处理登陆请求
 */
public class LoginAction extends HttpServlet {
            private static final long serialVersionUID = 1L;
            private IUserDao userDao = new UserDaoImpl();
            public LoginAction() {
                super();
            }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 			  ServletException, IOException {
            System.out.println("hello,login");
            String userName = request.getParameter("username");
            String passWorld = request.getParameter("pwd");
            //"mayun".equals(userName)&&"123456".equals(passWorld)这种方式被写死显然不是我们
            //想要的
            boolean bool = false;
            try {
                bool = userDao.login(userName,passWorld);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(bool) {
                        request.getRequestDispatcher("success.jsp")
                                .forward(request, response);
            		}
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
			}
}

```

#### <span id="3.5">3.5、数据访问层实现</span>

```java
package com.web01.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.web01.dao.IUserDao;
import com.web01.util.ConnectionUtils;

public class UserDaoImpl implements IUserDao{
	/**
	 * jdbc实现来完成，数据的验证功能，
	 * 自行实现。
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean login(String userName, String passWorld) throws Exception {
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		conn = ConnectionUtils.getConnection();
		String sql = "select * from emps where name =? and pwd=?";
		stmt =(PreparedStatement) conn.prepareStatement(sql); //预编译
		stmt.setObject(1, userName);
		stmt.setObject(2, passWorld);
		rs = stmt.executeQuery(); //改成prepareStatement对象时，要把Sql去掉。
		if(rs.next()) {
			return true;
		}
		rs.close();
		stmt.close();
		conn.close();
		return false;
	}
	public static void main(String[] args) throws Exception {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		System.out.println(userDaoImpl.login("zs","123"));
	}
}

```

------

#### <span id="4.1">4.1项目架构（前端）</span>

**<font color='blue'>请求发起页面(登录)</font>**

​		action 属性规定当提交表单时，向何处发送表单数据,method=get或post指明提交表单的HTTP方法.可能的值为：
​				**<font color='red'>post</font>：POST方法在表单的主干包含名称/值对并且无需包含于action特性的URL中 .**
​				**<font color='red'>get</font>：不赞成。GET方法把名称/值对加在action的URL后面并且把新的URL送至服务器.这是往前兼容的缺省值.这个值由于国际化的原因不赞成使用.**

```java
<form action="login.do" method="post">
		<table cellpadding="0" cellspacing="0" border="0"class="form_table">
							<tr>
								<td></td>
								<td style="color: red">
									${result}
									${checkcode_msg}						
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
                                <td valign="middle" align="left">
                                    <input type="text" class="inputgri" name="username"/>
                                </td>
								<span class="tips">${username}</span>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密&nbsp;&nbsp;&nbsp;&nbsp;码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="pwd" />
								</td>
								<span class="tips">${password}</span>	
							</tr>
		  </table>
		  <p>
				<input type="submit" class="button" value="确认 &raquo;" />
		  </p>
</form>
```

