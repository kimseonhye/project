<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="<c:url value='/resources/css/company/companyMyPlace.css' />" rel="stylesheet" type="text/css">

<body>

	<jsp:include page="../include/header.jsp" />

	<h2>내 장소 목록</h2>
	<p class="sub-text">* 스케줄 관리를 원할 시 달력 아이콘을 클릭해 주세요. </p>
	<br>
	        
		<div class="container" id="confirm">
		    <c:forEach var="place" items="${myPlaces}">
		        <div class="square-container">
		            <div class="square">
						<img alt="office_room" src="${pageContext.request.contextPath}/roomUploadImg/${place.room_images}">
					</div>
				<div class="room-type">
		                <a href="<c:url value='./companyRoomDetail/${place.room_no}'/>">
		                    <c:choose>
		                        <c:when test="${place.room_no == 2}"></c:when>
		                        <c:when test="${place.room_no == 3}"></c:when>
		                    </c:choose>
		                    ${place.room_name}
		                </a>
		            </div>
		            <div class="sub-text">
		                <c:choose>
		                    <c:when test="${place.room_no == 2}"></c:when>
		                    <c:when test="${place.room_no == 3}"></c:when>
		                </c:choose>
		                ${place.room_regi_date}
		            </div>
		            <br>
		        </div>
		    </c:forEach>
		</div>

		<br>
		<br>
		
		<nav aria-label="Page navigation example">
	  <ul class="pagination">

	    <li class="page-item"><a class="page-link" href="#confirm">^</a></li>

	  </ul>
	</nav>
		<br>
		
		<hr>
		<br>
		<h2>승인 대기 목록</h2>
		<br>


		<div id="wait" class="my_place">
			<c:forEach var="item" items="${waitRooms}">
				<div class="square-container">
					<div class="square">
						<img alt="office_room"
							src="${pageContext.request.contextPath}/roomUploadImg/${roomDetail.room_images}">
					</div>
					<div class="room-type">${item.room_name }</div>
					<div class="sub-text">${item.room_regi_date }</div>
				</div>
			</c:forEach>
		</div>
		<br> <br>
		<!-- 
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#wait"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#wait">1</a></li>
				<li class="page-item"><a class="page-link" href="#wait">2</a></li>
				<li class="page-item"><a class="page-link" href="#wait">3</a></li>
				<li class="page-item"><a class="page-link" href="#wait"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		 -->
		<br>

		<jsp:include page="../include/footer.jsp" />
	</div>
</body>
</html>