package com.kh.rightroom.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.UserNoticeVO;

@Component
public class AdminNoticeDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isTITLE(String n_u_title) {
		String sql = "SELECT COUNT(*) FROM usernotice WHERE n_u_title = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, n_u_title);
		
		return result > 0 ? true : false;
	}
	
	public int insertNotice(UserNoticeVO userNoticeVO) {
		String sql = "INSERT INTO usernotice(n_u_title, n_u_content, n_u_image, n_u_date) "
				   + "VALUES(?,?,?,NOW())";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql,
					userNoticeVO.getN_u_title(),
					userNoticeVO.getN_u_content(),
					userNoticeVO.getN_u_image());
		} catch (Exception e) {
			e.printStackTrace(); // 예외 정보 출력
		}
		
		return result;
	}
	
	public List<UserNoticeVO> getAllNotices() {
        // 데이터베이스에서 모든 공지사항을 조회하는 SQL 쿼리를 작성합니다.
        String sql = "SELECT * FROM usernotice";

        // JdbcTemplate을 사용하여 데이터를 조회하고 List<UserNoticeVO> 형태로 반환합니다.
        List<UserNoticeVO> notices = jdbcTemplate.query(sql, new UserNoticeRowMapper());

        return notices;
    }

    // UserNoticeVO를 매핑하는 RowMapper 클래스를 작성합니다.
    private static class UserNoticeRowMapper implements RowMapper<UserNoticeVO> {
        @Override
        public UserNoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserNoticeVO notice = new UserNoticeVO();
            notice.setN_u_no(rs.getInt("n_u_no"));
            notice.setN_u_title(rs.getString("n_u_title"));
            notice.setN_u_content(rs.getString("n_u_content"));
            notice.setN_u_image(rs.getString("n_u_image"));
            notice.setN_u_date(rs.getDate("n_u_date"));

            return notice;
        }
    }
    //검색
    public List<UserNoticeVO> selectNoticesBySearch(UserNoticeVO userNoticeVO) {
    	String sql = "SELECT * FROM usernotice "
    				+ "WHERE n_u_title LIKE ? "
    				+ "ORDER BY n_u_no DESC";
    	
    	List<UserNoticeVO> userNoticeVOs = null;
    	
    	try {
    		userNoticeVOs = jdbcTemplate.query(sql, new RowMapper<UserNoticeVO>() {
    			
    			@Override
    			public UserNoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    				
    				UserNoticeVO userNoticeVO = new UserNoticeVO();
    				
    				userNoticeVO.setN_u_no(rs.getInt("n_u_no"));
    				userNoticeVO.setN_u_title(rs.getString("n_u_title"));
    				userNoticeVO.setN_u_content(rs.getString("n_u_content"));
    				userNoticeVO.setN_u_image(rs.getString("n_u_image"));
    				userNoticeVO.setN_u_date(rs.getDate("n_u_date"));
					
					return userNoticeVO;
				}
			}, "%" + userNoticeVO.getN_u_title() + "%");  //컴퓨터(검색) => select * from tbl_book where b_name like %컴퓨터% order by b_no desc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userNoticeVOs.size() > 0 ? userNoticeVOs : null;
	}
    
    //수정
    public UserNoticeVO selectNotice(int n_u_no) {
    	String sql = "SELECT * FROM usernotice WHERE n_u_no = ?";
    	
    	List<UserNoticeVO> userNoticeVOs = null;
    	
    	try {
    		userNoticeVOs = jdbcTemplate.query(sql, new RowMapper<UserNoticeVO>() {
    			
    			@Override
    			public UserNoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
    				
    				UserNoticeVO userNoticeVO = new UserNoticeVO();
    				
    				userNoticeVO.setN_u_no(rs.getInt("n_u_no"));
    				userNoticeVO.setN_u_title(rs.getString("n_u_title"));
    				userNoticeVO.setN_u_content(rs.getString("n_u_content"));
    				userNoticeVO.setN_u_image(rs.getString("n_u_image"));
    				userNoticeVO.setN_u_date(rs.getDate("n_u_date"));
    				
    				return userNoticeVO;    				
    			}
    		}, n_u_no);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return userNoticeVOs.size() > 0 ? userNoticeVOs.get(0) : null;
    }
    
    public int updateNotice(UserNoticeVO userNoticeVO) {
        System.out.println("[AdminNoticeDAO] 리뷰 업데이트 테스트");

        String sql = "UPDATE usernotice SET n_u_title = ?, n_u_content = ?, n_u_date = NOW()";
        List<Object> args = new ArrayList<>();

        args.add(userNoticeVO.getN_u_title());
        args.add(userNoticeVO.getN_u_content());

        if (userNoticeVO.getN_u_image() != null) {
            sql += ", n_u_image = ?";
            args.add(userNoticeVO.getN_u_image());
        }

        sql += " WHERE n_u_no = ?";
        args.add(userNoticeVO.getN_u_no());

        int result = -1;

        try {
            result = jdbcTemplate.update(sql, args.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    //삭제
    public int deleteNotice(int n_u_no) {
    	String sql = "DELETE FROM usernotice "
    			+ "WHERE n_u_no = ?";
    	
    	int result = -1;
    	
    	try {
    		result = jdbcTemplate.update(sql, n_u_no);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }

}
