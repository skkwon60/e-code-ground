package vl.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import vl.dao.LogDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	Connection conn;
	
	@Override
	public void contextInitialized(ServletContextEvent event){
		try{
			ServletContext sc = event.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			LogDao logDao = new LogDao();
			logDao.setConnection(conn);
			
			sc.setAttribute("LogDao", logDao);
			System.out.println("AppInitServlet 준비…");

		} catch (Throwable e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event){
		try{
			conn.close();
		} catch(Exception e){
		}
	}
}
