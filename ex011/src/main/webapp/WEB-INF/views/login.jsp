<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.104.2">
    <title>Signin Template · Bootstrap v5.2</title>
<title>Insert title here</title>

<script type="text/javascript">
	// msg = '${msg}';
	window.addEventListener('load', function(){
		
		// 로그인
		btnSignin.addEventListener('click', function(e){
			// 기본이벤트 제거
			e.preventDefault();
			// 파라메터수집
			let obj={
					id : document.querySelector('#id').value
					, pw : document.querySelector('#pw').value
			}
			console.log(obj);
			// 요청
			fetchPost('/loginAction', obj, loginCheck)
		})
		
		
		/*
		btnSignUp.addEventListener('click', function(e){
			// 기본이벤트 제거
			e.preventDefault();
			// 파라메터수집
			let obj={
					id : document.querySelector('#id').value
					, pw : document.querySelector('#pw').value
			}
			console.log(obj);
			// 요청
			fetchPost('/loginAction', obj, loginCheck)
		})
		*/
		// 회원가입폼 출력
		btnSignupView.addEventListener('click', function(){
		      signupForm.style.display='';
		      signinForm.style.display='none';
		    })

		// 로그인폼 출력
	    btnSigninView.addEventListener('click', function(){
	      signupForm.style.display='none';
	      signinForm.style.display='';
	    })
	    
	    /*
	    signUpId.addEventListener('blur', function(){
	    	let obj={ id : signUpId.value };
	    	console.log("아이디 체크", obj);
	    	
	    	fetchPost('/idCheck', obj, idCheck);
			// alert('blur');
			// 아이디체크 : 서버에 가야되요 안가야되요? → 서버에 다녀와야 해요
	    })
	    */
	    signUpId.addEventListener('blur', function(){
			// alert('blur');
			// 아이디체크 : 서버에 가야되요 안가야되요? → 서버에 다녀와야 해요
	    	
	    	if(!signUpId.value){
	    		signupMsg.innerHTML = "아이디를 입력 해주세요";
	    		return;
	    	}
	    	
	    	let obj={ id : signUpId.value };
	    	console.log("아이디 체크", obj);

	    	fetchPost('/idCheck', obj, (map)=>{
	    		// alert('test');
	    		
	    		if(map.result == 'success'){
	    			// 아이디 사용 가능
	    			idCheckRes.value = '1'; // 아이디 사용 가능
	    			signUpName.focus();
	    		} else {
	    			// 아이디 사용 불가능
	    			idCheckRes.value = '0'; // 아이디 사용 불가능
	    			signUpId.focus();
	    			signUpId.value='';
	    		}
	    		signupMsg.innerHTML = map.msg; // 메세지 출력
	    	});
	    });
	    
	    pwCheck.addEventListener('blur', function(){
			// 비밀번호 체크 : 서버에 가야되요 안가야되요? → 서버에 안가도 되요
			// alert('blur');
			if(!signUpPw.value){
				signupMsg.innerHTML = '비밀번호를 입력해주세요';
				return;
			}
			if(!pwCheck.value){
				signupMsg.innerHTML = '비밀번호 확인을 입력해주세요';
				return;
			}
			if(signUpPw.value == pwCheck.value){
				pwCheckRes.value=1;
				signupMsg.innerHTML = '';
			} else {
				signupMsg.innerHTML = '비밀번호가 일치하지 않습니다.';
				pwCheckRes.value=0;
				signUpPw.focus();
				pwCheck.value='';
				signUpPw.value='';
			}
	    });
	    
	    btnSignup.addEventListener('click', function(e){
	    	// 기본 이벤트 초기화 ★★★ 페이지 전환 ★★★ form submit
	    	e.preventDefault();
	    	
	    	let id = signUpId.value;
	    	let pw = signUpPw.value;
	    	let name = signUpName.value;
	    	let pwCk = pwCheck.value;
	    	
	    	if(!id){
				signupMsg.innerHTML = '아이디를 입력해주세요';
				return;
	    	}
			if(!pw){
				signupMsg.innerHTML = '비밀번호를 입력해주세요';
				return;
			}
			if(!name){
				signupMsg.innerHTML = '이름를 입력해주세요';
				return;
			}
			if(!pwCk){
				signupMsg.innerHTML = '비밀번호 중복을 확인해주세요';
				return;
			}
			
			
			// 아이디 중복체크 확인
			if(idCheckRes.value != 1){
				signupMsg.innerHTML = '아이디 중복체크를 해주세요';
				signUpId.focus();
				return;
			}
			
			// 비밀번호 일치 확인
			if(idCheckRes.value != 1){
				signupMsg.innerHTML = '비밀번호가 일치하는지 확인 해주세요';
				pwCheck.focus();
				return;
			}
			
	    	obj = {
	    			id : id
	    			, pw : pw
	    			, name : name
	    	}
	    	
	    	console.log('회원가입 obj : ', obj);
	    	// 회원가입 요청
	    	fetchPost('/register', obj, (map)=>{
	    		//console.log(map);
	    		if(map.result == 'success'){
	    			location.href='/login?msg='+map.msg;
	    		} else {
	    			signupMsg.innerHTML = map.msg;
	    		}
	    		
	    	});
	    })
	    
	})
	
	
	
	function loginCheck(map){
		console.log(map);
		if(map.result == 'success'){
			// 로그인 성공 → list 로 이동
			location.href="board/list";
		} else{
			// 실패 → 메세지 처리
			msg.innerHTML = map.msg;
		}
	}
	
	
	/*
	function idCheck(map){
		if(map.result == 'success'){
			// 아이디 사용 가능
			idCheckRes.value = '1';
		} else {
			// 아이디 사용 불가능
			idCheckRes.value = '0';
		}
		signupMsg.innerHTML = map.msg;
	}
	*/
