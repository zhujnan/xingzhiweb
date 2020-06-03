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
