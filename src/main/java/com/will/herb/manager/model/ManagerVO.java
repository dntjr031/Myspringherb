package com.will.herb.manager.model;

import java.sql.Timestamp;

public class ManagerVO {
	private int no;
	private String userid;
	private String name;
	private String pwd;
	private String authCode;
	private Timestamp date;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ManagerVO [no=" + no + ", userid=" + userid + ", name=" + name + ", pwd=" + pwd + ", authCode="
				+ authCode + ", date=" + date + "]";
	}

}
