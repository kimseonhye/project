package com.kh.rightroom.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.UserFAQVO;

@Service
public class AdminFAQService {
	
	final static public int NOTICE_ALREADY_EXIST = 0;
	final static public int NOTICE_REGISTER_SUCCESS = 1;
	final static public int NOTICE_REGISTER_FAIL = -1;
	
	@Autowired
	AdminFAQDAO adminFAQDAO;
	
	public int registerFAQConfirm(UserFAQVO userFAQVO) {
		boolean isTITLE = adminFAQDAO.isTITLE(userFAQVO.getFaq_u_title());
		
		if (!isTITLE) {
			int result = adminFAQDAO.insertFAQ(userFAQVO);
			
			if (result > 0) {
				return NOTICE_REGISTER_SUCCESS;
			} else {
				return NOTICE_REGISTER_FAIL;
			}
		} else {
			return NOTICE_ALREADY_EXIST;
		}
	}

    public List<UserFAQVO> getAllFAQs() {

        return adminFAQDAO.getAllFAQs();
    }

	public UserFAQVO modifyFAQForm(int faq_u_no) {
		return adminFAQDAO.selectFAQ(faq_u_no);
	}
	
	public int modifyFAQConfirm(UserFAQVO userFAQVO) {
		return adminFAQDAO.updateFAQ(userFAQVO);
	}
	
	public int deleteFAQConfirm(int faq_u_no) {
		return adminFAQDAO.deleteFAQ(faq_u_no);
	}

}
