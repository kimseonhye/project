package com.kh.rightroom.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.UserListVO;
import com.kh.rightroom.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Transactional
	public String registerUser(UserVO userVO) {
		System.out.println("[UserService] registerUser()");

		try {

			int result = userDAO.registerUser(userVO);

			if (result > 0) {
				System.out.println("개인 회원가입이 완료되었습니다.");
				return "개인 회원가입이 완료되었습니다.";
			} else {
				System.out.println("개인 회원가입 중 오류가 발생하였습니다.");
				return "개인 회원가입 중 오류가 발생하였습니다.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "개인 회원가입 중 오류가 발생하였습니다.";
		}
	}

	// ID 중복 확인
	public boolean isUserIdUnique(String user_id) {
		// UserDAO를 통해 아이디 중복 확인을 수행
		return userDAO.isUserIdUnique(user_id);
	}

	// 사용자의 이메일로 아이디 찾기 서비스
	public String findUserId(String user_email) {
		System.out.println("[UserService] findUserId()");
		return userDAO.findUserId(user_email);
	}

	// 사용자의 아이디, 이메일로 비밀번호 찾기 서비스
	public String findUserPassword(String user_id, String user_email) {
		System.out.println("[UserService] findUserPassword()");
		return userDAO.findUserPassword(user_id, user_email);

	}

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public Map login(String userid, String userPwd) throws Exception {
		System.out.println("[UserService] login()");
		// TODO Auto-generated method stub
		Map user = userDAO.userLogin(userid, userPwd);
		return user;
	}

	public int userModify(UserVO userVO, HttpSession session) throws Exception {
		System.out.println("[UserService] userModify()");
		System.out.println("@@@@@@userVO : " + userVO);
		int retVal = userDAO.userModify(userVO);
		if (retVal > 0) {
			Map user = userDAO.getUser(userVO.getUser_id());

			session.setAttribute("userVO", user);
		}
		return retVal;
	}

	public int userDel(UserVO userVO, HttpSession session) throws Exception {
		System.out.println("[UserService] userDel()");
		System.out.println("@@@@@@userVO : " + userVO);
		int retVal = userDAO.userDel(userVO);
		if (retVal > 0) {
			session.setAttribute("userVO", null);
			session.removeAttribute("userVO");
		}
		return retVal;
	}

	public UserVO FindLoginUser(String userId) {
		return userDAO.FindLoginUser(userId);
	}

	public List<UserListVO> UserReserveList(UserVO uservo) {
		return userDAO.selectUserBooking(uservo);
	}

	public List<ReviewVO> userReviewList(UserVO uservo) {
		return userDAO.userReviewList(uservo);
	}

	public int reviewSubmit(BookingVO bookingVo, ReviewVO reviewvo) {
		int room_no = userDAO.findRoomNo(bookingVo);

		return userDAO.reviewSubmit(bookingVo, room_no, reviewvo);
	}

	public int findRoomNo(BookingVO bookingVo) {
		return userDAO.findRoomNo(bookingVo);
	}

	public int deleteReview(ReviewVO reviewVo) {
		return userDAO.deleteReview(reviewVo);
	}
	
	public BookingVO selectReserve(BookingVO bookingVo) {
		return userDAO.selectReserve(bookingVo);
	}
}