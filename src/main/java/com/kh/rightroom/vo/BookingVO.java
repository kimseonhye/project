package com.kh.rightroom.vo;

import java.sql.Date;

public class BookingVO {
	private int booking_no;
	private int user_no;
	private int room_no;
	private int b_ppl;
	private String b_user_name;
	private String b_user_contact;
	private String b_user_email;
	private String b_company;
	private String b_name;
	private String b_purpose;
	private Date b_checkin_date;
	private Date b_date;
	private int b_status;
	private Date b_checkout_date;
	private String b_table_set;
	private String b_comment;
	private int room_price;

	public int getRoom_price() {
		return room_price;
	}

	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}

	public String getB_user_name() {
		return b_user_name;
	}

	public void setB_user_name(String b_user_name) {
		this.b_user_name = b_user_name;
	}

	public String getB_user_contact() {
		return b_user_contact;
	}

	public void setB_user_contact(String b_user_contact) {
		this.b_user_contact = b_user_contact;
	}

	public String getB_user_email() {
		return b_user_email;
	}

	public void setB_user_email(String b_user_email) {
		this.b_user_email = b_user_email;
	}

	public String getB_company() {
		return b_company;
	}

	public void setB_company(String b_company) {
		this.b_company = b_company;
	}

	public String getB_table_set() {
		return b_table_set;
	}

	public void setB_table_set(String b_table_set) {
		this.b_table_set = b_table_set;
	}

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public int getB_ppl() {
		return b_ppl;
	}

	public void setB_ppl(int b_ppl) {
		this.b_ppl = b_ppl;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_purpose() {
		return b_purpose;
	}

	public void setB_purpose(String b_purpose) {
		this.b_purpose = b_purpose;
	}

	public Date getB_checkin_date() {
		return b_checkin_date;
	}

	public void setB_checkin_date(Date b_checkin_date) {
		this.b_checkin_date = b_checkin_date;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public int getB_status() {
		return b_status;
	}

	public void setB_status(int b_status) {
		this.b_status = b_status;
	}

	public Date getB_checkout_date() {
		return b_checkout_date;
	}

	public void setB_checkout_date(Date b_checkout_date) {
		this.b_checkout_date = b_checkout_date;
	}

	public String getB_comment() {
		return b_comment;
	}

	public void setB_comment(String b_comment) {
		this.b_comment = b_comment;
	}

}
