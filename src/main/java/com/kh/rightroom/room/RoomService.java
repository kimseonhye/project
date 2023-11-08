package com.kh.rightroom.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.RoomRegisterVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.TableSetVO;

@Service
public class RoomService {

	@Autowired
	private RoomDAO roomDAO;

	// 장소 검색 조회
	public List<RoomVO> searchRooms(String room_address, int room_max_ppl, String room_price) {
		System.out.println("[RoomService] searchRooms()");
		return roomDAO.searchRooms(room_address, room_max_ppl, room_price);
	}

	// 장소 상세 페이지 조회
	public RoomVO userRoomDetail(int room_no) {
		System.out.println("[RoomService] userRoomDetail()");
		return roomDAO.selectRoom(room_no);
	}

	// 리뷰 목록 조회 메서드
	public List<ReviewVO> userRoomReviewList(int room_no) {
		System.out.println("[RoomService] userRoomReviewList()");

		// RoomDAO의 selectReviewsForRoom 메서드를 호출하여 리뷰 목록 조회
		return roomDAO.selectReviewsForRoom(room_no);
	}
	
	// 테이블 세팅 메서드
	 public TableSetVO userRoomTableSet(int room_no) {
	  System.out.println("[RoomService] userRoomTableSet()");
	  return roomDAO.selectTableSet(room_no);
	 }

	// 장소등록 관련 메서드
	public boolean doesRoomExist(int roomNo) {
		return roomDAO.isRoomExists(roomNo);
	}

	// 장소등록 관련 메서드
	@Transactional
	public int registerRoom(RoomRegisterVO roomRegisterVO) {

		// Insert Table Set and get generated ID
		int tSetNo = roomDAO.insertTableSet(roomRegisterVO);

		// Insert Room and get number of affected rows
		int result = roomDAO.insertRoom(roomRegisterVO, tSetNo);

		if (result == 0) {
			throw new RuntimeException("Room registration failed.");
		}

		return result;
	}

	// 승인 반려해주는 메서드를 호출해주는 서비스 메서드
	public int updateRoomStatus(int roomNo, int newStatus) {
		return roomDAO.updateRoomStatus(roomNo, newStatus);
	}

}
