package com.kh.rightroom.vo;

import java.sql.Date;

public class CompanyFAQVO {
	private int faq_c_no;
	private String faq_c_title;
	private String faq_c_content;
	private Date faq_c_date;
	private Date faq_c_final_date;
	
	public int getFaq_c_no() {
		return faq_c_no;
	}
	public void setFaq_c_no(int faq_c_no) {
		this.faq_c_no = faq_c_no;
	}
	public String getFaq_c_title() {
		return faq_c_title;
	}
	public void setFaq_c_title(String faq_c_title) {
		this.faq_c_title = faq_c_title;
	}
	public String getFaq_c_content() {
		return faq_c_content;
	}
	public void setFaq_c_content(String faq_c_content) {
		this.faq_c_content = faq_c_content;
	}
	public Date getFaq_c_date() {
		return faq_c_date;
	}
	public void setFaq_c_date(Date faq_c_date) {
		this.faq_c_date = faq_c_date;
	}
	public Date getFaq_c_final_date() {
		return faq_c_final_date;
	}
	public void setFaq_c_final_date(Date faq_c_final_date) {
		this.faq_c_final_date = faq_c_final_date;
	}
	

	
}
