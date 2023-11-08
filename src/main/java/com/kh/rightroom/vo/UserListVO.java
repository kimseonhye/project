package com.kh.rightroom.vo;

import java.sql.Date;

public class UserListVO {
 private String user_name;
 private int booking_no;
 private int user_no;
 private int room_no;
 private int b_ppl;
 private String b_name;
 private String b_purpose;
 private Date b_checkin_date;
 private Date b_date;
 private int b_status;
 private Date b_checkout_date;
 private String b_comment;
 private String room_name;
 private String room_images;
 private String room_address;
 
 public String getRoom_address() {
  return room_address;
 }
 public void setRoom_address(String room_address) {
  this.room_address = room_address;
 }
 public String getUser_name() {
  return user_name;
 }
 public void setUser_name(String user_name) {
  this.user_name = user_name;
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
 public String getRoom_name() {
  return room_name;
 }
 public void setRoom_name(String room_name) {
  this.room_name = room_name;
 }
 public String getRoom_images() {
  return room_images;
 }
 public void setRoom_images(String room_images) {
  this.room_images = room_images;
 }
 
}