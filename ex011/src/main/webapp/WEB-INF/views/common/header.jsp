<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/7d9e0e4039.js" crossorigin="anonymous"></script>
<script type="text/javascript">
	/* 스크립트로 메세지처리를 하게되면 통일성이 떨어져요?
	// 메세지 처리
	//let msg = '${param.msg}'; // 파라메터로 넘어갔기때문에 파라메터로 넘어가요!
	let msg = '${msg}'; // 세션영역에 저장되기 때문에 addFlashAttribute 방식일때는 이렇게 해주세요
	if(msg!=''){
		alert(msg);
	}
	*/

	/*
		부트스트랩을 이용한 모달창 띄우기 
		
		1. css, js 파일 추가 하기
		2. 모달요소 복사 
			타이틀및 메세지 수정
		3. 모달창 열기
			자바스크립트를 이용해서 모달객체 생성후 show()메서드 호출
		4. 모달창 닫기(닫기버튼 클릭, 배경화면 클릭)
			모달창이 닫히면 자바스크립트를 이용해서 뒤로가기 실행
			-> 모달창이 닫히면서 발생하는 이벤트(hidden.bs.modal)에 뒤로가기 추가
	*/
	let msg = '${msg}';
	
	window.addEventListener('load', function(){
//	window.onload = function(){
		if(msg != ''){
			// 메세지 출력
			document.querySelector(".modal-body").innerHTML = msg;
			// 버튼 출력 제어
			document.querySelector("#btnModalSave").style.display='none'
			// 모달 생성
			let myModal = new bootstrap.Modal(document.getElementById('myModal'), {
				  keyboard: false
			});
			// 모달 보여주기
			myModal.show();
//		}
		};
		
		const myModalEl = document.getElementById('myModal')
		// 모달창이 닫힐때 이벤트 처리
		// myModalEl.addEventListener('hidden.bs.modal', event => {
	    // 뒤로가기
		// history.go(-1);
		// })
	});
	</script>

</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Fixed navbar</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled">Disabled</a>
        </li>
      </ul>
      <form class="d-flex" role="search">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>


<!-- Modal -->
<div id="myModal" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">알림</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
        <button type="button" id="btnModalSave" class="btn btn-primary">저장</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>