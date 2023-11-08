package com.kh.rightroom.admin;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kh.rightroom.vo.CompanyRoomVO;
import com.kh.rightroom.vo.RoomReviewVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserBookingVO;
import com.kh.rightroom.vo.UserVO;

@Service
public class AdminService {

	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	@Autowired
	AdminDAO adminDao;

	// adminUserManagementPage
	public List<UserVO> selectAllUser() {
		System.out.println("[AdminService] selectAllUser()");
		return adminDao.selectAllUser();
	}

	public List<UserVO> selectUser() {
		System.out.println("[AdminService] selectUser()");
		return adminDao.selectUser();
	}

	public List<UserVO> selectUserId(UserVO userVo) {
		System.out.println("[AdminService] selectUserId()");
		return adminDao.selectUserId(userVo);
	}

	public List<UserVO> selectCompanyUser() {
		System.out.println("[AdminService] selectCompanyUser()");
		return adminDao.selectCompanyUser();
	}

	public UserVO selectOneUser(UserVO userVo) {
		System.out.println("[AdminService] selectOneUser()");
		return adminDao.selectOneUser(userVo);
	}

	public UserVO selectOneUserToID(UserVO userVo) {
		System.out.println("[AdminService] selectOneUserToID()");
		return adminDao.selectOneUserToID(userVo);
	}

	public int deleteUser(UserVO userVo) {
		System.out.println("[AdminService] deleteUser()");
		return adminDao.deleteUser(userVo);
	}

	public int deleteReview(UserBookingVO userBookingVo) {
		System.out.println("[AdminService] deleteReview()");
		return adminDao.deleteReview(userBookingVo);
	}

	public List<UserBookingVO> selectUserBooking(UserVO userVo) {
		System.out.println("[AdminService] selectUserBooking()");
		return adminDao.selectUserBooking(userVo);
	}

	public List<UserBookingVO> selectCompanyBooking(UserVO userVo) {
		System.out.println("[AdminService] selectCompanyBooking()");
		return adminDao.selectCompanyBooking(userVo);
	}

	public List<CompanyRoomVO> selectCompanyUserRoom(UserVO userVo) {
		System.out.println("[AdminService] selectCompanyUserRoom()");
		return adminDao.selectCompanyUserRoom(userVo);
	}

	public List<CompanyRoomVO> selectRoomStatus1() {
		System.out.println("[AdminService] selectRoomStatus1()");
		return adminDao.selectRoomStatus1();
	}

	public List<CompanyRoomVO> selectRoomStatus2() {
		System.out.println("[AdminService] selectRoomStatus2()");
		return adminDao.selectRoomStatus2();
	}

	public List<CompanyRoomVO> selectRoomStatus3() {
		System.out.println("[AdminService] selectRoomStatus3()");
		return adminDao.selectRoomStatus3();
	}

	public List<CompanyRoomVO> roomSearch1(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomSearch1()");
		return adminDao.roomSearch1(roomVo);
	}

	public List<CompanyRoomVO> roomSearch2(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomSearch2()");
		return adminDao.roomSearch2(roomVo);
	}

	public List<CompanyRoomVO> roomSearch3(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomSearch3()");
		return adminDao.roomSearch3(roomVo);
	}

	public List<CompanyRoomVO> roomSelect1Status1() {
		System.out.println("[AdminService] roomSelect1Status1()");
		return adminDao.roomSelect1Status1();
	}

	public List<CompanyRoomVO> roomSelect2Status1() {
		System.out.println("[AdminService] roomSelect2Status1()");
		return adminDao.roomSelect2Status1();
	}

	public List<CompanyRoomVO> roomSelect3Status1() {
		System.out.println("[AdminService] roomSelect3Status1()");
		return adminDao.roomSelect3Status1();
	}

	public List<CompanyRoomVO> roomSelect1Status2() {
		System.out.println("[AdminService] roomSelect1Status2()");
		return adminDao.roomSelect1Status2();
	}

