<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/company/modal/modal_RoomDelete.css' />"
  rel="stylesheet" type="text/css">
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
  crossorigin="anonymous">
</head>
<body>



  <!-- Modal -->
  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
    data-bs-keyboard="false" tabindex="-1"
    aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-body">
          <h1 class="modal-title fs-5" id="staticBackdropLabel">
            <b>정말 삭제하시겠습니까?</b>
          </h1>
          <div class="modal-body-button">
            <button type="button" class="btn btn-outline-dark"
              data-bs-dismiss="modal" aria-label="Close">&lt; 뒤로</button>
            <button type="button" class="btn btn-outline-danger"
              data-bs-dismiss="modal" id="confirmDeleteBtn" aria-label="Close">삭제</button>
          </div>
        </div>
      </div>
    </div>
  </div>


  <!-- Include Bootstrap and jQuery -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>

  <!-- Initialize the modal -->
  <script>
  var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));

  document.getElementById('confirmDeleteBtn').addEventListener('click', function() {
      document.getElementById('deleteForm').submit();
  });
  </script>
  

</body>
</html>