package com.kh.rightroom.vo;

import java.sql.Date;

public class ReviewVO {
	private int review_no;
	private int booking_no;
	private int room_no;
	private String review_title;
	private String review_content;
	private float review_star;
	private Date review_date;

	private String room_name;
	private Date b_checkin_date;
	private Date b_checkout_date;
	private String user_name;
	private int user_no;

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	
	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public Date getB_checkin_date() {
		return b_checkin_date;
	}

	public void setB_checkin_date(Date b_checkin_date) {
		this.b_checkin_date = b_checkin_date;
	}

	public Date getB_checkout_date() {
		return b_checkout_date;
	}

	public void setB_checkout_date(Date b_checkout_date) {
		this.b_checkout_date = b_checkout_date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public float getReview_star() {
		return review_star;
	}

	public void setReview_star(float review_star) {
		this.review_star = review_star;
	}

	public Date getReview_date() {
		return review_date;
	}

	public void setReview_date(Date review_date) {
		this.review_date = review_date;
	}

}