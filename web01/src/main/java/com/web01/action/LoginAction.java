package com.web01.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;

/**
 * 锟斤拷锟斤拷锟铰斤拷锟斤拷锟�
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
		System.out.println(userName+","+passWorld);
		//"mayun".equals(userName)&&"123456".equals(passWorld)
		//boolean bool = false;
		Emp emp = new Emp();
		emp.setName(userName);
		emp.setPwd(passWorld);
			//bool = userDao.login(userName,passWorld);
			//根据用户名和密码查询，查到返回true
			//System.out.println(bool);
		Emp getEmp = userDao.findUserByUser(emp);
		HttpSession session = request.getSession();//获取session对象
		//session：请求连接之后的会话
		if(getEmp!=null) {
			session.setAttribute("user", getEmp);
			//request.getRequestDispatcher("success.jsp").forward(request, response);
			response.sendRedirect("list.do"); //告诉请求对象，向list.do再发起请求
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
