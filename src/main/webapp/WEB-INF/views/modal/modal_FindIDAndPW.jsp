<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link
	href="<c:url value='/resources/css/modal/modal_FindIDAndPW.css' />"
	rel="stylesheet" type="text/css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

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
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<div class="modal-body text-center">

					<ul class="nav nav-tabs nav-fill justify-content-center" id="myTab"
						role="tablist">
						<li class="nav-item" role="presentation"><a
							class="nav-link active" id="id-tab" data-bs-toggle="tab"
							data-bs-target="#findId" href="#findId" role="tab"
							aria-controls="findId" aria-selected="true">아이디 찾기</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link"
							id="pwd-tab" data-bs-toggle="tab" data-bs-target="#findPwd"
							href="#findPwd" role="tab" aria-controls="findPwd"
							aria-selected="false">비밀번호 찾기</a></li>
					</ul>
					<div class="modal-body">
						<div class="tab-content" id="myTabContent">

							<div class="tab-pane fade show active" id="findId"
								role="tabpanel" aria-labelledby="id-tab">
								<p>
									회원가입 시 등록한 이메일로 <br> 아이디를 확인하실 수 있습니다.
								</p>
								<form class="modal-body-form">
									<div class="mb-3">
										<input type="text" name="textbox_email_pwd" id="textbox_email"
											placeholder="이메일을 @ 포함하여 입력해 주세요.">
										<button class="btn btn-dark" onclick="findUserId()"
											style="margin: 30px 0 0; width: 100px;">확인</button>
									</div>
								</form>
							</div>

							<div class="tab-pane fade" id="findPwd" role="tabpanel"
								aria-labelledby="pwd-tab">
								<p>
									회원가입 시 등록한 아이디와 이메일로 <br> 비밀번호를 확인하실 수 있습니다.
								</p>
								<form class="modal-body-form">
									<div class="mb-3">
										<input type="text" name="textbox_id_pwd" id="textbox_id"
											placeholder="아이디를 입력해 주세요."> <input type="text"
											name="textbox_email_pwd" id="textbox_email2"
											placeholder="이메일을 입력해 주세요.">
										<button class="btn btn-dark" onclick="findUserPassword()"
											style="margin: 30px 0 0; width: 100px;">확인</button>
									</div>
								</form>
							</div>
						</div>
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
	<script>
		function findUserId() {
			var user_email = document.getElementById('textbox_email').value;

			// 서버로 사용자의 이메일을 기반으로 아이디 찾기 요청을 보냅니다
			$.ajax({
				type : "POST",
				url : "<c:url value='/user/findUserId' />",
				async : false,
				data : {
					user_email : user_email
				},
				success : function(result) {
					if (result == "") {
						alert("아이디를 찾을 수 없습니다.");
					} else {
						alert("회원님의 아이디는 " + result + " 입니다.");
					}
				},
				error : function(xhr, status, error) {
					alert("서버 오류로 인해 아이디 찾기에 실패했습니다. 오류: " + error);
				}
			});
		}

		// 사용자 비밀번호 찾기
		function findUserPassword() {
			var user_id = document.getElementById('textbox_id').value;
			var user_email = document.getElementById('textbox_email2').value;

			// 서버로 사용자의 아이디와 이메일을 기반으로 비밀번호 찾기 요청을 보냅니다
			$.ajax({
				type : "POST",
				url : "<c:url value='/user/findUserPassword' />",
				async : false,
				data : {
					user_id : user_id,
					user_email : user_email
				},
				success : function(result) {
					if (result == "") {
						alert("입력한 정보와 일치하는 계정을 찾을 수 없습니다.");
					} else {
						alert("임시 비밀번호는 가입 시 등록된 이메일로 발송되었습니다. ");
					}
				},
				error : function(xhr, status, error) {
					alert("서버 오류로 인해 비밀번호 찾기에 실패했습니다. 오류: " + error);
				}
			});
		}
	</script>
</body>
</html>