package com.web01.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();   
    
    public DeleteServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		boolean bool = userDao.deleteEmp(id);
		if(bool) {
			//删除成功重定向到查询所有页面
			response.sendRedirect("list.do");
		}
		System.out.println("获取的ID为"+id);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
