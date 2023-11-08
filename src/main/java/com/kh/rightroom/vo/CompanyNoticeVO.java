package com.kh.rightroom.vo;

import java.sql.Date;

public class CompanyNoticeVO {
	private int n_c_no;
	private String n_c_title;
	private String n_c_content;
	private Date n_c_date;
	private Date n_c_final_date;
	
	public int getN_c_no() {
		return n_c_no;
	}
	public void setN_c_no(int n_c_no) {
		this.n_c_no = n_c_no;
	}
	public String getN_c_title() {
		return n_c_title;
	}
	public void setN_c_title(String n_c_title) {
		this.n_c_title = n_c_title;
	}
	public String getN_c_content() {
		return n_c_content;
	}
	public void setN_c_content(String n_c_content) {
		this.n_c_content = n_c_content;
	}
	public Date getN_c_date() {
		return n_c_date;
	}
	public void setN_c_date(Date n_c_date) {
		this.n_c_date = n_c_date;
	}
	public Date getN_c_final_date() {
		return n_c_final_date;
	}
	public void setN_c_final_date(Date n_c_final_date) {
		this.n_c_final_date = n_c_final_date;
	}
	
}
