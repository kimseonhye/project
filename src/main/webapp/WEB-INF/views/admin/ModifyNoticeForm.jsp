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

<title>공지사항 수정 페이지</title>
</head>
<body>
<jsp:include page="../include/header.jsp" />
	<div class="maintitle">
		<h2>게시글 작성</h2>
	</div>
	<form action="<c:url value='/admin/modifyNoticeConfirm' />" name="ModifyNoticeForm" method="post" enctype="multipart/form-data">
		<div class="container">
			<h2>게시글을 작성해 주세요.</h2>
			<p class="commentary">작성 시 공지사항과 FAQ 중 하나를 선택하세요.</p>
			<div class="filter">
				<label for="notice">공지사항</label> <input type="checkbox" id="notice"
					name="filter" value="notice" checked> <label for="faq">FAQ</label>
				<input type="checkbox" id="faq" name="filter" value="faq">
			</div>
	
			<div class="input-box">
				<input type="text" id="title" name="n_u_title" value="${userNoticeVO.n_u_title}" placeholder="제목을 입력해 주세요.">
			</div>
	
			<div class="input-box">
				<textarea id="content" rows="10" name="n_u_content" placeholder="내용을 입력해 주세요.">${userNoticeVO.n_u_content}</textarea>
			</div>
	
			<div class="input-box">
				<div class="file-input-container">
					<input type="file" id="image" name="file" value="${userNoticeVO.n_u_image}" accept="image/*">
				</div>
			</div>
			<div class="buttons">
				<input type="button" id="delete" value="취소" onclick="goBack()">
				<input type="button" id="modify" value="수정" onclick="modifyNotice()">
			</div>
			<input type="hidden" name="n_u_no" value="${userNoticeVO.n_u_no}">
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
		
		function modifyNotice() {
			console.log('modifyNotice() CALLED!!');
			
			let form = document.ModifyNoticeForm;
			
			if (form.n_u_title.value == '') {
				alert('제목을 입력하세요.');
				form.n_u_title.focus();
				
			} else if (form.n_u_content.value == '') {
				alert('내용을 입력하세요.');
				form.n_u_content.focus();
				
			} else {
				form.submit();				
			}			
		} 

		
	</script>
	
	<jsp:include page="../include/footer.jsp" />
</body>

</html>