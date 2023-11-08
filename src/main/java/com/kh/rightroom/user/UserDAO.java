package com.kh.rightroom.user;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.UserListVO;
import com.kh.rightroom.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender javaMailSender;

	// 회원 등록 메서드
	public int registerUser(UserVO userVO) {
		System.out.println("[UserDAO] registerUser()");
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

	// 사용자 아이디를 이메일로 조회하는 메서드
	public String findUserId(String user_email) {
		System.out.println("[UserDAO] findUserId()");
		String sql = "SELECT user_id FROM USER WHERE user_email = :user_email";
		System.out.println(user_email);

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_email", user_email);

		try {
			return namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
		} catch (Exception e) {
			return null; // 이메일로 아이디를 찾을 수 없는 경우
		}
	}

	public String findUserPassword(String user_id, String user_email) {
		System.out.println("[UserDAO] findUserPassword()");
		String select_sql = "SELECT user_pw FROM USER WHERE user_id = :user_id AND user_email = :user_email";

		MapSqlParameterSource select_params = new MapSqlParameterSource();
		select_params.addValue("user_id", user_id);
		select_params.addValue("user_email", user_email);

		try {
			// 새로운 임시 비밀번호 생성
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
			int passwordLength = 10;
			Random random = new Random();

			StringBuilder password = new StringBuilder();
			for (int i = 0; i < passwordLength; i++) {
				int randomIndex = random.nextInt(characters.length());
				char randomChar = characters.charAt(randomIndex);
				password.append(randomChar);
			}
			String newPassword = password.toString();

			// 데이터베이스에서 비밀번호 업데이트
			String update_sql = "UPDATE USER SET user_pw = :newPassword WHERE user_id = :user_id";

			MapSqlParameterSource update_params = new MapSqlParameterSource();
			update_params.addValue("user_id", user_id);
			update_params.addValue("newPassword", passwordEncoder.encode(newPassword));

			namedParameterJdbcTemplate.update(update_sql, update_params);

			// 이메일을 통해 새로운 임시 비밀번호 전송
			try {
				System.out.println("[UserDAO] mailTest 시작");
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("1996371cm@gmail.com"); // 여기에 자신의 이메일 주소를 설정
				message.setTo(user_email);
				message.setSubject("[RightRoom] 임시 비밀번호 안내");
				message.setText("안녕하세요. RightRoom 임시 비밀번호 안내 드립니다. " + newPassword);
				System.out.println(message);

				javaMailSender.send(message);
				System.out.println("[UserDAO] mailTest 성공");
			} catch (Exception e) {
				// 이메일 전송 중 발생하는 예외 처리, 예를 들어 메일 서버 문제 등을 처리합니다.
				System.out.println("[UserDAO] mailTest 실패");
				e.printStackTrace();
			}

			return newPassword;

		} catch (Exception e) {
			return null; // 아이디와 이메일로 비밀번호를 찾을 수 없는 경우
		}
	}

	public Map<String, Object> userLogin(String user_id, String user_pw) {
		System.out.println("[UserDAO] userLogin()");
		String sql = "SELECT user_pw FROM USER WHERE user_id = ?";
		String hashedPassword = jdbcTemplate.queryForObject(sql, String.class, user_id);

		if (hashedPassword != null && passwordEncoder.matches(user_pw, hashedPassword)) {
			sql = "SELECT user_id, user_pw, user_name, user_phone, user_email, user_rank FROM USER WHERE user_id = ? AND user_pw = ?";
			Object[] args = { user_id, hashedPassword };
			int[] argTypes = { java.sql.Types.VARCHAR, java.sql.Types.VARCHAR };

			return jdbcTemplate.queryForMap(sql, args, argTypes);
		} else {
			return null;
		}
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

	public List<UserListVO> selectUserBooking(UserVO userVo) {
		String sql = "SELECT * FROM booking b JOIN room r ON r.room_no = b.room_no JOIN USER u ON u.user_no = r.user_no WHERE b.user_no = ?";

		List<UserListVO> userBookingVos = new ArrayList<UserListVO>();

		try {
			userBookingVos = jdbcTemplate.query(sql, new RowMapper<UserListVO>() {

				@Override
				public UserListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserListVO userBookingVo = new UserListVO();
					userBookingVo.setUser_name(rs.getString("user_name"));
					userBookingVo.setBooking_no(rs.getInt("booking_no"));
					userBookingVo.setUser_no(rs.getInt("user_no"));
					userBookingVo.setRoom_no(rs.getInt("room_no"));
					userBookingVo.setB_ppl(rs.getInt("b_ppl"));
					userBookingVo.setB_name(rs.getString("b_name"));
					userBookingVo.setB_purpose(rs.getString("b_purpose"));
					userBookingVo.setB_checkin_date(rs.getDate("b_checkin_date"));
					userBookingVo.setB_date(rs.getDate("b_date"));
					userBookingVo.setB_status(rs.getInt("b_status"));
					userBookingVo.setB_checkout_date(rs.getDate("b_checkout_date"));
					userBookingVo.setB_comment(rs.getString("b_comment"));
					userBookingVo.setRoom_name(rs.getString("room_name"));
					userBookingVo.setRoom_images(rs.getString("room_images"));
					userBookingVo.setRoom_address(rs.getString("room_address"));

					return userBookingVo;
				}
			}, userVo.getUser_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBookingVos;
	}

	public UserVO FindLoginUser(String userId) {
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
			}, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVos.size() > 0 ? userVos.get(0) : null;
	}

	public List<ReviewVO> userReviewList(UserVO uservo) {
		String sql = "SELECT * FROM review_list r JOIN booking b ON r.booking_no = b.booking_no JOIN room ro ON b.room_no = ro.room_no JOIN user u ON ro.user_no = u.user_no WHERE\n"
				+ "b.user_no = ?";
		List<ReviewVO> ReviewVos = new ArrayList<ReviewVO>();

		try {
			ReviewVos = jdbcTemplate.query(sql, new RowMapper<ReviewVO>() {

				@Override
				public ReviewVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					ReviewVO reivewVo = new ReviewVO();
					reivewVo.setReview_no(rs.getInt("review_no"));
					reivewVo.setRoom_no(rs.getInt("room_no"));
					reivewVo.setBooking_no(rs.getInt("booking_no"));
					reivewVo.setReview_content(rs.getString("review_content"));
					reivewVo.setReview_star(rs.getFloat("review_star"));
					reivewVo.setReview_date(rs.getDate("review_date"));
					reivewVo.setRoom_name(rs.getString("room_name"));
					reivewVo.setB_checkin_date(rs.getDate("b_checkin_date"));
					reivewVo.setB_checkout_date(rs.getDate("b_checkout_date"));
					reivewVo.setRoom_name(rs.getString("user_name"));

					return reivewVo;
				}
			}, uservo.getUser_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ReviewVos;
	}

	public int reviewSubmit(BookingVO bookingVo, int room_no, ReviewVO review) {
		String sql = "INSERT INTO review_list (room_no,booking_no,review_content,review_star,review_date) VALUES (?,?,?,?,?)";
		int result = -1;
		try {
			Date currentDate = new Date();
			Timestamp timestamp = new Timestamp(currentDate.getTime());

			result = jdbcTemplate.update(sql, room_no, bookingVo.getBooking_no(), review.getReview_content(),
					review.getReview_star(), timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int findRoomNo(BookingVO bookingVo) {
		String sql = "SELECT room_no FROM booking WHERE booking_no = ?";

		List<RoomVO> roomVos = new ArrayList<RoomVO>();

		try {
			roomVos = jdbcTemplate.query(sql, new RowMapper<RoomVO>() {

				@Override
				public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					RoomVO roomVo = new RoomVO();
					roomVo.setRoom_no(rs.getInt("room_no"));
					return roomVo;
				}
			}, bookingVo.getBooking_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomVos.size() > 0 ? roomVos.get(0).getRoom_no() : null;
	}

	public int deleteReview(ReviewVO reviewVo) {
		String sql = "DELETE FROM review_list WHERE REVIEW_NO = ?";

		int result = -1;

		try {
			result = jdbcTemplate.update(sql, reviewVo.getReview_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public BookingVO selectReserve(BookingVO bookingVo) {
		String sql = "SELECT booking.*, room.room_price\r\n"
				+ "FROM booking\r\n"
				+ "INNER JOIN room ON booking.room_no = room.room_no\r\n"
				+ "WHERE booking.booking_no = ?;";

		List<BookingVO> bookingVos = new ArrayList<BookingVO>();

		try {
			bookingVos = jdbcTemplate.query(sql, new RowMapper<BookingVO>() {

				@Override
				public BookingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					BookingVO bookingVo = new BookingVO();
					bookingVo.setB_user_name(rs.getString("b_user_name"));
					bookingVo.setB_user_contact(rs.getString("b_user_contact"));
					bookingVo.setB_user_email(rs.getString("b_user_email"));
					bookingVo.setB_company(rs.getString("b_company"));
					bookingVo.setB_ppl(rs.getInt("b_ppl"));
					bookingVo.setB_name(rs.getString("b_name"));
					bookingVo.setB_purpose(rs.getString("b_purpose"));
					bookingVo.setRoom_price(rs.getInt("room_price"));
					bookingVo.setB_table_set(rs.getString("b_table_set"));
					bookingVo.setB_comment(rs.getString("b_comment"));
					
					return bookingVo;
				}
			}, bookingVo.getBooking_no());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookingVos.size() > 0 ? bookingVos.get(0) : null;
	}
}
