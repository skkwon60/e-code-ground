package vl.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vl.dao.LogDao;
import vl.vo.Log;

@WebServlet("/visitlog/add")
public class EnterNewLog extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		try{
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/visitlog/EnterNewLog.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		try{
			ServletContext sc = this.getServletContext();
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			Log log = new Log();
			log.setBody(request.getParameter("body"))
				.setEmail(request.getParameter("email"))
				.setPassword(request.getParameter("password"));
			
			logDao.enterNewLog(log);
			
			response.sendRedirect("mainpage");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
