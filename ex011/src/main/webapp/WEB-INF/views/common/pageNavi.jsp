<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .active .page-link {
        background-color: black;
        color: white;
    }
</style>
<script type="text/javascript">
	function go(page){
		/*
		document.searchForm.action = "/board/list";
		document.searchForm.pageNo.value=page;
		document.searchForm.submit();
		*/
		document.searchForm.pageNo.value=page;
		// 확인용도 알람
		// alert(document.searchForm.pageNo.value);
		document.searchForm.submit();
		
	}

</script>
</head>
<body>

	<!-- 페이지 블럭 생성 -->
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-end">
	  <!-- 버튼에 대한 비활성화 처리 disabled -->
	    <li class="page-item ${pageDto.prev? '':'disabled' }">
	    <%-- board 버전
	     <input class="page-link" type='button' value='Previous' onclick='go(${pageDto.startNo-1})'>
	     --%>
		 <a class="page-link"
		  <c:if test="${pageDto.prev}">
		  	onclick='go(${pageDto.startNo-1})'
		  </c:if>
		  href='#'>Previous</a>
	     <%-- 
	      <a class="page-link" href="/board/list?pageNo=${pageDto.startNo - 1}">Previous</a>
	     --%>
	    </li>
		<c:forEach begin="${pageDto.startNo}" end="${pageDto.endNo}" var="i">
	    <li class="page-item <c:if test="${i == pageDto.currentNo}">active</c:if>">
	    	<%-- 링크버전 잘되는지 안되는지 모름
	    	<input class="page-link}" type='button' value='${i }' onclick='go(${i})'>
	    	--%>
	    	<a class="page-link" onclick='go(${i})'>${i }</a>
	    	<%-- board 버전
	    	<input class="page-link ${pageDto.cri.pageNo==i ? 'active':''}" type='button' value='${i }' onclick='go(${i})'>
	    	--%>
	    	<%-- 링크버전 잘되는지 안되는지 모름
	    	<a class="page-link" href="/board/list?pageNo=${i}">${i}
	    	--%>
	    </li>
	    </c:forEach>
	    <li class="page-item ${pageDto.next? '':'disabled' }">
	    	<input class="page-link" type='button' value='Next' onclick='go(${pageDto.endNo+1})'>
	    <%-- 
	      <a class="page-link" href="/board/list?pageNo=${pageDto.endNo + 1}">Next</a>
	    --%>
	    </li>
	  </ul>
	</nav>

</body>
</html>