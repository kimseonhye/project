package com.kh.rightroom.vo;

import java.sql.Date;

public class RoomVO {
 private int room_no;
 private int user_no;
 private int t_set_no;
 private String room_name;
 private String room_phone;
 private String user_phone;
 private int room_max_ppl;
 private String room_images;
 private Date room_regi_date;
 private String room_address;
 private int room_price;
 private String room_faq;
 private String room_cancellation_and_refund_policy;
 private String room_status;
 
 public String getUser_phone() {
	return user_phone;
}

public void setUser_phone(String user_phone) {
	this.user_phone = user_phone;
}

public String getRoom_address() {
  return room_address;
 }

 public void setRoom_address(String room_address) {
  this.room_address = room_address;
 }

 public int getRoom_price() {
  return room_price;
 }

 public void setRoom_price(int room_price) {
  this.room_price = room_price;
 }

 public String getRoom_faq() {
  return room_faq;
 }

 public void setRoom_faq(String room_faq) {
  this.room_faq = room_faq;
 }

 public String getRoom_cancellation_and_refund_policy() {
  return room_cancellation_and_refund_policy;
 }

 public void setRoom_cancellation_and_refund_policy(String room_cancellation_and_refund_policy) {
  this.room_cancellation_and_refund_policy = room_cancellation_and_refund_policy;
 }

 public String getRoom_status() {
  return room_status;
 }

 public void setRoom_status(String room_status) {
  this.room_status = room_status;
 }

 public int getRoom_no() {
  return room_no;
 }

 public void setRoom_no(int room_no) {
  this.room_no = room_no;
 }

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

 public String getRoom_phone() {
  return room_phone;
 }

 public void setRoom_phone(String room_phone) {
  this.room_phone = room_phone;
 }

 public int getRoom_max_ppl() {
  return room_max_ppl;
 }

 public void setRoom_max_ppl(int room_max_ppl) {
  this.room_max_ppl = room_max_ppl;
 }

 public String getRoom_images() {
  return room_images;
 }

 public void setRoom_images(String room_images) {
  this.room_images = room_images;
 }

 public Date getRoom_regi_date() {
  return room_regi_date;
 }

 public void setRoom_regi_date(Date room_regi_date) {
  this.room_regi_date = room_regi_date;
 }

 public int getT_set_no() {
  return t_set_no;
 }

 public void setT_set_no(int t_set_no) {
  this.t_set_no = t_set_no;
 }

}