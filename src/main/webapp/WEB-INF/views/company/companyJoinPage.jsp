<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../include/title.jsp" />

<link
	href="<c:url value='/resources/css/company/companyJoinPage.css' />"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>

	<jsp:include page="../include/header.jsp" />

	<section>

		<div id="section_wrap">

			<h3>
				<b>기업회원 회원가입</b>
			</h3>
			<br>
			<p>
				<span id="star"> * </span> 아래 필수입력정보 작성하여 주시기 바랍니다.
			</p>
			<br>
			<!-- 등록을 처리할 Spring MVC 컨트롤러의 URL과 일치하도록 양식 작업 수정 -->
			<form id="userJoinForm" action="<c:url value='/company/register' />"
				method="post" onsubmit="return validateForm()">
				<div class="create_account_form">

					<dl>
						<dt>
							ID<span id="star"> *</span>
						</dt>
						<dd>
							<input type="text" name="user_id" id="user_id"
								placeholder="아이디 입력" required>
							<button type="button" class="IDConfirm"
								onclick="checkDuplicateUserId()">ID 중복 확인</button>
							<p id="result"></p>
						</dd>
					</dl>

					<dl>
						<dt>
							PASSWORD<span id="star"> *</span>
						</dt>
						<dd>
							<input type="password" name="user_pw" placeholder="비밀번호 입력"
								maxlength="15" required>
							<p>영문, 숫자, 특수문자 조합으로 8~15자 이내로 입력</p>
						</dd>
					</dl>

					<dl>
						<dt>
							CONFIRM PASSWORD<span id="star"> *</span>
						</dt>
						<dd>
							<input type="password" name="user_pwAgain" placeholder="비밀번호 재입력"
								maxlength="15" required>
						</dd>
					</dl>

					<!-- UserVO 속성과 일치하도록 필드 이름 업데이트 -->

					<hr id="hr">

					<dl>
						<dt>
							기업명<span id="star"> *</span>
						</dt>
						<dd>
							<input type="text" name="user_name" placeholder="기업명을 입력하세요" required>
						</dd>
					</dl>

					<dl>
						<dt>
							연락처<span id="star"> *</span>
						</dt>
						<dd>
							<input type="text" name="user_phone" placeholder="- 제외하고 입력하세요"
								required>
						</dd>
					</dl>
					<dl>
						<dt>
							이메일<span id="star"> *</span>
						</dt>
						<dd>
							<input type="text" name="user_email_id" id="user_email_id"
								required>
							<!-- 아이디 부분 입력 -->
							@ <input type="text" name="str_email02" id="str_email02"
								value="naver.com"> <select class="emailAddress"
								name="user_emailDomain" id="selectEmail"
								onchange="updateEmail()">
								<option value="1">직접입력</option>
								<option value="naver.com" selected>naver.com</option>
								<option value="daum.net">daum.net</option>
								<option value="nate.com">nate.com</option>
								<option value="gmail.com">gmail.com</option>
							</select> <input type="hidden" name="user_email" id="user_email">
							<!-- 전체 이메일 주소를 저장할 숨은 필드 -->
						</dd>
					</dl>
				</div>
				<!-- 버튼 유형 및 텍스트 업데이트 -->
				<button class="cancel-button" onclick="goBack()">취소</button>
				<button type="submit" class="userJoinButton">회원가입</button>

			</form>
		</div>
	</section>
	<jsp:include page="../include/footer.jsp" />

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
	
	function checkDuplicateUserId() {
        var user_id = document.querySelector('#user_id').value;

        if (user_id.trim() === '') {
            alert('아이디를 입력해주세요.'); // 사용자에게 알림
            return;
        }
        
        // 정규표현식을 사용하여 아이디가 영문자, 숫자 조합인지 확인
        var pattern = /^[a-zA-Z0-9]+$/;
        if (!pattern.test(user_id)) {
            document.getElementById('result').innerText = '아이디는 영문자, 숫자 조합으로만 가능합니다.';
            document.getElementById('result').style.color = 'red';
            return;
        }
        
        $.ajax({
            type: "POST",
            url: "<c:url value='/user/checkDuplicateUserId' />",
            data: { user_id: user_id },
            success: function(result) {
            	 var resultElement = document.getElementById('result');
                 if (result === "true") {
                     resultElement.innerText = '사용 가능한 아이디입니다.';
                     resultElement.style.color = 'green'; // 초록색
                     isUserIdUnique = true; // 중복 확인 여부를 true로 설정
                 } else {
                     resultElement.innerText = '사용 중인 아이디입니다.';
                     resultElement.style.color = 'red'; // 빨간색
                     isUserIdUnique = false; // 중복 확인 여부를 false로 설정
                 }
            },
            error: function() {
                alert('서버 오류로 인해 아이디 중복 확인에 실패했습니다.');
            }
        });
    }
	
	// 이메일 입력방식 선택
	$('#selectEmail').change(function() {
	    var selectedOption = $("#selectEmail option:selected");
	    
	    if (selectedOption.val() == '1') { // 직접입력일 경우
	        $("#str_email02").val(''); // 값 초기화
	        $("#str_email02").prop("disabled", false); // 활성화
	    } else { // 직접입력이 아닐 경우
	        $("#str_email02").val(selectedOption.val()); // 선택값 입력
	        $("#str_email02").prop("disabled", true); // 비활성화
	    }
	    
	    // 전체 이메일 주소 업데이트
	    updateEmail();
	});

	// 사용자가 이메일 주소를 입력하면 전체 이메일 주소를 업데이트
	$('#user_email_id, #str_email02').on('input', function() {
	    updateEmail();
	});

	// 전체 이메일 주소 업데이트 함수
	function updateEmail() {
	    var id = $("#user_email_id").val();
	    var domain = $("#str_email02").val();
	    var fullEmail = id + '@' + domain;
	    $("#user_email").val(fullEmail);
	}
	
	// 취소 버튼 누르면 브라우저의 이전 페이지로 이동
	function goBack() {
		window.history.back();
	}
	 
	// 회원가입 조건 충족 유효성 검사 함수
	function validateForm() {
	    var form = document.getElementById('userJoinForm');
	    var inputs = form.getElementsByTagName('input');
	    var password = form.querySelector('input[name="user_pw"]').value;
	    var confirmPassword = form.querySelector('input[name="user_pwAgain"]').value;
	    var user_id = document.querySelector('#user_id').value;
	    var resultElement = document.getElementById('result');

	    // 모든 입력 폼이 입력이 되었는지 확인
	    for (var i = 0; i < inputs.length; i++) {
	        if (inputs[i].hasAttribute('required') && inputs[i].value.trim() === '') {
	            alert('모든 입력 폼을 작성해주세요.');
	            return false;
	        }
	    }
	    
	 	// 아이디에 한글이나 특수문자가 있는지 확인
	    if (/[\u3131-\uD79D!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/.test(user_id)) {
	        alert('아이디는 영문자, 숫자 조합으로만 가능합니다.');
	        return false;
	    }

	    // ID 중복 확인을 하지 않았는지 확인
	    if (user_id.trim() === '' || resultElement.innerText === '') {
	        alert('아이디 중복 확인이 되지 않았습니다.');
	        return false;
	    }

	    // 사용자 아이디가 중복 데이터일 경우
	    if (!isUserIdUnique) {
	        alert('아이디 중복 확인이 필요합니다.');
	        return false;
	    }

	    // 1. 비밀번호가 8자 이상 15자 이하인지 확인
	    if (password.length < 8 || password.length > 15) {
	        alert('비밀번호는 8자 이상 15자 이하이어야 합니다.');
	        return false;
	    }

	    // 2. 비밀번호와 비밀번호 확인이 동일한지 확인
	    if (password !== confirmPassword) {
	        alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
	        return false;
	    }

	    // 3. 비밀번호에 적어도 하나의 특수문자가 있는지 확인
	    if (!/[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]/.test(password)) {
	        alert('비밀번호에 특수문자가 최소 1개 이상 포함되어야 합니다.');
	        return false;
	    }

	    // 4. 비밀번호에 영문자가 포함되어 있는지 확인
	    if (!/[a-zA-Z]/.test(password)) {
	        alert('비밀번호에는 영문자가 반드시 포함되어야 합니다.');
	        return false;
	    }

 	    // 모든 조건이 충족되었을 때 모달 열기
	    myModal.show();

	    return true;
	}
	</script>

	<%-- <jsp:include page="../modal/modal_JoinSuccess.jsp" /> --%>

</body>
</html>
