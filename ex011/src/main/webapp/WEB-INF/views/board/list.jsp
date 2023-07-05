<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
  <head>

<script type="text/javascript">
	/* 스크립트로 메세지처리를 하게되면 통일성이 떨어져요?
	// 메세지 처리
	//let msg = '${param.msg}'; // 파라메터로 넘어갔기때문에 파라메터로 넘어가요!
	let msg = '${msg}'; // 세션영역에 저장되기 때문에 addFlashAttribute 방식일때는 이렇게 해주세요
	if(msg!=''){
		alert(msg);
	}
	*/
	/*
		부트스트랩을 이용한 모달창 띄우기 
		
		1. css, js 파일 추가 하기
		2. 모달요소 복사 
			타이틀및 메세지 수정
		3. 모달창 열기
			자바스크립트를 이용해서 모달객체 생성후 show()메서드 호출
		4. 모달창 닫기(닫기버튼 클릭, 배경화면 클릭)
			모달창이 닫히면 자바스크립트를 이용해서 뒤로가기 실행
			-> 모달창이 닫히면서 발생하는 이벤트(hidden.bs.modal)에 뒤로가기 추가
	*/
	let msg = '${msg}';
	
	window.onload = function(){
		if(msg != ''){
			// 메세지 출력
			document.querySelector(".modal-body").innerHTML = msg;
			// 버튼 출력 제어
			document.querySelector("#btnModalSave").style.display='none'
			// 모달 생성
			let myModal = new bootstrap.Modal(document.getElementById('myModal'), {
				  keyboard: false
			});
			// 모달 보여주기
			myModal.show();
		}
		
		const myModalEl = document.getElementById('myModal')
		// 모달창이 닫힐때 이벤트 처리
		// myModalEl.addEventListener('hidden.bs.modal', event => {
	    // 뒤로가기
		// history.go(-1);
		// })
	}
	</script>
  
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>Fixed top navbar example · Bootstrap v5.2</title>

	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbar-fixed/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="/resources/css/style.css" rel="stylesheet" >
	<%-- 
	<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
	--%>
    <!-- Custom styles for this template -->
    <%-- style에 추가했음 
    <link href="navbar-top-fixed.css" rel="stylesheet">
    --%>
  </head>
  <body>
    
<%@ include file="../common/header.jsp" %>
<main class="container">
  <div class="bg-light p-5 rounded">
    <h1>게시판</h1>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a class="btn btn-lg btn-primary" href="/board/write" role="button">글쓰기 &raquo;</a>
  </div>
  <p></p>
  <!-- 리스트 출력 -->
  <div class="list-group w-auto">
  	<c:forEach items="${list}" var="vo">
  	<!-- 출력확인 -->
  	<!-- <p>${vo.bno}</p> -->
    <a href="/board/view?bno=${vo.bno}" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true" >
      <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
      <div class="d-flex gap-2 w-100 justify-content-between">
        <div>
          <h6 class="mb-0">${vo.title }</h6>
          <p class="mb-0 opacity-75">${vo.writer }</p>
        </div>
        <small class="opacity-50 text-nowrap">${vo.regdate }</small>
      </div>
    </a>
    </c:forEach>
    <a href="#" class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
      <img src="https://github.com/twbs.png" alt="twbs" width="32" height="32" class="rounded-circle flex-shrink-0">
      <div class="d-flex gap-2 w-100 justify-content-between">
        <div>
          <h6 class="mb-0">Another title here</h6>
          <p class="mb-0 opacity-75">Some placeholder content in a paragraph that goes a little longer so it wraps to a new line.</p>
        </div>
        <small class="opacity-50 text-nowrap">3d</small>
      </div>
    </a>
  </div>
</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    
<!-- Modal -->
<div id="myModal" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">알림</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
        <button type="button" id="btnModalSave" class="btn btn-primary">저장</button>
      </div>
    </div>
  </div>
</div>
    
    
    
    
    
  </body>
</html>