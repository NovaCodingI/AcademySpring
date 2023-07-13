console.log('common.js=========');

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