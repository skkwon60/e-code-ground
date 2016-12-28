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

@WebServlet("/manage/pwdCheck")
public class LogInPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		try{
			ServletContext sc = this.getServletContext();
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			request.setAttribute("log", logDao.selectUser(Integer.parseInt(request.getParameter("no"))));
			
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/manage/PwdCheck.jsp");
			rd.include(request, response);
			
		} catch(Exception e){
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
			
			Log log = logDao.checkPwdCorrect(request.getParameter("email"),request.getParameter("password"));
			
			if(log != null){
				HttpSession session = request.getSession();
				session.setAttribute("log", log);
				response.sendRedirect("../visitlog/modify");
				
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/manage/PwdCheckFail.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
