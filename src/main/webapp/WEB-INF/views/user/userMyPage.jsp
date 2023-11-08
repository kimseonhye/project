<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link href="<c:url value='/resources/css/user/userMyPage.css' />"
    rel="stylesheet" type="text/css">
<body>
<jsp:include page="../include/header.jsp" />
    <div class="wrapper">
    <div class="sub_title">
        <h2>마이 페이지</h2>
	</div>
        <div class="container">
            <div class="box">
                <a href="<c:url value='./userMyInfo' />">
                    <span class="material-symbols-outlined">badge</span>
                    <div>
                        <p>개인 정보</p>
                        <p class="sub_text">조회 / 수정 / 탈퇴</p>
                    </div>
                </a>
            </div>
            <div class="box">
                <a href="<c:url value='./userReserveList' />">
                    <span class="material-symbols-outlined">fact_check</span>
                    <div>
                        <p>이용내역 조회</p>
                        <p class="sub_text">관심 장소 / 예약 리스트</p>
                    </div>
                </a>
            </div>
        </div>
    </div>
<jsp:include page="../include/footer.jsp" />
</body>
</html>