package com.kh.rightroom.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.CompanyRoomVO;
import com.kh.rightroom.vo.RoomReviewVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserBookingVO;
import com.kh.rightroom.vo.UserVO;

@Component
public class AdminDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserVO> selectAllUser() {
		System.out.println("[AdminDAO] selectAllUser()");
		String sql = "SELECT * FROM USER WHERE USER_RANK = 1 OR USER_RANK = 2";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos;
	}

	public List<UserVO> selectUserId(UserVO userVo) {
		System.out.println("[AdminDAO] selectUserId()");
		String sql = "SELECT * FROM USER WHERE USER_ID LIKE ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, "%" + userVo.getUser_id() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos.size() > 0 ? userVos : null;
	}

	public UserVO selectOneUserToID(UserVO userVo) {
		System.out.println("[AdminDAO] selectOneUserToID()");
		String sql = "SELECT * FROM USER WHERE USER_ID LIKE ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, userVo.getUser_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos.size() > 0 ? userVos.get(0) : null;
	}
	
	public UserVO selectOneUser(UserVO userVo) {
		System.out.println("[AdminDAO] selectOneUser()");
		String sql = "SELECT * FROM USER WHERE USER_NO = ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, userVo.getUser_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos.size() > 0 ? userVos.get(0) : null;
	}

	public int deleteUser(UserVO userVo) {
		System.out.println("[AdminDAO] deleteUser()");
		String sql = "DELETE FROM USER WHERE USER_NO = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, userVo.getUser_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReview(UserBookingVO userBookingVo) {
		System.out.println("[AdminDAO] deleteReview()");
		String sql = "DELETE FROM REVIEW_LIST WHERE REVIEW_NO = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, userBookingVo.getReview_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReviewFromRoom(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] deleteReviewFromRoom()");
		String sql = "DELETE FROM REVIEW_LIST WHERE room_no = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public UserVO selectUsertoReview(UserBookingVO userBookingVo) {
		System.out.println("[AdminDAO] selectUsertoReview()");
		String sql = "SELECT * FROM REVIEW_LIST rl LEFT JOIN BOOKING b ON rl.BOOKING_NO = b.BOOKING_NO LEFT JOIN USER u ON b.USER_NO = u.USER_NO WHERE rl.REVIEW_NO = ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, userBookingVo.getReview_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos.size() > 0 ? userVos.get(0) : null;
	}

	public List<UserVO> selectUser() {
		System.out.println("[AdminDAO] selectUser()");
		String sql = "SELECT * FROM USER WHERE USER_RANK = ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, 1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos;
	}

	public List<UserVO> selectCompanyUser() {
		System.out.println("[AdminDAO] selectCompanyUser()");
		String sql = "SELECT * FROM USER WHERE USER_RANK = ?";

		List<UserVO> userVos = new ArrayList<UserVO>();

		try {
			userVos = jdbcTemplate.query(sql, new RowMapper<UserVO>() {

				@Override
				public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserVO userVo = new UserVO();
					userVo.setUser_no(rs.getInt("user_no"));
					userVo.setUser_id(rs.getString("user_id"));
					userVo.setUser_pw(rs.getString("user_pw"));
					userVo.setUser_name(rs.getString("user_name"));
					userVo.setUser_phone(rs.getString("user_phone"));
					userVo.setUser_email(rs.getString("user_email"));
					userVo.setUser_rank(rs.getInt("user_rank"));
					return userVo;
				}
			}, 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos;
	}

	public List<UserBookingVO> selectUserBooking(UserVO userVo) {
		System.out.println("[AdminDAO] selectUserBooking()");
		String sql = "SELECT b.*, r.*, rl.* FROM booking b INNER JOIN room r ON b.room_no = r.room_no LEFT JOIN review_list rl ON b.booking_no = rl.booking_no WHERE b.user_no = ?";

		List<UserBookingVO> userBookingVos = new ArrayList<UserBookingVO>();

		try {
			userBookingVos = jdbcTemplate.query(sql, new RowMapper<UserBookingVO>() {

				@Override
				public UserBookingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserBookingVO userBookingVo = new UserBookingVO();
					userBookingVo.setBooking_no(rs.getInt("booking_no"));
					userBookingVo.setRoom_no(rs.getInt("room_no"));
					userBookingVo.setRoom_status(rs.getInt("room_status"));
					userBookingVo.setUser_no(rs.getInt("user_no"));
					userBookingVo.setRoom_name(rs.getString("room_name"));
					userBookingVo.setB_checkin_date(rs.getDate("b_checkin_date"));
					userBookingVo.setB_checkout_date(rs.getDate("b_checkout_date"));
					userBookingVo.setB_date(rs.getDate("b_date"));
					userBookingVo.setReview_no(rs.getInt("review_no"));
					userBookingVo.setReview_content(rs.getString("review_content"));
					userBookingVo.setReview_star(rs.getFloat("review_star"));
					return userBookingVo;
				}
			}, userVo.getUser_no());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBookingVos;
	}

	public List<CompanyRoomVO> selectCompanyUserRoom(UserVO userVo) {
		System.out.println("[AdminDAO] selectCompanyUserRoom()");
		String sql = "SELECT * FROM room r INNER JOIN user u ON r.user_no = u.user_no WHERE u.user_no = ?";

		List<CompanyRoomVO> CompanyUserRoomVos = new ArrayList<CompanyRoomVO>();

		try {
			CompanyUserRoomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			}, userVo.getUser_no());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CompanyUserRoomVos;
	}
	
	public List<UserBookingVO> selectCompanyBooking(UserVO userVo) {
		System.out.println("[AdminDAO] selectCompanyBooking()");
		String sql = "SELECT room.*, booking.*, user.user_id, review_list.*\r\n"
				+ "FROM room\r\n"
				+ "INNER JOIN booking ON room.room_no = booking.room_no\r\n"
				+ "INNER JOIN user ON booking.user_no = user.user_no\r\n"
				+ "LEFT JOIN review_list ON booking.booking_no = review_list.booking_no\r\n"
				+ "WHERE room.user_no = ? "; 
		List<UserBookingVO> companyBookingVos = new ArrayList<UserBookingVO>();

		try {
			companyBookingVos = jdbcTemplate.query(sql, new RowMapper<UserBookingVO>() {

				@Override
				public UserBookingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserBookingVO userBookingVo = new UserBookingVO();
					userBookingVo.setBooking_no(rs.getInt("booking_no"));
					userBookingVo.setRoom_no(rs.getInt("room_no"));
					userBookingVo.setRoom_status(rs.getInt("room_status"));
					userBookingVo.setUser_no(rs.getInt("user_no"));
					userBookingVo.setUser_id(rs.getString("user_id"));
					userBookingVo.setRoom_name(rs.getString("room_name"));
					userBookingVo.setB_checkin_date(rs.getDate("b_checkin_date"));
					userBookingVo.setB_checkout_date(rs.getDate("b_checkout_date"));
					userBookingVo.setB_date(rs.getDate("b_date"));
					userBookingVo.setReview_no(rs.getInt("review_no"));
					userBookingVo.setReview_content(rs.getString("review_content"));
					userBookingVo.setReview_star(rs.getFloat("review_star"));
					return userBookingVo;
				}
			}, userVo.getUser_no());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyBookingVos;
	}


	public List<CompanyRoomVO> selectRoomStatus1() {
		System.out.println("[AdminDAO] selectRoomStatus1()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_STATUS = 1";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> selectRoomStatus2() {
		System.out.println("[AdminDAO] selectRoomStatus2()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_STATUS = 2";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> selectRoomStatus3() {
		System.out.println("[AdminDAO] selectRoomStatus3()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_STATUS = 3";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSearch1(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomSearch1()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_NAME LIKE ? AND ROOM.ROOM_STATUS = 1";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			}, "%" + roomVo.getRoom_name() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos.size() > 0 ? roomVos : null;
	}

	public List<CompanyRoomVO> roomSearch2(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomSearch2()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_NAME LIKE ? AND ROOM.ROOM_STATUS = 2";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			}, "%" + roomVo.getRoom_name() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos.size() > 0 ? roomVos : null;
	}

	public List<CompanyRoomVO> roomSearch3(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomSearch3()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_NAME LIKE ? AND ROOM.ROOM_STATUS = 3";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			}, "%" + roomVo.getRoom_name() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos.size() > 0 ? roomVos : null;
	}

	public List<CompanyRoomVO> roomSelect1Status1() {
		System.out.println("[AdminDAO] roomSelect1Status1()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 1 ORDER BY ROOM_NAME ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect2Status1() {
		System.out.println("[AdminDAO] roomSelect2Status1()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 1 ORDER BY ROOM_REGI_DATE DESC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect3Status1() {
		System.out.println("[AdminDAO] roomSelect3Status1()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 1 ORDER BY ROOM_REGI_DATE ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect1Status2() {
		System.out.println("[AdminDAO] roomSelect1Status2()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 2 ORDER BY ROOM_NAME ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect2Status2() {
		System.out.println("[AdminDAO] roomSelect2Status2()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 2 ORDER BY ROOM_REGI_DATE DESC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect3Status2() {
		System.out.println("[AdminDAO] roomSelect3Status2()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 2 ORDER BY ROOM_REGI_DATE ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect1Status3() {
		System.out.println("[AdminDAO] roomSelect1Status3()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 3 ORDER BY ROOM_NAME ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect2Status3() {
		System.out.println("[AdminDAO] roomSelect2Status3()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 3 ORDER BY ROOM_REGI_DATE DESC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public List<CompanyRoomVO> roomSelect3Status3() {
		System.out.println("[AdminDAO] roomSelect3Status3()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM_STATUS = 3 ORDER BY ROOM_REGI_DATE ASC";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos;
	}

	public int roomApproval(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomApproval()");
		String sql = "UPDATE room SET room_status = 3 WHERE room_no = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int roomCancle(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomCancle()");
		String sql = "UPDATE room SET room_status = 2 WHERE room_no = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int roomDelete(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomDelete()");
		String sql = "DELETE FROM room WHERE room_no = ?";

		int result = -1;
		try {
			result = jdbcTemplate.update(sql, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public CompanyRoomVO roomSearch(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] roomSearch()");
		String sql = "SELECT * FROM ROOM INNER JOIN USER ON ROOM.user_no = USER.user_no WHERE ROOM.ROOM_NO = ?";

		List<CompanyRoomVO> roomVos = new ArrayList<CompanyRoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<CompanyRoomVO>() {

				@Override
				public CompanyRoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					CompanyRoomVO roomVo = new CompanyRoomVO();
					roomVo.setUser_no(rs.getInt("user_no"));
					roomVo.setUser_name(rs.getString("user_name"));
					roomVo.setUser_phone(rs.getString("user_phone"));
					roomVo.setUser_email(rs.getString("user_email"));
					roomVo.setRoom_no(rs.getInt("room_no"));
					roomVo.setT_set_no(rs.getInt("t_set_no"));
					roomVo.setRoom_name(rs.getString("room_name"));
					roomVo.setRoom_max_ppl(rs.getInt("room_max_ppl"));
					roomVo.setRoom_images(rs.getString("room_images"));
					roomVo.setRoom_regi_date(rs.getDate("room_regi_date"));
					roomVo.setRoom_address(rs.getString("room_address"));
					roomVo.setRoom_price(rs.getInt("room_price"));
					roomVo.setRoom_faq(rs.getString("room_faq"));
					roomVo.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
					roomVo.setRoom_status(rs.getInt("room_status"));

					return roomVo;
				}
			}, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos.size() > 0 ? roomVos.get(0) : null;
	}

	public List<RoomReviewVO> CompanyPlaceReviewlist(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] CompanyPlaceReviewlist()");
		String sql = "SELECT rl.*, u.user_no, u.user_id FROM review_list rl JOIN room r ON rl.room_no = r.room_no JOIN booking b ON rl.booking_no = b.booking_no JOIN user u ON b.user_no = u.user_no WHERE r.room_no = ?";

		List<RoomReviewVO> reviewVos = new ArrayList<RoomReviewVO>();

		try {
			reviewVos = jdbcTemplate.query(sql, new RowMapper<RoomReviewVO>() {

				@Override
				public RoomReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomReviewVO reviewVo = new RoomReviewVO();
					reviewVo.setUser_no(rs.getString("user_no"));
					reviewVo.setUser_id(rs.getString("user_id"));
					reviewVo.setReview_no(rs.getInt("review_no"));
					reviewVo.setBooking_no(rs.getInt("review_no"));
					reviewVo.setRoom_no(rs.getInt("room_no"));
					reviewVo.setReview_content(rs.getString("review_content"));
					reviewVo.setReview_star(rs.getFloat("review_star"));
					reviewVo.setReview_date(rs.getDate("review_date"));

					return reviewVo;
				}
			}, roomVo.getRoom_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewVos.size() > 0 ? reviewVos : null;
	}

	public TableSetVO selectRoomTableSet(CompanyRoomVO roomVo) {
		System.out.println("[AdminDAO] selectRoomTableSet()");
		String sql = "SELECT * FROM table_set WHERE t_set_no = ?";

		List<TableSetVO> tableSetVos = new ArrayList<TableSetVO>();

		try {
			tableSetVos = jdbcTemplate.query(sql, new RowMapper<TableSetVO>() {

				@Override
				public TableSetVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					TableSetVO tableSetVo = new TableSetVO();
					tableSetVo.setT_set_no(rs.getInt("t_set_no"));
					tableSetVo.setT_set1(rs.getBoolean("t_set1"));
					tableSetVo.setT_set2(rs.getBoolean("t_set2"));
					tableSetVo.setT_set3(rs.getBoolean("t_set3"));
					tableSetVo.setT_set4(rs.getBoolean("t_set4"));

					return tableSetVo;
				}
			}, roomVo.getT_set_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableSetVos.size() > 0 ? tableSetVos.get(0) : null;
	}
}
