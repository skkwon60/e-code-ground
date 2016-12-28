package spms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.vo.Member;

public class MemberDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds){
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO,MNAME,EMAIL,CRE_DATE" + " FROM MEMBERS" + " ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
			return members;
		} catch (Exception e){
			throw e;
		}
		finally{
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
	
	public int insert(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try{
			connection = ds.getConnection();
			stmt = connection.prepareStatement("INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
				+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.executeUpdate();
			
			return 1;
		} catch(Exception e) {
			throw e;
		}
		finally {
			try {if(stmt != null) stmt.close();} catch(Exception e){}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
	
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null; 
		try{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
			"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
			" WHERE MNO=" + no);	
			rs.next();
		
			Member member = new Member();
			
			member.setCreatedDate(rs.getDate("CRE_DATE"))
			.setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL")).setNo(rs.getInt("MNO"));
		
			return member;
		
		} catch (Exception e){
			throw e;
			
		}
		finally {
			try {if(stmt != null) stmt.close();} catch (Exception e){}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
	
	public int update(Member member) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
				+ " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
		
			stmt.executeUpdate();
		
			return 1;
		
		} catch (Exception e) {
			throw e;
		
		}
		finally {
			try {if(stmt != null) stmt.close();} catch (Exception e){}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
	
	public int delete(int no) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try{
			connection = ds.getConnection();
			stmt = connection.prepareStatement("DELETE FROM MEMBERS WHERE MNO=" + no);
			stmt.executeUpdate();
			
			return 1;
			
		} catch(Exception e){
			throw e;
			
		}
		finally {
			try {if(stmt != null) stmt.close();} catch (Exception e){}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
	
	public Member exist(String email, String password) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		
		try{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MNAME,EMAIL FROM MEMBERS WHERE EMAIL=\""
			+ email + "\" AND PWD=\"" + password + "\"");
			
			Member member = null;
			
			if(rs.next()){
				member = new Member();
				member.setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL"));
			}
			return member;
			
		} catch (Exception e){
			throw e;
		}
		finally {
			try {if(stmt != null) stmt.close();} catch (Exception e){}
			try {if (connection != null) connection.close();} catch(Exception e){}
		}
	}
}
