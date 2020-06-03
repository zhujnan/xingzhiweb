package com.web01.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web01.dao.IUserDao;
import com.web01.dao.impl.UserDaoImpl;
import com.web01.entity.Emp;
/**
 * 负责查询所有
 * @author zhujn
 *
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserDao userDao = new UserDaoImpl();
    public ListServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Emp> arrayList = userDao.findAll();
		request.setAttribute("employees", arrayList);
		RequestDispatcher rd = request.getRequestDispatcher("emplist2.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
