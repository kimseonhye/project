package com.kh.rightroom.company;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.rightroom.user.UserService;
import com.kh.rightroom.vo.RoomDetailVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.UserVO;

@Controller
@RequestMapping("/company")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	// 중복 아이디 확인 여부를 저장하는 변수
	private boolean isUserIdUnique = false;

	// 회원 등록 페이지로 이동
	@RequestMapping(value = "/companyMyPage", method = RequestMethod.GET)
	public String companyMyPage() {
		return "company/companyMyPage"; // 회원 등록 페이지의 뷰 이름
	}

	// 회원 등록 페이지로 이동
	@RequestMapping(value = "/companyJoinPage", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "company/companyJoinPage"; // 회원 등록 페이지의 뷰 이름
	}

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

		// user_rank 값을 '2'로 설정
		userVO.setUser_rank(2);

		String message = companyService.registerUser(userVO);

		if ("기업 회원가입 중 오류가 발생하였습니다.".equals(message)) {
			// 가입 실패 시 콘솔에 메시지 출력
			System.out.println("회원 가입 실패: " + message);
		}

		// 결과 메시지를 모델에 추가하여 회원 가입 결과를 뷰에 전달
		model.addAttribute("message", message);

		// 회원 가입 성공 시 loginPage.jsp로 리다이렉트
		return "redirect:/joinSuccess"; // loginPage.jsp로 리다이렉트
	}

	// ID 중복 확인
	@ResponseBody
	@PostMapping("/checkDuplicateUserId")
	public String checkDuplicateUserId(@RequestParam("user_id") String user_id) {
		// UserService를 통해 아이디 중복 확인을 수행
		boolean isUnique = companyService.isUserIdUnique(user_id);

		if (isUnique) {
			return "true";
		} else {
			return "false";
		}
	}

	@GetMapping("/companyMyPlace")
	public String companyMyPlace(Model model, Model model2, HttpSession session, HttpServletRequest request) {
		System.out.println("[룸 컨트롤러]내 장소 목록 이동 매핑 작동중!!");

		// 로그인한 사용자의 ID를 얻는 코드가 여기에 필요합니다. 예를 들어:
		// Integer userNo = getLoggedInUserId(request); // 이 메서드는 현재 로그인한 사용자의 ID를 반환해야
		// 합니다.
		Map userVo = (Map) session.getAttribute("userVO");
		String userId = (String) userVo.get("user_id");

		UserVO loginUser = userService.FindLoginUser(userId);

		// 임시로 userNo를 1로 설정합니다.
		Integer userNo = loginUser.getUser_no();

		// 이제 userNo를 이용해서 이 사용자가 등록한 모든 방을 조회합니다.
		List<RoomDetailVO> myPlaces = companyService.getAllMyPlaces(userNo);

		// 이 방 목록을 모델에 추가해서 뷰에 전달합니다.
		model.addAttribute("myPlaces", myPlaces);

		// 석영 승인 대기중인 장소를 불러오기 위한 매서드
		System.out.println("[CompanyController] - getAWaitPlace() - called");
		List<RoomVO> waitRooms = companyService.getWaitPlace();
		model2.addAttribute("waitRooms", waitRooms);

		return "company/companyMyPlace";
	}

	// room_no기준으로 장소상세페이지 조회 이동
	@GetMapping("/companyRoomDetail/{room_no}")
	public String companyRoomDetail(@PathVariable int room_no, Model model) {
		System.out.println("[룸 컨트롤러] companyRoomDetail 이동 매핑 작동중!!");

		RoomDetailVO roomDetail = companyService.getRoomDetail(room_no);

		model.addAttribute("roomDetail", roomDetail);

		return "company/companyRoomDetailPageModify";
	}

	// room_no기준으로 장소수정페이지 이동
	@GetMapping("/companyRoomModify/{room_no}")
	public ModelAndView companyRoomModify(@PathVariable("room_no") int roomNo) {
		System.out.println("[룸 컨트롤러] companyRoomModify 이동 GET매핑 작동중!!");
		ModelAndView mav = new ModelAndView("company/companyRoomModify");

		// 예를 들어, RoomDetailVO 객체를 가져와서 페이지에 전달하고자 하는 경우
		RoomDetailVO roomDetail = companyService.getRoomDetail(roomNo);

		// 모델에 데이터 추가
		mav.addObject("roomDetail", roomDetail);

		// 뷰 이름 설정 및 모델과 함께 반환
		return mav;
	}

	// 사진 빼고 나머지 데이터들을 수정하기 위한 컨트롤러
	@RequestMapping(value = "/companyRoomModify/{room_no}", method = RequestMethod.POST)
	public String updateRoomDetail(@PathVariable String room_no, @ModelAttribute RoomDetailVO updatedRoomDetail,
			RedirectAttributes redirectAttributes) {
		System.out.println("[룸 컨트롤러] companyRoomModify 이동 POST매핑 작동중!!");
		System.out.println("Received room_no: " + room_no);
		try {
			int roomNo = Integer.parseInt(room_no);
			boolean isUpdated = companyService.updateRoomDetail(roomNo, roomNo, roomNo, updatedRoomDetail);

			redirectAttributes.addFlashAttribute("message", "내 장소가 성공적으로 수정되었습니다.");
			return "redirect:../companyRoomDetail/" + roomNo;

		} catch (NumberFormatException e) {
			return "company/companyMyPlace";
		}
	}

	// 룸 삭제하는 메서드
	@PostMapping("/deleteCompanyRoom")
	public String deleteRoom(@RequestParam int roomNo, RedirectAttributes redirectAttributes) {
		try {
			companyService.deleteRoom(roomNo);
			redirectAttributes.addFlashAttribute("successMessage", "룸이 성공적으로 삭제되었습니다!");
			return "redirect:/company/companyMyPlace";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the room!");
			return "redirect:/company/roomDetail?roomNo=" + roomNo;
		}
	}

	@GetMapping("/companyMyInfo")
	public String userMyInfo(HttpSession session) {
		return "/company/companyMyInfo";
	}

	@RequestMapping(value = "/userModifyProc", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, String> userModifyProc(HttpSession session, UserVO userVO) {
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			logger.info("join 진입");

			userService.userModify(userVO, session);
			logger.info("join service 성공");

			result.put("resultCode", "0000");
		} catch (Exception ex) {
			result.put("resultCode", "9999");
			result.put("resultMessage", ex.getMessage());
		}

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
}
