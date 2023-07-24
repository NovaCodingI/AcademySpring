<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		// 폼이름을 확인해주세요 <form name="viewForm"
		viewForm.action = url;
		viewForm.submit();
	}
	
	window.addEventListener('load', function(){
		btnList.addEventListener('click', function(){
			viewForm.action='/board/list';
			viewForm.method="get";
			viewForm.submit();
		});
		
		// 파일목록 조회 및 출력
		getFileList();
		
	});
	
	function getFileList(){
		// /file/list/{bno}
		// var는 전역변수 let으로 지정해줍시다.
		// bno 값은 화면으로부터 가지고 오지 않습니다.
//		let bno = document.querySelector("#bno").value;
		let bno = '${board.bno}';
		if(bno){
			fetch('/file/list/'+bno)
				.then(response => response.json())
				.then(map => viewFileList(map));
		}
	}
	
	function viewFileList(map){
		console.log(map);
		let content = '';
		if(map.list.length > 0){
			
			content += ''
					+ '<div class="mb-3">                                                   '
					+ '  <label for="attachFile" class="form-label">첨부파일 목록</label>   '
					+ '  <div class="form-control" id="attachFile">                         ';
					
					map.list.forEach(function(item, index){
						let savePath = encodeURIComponent(item.savePath)	
						console.log(item.savePath);				
//						content += item.fileName + "/" + item.savePath + '<br>';
						content += ""
								+ "<a href='/file/download?fileName="
								+ savePath+"'>"
//								+ encodeURIComponent(item.savePath) + '">'
//								+ item.savePath + '">' 요청 타겟에서 유효하지 않은 문자가 발견되었습니다. 유효한 문자들은 RFC 7230과 RFC 3986에 정의되어 있습니다. 400 에러
								+ item.fileName + '</a>'
								+ '<i onclick="attachFileDelete(this)"'
								+ ' data-bno="'+item.bno+'" data-uuid="'+item.uuid+'"'
								+ ' class="fa-solid fa-trash-arrow-up"></i>'
								+ '<br>';
					})
					
			content += '  </div>                                                             '
					+ '</div>                                                               ';
			
		} else {
			content = '등록된 파일이 없습니다.';
		}
		divFileupload.innerHTML = content;
	}
	
	function attachFileDelete(e){
		let bno = e.dataset.bno;
		let uuid = e.dataset.uuid;
		// 값이 유효하지 않은 없는 경우 메세지 처리
		// fetch 요청
		fetch(`/file/delete/\${uuid}/\${bno}`)
		// \${ } el 표현식 → \${}, jsp에서 el표현식은 null, undefiend 값, 주석처리하여 \${ }(el 표현식으로 처리 하지 않음)
//		fetch('/file/delete/' + uuid + '/' + bno)
	    // 오브젝트에 map은 이름준겁니다.  response.json() → map → replyWriteRes(map)
	    .then(response => response.json())
		.then(map => fileDeleteRes(map));
//		(e.dataset.aaa)?'true':'false';
//		console.log(e.dataset.bno
//					, e.dataset.uuid
//					, e.dataset.aaa);
//		console.log(e);
	}
	
	// 삭제 결과 처리
	function fileDeleteRes(map){
		if(map.result == 'success'){
			console.log(map.msg);
			// 리스트 조회
			getFileList();
		} else {
			alert(map.msg);
		}
	}
	
</script>

<main class="container">
  <div class="bg-light p-5 rounded">
    <h1>글쓰기 페이지</h1>
    <p class="lead">부트스트랩을 이용한 게시판 만들기</p>
    <a class="btn btn-lg btn-primary" href="#" id="btnList" role="button">리스트 &raquo;</a>
  </div>
  <p></p>
  <!-- 글쓰기 -->
  <div class="list-group w-auto">
<!-- 사용자를 입력을 입력받는(글쓰기) 같은 경우에는 post방식을 -->
<!-- list나 상세화면, 페이지를 요청할때는 get방식 -->
	<form method="post" enctype="multipart/form-data" name="viewForm" action="/board/write">
	
	<!-- 페이지, 검색유지 -->
	<!-- 쿼리스트링에 name은 value, name값을 잘 맞추어서 작성해주어야합니다. -->
	<!-- 삼항연산자 사용하여 예외처리 
	org.springframework.validation.BeanPropertyBindingResult: 
	1 errors Field error in object 'criteria' on field 'pageNo': rejected value []; 
	codes [typeMismatch.criteria.pageNo,typeMismatch.pageNo,typeMismatch.int,typeMismatch] -->
	<%--
	<input type="text" name="pageNo" id="pageNo" value="${param.pageNo==''?'1':param.pageNo}">
	--%>
	<%--
	<input type="number" name="pageNo" id="pageNo" min="1" value="${empty param.pageNo ? 1 : param.pageNo}">
	--%>
	<c:if test="${not empty param.pagNo}">
		<input type="text" name="pageNo" value="${param.pagNo}">
	</c:if>
	<c:if test="${empty param.pagNo}">
		<input type="text" name="pageNo" value="1">
	</c:if>
	
	<input type="text" name="searchField" id="searchField" value="${param.searchField }">
	<input type="text" name="searchWord" id="searchWord" value="${param.searchWord }">
	
	<div class="mb-3">
	  <label for="title" class="form-label">제목</label>
	  <input name="title" id="title" type="text" class="form-control" value="${board.title}">
	  <%-- <input name="title" id="title" type="text" readonly class="form-control-plaintext" value='타이틀' > --%>
	</div>
	<div class="mb-3">
	  <label for="content" class="form-label">내용</label>
	  <textarea class="form-control" id="content" name="content" rows="3">${board.content}</textarea>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">작성자</label>
	  <!-- 작성화면일때 -->
	  <c:if test="${empty board.writer }">
	  <input type="text" class="form-control" id="writer" name="writer" readonly="readonly" value="${userId}">
	  </c:if>
	  <!-- 수정화면일때 -->
	  <c:if test="${not empty board.writer }">
	  <input type="text" class="form-control" id="writer" name="writer" readonly="readonly" value="${board.writer}">
	  </c:if>
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">첨부파일</label>
	  <input class="form-control" type="file" id="files" name="files" multiple="multiple">
	</div>
	<!-- 첨부파일 -->
	<div id="divFileupload">
	</div>
	<div class="d-grid gap-2 d-md-flex justify-content-md-center">
		<!-- bno 값이 있으면 수정하기 res로 변수이름 담아주기 -->
		<c:if test="${not empty board.bno }" var="res">
			<!-- 빈문자열 오류가 날수있어요 -->
			<input type="hidden" name="bno" value="${board.bno}">
			<button type="submit" class="btn btn-primary btn-lg" 
				onclick="requestAction('/board/editAction')">수정하기</button>
				
		</c:if>
		<!-- 없으면 등록하기 -->
		<c:if test="${not res}">
			<button type="submit" class="btn btn-primary btn-lg">글쓰기</button>
		</c:if>
		
		<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
	</div>
	</form>
  </div>
</main>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
	
</body>
</html>