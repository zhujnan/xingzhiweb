package com.jdbc01.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 实现插入方法
 * @author zhujn
 *
 */
public class Jdbc02 {
	private static String url;
	private static String dbUser;
	private static String dbPassword;
	private static void getParams(String fileName) throws IOException {
		Properties props = new Properties();//1.创建属性文件对象
		File file = new File(fileName);//2.创建File对象
		try {
			FileInputStream fileInputStream
				= new FileInputStream(file);//3.文件输入流对象
			props.load(fileInputStream);
			//4.属性文件对象加载指定的文件对象
			url = props.getProperty("url");
			dbUser = props.getProperty("username");
			dbPassword = props.getProperty("password");
			System.out.println(url);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static boolean addPerson(int id, String name, int age) {
		boolean flag = true;//新建一个标志变量
		Connection conn = null;//连接对象
		Statement stmt = null;//语句对象
		try {
			conn = DriverManager.getConnection(url,dbUser,dbPassword);
			stmt = conn.createStatement();
			String sql = "insert into person(id,name,age) values("+id+",'"+name+"',"+age+");";
			int n = stmt.executeUpdate(sql);//更新方法，返回影响的行数
			if(n==1) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static void main(String[] args)throws Exception {
			getParams("src/main/resources/pro.properties");
		//	boolean bool = addPerson(4,"马云",32);
		//	boolean bl = deletePerson(6);
			boolean upddatebl = updatePerson(5,"mht2");
			
			System.out.println(upddatebl);
	}
	private static boolean updatePerson(int i,
			String name) {
		boolean flag = false;//新建一个标志变量
		Connection conn = null;//连接对象
		Statement stmt = null;//语句对象
		
		try {
			conn = DriverManager.getConnection(url,dbUser,dbPassword);
			stmt = conn.createStatement();
			String sql = "update person set name ='"+name+"'where id ="+i;
			int n = stmt.executeUpdate(sql);//更新方法，返回影响的行数
			if(n==1) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	private static boolean deletePerson(int i) {

		boolean flag = true;//新建一个标志变量
		Connection conn = null;//连接对象
		Statement stmt = null;//语句对象
		try {
			conn = DriverManager.getConnection(url,dbUser,dbPassword);
			stmt = conn.createStatement();
			String sql = "delete from person where id ="+i;
			int n = stmt.executeUpdate(sql);//更新方法，返回影响的行数
			if(n==1) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	
}
