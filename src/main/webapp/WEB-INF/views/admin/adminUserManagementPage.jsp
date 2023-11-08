<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<link
	href="<c:url value='/resources/css/admin/adminUserManagementPage.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<body>
	<jsp:include page="../include/header.jsp" />
	<div class="bodyMargin">

		<h1>회원 관리</h1>

		<div class="selectAndSearchContainer">
			<section id="u_select">
				<div class="dropdown">
					<button class="btn btn-outline-secondary dropdown-toggle"
						type="button" data-bs-toggle="dropdown" aria-expanded="false">
						회원 분류</button>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='./companyUserManagement' />">기업
								회원</a></li>
						<li><a href="<c:url value='./userManagement' />">개인 회원</a></li>
					</ul>
				</div>
			</section>

			<div class="search">
				<form action="<c:url value='./userSearch' />" method="get">
					<input type="text" name="user_id" placeholder="아이디"> <input
						class="btn btn-outline-secondary" type="submit" value="search">
				</form>
			</div>
		</div>
		<section id="u_table">
			<p>total : ${userVos.size()}</p>
			<div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>연락처</th>
							<th>회원 등급</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" />
						<c:forEach var="item" items="${userVos}">
							<tr>
								<td><c:out value="${count}" /></td>
								<c:set var="count" value="${count + 1}" />
								<td><a
									href="<c:url value='./userDetailsPage?user_no=${item.user_no}' />">${item.user_id}</a></td>
								<td>${item.user_name}</td>
								<td>${item.user_phone}</td>
								<td><c:choose>
										<c:when test="${item.user_rank == 1}">
											<c:out value="개인" />
										</c:when>
										<c:when test="${item.user_rank == 2}">
											<c:out value="기업" />
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
</body>
</html>