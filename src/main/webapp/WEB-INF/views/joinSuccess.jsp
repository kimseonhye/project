<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 성공</title>

<link href="<c:url value='/resources/css/main/joinSuccess.css' />"
	rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="./include/header.jsp" />
		<div class="container">
			<div class="content">
				<h1>회원가입이 완료되었습니다</h1>
				<p>"성공적인 비즈니스를 위한 최적의 장소를 만나보세요"</p>
				<a href="loginPage" class="button">로그인</a>
			</div>
		</div>
	
	<jsp:include page="./include/footer.jsp" />
</body>
</html>