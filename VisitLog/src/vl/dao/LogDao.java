package vl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import vl.vo.Log;

public class LogDao {
	Connection connection;
	protected boolean modifyParam = false;
	
	public void setConnection(Connection conn){
		this.connection = conn;
	}
	
	public void setCanModifyParam(){
		modifyParam = true;
	}
	
	public boolean getCanModifyParam(){
		if(modifyParam == true){
			modifyParam = false;
			return true;
		}
		else
			return false;
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
						.setPassword(rs.getString("PWD"))
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
	
	public boolean enterNewLog(Log log) throws Exception{
		PreparedStatement stmt = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		
		try{
			stmt2 = connection.createStatement();
			rs = stmt2.executeQuery("SELECT * FROM visitlist where EMAIL=" + "\"" + log.getEmail() + "\"");
			if(!rs.next()){
				stmt = connection.prepareStatement(
						"INSERT INTO visitlist(EMAIL,PWD,BODY,CRE_DATE,MOD_DATE)"
						+ " value(?,?,?,now(),now())");
				stmt.setString(1, log.getEmail());
				stmt.setString(2, log.getPassword());
				stmt.setString(3, log.getBody());
			
				stmt.executeUpdate();
				
				return true;
			}
			else{
				return false;
			}
			
		} catch(Exception e){
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
	
	public Log selectUser(int no) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			Log log = new Log();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM visitlist WHERE MNO=" + no);
			
			rs.next();
			log.setBody(rs.getString("BODY"))
				.setEmail(rs.getString("EMAIL"))
				.setPassword(rs.getString("PWD"))
				.setCreatedDate(rs.getDate("CRE_DATE"))
				.setModifiedDate(rs.getDate("MOD_DATE"));
			
			return log;
			
		} catch (Exception e){
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (rs != null) rs.close();} catch(Exception e) {}
		}
	}
	
	public Log checkPwdCorrect(String email, String password) throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		
		Log log = null;

		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM visitlist where EMAIL=\"" + email + "\" AND PWD=\"" + password + "\"");
			
			if(rs.next()){
				log = new Log();
				log.setEmail(rs.getString("EMAIL")).setNo(rs.getInt("MNO")).setBody(rs.getString("BODY"));
			}
			
			return log;
			
		} catch(Exception e){
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (rs != null) rs.close();} catch(Exception e) {}
		}
	}
	
	public void modifyBody(Log log) throws Exception{
		PreparedStatement stmt = null;
		
		try{
			stmt = connection.prepareStatement(
					"UPDATE visitlist set BODY=?,MOD_DATE=now() WHERE MNO=?");
			stmt.setString(1, log.getBody());
			stmt.setInt(2, log.getNo());
			
			stmt.executeUpdate();
			
		} catch (Exception e){
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
	
	public void deleteLog(int no) throws Exception{
		PreparedStatement stmt = null;
		
		try{
			stmt = connection.prepareStatement(
					"DELETE FROM visitlist where MNO=?");
			stmt.setInt(1, no);
			
			stmt.executeUpdate();
			
		} catch(Exception e){
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
