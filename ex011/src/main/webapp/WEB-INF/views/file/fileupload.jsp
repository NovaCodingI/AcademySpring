<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	window.addEventListener('load', function(){
		btnList.addEventListener('click', function(){
			getFileList();
		})
	});

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
				content += item.fileName + "/" + item.savePath + '<br>';
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
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br>
		<input type="file" name="files"> <br>
		
		<button type="submit">파일업로드</button>
		<br>
		res : ${param.msg }
		
	</form>
	
	<h2> 파일 리스트 조회 </h2>
	<button type="button" id="btnList">리스트 조회</button>
	<div id="divFileupload"></div>
	
</body>
</html>