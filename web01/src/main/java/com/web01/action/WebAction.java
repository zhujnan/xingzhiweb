package com.web01.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public WebAction() {
        super();
    }
	protected void doGet(HttpServletRequest request, 
				HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		//设置响应方式
		PrintWriter out = response.getWriter();
		//获取字符串输出流
		out.print("hello,servlet");
		//通过流向客户端打印hello,servlet
		out.close();
		//关闭流
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
