<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<script type="text/javascript">

function requestAction(url){
	viewForm.action=url;
	viewForm.submit();
}

</script>
<script>
/*
function submitForm(action) {
    var form = document.getElementById('editForm');
    form.action = action;
    form.submit();
}
*/
</script>

<main class="container">
  <div class="bg-light p-5 rounded">
    <h1>게시판 상세페이지</h1>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <%-- 
    <a class="btn btn-lg btn-primary" href="/board/list" role="button">리스트 &raquo;</a>
   	href="/board/list?pageNo=${pageDto.cri.pageNo}&searchField=${pageDto.cri.searchField}&searchWord=${pageDto.cri.searchWord}"
    --%>
    <a class="btn btn-lg btn-primary" 
   	href="/board/list?pageNo=${param.pageNo}&searchField=${param.searchField}&searchWord=${param.searchWord}"
    	role="button">
    	리스트 &raquo;</a>
  </div>
  <p></p>
  <!-- 상세보기 -->
  <div class="list-group w-auto">
<!-- 사용자를 입력을 입력받는(글쓰기) 같은 경우에는 post방식을 -->
<!-- list나 상세화면, 페이지를 요청할때는 get방식 -->
	<%--
	<form method="post" action="/board/edit">
	--%>
	<%--
	form method="get" id="viewForm" 로 불러오면 get 오류나요 name='viewForm'으로 변경해주세요
	<form method="post" action="/board/delete?bno=${board.bno}">
	--%>
	<form method="get" name="viewForm">
	<%-- 
	<form id="editForm" method="post" action="/board/edit">
	--%>
	<input type="hidden" name="bno" value="${board.bno }">
	<div class="mb-3">
	  <label for="title" class="form-label">제목</label>
	  <input name="title" id="title" type="text" readonly class="form-control" value='${board.title}' >
	</div>
	<div class="mb-3">
	  <label for="content" class="form-label">내용</label>
	  <textarea class="form-control" id="content" name="content" rows="3" readonly>${board.content}</textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">작성자</label>
	  <input type="text" readonly class="form-control" id="writer" name="writer" value='${board.writer}'>
	</div>
	
	<div class="d-grid gap-2 d-md-flex justify-content-md-center">
		<button type="submit" class="btn btn-primary btn-lg" onclick="requestAction('/board/edit')">수정</button>
		<button type="button" class="btn btn-primary btn-lg" onclick="location.href='/board/delete?bno=${board.bno}'">삭제</button>
	<%-- 
		<!-- 함수를 이용해도 되고 location.href를 이용해도 되고 근데 location으로 하니까 반환이 안되네? 
			대신 type을 button으로 해야됩니다. 돌아온다음 리다이렉트가 되어야하는데 submit으로 하면 삭제되고 리스트가 넘어가요 -->
		<button type="submit" class="btn btn-primary btn-lg" onclick="requestAction('/board/delete')">삭제</button>
		<button type="button" class="btn btn-primary btn-lg" onclick="submitForm('/board/edit')">수정</button>
		<button type="button" class="btn btn-primary btn-lg" onclick="submitForm('/board/delete')">삭제</button>
	--%>
	</div>
	</form>

  </div>
</main>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>