package com.kh.rightroom.vo;

import java.sql.Date;

public class ScheduleVO {

	private int schedule_no;
	private int user_no;
	private int room_no;
	private Date start_no_booking;
	private Date end_no_booking;
	private String not_reason;
	
	public int getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
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
	public Date getStart_no_booking() {
		return start_no_booking;
	}
	public void setStart_no_booking(Date start_no_booking) {
		this.start_no_booking = start_no_booking;
	}
	public Date getEnd_no_booking() {
		return end_no_booking;
	}
	public void setEnd_no_booking(Date end_no_booking) {
		this.end_no_booking = end_no_booking;
	}
	public String getNot_reason() {
		return not_reason;
	}
	public void setNot_reason(String not_reason) {
		this.not_reason = not_reason;
	}
}
