package com.kh.rightroom.vo;

import java.sql.Date;

public class FavoriteRoomVO {

	private int favorite_room;
	private int user_no;
	private int room_no;
	private Date f_r_date;
	
	public int getFavorite_room() {
		return favorite_room;
	}
	public void setFavorite_room(int favorite_room) {
		this.favorite_room = favorite_room;
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
	public Date getF_r_date() {
		return f_r_date;
	}
	public void setF_r_date(Date f_r_date) {
		this.f_r_date = f_r_date;
	}
}
