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

@WebServlet("/visitlog/modify")
public class LogModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		
		try{
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/visitlog/ModifyPage.jsp");
			rd.include(request, response);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
		
		try{
			ServletContext sc = this.getServletContext();
			LogDao logDag = (LogDao)sc.getAttribute("LogDao");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
