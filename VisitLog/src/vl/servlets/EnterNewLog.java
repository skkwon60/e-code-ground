package vl.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

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
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/DKnowError.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		try{
			ServletContext sc = this.getServletContext();
			LogDao logDao = (LogDao)sc.getAttribute("LogDao");
			
			Log log = new Log();
			
			String re = "^[a-zA-Z0-9_\\.]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]{2,4}){1,2}$";
			String ws = "\\s";
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String body = request.getParameter("body");
			
			email = email.trim();
			
			if(email == null || password == null || body == null){
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				String e = "NullError";
				request.setAttribute("e", e);
				rd.forward(request, response);
			}
			else if(!email.matches(re)){
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				String e = "EmailError";
				request.setAttribute("e", e);
				rd.forward(request, response);
			}
			else if(email == "" || password == "" || body == ""){
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				String e = "NoValueError";
				request.setAttribute("e", e);
				rd.forward(request, response);
			}
			else if(password.matches(ws)){
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				String e = "SpaceInPwd";
				request.setAttribute("e", e);
				rd.forward(request, response);
			}
			
			log.setBody(body)
				.setEmail(email)
				.setPassword(password);
			
			if(logDao.enterNewLog(log)){
				response.sendRedirect("mainpage");
			}
			else{
				response.setContentType("text/html; charset=UTF-8");
				RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
				String e = "EmailDupError";
				request.setAttribute("e", e);
				rd.forward(request, response);
			}
			
		} catch (Exception e){
			e.printStackTrace();
			response.setContentType("text/html; charset=UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/DKnowError.jsp");
			rd.forward(request, response);
		}
	}
}
