<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<link
	href="<c:url value='/resources/css/admin/adminUserDetailsPage.css'/>"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<body>

	<jsp:include page="../include/header.jsp" />


	<div class="bodyMargin">

		<div id="u_h1">
			<h1>리뷰 내역</h1>
		</div>
		<div id="container1">
			<form action="<c:url value='./userReserveList' />">
				<button class="uncheck-button">예약리스트</button>
			</form>
			<button class="check-button">리뷰목록</button>


		</div>

		<section id="u_table">
			<div>
				<table>
					<thead>
						<tr>
							<th>리뷰번호</th>
							<th>장소명</th>
							<th>이용날짜</th>
							<th>리뷰</th>
							<th>리뷰 삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ReviewVos}" var="review">

							<tr>

								<td>${review.review_no}</td>
								<td>${review.room_name}</td>
								<td>${review.review_date}</td>
								<td>${review.review_content}</td>

								<td><a
									href="<c:url value ='./reviewDelete?review_no=${review.review_no}'/>"
									class="delete-button2">삭제</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</section>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>