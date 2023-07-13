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
	window.addEventListener('load', function(){
		
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
		
		btnSignupView.addEventListener('click', function(){
		      signupForm.style.display='';
		      signinForm.style.display='none';
		    })

	    btnSigninView.addEventListener('click', function(){
	      signupForm.style.display='none';
	      signinForm.style.display='';
	    })
		
	});
	
	
	
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
	
</script>
<script type="text/javascript" src="/resources/js/common.js"></script>
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
	
		<main class="form-signin w-100 m-auto">
			<form name='signinForm'> 
		    <img class="mb-4" src="resources/css/bootstrap-logo.svg" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		
		    <div class="form-floating">
		      <input type="text" class="form-control start" id="id" placeholder="Id">
		      <label for="id">id</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control end" id="pw" placeholder="Password">
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
		
		  <form name='signupForm' style='display:none'>
		    <img class="mb-4" src="resources/css/bootstrap-logo.svg" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
		
		    <div class="form-floating">
		      <input type="text" class="form-control start" id="signupId" placeholder="회원가입 Id">
		      <label for="id">회원가입 Id</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control middle" id="signupPw" placeholder="회원가입 PassWord">
		      <label for="pw">회원가입 Password</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" class="form-control end" id="pwCheck" placeholder="Password">
		      <label for="pwCheck">PasswordCheck</label>
		    </div>

		    <button class="w-100 btn btn-lg btn-primary signupBtn" type="submit" id='btnSignup'>회원가입</button>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
		  </form>
		  <button id='btnSigninView'>로그인</button>
		  <button id='btnSignupView'>회원가입</button>
		</main>
  
</body>
</html>