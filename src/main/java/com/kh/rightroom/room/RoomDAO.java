package com.kh.rightroom.room;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.RoomRegisterVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.TableSetVO;

@Repository
public class RoomDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate; // 데이터베이스와 상호 작용하기 위한 JdbcTemplate를 사용

	// 장소 조건 검색
	public List<RoomVO> searchRooms(String room_address, int room_max_ppl, String room_price) {
		System.out.println("[RoomDAO] searchRooms()");

		// 수정된 SQL 쿼리
		String sql = "SELECT * FROM room WHERE room_address LIKE ? AND room_max_ppl >= ? AND room_price <= ?";
		try {
			RowMapper<RoomVO> rowMapper = BeanPropertyRowMapper.newInstance(RoomVO.class);
			// '%'를 추가하여 LIKE 연산을 가능하게 합니다.
			List<RoomVO> roomVOs = jdbcTemplate.query(sql, rowMapper, "%" + room_address + "%", room_max_ppl,
					room_price);
			System.out.println("[RoomDAO] searchRooms()");
			return roomVOs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	// 장소 상세 조회 페이지
	public RoomVO selectRoom(int room_no) {
		System.out.println("[RoomDAO] selectRoom()");

		String sql = "SELECT * FROM room WHERE room_no = ?";

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

	// 리뷰 목록 조회 메서드
	public List<ReviewVO> selectReviewsForRoom(int room_no) {
		System.out.println("[RoomDAO] selectReviewsForRoom()");

		String sql = "SELECT * FROM review_list WHERE room_no = ?";
		try {
			System.out.println("[RoomDAO] 리뷰 가져옴");
			System.out.println(room_no);
			RowMapper<ReviewVO> rowMapper = BeanPropertyRowMapper.newInstance(ReviewVO.class);
			List<ReviewVO> reviewVOs = jdbcTemplate.query(sql, rowMapper, room_no);

			for (ReviewVO review : reviewVOs) {
				System.out.println("Review Content: " + review.getReview_content());
				System.out.println("Review date: " + review.getReview_date());
				System.out.println("Review star: " + review.getReview_star());

			}

			return reviewVOs;

		} catch (Exception e) {
			System.out.println("[RoomDAO] 리뷰 실패임");
			e.printStackTrace();
		}

		return Collections.emptyList(); // 예외 발생 시 빈 목록 반환
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
	 
	// 장소 등록 관련 테이블 조인 하는 메서드
	public boolean isRoomExists(int roomNo) {
		System.out.println("[RoomDAO] isRoomExists 작동중!!");

//     String sql = "SELECT COUNT(*) FROM room " +
//                    "JOIN USER ON room.user_no = USER.user_no " +
//                    "JOIN table_set ON room.t_set_no = table_set.t_set_no " +
//                    "WHERE room.room_no = ?";
		String sql = "SELECT COUNT(*) FROM room WHERE room.room_no = ?";

		int result = jdbcTemplate.queryForObject(sql, Integer.class, roomNo);

		return result > 0;
	}

	public int insertTableSet(RoomRegisterVO roomRegisterVO) {
		System.out.println("[RoomDAO] insertTableSet 작동중!!");
		String sql = "INSERT INTO table_set(t_set1, t_set2, t_set3, t_set4) VALUES (?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, new String[] { "t_set_no" });
			ps.setBoolean(1, roomRegisterVO.getT_set1() != null && roomRegisterVO.getT_set1());
			ps.setBoolean(2, roomRegisterVO.getT_set2() != null && roomRegisterVO.getT_set2());
			ps.setBoolean(3, roomRegisterVO.getT_set3() != null && roomRegisterVO.getT_set3());
			ps.setBoolean(4, roomRegisterVO.getT_set4() != null && roomRegisterVO.getT_set4());
			return ps;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int insertRoom(RoomRegisterVO roomRegisterVO, int tSetNo) {
		String sql = "INSERT INTO room(user_no, t_set_no, room_images, room_name, room_address, room_price, room_max_ppl, room_faq, room_cancellation_and_refund_policy, room_regi_date, room_status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), 1);";

		try {
			return jdbcTemplate.update(sql, roomRegisterVO.getUser_no(), tSetNo, roomRegisterVO.getRoom_images(),
					roomRegisterVO.getRoom_name(), roomRegisterVO.getRoom_address(), roomRegisterVO.getRoom_price(),
					roomRegisterVO.getRoom_max_ppl(), roomRegisterVO.getRoom_faq(),
					roomRegisterVO.getRoom_cancellation_and_refund_policy());
		} catch (DataAccessException e) {
			// 로깅과 에러 핸들링 로직을 여기에 추가
			throw new RuntimeException("Error inserting into room", e); // or throw your custom exception
		}
	}

	// 대기상태인 룸 상태를 승인 또는 반려해주는 메서드
	public int updateRoomStatus(int roomNo, int newStatus) {
		String sql = "UPDATE room SET room_status = ? WHERE room_no = ?";

		return jdbcTemplate.update(sql, newStatus, roomNo);
	}

}
