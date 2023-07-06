<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
페이지 dto : ${pageDto } <br>
페이지 cri : ${pageDto.cri} <br>
페이지 cri pageNo : ${pageDto.cri.pageNo} <br>
페이지 cri searchField : ${pageDto.cri.searchField} <br>
페이지 cri searchWord : ${pageDto.cri.searchWord} <br>

	<form class="row g-3" action="/board/list" 
			method="get" name="searchForm">
	<%-- 
	<input type="text" name="pageNo" value="1">
	--%>
	<!-- Dto가 수집할때 페이지가 ... -->
	<input type="text" name="bno" value="">
	<input type="text" name="pageNo" value="${pageDto.cri.pageNo}">
	  <div class="row g-3 justify-content-center">
		  <div class="col-sm-3">
		    <select name="searchField" class="form-select" 
		    	aria-label="Default select example">
			  <option value="title" <c:if test="${pageDto.cri.searchField == 'title'}">selected</c:if>>제목</option>
			  <option value="content" ${pageDto.cri.searchField == 'content' ? 'selected' : ''}>내용</option>
			  <option value="writer" ${pageDto.cri.searchField eq 'writer' ? 'selected' : ''}>작성자</option>
			</select>
		  </div>
		  
		  <div class="col-sm-6">
		    <label for="searchWord" class="visually-hidden">검색창</label>
		    <input name="searchWord" type="text" class="form-control" id="searchWord"
		    	placeholder="검색어" value="${pageDto.cri.searchWord}">
		  </div>
		  <%--
		  <div class="col-auto">
		  --%>
		  <div class="col-sm-3">
		  <%-- 
		    <button type="submit" class="btn btn-primary mb-3 w-100">검색</button>
		  --%>
		  	<!-- 스크립트 호이스팅으로 인해 다른페이지에 함수가 있어도 사용할 수 있습니다. -->
		    <button type="submit" class="btn btn-primary mb-3 w-100" onclick="go(1)">검색</button>
		  </div>
	  </div>
	</form>
	
</body>
</html>