package com.kh.rightroom;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.rightroom.company.CompanyService;
import com.kh.rightroom.vo.ReservationListVO;
import com.kh.rightroom.vo.RoomVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	CompanyService companyService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	@GetMapping("/loginPage")
	public String login() {
		return "login/loginPage";
	}

	@GetMapping("/signUp")
	public String signUp() {
		return "login/signUp";
	}

	@GetMapping("/userJoinPage")
	public String userJoinPage() {
		return "user/userJoinPage";
	}

	@GetMapping("/companyJoinPage")
	public String companyJoinPage() {
		return "company/companyJoinPage";
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}

	@GetMapping("/searchPlace")
	public String searchPlace() {
		return "searchPlace";
	}

	// 회원가입 성공 페이지
	@GetMapping("/joinSuccess")
	public String joinSuccess() {
		return "joinSuccess";
	}

	// 장소 조회 페이지
	@GetMapping("/userRoomList")
	public String userRoomList() {
		return "room/userRoomList";
	}

	// 장소 상세 페이지
	@GetMapping("/userRoomDetail")
	public String userRoomDetailHome(@RequestParam("room_no") String room_no) {
		return "redirect:/room/userRoomDetail?room_no=" + room_no;
	}

	// 사용자 이용내역
	public String userReserveList() {
		return "user/userReserveList";
	}

	// 업체 마이페이지(여기서부터는 나한테 해당하는 부분 직접적으로 구현했음) - 설원

	@GetMapping("/companyMyPage") // http://localhost:8090/khkh/companyMyPage 입력하면 이동가능
	public String companyMyPage() {
		return "company/companyMyPage";
	}

	// 업체정보

	@GetMapping("/companyMyInfo")
	public String companyMyInfo() {
		return "company/companyMyInfo";
	}

	// 장소등록하기

	@GetMapping("/companyRoomRegisterForm")
	public String companyRoomRegisterForm() {
		return "company/companyRoomRegisterForm";
	}

	// 업체 측 내 장소 관리페이지
	@GetMapping("/companyMyPlace")
	public String companyMyPlace(Model model1, Model model2) {
		String nextPage = "company/companyMyPlace";

		// 승인 처리된 장소를 불러오기 위한 매서드
		System.out.println("[HomeController] - getConfirmPlace() - called");
		List<RoomVO> confirmRooms = companyService.getConfirmPlace();
		model1.addAttribute("confirmRooms", confirmRooms);

		// 승인 대기중인 장소를 불러오기 위한 매서드
		System.out.println("[HomeController] - getAWaitPlace() - called");
		List<RoomVO> waitRooms = companyService.getWaitPlace();
		model2.addAttribute("waitRooms", waitRooms);

		return nextPage;
	}

	// 업체 측 예약관리
	@GetMapping("/companyReservationList")
	public String companyReservationList(Model model) {
		String nextPage = "company/companyReservationList";

		System.out.println("[HomeController] - companyReservationList() - called");
		List<ReservationListVO> bookingList = companyService.getAllList();
		model.addAttribute("List", bookingList);

		return nextPage;
	}

	// 업체 측 예약 리스트 삭제
	@GetMapping("/companyReservationList/delete/{booking_no}")
	public String listDelete(@PathVariable("booking_no") int id) {
		System.out.println("[HomeController] - listDelete() - called");

		companyService.deleteList(id);

		// redirect 로 /companyReservationList 를 재요청 - 즉 /companyReservationList 으로 이동
		return "redirect:/companyReservationList";
	}

	// 업체 측 예약관리에서 상세 예약서 조회
	@GetMapping("/companyReservationList/select/{booking_no}")
	public String listShowInfo(Model model, @PathVariable("booking_no") int id) {
		System.out.println("[HomeController] - listShowInfo() - called");
		String nextPage = "company/companyReserveForm";

		List<ReservationListVO> reserveInfo = companyService.listShowInfo(id);
		model.addAttribute("Info", reserveInfo);

		return nextPage;
	}

	// 업체 측 예약서 조회 페이지
	@GetMapping("/companyReserveForm")
	public String companyReserveForm() {
		String nextPage = "company/companyReserveForm";

		System.out.println("[HomeController] - companyReserveForm() - called");

		return nextPage;
	}

}