</script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<!-- 네트워크에서 받아옵니다. -->
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/signin.css" rel="stylesheet">

	<style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      
      .logo {
	    height: 52px;
	    width: 72px;
	    object-fit: contain; /* This ensures the image maintains aspect ratio */
	  }
	  
	  
    </style>
    
</head>
<body>
	<%--
	<h1>로그인</h1>
	<div id="msg"></div>
	<form method="post" action="./loginAction">
		id<input type="text" name="id" id="id" value="admin"><br>
		pw<input type="text" name="pw" id="pw" value="1234"><br>
		<input type="submit" id="btnSignUp" value="회원가입">
		<input type="submit" id="btnLogin" value="로그인">
	</form>
	--%>
	
		<main class="form-signin w-100 m-auto center">
		<!-- 로그인 폼 -->
			<form name='signinForm'> 
			
		    <div class="d-flex justify-content-center align-items-center">
			  <img class="mb-4 logo" src="resources/css/bootstrap-logo.svg" alt="">
			</div>
			
		    <div class="text-center">
			  <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			</div>
			
			<div id="msg">${param.msg }</div>
			
		    <div class="form-floating">
		      <input type="text" class="form-control start" id="id" placeholder="Id">
		      <label for="id">id</label>
		    </div>
		    
		    <div class="form-floating">
		      <input type="password" class="form-control end" id="pw" placeholder="Password" required="required">
		      <label for="pw">Password</label>
		    </div>
		    
		    <div class="checkbox mb-3">
		      <label>
		        <input type="checkbox" value="remember-me" class="check-box"> Remember me
		      </label>
		    </div>
		    
		    <button class="w-100 btn btn-lg btn-primary" type="submit" id='btnSignin'>로그인</button>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
		  </form>
		
		<!-- 회원가입 폼 -->
		  <form name='signupForm' style='display:none'>
		    <img class="mb-4 image-container" src="resources/css/bootstrap-logo.svg" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
		    
			<div id="signupMsg"></div>
			
		  	
		    <div class="form-floating">
		      <input type="text" class="form-control start" id="signUpId" placeholder="회원가입 Id">
		      <label for="signUpId">signUp Id</label>
		    </div>
		      
		    <div class="form-floating">
		      <input type="text" class="form-control middle" id="signUpName" placeholder="회원가입  이름">
		      <label for="signUpName">signUp Name</label>
		    </div>
		    
		    <div class="form-floating">
		      <input type="password" class="form-control middle" id="signUpPw" placeholder="회원가입 PassWord">
		      <label for="pw">signUp Password</label>
		    </div>
		    
		    <div class="form-floating">
		      <input type="password" class="form-control end" id="pwCheck" placeholder="Password">
		      <label for="pwCheck">signUp PasswordCheck</label>
		    </div>
		    
		    <button class="w-100 btn btn-lg btn-primary signupBtn" type="submit" id='btnSignup'>회원가입</button>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
		  </form>

	  	idCheckRes <input type="text" value="0" id="idCheckRes">
	  	pwCheckRes <input type="text" value="0" id="pwCheckRes">
	  	
		<button id='btnSigninView'>로그인</button>
		<button id='btnSignupView'>회원가입</button>

		</main>
  
</body>
</html>