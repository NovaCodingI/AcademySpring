package com.ikik.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikik.service.FileuploadService;
import com.ikik.vo.FileuploadVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
public class FileuploadController extends CommonRestController{

	@GetMapping("/file/fileupload")
	public void fileupload() {
	
	}
	
	// 문자로 인식시켜주려고 \\ 두개 쓴거입니다. \
	// c:\ upload\2023\07\18
	// c:\\upload\2023\07\18
	private static final String ATTACHS_DIR = "c:\\upload\\";
	/**
	 * 전달된 파일이 없는 경우
	 * enctype="multipart/form-data" 오타
	 * 설정이 안되었을때
	 * 		commons-fileupload 라이브러리 추가
	 * 		id="multipartResolver" bean 생성
	 * @param files
	 * @return
	 */
	// void 라고 해놨짜나여 /file/fileuploadAction 못찾잖아영 404 오류 나자나여!!
	@PostMapping("/file/fileuploadAction")
//	public void fileAction(List<MultipartFile> files) {
	// 컨트롤러가 수집할때 input에 name속성을 보고 들어오는것이기 때문에 일치해야한다.
	public String fileAction(List<MultipartFile> files
								, int bno
								, RedirectAttributes rttr) {
		
		int insertRes = fileupload(files, bno);
		
		// 람다식는 외부에서 사용하는 전역변수를 사용할수 없다고 에러가 납니다.
		// Local variable insertRes defined in an enclosing scope must be final or effectively final
		// continue 나 break 는 forEach에서 사용할수없다.
//		files.forEach(file ->{
		// 향상된 for문 공통로직으로 처리 - 호출만 할꺼임 public int fileupload() {

//		}	
//		});
		// return "file/fileupload";
		// 루트 경로 잡아주셔야 합니다 안잡아주시면 현재경로에서 두번 나오게 됩니다!
		// url은 처음 받아오는 요청에 의해서 받아오는거거든요!
		String msg = insertRes + "건 저장 되었습니다.";
		rttr.addAttribute("msg", msg);
		return "redirect:/file/fileupload";
//		return "redirect:/file/fileupload?msg="+msg;
		// ★페이지를 요청합니다 응답으로 jsp 파일을 보내줍니다.★
	}
	
