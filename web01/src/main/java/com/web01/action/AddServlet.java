package com.web01.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl(); 
    public AddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			/**
			 * 获取表单的值
			 */
			int  id = Integer.valueOf(request.getParameter("id"));
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd");
			Emp emp = new Emp();
			emp.setId(id);
			emp.setName(name);
			emp.setPwd(pwd);
			boolean bool = userDao.addEmp(emp);
			if(bool) {
				response.sendRedirect("list.do");
			}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
