package com.kh.rightroom.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.RoomVO;

@Component
public class UserReserveConfirmDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
		public BookingVO selectReserveBooking(int booking_no) {
			
			System.out.println("[BookingVO] selectReserve");
	
	        String sql = "SELECT * FROM booking "
	        			+ "WHERE booking_no = ?";
	        
	        BookingVO bookingVO = null;
	       
	        try {
	        	bookingVO = jdbcTemplate.queryForObject(sql, new RowMapper<BookingVO>() {
	
		        @Override
		        public BookingVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	BookingVO bookingVO = new BookingVO();
		        	
		        	bookingVO.setBooking_no(rs.getInt("booking_no"));
		        	bookingVO.setB_user_name(rs.getString("b_user_name"));
		        	bookingVO.setB_user_contact(rs.getString("b_user_contact"));
		        	bookingVO.setB_user_email(rs.getString("b_user_email"));
		        	bookingVO.setB_company(rs.getString("b_company"));
		        	bookingVO.setB_ppl(rs.getInt("b_ppl"));
		        	bookingVO.setB_name(rs.getString("b_name"));
		        	bookingVO.setB_purpose(rs.getString("b_purpose"));
		        	bookingVO.setB_table_set(rs.getString("b_table_set"));
		        	bookingVO.setB_comment(rs.getString("b_comment"));
		
		            return bookingVO;
		        }
		    }, booking_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	        return bookingVO;
		}
		
		public RoomVO selectReserveRoom(int room_no) {
			
			System.out.println("[RoomVO] selectReserve");
	
	        String sql = "SELECT * FROM room "
	        			+ "WHERE room_no = ?";
	        
	        RoomVO roomVO = null;
	       
	        try {
	        	roomVO = jdbcTemplate.queryForObject(sql, new RowMapper<RoomVO>() {
	
		        @Override
		        public RoomVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	RoomVO roomVO = new RoomVO();
		        	
		        	roomVO.setRoom_no(rs.getInt("room_no"));
		        	roomVO.setRoom_price(rs.getInt("room_price"));
		
		            return roomVO;
		        }
		    }, room_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
	        return roomVO;
		}
}
