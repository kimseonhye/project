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

			<div class="search-container">			
				<form action="<c:url value='/admin/searchNoticeConfirm' />" name="adminNotice" method="get">
					<input type="text" class="search-input" id="n_u_title" name="n_u_title" placeholder="search">
					<button type="submit" class="button-input" id="search" name="search" onclick="searchForm();">
						<span class="material-symbols-outlined">search</span>
					</button>
				</form>			
			</div>

		</div>
			<c:if test="${sessionScope.userVO.user_rank eq 3}">
			<div class="writing">
				<a href="adminCreateNotice"><input type="button" id="btn" value="글 작성"></a>
			</div>
			</c:if>
		<c:forEach items="${noticeList}" var="notice">
			<div class="post">
				<div class="post-header">
					<p class="post-title">${notice.n_u_title}</p>
					<span class="date-created">${notice.n_u_date}</span>
					<span class="post-arrow"> 
					<span class="material-symbols-outlined"> expand_more </span>
					</span>
				</div>
				<div class="post-content">
				<c:if test="${not empty notice.n_u_image}">
					<img src="<c:url value='/noticeUploadImg/${notice.n_u_image}'/>" class="image-resize">
				</c:if>
					<p class="post-answer">${notice.n_u_content}</p>
					<c:if test="${sessionScope.userVO.user_rank eq 3}">
					<div class="post-actions">
						<c:url value='/admin/modifyNoticeForm' var='modify_url'>
							<c:param name='n_u_no' value='${notice.n_u_no}' />
						</c:url>
						<a class="post-edit" href="${modify_url}">수정</a>
	
						<c:url value='/admin/deleteNoticeForm' var='delete_url'>
							<c:param name='n_u_no' value='${notice.n_u_no}' />
						</c:url>
						<a class="post-delete" href="#none"
							onclick="deleteNotice(${notice.n_u_no})">삭제</a>
					</div>
					</c:if>
				</div>
			</div>
			</c:forEach>

			<div class="pagination">
			    <a href="#" class="prev">이전</a>
			    <c:forEach var="page" begin="1" end="5">
			        <a href="#" class="page${page == currentPage ? ' active' : ''}">${page}</a>
			    </c:forEach>
			    <a href="#" class="next">다음</a>
			</div>

	<script>
	
	const postArrows = document.querySelectorAll('.post-arrow');
	postArrows.forEach(arrow => {
    arrow.addEventListener('click', () => {
        const post = arrow.closest('.post');
		const postContent = post.querySelector('.post-content');
        
			// postContent의 display 속성을 토글
	        if (postContent.style.display === 'none' || postContent.style.display === '') {
	            postContent.style.display = 'block';
	        } else {
	            postContent.style.display = 'none';
	        }
   		});
	});
	
	function refreshPage() {
	    location.reload(); // 현재 페이지를 새로 고침
	}
	
	function searchForm() {
	    console.log('searchForm() CALLED!!');
	    
	    let form = document.forms.adminNotice;
	    
	    if (form.n_u_title.value == '') {
	        form.n_u_title.focus();
	    } else {
	        form.submit();
	    }
	}
	
	function deleteNotice(n_u_no) {
	    console.log('deleteNotice() CALLED!!');

	    let result = confirm('정말 삭제하시겠습니까?');

	    if (result) {
	        let deleteUrl = "/project/admin/deleteNoticeConfirm?n_u_no=" + n_u_no;
	        location.href = deleteUrl;
	    }
	}
	
	function redirectToPostPage() {
        window.location.href = "post.jsp";
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
