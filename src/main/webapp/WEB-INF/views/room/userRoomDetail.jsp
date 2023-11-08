<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 내부css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/room/userRoomDetail.css"
	type="text/css">
<!-- 외부css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	
</head>
<body>
	<jsp:include page="../include/header.jsp" />

	<div class="centered-container">
		<div class="circle-icon">
			<div class="inner-icon">
				<i class="material-symbols-outlined">newsmode</i>
			</div>
		</div>
		<div class="text">상세 페이지</div>
	</div>

	<div class="wrapper">
		<div class="left-section">
			<div class="gallery" id="gallery">
				<%-- <img
					src="${pageContext.request.contextPath}/resources/img/room/hotelexample_1.jpg" /> --%>
				<img alt="room_image" src="${pageContext.request.contextPath}/roomUploadImg/${roomVO.room_images}"/>
			</div>

			<hr class="custom-hr">
			<div id="map" style="width: 100%; height: 350px;"></div>
			<hr class="custom-hr">
			<div class="max-occupancy-text">최대 수용 인원</div>
			<!-- <div class="max-occupancy"> -->
					<p class="db-data">${roomVO.room_max_ppl}명</p>
			<!-- </div> -->

			<hr class="custom-hr">

			<!-- 업체가 장소 등록 시 입력한 테이블 배치값들만 나타나며 아닌 것들은 display:none; -->

			<div class="max-occupancy-text">테이블 배치</div> 
			<br>
			<div class="checkbox-group">
				<div class="form-check form-check-inline custom-checkbox" id="t_set1">
					 <label for="d-shaped" class="form-check-label"> ㄷ자형 
					 	<span class="material-icons">drag_indicator</span>
					</label>
				</div>


				<div class="form-check form-check-inline custom-checkbox" id="t_set2">
					<label for="lecture" class="form-check-label"> 강의식
						<!-- 얘가 아이콘 --> 
						<span class="material-icons">width_full</span>
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox" id="t_set3">
					<label for="banquet" class="form-check-label"> 연회식 
						<!-- 얘가 아이콘 --> 
						<span class="material-icons">wb_sunny</span>
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox" id="t_set4">
					<!-- 얘가 아이콘 --> 
					<label for="theater" class="form-check-label"> 극장식 
						<span class="material-icons">theater_comedy</span>
					</label>
				</div>
			</div>

			<hr class="custom-hr">

			<div class="cancellation-policy">
				<div class="max-occupancy-text">FAQ</div>
					<p class="db-data">${roomVO.room_faq}</p>
			</div>

			<hr class="custom-hr">

			<div class="cancellation-policy">
				<div class="max-occupancy-text">취소 환불 규정</div>
					<p class="db-data">${roomVO.room_cancellation_and_refund_policy}</p>
			</div>

			<hr class="custom-hr">

			<div class="cancellation-policy">
				<div class="max-occupancy-text">리뷰</div>
					<!-- 리뷰 목록을 반복 -->
					<c:forEach items="${reviews}" var="review">
						<li class="room-review">
							<span class="r-score">${review.review_star}점</span>
							<span class="r-content">${review.review_content}</span>
							<span class="r-date">[${review.review_date}]</span>
							<hr class="review-hr">
						</li>
					</c:forEach>
			</div>
		</div>

		<div class="right-section">
			<div class="hotel-info-box">
				<!-- 다음은 RoomVO에서 가져온 변수 사용 -->
				<p class="room-list-name">${roomVO.room_name}</p>
				<p class="room-list-address">${roomVO.room_address}</p>
				<p class="room-list-phone">${roomVO.room_phone}</p>
			</div>
			<div class="total-amount">
				<p class="total-amount-label">총 예약 금액</p>
				<!-- 다음은 RoomVO에서 가져온 변수 사용 -->
				<p class="total-amount-value" id="price">${roomVO.room_price}</p>
			</div>
			<div class="buttons">
				<a href="<c:url value='../user/userBookingPage?room_no=${roomVO.room_no}' />" style="background:black">예약서 작성</a>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=06a7ff448b4d3de0fac2430da275797d&libraries=services"></script>
	<script>
	//지도
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	var sessionAddress = '<%= session.getAttribute("addressFromSession") %>';

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(sessionAddress, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
  		  } 
	});
	
    // 가격 천 단위 구분 함수
	document.addEventListener("DOMContentLoaded", function () {
    // 가격 요소 가져오기
    var priceElement = document.getElementById("price");

    // 가격 형식화 함수
    function formatPrice(price) {
        return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }

    // 가격 형식화 적용
    var originalPrice = priceElement.innerText;
	    priceElement.innerText = formatPrice(originalPrice) + " 원";
	});
	// 가격 천 단위 구분 함수 끝

	// 테이블 세팅
	if (${tableSet.t_set1} == 0) {
   		document.getElementById('t_set1').style.display = 'none';
	}
	if (${tableSet.t_set2} == 0) {
	   	 document.getElementById('t_set2').style.display = 'none';
	}
	if (${tableSet.t_set3} == 0) {
	   	 document.getElementById('t_set3').style.display = 'none';
	}
	if (${tableSet.t_set4} == 0) {
	   	 document.getElementById('t_set4').style.display = 'none';
	}
	
	</script>

	<jsp:include page="../include/footer.jsp" />
</body>
</html>
