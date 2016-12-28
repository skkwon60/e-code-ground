package spms.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
		rd.include(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");
		
		PreparedStatement stmt = null;
		System.out.println("Asssst 준비…");
		try {
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = new Member();
			member.setName(request.getParameter("name")).setEmail(request.getParameter("email"))
				.setPassword(request.getParameter("password"));
			
			memberDao.insert(member);

			response.sendRedirect("list");
			
		} catch (Exception e) {
			//throw new ServletException(e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
