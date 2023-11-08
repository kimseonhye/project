<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 내부css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/room/userRoomList.css"
	type="text/css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,0,200" />

<jsp:include page="../include/title.jsp" />
</head>

<body>

	<div id="wrapper">
		<jsp:include page="../include/header.jsp" />
		<div id="requirements">
			<p class="search-room-text">"새로운 도전을 위한 장소를 찾아보세요."</p>
		</div>
		<br>
		<!-- <hr> -->
		<div id="dropbox">
			<select id="selecter" onchange="sortRooms()">
				<option value="">전체</option>
				<option value="top_price">높은 가격순</option>
				<option value="low_price">낮은 가격순</option>
			</select>
		</div>
		<div id="list_area">
			<ul id="roomList">
				<!-- JSTL을 사용하여 검색 결과 반복 표시 -->
				<c:forEach items="${rooms}" var="room">
					<div class="room_list">
						<div class="hotel_pic">
							<!-- 방 이미지 -->
							<img class="room_img" alt="room_image" src="${pageContext.request.contextPath}/roomUploadImg/${room.room_images}"
									style="display: block; height: 200px;" />
						</div>
						<div class="hotel_info">
							<!-- 방 정보 -->
							<%-- <c:forEach items="${rooms}" var="room"> --%>
								<li>
									<p class="search-final-name">${room.room_name}</p>
									<p class="search-final-ppl">최대 수용 인원 : ${room.room_max_ppl}명</p>
									<p class="search-final-address">주소 : ${room.room_address}</p>
									<p class="search-final-price">금액 : ${room.room_price}</p>
								</li>
								<div class="more">
									<a href="userRoomDetail?room_no=${room.room_no}"class="more-btn">상세정보</a>
								</div>
							<%-- </c:forEach> --%>
						</div>
					</div>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<jsp:include page="../include/footer.jsp" />
	
	<script>
	// 가격 천 단위 구분 함수
	document.addEventListener("DOMContentLoaded", function () {
	    // 가격 요소 가져오기
	    var priceElement = document.getElementsByClassName("search-final-price");
	
	    // 가격 형식화 함수
	    function formatPrice(price) {
	        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	    }
	    
	    // 가격 형식화 적용
	    for (var element of priceElement) {
	    	var originalPrice = element.innerText;
	    	element.innerText = formatPrice(originalPrice) + " 원";
	    }
	});
	// 가격 천 단위 구분 함수 끝
	
	// 필터 기능
	function sortRooms() {
    // 선택한 옵션 값을 가져오기
    var selectedOption = document.getElementById("selecter").value;
    
    // 방 목록을 가져오기
    var roomList = document.querySelectorAll(".room_list");

    // 가격에 따라 방 목록을 정렬
    if (selectedOption === "top_price") {
        roomList = Array.from(roomList).sort(function (a, b) {
            var priceA = parseInt(a.querySelector(".search-final-price").textContent.replace(/\D/g, ''));
            var priceB = parseInt(b.querySelector(".search-final-price").textContent.replace(/\D/g, ''));
            return priceB - priceA;
        });
    } else if (selectedOption === "low_price") {
        roomList = Array.from(roomList).sort(function (a, b) {
            var priceA = parseInt(a.querySelector(".search-final-price").textContent.replace(/\D/g, ''));
            var priceB = parseInt(b.querySelector(".search-final-price").textContent.replace(/\D/g, ''));
            return priceA - priceB;
        });
    }

    // 정렬된 목록을 화면에 다시 그리기
    var listArea = document.getElementById("list_area");
    listArea.innerHTML = ""; // 목록 지우기

    for (var room of roomList) {
        listArea.appendChild(room);
    }
}
	</script>
</body>
</html>
