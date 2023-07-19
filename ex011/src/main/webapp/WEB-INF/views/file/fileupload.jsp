<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	window.addEventListener('load', function(){
		
		// 리스트 조회
		btnList.addEventListener('click', function(){
			getFileList();
		})
		
		// 파일 업로드
		btnFileupload.addEventListener('click', function(){
			// 웹개발에서 HTML 폼 데이터를
			// JavaScript로 쉽게 조작하고 전송하는 방법을 제공하는 API 입니다
			let formData = new FormData(fileuploadForm);
			formData.append('name', 'ikik');
			
			console.log("=====formData : ", formData);
			
			// FormData값 확인
			// 이름, 값 배열에 들어있다 폼에있는 요소가 수집되는것 inputType이 file 이면 objectType으로 들어옵니다.
			for(var pair of formData.entries()){
				console.log(pair);
				console.log(pair[0] + ' : ' + pair[1]);
//				console.log(pair[0] + ' : ' + pair[1] + ' : ' + pair[2]); // 궁금해서 찍어봄 undefiend 나옴
				if(typeof(pair[1]) == 'object'){
//					let fileName = [pair[1].name];
//					let fileSize = [pair[1].size];
					let fileName = pair[1].name;
					let fileSize = pair[1].fileSize;
					// 파일 확장자, 크기 체크
					// 서버에 전송 가능한 형식인지 확인
					// 최대 전송가능한 용량을 초과 하지 않는지
					
//					console.log("fileName : ",[pair[1].name]);
//					console.log("fileSize : ",[pair[1].size]);
					
					if(!checkExtension(fileName, fileSize)){
						return false;	
					}
					
					
					console.log("fileName : ", fileName);
					console.log("fileSize : ", fileSize);
				}
			}
//			fetch('/file/fileuploadAction'
			fetch('/file/fileuploadActionFetch'
					,{
						method : 'post'
						, body : formData
					})
				.then(response => response.json()) // 함수라 실행해야합니다. .json()
				.then(map => fileuploadRes(map))
//				.then(map => console.log(map))
//				.then(map => (map) =>{
//					console.log('map : ' + map);
//				});
		});
		
	})
	
	function checkExtension(fileName, fileSize){
			let maxSize = 1024 * 1024 * 10;
			// $는 ~로 끝나는
			// .exe, .sh, .zip, .alz 끝나는 문자열
			// 정규표현식 : 특정 규칙을 가진 문자열을 검색하거나 치환 할때 사용
			let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			if(maxSize <= fileSize){
				alert("파일 사이즈 초과");
				return false;
			}
			
			// 문자열에 정규식 패턴을 만족하는 값이 있으면 true, 없으면 flase를 리턴한다
			// TEST 할때 false 값을 주어 아니면.. if(!regex.test(fileName)){
			if(regex.test(fileName)){
				alert("해당 종류의 파일은 업로드 할 수 없습니다.")
				return false;
			}
			return true;
		}
	
	// 콜백함수 대신 ?? fileuploadRes 함수 만들어서 콜백
	function fileuploadRes(map){
			if(map.result=='success'){
				divFileuploadRes.innerHTML = map.msg;
			}
		}
		

	function getFileList(){
		// /file/list/{bno}
		// var는 전역변수 let으로 지정해줍시다.
		let bno = document.querySelector("#bno").value;
		fetch('/file/list/'+bno)
			.then(response => response.json())
			.then(map => viewFileList(map));
	}
	
	function viewFileList(map){
		console.log(map);
		let content = '';
		if(map.list.length > 0){
			map.list.forEach(function(item, index){
//				content += item.fileName + "/" + item.savePath + '<br>';
				content += item.fileName + '<br>';
			})
		} else {
			content = '등록된 파일이 없습니다.';
		}
		divFileupload.innerHTML = content;
	}
	
</script>
</head>
<body>
	<h2> 🎦 안녕 나는 파일 업로드야  🎦 </h2>
	<form method="post" enctype="multipart/form-data"
			action="/file/fileuploadAction" name="fileuploadForm">
	
	<h2> 파일 선택 </h2>
		<%-- 브라우저 버전을 좀 탑니다! multiple="multiple"
		<input type="file" name="files" multiple="multiple"> <br>
		--%>
		bno : <input type="text" id="bno" name ="bno" value="30">
		<br>
		<input type="file" name="files" multiple="multiple"> <br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br>
		
		<button type="submit">파일업로드</button>
		<button type="button" id="btnFileupload">Fetch 파일업로드</button>
		<br>
		<div id="divFileuploadRes">
		res : ${param.msg }
		</div>
	</form>
	
	<h2> 파일 리스트 조회 </h2>
	<button type="button" id="btnList">리스트 조회</button>
	<div id="divFileupload">
	
	</div>
	
</body>
</html>