package com.kh.rightroom.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserVO;

@Component
public class UserBookingDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	// 예약서 작성 메서드
//	public BookingVO insertBooking(String b_user_name, String b_user_contact, String b_user_email, String b_company,
//			int b_ppl, String b_name, String b_purpose, String b_comment, String room_name, String user_id) {
//		// user_no 가져오기
//		String getUserNoSql = "SELECT user_no FROM USER WHERE user_id = ?";
//		int user_no = jdbcTemplate.queryForObject(getUserNoSql, Integer.class, user_id);
//
//		// room_no 가져오기
//		String getRoomNoSql = "SELECT room_no FROM room WHERE room_name = ?";
//		int room_no = jdbcTemplate.queryForObject(getRoomNoSql, Integer.class, room_name);
//
//		// BookingVO 객체에 값 설정
//		BookingVO bookingVO = new BookingVO();
//		bookingVO.setUser_no(user_no);
//		bookingVO.setRoom_no(room_no);
//		bookingVO.setB_user_name(b_user_name);
//		bookingVO.setB_user_contact(b_user_contact);
//		bookingVO.setB_user_email(b_user_email);
//		bookingVO.setB_company(b_company);
//		bookingVO.setB_ppl(b_ppl);
//		bookingVO.setB_name(b_name);
//		bookingVO.setB_purpose(b_purpose);
//		bookingVO.setB_comment(b_comment);
//
//		// 데이터베이스에 삽입
//		int result = insertBooking(bookingVO);
//		if (result == 1) {
//			return bookingVO;
//		} else {
//			return null;
//		}
//	}
	
	public int insertBooking(UserVO loginUser, int roomNo, BookingVO bookingVO) {
		
		System.out.println("[UserBookingDAO] 데이터 넣기");
		
		String sql = "INSERT INTO booking (user_no, room_no, b_user_name, b_user_contact, b_user_email, "
	                + "b_company, b_ppl, b_name, b_purpose, b_table_set, b_checkin_date, b_checkout_date, b_date, b_comment) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?)";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
					loginUser.getUser_no(),
					roomNo,
					bookingVO.getB_user_name(),
					bookingVO.getB_user_contact(),
					bookingVO.getB_user_email(),
					bookingVO.getB_company(),
					bookingVO.getB_ppl(),
					bookingVO.getB_name(),
					bookingVO.getB_purpose(),
					bookingVO.getB_table_set(),
					bookingVO.getB_checkin_date(),
					(bookingVO.getB_checkin_date().toLocalDate().plusDays(1)),
					bookingVO.getB_date(),
					bookingVO.getB_comment()
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 장소 상세 조회 페이지
		public RoomVO selectRoom(int room_no) {
			System.out.println("[RoomDAO] selectRoom()");

			String sql = "SELECT u.user_phone, r.* FROM USER u INNER JOIN ROOM r ON u.user_no = r.user_no WHERE room_no = ?";

			try {
				RowMapper<RoomVO> rowMapper = BeanPropertyRowMapper.newInstance(RoomVO.class);
				List<RoomVO> roomVOs = jdbcTemplate.query(sql, rowMapper, room_no);

				if (roomVOs.size() > 0) {
					return roomVOs.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
		
		// 테이블 세팅 조회
		public TableSetVO selectTableSet(int room_no) {
			System.out.println("[RoomDAO] selectTableSet()");

			String sql = "SELECT * FROM table_set WHERE t_set_no = ?";

			try {
				RowMapper<TableSetVO> rowMapper = BeanPropertyRowMapper.newInstance(TableSetVO.class);
				List<TableSetVO> tableSetVOs = jdbcTemplate.query(sql, rowMapper, room_no);

				if (tableSetVOs.size() > 0) {
					return tableSetVOs.get(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;

		}

}
