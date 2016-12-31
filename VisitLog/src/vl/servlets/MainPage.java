package vl.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vl.dao.LogDao;
import vl.vo.Log;

@WebServlet("/visitlog/mainpage")
public class MainPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
		HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			
			ServletContext sc = this.getServletContext();
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			ArrayList<Log> loglist = logDao.selectLog();
			
			request.setAttribute("loglist", loglist);

			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/visitlog/MainPage.jsp");
			rd.include(request, response);

		} catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/DKnowError.jsp");
			rd.forward(request, response);
		}
	}
}
