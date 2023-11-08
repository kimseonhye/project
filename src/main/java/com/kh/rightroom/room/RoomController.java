package com.kh.rightroom.room;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.rightroom.room.util.UploadFileService;
import com.kh.rightroom.user.UserService;
import com.kh.rightroom.vo.ReviewVO;
import com.kh.rightroom.vo.RoomRegisterVO;
import com.kh.rightroom.vo.RoomVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserVO;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserService userService;

	@Autowired
	UploadFileService uploadFileService;

	// 장소 조건 검색
	// 검색 기능 처리
	@RequestMapping(value = "/searchPlace", method = RequestMethod.GET)
	public String searchRooms(@RequestParam("room_address") String room_address,
			@RequestParam("room_max_ppl") int room_max_ppl, @RequestParam("room_price") String room_price,
			Model model) {
		System.out.println("[RoomController] searchRooms()");

		List<RoomVO> rooms = roomService.searchRooms(room_address, room_max_ppl, room_price);

		model.addAttribute("rooms", rooms);
		System.out.println(room_address);

		return "room/userRoomList"; // 결과를 표시할 뷰 페이지의 이름 (userRoomList.jsp)
//        return "redirect:/userRoomList";
	}

	// 장소 상세페이지 조회
	@GetMapping("/userRoomDetail")
	public String userRoomDetail(@RequestParam("room_no") int room_no, Model model) {
		System.out.println("[RoomController] userRoomDetail()");

		RoomVO roomVO = roomService.userRoomDetail(room_no);
		// RoomService의 userRoomReviewList 메서드를 호출하여 리뷰 목록 조회
		List<ReviewVO> reviews = roomService.userRoomReviewList(room_no);
		// TableSet 조회
		TableSetVO tableSet = roomService.userRoomTableSet(room_no);

		model.addAttribute("roomVO", roomVO);
		model.addAttribute("reviews", reviews);
		model.addAttribute("tableSet", tableSet);

		return "room/userRoomDetail";
	}

	// 장소등록 폼 이동
	@GetMapping("/companyRoomRegisterForm")
	public String companyRoomRegisterForm(HttpSession session) {
		System.out.println("[룸 컨트롤러]업체 장소 등록 폼 작동중입니다!!!");

		return "company/companyRoomRegisterForm";
	}

	// 장소등록 이동
	@PostMapping("/companyRoomRegisterFormConfirm")
	public String companyRoomRegisterFormConfirm(HttpSession session, RoomRegisterVO roomRegisterVO,
			@RequestParam("room_images1") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
		try {
			System.out.println("[룸 컨트롤러]업체 장소 등록 확인 작동중입니다!!");

			// 로깅: roomRegisterVO 상태 확인
			System.out.println("roomRegisterVO: " + roomRegisterVO);

			Map userVo = (Map) session.getAttribute("userVO");
			String userId = (String) userVo.get("user_id");

			UserVO loginUser = userService.FindLoginUser(userId);

			// 지금 로그인 기능을 구현하지 않아서 user_no가 1인 상황을 가정하고 데이터를 추가해줬음!!!
			roomRegisterVO.setUser_no(loginUser.getUser_no());

			// 파일 저장 및 파일 경로 가져오기
			String savedFilePath = uploadFileService.upload(file);
			System.out.println("savedFilePath: " + savedFilePath);

			// 방의 존재 확인
			if (roomService.doesRoomExist(roomRegisterVO.getRoom_no())) {
				// 방이 이미 존재할 경우 사용자에게 메시지 전달
				redirectAttributes.addFlashAttribute("errorMsg", "방이 이미 존재합니다.");
				return "redirect:/";
			}

			// 파일 저장 검증
			if (savedFilePath != null) {
				// 업로드된 파일의 경로를 room_images 필드에 설정
				roomRegisterVO.setRoom_images(savedFilePath);

				// 장소 등록 로직 실행
				int result = roomService.registerRoom(roomRegisterVO);
				if (result > 0) {
					// 장소 등록 성공 시, 등록한 정보를 리다이렉트할 페이지로 전달
					session.setAttribute("roomRegisterVO", roomRegisterVO); // 세션에 저장
					model.addAttribute("roomVO", loginUser);
					return "redirect:./companyFinalSubmitPage";
				} else {
					// 장소 등록 실패 시 실패 메시지를 리다이렉트할 페이지로 전달
					redirectAttributes.addFlashAttribute("errorMsg", "장소 등록에 실패하였습니다.");
					return "redirect:/";
				}
			} else {
				// 파일 업로드 실패 시 실패 메시지를 리다이렉트할 페이지로 전달
				redirectAttributes.addFlashAttribute("errorMsg", "파일 업로드에 실패하였습니다.");
				return "redirect:/";
			}
		} catch (Exception e) {
			// 예외 로깅
			e.printStackTrace();
			// 사용자에게 보여질 메시지 설정
			redirectAttributes.addFlashAttribute("errorMsg", "오류가 발생하였습니다. 다시 시도해주세요.");
			// 에러 페이지 또는 홈페이지로 리다이렉트
			return "redirect:/";
		}
	}

	// DB에 저장된 후에 업체 최종등록 확인 페이지 이동
	@GetMapping("/companyFinalSubmitPage")
	public String showFinalSubmitPage(HttpSession session, Model model) {
		RoomRegisterVO roomRegisterVO = (RoomRegisterVO) session.getAttribute("roomRegisterVO");
		
		Map userVo = (Map) session.getAttribute("userVO");
		String userId = (String) userVo.get("user_id");
		UserVO loginUser = userService.FindLoginUser(userId);
		model.addAttribute("roomVO", loginUser);
		
		if (roomRegisterVO != null) {
			System.out.println("Received roomRegisterVO from session: " + roomRegisterVO);
		}
		return "company/companyFinalSubmitPage";
	}

	// 룸 승인이 났을경우 업데이트 해주는 경로의 메서드
	@PostMapping("/updateRoomStatus")
	public String updateRoomStatus(@RequestParam int roomNo, @RequestParam int newStatus) {

		roomService.updateRoomStatus(roomNo, newStatus);
		return "redirect:/companyMyPlace"; // 예시: 룸 목록 페이지로 리다이렉트
	}

}