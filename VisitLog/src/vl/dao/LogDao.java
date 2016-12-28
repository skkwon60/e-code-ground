package vl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import vl.vo.Log;

public class LogDao {
	Connection connection;
	
	public void setConnection(Connection conn){
		this.connection = conn;
	}
	
	public ArrayList<Log> selectLog() throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM visitlist ORDER BY MNO DESC");
			
			ArrayList<Log> loglist = new ArrayList<Log>();
			
			while(rs.next()){
				loglist.add(new Log().setBody(rs.getString("BODY"))
						.setCreatedDate(rs.getDate("CRE_DATE"))
						.setEmail(rs.getString("EMAIL"))
						.setModifiedDate(rs.getDate("MOD_DATE"))
						.setName(rs.getString("name"))
						.setNo(rs.getInt("MNO")));
			}
			
			return loglist;
			
		} catch (Exception e){
			throw e;
		} finally {
		    try {if (rs != null) rs.close();} catch(Exception e) {}
		    try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
