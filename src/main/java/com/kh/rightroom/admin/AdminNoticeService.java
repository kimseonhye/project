package com.kh.rightroom.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.UserNoticeVO;

@Service
public class AdminNoticeService {
	
	final static public int NOTICE_ALREADY_EXIST = 0;
	final static public int NOTICE_REGISTER_SUCCESS = 1;
	final static public int NOTICE_REGISTER_FAIL = -1;
	
	@Autowired
	AdminNoticeDAO adminNoticeDAO;
	
	public int registerNoticeConfirm(UserNoticeVO userNoticeVO) {
		boolean isTITLE = adminNoticeDAO.isTITLE(userNoticeVO.getN_u_title());
		
		if (!isTITLE) {
			int result = adminNoticeDAO.insertNotice(userNoticeVO);
			
			if (result > 0) {
				return NOTICE_REGISTER_SUCCESS;
			} else {
				return NOTICE_REGISTER_FAIL;
			}
		} else {
			return NOTICE_ALREADY_EXIST;
		}
	}

    public List<UserNoticeVO> getAllNotices() {
        // 데이터베이스에서 모든 공지사항을 조회하는 메서드를 AdminNoticeDAO에서 호출합니다.
        return adminNoticeDAO.getAllNotices();
    }
    
    public List<UserNoticeVO> searchNoticeConfirm(UserNoticeVO userNoticeVO) {
    	return adminNoticeDAO.selectNoticesBySearch(userNoticeVO);
    }

	public UserNoticeVO modifyNoticeForm(int n_u_no) {		
		return adminNoticeDAO.selectNotice(n_u_no);
	}
	
	public int modifyNoticeConfirm(UserNoticeVO userNoticeVO) {
		return adminNoticeDAO.updateNotice(userNoticeVO);
	}
	
	public int deleteNoticeConfirm(int n_u_no) {
		return adminNoticeDAO.deleteNotice(n_u_no);
	}
	
}
        
	
	
