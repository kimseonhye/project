<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>이용내역조회</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/user/userReserveList.css">
</head>
<body>

	<jsp:include page="../include/header.jsp" />
	<div class="bodyMargin">
		<div class="wrapper">

			<h1>이용내역조회</h1>
			<br>
			<div id="menu-container">
				<p id="check" class="menu-item">예약리스트</p>
				<a href="<c:url value ='./userReviewList'/>" class="menu-item">리뷰목록</a>

			</div>
			<br>
			<c:forEach var="item" items="${userListVos}">
				<div>

					<div class="hotel-info">
						<div class="hotel-image">
							<img
								src="${pageContext.request.contextPath}/roomUploadImg/${item.room_images}">
						</div>
						<div class="hotel-details">
							<h2>호텔이름 : ${item.user_name}</h2>
							<p>룸이름 : ${item.room_name}</p>
							<p>주소 : ${item.room_address}</p>
							<p>예약 날짜 : ${item.b_checkin_date}~${item.b_checkout_date}</p>
						</div>
					</div>
					<div class="button-group">
						<form action="<c:url value='/user/selectReserve' />">
							<input type="hidden" name="booking_no" value="${item.booking_no}" />
							<button class="btn btn-secondary"
								id="btn-reservation-${item.booking_no}">예약서 확인</button>
						</form>

						<form action="<c:url value='/user/writeReview' />">
							<input type="hidden" name="booking_no" value="${item.booking_no}" />
							<button class="btn btn-info"
								id="openReviewModal-${item.booking_no}">리뷰 작성</button>
						</form>

						<p>
							<input type="hidden" name="booking_no" value="${item.booking_no}" />
							<button class="btn btn-danger"
								id="openCancelModal-${item.booking_no}" data-bs-toggle="modal"
								data-bs-target="#staticBackdrop">예약 취소</button>
						</p>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script>
  function navigateToLink(link) {
   window.location.href = link;
  }

  // 개인회원 박스 클릭 이벤트 핸들러
  document.getElementById("btn-reservation").addEventListener("click",
    function() {
     navigateToLink("<c:url value='/userReserveConfirm' />");

    });
 </script>
	<script>
    // 반복문으로 각 아이템에 대한 작업을 수행
    <c:forEach var="item" items="${userListVos}">
        // 현재 날짜와 시간 가져오기
        var currentDate = new Date();
        // 체크인 날짜와 시간 가져오기 (item.b_checkin_date는 체크인 날짜를 나타내는 변수로 가정)
        var checkinDate = new Date("${item.b_checkin_date}");
        // 체크아웃 날짜와 시간 가져오기 (item.b_checkout_date는 체크아웃 날짜를 나타내는 변수로 가정)
        var checkoutDate = new Date("${item.b_checkout_date}");
        // 리뷰 작성 버튼과 예약 취소 버튼 엘리먼트 가져오기
        var reviewButton = document.getElementById("openReviewModal-" + ${item.booking_no});
        var cancelButton = document.getElementById("openCancelModal-" + ${item.booking_no});

        // 체크인 날짜가 현재 날짜보다 이전이면 예약 취소 버튼 표시, 리뷰 작성 버튼 숨김
        if (checkinDate > currentDate) {
            reviewButton.style.display = "none";
            cancelButton.style.display = "block";
        }
        // 체크아웃 날짜가 현재 날짜보다 이전이면 리뷰 작성 버튼 표시, 예약 취소 버튼 숨김
        else if (checkinDate < currentDate) {
            reviewButton.style.display = "block";
            cancelButton.style.display = "none";
        }

        // 예약 확인 버튼의 클릭 이벤트 핸들러 (이 부분은 예약 확인 버튼에 대한 작업을 추가하는 부분입니다)
        document.getElementById("btn-reservation-" + ${item.booking_no}).addEventListener("click", function() {
            navigateToLink("<c:url value='/userReserveConfirm' />");
        });
    </c:forEach>
</script>
	<script>
 $('#staticBackdrop1, #staticBackdrop2').click(
      'show.bs.modal',
      function(event) {
       
          var button = $(event.relatedTarget);
          var booking_no = button.data('booking_no');
          var modal = $(this);
          $('#reviewsubmitbutton').data('booking_no', booking_no);
          $('#reviewsubmitbutton').attr('booking_no', booking_no);
          modal.find('#canclebutton, #reviewsubmitbutton').data('booking_no', booking_no);
      }
   );

  </script>

	<jsp:include page="./modal/modal_ReservationCancel.jsp" />
	<jsp:include page="../include/footer.jsp" />

</body>


</head>

</html>