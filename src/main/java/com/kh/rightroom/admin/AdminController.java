package com.kh.rightroom.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.rightroom.room.util.UploadFileService;
import com.kh.rightroom.vo.CompanyRoomVO;
import com.kh.rightroom.vo.RoomReviewVO;
import com.kh.rightroom.vo.TableSetVO;
import com.kh.rightroom.vo.UserBookingVO;
import com.kh.rightroom.vo.UserFAQVO;
import com.kh.rightroom.vo.UserNoticeVO;
import com.kh.rightroom.vo.UserVO;

/*Handles requests for the application home page.*/

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminservice;

	@Autowired
	AdminNoticeService adminNoticeService;
	
	@Autowired
	AdminFAQService adminFAQService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@GetMapping("/adminMyPage")
	public String adminMyPage() {
		System.out.println("[AdminController] adminMyPage()");
		String nextPage = "/admin/adminMyPage";

		return nextPage;
	}

	// adminUserManagementPage
	@GetMapping("/allUserManagement")
	public String alluserManagement(Model model) {
		System.out.println("[AdminController] alluserManagement()");
		String nextPage = "/admin/adminUserManagementPage";
		List<UserVO> userVos = adminservice.selectAllUser();
		model.addAttribute("userVos", userVos);

		return nextPage;
	}

	@GetMapping("/userManagement")
	public String userManagement(Model model) {
		System.out.println("[AdminController] userManagement()");
		String nextPage = "/admin/adminUserManagementPage";
		List<UserVO> userVos = adminservice.selectUser();
		model.addAttribute("userVos", userVos);
		return nextPage;
	}

	@GetMapping("/companyUserManagement")
	public String companyUserManagement(Model model) {
		System.out.println("[AdminController] companyUserManagement()");
		String nextPage = "/admin/adminUserManagementPage";
		List<UserVO> userVos = adminservice.selectCompanyUser();
		model.addAttribute("userVos", userVos);
		return nextPage;
	}

	@GetMapping("/userSearch")
	public String userSearch(UserVO userVo, Model model) {
		System.out.println("[AdminController] userSearch()");
		String nextPage = "/admin/adminUserManagementPage";
		List<UserVO> userVos = adminservice.selectUserId(userVo);
		model.addAttribute("userVos", userVos);
		return nextPage;
	}

	@GetMapping("/selectOneUserToID")
	public String selectOneUserToID(UserVO userVo, Model model) {
		System.out.println("[AdminController] selectOneUserToID()");
		userVo = adminservice.selectOneUserToID(userVo);
		String nextPage = userDetailsPage(userVo, model);
		return nextPage;
	}
	
	@GetMapping("/companyuserDetailsPage2")
	public String companyuserDetailsPage2(UserVO userVo, Model model) {
		System.out.println("[AdminController] companyuserDetailsPage2()");
		String nextPage = "/admin/adminCompanyUserDetailsPage2";
		userVo = adminservice.selectOneUser(userVo);
		model.addAttribute("userVo", userVo);
		List<UserBookingVO> CompanyBookingVos = adminservice.selectCompanyBooking(userVo);
		model.addAttribute("CompanyBookingVos", CompanyBookingVos);
		return nextPage;
	}
	
	// adminUserManagementPage
	@GetMapping("/userDetailsPage")
	public String userDetailsPage(UserVO userVo, Model model) {
		System.out.println("[AdminController] userDetailsPage()");
		String nextPage = "/admin/adminUserDetailsPage";
		userVo = adminservice.selectOneUser(userVo);
		model.addAttribute("userVo", userVo);
		if (userVo.getUser_rank() == 1) {
			List<UserBookingVO> UserBookingVos = adminservice.selectUserBooking(userVo);
			model.addAttribute("UserBookingVos", UserBookingVos);
		} else if (userVo.getUser_rank() == 2) {
			List<CompanyRoomVO> CompanyUserRoomVos = adminservice.selectCompanyUserRoom(userVo);
			model.addAttribute("CompanyUserRoomVos", CompanyUserRoomVos);
			nextPage = "/admin/adminCompanyUserDetailsPage";
		}
		return nextPage;
	}

	@GetMapping("/userDelete")
	public String userDelete(UserVO userVo, Model model) {
		System.out.println("[AdminController] userDelete()");
		String nextPage = "/admin/adminUserManagementPage";
		adminservice.deleteUser(userVo);

		List<UserVO> userVos = adminservice.selectAllUser();
		model.addAttribute("userVos", userVos);

		// 삭제에 성공하든 실패하든 항상 사용자 관리 페이지로 리다이렉트
		return nextPage;
	}

	@GetMapping("/reviewDelete")
	public String reviewDelete(UserBookingVO userBookingVo, Model model) {
		System.out.println("[AdminController] reviewDelete()");
		
		UserVO userVo = adminservice.selectUsertoReview(userBookingVo);
		adminservice.deleteReview(userBookingVo);
		String nextPage = userDetailsPage(userVo, model);

		return nextPage;
	}

	@GetMapping("/selectRoomStatus1")
	public String selectRoomStatus1(Model model) {
		System.out.println("[AdminController] selectRoomStatus1()");
		String nextPage = "/admin/adminRoomManagementPage1";
		List<CompanyRoomVO> roomVOs = adminservice.selectRoomStatus1();
		model.addAttribute("roomVos", roomVOs);
		return nextPage;
	}

	@GetMapping("/selectRoomStatus2")
	public String selectRoomStatus2(Model model) {
		System.out.println("[AdminController] selectRoomStatus2()");
		String nextPage = "/admin/adminRoomManagementPage2";
		List<CompanyRoomVO> roomVOs = adminservice.selectRoomStatus2();
		model.addAttribute("roomVos", roomVOs);
		return nextPage;
	}

	@GetMapping("/selectRoomStatus3")
	public String selectRoomStatus3(Model model) {
		System.out.println("[AdminController] selectRoomStatus3()");
		String nextPage = "/admin/adminRoomManagementPage3";

		List<CompanyRoomVO> roomVOs = adminservice.selectRoomStatus3();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSearch1")
	public String roomSearch1(CompanyRoomVO roomVo, Model model) {
		System.out.println("[AdminController] roomSearch1()");
		String nextPage = "/admin/adminRoomManagementPage1";

		List<CompanyRoomVO> roomVOs = adminservice.roomSearch1(roomVo);
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSearch2")
	public String roomSearch2(CompanyRoomVO roomVo, Model model) {
		System.out.println("[AdminController] roomSearch2()");
		String nextPage = "/admin/adminRoomManagementPage2";

		List<CompanyRoomVO> roomVOs = adminservice.roomSearch2(roomVo);
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSearch3")
	public String roomSearch3(CompanyRoomVO roomVo, Model model) {
		System.out.println("[AdminController] roomSearch3()");
		String nextPage = "/admin/adminRoomManagementPage3";

		List<CompanyRoomVO> roomVOs = adminservice.roomSearch3(roomVo);
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect1Status1")
	public String roomSelect1Status1(Model model) {
		System.out.println("[AdminController] roomSelect1Status1()");
		String nextPage = "/admin/adminRoomManagementPage1";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect1Status1();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect2Status1")
	public String roomSelect2Status1(Model model) {
		System.out.println("[AdminController] roomSelect2Status1()");
		String nextPage = "/admin/adminRoomManagementPage1";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status1();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect3Status1")
	public String roomSelect3Status1(Model model) {
		System.out.println("[AdminController] roomSelect3Status1()");
		String nextPage = "/admin/adminRoomManagementPage1";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect3Status1();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect1Status2")
	public String roomSelect1Status2(Model model) {
		System.out.println("[AdminController] roomSelect1Status2()");
		String nextPage = "/admin/adminRoomManagementPage2";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect1Status2();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect2Status2")
	public String roomSelect2Status2(Model model) {
		System.out.println("[AdminController] roomSelect2Status2()");
		String nextPage = "/admin/adminRoomManagementPage2";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status2();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect3Status2")
	public String roomSelect3Status2(Model model) {
		System.out.println("[AdminController] roomSelect3Status2()");
		String nextPage = "/admin/adminRoomManagementPage2";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect3Status2();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect1Status3")
	public String roomSelect1Status3(Model model) {
		System.out.println("[AdminController] roomSelect1Status3()");
		String nextPage = "/admin/adminRoomManagementPage3";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect1Status3();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect2Status3")
	public String roomSelect2Status3(Model model) {
		System.out.println("[AdminController] roomSelect2Status3()");
		String nextPage = "/admin/adminRoomManagementPage3";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status3();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomSelect3Status3")
	public String roomSelect3Status3(Model model) {
		System.out.println("[AdminController] roomSelect3Status3()");
		String nextPage = "/admin/adminRoomManagementPage3";

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect3Status3();
		model.addAttribute("roomVos", roomVOs);

		return nextPage;
	}

	@GetMapping("/roomApproval")
	public String roomApproval(CompanyRoomVO roomVo, Model model) {
		System.out.println("[AdminController] roomApproval()");
		String nextPage = "/admin/adminRoomManagementPage3";

		int result = adminservice.roomApproval(roomVo);
		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status3();
		model.addAttribute("roomVos", roomVOs);

		if (result <= 0) {
			nextPage = "/admin/adminMyPage";
		}

		return nextPage;
	}

	@GetMapping("/roomCancle")
	public String roomCancle(CompanyRoomVO roomVo, @RequestParam("cancleReason") String cancleReason, Model model) {
		System.out.println("[AdminController] roomCancle()");
		String nextPage = "/admin/adminRoomManagementPage2";

		adminservice.roomCancleEmailing(cancleReason, roomVo);
		int result = adminservice.roomCancle(roomVo);

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status2();
		model.addAttribute("roomVos", roomVOs);

		if (result <= 0) {
			nextPage = "/admin/adminMyPage";
		}

		return nextPage;
	}

	@GetMapping("/roomDelete")
	public String roomDelete(CompanyRoomVO roomVo, @RequestParam("deleteReason") String deleteReason, Model model) {
		System.out.println("[AdminController] roomDelete()");
		String nextPage = "/admin/adminRoomManagementPage3";

		adminservice.roomDeleteEmailing(deleteReason, roomVo);
		int result = adminservice.roomDelete(roomVo);

		List<CompanyRoomVO> roomVOs = adminservice.roomSelect2Status3();
		model.addAttribute("roomVos", roomVOs);

		if (result <= 0) {
			nextPage = "/admin/adminMyPage";
		}

		return nextPage;
	}

	@GetMapping("/adminUserHistoryConfirm")
	public String adminUserHistoryConfirm(CompanyRoomVO roomVo, @RequestParam("user_id") String user_id, Model model) {
		System.out.println("[AdminController] adminUserHistoryConfirm()");
		model.addAttribute("user_id", user_id);

		String nextPage = "/admin/adminCompanyPlaceConfirm";
		roomVo = adminservice.adminCompanyPlaceConfirm(roomVo);
		model.addAttribute("roomVo", roomVo);

		List<RoomReviewVO> reviewVos = adminservice.CompanyPlaceReviewlist(roomVo);
		model.addAttribute("reviewVos", reviewVos);

		TableSetVO tableSetVo = adminservice.selectRoomTableSet(roomVo);
		model.addAttribute("tableSetVo", tableSetVo);

		return nextPage;
	}

	@GetMapping("/adminCompanyPlaceConfirm")
	public String adminCompanyPlaceConfirm(CompanyRoomVO roomVo, Model model) {
		System.out.println("[AdminController] adminCompanyPlaceConfirm()");
		String nextPage = "/admin/adminCompanyPlaceConfirm2and3";
		roomVo = adminservice.adminCompanyPlaceConfirm(roomVo);
		model.addAttribute("roomVo", roomVo);

		List<RoomReviewVO> reviewVos = adminservice.CompanyPlaceReviewlist(roomVo);
		model.addAttribute("reviewVos", reviewVos);

		TableSetVO tableSetVo = adminservice.selectRoomTableSet(roomVo);
		model.addAttribute("tableSetVo", tableSetVo);

		if (roomVo.getRoom_status() == 1) {
			nextPage = "/admin/adminCompanyPlaceConfirm1";
		}

		return nextPage;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//공지사항 FAQ create read 매핑
	@GetMapping("/adminCreateNotice")
	public String adminCreateNotice() {
		return "admin/adminCreateNotice";
	}
	
	@GetMapping("/adminCreateFAQ")
	  public String adminCreateFAQ() {
	    return "admin/adminCreateFAQ";
	  }
	
	@PostMapping("/registerNoticeConfirm")
    public String registerNotice(UserNoticeVO userNoticeVO,
    							 @RequestParam("n_u_title") String title,
                                 @RequestParam("n_u_content") String content,
                                 @RequestParam(name = "file", required = false) MultipartFile file) {
	
		String nextPage = "admin/adminNotice";
		
		// 파일이 첨부되지 않은 경우 또는 파일 크기가 0인 경우 처리
	    if (file == null || file.isEmpty()) {
	        // 이미지가 첨부되지 않은 경우 처리
	        userNoticeVO.setN_u_image(null);
	    } else {
	        String savedFileName = uploadFileService.upload(file);
	        if (savedFileName != null) {
	            userNoticeVO.setN_u_image(savedFileName);
	        }
	    }
		
		int result = adminNoticeService.registerNoticeConfirm(userNoticeVO);

	    if (result <= 0) {
	        nextPage = "admin/adminNotice";
	    }

        return nextPage; // 이동할 페이지 설정
    }

    @PostMapping("/registerFAQConfirm")
    public String registerFAQ(UserFAQVO userFAQVO,
    						  @RequestParam("faq_u_title") String title,
                              @RequestParam("faq_u_content") String content,
                              @RequestParam(name = "file", required = false) MultipartFile file) {

		String nextPage = "admin/adminFAQ";
		
		// 파일이 첨부되지 않은 경우 또는 파일 크기가 0인 경우 처리
	    if (file == null || file.isEmpty()) {
	        // 이미지가 첨부되지 않은 경우 처리
	        userFAQVO.setFaq_u_image(null);
	    } else {
	        String savedFileName = uploadFileService.upload(file);
	        if (savedFileName != null) {
	            userFAQVO.setFaq_u_image(savedFileName);
	        }
	    }
		
		int result = adminFAQService.registerFAQConfirm(userFAQVO);

	    if (result <= 0) {
	        nextPage = "admin/adminFAQ";
	    }

        return nextPage; // 이동할 페이지 설정
    }
	
	@GetMapping("/adminNotice")
	  public String adminNotice(Model model) {
		
		List<UserNoticeVO> noticeList = adminNoticeService.getAllNotices();		
		model.addAttribute("noticeList", noticeList);
	
	    return "admin/adminNotice";
	  }
	
	@GetMapping("/adminFAQ")
	  public String adminFAQ(Model model) {
		
		List<UserFAQVO> FAQList = adminFAQService.getAllFAQs();
		
		model.addAttribute("FAQList", FAQList);
		
	    return "admin/adminFAQ";
	  }
	
	//공지사항 update 매핑
	@GetMapping("/modifyNoticeForm")
	public String modifyNoticeForm(@RequestParam("n_u_no") int n_u_no, Model model) {
		
		String nextPage = "admin/ModifyNoticeForm";
		
		UserNoticeVO userNoticeVO = adminNoticeService.modifyNoticeForm(n_u_no);
		
		model.addAttribute("userNoticeVO", userNoticeVO);
		
		return nextPage;
	}
	
	//FAQ update 매핑
	@GetMapping("/modifyFAQForm")
	public String modifyFAQForm(@RequestParam("faq_u_no") int faq_u_no, Model model) {
		
		String nextPage = "admin/ModifyFAQForm";
		
		UserFAQVO userFAQVO = adminFAQService.modifyFAQForm(faq_u_no);
		
		model.addAttribute("userFAQVO", userFAQVO);
		
		return nextPage;
	}
	
	@PostMapping("/modifyNoticeConfirm")
	public String modifyNoticeConfirm(UserNoticeVO userNoticeVO,
									@RequestParam("file") MultipartFile file) {
		String nextPage = "admin/adminNotice";
		
		if(!file.getOriginalFilename().equals("")) {
			//파일 저장
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null)
				userNoticeVO.setN_u_image(savedFileName);
		}
		
		int result = adminNoticeService.modifyNoticeConfirm(userNoticeVO);
		
		if(result <= 0)
			nextPage = "admin/adminNotice";
		
		return nextPage;
	}
	
	@PostMapping("/modifyFAQConfirm")
	public String modifyFAQConfirm(UserFAQVO userFAQVO,
									@RequestParam("file") MultipartFile file) {
		String nextPage = "admin/adminFAQ";
		
		if(!file.getOriginalFilename().equals("")) {
			//파일 저장
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null)
				userFAQVO.setFaq_u_image(savedFileName);
		}
		
		int result = adminFAQService.modifyFAQConfirm(userFAQVO);
		
		if(result <= 0)
			nextPage = "admin/adminFAQ";
		
		return nextPage;
	}
	
	//공지사항 search 매핑
	@GetMapping("/searchNoticeConfirm")
	public String searchNoticeConfirm(UserNoticeVO userNoticeVO, Model model) {
		String nextPage = "admin/adminNotice";
		
		List<UserNoticeVO> userNoticeVOs = adminNoticeService.searchNoticeConfirm(userNoticeVO);
		
		model.addAttribute("userNoticeVOs", userNoticeVOs);
		
		return nextPage;
	}
	
	//공지사항 delete 매핑
	@GetMapping("/deleteNoticeConfirm")
	public String deleteNoticeConfirm(@RequestParam("n_u_no") int n_u_no) {
		String nextPage = "admin/adminNotice";
		
		int result = adminNoticeService.deleteNoticeConfirm(n_u_no);
		
		if(result <= 0)
			nextPage = "admin/adminNotice";
		
		return nextPage;
	}
	
	//FAQ delete 매핑
		@GetMapping("/deleteFAQConfirm")
		public String deleteFAQConfirm(@RequestParam("faq_u_no") int faq_u_no) {
			String nextPage = "admin/adminFAQ";
			
			int result = adminFAQService.deleteFAQConfirm(faq_u_no);
			
			if(result <= 0)
				nextPage = "admin/adminFAQ";
			
			return nextPage;
		}
	
}