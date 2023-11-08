<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@30,400,0,0" />
<!-- 돋보기 아이 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<link href="<c:url value='/resources/css/admin/adminNotice.css' />"
	rel="stylesheet" type="text/css">

<title>고객센터</title>
</head>
<body>
		<jsp:include page="../include/header.jsp" />
		<h3 class="maintitle">고객 센터</h3>

		<div class="container">
			<nav class="nav">
				<a href="<c:url value='/admin/adminNotice' />" class="active">공지사항</a> 
				<a href="<c:url value='/admin/adminFAQ' />" class="active">FAQ</a>
			</nav>
		</div>
	
		<div class="search-container">
			<form action="<c:url value='/admin/searchNoticeConfirm' />"
				name="searchNoticeForm" method="get">
				<input type="text" class="search-input" placeholder="search">
				<button type="submit" class="button-input"
					onclick="searchBookForm();">
					<span class="material-symbols-outlined">search</span>
				</button>
			</form>
		</div>
			<c:if test="${sessionScope.userVO.user_rank eq 3}">
			<div class="writing">
				<a href="adminCreateFAQ"><input type="button" id="btn" value="글 작성"></a>
			</div>
			</c:if>
		<c:forEach items="${FAQList}" var="faq">
			<div class="post">
				<div class="post-header">
					<p class="post-title">${faq.faq_u_title}</p>
					<span class="date-created">${faq.faq_u_date}</span> 
					<span class="post-arrow"> <span class="material-symbols-outlined">
							expand_more </span>
					</span>
				</div>
				<div class="post-content">
				<c:if test="${not empty faq.faq_u_image}">
					<img src="<c:url value='/noticeUploadImg/${faq.faq_u_image}'/>" class="image-resize">
				</c:if>
				<p class="post-answer">${faq.faq_u_content}</p>
					<c:if test="${sessionScope.userVO.user_rank eq 3}">
					<div class="post-actions">
						<c:url value='/admin/modifyFAQForm' var='modify_url'>
							<c:param name='faq_u_no' value='${faq.faq_u_no}' />
						</c:url>
						<a class="post-edit" href="${modify_url}">수정</a>

						<c:url value='/admin/deleteFAQForm' var='delete_url'>
							<c:param name='faq_u_no' value='${faq.faq_u_no}' />
						</c:url>
						<a class="post-delete" href="#none"
							onclick="deleteFAQ(${faq.faq_u_no})">삭제</a>
					</div>
					</c:if>
				</div>
			</div>
		</c:forEach>

		<div class="pagination">
				<a href="#" class="prev">이전</a> <a href="#" class="page active">1</a>
				<a href="#" class="page">2</a> <a href="#" class="page">3</a> <a
					href="#" class="page">4</a> <a href="#" class="page">5</a> <a
					href="#" class="next">다음</a>
			</div>


	<script>
	
	const postArrows = document.querySelectorAll('.post-arrow');
	postArrows.forEach(arrow => {
    arrow.addEventListener('click', () => {
        const post = arrow.closest('.post');
        post.classList.toggle('active');
   		});
	});
	
	function redirectToPostPage() {
        // post.jsp 페이지로 이동
        window.location.href = "post.jsp";
    }
	
	function deleteFAQ(faq_u_no) {
	    console.log('deleteFAQ() CALLED!!');

	    let result = confirm('정말 삭제하시겠습니까?');

	    if (result) {
	        // URL을 동적으로 생성
	        let deleteUrl = "/project/admin/deleteFAQConfirm?faq_u_no=" + faq_u_no;
	        location.href = deleteUrl;
	    }
	}
	
    document.addEventListener("DOMContentLoaded", function () {
        const prevButton = document.querySelector(".prev");
        const nextButton = document.querySelector(".next");
        const pages = document.querySelectorAll(".page");

        let currentPage = 1;

        // 초기 페이지 설정
        updatePagination();

        // 이전 페이지로 이동
        prevButton.addEventListener("click", function (e) {
            e.preventDefault();
            if (currentPage > 1) {
                currentPage--;
                updatePagination();
            }
        });

        // 다음 페이지로 이동
        nextButton.addEventListener("click", function (e) {
            e.preventDefault();
            if (currentPage < pages.length) {
                currentPage++;
                updatePagination();
            }
        });

        // 페이지 번호 클릭 시 해당 페이지로 이동
        pages.forEach(function (page, index) {
            page.addEventListener("click", function (e) {
                e.preventDefault();
                currentPage = index + 1;
                updatePagination();
            });
        });

        // 페이지네이션 업데이트 함수
        function updatePagination() {
            pages.forEach(function (page, index) {
                if (index === currentPage - 1) {
                    page.classList.add("active");
                } else {
                    page.classList.remove("active");
                }
            });

            if (currentPage === 1) {
                prevButton.style.display = "none";
            } else {
                prevButton.style.display = "inline-block";
            }

            if (currentPage === pages.length) {
                nextButton.style.display = "none";
            } else {
                nextButton.style.display = "inline-block";
            }
        }
    });

	</script>
	
	<jsp:include page="../include/footer.jsp" />

</body>
</html>
