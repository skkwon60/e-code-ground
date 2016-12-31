package vl.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vl.dao.LogDao;
import vl.vo.Log;

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
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			if(logDao.getCanModifyParam()){
				logDao.modifyBody(new Log()
						.setBody(request.getParameter("body"))
						.setNo(Integer.parseInt(request.getParameter("no"))));
				
				response.sendRedirect("mainpage");
			}
			else{
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/ModifyFail.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
