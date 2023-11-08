<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value='/resources/css/admin/adminCreatePost.css' />"
	rel="stylesheet" type="text/css">

<title>FAQ 작성 페이지</title>
</head>
<body>
<jsp:include page="../include/header.jsp" />
	<div class="maintitle">
		<h2>게시글 작성</h2>
	</div>
	<form action="<c:url value='/admin/registerFAQConfirm' />" id="FAQForm" name="adminCreateFAQ" method="post" enctype="multipart/form-data">
		<div class="container">
			<h2>게시글을 작성해 주세요.</h2>
			<p class="commentary">작성 시 공지사항과 FAQ 중 하나를 선택하세요.</p>
			<div class="filter">
				<label for="notice">공지사항</label> <input type="checkbox" id="notice"
					name="filter" value="notice"> <label for="faq">FAQ</label>
				<input type="checkbox" id="faq" name="filter" value="faq" checked>
			</div>
	
			<div class="input-box">
				<input type="text" id="title" name="faq_u_title" placeholder="제목을 입력해 주세요.">
			</div>
	
			<div class="input-box">
				<textarea id="content" rows="10" name="faq_u_content" placeholder="내용을 입력해 주세요."></textarea>
			</div>
	
			<div class="input-box">
				<div class="file-input-container">
					<input type="file" id="image" name="file" accept="image/*">
				</div>
			</div>
			<div class="buttons">
				<input type="button" id="delete" value="취소" onclick="goBack()">
				<input type="button" id="register" value="등록" onclick="registerFAQ()">
			</div>
		</div>
	</form>

	<script>
		 // 둘 중 하나만 체크 가능하도록 함
		const noticeCheckbox = document.getElementById("notice");
		const faqCheckbox = document.getElementById("faq");

		noticeCheckbox.addEventListener("change", function() {
			if (this.checked) {
				faqCheckbox.checked = false;
			}
		});

		faqCheckbox.addEventListener("change", function() {
			if (this.checked) {
				noticeCheckbox.checked = false;
			}
		});

		function goBack() {
			window.history.back();
		}
		
		function registerFAQ() {
		    let form = document.getElementById("FAQForm");

		    if (form.faq_u_title.value == '') {
		        alert('제목을 입력하세요.');
		        form.faq_u_title.focus();
		    } else if (form.faq_u_content.value == '') {
		        alert('내용을 입력하세요.');
		        form.faq_u_content.focus();
		    } else {
		        form.submit();
		    }
		}  

		
	</script>
	
	<jsp:include page="../include/footer.jsp" />
</body>

</html>