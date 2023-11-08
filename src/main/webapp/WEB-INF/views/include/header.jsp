<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/resources/css/include/header.css' />"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,0,200" />
<head>
</head>
<header>

	<div id="header">
		<div class="menu">
			<div class="dropdown">
				<span id="hamburger_icon" class="material-symbols-outlined md-30">menu</span>
				<div class="dropdown_option">
					<c:choose>
						<c:when test="${empty sessionScope.userVO}">
							<a href="loginPage">로그인 / 회원가입</a>
						</c:when>
						<c:otherwise>
							<a href="../logout">로그아웃</a>
							<c:choose>
								<c:when test="${sessionScope.userVO.user_rank eq 1}">
									<a href="${pageContext.request.contextPath}/user/userMyPage">마이페이지</a>
								</c:when>
								<c:when test="${sessionScope.userVO.user_rank eq 2}">
									<a
										href="${pageContext.request.contextPath}/company/companyMyPage">마이페이지</a>
								</c:when>
								<c:when test="${sessionScope.userVO.user_rank eq 3}">
									<a href="${pageContext.request.contextPath}/admin/adminMyPage">마이페이지</a>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<a href="../searchPlace">장소 검색</a> <a
						href="${pageContext.request.contextPath}/admin/adminNotice">고객센터</a>
				</div>
			</div>
		</div>

		<a href="/project"> <img alt="Right Room logo"
			src="${pageContext.request.contextPath}/resources/img/rightroomLogo.png" />
		</a>
	</div>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var dropdown = document.querySelector(".dropdown");
			var dropdownOption = document.querySelector(".dropdown_option");

			// 클릭 이벤트를 추가하여 드롭다운 메뉴를 토글합니다.
			dropdown.addEventListener("click", function(event) {
				event.stopPropagation(); // 이벤트 버블링 방지

				if (dropdownOption.style.display === "block") {
					dropdownOption.style.display = "none";
				} else {
					dropdownOption.style.display = "block";
				}
			});

			// 다른 곳을 클릭하면 드롭다운을 닫습니다.
			document.addEventListener("click", function(event) {
				if (dropdownOption.style.display === "block") {
					dropdownOption.style.display = "none";
				}
			});

			// 드롭다운 내부의 링크가 클릭되면 드롭다운을 닫습니다.
			dropdownOption.addEventListener("click", function(event) {
				event.stopPropagation(); // 이벤트 버블링 방지
			});
		});
	</script>
</header>

<hr>