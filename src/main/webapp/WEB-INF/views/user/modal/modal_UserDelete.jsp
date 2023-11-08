<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="<c:url value='/resources/css/user/modal/modal_UserDelete.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<body>

	<h2>탈퇴하시겠습니까?</h2>
	<p class="sub-text">jangan20190906rhsrhs@gmail.com</p>
	<br>
	<div>
		<span class="material-symbols-outlined"> check_circle </span>
		<p>이 계정과 관련된 정보가 삭제됩니다.</p>
	</div>
	<div>
		<span class="material-symbols-outlined">check_circle</span>
		<p>향후 계정 정보나 이전 예약을 확인할 수 없습니다.</p>
	</div>
	<div class="go-back"
		onclick="window.location.href='https://www.naver.com';">
		<span class="material-symbols-outlined"> chevron_left </span>
		<p>뒤로</p>
		<button class="delete" type="submit">탈퇴하기</button>
	</div>
</body>
</html>