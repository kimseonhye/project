<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="<c:url value='/resources/css/company/companyReserveForm.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<jsp:include page="../include/title.jsp" />
<body>
	<jsp:include page="../include/header.jsp" />
	<div class="container">
		<div class="box">
			<h2>고객 예약서</h2>
			<br>
			<c:forEach items="${Info }" var="info">
				<div>
					<span class="material-symbols-sharp">counter_1</span>
					<h3>고객 정보</h3>
				</div>
				<hr>
				<div id="consumer_info">
					<div class="area">
						<div class="name">이름</div>
						<div class="value">${info.user_name }</div>
					</div>
					<div class="area">
						<div class="name">연락처</div>
						<div class="value">${info.user_phone }</div>
					</div>
					<div class="area">
						<div class="name">이메일</div>
						<div class="value">${info.user_email }</div>
					</div>
					<div class="area">
						<div class="name">인원</div>
						<div class="value">${info.b_ppl }</div>
					</div>
					<div class="area">
						<div class="name">총 예약 금액</div>
						<div class="value">${info.room_price }</div>
					</div>
				</div>
				<div>
					<span class="material-symbols-sharp">counter_2</span>
					<h3>행사 정보</h3>
				</div>
				<hr>
				<div id="reserve_info">
					<div class="area">
						<div class="name">행사명</div>
						<div class="value">${info.b_name }</div>
					</div>
					<div class="area">
						<div class="name">행사 목적</div>
						<div class="value">${info.b_purpose }</div>
					</div>
				</div>
				<div>
					<span class="material-symbols-sharp">counter_3</span>
					<h3>기타 요청사항</h3>
				</div>
				<hr>
				<div>
					<div>${info.b_comment }</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="button-container">
		<button type="submit"
			onclick="location.href='/project/companyReservationList'">확인</button>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
