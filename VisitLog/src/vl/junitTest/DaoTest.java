package vl.junitTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import vl.dao.LogDao;
import vl.vo.Log;

public class DaoTest {

	@Test
	public void testSelectLog() {
		LogDao logDao = new LogDao();
	
		Connection conn;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/visitdb",
					"test",
					"test");
			logDao.setConnection(conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Log> loglist = new ArrayList<Log>();
		try {
			assertEquals(loglist,logDao.selectLog());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
