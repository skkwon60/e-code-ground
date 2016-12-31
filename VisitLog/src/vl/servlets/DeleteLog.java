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

@WebServlet("/visitlog/delete")
public class DeleteLog extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) 
					throws IOException, ServletException{
		try{
			ServletContext sc = this.getServletContext();
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			if(logDao.getCanModifyParam()){
				int no = Integer.parseInt(request.getParameter("no"));
				
				logDao.deleteLog(no);
				
				response.sendRedirect("mainpage");
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("/ModifyFail.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
