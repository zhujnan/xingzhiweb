package com.web01.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;
/**
 * 处理修改的请求，
 * 		1.根据id查询该行记录，跳转到更新页面
 * 		2.在更新页面更新完成之后，点完成之后重定向到查询所有页面。
 * @author zhujn
 *
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private IUserDao userDao = new UserDaoImpl();   
    public UpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Emp emp = userDao.findEmpById(id);
		request.setAttribute("e", emp);
		request.getRequestDispatcher("updateEmp.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
