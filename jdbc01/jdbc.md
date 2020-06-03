# JDBC

### 一、概述
​			JDBC(Java Database Connectivity)是一个独立于特定数据库管理系统、通用的SQL数据库存取和操作的公共接口（一组API），定义了用来访问数据库的标准Java类库，使用这个类库可以以一种标准的方法、方便地访问数据库资源JDBC为访问不同的数据库提供了一种统一的途径，为开发者屏蔽了一些细节问题。
​			JDBC的目标是使Java程序员使用JDBC可以连接任何提供了JDBC驱动程序的数据库系统，这样就使得程序员无需对特定的数据库系统的特点有过多的了解，从而大大简化和加快了开发过程。

## 		 					　　　　　　<img src="https://i.bmp.ovh/imgs/2020/05/5d382e326fd5db98.png" style="zoom: 80%;" />

JDBC API 是一系列的接口，它使得应用程序能够进行数据库联接，执行SQL语句，并且得到返回结果。

　　　			<img src="https://i.bmp.ovh/imgs/2020/05/c5e5922157307bf2.png" style="zoom: 67%;" />

### 二、连接步骤

#### 			2.1、加载与注册 JDBC 驱动

​				加载 JDBC 驱动需调用 Class 类的静态方法 forName()，向其传递要加载的 JDBC 驱动的类名
DriverManager 类是驱动程序管理器类，负责管理驱动程序通常不用显式调用 DriverManager 类registerDriver() 方法来注册驱动程序类的实例，因为 Driver 接口的驱动程序类都包含了静态代码块，在这个静态代码块中，会调用 DriverManager.registerDriver() 方法来注册自身的一个实例

#### 			2.2、建立连接

​			可以调用 DriverManager 类的 getConnection() 方法建立到数据库的连接JDBC URL 用于标识一个被注册的驱动程序，驱动程序管理器通过这个 URL 选择正确的驱动程序，从而建立到数据库的连接。

​			 JDBC URL的标准由三部分组成，各部分间用冒号分隔。 
​			 jdbc:<子协议>:<子名称>
​			 协议：JDBC URL中的协议总是jdbc 
​			子协议：子协议用于标识一个数据库驱动程序
​			子名称：一种标识数据库的方法。子名称可以依不同的子协议而变化，用子名称的目的是为了定位数据库提供足够的信息

​						<img src="https://i.bmp.ovh/imgs/2020/05/bb7c544f4a73bcce.png" style="zoom: 67%;" />

#### 			2.3、创建语句对象

​					通过调用 Connection 对象的 createStatement 方法创建该对象该对象用于执行静态的 SQL 语句，并且返回执行结果Statement 接口中定义了下列方法用于执行 SQL 语句：
​					ResultSet excuteQuery(String sql)
​					int excuteUpdate(String sql)

```java
	Statement stmt = (Statement) conn.createStatement();
```

#### 			2.4、处理结果集

​				通过调用 Statement 对象的 excuteQuery() 方法创建该对象。
ResultSet 对象以逻辑表格的形式封装了执行数据库操作的结果集，ResultSet 接口由数据库厂商实现
ResultSet 对象维护了一个指向当前数据行的游标，初始的时候，游标在第一行之前，可以通过 ResultSet 对象的 next() 方法移动到下一行

```java
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(id+","+name+","+age);
		}
```

#### 			2.5、关闭连接

### 三、SQL注入

​	SQL 注入是利用某些系统没有对用户输入的数据进行充分的检查，而在用户输入数据中注入非法的 SQL 语句段或命令，从而利用系统的 SQL 引擎完成恶意行为的做法对于 Java 而言，要防范 SQL 注入，只要用 PreparedStatement 取代 Statement 就可以了

