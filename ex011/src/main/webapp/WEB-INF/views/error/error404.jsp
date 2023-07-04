<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 안보여요 못찾아요 눈이 안보여요 </h1>
<h1> 페이지를 찾을수 없습니다.</h1>

🙄 예외 : ${ exception.message }🙄 

 <h4><c:out value="${exception.getMessage()}"></c:out></h4>
 
  <ul>
   <c:forEach items="${exception.getStackTrace() }" var="stack">
     <li><c:out value="${stack}"></c:out></li>
   </c:forEach>
  </ul>


</body>
</html>