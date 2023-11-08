package com.kh.rightroom.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.rightroom.vo.UserFAQVO;

@Component
public class AdminFAQDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public boolean isTITLE(String faq_u_title) {
		String sql = "SELECT COUNT(*) FROM userfaq WHERE faq_u_title = ?";
		
		int result = jdbcTemplate.queryForObject(sql, Integer.class, faq_u_title);
		
		return result > 0 ? true : false;
	}
	
	public int insertFAQ(UserFAQVO userFAQVO) {
		String sql = "INSERT INTO userfaq(faq_u_title, faq_u_content, faq_u_image, faq_u_date) "
				   + "VALUES(?,?,?,NOW())";
		
		int result = -1;
		
		try {
			result = jdbcTemplate.update(sql,
					userFAQVO.getFaq_u_title(),
					userFAQVO.getFaq_u_content(),
					userFAQVO.getFaq_u_image());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<UserFAQVO> getAllFAQs() {
        // 데이터베이스에서 모든 FAQ을 조회하는 SQL 쿼리를 작성합니다.
        String sql = "SELECT * FROM userfaq";

        // JdbcTemplate을 사용하여 데이터를 조회하고 List<UserFAQVO> 형태로 반환합니다.
        List<UserFAQVO> FAQs = jdbcTemplate.query(sql, new UserFAQRowMapper());

        return FAQs;
    }

    // UserFAQVO를 매핑하는 RowMapper 클래스를 작성합니다.
    private static class UserFAQRowMapper implements RowMapper<UserFAQVO> {
        @Override
        public UserFAQVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserFAQVO faq = new UserFAQVO();
            faq.setFaq_u_no(rs.getInt("faq_u_no"));
            faq.setFaq_u_title(rs.getString("faq_u_title"));
            faq.setFaq_u_content(rs.getString("faq_u_content"));
            faq.setFaq_u_image(rs.getString("faq_u_image"));
            faq.setFaq_u_date(rs.getDate("faq_u_date"));

            return faq;
        }
    }
        
        public UserFAQVO selectFAQ(int faq_u_no) {
        	String sql = "SELECT * FROM userfaq WHERE faq_u_no = ?";
        	
        	List<UserFAQVO> userFAQVOs = null;
        	
        	try {
        		userFAQVOs = jdbcTemplate.query(sql, new RowMapper<UserFAQVO>() {
        			
        			@Override
        			public UserFAQVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        				
        				UserFAQVO userFAQVO = new UserFAQVO();
        				
        				userFAQVO.setFaq_u_no(rs.getInt("faq_u_no"));
        				userFAQVO.setFaq_u_title(rs.getString("faq_u_title"));
        				userFAQVO.setFaq_u_content(rs.getString("faq_u_content"));
        				userFAQVO.setFaq_u_image(rs.getString("faq_u_image"));
        				userFAQVO.setFaq_u_date(rs.getDate("faq_u_date"));
        				
        				return userFAQVO;    				
        			}
        		}, faq_u_no);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	return userFAQVOs.size() > 0 ? userFAQVOs.get(0) : null;
        }
        
        
        //수정
        public int updateFAQ(UserFAQVO userFAQVO) {
            System.out.println("[AdminFAQDAO] FAQ 업데이트 테스트");

            String sql = "UPDATE userfaq SET faq_u_title = ?, faq_u_content = ?, faq_u_date = NOW()";
            List<Object> args = new ArrayList<>();

            args.add(userFAQVO.getFaq_u_title());
            args.add(userFAQVO.getFaq_u_content());

            if (userFAQVO.getFaq_u_image() != null) {
                sql += ", faq_u_image = ?";
                args.add(userFAQVO.getFaq_u_image());
            }

            sql += " WHERE faq_u_no = ?";
            args.add(userFAQVO.getFaq_u_no());

            int result = -1;

            try {
                result = jdbcTemplate.update(sql, args.toArray());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
      }
        
        //삭제
        public int deleteFAQ(int faq_u_no) {
        	String sql = "DELETE FROM userfaq "
        			+ "WHERE faq_u_no = ?";
        	
        	int result = -1;
        	
        	try {
        		result = jdbcTemplate.update(sql, faq_u_no);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	return result;
        }
  }



