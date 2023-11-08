package com.kh.rightroom.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.RoomVO;

@Service
public class UserReserveConfirmService {
	
	@Autowired
	UserReserveConfirmDAO userReserveConfirmDAO;

	public BookingVO selectReserveBooking(int booking_no) {
		return userReserveConfirmDAO.selectReserveBooking(booking_no);
	}
	
	public RoomVO selectReserveRoom(int room_no) {
		return userReserveConfirmDAO.selectReserveRoom(room_no);
	}

}
