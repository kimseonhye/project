<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="<c:url value='/resources/css/main/searchPlace.css' />"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,0,200" />

<!-- 캘린더 -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<br>
<br>
<h2>"성공적인 비즈니스를 위한 최적의 장소를 찾아보세요"</h2>
<!-- <form action="userRoomList" method="GET"> -->
<form id="searchPlace" action="<c:url value='/room/searchPlace' />"
	method="get">
	<!-- 폼 엘리먼트 추가 -->
	<div id="requirements">

		<div class="requirement" id="mapRequirement">
			<span class="material-symbols-outlined"> map 지역</span>
		</div>
		<div class="search">

			<div class="search">
				<!-- 검색 창과 변경 버튼 -->
				<input type="text" class="locationInput_pp" placeholder="구/동 입력"
					name="room_address">
			</div>

		</div>

		<div class="requirement" style="border-left: solid 2px grey;">
			<span class="material-symbols-outlined"> groups 인원</span>
		</div>

		<div class="search">
			<!-- 검색 창과 변경 버튼 -->
			<input type="text" class="locationInput_pp" placeholder="이용 인원"
				name="room_max_ppl">
		</div>

		<div class="requirement" style="border-left: solid 2px grey;">
			<span class="material-symbols-outlined">add_card 가격</span>

		</div>
		<div class="search">
			<div class="search">
				<!-- 검색 창과 변경 버튼 -->
				<input type="text" class="locationInput_pp" placeholder="원하는 가격"
					name="room_price">
			</div>

		</div>
		<button type="submit">검색</button>

	</div>
</form>

<div class="square-container">
	<div class="square">
		<img alt="square_picture" src="resources/img/square_1.jpg">
		<div class="overlay">"혁신적"</div>
	</div>
	<div class="square">
		<img alt="square_picture" src="resources/img/square_2.jpg">
		<div class="overlay">"창의적"</div>
	</div>
	<div class="square">
		<img alt="square_picture" src="resources/img/square_3.jpg">
		<div class="overlay">"열정적"</div>
	</div>
</div>


<script>
	function toggleDropdown(dropdownId) {
		var dropdown = document.getElementById(dropdownId);
		var style = window.getComputedStyle(dropdown);
		var display = style.getPropertyValue('display');
		if (display === "none") {
			dropdown.style.display = "block";
		} else {
			dropdown.style.display = "none";
		}
	}
</script>