package com.kh.rightroom.vo;

import java.sql.Date;

public class UserRoomBookingVO {
	private String user_name;
	private String room_name;
	private String room_address;
	private String user_phone;
	private Date b_checkin_date;
	private Date b_checkout_date;
	private int room_price;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_address() {
		return room_address;
	}
	public void setRoom_address(String room_address) {
		this.room_address = room_address;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
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
	public int getRoom_price() {
		return room_price;
	}
	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}

}
