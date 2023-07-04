<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- model.addAttribute("age", age); 을 해주었을때 -->
name : ${name } <br>
age : ${age } <br>

<!-- 넘어온값을 출력하고 싶을때는 EL 표현식을 사용해주면 됩니다. -->
name : ${ param.name }	<br>
age : ${ param.age }	<br>
===============================
<h3>vo객체 출력</h3>
name 	: ${ member.name } 		<br>
age 	: ${ member.age } 		<br>
Date 	: ${ member.dueDate } 	<br>
message : ${ message } 			<br>
===============================
<h3>배열 출력</h3>
ids 	: ${ ids }			<br>
ids 	: ${ param.ids }	<br>


<br>
🍔🍔 햄버거는 핫크리스피버거, 화이트갈릭버거 <br>

<!-- 링크는 어떤방식? form은 어떤방식? -->
<!-- /mapping/requestMapping -->
<!-- context루트가 없으면 편합니다. ../, ./ 를 작성을 구지 않적어도  / 루트경로로 적어준다면 -->
<a href="/mapping/requestMapping">requestMapping 호출(링크방식은 get방식)</a> <br>

<a href="/mapping/getMapping?name=익익&age=55">getMapping 호출</a> <br>

<a href="/mapping/getMappingVO?name=익크엑크&age=77&dueDate=2012-09-13">getMappingVO 호출</a> <br>

<a href="/mapping/getMappingArr?ids=id1&ids=id2&ids=id3">getMappingArr 호출</a> <br>

<a href="/mapping/getMappingList?ids=id1&ids=id2&ids=id3">getMappingList 호출</a> <br>

<form action="/mapping/requestMapping" method="get">
	<input type="submit" value="get전송"> 
</form>
<br>
<form action="/mapping/requestMapping" method="post">
	<input type="submit" value="post전송">
</form>

<h3>객체 리스트를 파라메터로 전달 해봅시다</h3>
	파라메터 전달 방법 :
	list[0].name=ikik&list[0].age=123&list[1].name=admin&list[1].age=120	<br>

<!-- 요청 타겟에서 유효하지 않은 문자가 발견되었습니다. 유효한 문자들은 RFC 7230과 RFC 3986에 정의되어 있습니다. -->
<!-- 대괄호에서 에러가 나는데 스크립트에서 인코딩URL 함수를 이용하여 -->
<script>
	function voList(){
		let url = "/mapping/getMappingMemberList"
					+ "?list[0].name=ikik&list[0].age=123&list[1].name=admin&list[1].age=120"
		url = encodeURI(url);
		console.log(url);
		alert(url);
		location.href = url;
	}
</script>
<a href="/mapping/getMappingMemberList?list[0].name=ikik&list[0].age=123&list[1].name=admin&list[1].age=120">getMappingMemberList 호출</a> <br>
<a href="#" onclick="voList()">객체리스트 전달</a> <br>
<br>

</body>
</html>