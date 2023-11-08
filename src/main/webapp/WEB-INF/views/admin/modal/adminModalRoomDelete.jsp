<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link
	href="<c:url value='/resources/css/admin/modal/adminModalRoomCancle.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop5" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">
						<b>삭제하시겠습니까?</b>
					</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<p id="modalRoomName"></p>
							<p for="message-text" class="col-form-label">삭제사유는 업체 이메일로
								발송됩니다.</p>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<div class="d-grid gap-2 col-10 mx-auto">
						<button id="RoomDelete-confirm-button" type="submit"
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
				.getElementById('staticBackdrop5'));

		// 모달이 나타날 때 이벤트를 감지하여 해당 위치에 room_name 값을 출력
		$('#staticBackdrop5').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); // 클릭한 버튼을 가져옵니다.
			var room_name = button.data('room_name'); // data-room_name 속성에서 room_name 값을 가져옵니다.
			var room_no = button.data('room_no'); // data-room_no 속성에서 room_no 값을 가져옵니다.
			var modal = $(this);

			// 모달 내부의 해당 위치에 room_name 값을 표시
			modal.find('#modalRoomName').text('장소명 : ' + room_name);

			// 모달 내부의 버튼에 room_no 값을 저장
			modal.find('#RoomDelete-confirm-button').data('room_no', room_no);

		});
		
	</script>

	<script>
		// 모달 내부의 삭제 버튼을 클릭했을 때 이벤트 처리
		$('#RoomDelete-confirm-button').on(
				'click',
				function() {
					var roomNo = $(this).data('room_no'); // data-room_no 속성에서 room_no 값을 가져옵니다.
					var deleteReason = $('#message-text').val(); // 삭제 이유를 가져옵니다.

					// 이제 roomNo를 사용하여 서버로 데이터를 전송하거나, 링크를 생성하여 컨트롤러로 이동할 수 있습니다.
					// 컨트롤러로 이동하는 링크를 생성하겠습니다.
					var link = '<c:url value="./roomDelete?room_no=' + roomNo
							+ '&deleteReason=' + deleteReason + '" />';

					// link 변수를 사용하여 컨트롤러로 이동하도록 설정
					window.location.href = link;
				});
		
	</script>

</body>
</html>