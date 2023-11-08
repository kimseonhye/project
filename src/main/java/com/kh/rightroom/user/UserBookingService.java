package com.kh.rightroom.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserVO;

@Service
public class UserBookingService {

	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	@Autowired
	UserBookingDAO userBookingDAO;
	
	public int reserveConfirm(UserVO loginUser, int roomNo, BookingVO bookingVO) {
		return userBookingDAO.insertBooking(loginUser, roomNo, bookingVO);
	}

	public RoomVO userBookingPage(int room_no) {
		System.out.println("[RoomService] userBookingPage()");
		return userBookingDAO.selectRoom(room_no);
	}
	
	// 테이블 세팅 메서드
		public TableSetVO userRoomTableSet(int room_no) {
			System.out.println("[RoomService] userRoomTableSet()");
			return userBookingDAO.selectTableSet(room_no);
		}

}
