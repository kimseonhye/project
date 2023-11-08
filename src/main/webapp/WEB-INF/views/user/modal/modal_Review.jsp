<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link
	href="<c:url value='/resources/css/user/modal/modal_Review.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>


	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#staticBackdrop">리뷰</button>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<h2 class="modal-title fs-5" id="staticBackdropLabel">
						<b>리뷰 작성</b>
					</h2>
					<p>별점 등록</p>
					<div class="stars">
						<span class="star" onclick="rateStar(1)">★</span>
						<span class="star" onclick="rateStar(2)">★</span>
						<span class="star" onclick="rateStar(3)">★</span>
						<span class="star" onclick="rateStar(4)">★</span>
						<span class="star" onclick="rateStar(5)">★</span>
					</div>
					<hr class="secede">


					<div class="modal-body text-center">
						<!-- Add a textarea for writing a review -->
						<div class="form-group">
							<textarea class="form-control" id="review" rows="4"></textarea>
						</div>
					</div>

					<div class="modal-body-button">
						<button type="button" class="btn btn-outline-dark"
							data-bs-dismiss="modal" aria-label="Close">&lt; 뒤로</button>
						<button id="deleteButton" type="button"
							class="btn btn-outline-danger">저장</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Include Bootstrap and jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>

	<!-- Initialize the modal -->
	<script>
		var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
	</script>
	 <script>
        let selectedRating = 0;

        function rateStar(star) {
            selectedRating = star;

            const stars = document.querySelectorAll('.star');
            stars.forEach((starElement, index) => {
                if (index < star) {
                    starElement.innerHTML = "★";
                } else {
                    starElement.innerHTML = "☆";
                }
            });

            document.getElementById('selectedRating').innerHTML = `You rated ${selectedRating} stars!`;
        }
    </script>

</body>
</html>