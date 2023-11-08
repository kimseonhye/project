<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    
    <jsp:include page="../include/title.jsp" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- css링크 -->
    <!-- 내부css -->
    <link href="${pageContext.request.contextPath}/resources/css/company/companyRoomRegisterForm.css" rel="stylesheet" type="text/css">
    <!-- 외부 css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>
	
	<body>
	

	<jsp:include page="../include/header.jsp" />
	<!-- 하단에 푸터를 고정시키기 위해 wrapper클래스 속성 만들어주기 -->
	<div class="wrapper">

	<div class="container">
	
    <h1>내 장소 등록</h1>
    
  		<form action="<c:url value='./room/companyRoomRegisterFormConfirm' />" name="companyRoomRegisterForm" method="post" enctype="multipart/form-data">
		
		<!--숫자 1 아이콘 불러오기 -->
		<div class="icon-text-container">
		    <span class="material-symbols-sharp">counter_1</span>
		    <span class="room-image-text">룸 이미지<span class="required-field">필수입력*</span></span>
		</div>
        <hr>
        
		<!-- 이미지 첨부 만들기 -->
		<div class="upload-container">
		    <div class="upload-box">
		        <label for="room_images" class="drag-file">
		            <img src="https://img.icons8.com/pulsar-line/3x/image-file.png" alt="파일 아이콘" class="image">
		            <button type="button" class="upload-button" onclick="document.getElementById('fileUpload1').click();">사진 첨부</button>
		        </label>
		        <input id="fileUpload1" name="room_images1" class="file" type="file" style="display: none;" accept="image/*" onchange="displayPreview(this);">
		        <img id="preview1" src="#" alt="미리보기" class="preview-image">
		    </div>
		</div>

		<!-- 장소 정보 -->
		<!--숫자 2 아이콘 불러오기 -->
		<div class="icon-text-container">
		    <span class="material-symbols-sharp">counter_2</span>
		    <span class="room-image-text">장소정보 <span class="required-field">필수입력*</span></span>
		</div>
		<hr>

        
		<!-- 등록될 정보들 입력-->
		<div class="mb-3 d-flex align-items-center"  >
		    <label for="hotelName" class="form-label me-3">호텔 이름</label>
		    <input type="text" id="hotelName" name="user_name" class="form-control" placeholder=" *등록하신 장소가 자동으로 입력됩니다" style="margin-left: 80px;" readonly/>
		</div>
		
        <div class="mb-3 d-flex align-items-center">
            <label for="roomName" class="form-label me-3">룸 이름</label>
            <input type="text" id="roomName" name="room_name" class="form-control" style="margin-left: 100px;" />
        </div>
        
	     <!-- 우편번호 검색은 API가 필요하다 -->   
	    <div class="mb-3 d-flex align-items-center">
	        <label for="address" class="form-label me-3">주소</label>
	        <input type="text" id="addressInput" name="room_address" class="form-control" style="margin-left: 120px;" />
	        <button type="button" id="searchAddress" class="my-custom-button" style="margin-left: 30px;">우편번호 검색</button>
	    </div>
	    


	    <!-- 다음 우편번호 검색 API 스크립트 -->
	     <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

        
        
				
		<div class="mb-3 d-flex align-items-center">
		    <label for="phoneNumber" class="form-label me-3">호텔 연락처</label>
		    
		    <div class="input-container">
		        <input type="tel" id="phoneNumberMiddle" name="user_phone" class="form-control" placeholder=" *등록하신 번호가 자동으로 입력됩니다" style="margin-left: 65px;" readonly/>
		    </div>
		</div>

		<div class="mb-3 d-flex align-items-center">
		    <label for="price" class="form-label me-3">1인당 가격</label>
		    <div class="input-container">
		        <input type="text" id="price" name="room_price" class="form-control" style="margin-left: 125px;"/>
		        <span>원</span>
		    </div>
		</div>
		
		<div class="mb-3 d-flex align-items-center">
            <label for="maxCapacity" class="form-label me-3">최대 수용 인원</label>
            <div class="input-container">
           		<input type="text" id="maxCapacity" name="room_max_ppl" class="form-control"  style="margin-left: 85px; margin-right: 15px;" />
           		<span>명</span>
            </div>
        </div>
		
		<!-- 테이블 배치 체크박스 -->
		<hr>
    <div class="mb-3 d-flex align-items-center checkbox-group" >
        <label for="tableArrangement" class="form-label me-3">테이블 배치</label>

         <div class="form-check form-check-inline custom-checkbox" style="margin-left: 60px;">
            <input type="checkbox" id="d-shaped" name="t_set1" value="true" class="form-check-input" />
            <label for="d-shaped" class="form-check-label">
                ㄷ자형
                <span class="material-icons">drag_indicator</span>
                <!--최대 30명-->
            </label>
        </div>
        
        <div class="form-check form-check-inline custom-checkbox">
            <input type="checkbox" id="lecture" name="t_set2" value="true" class="form-check-input" />
            <label for="lecture" class="form-check-label">
                강의식
                <span class="material-icons">width_full</span>
                <!--최대 50명-->
            </label>
        </div>
        
        <div class="form-check form-check-inline custom-checkbox">
            <input type="checkbox" id="banquet" name="t_set3" value="true" class="form-check-input" />
            <label for="banquet" class="form-check-label">
                연회식
                <span class="material-icons">wb_sunny</span>
                <!--최대 100명-->
            </label>
        </div>

        <div class="form-check form-check-inline custom-checkbox">
            <input type="checkbox" id="theater" name="t_set4" value="true" class="form-check-input" />
            <label for="theater" class="form-check-label">
                극장식
                <span class="material-icons">theater_comedy</span>
                <!--최대 150명-->
            </label>
        </div>
    </div>
    
		<p style="font-family: 'sans-serif'; color: #888; font-size: 14px; margin-top: 20px; margin-left: 165px;">*제공 가능한 테이블 배치를 선택하세요</p>
		<hr><br>
	
		<!-- FAQ -->
		<div class="mb-3 d-flex align-items-center">
		    <label for="faq" class="form-label me-3">FAQ</label>
		    <div class="custom-input-box" style="margin-left: 92px;">
		        <textarea id="faq" name="room_faq" rows="4" cols="40" class="form-control custom-textarea"></textarea>
		    </div>
		</div>
		
		
		<div class="mb-3 d-flex align-items-center">
		    <label for="cancle" class="form-label me-3">취소 및 환불규정</label>
		    <div class="custom-input-box" >
		        <textarea id="cancle" name="room_cancellation_and_refund_policy" rows="4" cols="40" class="form-control custom-textarea"></textarea>
		    </div>
		</div>

        
        <!-- 취소 및 등록 -->
		<div class="mb-3 text-center">
		    <input type="button" class="custom-button custom-button-cancel" onclick="goBack()" value="취소">
		    
		    <input type="submit" class="custom-button custom-button-submit" value="등록">
		    
		</div>
		        
	    </form>
		</div>

		</div>
		
		<jsp:include page="../include/footer.jsp" />
	
	    <script>
        // 1.JavaScript 코드를 작성하여 클릭 이벤트 처리
        const checkboxes = document.querySelectorAll('input[value="true"]');

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function () {
                const label = this.nextElementSibling;

                if (this.checked) {
                    // 선택한 경우 배경 색상 변경
                    label.classList.add('selected');
                } else {
                    // 선택을 취소한 경우 원래 색상으로 복원
                    label.classList.remove('selected');
                }
            });
        });
        
        //2.사진 미리보기
		function displayPreview(input) {
		    const preview = document.getElementById('preview1');
		
		    if (input.files && input.files[0]) {
		        const reader = new FileReader();
		
		        reader.onload = function (e) {
		            preview.src = e.target.result;
		            preview.style.display = 'block';
		        };
		
		        reader.readAsDataURL(input.files[0]);
		    }
		}

		// 3.다음 우편번호 검색 API 스크립트가 로드된 이후에 실행되도록 이벤트 핸들러 설정
		document.addEventListener("DOMContentLoaded", function () {
		    // "searchAddress" 버튼 클릭 시 주소 검색 팝업 열기
		    document.getElementById("searchAddress").addEventListener("click", function () {
		        new daum.Postcode({
		            oncomplete: function (data) {
		                // 선택한 주소 정보를 입력 필드에 채우기
		                document.getElementById("addressInput").value = data.roadAddress;
		            }
		        }).open();
		    });
		});

	    // 4.취소 버튼 누르면 브라우저의 이전 페이지로 이동
		function goBack() {
			window.history.back();
		}
    </script>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
