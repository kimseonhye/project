<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 내부css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/company/companyRoomDetailPageModify.css" type="text/css">
<!-- 외부css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

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
        <!-- 헤더 아래에 상세페이지 아이콘 -->
        <!-- 왼쪽 섹션 -->
        <div class="left-section">
            <!-- 호텔 이미지 슬라이더 -->
			<div class="gallery" id="gallery">
				<img alt="room image"
					src="${pageContext.request.contextPath}/roomUploadImg/${roomDetail.room_images}">
			</div>
            
			<hr class="custom-hr">
			

			<!-- 지도 부분 -->
			<span class="table-label">위치</span>
			<br>
			<div id="map" style="width:100%;height:350px;"></div>
   
   			<hr class="custom-hr">
			


			<!-- 최대 수용 인원을 감싸는 div -->
			<div class="max-occupancy">
				<div class="data-area">
					<span class="table-label">최대 수용 인원</span> <input type="text"
						id="maxCapacity" name="room_max_ppl"
						value="${roomDetail.room_max_ppl}명" class="form-control"
						readonly />
				</div>
			</div>

		<hr class="custom-hr">
		
		<!-- 테이블 배치 체크박스 -->
   		<span class="table-label">테이블 배치</span>
   		<br>
		<div class="checkbox-group">
		
			<div class="form-check form-check-inline custom-checkbox">
				<input type="checkbox" id="d-shaped" name="t_set1"
					class="form-check-input" ${roomDetail.t_set1 ? 'checked' : ''}
					readonly /> <label for="d-shaped" class="form-check-label">
					ㄷ자형 <span class="material-icons">drag_indicator</span> <!--최대 30명-->
				</label>
			</div>

			<div class="form-check form-check-inline custom-checkbox">
				<input type="checkbox" id="lecture" name="t_set2"
					class="form-check-input" ${roomDetail.t_set2 ? 'checked' : ''}
					readonly /> <label for="lecture" class="form-check-label">
					강의식 <span class="material-icons">width_full</span> <!--최대 50명-->
				</label>
			</div>

			<div class="form-check form-check-inline custom-checkbox">
				<input type="checkbox" id="banquet" name="t_set3"
					class="form-check-input" ${roomDetail.t_set3 ? 'checked' : ''}
					readonly /> <label for="banquet" class="form-check-label">
					연회식 <span class="material-icons">wb_sunny</span> <!--최대 100명-->
				</label>
			</div>

			<div class="form-check form-check-inline custom-checkbox">
				<input type="checkbox" id="theater" name="t_set4"
					class="form-check-input" ${roomDetail.t_set4 ? 'checked' : ''}
					readonly /> <label for="theater" class="form-check-label">
					극장식 <span class="material-icons">theater_comedy</span> <!--최대 150명-->
				</label>
			</div>
	</div>
		
		<hr class="custom-hr">
		
		<!-- FAQ 섹션 -->
		<div class="mb-3 d-flex align-items-center">
			<label for="faq" class="form-label me-3">FAQ</label>
			<div class="custom-input-box" style="margin-left: 92px;">
				<textarea id="faq" name="room_faq"
					class="form-control custom-textarea" readonly>${roomDetail.room_faq}
				</textarea>
			</div>
		</div>

		<hr class="custom-hr">
		
		<!-- 취소 환불 규정 -->
		<div class="mb-3 d-flex align-items-center">
			<label for="cancle" class="form-label me-3">취소 및 환불규정</label>
			<div class="custom-input-box">
				<textarea id="cancle" name="room_cancellation_and_refund_policy"
					class="form-control custom-textarea" readonly>${roomDetail.room_cancellation_and_refund_policy}
				</textarea>
			</div>
		</div>
	
		<hr class="custom-hr">
		
		<!-- 리뷰 섹션 -->
		<div class="cancellation-policy">
			<div class="max-occupancy-text">리뷰</div>
			<div class="data-area">
				<p>${roomDetail.review_Star}</p>
				<p>${roomDetail.review_date}</p>
				<p>${roomDetail.review_Content}</p>
			</div>
		</div>
		
		</div>

	<!-- 오른쪽 섹션 -->
	<div class="right-section">
	
		<div class="hotel-info-box">
			<h3>${roomDetail.user_name}</h3>
			<br>
			<p>${roomDetail.room_name}</</p>
			<p>${roomDetail.room_address}</p>
			<p>${roomDetail.user_phone}</p>
			<p>${roomDetail.room_regi_date}</p>
		</div>
		
		<div class="total-amount">
			<p class="total-amount-label">1인당 예약 금액</p>
			<p class="total-amount-value">${roomDetail.room_price}KRW</p>
		</div>

		<!-- 모달 창이 들어가야함 목록/수정/삭제 -->
		<div class="btn-group" role="group" aria-label="Basic example">
		
		    <button type="button" class="btn btn-secondary me-2" 
		    	onclick="goBack()" style="width: 100px;">목록</button>
		    
		    <a href="<c:url value='../companyRoomModify/${roomDetail.room_no}'/>" 
		    	class="btn btn-dark me-2" style="width: 100px;">수정</a>
		    
		    <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop" 
		    	style="width: 100px; background-color: #FF6B6B;">삭제</button>
		
		</div>

	    
	</div>
		</div>
		
		<jsp:include page="../include/footer.jsp" />
		
				<!-- Hidden Form for Deleting -->
		<form action="<c:url value='/company/deleteCompanyRoom'/>" method="post" id="deleteForm" style="display: none;">
		    <input type="hidden" name="roomNo" value="${roomDetail.room_no}" />
		</form>
		
		<jsp:include page="modal/modal_RoomDelete.jsp" />
		
		<!-- 카카오 맵 API를 사용하기 위한 스크립트 태그 appkey가 있어야 함 -->
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=06a7ff448b4d3de0fac2430da275797d&libraries=services"></script>
    		
		
		<script>

	    // 1.취소 버튼 누르면 브라우저의 이전 페이지로 이동
		function goBack() {
			window.history.back();
		}

		// 2.클릭하면 적용되는 기능
		const checkboxes = document.querySelectorAll('input[type="checkbox"]');
		
		checkboxes.forEach(checkbox => {
		    checkbox.addEventListener('change', function () {
		        const label = this.nextElementSibling;
		
		        if (this.checked) {
		            // 선택한 경우 배경 색상 변경
		            label.classList.add('selected');
		            checkbox.parentElement.classList.add('selected'); // 부모 요소에도 selected 클래스 추가
		        } else {
		            // 선택을 취소한 경우 원래 색상으로 복원
		            label.classList.remove('selected');
		            checkbox.parentElement.classList.remove('selected'); // 부모 요소에서 selected 클래스 제거
		        }
		    });
		});
		
		// 3.DB에서 가져온 주소를 사용하여 지도에 표시 (카카오 맵 api사용)
       var dbAddress ='${roomDetail.room_address}';

        var mapContainer = document.getElementById('map');
        var mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3
        };
        
        var map = new kakao.maps.Map(mapContainer, mapOption);

        var geocoder = new kakao.maps.services.Geocoder();

        // DB에서 가져온 주소를 사용하여 지도에 표시
        geocoder.addressSearch(dbAddress, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: coords
                });
                var infowindow = new kakao.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;padding:6px 0;">' + dbAddress + '</div>'
                });
                infowindow.open(map, marker);
                map.setCenter(coords);
            }
        });
		</script>

</body>
</html>
