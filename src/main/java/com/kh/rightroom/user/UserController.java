package com.kh.rightroom.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.rightroom.vo.BookingVO;
import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.UserListVO;
import com.kh.rightroom.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	UserBookingService userBookingService;

	@Autowired
	UserRoomBookingService userRoomBookingService;

	@Autowired
	UserReserveConfirmService userReserveConfirmService;

	// 중복 아이디 확인 여부를 저장하는 변수
	private boolean isUserIdUnique = false;

//	// 회원 등록 페이지로 이동
//	@RequestMapping(value = "/userJoinPage", method = RequestMethod.GET)
//	public String showRegistrationForm() {
//		System.out.println("여긴 되나");
//		return "user/userJoinPage"; // 회원 등록 페이지의 뷰 이름
//	}

	// 회원 등록 처리
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw,
			@RequestParam("user_name") String user_name, @RequestParam("user_phone") String user_phone,
			@RequestParam("user_email") String user_email, Model model) {

		UserVO userVO = new UserVO();
		userVO.setUser_id(user_id);
		userVO.setUser_pw(user_pw);
		userVO.setUser_name(user_name);
		userVO.setUser_phone(user_phone);
		userVO.setUser_email(user_email);

		// user_rank 값을 '1'로 설정
		userVO.setUser_rank(1);

		String message = userService.registerUser(userVO);

		if ("개인 회원가입 중 오류가 발생하였습니다.".equals(message)) {
			// 가입 실패 시 콘솔에 메시지 출력
			System.out.println("회원 가입 실패: " + message);
		}

		// 결과 메시지를 모델에 추가하여 회원 가입 결과를 뷰에 전달
		model.addAttribute("message", message);

		// 회원 가입 성공 시 loginPage.jsp로 리다이렉트
		return "redirect:/joinSuccess";
	}

	// ID 중복 확인
	@ResponseBody
	@PostMapping("/checkDuplicateUserId")
	public String checkDuplicateUserId(@RequestParam("user_id") String user_id) {
		// UserService를 통해 아이디 중복 확인을 수행
		boolean isUnique = userService.isUserIdUnique(user_id);

		if (isUnique) {
			return "true";
		} else {
			return "false";
		}
	}

	// 아이디 찾기
	@ResponseBody
	@PostMapping("/findUserId")
	public String findUserId(@RequestParam("user_email") String user_email) {
		System.out.println("[UserController] findUserId()");
		// userService를 사용하여 이메일을 기반으로 사용자의 아이디를 찾습니다
		String user_id = userService.findUserId(user_email);
		System.out.println(user_id);
		return user_id != null ? user_id : "";
	}

	// 비밀번호 찾기
	@ResponseBody
	@PostMapping("/findUserPassword")
	public String findUserPassword(@RequestParam("user_id") String user_id,
			@RequestParam("user_email") String user_email) {
		// userService를 사용하여 아이디와 이메일을 기반으로 비밀번호를 찾습니다
		String new_password = userService.findUserPassword(user_id, user_email);
		return new_password != null ? new_password : "";
	}

	@RequestMapping(value = "/loginPage")
	public void loginGET() {
		logger.info("로그인 페이지 진입");
	}

	@RequestMapping(value = "/loginProc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginProc(HttpServletRequest request, String userid, String pwd) throws Exception {
		System.out.println("[UserController] loginProc()");
		HashMap<String, String> result = new HashMap<String, String>();
		String resultCode = "";
		String resultMessage = "";

		try {
			logger.info("userIdChk() 진입");
			Map userVO = userService.login(userid, pwd);
			logger.info("결과값 = " + userVO);

			if (userVO != null && !StringUtils.isEmpty(userVO.get("user_id"))) {
				HttpSession session = request.getSession();
				session.setAttribute("userVO", userVO);

				resultCode = "0000";
				resultMessage = "로그인에 성공하였습니다.";
//             return "redirect:/";
			} else {
				resultCode = "9999";
				resultMessage = "로그인에 실패하였습니다.";

			}
		} catch (Exception ex) {
			resultCode = "9999";
			resultMessage = ex.getMessage();
		}

		result.put("resultCode", resultCode);
		result.put("resultMessage", resultMessage);
		return result;
	}

	@GetMapping("/userMyInfo")
	public String userMyInfo(HttpSession session) {
		return "/user/userMyInfo";
	}

	@GetMapping("/userMyPage")
	public String userMyPage() {
		return "/user/userMyPage";
	}

	// 회원수정
	@RequestMapping(value = "/userModifyProc", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> userModifyProc(HttpSession session, UserVO userVO) {
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			logger.info("join 진입");

			// 회원가입 서비스 실행
			userService.userModify(userVO, session);
			logger.info("join service 성공");

			result.put("resultCode", "0000");
		} catch (Exception ex) {
			result.put("resultCode", "9999");
			result.put("resultMessage", ex.getMessage());
		}

		// return "redirect:/index";
		return result;
	}

	@RequestMapping(value = "/userDelProc", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> userDelProc(HttpSession session, UserVO userVO) {
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			logger.info("회원탈퇴 진입");

			// 사용자의 로그인 상태 확인
			if (userVO == null) {
				result.put("resultCode", "9999");
				result.put("resultMessage", "로그인한 사용자만 탈퇴할 수 있습니다.");
			} else {
				// 탈퇴 서비스 실행 (사용자 계정을 비활성화 또는 삭제)
				userService.userDel(userVO, session);
				logger.info("회원탈퇴 서비스 성공");

				// 세션 로그아웃
				session.invalidate();

				result.put("resultCode", "0000");
			}
		} catch (Exception ex) {
			result.put("resultCode", "9999");
			result.put("resultMessage", ex.getMessage());
		}

		return result;
	}

	@GetMapping("/userReserveList")
	public String userReserveList(HttpSession session, Model model) {
		String nextPage = "user/userReserveList";

		Map userVo = (Map) session.getAttribute("userVO");
		String userId = (String) userVo.get("user_id");

		UserVO loginUser = userService.FindLoginUser(userId);

		List<UserListVO> userListVos = userService.UserReserveList(loginUser);

		model.addAttribute("userListVos", userListVos);

		return nextPage;
	}

	@GetMapping("/userReviewList") // 리뷰 리스트
	public String userReviewList(HttpSession session, Model model) {

		Map userVo = (Map) session.getAttribute("userVO");
		String userId = (String) userVo.get("user_id");

		UserVO loginUser = userService.FindLoginUser(userId);

		List<ReviewVO> ReviewVos = userService.userReviewList(loginUser);

		model.addAttribute("ReviewVos", ReviewVos);
		String nextPage = "user/userReviewList";

		return nextPage;
	}

	@GetMapping("/writeReview") // 리뷰 작성 페이지 이동
	public String writeReview(BookingVO bookingVo, Model model) {
		model.addAttribute("item", bookingVo);

		return "user/writeReview";
	}

	@PostMapping("/reviewSubmit")
	public String reviewSubmit(HttpSession session, BookingVO bookingVo, ReviewVO reviewvo, Model model) {

		userService.reviewSubmit(bookingVo, reviewvo);

		String nextPage = userReviewList(session, model);

		return nextPage;
	}

	// 이하 리뷰 저장 및 기타 로직
	@GetMapping("/reviewDelete")
	public String reviewDelete(HttpSession session, ReviewVO reviewVo, Model model) {

		userService.deleteReview(reviewVo);
		String nextPage = userReviewList(session, model);

		return nextPage;
	}
	
	@GetMapping("/selectReserve")
	public String selectReserve(BookingVO bookingVO, Model model) {

		bookingVO = userService.selectReserve(bookingVO);
		model.addAttribute("bookingVO", bookingVO);
		String nextPage = "user/userReservation";

		return nextPage;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 예약서 작성
	@GetMapping("/userBookingPage")
	public String userBookingPage(@RequestParam("room_no") int room_no, Model model) {
		System.out.println("room_no :" + room_no);

		RoomVO roomVO = userBookingService.userBookingPage(room_no);

		model.addAttribute("roomVO", roomVO);

		return "user/userBookingPage";
	}

	@GetMapping("/userReserveConfirm")
	public String userReserveConfirm() {
		return "user/userReserveConfirm";
	}

	// 예약서 확인
	@PostMapping("/userReserveConfirm")
	public String userReserveConfirm(HttpSession session, BookingVO bookingVo, @RequestParam("room_no") String room_no, Model model) {

		System.out.println("[userReserveConfirm] 데이터 조회" + room_no);

		Map userVo = (Map) session.getAttribute("userVO");
		String userId = (String) userVo.get("user_id");

		UserVO loginUser = userService.FindLoginUser(userId);
		
		int roomNo = Integer.parseInt(room_no);
		userBookingService.reserveConfirm(loginUser, roomNo, bookingVo);
		
		RoomVO roomVO = userReserveConfirmService.selectReserveRoom(roomNo);

		model.addAttribute("bookingVO", bookingVo);
		model.addAttribute("roomVO", roomVO);

		return "user/userReserveConfirm";
	}

}
