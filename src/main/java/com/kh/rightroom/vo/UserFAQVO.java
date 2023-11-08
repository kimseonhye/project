package com.kh.rightroom.vo;

import java.sql.Date;

public class UserFAQVO {
	private int faq_u_no;
	private String faq_u_title;
	private String faq_u_content;
	private String faq_u_image;
	private Date faq_u_date;
	
	public int getFaq_u_no() {
		return faq_u_no;
	}
	public void setFaq_u_no(int faq_u_no) {
		this.faq_u_no = faq_u_no;
	}
	public String getFaq_u_title() {
		return faq_u_title;
	}
	public void setFaq_u_title(String faq_u_title) {
		this.faq_u_title = faq_u_title;
	}
	public String getFaq_u_content() {
		return faq_u_content;
	}
	public void setFaq_u_content(String faq_u_content) {
		this.faq_u_content = faq_u_content;
	}
	public String getFaq_u_image() {
		return faq_u_image;
	}
	public void setFaq_u_image(String faq_u_image) {
		this.faq_u_image = faq_u_image;
	}
	public Date getFaq_u_date() {
		return faq_u_date;
	}
	public void setFaq_u_date(Date faq_u_date) {
		this.faq_u_date = faq_u_date;
	}

}
