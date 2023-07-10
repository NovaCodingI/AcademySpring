<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7d9e0e4039.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	
	// 함수안에 있기 때문에 리스트출력이 안됩니다.
	// 1. 서버에 댓글리스트 요청
	function getList(){
		let bno = document.querySelector("#bno").value;
	// url 요청 결과를 받아 옵니다.
	//	fetch(url)
	//	fetch('/reply/list/30') // 고정적인 url이 아닌 원하는 번호를 가져올겁니다.
		fetch('/reply/list/' + bno)
		// response.json() : 요청 결과를 js object형식으로 반환 (object로 파싱하고)
	    .then(response => response.json())
	    // 반환받은 오브젝트를 이용하여 화면에 출력합니다.
		.then(list => replyView(list));
	//	.then(list => console.log(list));
	//  .then(list => callback(list));
	//  .then(map => callback(map));
	}
	
	// 2. 그냥 리스트 받아서 화면에 출력하는 역할만 하는것
	function replyView(list){
		// 콘솔창에 리스트 출력
		console.log(list)
		
		// div 초기화
		
		replyDiv.innerHTML = ''; 
		// list를 받아 reply에 하나씩 넣어줄꺼고 index는 방번호
		// => 화살표 익명의 함수
		
		// 댓글 리스트로부터 댓글을 하나씩 읽어와서 div에 출력
		// (아이템, 인덱스) "" 쌍따옴표 안에 넣어줘야함!
		list.forEach((reply, index)=>{
		// 답글을 DIV에 출력
		replyDiv.innerHTML 
			+= '<figure id="reply'+index+'">'
			+ '		<blockquote class="blockquote">'
			+ '			<p>' + reply.reply 
			
			+ ' <i class="fa-solid fa-file-pen"></i> '
			+ ' <i class="fa-solid fa-trash-can" onclick="replyDelete('+ reply.rno +')"></i> '
			
			+ '</p>'
			+ '		</blockquote>'
			
			+ '		<figcaption class="blockquote-footer">'
			+ ' 	' + reply.replyer
			+ '	<cite title="Source Title">' + reply.replyDate + '</cite>'
			+ '		</figcaption>'
			+ '</figure>';
			/*
			console.log();
			replyDiv.innerHTML += '<br>' + reply.bno;
			replyDiv.innerHTML += '<br>' + reply.rno;
			replyDiv.innerHTML += '<br>' + reply.reply;
			replyDiv.innerHTML += '<br>' + reply.replyer;
			replyDiv.innerHTML += '<br>' + reply.replyDate;
			replyDiv.innerHTML += '<br>' + reply.updateDate;
			*/
		});
	}
	
	// 버튼이 생성되고 나서 (올라오고) 이벤트를 부여(추가) 해야합니다. window.onload
	window.onload = function(){
		// 리스트 조회 및 출력
		getList();
		
		var btnWrite = document.getElementById('btnWrite');
		btnWrite.addEventListener('click', function(){
			// 1. 서버에 전송할 파라메터 수집
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			console.log('bon : ', bno);
			console.log('reply : ', reply);
			console.log('replyer : ', replyer);
			// alert('댓글 작성');
			
			// 2. 전송할 데이터를 javascript객체로 생성 (Object 만듬)
			// 처음부터 문자열로 해도 되지만 'bno' 이런식으로 따옴표를 추가 해야 하기때문에
			// 번거롭기 때문에 문자열로 변환 하여 작업
			let replyObj = {
				  // 이름 : 값
					bno : bno
					, reply : reply
					, replyer : replyer
			};
			
			// 3. 객체를 json 타입으로 문자열로 변환 (전송하기 위해서)
			let replyJson = JSON.stringify(replyObj);
			
			console.log('replyObj : ',  replyObj);
			console.log('replyJson : ',  replyJson);
			
			// 4. 서버에 요청
			fetch('/reply/insert'
					, {method : 'post'
						// 내가 전송할 타입
						, headers : {'Content-Type' : 'application/json'}
						// 전송하고자 하는 데이터 담아줍니다.
						, body : replyJson})
				// 5. 응답처리
				.then(response => response.json())
			//	.then(map => console.log(map)); (확인용)
			// 결과에 대한처리를 하고싶기때문에 함수를 불러주는 것입니다. // 밑에다 replyWriteRes(map) 함수 만들어봅시다
				.then(map => replyWriteRes(map));
			// fetch, get방식은 url만 넘기면 되지만 post방식은 가지고갈 데이터도 포함해야합니다. 
		});
	}
	
	function replyWriteRes(map){
		if(map.result == 'success'){
			// 등록성공
			// 리스트 조회 및 출력
			getList();
		} else {
			// 등록실패
			alert(map.message);
		}
	}
	
	function replyDelete(rno){
			fetch('/reply/delete/' + rno)
		    .then(response => response.json())
		    // 오브젝트에 map은 이름준겁니다. 
			.then(map => replyWriteRes(map));
	}
	
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h2>답글달기</h2>

<div class="input-group mb-3">
	<input type="text" name="bno" id="bno" value="30">
	<input type="text" id="replyer">
	<input type="text" id="reply" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnWrite">댓글작성</span>
</div>

<div id="replyDiv"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>