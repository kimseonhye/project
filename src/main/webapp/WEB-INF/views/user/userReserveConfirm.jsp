<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="<c:url value='/resources/css/user/userReserveConfirm.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<jsp:include page="../include/title.jsp" />
<body>
	<jsp:include page="../include/header.jsp" />
	<div class="container">
		<div class="box">
			<h2>예약이 완료되었습니다.</h2>
			<p class="sub-text">예약 확정 여부는 입력하신 이메일로 발송됩니다.</p>
			<input type="hidden" name="booking_no" value="${bookingVO.booking_no}" />
			<!--<input type="hidden" name="booking_no" value="1" /> --> <br>
			<div>
				<span class="material-symbols-sharp">counter_1</span>
				<h3>고객 정보</h3>
			</div>
			<hr class="line">
			<div id="list">
				<p>이름</p>
				<span><c:out value="${bookingVO.b_user_name}" /></span>
			</div>
			<div id="list">
				<p>연락처</p>
				<span><c:out value="${bookingVO.b_user_contact}" /></span>
			</div>
			<div id="list">
				<p>이메일</p>
				<span><c:out value="${bookingVO.b_user_email}" /></span>
			</div>
			<div id="list">
				<p>회사명</p>
				<span><c:out value="${bookingVO.b_company}" /></span>
			</div>
			<div id="list">
				<p>인원</p>
				<span><c:out value="${bookingVO.b_ppl}" /></span>
			</div>
			<div id="list">
				<p>총 예약 금액</p>
				<span><c:out value="${roomVO.room_price}" /></span>
			</div>
			<div>
				<span class="material-symbols-sharp">counter_2</span>
				<h3>행사 정보</h3>
			</div>
			<hr class="line">
			<div id="list">
				<p>행사명</p>
				<span><c:out value="${bookingVO.b_name}" /></span>
			</div>
			<div id="list">
				<p>행사 목적</p>
				<span><c:out value="${bookingVO.b_purpose}" /></span>
			</div>
			<div id="list">
				<p>테이블 배치</p>
				<span><c:out value="${bookingVO.b_table_set}" /></span>
			</div>
			<div>
				<span class="material-symbols-sharp">counter_3</span>
				<h3>기타 요청사항</h3>
			</div>
			<hr class="line">
			<div id="list">
				<p>
					<c:out value="${bookingVO.b_comment}" />
				</p>
			</div>
		</div>
	</div>
	<div class="button-container">
		<input type="button" class="button" value="확인" onclick="confirm()">
	</div>

	<script>
		function confirm() {

			var mainPageURL = "../";
			window.location.href = mainPageURL;
		}
	</script>
	<jsp:include page="../include/footer.jsp" />
</body>
