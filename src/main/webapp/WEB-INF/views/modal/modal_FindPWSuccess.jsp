<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/modal/modal_FindIDAndPWFail.css' />"
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
		data-bs-target="#staticBackdrop">Launch static backdrop modal
	</button>

	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">
						<b>아이디/비밀번호 찾기</b>
					</h1>
				</div>
				<div class="modal-body">
					<p>고객님의 비밀번호가 등록하신</p>
					<p>이메일로 발송되었습니다.</p>
				</div>
				<div class="modal-footer">
					<div class="d-grid gap-2 col-10 mx-auto">
						<button class="btn btn-dark" type="button" data-bs-dismiss="modal"
							aria-label="Close">닫기</button>
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
				.getElementById('staticBackdrop'));
	</script>
</body>
</html>