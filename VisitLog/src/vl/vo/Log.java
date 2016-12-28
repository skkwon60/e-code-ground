package vl.vo;

import java.util.Date;

public class Log {
	protected String name;
	protected String email;
	protected int no;
	protected String body;
	protected Date cre_date;
	protected Date mod_date;
	
	public Log setName(String name){
		this.name = name;
		return this;
	}
	public Log setEmail(String email){
		this.email = email;
		return this;
	}
	public Log setNo(int no){
		this.no = no;
		return this;
	}
	public Log setBody(String body){
		this.body = body;
		return this;
	}
	public Log setCreatedDate(Date cre_date){
		this.cre_date = cre_date;
		return this;
	}
	public Log setModifiedDate(Date mod_date){
		this.mod_date = mod_date;
		return this;
	}
	public String getName(){
		return this.name;
	}
	public String getEmail(){
		return this.email;
	}
	public int getNo(){
		return this.no;
	}
	public String getBody(){
		return this.body;
	}
	public Date getCreatedDate(){
		return this.cre_date;
	}
	public Date getModifiedDate(){
		return this.mod_date;
	}
}
