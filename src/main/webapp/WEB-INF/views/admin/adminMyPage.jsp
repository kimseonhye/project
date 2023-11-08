<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link href="<c:url value='/resources/css/admin/adminMyPage.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<body>
	<jsp:include page="../include/header.jsp" />

	<div class="maintitle">
		<h2>관리자 페이지</h2>
	</div>
	<div class="container">
		<div class="box" id="userManagement">

			<div>
				<span class="material-symbols-outlined"> manage_accounts </span>
				<p class="usermg">회원 관리</p>
				<p class="type">개인 회원 / 업체 회원</p>
			</div>
		</div>
		<div class="box" id="roomManagement">

			<div>
				<span class="material-symbols-outlined"> domain_add </span>
				<p class="placemg">장소 관리</p>
				<p class="type">신규 / 삭제 / 조회</p>
			</div>
		</div>
		<div class="box" id="customerServiceCenter">

			<div>
				<span class="material-symbols-outlined"> support_agent </span>
				<p class="customermg">고객 센터</p>
				<p class="type">등록 / 수정 / 삭제</p>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />

	<script>
		// 박스를 클릭할 때 링크로 이동하는 함수
		function navigateToLink(link) {
			window.location.href = link;
		}

		// 회원관리 박스 클릭 이벤트 핸들러
		document.getElementById("userManagement").addEventListener("click",
				function() {
					navigateToLink("<c:url value='./allUserManagement' />");
				});

		// 장소관리 박스 클릭 이벤트 핸들러
		document.getElementById("roomManagement").addEventListener("click",
				function() {
					navigateToLink("<c:url value='./selectRoomStatus3' />");
				});

		// 고객센터 박스 클릭 이벤트 핸들러
		document.getElementById("customerServiceCenter").addEventListener(
				"click", function() {
					navigateToLink("<c:url value='./adminNotice' />");
				});
	</script>

</body>
</html>
