<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link href="<c:url value='/resources/css/user/userMyInfo.css' />" rel="stylesheet" type="text/css">

<body>

	<jsp:include page="../include/header.jsp" />

	<form name="mainForm" id="mainForm" method="post" action="">
	<input type="hidden" name="user_rank" id="user_rank" value="<c:out value="${userVO.user_rank}" />">
	<input type="hidden" name="user_phone" id="user_phone" value="<c:out value="${userVO.user_phone}" />">
	<input type="hidden" name="user_email" id="user_email" value="<c:out value="${userVO.user_email}" />">
			
    <div class="container">
		<div class="sub_title">
		<h2>마이페이지</h2></div>
		
		<div id="section_wrap">
		
		<h1 class="title">회원정보수정</h1>
		<p class="text-login02">회원님의 개인정보 확인 및 수정을 하실 수 있습니다.</p>
        
		<hr>

				
			<div class="create_account_form">

					<dl>
						<dt>ID<span id="star"> *</span></dt>
						<dd>
						<input type="text" name="user_id" id ="user_id" placeholder="아이디 입력" required value="<c:out value="${userVO.user_id}" />" readonly="readonly">
						</dd>
					</dl>
					
					<dl>
						<dt>PASSWORD<span id="star"> *</span></dt>
						<dd>
							<input type="password" name="user_pw"
								placeholder="비밀번호 입력" maxlength="12" required value="••••••••">
							<p>영문, 숫자, 특수문자 조합으로 8~15자 이내로 입력</p>
						</dd>
					</dl>
					
					<dl>
						<dt>CONFIRM PASSWORD<span id="star"> *</span></dt>
						<dd>
							<input type="password" name="user_pw_again"
								placeholder="비밀번호 재입력" maxlength="12" required value="••••••••">
						</dd>
					</dl>
					
<!-- 					<ul>
						<li>비밀번호 내 ID가 포함되거나, 연속되는 문자 또는 숫자는 3자리 이상 사용할 수 없습니다.</li>
						<li>특수문자(!@#$%^&+=)중 하나를 선택하시면 됩니다.</li>
					</ul> -->
					
					<hr id="hr">
					
					<dl>
						<dt>이름<span id="star"> *</span></dt>
						<dd>
						<input type="text" name="user_name" placeholder="이름 입력" required value="<c:out value="${userVO.user_name}" />">
						</dd>
					</dl>
					
					<dl>
						<dt>연락처<span id="star"> *</span></dt>
						<dd>
    					<select  id="FirstPhone1" name="user_phone1" onchange="showInput(this)">
        					<option value="010" <c:if test="${fn:substring(userVO.user_phone, 0, 3) == '010'}">selected</c:if> >010</option>
        					<option value="writeDirectly" <c:if test="${fn:substring(userVO.user_phone, 0, 3) != '010'}">selected</c:if> >직접입력</option>
    					</select>
    					<div id="directInput" style="display: none;">
							<input type="text" name="custom_phone" placeholder="직접 입력" value="<c:out value="${fn:substring(userVO.user_phone, 0, 3)}" />">
						</div>
    					<input type="text" maxlength="4" id="MidPhone1" name="user_phone2"  placeholder="" value="<c:out value="${fn:substring(userVO.user_phone, 3, 7)}" />">
    					<input type="text" maxlength="4" id="LastPhone1" name="user_phone3"  placeholder="" value="<c:out value="${fn:substring(userVO.user_phone, 7, 11)}" />">
						</dd>
					</dl>
					
					<dl>
						<dt>이메일<span id="star"> *</span></dt>
						<dd>
						<input type="email" name="user_email1" id="user_email1" placeholder="이메일 입력" required value="<c:out value="${fn:substring(userVO.user_email, 0, fn:indexOf(userVO.user_email, '@'))}" />">
						<span>@</span>
	
						<select class="EmailAddress"  name="user_email2" id="user_email2"
							onchange="emailShowInput(this)">
							<option value="gmail.com">gmail.com</option>
							<option value="naver.com">naver.com</option>
							<option value="daum.com">daum.com</option>
							<option value="writeDirectly">직접입력</option>
						</select>
						<div id="emailDirectInput" style="display: none;">
							<input type="text" name="custom_email" id="custom_email" placeholder="직접 입력">
						</div>
						</dd>
					</dl>
					
				</div>
				<button type="button" id="btnBack" onclick="goBack()">이전</button>
				<button type="button" id="btnSave" onclick="saveIt()">저장</button>		
				<button type="button" id="btnDel" onclick="delIt()">탈퇴</button>	
						
		</div>
	</div>
	</form>
    
    <jsp:include page="../include/footer.jsp" />
    
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <script>

	// 취소 버튼 누르면 브라우저의 이전 페이지로 이동
	function goBack() {
		window.history.back();
	}
	
	
    function saveIt() {
    	let firstPhone1 = $("#FirstPhone1").val();
    	if (firstPhone1 == 'writeDirectly'){
    		firstPhone1 = $("#FirstPhone1direct").val();
    	}
    	let user_phone = firstPhone1 + $("#MidPhone1").val() + $("#LastPhone1").val();  
    	$("#user_phone").val(user_phone);
    	
    	let user_email1 = $("#user_email1").val();
    	let user_email2 = $("#user_email2").val();
    	if (user_email2 == 'writeDirectly'){
    		user_email2 = $("#custom_email").val();
    	}
    	let user_email = user_email1 + "@" + user_email2;  
    	$("#user_email").val(user_email);
			    	
    	$.post('<c:url value='./userModifyProc' />', $('#mainForm').serialize(), function (result) {
    	    // Process the response from the server
    	    if (result.resultCode == "0000") {
    	        alert('회원수정이 완료되었습니다.');
    	        location.href = '<c:url value="/" />';
    	    } else {
    	        alert(result.resultMessage);
    	    }
    	}, 'json');
    }    
    
    function delIt() {
    	let firstPhone1 = $("#FirstPhone1").val();
    	if (firstPhone1 == 'writeDirectly'){
    		firstPhone1 = $("#FirstPhone1direct").val();
    	}
    	let user_phone = firstPhone1 + $("#MidPhone1").val() + $("#LastPhone1").val();  
    	$("#user_phone").val(user_phone);
    	
    	let user_email1 = $("#user_email1").val();
    	let user_email2 = $("#user_email2").val();
    	if (user_email2 == 'writeDirectly'){
    		user_email2 = $("#custom_email").val();
    	}
    	let user_email = user_email1 + "@" + user_email2;  
    	$("#user_email").val(user_email);
			    	
    	$.post('<c:url value='./userDelProc' />', $('#mainForm').serialize(), function (result) {
    	    // Process the response from the server
    	    if (result.resultCode == "0000") {
    	        alert('회원탈퇴가 완료되었습니다.');
    	        location.href = '<c:url value="/" />';
    	    } else {
    	        alert(result.resultMessage);
    	    }
    	}, 'json');
    }    
    
</script>

</body>
</html>