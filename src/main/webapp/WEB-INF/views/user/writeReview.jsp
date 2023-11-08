
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/user/writeReview.css">
</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<h1>리뷰</h1>

	<div class="container">

		<!-- Add new review -->
		<h2>Add a Review</h2>
		<form action="<c:url value='./reviewSubmit' />" method="post">
			<input type="hidden" name="booking_no" value="${item.booking_no}" />
			<!-- <label for="review_title">Review Title:</label> -->
			<p>Review Title:</p>
			<input type="text" id="review_title" name="review_title" required><br>
			<br>

			<!-- <label for="rating">Rating:</label> -->
			<p>Rating:</p>
			<input type="number" id="review_star" name="review_star" min="1"
				max="5" step="0.1" required><br> <br>

			<!-- <label for="review_content">Review:</label><br> -->
			<p>Review:</p>
			<textarea id="review_content" name="review_content" rows="4"
				cols="50" required></textarea>
			<br> <br> <input type="submit" id="submitBtn"
				value="Submit Review">
		</form>
	</div>

	<jsp:include page="../include/footer.jsp" />


</body>
</html>
