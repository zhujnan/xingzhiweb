package com.web01.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import com.web01.dao.IUserDao;
import com.web01.entity.Emp;
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
		System.out.println(userDaoImpl.findAll());
		Emp emp = new Emp();
		emp.setId(6);
		emp.setName("6666");
		emp.setPwd("6");
		System.out.println(userDaoImpl.updateEmp(emp));
		//System.out.println(userDaoImpl.deleteEmp(1));
	}
	
	public ArrayList<Emp> findAll() {

		ArrayList<Emp> userlist = new ArrayList<Emp>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionUtils.getConnection();
			ps = conn.prepareStatement("select * from emps");
			rs = ps.executeQuery();
			while(rs.next()) {
				Emp user = new Emp(); //一定要加,否则会发生空指针异常
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				userlist.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return userlist;
	}
	public boolean deleteEmp(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			conn = ConnectionUtils.getConnection(); //1.加载驱动建立连接
			ps = conn.prepareStatement("delete from emps where id = ?");//2.创建语句对象
			ps.setObject(1,id);
			int n = ps.executeUpdate();//3.执行语句对象 
									    //4.处理结果
										//5.关闭连接。
			if(n>=1) {
				bool = true;
			}else {
				bool = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return bool;
}
	public boolean addEmp(Emp emp) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			conn = ConnectionUtils.getConnection(); //1.加载驱动建立连接
			ps = conn.prepareStatement("insert into emps values(?,?,?)");//2.创建语句对象
			ps.setObject(1,emp.getId());
			ps.setObject(2,emp.getName());
			ps.setObject(3,emp.getPwd());
			int n = ps.executeUpdate();//插入、删除、修改
			
			if(n>=1) {
				bool = true;
			}else {
				bool = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return bool;

	}
	public Emp findEmpById(Integer id) {

		Connection conn = null;
		PreparedStatement ps = null;
		boolean bool = false;
		ResultSet res = null;
		Emp emp =null;
		try {
			conn = ConnectionUtils.getConnection(); //1.加载驱动建立连接
			ps = conn.prepareStatement("select * from emps where id = ?");//2.创建语句对象
			ps.setObject(1,id);
			res = ps.executeQuery();
			while(res.next()) {
				emp = new Emp();
				emp.setId(res.getInt("id"));
				emp.setName(res.getString("name"));
				emp.setPwd(res.getString("pwd"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return emp;
	}
	public boolean updateEmp(Emp emp) {


		Connection conn = null;
		PreparedStatement ps = null;
		boolean bool = false;
		try {
			conn = ConnectionUtils.getConnection(); //1.加载驱动建立连接
			ps = conn.prepareStatement("update emps set name = ?,pwd=? where id = ?");//2.创建语句对象
			ps.setObject(1,emp.getName());
			ps.setObject(2, emp.getPwd());
			ps.setObject(3, emp.getId());
			int n = ps.executeUpdate();
			if(n>=1) {
				bool = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return bool;
	}
	public Emp findUserByUser(Emp emp) {
		Connection conn = null;
		PreparedStatement  stmt = null;
		ResultSet rs = null;
		Emp emps = null;
		try {
			conn = ConnectionUtils.getConnection();
			String sql = "select * from emps where name =? and pwd=?";
			stmt =(PreparedStatement) conn.prepareStatement(sql); //预编译
			stmt.setObject(1, emp.getName());
			stmt.setObject(2, emp.getPwd());
			rs = stmt.executeQuery(); //改成prepareStatement对象时，要把Sql去掉。
		
			while(rs.next()) {
				 emps = new Emp();
				 emps.setId(emps.getId());
				 emps.setName(emps.getName());
				 emps.setPwd(emps.getPwd());
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emps;
	}
	
}
