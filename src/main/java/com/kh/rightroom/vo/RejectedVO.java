package com.kh.rightroom.vo;

import java.sql.Date;

public class RejectedVO {

	private int rejected_no;
	private int room_no;
	private String rejected_reason;
	private Date rejected_date;
	
	public int getRejected_no() {
		return rejected_no;
	}
	public void setRejected_no(int rejected_no) {
		this.rejected_no = rejected_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public String getRejected_reason() {
		return rejected_reason;
	}
	public void setRejected_reason(String rejected_reason) {
		this.rejected_reason = rejected_reason;
	}
	public Date getRejected_date() {
		return rejected_date;
	}
	public void setRejected_date(Date rejected_date) {
		this.rejected_date = rejected_date;
	}
	
}
