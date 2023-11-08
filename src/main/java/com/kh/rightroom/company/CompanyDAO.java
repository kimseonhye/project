package com.kh.rightroom.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.rightroom.vo.ReservationListVO;
import com.kh.rightroom.vo.RoomDetailVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.UserVO;

@Repository
public class CompanyDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;

	// 회원 등록 메서드
	public int registerUser(UserVO userVO) {
		String sql = "INSERT INTO USER(user_id, user_pw, user_name, user_phone, user_email, user_rank) "
				+ "VALUES (:user_id, :user_pw, :user_name, :user_phone, :user_email, :user_rank)";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", userVO.getUser_id());
		params.addValue("user_pw", passwordEncoder.encode(userVO.getUser_pw())); // 비밀번호를 암호화하여 저장
		params.addValue("user_name", userVO.getUser_name());
		params.addValue("user_phone", userVO.getUser_phone());
		params.addValue("user_email", userVO.getUser_email());
		params.addValue("user_rank", userVO.getUser_rank());

		return namedParameterJdbcTemplate.update(sql, params); // 데이터베이스에 회원 정보를 등록하고 결과를 반환
	}

	// ID 중복 확인
	public boolean isUserIdUnique(String user_id) {
		String sql = "SELECT COUNT(*) FROM USER WHERE user_id = :user_id";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", user_id);

		int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);

		return count == 0; // 0이면 중복 아이디가 없음
	}

	public List<RoomVO> selectComfirmPlaces() {
		System.out.println("[CompanyDAO] selectAllPlaces() called");

		String sql = "SELECT * FROM room " + "WHERE room_status = 3 " + "ORDER BY room_regi_date DESC";

		List<RoomVO> places = new ArrayList<RoomVO>();

		try {
			places = jdbcTemplate.query(sql, new RowMapper<RoomVO>() {
				@Override
				public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomVO roomVO = new RoomVO();

					roomVO.setRoom_no(rs.getInt("room_no"));
					roomVO.setRoom_name(rs.getString("room_name"));
					roomVO.setRoom_images(rs.getString("room_images"));
					roomVO.setRoom_regi_date(rs.getDate("room_regi_date"));

					return roomVO;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return places.size() > 0 ? places : null;
	}

	public List<RoomVO> selectWaitPlaces() {
		System.out.println("[CompanyDAO] selectWaitPlaces() called");

		String sql = "SELECT * FROM room " + "WHERE room_status = 1 " + "ORDER BY room_regi_date ASC";

		List<RoomVO> places = new ArrayList<RoomVO>();

		try {
			places = jdbcTemplate.query(sql, new RowMapper<RoomVO>() {
				@Override
				public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomVO roomVO = new RoomVO();

					roomVO.setRoom_no(rs.getInt("room_no"));
					roomVO.setRoom_name(rs.getString("room_name"));
					roomVO.setRoom_images(rs.getString("room_images"));
					roomVO.setRoom_regi_date(rs.getDate("room_regi_date"));

					return roomVO;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return places.size() > 0 ? places : null;
	}

	public List<ReservationListVO> selectAllList() {
		System.out.println("[CompanyDAO] - selectAllList() - called");

		String sql = "SELECT booking_no , room_name , user_name , user_phone , b_checkin_date "
				+ "FROM booking INNER JOIN user " + "ON booking.user_no = user.user_no " + "INNER JOIN room "
				+ "ON booking.room_no = room.room_no " + "ORDER BY booking_no ASC";

		List<ReservationListVO> reserve = new ArrayList<ReservationListVO>();

		try {
			reserve = jdbcTemplate.query(sql, new RowMapper<ReservationListVO>() {
				@Override
				public ReservationListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReservationListVO reserveVO = new ReservationListVO();

					reserveVO.setBooking_no(rs.getInt("booking_no"));
					reserveVO.setRoom_name(rs.getString("room_name"));
					reserveVO.setUser_name(rs.getString("user_name"));
					reserveVO.setUser_phone(rs.getString("user_phone"));
					reserveVO.setStart_date(rs.getDate("b_checkin_Date"));

					return reserveVO;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reserve.size() > 0 ? reserve : null;
	}

	public List<ReservationListVO> deleteList(int number) {
		System.out.println("[CompanyDAO] - deleteList() - called");

		String sql = "DELETE FROM booking WHERE booking_no = ?";
		List<ReservationListVO> reserve = new ArrayList<ReservationListVO>();

		try {
			reserve = jdbcTemplate.query(sql, new RowMapper<ReservationListVO>() {
				@Override
				public ReservationListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReservationListVO reserveVO = new ReservationListVO();

					reserveVO.setBooking_no(rs.getInt("booking_no"));
					reserveVO.setRoom_name(rs.getString("room_name"));
					reserveVO.setUser_name(rs.getString("user_name"));
					reserveVO.setUser_phone(rs.getString("user_phone"));
					reserveVO.setStart_date(rs.getDate("b_checkin_Date"));

					return reserveVO;
				}
			}, number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reserve.size() > 0 ? reserve : null;
	}

	public List<ReservationListVO> listShowInfo(int number) {
		System.out.println("[CompanyDAO] - listShowInfo() - called");

		String sql = "SELECT user.user_name, user.user_phone, user.user_email, booking.b_ppl, room.room_price, booking.b_name, booking.b_purpose, booking.b_comment FROM booking "
				+ "INNER JOIN user " + "ON booking.user_no = user.user_no " + "INNER JOIN room "
				+ "ON booking.room_no = room.room_no " + "WHERE booking.booking_no = ?";

		List<ReservationListVO> reserve = new ArrayList<ReservationListVO>();

		try {
			reserve = jdbcTemplate.query(sql, new RowMapper<ReservationListVO>() {
				@Override
				public ReservationListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReservationListVO reserveVO = new ReservationListVO();

					reserveVO.setUser_name(rs.getString("user_name"));
					reserveVO.setUser_phone(rs.getString("user_phone"));
					reserveVO.setUser_email(rs.getString("user_email"));
					reserveVO.setB_ppl(rs.getInt("b_ppl"));
					reserveVO.setRoom_price(rs.getInt("room_price"));
					reserveVO.setB_name(rs.getString("b_name"));
					reserveVO.setB_purpose(rs.getString("b_purpose"));
					reserveVO.setB_comment(rs.getString("b_comment"));

					return reserveVO;
				}
			}, number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reserve.size() > 0 ? reserve : null;
	}

	public RoomDetailVO findRoomDetail(int roomNo) {
		String sql = "SELECT * FROM room " + "JOIN user ON room.user_no = user.user_no "
				+ "JOIN table_set ON room.t_set_no = table_set.t_set_no "
				+ "LEFT JOIN booking ON room.room_no = booking.room_no "
				+ "LEFT JOIN review_list ON room.room_no = review_list.room_no " + "WHERE room.room_no = ?";

		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { roomNo }, new RoomDetailRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public class RoomDetailRowMapper implements RowMapper<RoomDetailVO> {
		@Override
		public RoomDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoomDetailVO roomDetail = new RoomDetailVO();

			// Room 테이블 필드 매핑
			roomDetail.setRoom_no(rs.getInt("room_no"));
			roomDetail.setUser_no(rs.getInt("user_no"));
			roomDetail.setT_set_no(rs.getInt("t_set_no"));
			roomDetail.setRoom_images(rs.getString("room_images"));
			roomDetail.setRoom_price(rs.getInt("room_price"));
			roomDetail.setRoom_max_ppl(rs.getInt("room_max_ppl"));
			roomDetail.setRoom_faq(rs.getString("room_faq"));
			roomDetail.setRoom_cancellation_and_refund_policy(rs.getString("room_cancellation_and_refund_policy"));
			roomDetail.setRoom_name(rs.getString("room_name"));
			roomDetail.setRoom_address(rs.getString("room_address"));
			roomDetail.setRoom_regi_date(rs.getDate("room_regi_date"));

			// Table_set 테이블 필드 매핑
			roomDetail.setT_set1(rs.getBoolean("t_set1"));
			roomDetail.setT_set2(rs.getBoolean("t_set2"));
			roomDetail.setT_set3(rs.getBoolean("t_set3"));
			roomDetail.setT_set4(rs.getBoolean("t_set4"));

			// User 테이블 필드 매핑
			roomDetail.setUser_name(rs.getString("user_name"));
			// User 테이블의 user_phone 컬럼을 참조한다고 가정합니다. 실제 스키마에 맞게 수정이 필요할 수 있습니다.
			roomDetail.setUser_phone(rs.getString("user_phone"));

			// Booking 테이블 필드 매핑
			// SQL 쿼리에서 booking_no를 선택하였다고 가정합니다. 실제 필요한 컬럼에 따라 수정이 필요할 수 있습니다.
			roomDetail.setBooking_no(rs.getInt("booking_no"));

			// Review_list 테이블 필드 매핑
			// 이 부분은 review_list 테이블의 데이터가 NULL일 수 있으므로 체크가 필요함
			roomDetail.setReview_no(rs.getInt("review_no"));
			roomDetail.setReview_Content(rs.getString("review_content"));
			roomDetail.setReview_Star(rs.getFloat("review_star"));
			roomDetail.setReview_date(rs.getDate("review_date"));

			return roomDetail;
		}
	}

	// 룸테이블 업데이트
	public boolean updateRoom(int roomNo, RoomDetailVO updatedRoomDetail) {
		String sql = "UPDATE room SET " + "room_name = ?, " + "room_max_ppl = ?, " + "room_price = ?, "
				+ "room_faq = ?, " + "room_cancellation_and_refund_policy = ?, " + "room_address = ? "
				+ "WHERE room_no = ?";

		try {
			int updatedRowCount = jdbcTemplate.update(sql, updatedRoomDetail.getRoom_name(),
					updatedRoomDetail.getRoom_max_ppl(), updatedRoomDetail.getRoom_price(),
					updatedRoomDetail.getRoom_faq(), updatedRoomDetail.getRoom_cancellation_and_refund_policy(),
					updatedRoomDetail.getRoom_address(), roomNo);
			return updatedRowCount > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 테이블세팅 테이블 업데이트
	public boolean updateTableSet(int tSetNo, RoomDetailVO updatedRoomDetail) {
		String sql = "UPDATE table_set SET " + "t_set1 = ?, " + "t_set2 = ?, " + "t_set3 = ?, " + "t_set4 = ? "
				+ "WHERE t_set_no = ?";

		try {
			int updatedRowCount = jdbcTemplate.update(sql, updatedRoomDetail.getT_set1(), updatedRoomDetail.getT_set2(),
					updatedRoomDetail.getT_set3(), updatedRoomDetail.getT_set4(), tSetNo);
			return updatedRowCount > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateRoomDetail(int roomNo, int tSetNo, int userNo, RoomDetailVO updatedRoomDetail) {
		boolean isRoomUpdated = updateRoom(roomNo, updatedRoomDetail);
		boolean isTableSetUpdated = updateTableSet(tSetNo, updatedRoomDetail);
		boolean isUserUpdated = true;

		return isRoomUpdated && isTableSetUpdated && isUserUpdated;
	}

	@Transactional
	public void deleteRoom(int roomNo) {
		// 1. room 테이블에서 해당 room_no로 t_set_no를 가져옵니다.
		String selectTSetSql = "SELECT t_set_no FROM room WHERE room_no = ?";
		Integer tSetNo = jdbcTemplate.queryForObject(selectTSetSql, new Object[] { roomNo }, Integer.class);

		// tSetNo가 null이라면, 존재하지 않는 room_no이므로 RuntimeException을 발생시킵니다.
		if (tSetNo == null) {
			throw new RuntimeException("No room found with room_no: " + roomNo);
		}

		// 2. review_list 테이블에서 데이터를 삭제합니다.
		String deleteReviewSql = "DELETE FROM review_list WHERE room_no = ?";
		jdbcTemplate.update(deleteReviewSql, roomNo);

		// 3. room 테이블에서 데이터를 삭제합니다.
		String deleteRoomSql = "DELETE FROM room WHERE room_no = ?";
		jdbcTemplate.update(deleteRoomSql, roomNo);

		// 4. table_set 테이블에서 데이터를 삭제합니다.
		String deleteTableSetSql = "DELETE FROM table_set WHERE t_set_no = ?";
		jdbcTemplate.update(deleteTableSetSql, tSetNo);
	}

	public List<RoomDetailVO> findAllMyPlaces(int userNo) {
        String sql = "SELECT room.*, user.user_name, user.user_phone, table_set.*, b.*, r.* " + 
                     "FROM room " + 
                     "JOIN user ON room.user_no = user.user_no " + 
                     "JOIN table_set ON room.t_set_no = table_set.t_set_no " +
                     "LEFT JOIN (" +
                     "    SELECT booking.* FROM booking JOIN (SELECT room_no, MAX(b_date) as max_date FROM booking GROUP BY room_no) as latest_bookings ON booking.room_no = latest_bookings.room_no AND booking.b_date = latest_bookings.max_date" +
                     ") AS b ON room.room_no = b.room_no " +
                     "LEFT JOIN (" +
                     "    SELECT review_list.* FROM review_list JOIN (SELECT room_no, MAX(review_date) as max_review_date FROM review_list GROUP BY room_no) as latest_reviews ON review_list.room_no = latest_reviews.room_no AND review_list.review_date = latest_reviews.max_review_date" +
                     ") AS r ON room.room_no = r.room_no " +
                     "WHERE room.user_no = ?";

        return jdbcTemplate.query(sql, new Object[]{userNo}, new RoomDetailRowMapper());
    }

	public Map<String, Object> getUser(String user_id) {
		System.out.println("[UserDAO] getUser()");
		String sql = "SELECT user_id, user_pw, user_name, user_phone, user_email, user_rank FROM USER WHERE user_id = ? ";
		Object[] args = { user_id };
		int[] argTypes = { java.sql.Types.VARCHAR };

		return jdbcTemplate.queryForMap(sql, args, argTypes);
	}

	public int userModify(UserVO userVO) {
		System.out.println("[UserDAO] userModify()");
		String sql = "UPDATE user \n"
				+ "SET user_pw = ?, user_name = ?, user_phone = ?, user_email = ?, user_rank = ? \n"
				+ "WHERE user_id = ? ";

		String hashedPassword = passwordEncoder.encode(userVO.getUser_pw());

		return jdbcTemplate.update(sql, hashedPassword, userVO.getUser_name(), userVO.getUser_phone(),
				userVO.getUser_email(), userVO.getUser_rank(), userVO.getUser_id());
	}

	public int userDel(UserVO userVO) {
		System.out.println("[UserDAO] userDel()");
		String sql = "DELETE FROM user WHERE user_id = ?";
		return jdbcTemplate.update(sql, userVO.getUser_id());
	}
}
