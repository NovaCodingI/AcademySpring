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

	<!-- 스타일시트 -->
	<link href="/resources/css/style.css" rel="stylesheet" >
	
	<!-- JS -->
	<script src="/resources/js/reply.js"></script>
	
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

window.addEventListener('load', function(){
// window.onload = function(){
	// 수정페이지로 이동
	btnEdit.addEventListener('click', function(){
		
		document.getElementById('pageNo').value = '${param.pageNo}';
		document.getElementById('searchField').value = '${param.searchField}';
		document.getElementById('searchWord').value = '${param.searchWord}';
		    
		viewForm.action='/board/edit';
		viewForm.submit();
	});
	
	// 삭제 처리 후 리스트 페이지로 이동
	btnDelete.addEventListener('click', function(){
//		onclick="location.href='/board/delete?bno=${board.bno}'"
		// form method="get" name="viewForm" 에 bno가 있기때문에 적을 필요가 없음
		viewForm.action='/board/delete';
		viewForm.submit();
	
	});
	
	// 리스트 페이지로 이동
	btnList.addEventListener('click', function(){
//		/board/list?pageNo=${sessionScope.pageNo}&searchField=${sessionScope.searchField}&searchWord=${sessionScope.searchWord}
		viewForm.action='/board/list';
		viewForm.submit();
	});
	
	// 답글 등록 버튼
	btnReplyWrite.addEventListener('click', function(){
		replyWrite();
		
	});
	
	// 댓글목록 조회 및 출력, reply.js 에 작성
	getReplyList();
	
});

/* 필요없어짐
function requestAction(url){
	viewForm.action=url;
	viewForm.submit();
}
*/


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
    <%-- 첫페이지로 전환 
    <a class="btn btn-lg btn-primary" 
   	href="/board/list?pageNo=${empty param.pageNo ? 1 : param.pageNo}&searchField=${param.searchField}&searchWord=${param.searchWord}"
    	role="button">
    	리스트 &raquo;</a>
   	--%>
   	<!-- 세션을 사용하면 서버에 부하가 생길수 있으므로 많은 양의 세션데이터로 인해 서버에 메모리가 금방 차게됩니다. -->
   	<%-- 버튼에 적용되어있는 이벤트들을 상단에 함수로 정의 해보기 
    <a class="btn btn-lg btn-primary" 
   	href="/board/list?pageNo=${sessionScope.pageNo}&searchField=${sessionScope.searchField}&searchWord=${sessionScope.searchWord}"
   		role="button">
   		리스트 &raquo;
   	--%>
    <a class="btn btn-lg btn-primary" 
   	href="#" id="btnList"
   		role="button">
   		리스트 &raquo;
</a>
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
	
	<!-- 파라메터 포멧상단에 적용 -->
	<%--
	<input type="text" name="pageNo" id="pageNo" value="${sessionScope.pageNo }">
	<input type="text" name="searchField" id="searchField" value="${sessionScope.searchField }">
	<input type="text" name="searchWord" id="searchWord" value="${sessionScope.searchWord }">
	 --%>
	 
	<input type="text" name="pageNo" id="pageNo" value="${param.pageNo }">
	<input type="text" name="searchField" id="searchField" value="${param.searchField }">
	<input type="text" name="searchWord" id="searchWord" value="${param.searchWord }">
	
	<%-- 
	<form id="editForm" method="post" action="/board/edit">
	--%>
	<input type="text" name="bno" id="bno" value="${board.bno }">
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
		<%-- 버튼으로 변경 후 id 줍시다. 추가로 상단에  onclick 정의 
		<button type="submit" class="btn btn-primary btn-lg" onclick="requestAction('/board/edit')">수정</button>
		--%>
		<button type="button" id="btnEdit" class="btn btn-primary btn-lg">수정</button>
		<%-- 상단에 onclick 정의
		<button type="button" id="btnDelete" class="btn btn-primary btn-lg" onclick="location.href='/board/delete?bno=${board.bno}'">삭제</button>
		--%>
		<button type="button" id="btnDelete" class="btn btn-primary btn-lg">삭제</button>
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
  <p></p>
  <input type ="text" id="replyer" value="작성자">
  <div class="input-group">
	  <span class="input-group-text">답글작성</span>
	  <input type="text" name="page" id="page" value="1">
	  <input type="text" aria-label="First name" class="form-control" id="reply">
	  <input type="button" id="btnReplyWrite" aria-label="Last name" class="input-group-text" value="등록하기">
  </div>
  
  <!-- 댓글 리스트 -->
  <div id="replyDiv"></div>
  <%--
  <%@include file="../reply/test.jsp" %>
  --%>
</main>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>