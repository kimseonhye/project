<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<link
	href="<c:url value='/resources/css/admin/adminRoomManagementPage.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<body>

	<jsp:include page="../include/header.jsp" />
	<div class="bodyMargin">
		<div id="u_h1">
			<h1>장소 관리</h1>
		</div>
		<div id="container1">
			<form action="<c:url value='./selectRoomStatus3' />">
				<button class="uncheck-button">승인</button>
			</form>
			<button class="check-button">대기</button>
			<form action="<c:url value='./selectRoomStatus2' />">
				<button class="uncheck-button">반려</button>
			</form>
		</div>

		<div class="container2">

			<section id="u_select">
				<div class="dropdown">
					<button class="btn btn-outline-secondary dropdown-toggle"
						type="button" data-bs-toggle="dropdown" aria-expanded="false">
						정렬 기준</button>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='./roomSelect1Status1' />">가나다순</a></li>
						<li><a href="<c:url value='./roomSelect2Status1' />">최신순</a></li>
						<li><a href="<c:url value='./roomSelect3Status1' />">오래된순</a></li>
					</ul>
				</div>
			</section>

			<div class="search">
				<form action="<c:url value='./roomSearch1' />" method="get">
					<input type="text" name="room_name" placeholder="장소 이름"> <input
						class="btn btn-outline-secondary" type="submit" value="search">
				</form>
			</div>
		</div>

		<section id="u_table">
			<p>total : ${roomVos.size()}</p>
			<div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>업체명</th>
							<th>장소명</th>
							<th>주소</th>
							<th>가격</th>
							<th>등록일</th>
							<th>승인</th>
							<th>반려</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" />
						<c:forEach var="item" items="${roomVos}">
							<tr>
								<td><c:out value="${count}" /></td>
								<c:set var="count" value="${count + 1}" />
								<td><a
									href="<c:url value='./userDetailsPage?user_no=${item.user_no}' />">${item.user_name}</a></td>
								<td><a
									href="<c:url value='./adminCompanyPlaceConfirm?room_no=${item.room_no}' />">${item.room_name}</a></td>
								<td>${item.room_address}</td>
								<td>${item.room_price}</td>
								<td>${item.room_regi_date}</td>
								<td><a class="btn btn-outline-success"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop3"
									data-room_name="${item.room_name}"
									data-room_no="${item.room_no}">승인</a></td>
								<td><a class="btn btn-outline-danger"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop4"
									data-room_name="${item.room_name}"
									data-room_no="${item.room_no}">반려</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<jsp:include page="../admin/modal/adminModalRoomApproval.jsp" />
	<jsp:include page="../admin/modal/adminModalRoomCancle.jsp" />
	<!-- Initialize the modal -->
	<script>
		// 모달이 나타날 때 이벤트를 감지하여 해당 위치에 room_name 값을 출력
		$('#staticBackdrop3, #staticBackdrop4').on(
				'show.bs.modal',
				function(event) {
					var button = $(event.relatedTarget); // 클릭한 버튼을 가져옵니다.
					var room_name = button.data('room_name'); // data-room_name 속성에서 room_name 값을 가져옵니다.
					var room_no = button.data('room_no'); // data-room_no 속성에서 room_no 값을 가져옵니다.
					var modal = $(this);

					// 모달 내부의 해당 위치에 room_name 값을 표시
					modal.find('#modalRoomName').text('장소명 : ' + room_name);

					// 모달 내부의 버튼에 room_no 값을 저장
					modal.find(
							'#approval-confirm-button, #cancle-confirm-button')
							.data('room_no', room_no);
				});
	</script>
</body>
</html>