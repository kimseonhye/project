package com.kh.rightroom.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.UserRoomBookingVO;

@Component
public class UserRoomBookingDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<UserRoomBookingVO> getAllURBs() {

        String sql = "SELECT * FROM user u "
        			+ "JOIN room r ON u.user_no = r.user_no "
        			+ "WHERE r.room_name = '방 이름'";

		List<UserRoomBookingVO> urb = jdbcTemplate.query(sql, new RoomRowMapper());

        return urb;
    }
	
    private static class RoomRowMapper implements RowMapper<UserRoomBookingVO> {
        @Override
        public UserRoomBookingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserRoomBookingVO urb = new UserRoomBookingVO();
            urb.setUser_name(rs.getString("user_name"));
            urb.setRoom_name(rs.getString("room_name"));
            urb.setRoom_address(rs.getString("room_address"));
            urb.setUser_phone(rs.getString("user_phone"));
            urb.setRoom_price(rs.getInt("room_price"));

            return urb;
        }
    }
    
	public int insertBooking(BookingVO bookingVO) {
		
		System.out.println("[UserBookingDAO] 데이터 들어가는지 확인");
		
		String sql = "INSERT INTO booking (user_no, room_no, b_user_name, b_user_contact, b_user_email, "
	                + "b_company, b_ppl, b_name, b_purpose, b_table_set, b_checkin_date, b_date, b_comment) "
	                + "VALUES (1, 1, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?)";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql, 
					bookingVO.getB_user_name(),
					bookingVO.getB_user_contact(),
					bookingVO.getB_user_email(),
					bookingVO.getB_company(),
					bookingVO.getB_ppl(),
					bookingVO.getB_name(),
					bookingVO.getB_purpose(),
					bookingVO.getB_table_set(),
					bookingVO.getB_checkin_date(),
					bookingVO.getB_date(),
					bookingVO.getB_comment()
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
