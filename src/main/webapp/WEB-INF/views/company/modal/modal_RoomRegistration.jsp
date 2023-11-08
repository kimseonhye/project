<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/company/modal/modal_RoomRegistration.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">
						<b>등록이 완료되었습니다.</b>
					</h1>
				</div>
				<div class="modal-body">
					<p>처리 결과는 등록하신 이메일로 발송됩니다.</p>
					<button class="btn btn-dark" type="button" data-bs-dismiss="modal"
						aria-label="Close" onclick="goToCompanyMyPage();">확인</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Include Bootstrap and jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>

	<!-- Initialize the modal -->
	<script>
		var myModal = new bootstrap.Modal(document
				.getElementById('staticBackdrop'));
		
		// 3.등록버튼 누르면 컴퍼니 마이페이지로 이동
	    function goToCompanyMyPage() {
			
			//지금은 프로젝트로 경로가 설정되어있지만 이후에는 메인페이지로 이동하는 경로로 수정 해야함!!!
	        window.location.href = "/project";
	    }
	</script>
</body>
</html>