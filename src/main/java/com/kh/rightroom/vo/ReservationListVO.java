package com.kh.rightroom.vo;

import java.util.Date;

// sql 문 join 데이터 담을 Value Object
public class ReservationListVO {
	private int booking_no;
	private String room_name;
	private String user_name;
	private String user_phone;
	private String user_email;
	private int b_ppl;
	private int room_price;
	private String b_name;
	private String b_purpose;
	private String b_comment;
	private Date start_date;

	public int getBooking_no() {
		return booking_no;
	}

	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getB_ppl() {
		return b_ppl;
	}

	public void setB_ppl(int b_ppl) {
		this.b_ppl = b_ppl;
	}

	public int getRoom_price() {
		return room_price;
	}

	public void setRoom_price(int room_price) {
		this.room_price = room_price;
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

	public String getB_comment() {
		return b_comment;
	}

	public void setB_comment(String b_comment) {
		this.b_comment = b_comment;
	}

}
