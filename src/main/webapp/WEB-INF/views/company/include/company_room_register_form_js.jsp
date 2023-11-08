<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
//파일 드롭 영역 설정
var dropFile = document.querySelectorAll(".drag-file");

dropFile.forEach(function (element) {
    var input = element.querySelector("input[type=file]");
    var message = element.querySelector(".message");

    element.addEventListener("dragover", function (e) {
        e.preventDefault();
        element.style.border = "2px dashed #555";
    });

    element.addEventListener("dragleave", function () {
        element.style.border = "2px dashed #ccc";
    });

    element.addEventListener("drop", function (e) {
        e.preventDefault();
        element.style.border = "2px dashed #ccc";
        var file = e.dataTransfer.files[0];

        if (file) {
            input.files = e.dataTransfer.files;
            message.innerHTML = file.name;
        }
    });
});

// 주소 검색 버튼 클릭 시 동작
var searchAddressButton = document.getElementById("searchAddress");

searchAddressButton.addEventListener("click", function () {
    // 주소 검색 로직을 구현하세요.
    alert("주소 검색 로직을 구현하세요.");
});

// 폼 제출 시 동작 (필요한 경우 추가적인 로직을 구현하세요)
var placeForm = document.getElementById("placeForm");

placeForm.addEventListener("submit", function (e) {
    // 폼 제출 전에 필요한 로직을 구현하세요.
    alert("폼 제출 전에 필요한 로직을 구현하세요.");
});




</script>