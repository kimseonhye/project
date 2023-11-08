<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="http://code.jquery.com/jquery-latest.js"></script>

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
			<span class="material-symbols-outlined"
				style="color: black; font-size: 3em;">
				keyboard_double_arrow_down </span>
		</div>
		<div id="main_logo">
			<div class="hamburger">
				<div class="dropdown">
					<span id="hamburger_icon" class="material-symbols-outlined md-30">menu</span>
					<div class="dropdown_option">
						<a href="#">
							<span class="material-symbols-outlined" style="margin: 0 0 0 28px;">favorite</span>
						</a>
						<a href="#">장소 검색</a>
						<a href="#">고객센터</a>
					</div>
				</div>
			</div>
			<img alt="Right Room logo" src="resources/img/rightroomLogo.png" />
		</div>
		<div id="main_pic01" class="picture">
			<img alt="main_picture" src="resources/img/room/room_main01.jpg">
		</div>
		<div id="main_pic02" class="picture">
			<img alt="main_picture" src="resources/img/main_pic01.jpg">
		</div>
		<div id="main_pic03" class="picture">
			<img alt="main_picture" src="resources/img/main_pic02.jpg">
		</div>
		<div id="main_pic04" class="picture">
			<img alt="main_picture" src="resources/img/main_pic03.jpg">
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
		window.addEventListener("wheel", function(e) {
			e.preventDefault();
		}, {
			passive : false
		});

		var $html = $("html");
		var page = 1;
		var lastPage = $(".container").length;

		$html.animate({
			scrollTop : 0
		}, 10);

		$(window).on("wheel", function(e) {
			if ($html.is(":animated"))
				return;

			if (e.originalEvent.deltaY > 0) {
				if (page == lastPage)
					return;

				page++;
			} else if (e.originalEvent.deltaY < 0) {
				if (page == 1)
					return;

				page--;
			}
			var posTop = (page - 1) * $(window).height();

			$html.animate({
				scrollTop : posTop
			});
		});
	</script>
</body>
</html>