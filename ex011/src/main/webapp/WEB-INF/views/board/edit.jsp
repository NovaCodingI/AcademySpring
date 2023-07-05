<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script type="text/javascript">
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
    <h1>게시판 상세페이지</h1>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a class="btn btn-lg btn-primary" href="/board/list" role="button">리스트 &raquo;</a>
  </div>
  <p></p>
  <!-- 글쓰기 -->
  <div class="list-group w-auto">
<!-- 사용자를 입력을 입력받는(글쓰기) 같은 경우에는 post방식을 -->
<!-- list나 상세화면, 페이지를 요청할때는 get방식 -->
	<form method="post" action="/board/write">
	<div class="mb-3">
	  <label for="title" class="form-label">제목</label>
	  <input name="title" id="title" type="text" class="form-control" value='${board.title}' >
	</div>
	<div class="mb-3">
	  <label for="content" class="form-label">내용</label>
	  <textarea class="form-control" id="content" name="content" rows="3" >${board.content}</textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">작성자</label>
	  <input type="text" class="form-control" id="writer" name="writer" value='${board.writer}'>
	</div>
	
	<div class="d-grid gap-2 d-md-flex justify-content-md-center">
		<button type="submit" class="btn btn-primary btn-lg">수정</button>
		<button type="reset" class="btn btn-primary btn-lg">초기화</button>
	</div>
	
	</form>
  </div>
</main>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>