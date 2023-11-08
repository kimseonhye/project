<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link
	href="<c:url value='/resources/css/admin/modal/adminModalUserDelete.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">
						<b>리뷰 삭제</b>
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p id="modalReviewContent"></p>
					<p>해당 리뷰를 정말 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<div id="modal-button" class="d-grid gap-2 col-10 mx-auto">
						<button id="reviewDelete-confirm-button" type="submit"
							class="btn btn-outline-danger">삭제</button>
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
		var myModal = new bootstrap.Modal(document
				.getElementById('staticBackdrop2'));

		// 모달이 나타날 때 이벤트를 감지하여 해당 위치에 review_contente 값을 출력
		$('#staticBackdrop2').on(
				'show.bs.modal',
				function(event) {
					var button = $(event.relatedTarget); // 클릭한 버튼을 가져옵니다.
					var review_content = button.data('review_content'); // data-roomname 속성에서 review_content 값을 가져옵니다.
					var review_no = button.data('review_no');
					var modal = $(this);

					// 모달 내부의 해당 위치에 review_content 값을 표시
					modal.find('#modalReviewContent').text(
							'리뷰 내용 : ' + review_content);

					// 모달 내부의 버튼에 room_no 값을 저장
					modal.find('#reviewDelete-confirm-button').data('review_no',
							review_no);

				});
	</script>

	<script>
		// 모달 내부의 삭제 버튼을 클릭했을 때 이벤트 처리
		$('#reviewDelete-confirm-button').on(
				'click',
				function() {
					var reviewno = $(this).data('review_no'); // data-room_no 속성에서 reviewno 값을 가져옵니다.

					// 이제 roomNo를 사용하여 서버로 데이터를 전송하거나, 링크를 생성하여 컨트롤러로 이동할 수 있습니다.
					// 컨트롤러로 이동하는 링크를 생성하겠습니다.
					var link = '<c:url value="./reviewDelete?review_no='
							+ reviewno + '" />';

					// link 변수를 사용하여 컨트롤러로 이동하도록 설정
					window.location.href = link;
				});
	</script>
</body>
</html>