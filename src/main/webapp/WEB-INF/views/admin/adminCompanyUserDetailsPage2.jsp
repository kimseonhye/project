<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<link
	href="<c:url value='/resources/css/admin/adminUserDetailsPage.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<body>

	<jsp:include page="../include/header.jsp" />
	<div class="bodyMargin">
		<div id="container">
			<div id="u_h1">
				<h1>회원 아이디 ${userVo.user_id}</h1>
				<a class="btn btn-outline-danger" data-bs-toggle="modal"
					data-bs-target="#staticBackdrop1">회원 삭제</a>
			</div>
			<section id="section">
				<p>이름 - ${userVo.user_name}</p>
				<p>연락처 - ${userVo.user_phone}</p>
				<p>이메일 - ${userVo.user_email}</p>
			</section>
		</div>
		<hr>

		<div id="container1">

			<form action="<c:url value='./userDetailsPage' />">
				<input type="hidden" name="user_no" value="${userVo.user_no}" />
				<button class="uncheck-button">장소 내역</button>
			</form>
			<button class="check-button">예약 목록</button>

		</div>

		<section id="u_table">
			<p>total : ${CompanyBookingVos.size()}</p>
			<div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>장소명</th>
							<th>이용회원</th>
							<th>예약일</th>
							<th>이용날짜</th>
							<th>리뷰</th>
							<th>리뷰삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" />
						<c:forEach var="item" items="${CompanyBookingVos}">
							<tr>
								<td><c:out value="${count}" /></td>
								<c:set var="count" value="${count + 1}" />
								<td><a
									href="<c:url value='./adminCompanyPlaceConfirm?room_no=${item.room_no}' />">${item.room_name}</a></td>
								<td><a
									href="<c:url value='./selectOneUserToID?user_id=${item.user_id}' />">${item.user_id}</a></td>
								<td>${item.b_date}</td>
								<td>${item.b_checkin_date}~${item.b_checkout_date}</td>
								<td>${item.review_content}</td>
								<td><a class="btn btn-outline-danger review-delete-button"
									" data-bs-toggle="modal" data-bs-target="#staticBackdrop2"
									data-review_content="${item.review_content}"
									data-review_no="${item.review_no}">삭제</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<jsp:include page="../admin/modal/adminModalUserDelete.jsp" />
	<jsp:include page="../admin/modal/adminModalUserReviewDelete.jsp" />

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var reviewButtons = document
					.querySelectorAll(".review-delete-button");

			reviewButtons.forEach(function(button) {
				var reviewContent = button.getAttribute("data-review_content");

				if (!reviewContent || reviewContent.trim() === "") {
					button.style.display = "none";
				}
			});
		});
	</script>
</body>
</html>