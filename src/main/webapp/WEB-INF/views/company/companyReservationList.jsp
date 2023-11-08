<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link
	href="<c:url value='/resources/css/company/companyReservationList.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<body>

	<jsp:include page="../include/header.jsp" />

	<div id="u_h1">
		<h1>예약 관리</h1>
	</div>
	<div class="container">
		<section id="u_select">
			<div class="dropdown">
				<a class="dropdown-toggle" role="button" aria-expanded="false">
					정렬 기준 <i class="fas fa-caret-down"></i>
				</a>

				<div class="dropdown-menu">
					<a href="#">날짜 순</a> <a href="#">이름 순</a>
				</div>
			</div>
		</section>

		<div class="search">
			<form>
				<input type="text" name="b_name" placeholder="아이디/이름"> <input
					type="button" value="search" onclick="">
			</form>
		</div>
	</div>

	<section id="u_table">
		<div>
			<table>
				<thead>
					<tr>
						<th>장소명</th>
						<th>회원명</th>
						<th>연락처</th>
						<th>예약일자</th>
						<th>예약서</th>
						<th>예약취소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${List }">
						<tr>
							<td>${item.room_name }</td>
							<td>${item.user_name }</td>
							<td>${item.user_phone }</td>
							<td>${item.start_date }</td>
							<td>
								<a id="checked_List" href="<c:url value='/companyReservationList/select/${item.booking_no }'/> ">
									<button class="approve-button">조회</button>
								</a>
							</td>
							<td>
								<a id="delete_List" href="<c:url value='/companyReservationList/delete/${item.booking_no }'/> ">
									<button class="delete-button">삭제</button>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>