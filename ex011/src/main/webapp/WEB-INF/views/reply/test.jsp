<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7d9e0e4039.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	
	// 버튼이 생성되고 나서 (올라오고) 이벤트를 부여(추가) 해야합니다. window.onload
	window.onload = function(){
		// 리스트 조회 및 출력
		getList();
		
		var btnWrite = document.getElementById('btnWrite');
		btnWrite.addEventListener('click', function(){
			// 1) 서버에 전송할 파라메터 수집
			let bno = document.querySelector('#bno').value;
			let reply = document.querySelector('#reply').value;
			let replyer = document.querySelector('#replyer').value;
			
			console.log('bno : ', bno);
			console.log('reply : ', reply);
			console.log('replyer : ', replyer);
			// alert('댓글 작성');
			
			// 2) 전송할 데이터를 javascript객체로 생성 (Object 만듬)
			// 처음부터 문자열로 해도 되지만 'bno' 이런식으로 따옴표를 추가 해야 하기때문에
			// 번거롭기 때문에 문자열로 변환 하여 작업
			let replyObj = {
				  // 이름 : 값
					bno : bno
					, reply : reply
					, replyer : replyer
			};
			// 서버에 요청
			// url은 문자열입니다 감싸주셔야 합니다 ''
			fetchPost('/reply/insert', replyObj, replyWriteRes);
			
			/* fetchPost(/reply/insert, replyObj, replyWriteRes); 가 대체했습니다.
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
			*/
		});
	}
	// 함수안에 있기 때문에 리스트출력이 안됩니다.
	// 1. 서버에 댓글리스트 요청
	// . 찍어서 쓰기위해서는 객체타입으로 변환해야합니다. 문자열을 → js object로 변환
//	function getList(){
//	function getList(page){
	function getList(){
		let bno = document.querySelector("#bno").value;
		// 페이지 정보를 화면에서 읽어 오는구나 View페이지에 pageNo를 가져올수도 있기때문에 헷갈릴수있습니다. 그래서 page로 했어요
		let page = document.querySelector("#page").value;
		
		console.log(bno);
		fetchGet('/reply/list/' + bno + '/' + page, replyView);
		
	// url 요청 결과를 받아 옵니다.
	//	fetch(url)
	//	fetch('/reply/list/30') // 고정적인 url이 아닌 원하는 번호를 가져올겁니다.
		fetch('/reply/list/' + bno + '/' + page)
		// response.json() : 요청 결과를 js object형식으로 반환 (object로 파싱하고)
	    .then(response => response.json())
	    // 반환받은 오브젝트를 이용하여 화면에 출력합니다.
//		.then(list => replyView(list));
		.then(map => replyView(map));
	//	.then(list => console.log(list));
	//  .then(list => callback(list));
	//  .then(map => callback(map));
	}
	
	function getPage(page){
		document.querySelector("#page").value = page;
		getList();
	}
	
	// 2. 그냥 리스트 받아서 화면에 출력하는 역할만 하는것
