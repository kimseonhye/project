package com.kh.rightroom.company;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.rightroom.vo.ReservationListVO;
import com.kh.rightroom.vo.RoomDetailVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.UserVO;

@Service
public class CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	@Transactional
	public String registerUser(UserVO userVO) {
		System.out.println("[CompanyService] registerUser()");

		try {

			int result = companyDAO.registerUser(userVO);

			if (result > 0) {
				System.out.println("기업 회원가입이 완료되었습니다.");
				return "기업 회원가입이 완료되었습니다.";
			} else {
				System.out.println("기업 회원가입 중 오류가 발생하였습니다.");
				return "기업 회원가입 중 오류가 발생하였습니다.";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "기업 회원가입 중 오류가 발생하였습니다.";
		}
	}

	// ID 중복 확인
	public boolean isUserIdUnique(String user_id) {
		// UserDAO를 통해 아이디 중복 확인을 수행
		return companyDAO.isUserIdUnique(user_id);
	}

	public List<RoomVO> getConfirmPlace() {
		System.out.println("[CompanyService] - getAllPlace() - called");
		return companyDAO.selectComfirmPlaces();
	}

	public List<RoomVO> getWaitPlace() {
		System.out.println("[CompanyService] - getWaitPlace() - called");
		return companyDAO.selectWaitPlaces();
	}

	public List<ReservationListVO> getAllList() {
		System.out.println("[CompanyService] - getAllList() - called");
		return companyDAO.selectAllList();
	}

	public List<ReservationListVO> deleteList(int number) {
		System.out.println("[CompanyService] - deleteList() - called");
		return companyDAO.deleteList(number);
	}

	public List<ReservationListVO> listShowInfo(int number) {
		System.out.println("[CompanyService] - listShowInfo() - called");
		return companyDAO.listShowInfo(number);
	}

	public RoomDetailVO getRoomDetail(int roomNo) {
		return companyDAO.findRoomDetail(roomNo);
	}

	@Transactional
	public boolean updateRoomDetail(int roomNo, int tSetNo, int userNo, RoomDetailVO updatedRoomDetail) {
		// 로직의 복잡도나 필요에 따라 여기에 추가적인 비즈니스 로직을 추가할 수 있습니다.
		return companyDAO.updateRoomDetail(roomNo, tSetNo, userNo, updatedRoomDetail);
	}

	public void deleteRoom(int roomNo) throws Exception {
		companyDAO.deleteRoom(roomNo);
	}

	public List<RoomDetailVO> getAllMyPlaces(int userNo) {
		return companyDAO.findAllMyPlaces(userNo);
	}

	public void UserService(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public int userModify(UserVO userVO, HttpSession session) throws Exception {
		System.out.println("[UserService] userModify()");
		System.out.println("@@@@@@userVO : " + userVO);
		int retVal = companyDAO.userModify(userVO);
		if (retVal > 0) {
			Map user = companyDAO.getUser(userVO.getUser_id());

			session.setAttribute("userVO", user);
		}
		return retVal;
	}

	public int userDel(UserVO userVO, HttpSession session) throws Exception {
		System.out.println("[UserService] userDel()");
		System.out.println("@@@@@@userVO : " + userVO);
		int retVal = companyDAO.userDel(userVO);
		if (retVal > 0) {
			session.setAttribute("userVO", null);
			session.removeAttribute("userVO");
		}
		return retVal;
	}

}
