package com.kh.rightroom.vo;

import java.sql.Date;

public class TransactionVO {

	private int trans_no;
	private int booking_no;
	private int t_price;
	private Date t_date;
	private int t_status;
	
	public int getTrans_no() {
		return trans_no;
	}
	public void setTrans_no(int trans_no) {
		this.trans_no = trans_no;
	}
	public int getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}
	public int getT_price() {
		return t_price;
	}
	public void setT_price(int t_price) {
		this.t_price = t_price;
	}
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public int getT_status() {
		return t_status;
	}
	public void setT_status(int t_status) {
		this.t_status = t_status;
	}
}
