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
	href="../resources/css/admin/adminCompanyPlaceConfirm.css"
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
		<div class="text">장소 상세 페이지</div>
	</div>

	<div class="wrapper">
		<!-- 헤더 아래에 상세페이지 아이콘 -->
		<!-- 왼쪽 섹션 -->
		<div class="left-section">
			<!-- 호텔 이미지 슬라이더 -->
			<div class="gallery" id="gallery">
				<img src="${pageContext.request.contextPath}/roomUploadImg/${roomVo.room_images}" />
			</div>


			<hr class="custom-hr">
			<!-- 최대 수용 인원을 감싸는 div -->
			<div class="cancellation-policy">
				<div class="max-occupancy-text">최대 수용 인원</div>
				<div class="data-area">
					<p>${roomVo.room_max_ppl}명까지 수용가능합니다.</p>
				</div>
			</div>

			<hr class="custom-hr">

			<!-- 테이블 배치 체크박스 -->
			<span class="table-label">테이블 배치</span> <br>
			<div class="checkbox-group">

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="d-shaped" name="tableArrangement"
						value="ㄷ자형" class="form-check-input" /> <label for="d-shaped"
						class="form-check-label"> ㄷ자형 <span class="material-icons">drag_indicator</span>
						최대 30명
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="lecture" name="tableArrangement"
						value="강의식" class="form-check-input" /> <label for="lecture"
						class="form-check-label"> 강의식 <span class="material-icons">width_full</span>
						최대 50명
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="banquet" name="tableArrangement"
						value="연회식" class="form-check-input" /> <label for="banquet"
						class="form-check-label"> 연회식 <span class="material-icons">wb_sunny</span>
						최대 100명
					</label>
				</div>

				<div class="form-check form-check-inline custom-checkbox">
					<input type="checkbox" id="theater" name="tableArrangement"
						value="극장식" class="form-check-input" /> <label for="theater"
						class="form-check-label"> 극장식 <span class="material-icons">theater_comedy</span>
						최대 150명
					</label>
				</div>

			</div>

			<hr class="custom-hr">

			<!-- FAQ 섹션 -->
			<div class="cancellation-policy">
				<div class="max-occupancy-text">FAQ</div>
				<div class="data-area">
					<p>${roomVo.room_faq}</p>
				</div>
			</div>

			<hr class="custom-hr">

			<!-- 취소 환불 규정 -->
			<div class="cancellation-policy">
				<div class="max-occupancy-text">취소 환불 규정</div>
				<div class="data-area">
					<p>${roomVo.room_cancellation_and_refund_policy}</p>
				</div>
			</div>

			<hr class="custom-hr">
			<!-- 리뷰 섹션 -->
			<div class="cancellation-policy">
				<div class="max-occupancy-text">리뷰</div>
				<div class="data-area">
					<p>
						<c:forEach var="item" items="${reviewVos}">
						${item.review_star} | ${item.review_date} <a
								href="<c:url value='./userDetailsPage?user_no=${item.user_no}' />">${item.user_id}</a>
							<c:choose>
								<c:when test="${user_id eq item.user_id}">
									<a id="reviewdeleteModal" class="btn btn-outline-danger"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop2"
										data-review_content="${item.review_content}"
										data-review_no="${item.review_no}">리뷰 삭제</a>
								</c:when>
							</c:choose>
							<br> ${item.review_content}<br>
							<br>
						</c:forEach>
					</p>

				</div>
			</div>

		</div>

		<!-- 오른쪽 섹션 -->
		<div class="right-section">

			<div class="hotel-info-box">
				<h3>${roomVo.user_name}</h3>
				<br>
				<p>${roomVo.room_name}</p>
				<p>${roomVo.room_address}</p>
				<p>${roomVo.user_phone}</p>
			</div>
			<div class="total-amount">
				<p class="total-amount-label">총 예약 금액</p>
				<p class="total-amount-value">${roomVo.room_price}KRW</p>
			</div>

			<div class="buttons">
				<a class="btn btn-outline-success" onclick="goBack()">확인</a>
			</div>

		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script>
		<c:choose>
		<c:when test="${tableSetVo.t_set1 eq true}">
		// t_set1 값이 true일 때의 동작
		document.querySelector('#d-shaped').parentElement.style.display = 'block';
		</c:when>
		<c:otherwise>
		// t_set1 값이 false일 때의 동작
		document.querySelector('#d-shaped').parentElement.style.display = 'none';
		</c:otherwise>
		</c:choose>

		<c:choose>
		<c:when test="${tableSetVo.t_set2 eq true}">
		// t_set2 값이 true일 때의 동작
		document.querySelector('#lecture').parentElement.style.display = 'block';
		</c:when>
		<c:otherwise>
		// t_set2 값이 false일 때의 동작
		document.querySelector('#lecture').parentElement.style.display = 'none';
		</c:otherwise>
		</c:choose>

		<c:choose>
		<c:when test="${tableSetVo.t_set3 eq true}">
		// t_set3 값이 true일 때의 동작
		document.querySelector('#banquet').parentElement.style.display = 'block';
		</c:when>
		<c:otherwise>
		// t_set3 값이 false일 때의 동작
		document.querySelector('#banquet').parentElement.style.display = 'none';
		</c:otherwise>
		</c:choose>

		<c:choose>
		<c:when test="${tableSetVo.t_set4 eq true}">
		// t_set4 값이 true일 때의 동작
		document.querySelector('#theater').parentElement.style.display = 'block';
		</c:when>
		<c:otherwise>
		// t_set4 값이 false일 때의 동작
		document.querySelector('#theater').parentElement.style.display = 'none';
		</c:otherwise>
		</c:choose>
	</script>
	<script>
		function goBack() {
			window.history.back(); // 이전 페이지로 이동
		}
	</script>
	<jsp:include page="../admin/modal/adminModalUserReviewDelete.jsp" />
</body>

</html>