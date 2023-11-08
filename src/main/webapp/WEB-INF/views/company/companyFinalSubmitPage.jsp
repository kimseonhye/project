<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter.format(date);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 내부css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/company/companyFinalSubmitPage.css" type="text/css">
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
		<!-- 헤더 아래에 상세페이지 아이콘 -->
		<!-- 왼쪽 섹션 -->
		<div class="left-section">
			<!-- 호텔 이미지 슬라이더 -->
			<div class="gallery" id="gallery">
				<img alt="room image"
					src="${pageContext.request.contextPath}/roomUploadImg/${roomRegisterVO.room_images}">
			</div>

			<hr class="custom-hr">

			<!-- 최대 수용 인원을 감싸는 div -->
			<div class="max-occupancy">
				<div class="data-area">
					<span class="table-label">최대 수용 인원</span> <input type="text"
						id="maxCapacity" name="room_max_ppl"
						value="${roomRegisterVO.room_max_ppl}명" class="form-control"
						readonly />
				</div>
			</div>

			<hr class="custom-hr">

			<!-- 테이블 배치 체크박스 -->
			<span class="table-label">테이블 배치</span> <br>
			<div class="checkbox-group">

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="d-shaped" name="t_set1"
						class="form-check-input" ${roomRegisterVO.t_set1 ? 'checked' : ''}
						readonly /> <label for="d-shaped" class="form-check-label">
						ㄷ자형 <span class="material-icons">drag_indicator</span> <!--최대 30명-->
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="lecture" name="t_set2"
						class="form-check-input" ${roomRegisterVO.t_set2 ? 'checked' : ''}
						readonly /> <label for="lecture" class="form-check-label">
						강의식 <span class="material-icons">width_full</span> <!--최대 50명-->
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="banquet" name="t_set3"
						class="form-check-input" ${roomRegisterVO.t_set3 ? 'checked' : ''}
						readonly /> <label for="banquet" class="form-check-label">
						연회식 <span class="material-icons">wb_sunny</span> <!--최대 100명-->
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="theater" name="t_set4"
						class="form-check-input" ${roomRegisterVO.t_set4 ? 'checked' : ''}
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
						class="form-control custom-textarea" readonly>${roomRegisterVO.room_faq}
					</textarea>
				</div>
			</div>

			<hr class="custom-hr">

			<!-- 취소 환불 규정 -->
			<div class="mb-3 d-flex align-items-center">
				<label for="cancle" class="form-label me-3">취소 및 환불규정</label>
				<div class="custom-input-box">
					<textarea id="cancle" name="room_cancellation_and_refund_policy"
						class="form-control custom-textarea" readonly>${roomRegisterVO.room_cancellation_and_refund_policy}
					</textarea>
				</div>
			</div>

			<hr class="custom-hr">

			<!-- 리뷰 섹션 -->
			<div class="cancellation-policy">
				<div class="max-occupancy-text">리뷰</div>
				<div class="data-area">
					<p></p>
				</div>
			</div>

		</div>

		<!-- 오른쪽 섹션 -->
		<div class="right-section">


			<div class="hotel-info-box">
				<h3>${roomVO.user_name}</h3>
				<br>
				<p>${roomRegisterVO.room_name}</p>
				<p>${roomRegisterVO.room_address}</p>
				<p>${roomVO.user_phone}</p>
				<p><%= strDate %></p>
			</div>

			<div class="total-amount">
				<p class="total-amount-label">1인당 예약 금액</p>
				<p class="total-amount-value">${roomRegisterVO.room_price}KRW</p>
			</div>

			<button type="button" class="btn btn-dark" data-bs-toggle="modal"
				data-bs-target="#staticBackdrop" style="width: 100px;">등록</button>

		</div>
	</div>

	<jsp:include page="../include/footer.jsp" />

	<jsp:include page="modal/modal_RoomRegistration.jsp" />

	<script>


			// 1.클릭하면 적용되는 기능
			const checkboxes = document.querySelectorAll('input[value="${roomRegisterVO.t_set1}"],input[value="${roomRegisterVO.t_set2}"],input[value="${roomRegisterVO.t_set3}"],input[value="${roomRegisterVO.t_set4}"]');
														
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
			</script>

</body>
</html>