//	function replyView(list){
	function replyView(map){

		let list = map.list;
		let pageDto = map.pageDto;
		
		// 콘솔창에 리스트 출력
		console.log(list);
		console.log(pageDto);
		
		// div 초기화
		
		replyDiv.innerHTML = ''; 
		// list를 받아 reply에 하나씩 넣어줄꺼고 index는 방번호
		// => 화살표 익명의 함수
		
		// 댓글 리스트로부터 댓글을 하나씩 읽어와서 div에 출력
		// (아이템, 인덱스) "" 쌍따옴표 안에 넣어줘야함!
		list.forEach((reply, index)=>{
		// 답글을 DIV에 출력
		replyDiv.innerHTML 
				// 문자열을 비우고 수정할 박스안에다가 index를 주는 이유는 여러개 생길 예정 id값을 주는것
			+= '<figure id="reply'+index+'" data-value="'+ reply.reply +'" data-rno="' + reply.rno + '">'
			+ '		<blockquote class="blockquote">'
			+ '			<p>' + reply.reply 
																// 변수이기 때문에 따옴표 안에 넣으세요 혹은 ``(백틱) 을 사용해서
																// 인덱스 번호를 알아야 박스에 접근, 답글번호
			+ ' <i class="fa-solid fa-file-pen" onclick="replyEdit('+ index +', ' +reply.rno+ ');"></i> '
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
		
		let pageBlock = '';
		pageBlock += ''
		// replyDiv.innerHTML +=
		// 페이지 블럭 생성
				+ '<nav aria-label="...">'
				+   '<ul class="pagination">';
		if(pageDto.prev){
		pageBlock += ''
				// prev 버튼
				//+     '<li class="page-item disabled" onclick="getPage('+(pageDto.startNo-1)+')">'
				+     '<li class="page-item" onclick="getPage('+(pageDto.startNo-1)+')">'
				+       '<span class="page-link">Previous</span>'
				+     '</li>';
		}
				
				
		//let totalPage = Math.ceil(pageDto.total / 10); // 페이지 당 10개라고 가정
		//for(let i = pageDto.startNo; i <= Math.min(pageDto.endNo, totalPage); i++){
			
			//console.log("startNo: " + pageDto.startNo);
			//console.log("endNo: " + pageDto.endNo);
			//console.log("totalPage: " + totalPage);
		
		for(i=pageDto.startNo; i<=pageDto.endNo; i++){
			let activeStr = (pageDto.cri.pageNo == i) ? 'active' : '';
		
		// replyDiv.innerHTML +=
			pageBlock += ''
			     		+ '<li class="page-item '+ activeStr +'" onclick="getPage('+ i +')">'
						+	'<a class="page-link" href="#">'+ i +'</a></li>';
				
				
		}
		//페이지 번호 생성 (반복문 startNo~endNo)
				
				/*
				+     '<li class="page-item active" aria-current="page">'
				+       '<span class="page-link">2</span>' // 액티브 (활성화처리된것)
				+     '</li>'
				+     '<li class="page-item"><a class="page-link" href="#">3</a></li>'
				*/
		
		if(pageDto.next){
			
		// replyDiv.innerHTML +=
			pageBlock += ''
				// next 버튼
				// + 그냥 더하기가 있으면 NaN이 발생할수있습니다. 혹은 문자열로 넣어주거나 ''
				+
				     '<li class="page-item" onclick="getPage('+(pageDto.endNo+1)+')">'
				+       '<a class="page-link" href="#">Next</a>'
				+     '</li>';
		}
			pageBlock += ''
				+   '</ul>'
				+ '</nav>';
				
		replyDiv.innerHTML += pageBlock;
		
		/* alt + shift + a 세로 입력으로 편하게 입력해보세요
		+ ' <nav aria-label="...">                                                     '
		+ '   <ul class="pagination">                                                  '
		+ '     <li class="page-item disabled">                                        '
		+ '       <span class="page-link">Previous</span>                              '
		+ '     </li>                                                                  '
		+ '     <li class="page-item"><a class="page-link" href="#">1</a></li>         '
		+ '     <li class="page-item active" aria-current="page">                      '
		+ '       <span class="page-link">2</span>                                     '
		+ '     </li>                                                                  '
		+ '     <li class="page-item"><a class="page-link" href="#">3</a></li>         '
		+ '     <li class="page-item">                                                 '
		+ '       <a class="page-link" href="#">Next</a>                               '
		+ '     </li>                                                                  '
		+ '   </ul>                                                                    '
		+ ' </nav>                                                                     ';
		*/
	}
	
	
	function replyWriteRes(map){
		if(map.result == 'success'){
			// 등록성공
			// 리스트 조회 및 출력
			getList(); //getList(1);로도 할수있지만 
		} else {
			// 등록실패
			alert(map.message);
		}
	}
	
	function replyDelete(rno){
			fetch('/reply/delete/' + rno)
		    .then(response => response.json())
		    // 오브젝트에 map은 이름준겁니다.  response.json() → map → replyWriteRes(map)
			.then(map => replyWriteRes(map));
	}
	
	// 수정 화면 보여주기
	function replyEdit(index, rno){
		// 요소를 선택합니다, 가지고 있는 속성을 사용할것이기 때문에 변수에 저장합니다.
		let editbox = document.querySelector('#reply'+index);
		let replyTxt = editbox.dataset.value;
		// edit.innerHTML='aaaaaaaaaaaa';
		editbox.innerHTML=''
				+ '<div class="input-group mb-3">'
				+	'<input type="text" id="editReply'+rno+'" value="' + replyTxt + '" class="form-control" username" aria-describedby="basic-addon2">'
				+ 	'<span class="input-group-text" id="btnWrite" onclick="replyEditAction('+rno+')">수정하기</span>'
				+ '</div>';
	}
	
	function replyEditAction(rno){
		
		// 1) 서버에 전송할 파라메터 수집
		let reply = document.querySelector('#editReply'+rno).value;; //document.querySelector('#reply').value; //
		
		// 2) 전송할 데이터를 javascript객체로 생성 (Object 만듬)
		let replyObj = {
			  // 이름 : 값
				rno : rno
				, reply : reply
		};
		
		// 3. 객체를 json 타입으로 문자열로 변환 (전송하기 위해서)
		let replyJson = JSON.stringify(replyObj);
		
		console.log('replyObj : ',  replyObj);
		console.log('replyJson : ',  replyJson);	
		
		// 4. 서버에 요청
		fetch('/reply/editAction'
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
	}
	
// /* reply.js로 파일 빼놓음
	// get방식 요청
	// 매개변수의 callback은 사용자가 입력해주는것이죠
	function fetchGet(url, callback){
//		console.log(url);
//		console.log(callback);
		try{
		// url 요청	
		fetch(url)
			// 요청결과 json 문자열을 javascript 객체로 반환
			.then(response => response.json())
			// callback 함수의 이름을 넘겨주면 괄호를 붙여서 함수를 실행시켜주는겁니다.
			// 콜백함수 실행
			.then(map => callback(map));
		} catch(e){
			console.log('fetchGet', e);
		}
	}
	// post 방식 요청
	// post방식은 데이터를 함께 넘겨주고싶은 것입니다.
	// 공통함수 만들어주는게 굉장히 가벼워 지는것!
	function fetchPost(url, obj, callback){
		try{
			// url 요청	
			fetch(url
					,{
						method : 'post'
						, headers : { 'Content-Type' : 'application/json'}
						, body : JSON.stringify(obj)
					})
				// 요청결과 json 문자열을 javascript 객체로 반환
				.then(response => response.json())
				// callback 함수의 이름을 넘겨주면 괄호를 붙여서 함수를 실행시켜주는겁니다.
				// 콜백함수 실행
				.then(map => callback(map));
			} catch(e){
				console.log('fetchPost', e);
			}
	}
// */
</script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<h2>답글달기</h2>

<div class="input-group mb-3">
	<%-- 이걸 추가하면 게시물이 30번으로 고정되어서 나옵니다.
	<input type="hidden" name="bno" id="bno" value="30">
	--%>
	<input type="text" name="bno" id="bno" value="30">
	<!-- 필드로 page 값 1로 주는 방법도 있습니다. 파라메터로 가지고 다닐 필요가 없어집니다. 요소에 저장하기때문입니다. -->
	<input type="hidden" name="page" id="page" value="1">
	<input type="text" id="replyer" placeholder='작성자'>
	<input type="text" id="reply" class="form-control" placeholder="댓글내용" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <span class="input-group-text" id="btnWrite">댓글작성</span>
</div>

<div id="replyDiv"></div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>