	@PostMapping("/file/fileuploadActionFetch")
//	public void fileAction(List<MultipartFile> files) {
	public @ResponseBody Map<String, Object> fileActionFetch(List<MultipartFile> files
								, int bno) {
		
		log.info("fileuploadActionFetch");
		int insertRes = fileupload(files, bno);
		log.info("업로드 건수 : " + insertRes);
		return responseMapMessage("success", insertRes + "건 저장 되었습니다.");

	}
	
	
	@Autowired
	FileuploadService service;
	// path로 부터 변수를 가지고 올겁니다.
	@GetMapping("/file/list/{bno}")
	// json 형식으로 받아줄수 있도록 @ResponseBody
	public @ResponseBody Map<String, Object> fileuploadList(@PathVariable("bno") int bno) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.getList(bno)); 
		
		return map;
	}
	
	// 중복 방지용
	//		업로드 날짜를 폴더 이름으로 사용
	//		2023\07\18
	public String getFolder() {
		LocalDate currentDate = LocalDate.now();
		String uploadPath
			= currentDate.toString().replace("-", File.separator)
				+ File.separator;
		log.info("======CurrentDate : " + currentDate);
		log.info("======경로 : " + uploadPath);
		
		// 폴더 생성 (없으면)
		// 위에서 생성해 놓은 경로 복사
		File saveDir = new File(ATTACHS_DIR + uploadPath);
		if(!saveDir.exists()) {
			// s 가 붙어있으면 여러개 생성가능, 없으면 하나 밖에 생성 안됌
			if(saveDir.mkdirs()) {
				log.info("폴더생성!!");
			} else {
				log.info("폴더생성 실패!!");
			}
		}
		
		return uploadPath;
	}
	
	public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();
		String uploadPath
																	// 파일구분자 추가
			= currentDate.toString().replace("-", File.separator) + File.separator;
		log.info("======CurrentDate : " + currentDate);
		log.info("======경로 : " + uploadPath);
		
		System.out.println("======CurrentDate : " + currentDate);
		System.out.println("======경로 : " + uploadPath);
	}
	
	/**
	 * 첨부파일 저장 및 데이터 베이스에 등록
	 * @param files
	 * @param bno
	 * @return
	 */
	public int fileupload(List<MultipartFile> files
							, int bno)  {
		
		int insertRes = 0;
		
		for(MultipartFile file : files) {
			// 선택된 파일이 없는 경우 다음 파일로 이동
			if(file.isEmpty()) {
				continue;
			}
			// 파일이 비었을때는 그냥 넘어가도록 처리
//			if(!file.isEmpty()) {
			log.info("=== ofileName : " + file.getOriginalFilename());
			// jsp 에서 넘긴이름
			log.info("=== Name : " + file.getName());
			log.info("=== Size : " + file.getSize());
			
			try {
				// UUID
				/**
				 * universally unique identifier
				 * 소프트웨어 구축에 쓰이는 식별자(중복되지않는 값) 표준
				 * 파일이름이 중복되어 파일이 소실되지 않도록 uuid를 붙여서 저장 
				 */
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid + "_" + file.getOriginalFilename();
				// 경로지정
//				File sFile = new File(pathname);
				
				String uploadPath = getFolder();
				// dir 경로
				// c:/upload/2023/7/18/  ▶ return str.replace("-", File.separator);
				// 년/월/일 (폴더안에) 추가
				File sFile = new File(ATTACHS_DIR 
											// 경로 (2023\\07\\18) 반환
											+ uploadPath
											+ saveFileName);
				
				// file(원본파일) sFile(저장 대상 파일)에 저장
				file.transferTo(sFile);
				
				FileuploadVO vo = new FileuploadVO();
				// 주어진 파일의 Mime유형
				String contentType = Files.probeContentType(sFile.toPath());
				
				// Mime타입을 확인하여 이미지인 경우 썸네일을 생성
				if(contentType != null
						&& contentType.startsWith("image")) {
					vo.setFileType("I");
					String thmbnail = ATTACHS_DIR 
							// 경로 (2023\07\18) 반환
							+ uploadPath
							+ "s_"
							+ saveFileName;
					// 썸네일 생성
					// 원본파일, 크기, 저장될경로
//					Thumbnails.of(원본파일).size(크기).toFile(파일경로);
					Thumbnails
							.of(sFile)
							.size(100,100)
							.toFile(thmbnail);
				} else {
					vo.setFileType("F");
				}
				
				vo.setBno(bno);
				vo.setFileName(file.getOriginalFilename());
//				vo.setFileType("I");
				vo.setUploadPath(uploadPath);
				vo.setUuid(uuid.toString());
				
				// 서버에 저장하기
				int res = service.insert(vo);
				
				if(res>0) {
					insertRes++;
				}
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return insertRes;
	}
	
	@GetMapping("/file/delete/{uuid}/{bno}")
	public Map<String, Object> delete(@PathVariable("uuid") String uuid
										, @PathVariable("bno") int bno){
		int res = service.delete(bno, uuid);
		if(res>0) {
			return	responseDeleteMap(res);
		} else {
			return	responseDeleteMap(res);
		}
	}
	
	/**
	 * 파일다운로드
	 * 		컨텐츠타입을 다우놀드 받을 수 있는 형식으로 지정하여
	 * 		브라우저에서 파일을 다운로드 할 수 있게 처리
	 * @param fileName
	 * @return
	 */
	@GetMapping("/file/download")
	public @ResponseBody ResponseEntity<byte[]> download(String fileName) {
		// ResponseEntity 를 이용하면 헤더를 지정해줄 수 있습니다. 결과번호를 세팅해줄 수 도있고..
		log.info("download file : " + fileName);
		HttpHeaders headers = new HttpHeaders();
		
		File file = new File(ATTACHS_DIR + fileName);
		
		if(file.exists()) {
			// 컨텐츠타입을 지정
			// APPLICATION_OCTET_STREAM : 이진 파일의 콘텐츠 유형
			headers.add("contentType"
					, MediaType.APPLICATION_OCTET_STREAM.toString());
			// 컨텐츠에 대한 추가 설명 및 파일이름 한글처리
			try {
				headers.add("Content-Disposition"
							, "attachment; filename=\""
							+ new String(file.getName().getBytes("UTF-8"),"ISO-8859-1") + "\"");
//							+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");
				return new ResponseEntity<>(
							FileCopyUtils.copyToByteArray(file)
							, headers
							, HttpStatus.OK
						);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		return new ResponseEntity<>(HttpStatus.OK); 오류나지말라고 처음에 만들어둔 문장 필요X
	}
}
