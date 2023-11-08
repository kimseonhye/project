package com.kh.rightroom.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.UserRoomBookingVO;

@Service
public class UserRoomBookingService {
	
	@Autowired
	UserRoomBookingDAO userRoomBookingDAO;
	
	public List<UserRoomBookingVO> getAllURBs() {
		return userRoomBookingDAO.getAllURBs();
	}

}
