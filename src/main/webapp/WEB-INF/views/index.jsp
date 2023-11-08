<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<jsp:include page="include/title.jsp" />

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<link href="<c:url value='/resources/css/main/index.css' />"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,0,200" />

</head>
<body>

	<!-- 위 영역 컨테이너 -->
	<div class="container">
		<div class="scroll_down">
			<span class="material-symbols-outlined" id="scroll"
				style="color: black; font-size: 3em;">
				keyboard_double_arrow_down </span>
		</div>
		<div id="main_logo">
			<div class="hamburger">
				<div class="dropdown">
					<span id="hamburger_icon" class="material-symbols-outlined md-30"
						style="font-size: 3em;">menu</span>
					<div class="dropdown_option">
						<c:choose>
							<c:when test="${empty sessionScope.userVO}">
								<!-- User is not logged in -->
								<a href="loginPage">로그인 / 회원가입</a>
							</c:when>
							<c:otherwise>
								<!-- User is logged in -->
								<a href="/project/logout">로그아웃</a>
								<c:choose>
									<c:when test="${sessionScope.userVO.user_rank eq 1}">
										<a href="user/userMyPage">마이페이지</a>
									</c:when>
									<c:when test="${sessionScope.userVO.user_rank eq 2}">
										<a href="company/companyMyPage">마이페이지</a>
									</c:when>
									<c:when test="${sessionScope.userVO.user_rank eq 3}">
										<a href="admin/adminMyPage">마이페이지</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<a href="searchPlace">장소 검색</a> <a
							href="${pageContext.request.contextPath}/admin/adminNotice">고객센터</a>

					</div>
				</div>
			</div>
			<img alt="Right Room logo" src="resources/img/rightroomLogo.png" />
		</div>
		<div id="main_pic01" class="picture">
			<img alt="main_picture" src="resources/img/main_pic01.jpg">
		</div>
		<div id="main_pic02" class="picture">
			<img alt="main_picture" src="resources/img/main_pic02.jpg">
		</div>
		<div id="main_pic03" class="picture">
			<img alt="main_picture" src="resources/img/main_pic03.jpg">
		</div>
		<div id="main_pic04" class="picture">
			<img alt="main_picture" src="resources/img/main_pic04.jpg">
		</div>
	</div>

	<!-- 아래 영역 컨테이너 -->
	<div class="container">
		<jsp:include page="searchPlace.jsp" />
		<div class="footer_info">
			<jsp:include page="include/footer.jsp" />
		</div>
	</div>

	<script type="text/javascript">
		var page = 1;

		$(document).ready(function() {
			$(".scroll_down").click(function() {
				if (page == $(".container").length)
					return;
				page++;
				var posTop = (page - 1) * $(window).height();
				$("html, body").animate({
					scrollTop : posTop
				}, 1000);
			});

			$(window).on("wheel", function(e) {
				if ($("html").is(":animated"))
					return;

				if (e.originalEvent.deltaY > 0) {
					if (page == $(".container").length)
						return;
					page++;
				} else if (e.originalEvent.deltaY < 0) {
					if (page == 1)
						return;
					page--;
				}
				var posTop = (page - 1) * $(window).height();

				$("html, body").animate({
					scrollTop : posTop
				}, 1000);
			});
		});
	</script>
</body>
</html>