console.log('reply.js======');

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
	

	// 댓글 조회 및 출력
	function getReplyList(){
		let bno = document.querySelector("#bno").value;
		// 0으로 만들고 test
//		let bno = 0;
		/* let page = '1'; 선언 안하면 오류 납니다.
		reply.js:49 Uncaught ReferenceError: page is not defined
	    at getReplyList (reply.js:49:44)
	    at view?bno=119&pageNo=1&searchField=title&searchWord=:165:2
	    */ 
		let page = '1';
		console.log('bno : ', bno);
		console.log('/reply/list/' + bno + '/' + page);
		// `` 백킷 사용
		console.log(`/reply/list/${bno}/${page}`);
		
		// fetchGet('/reply/list/{bno}/{page}')
		// fetch 한다는건 요청하는것, 그리고 답변이 올때까지 기다리는것
		// 답변이 오면 그 답변을 가지고 다른 함수를 실행시켜줘 → 콜백함수를 실행시켜주는것
		// 함수는 괄호를 붙여줘야 실행시켜주는것! 혹은 변수처럼 이름을 대입하여. 매개변수대입을 하던가
		// 응답결과가 와야지 매개변수를 넣을수있음
		// url : 요청경로
		// callback : 응답결과를 받아 실행시킬 함수
		fetchGet(`/reply/list/${bno}/${page}`, replyView);
	}
	
	function getList(){
		let bno = document.querySelector("#bno").value;
		let page = document.querySelector("#page").value;
		
		console.log(bno);
		fetchGet('/reply/list/' + bno + '/' + page, replyView);
		
		fetch('/reply/list/' + bno + '/' + page)
	    .then(response => response.json())
		.then(map => replyView(map));
	}
	
	function getPage(page){
		document.querySelector("#page").value = page;
		getList();
	}
	
	function replyView(map){
		let list = map.list;      
		let pageDto = map.pageDto;
		console.log(list);
		console.log('pageDto======', pageDto);
		if(list.length == 0){
			// 리스트 사이즈를 확인하여 메세지 처리
			replyDiv.innerHTML = '댓글이 없습니다람쥐람쥐람쥐 '
		// 만약에 리스트 비어있을때 처리, not연산자 가능
		// 값이 없을때 !list 는 false를 반환합니다.
		
//	if(!list){
//	} else {
//		replyDiv.innerHTML = '댓글이 없습니다람쥐람쥐람쥐 '
//	}
		} else {
			// 변수선언
			
//			replyDiv.innerHTML = '댓글목록' // innerHTML는 갑자기 닫히는 현상이 나올수있음,
			// 새로운 변수에 추가해주고 다 만들어지면 마지막에 붙여줍시다
				
			let replyDivStr = '댓글목록'
			+ '	<table class="table text-break text-center">                '
			+ '	  <thead>                            '
			+ '	    <tr>                             '
			+ '	      <th scope="col" class="col-1">#</th>         '
			+ '	      <th scope="col" class="col-8">댓글</th>     '
			+ '	      <th scope="col" class="col-2">작성자</th>      '
			+ '	      <th scope="col" class="col-1">등록일날짜</th>    '
			+ '	    </tr>                            '
			+ '	  </thead>                           '
			+ '	  <tbody>                            ';
			
			// 인덱스를 주는 경우는 유일한 인데 rno를 주어 해보자
//			list.forEach((reply, index) => {
			// 리스트를 돌며 댓글목록을 생성
			list.forEach(reply => {
				replyDivStr += ''
				+ '	    <tr id="tr'+ reply.rno +'" data-value="' + reply.reply + '">                             '
				+ '	      <th scope="row">'+ reply.rno +'</th>         '
				+ '	      <td class="text-start">' + reply.reply 
				+ '			<i class="fa-solid fa-keyboard" onclick="replyUpdate('+ reply.rno +')"></i>'
				+ '			<i class="fa-solid fa-trash" onclick="replyDelete('+ reply.rno +')"></i>'		
				+ '		  </td>                  '
				+ '	      <td>' + reply.replyer + '</td>                  '
				+ '	      <td>' + reply.replyDate + '</td>                  '
				+ '	    </tr>                            ';
			})
			
			
			replyDivStr += ''
			+ '	  </tbody>                           '
			+ '	</table>                             ';
			
			// 화면에 출력
			replyDiv.innerHTML = replyDivStr;
			
			// 페이지 블럭 생성
			let pageBlock = 
				
					  `	<nav aria-label="...">                                              `
					+ `	  <ul class="pagination justify-content-end">                       `;
			
			if(pageDto.prev){
				pageBlock +=
				  `	    <li class="page-item" onclick="getPage(${pageDto.startNo-1})">  `
				+ `	      <a class="page-link">Previous</a>                             `
				+ `	    </li>                                                           `;
			}
			
			for(i=pageDto.startNo;i<=pageDto.endNo;i++){
				let activeStr = (pageDto.cri.pageNo == i) ? 'active' : '';
			
				pageBlock +=
					  `	    <li class="page-item ${activeStr}" onclick="getPage(${i})">  `
					+ `	      <a class="page-link" href="#">${i}</a>                           `
					+ `	    </li>                                                           `;
			}
					
			if(pageDto.next){		
				pageBlock +=	
					  `	    <li class="page-item" onclick="getPage(${pageDto.endNo+1})">                                          `
					+ `	      <a class="page-link" href="#">Next</a>                        `
					+ `	    </li>      `;
			}	
				pageBlock +=
					  `	  </ul>                                                             `
					+ `	</nav>                                                              `;
					
			replyDiv.innerHTML += pageBlock;
		}                                                                       
	
	}
	
	// 답글 등록하기
	function replyWrite(){
		// bno, reply, replyer (로그인한 사용자만 답글을 달수있게)
		
		// 답글 작성시 필요한 데이터 수집 - bno, reply, replyer, view.jsp 확인
		let bno = document.querySelector('#bno').value;
		let reply = document.querySelector('#reply').value;
		let replyer = document.querySelector('#replyer').value;
		
		// 전달할 객체로 생성
		let obj = {bno : bno
					, reply : reply
					, replyer : replyer};
		console.log(obj);
		// url : /reply/insert
		// json 문자열로 파싱해주기
		// url : 요청경로
		// obj : JSON 형식으로 전달할 데이터
		// callback : callback함수(응답결과를 받아서 처리할 함수)
		fetchPost('/reply/insert', obj, replyRes)
		
	}
	
	// 답글 등록, 수정, 삭제의 결과를 처리하는 함수 (결과가 모두 int 1로 나오는)
	function replyRes(map){
		console.log(map);
		// 성공 : 리스트 조회 및 출력
		if(map.result == 'success'){
			getReplyList();
		} else {
		// 실패 : 메세지 출력 (어드바이스 안쓰고 트라이캐치로 묶어서 해봅시다)
			alert(map.message);
		}
		// map으로 결과 얻어와서 list 출력
		
		
	}
	
	// 답글 삭제하기
	function replyDelete(rno){
		console.log('rno', rno);
		// replyRes 요청하고 /reply/delete/ 요청결과 실행해주고
		fetchGet('/reply/delete/' + rno, replyRes);
	}
	
	
	function replyUpdate(rno){
		// 요소를 선택합니다, 가지고 있는 속성을 사용할것이기 때문에 변수에 저장합니다.
		let ReplyEdit = document.querySelector('#tr' + rno);
		let replyTxt = ReplyEdit.dataset.value;
		console.log('ReplyEdit', ReplyEdit);
		console.log('replyTxt', replyTxt);
		ReplyEdit.innerHTML='<td colspan="3">'
						+ ' <div class="input-group"> '
						+ '	  <span class="input-group-text">답글수정</span>                                                            '
						+ '	  <input type="text" aria-label="First name" '
//						+ ' 	class="form-control" id="reply'+rno+'" value="'+ReplyEdit.dataset.value+'">                            '
						+ ' 	class="form-control" id="reply'+rno+'" value="' + replyTxt + '">                            '
						+ '	  <input type="button" onclick="replyEditAction('+ rno +')" aria-label="Last name" class="input-group-text" value="수정하기"> '
						+ ' </div>                                                                                                   '
						+ ' </td>';
		
	}
	
	function replyEditAction(rno) {
		// 파라메터 수집
		let reply = document.querySelector('#reply'+rno).value;
		console.log(reply);
		// 전송할 데이터를 JS 객체로 생성
		let obj = {
					rno : rno
					, reply : reply
		}
		// 서버에 요청
		fetchPost('/reply/editAction', obj, replyRes)
	}