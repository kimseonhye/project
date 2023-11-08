<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0">

<link
	href="<c:url value='resources/css/user/userRoomList.css' />"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,0,200" />

<jsp:include page="../include/title.jsp" />
</head>
 
<body>

	<div id="wrapper">
		<jsp:include page="../include/header.jsp" />

		<div id="requirements">
			<div class="requirement">
				<span class="material-symbols-outlined" style="font-size: 30px;">
					map 지역</span>
			</div>
			<div class="requirement" style="border-left: solid 3px;">
				<span class="material-symbols-outlined" style="font-size: 30px;">
					groups 인원</span>
			</div>
			<div class="requirement" style="border-left: solid 3px;">
				<span class="material-symbols-outlined" style="font-size: 30px;">
					calendar_month 날짜</span>
			</div>
			<div class="requirement" style="border-left: solid 3px;">
				<span class="material-symbols-outlined" style="font-size: 30px;">
					schedule 시간</span>
			</div>
			<div class="search_box">재검색</div>
		</div>
		<br>
		<hr style="height: 3px; background-color: black;" />
		<div id="dropbox">
			<select id="selecter">
				<option value="">전체</option>
				<option value="top_price">높은 가격순</option>
				<option value="low_price">낮은 가격순</option>
			</select>
		</div>
		<div id="list_area">
			<ul>
				<jsp:include page="include/room_list.jsp" />
			</ul>
		</div>
	</div>
	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>
