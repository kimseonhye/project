<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="../include/title.jsp" />
<link href="<c:url value='/resources/css/user/userBookingPage.css' />" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>
  	<jsp:include page="../include/header.jsp" />
  	
	<div class="centered-container">
		<div class="circle-icon">
			<div class="inner-icon">
				<span class="material-symbols-outlined"> contract_edit </span>
			</div>
		</div>
		<div class="text">예약 정보 입력</div>
	</div>
	<form action="<c:url value='/user/userReserveConfirm' />" id="reserveForm" name="ReserveConfirm" method="post">
		<input type="hidden" name="room_no" value="${roomVO.room_no}" />
		<!-- <input type="hidden" name="booking_no" value="1" />
		<input type="hidden" name="room_no" value="1" /> -->
		<div class="container">
			<div class="left-box">
				<div>
				<span class="material-symbols-sharp">counter_1</span>
				<span class="title">고객 정보</span>
			</div>
				<hr class="line">
				<div class="form-group">
					<div class="input-group">
						<label for="name">이름<span class="star"> *</span></label> 
						<input type="text" id="name" name="b_user_name">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label for="contact">연락처<span class="star"> *</span></label>
						<input type="text" id="contact" name="b_user_contact" placeholder="-까지 입력해 주세요.">
					</div>
					<div class="ment">* 연락 가능한 번호를 적어주십시오.</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label for="email">이메일<span class="star"> *</span></label>
						<input id="domain" type="email" name="b_user_email" placeholder="@까지 입력해 주세요.">
					</div>
					<div class="ment">* 입력하신 이메일로 예약 내용이 발송됩니다.</div>
				</div>
				<div class="form-group">
					<dl>
						<dt>
							날짜<span class="star"> *</span>
						</dt>
						<dd>
							<label for="date"></label> <input type="date"
								name="b_checkin_date" class="locationInput" id="date"
								value="2023-10-19" max="2024-12-31" required>
						</dd>
					</dl>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label for="company">회사명</label> 
						<input type="text" id="company" name="b_company">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label for="guests">인원<span class="star"> *</span></label> 
						<input type="number" id="guests" name="b_ppl">
					</div>
				</div>
				<div>
					<span class="material-symbols-sharp">counter_2</span>
					<span class="title">행사 정보</span>
				</div>
				<hr class="line">
				<div class="form-group">
					<div class="input-group">
						<label for="event-name">행사명<span class="star"> *</span></label> <input
							type="text" id="event-name" name="b_name">
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label for="event-purpose">행사 목적<span class="star"> *</span></label>
						<textarea id="event-purpose" name="b_purpose" rows="4"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="table-arrangement">테이블 배치</label>
					<div class="box" data-value="연회식"
						onclick="selectTableArrangement(this)">
						<div class="material-icons">wb_sunny</div>
						<div class="box-content">연회식</div>
					</div>
					<div class="box" data-value="극장식"
						onclick="selectTableArrangement(this)">
						<div class="material-icons">theater_comedy</div>
						<div class="box-content">극장식</div>
					</div>
					<div class="box" data-value="강의식"
						onclick="selectTableArrangement(this)">
						<div class="material-icons">width_full</div>
						<div class="box-content">강의식</div>
					</div>
					<div class="box" data-value="ㄷ자형"
						onclick="selectTableArrangement(this)">
						<div class="material-icons">drag_indicator</div>
						<div class="box-content">ㄷ자형</div>
					</div>
				</div>
				<div id="selected-value">선택된 테이블 : <span id="selected-table-arrangement-text"></span></div>
   				<input type="hidden" id="selected-table-arrangement" name="b_table_set" value="" />
				<div>
					<span class="material-symbols-sharp">counter_3</span>
					<span class="title">기타 요청사항</span>
				</div>
				<hr class="line">
				<div class="form-group">
					<textarea id="b_comment" name="b_comment" rows="8" placeholder="요청사항은 상황에 따라 수용이 어려울 수 있습니다. 양해 부탁 드립니다."></textarea>
				</div>
			</div>
			<div class="right-box" id="hotel-info">
				<div>
					<h2>호텔 정보</h2>
					<!-- 여기는 room 테이블만 사용할 거임 -->
					<p>룸 이름 : ${roomVO.room_name}</p>
					<p>주소 : ${roomVO.room_address}</p>
					<p>호텔 연락처 : ${roomVO.user_phone}</p>
				</div>
				<div class="reservation-summary">
					<h2>총 예약 금액</h2>
					<p>￦ ${roomVO.room_price}</p>
				</div>
				<div class="button">
					<button type="submit" class="reserve-button" id="reserve"
						value="예약" onclick="booking()">예약</button>
				</div>
			</div>
		</div>
	</form>
	
	<script>
            // 오른쪽 박스의 높이를 내용에 맞게 조절
            function adjustRightBoxHeight() {
                const rightBox = document.getElementById('hotel-info');
                if (rightBox) {
                    rightBox.style.height = rightBox.scrollHeight + 'px';
                }
            }
               		
    		function booking() {
    		    let form = document.getElementById("reserveForm");

    		    // 선택한 테이블 배치 값을 가져오고 Form 데이터에 추가
    		    let selectedTableArrangement = document.querySelector(".box.clicked");
    		    if (selectedTableArrangement) {
    		        let selectedValue = selectedTableArrangement.getAttribute("data-value");
    		        form.elements["b_table_set"].value = selectedValue;
    		    }

    		    if (form.b_user_name.value === '') {
    		        alert('이름을 입력하세요.');
    		        form.b_user_name.focus();
    		    } else if (form.b_user_contact.value === '') {
    		        alert('연락처를 입력하세요.');
    		        form.b_user_contact.focus();
    		    } else if (form.b_user_email.value === '') {
    		        alert('이메일을 입력하세요.');
    		        form.b_user_email.focus();
    		    } else if (form.b_ppl.value === '') {
    		        alert('인원을 입력하세요.');
    		        form.b_ppl.focus();
    		    } else if (form.b_name.value === '') {
    		        alert('행사명을 입력하세요.');
    		        form.b_name.focus();
    		    } else if (form.b_purpose.value === '') {
    		        alert('행사 목적을 입력하세요.');
    		        form.b_purpose.focus();
    		    } else { 
    		    	form.submit();
    		    }
    		}

            // 페이지 로드 시 박스 높이 조절
            window.addEventListener('load', adjustRightBoxHeight);

            // 페이지 크기가 변경될 때도 박스 높이 조절
            window.addEventListener('resize', adjustRightBoxHeight);
            
            function selectTableArrangement(selectedOption) {
                // 선택한 테이블 배치를 JavaScript 변수에 저장
                let selectedTableArrangement = selectedOption.getAttribute("data-value");

                // 저장한 값을 아래쪽에 표시
                let selectedValueText = document.getElementById("selected-table-arrangement-text");
                selectedValueText.textContent = selectedTableArrangement;

                // 선택한 값을 숨겨진 input 엘리먼트에 저장
                let selectedTableArrangementInput = document.getElementById("selected-table-arrangement");
                selectedTableArrangementInput.value = selectedTableArrangement;
            }

            //테이블 배치
			document.addEventListener('DOMContentLoaded', () => {
            const tableOptions = document.querySelectorAll('.box'); 
            const selectedTableArrangementInput = document.getElementById('selected-table-arrangement'); 

            tableOptions.forEach(option => {
                option.addEventListener('click', () => {
                    const isClicked = option.classList.contains('clicked');

                    if (isClicked) {
                        option.classList.remove('clicked');
                        option.style.backgroundColor = 'white';
                        option.style.color = 'black';
                        selectedTableArrangementInput.value = ''; 
                    } else {
                        option.classList.add('clicked');
                        option.style.backgroundColor = '#6e6e6e';
                        option.style.color = 'white';

                        tableOptions.forEach(otherOption => {
                            if (otherOption !== option) {
                                otherOption.classList.remove('clicked');
                                otherOption.style.backgroundColor = 'white';
                                otherOption.style.color = 'black';
                            }
                        });

                        selectedTableArrangementInput.value = option.querySelector('.box-content').textContent;
                    }
                });

                // Hover 효과
                option.addEventListener('mouseover', () => {
                    if (!option.classList.contains('clicked')) {
                        option.style.backgroundColor = 'orange';
                    }
                });

                option.addEventListener('mouseout', () => {
                    if (!option.classList.contains('clicked')) {
                        option.style.backgroundColor = 'white';
                    }
                });
            });
        });
            
        // // 도메인 직접 입력 or domain option 선택
		// 	const domainListEl = document.querySelector('#domain-list')
		// 	const domainInputEl = document.querySelector('#domain-txt')
		// // select 옵션 변경 시
		// 	domainListEl.addEventListener('change', (event) => {
  		// // option에 있는 도메인 선택 시
		// 	  if(event.target.value !== "type") {
		// 	    domainInputEl.value = event.target.value
		// 	    domainInputEl.disabled = true
		// 	  } else { // 직접 입력 시
		// 	    // input 내용 초기화 & 입력 가능하도록 변경
		// 	    domainInputEl.value = ""
		// 	    domainInputEl.disabled = false
		// 	  }
		// 	});
	        
        </script>
       
    <jsp:include page="../include/footer.jsp" />
</body>
</html>