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

			<button class="check-button">장소 내역</button>
			<form action="<c:url value='./companyuserDetailsPage2' />">
				<input type="hidden" name="user_no" value="${userVo.user_no}" />
				<button class="uncheck-button">예약 목록</button>
			</form>

		</div>

		<section id="u_table">
			<p>total : ${CompanyUserRoomVos.size()}</p>
			<div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>장소명</th>
							<th>등록일자</th>
							<th>주소</th>
							<th>장소 관리</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" />
						<c:forEach var="item" items="${CompanyUserRoomVos}">
							<tr>
								<td><c:out value="${count}" /></td>
								<c:set var="count" value="${count + 1}" />
								<td><a
									href="<c:url value='./adminCompanyPlaceConfirm?room_no=${item.room_no}' />">${item.room_name}</a></td>
								<td>${item.room_regi_date}</td>
								<td>${item.room_address}</td>
								<td><c:choose>
										<c:when
											test="${item.room_status == 2 or item.room_status == 3}">
											<a class="btn btn-outline-success"
												href="<c:url value='./adminCompanyPlaceConfirm?room_no=${item.room_no}' />">확인</a>
											<a class="btn btn-outline-danger" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop5"
												data-room_name="${item.room_name}"
												data-room_no="${item.room_no}">삭제</a>
										</c:when>
										<c:when test="${item.room_status == 1}">
											<a class="btn btn-outline-success" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop3"
												data-room_name="${item.room_name}"
												data-room_no="${item.room_no}">승인</a>
											<a class="btn btn-outline-danger" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop4"
												data-room_name="${item.room_name}"
												data-room_no="${item.room_no}">반려</a>
										</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<jsp:include page="../admin/modal/adminModalUserDelete.jsp" />
	<jsp:include page="../admin/modal/adminModalRoomDelete.jsp" />
	<jsp:include page="../admin/modal/adminModalRoomApproval.jsp" />
	<jsp:include page="../admin/modal/adminModalRoomCancle.jsp" />
	<!-- Initialize the modal -->
	<script>
		// 모달이 나타날 때 이벤트를 감지하여 해당 위치에 room_name 값을 출력
		$('#staticBackdrop3, #staticBackdrop4, #staticBackdrop5')
				.on(
						'show.bs.modal',
						function(event) {
							var button = $(event.relatedTarget); // 클릭한 버튼을 가져옵니다.
							var room_name = button.data('room_name'); // data-room_name 속성에서 room_name 값을 가져옵니다.
							var room_no = button.data('room_no'); // data-room_no 속성에서 room_no 값을 가져옵니다.
							var modal = $(this);

							// 모달 내부의 해당 위치에 room_name 값을 표시
							modal.find('#modalRoomName').text(
									'장소명 : ' + room_name);

							// 모달 내부의 버튼에 room_no 값을 저장
							modal
									.find(
											'#approval-confirm-button, #cancle-confirm-button, #RoomDelete-confirm-button')
									.data('room_no', room_no);
						});
	</script>
</body>
</html>