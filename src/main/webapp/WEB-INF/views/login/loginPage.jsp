<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/title.jsp" />
<link href="<c:url value='/resources/css/login/loginPage.css' />" rel="stylesheet"
	type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	/*
	var resultCode = "<c:out value="${resultCode}" />";
	var resultMessage = "<c:out value="${resultMessage}" />";
	
	if (resultCode != "0000" && resultMessage != ""){
		alert(resultMessage);
	}
	*/
	
	function loginForm(){
		//document.formlogin.action="<c:url value='/loginProc' />";
		//document.formlogin.submit();

    	$.post('<c:url value='/user/loginProc' />', $('#formlogin').serialize(), function (result) {
    	    if (result.resultCode == "0000") {
    	        location.href = '<c:url value="/" />';
    	    } else {
    	        alert(result.resultMessage);
    	    }
    	}, 'json');
    }    	
</script>
</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<section>

		<div id="section_wrap">

			<div class="login_form">

				<form id="formlogin" name="formlogin" method="post">
				<!-- <form action="/signInConfirm"> -->

					<input type="text" name="userid" placeholder="아이디" required>
					<br> <input type="password" name="pwd" placeholder="비밀번호"
						required> <br>
					<button type="button" class="login-button" onclick="loginForm();">로그인</button>
				</form>

			</div>

			<div class="find_password_create_account">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#staticBackdrop">아이디/비밀번호 찾기 ></button>
				<a href="<c:url value='/signUp' />">회원가입 ></a>

			</div>

		</div>

	</section>

	<jsp:include page="../include/footer.jsp" />

	<jsp:include page="../modal/modal_FindIDAndPW.jsp" />

</body>
</html>