<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 작성하기</h1>
<form method="post" action="/board/write">
	<input type="text" name="title" value="제목">
	<input type="text" name="content" value="내용">
	<input type="text" name="writer" value="작성자">
	<input type="submit">전송하기
</form>
</body>
</html>