	public List<CompanyRoomVO> roomSelect2Status2() {
		System.out.println("[AdminService] roomSelect2Status2()");
		return adminDao.roomSelect2Status2();
	}

	public List<CompanyRoomVO> roomSelect3Status2() {
		System.out.println("[AdminService] roomSelect3Status2()");
		return adminDao.roomSelect3Status2();
	}

	public List<CompanyRoomVO> roomSelect1Status3() {
		System.out.println("[AdminService] roomSelect1Status3()");
		return adminDao.roomSelect1Status3();
	}

	public List<CompanyRoomVO> roomSelect2Status3() {
		System.out.println("[AdminService] roomSelect2Status3()");
		return adminDao.roomSelect2Status3();
	}

	public List<CompanyRoomVO> roomSelect3Status3() {
		System.out.println("[AdminService] roomSelect3Status3()");
		return adminDao.roomSelect3Status3();
	}

	public int roomApproval(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomApproval()");
		return adminDao.roomApproval(roomVo);
	}

	public int roomCancle(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomCancle()");
		return adminDao.roomCancle(roomVo);
	}

	public int roomDelete(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomDelete()");
		adminDao.deleteReviewFromRoom(roomVo);
		return adminDao.roomDelete(roomVo);
	}

	public void roomCancleEmailing(String cancleReason, CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomCancleEmailing()");
		roomVo = adminDao.roomSearch(roomVo);
		String CompanyName = roomVo.getUser_name();
		String RoomName = roomVo.getRoom_name();
		String toMailAddr = roomVo.getUser_email();

		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo("hshshs1414@naver.com");
				// mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[RightRoom] 장소 반려 사유 안내");

				String firstContent = "안녕하세요. RightRoom입니다.<br>" + CompanyName + " 회원님의 장소가 반려되었음을 안내드립니다.";
				String secondContent = RoomName + " 반려 사유 : " + cancleReason;
				String combinedContent = firstContent + "<br><br>" + secondContent;
				mimeMessageHelper.setText(combinedContent, true);

			}

		};
		javaMailSenderImpl.send(mimeMessagePreparator);
	}

	public void roomDeleteEmailing(String deleteReason, CompanyRoomVO roomVo) {
		System.out.println("[AdminService] roomDeleteEmailing()");
		roomVo = adminDao.roomSearch(roomVo);
		String CompanyName = roomVo.getUser_name();
		String RoomName = roomVo.getRoom_name();
		String toMailAddr = roomVo.getUser_email();

		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				mimeMessageHelper.setTo("hshshs1414@naver.com");
				// mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[RightRoom] 장소 삭제 사유 안내");

				String firstContent = "안녕하세요. RightRoom입니다.<br>" + CompanyName + " 회원님의 장소가 삭제되었음을 안내드립니다.";
				String secondContent = RoomName + " 삭제 사유 : " + deleteReason;
				String combinedContent = firstContent + "<br><br>" + secondContent;
				mimeMessageHelper.setText(combinedContent, true);

			}

		};
		javaMailSenderImpl.send(mimeMessagePreparator);
	}

	public CompanyRoomVO adminCompanyPlaceConfirm(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] adminCompanyPlaceConfirm()");
		roomVo = adminDao.roomSearch(roomVo);
		return roomVo;
	}

	public List<RoomReviewVO> CompanyPlaceReviewlist(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] CompanyPlaceReviewlist()");
		List<RoomReviewVO> reviewVos = adminDao.CompanyPlaceReviewlist(roomVo);
		return reviewVos;
	}

	public TableSetVO selectRoomTableSet(CompanyRoomVO roomVo) {
		System.out.println("[AdminService] selectRoomTableSet()");
		TableSetVO tableSetVo = adminDao.selectRoomTableSet(roomVo);
		return tableSetVo;
	}

	public UserVO selectUsertoReview(UserBookingVO userBookingVo) {
		System.out.println("[AdminService] selectUsertoReview()");
		UserVO Uservo = adminDao.selectUsertoReview(userBookingVo);
		return Uservo;
	}

}
