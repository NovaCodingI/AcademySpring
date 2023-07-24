package com.ikik.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikik.controller.FileuploadController;
import com.ikik.mapper.FileuploadMapper;
import com.ikik.vo.FileuploadVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Service
@Log4j
public class FileuploadServiceImpl implements FileuploadService {

	@Autowired
	FileuploadMapper mapper;
	
	@Override
	public List<FileuploadVO> getList(int bno) {

		return mapper.getList(bno);
	}

	@Override
	public int insert(FileuploadVO vo) {

		return mapper.insert(vo);
	}

	@Override
	public int delete(int bno, String uuid) {
		// 파일 삭제
		
		// 삭제할 파일 조회
		
		// 삭제
		FileuploadVO vo = mapper.getOne(bno, uuid);
		String savePath = vo.getSavePath();
		String s_savePath = vo.getS_savePath();
		System.out.println("savePath : " + savePath);
		System.out.println("s_savePath : " + s_savePath);
		if(savePath != null && savePath.equals("")) {
			File file = new File(FileuploadController.ATTACHS_DIR
									+ savePath);
			if(file.exists()) {
				if(!file.delete()) {
					System.err.println("path : " + savePath);
					System.err.println("파일 삭제 실패!");
				}
			}
		}
		if(s_savePath != null && s_savePath.equals("")) {
			File file = new File(FileuploadController.ATTACHS_DIR
									+ s_savePath);
			if(file.exists()) {
				if(!file.delete()) {
					System.err.println("path : " + s_savePath);
					System.err.println("파일 삭제 실패!");
				}
			}
		}
		// 데이터 베이스에서 삭제
		return mapper.delete(bno, uuid);
	}

//	@Override
//	public int delete(FileuploadVO vo) {
//		// TODO Auto-generated method stub
//		return mapper.delete(vo);
//	}
	
	/**
	 * 첨부파일 저장 및 데이터 베이스에 등록
	 * @param files
	 * @param bno
	 * @return
	 * @throws Exception 
	 */
	public int fileupload(List<MultipartFile> files, int bno) throws Exception  {
		
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
				File sFile = new File(FileuploadController.ATTACHS_DIR 
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
					String thmbnail = FileuploadController.ATTACHS_DIR 
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
				int res = insert(vo);
				
				if(res>0) {
					insertRes++;
				}
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(IllegalStateException)");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(IOException)");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록중 예외사항이 발생 하였습니다.(Exception)");
			}
			
		}
		return insertRes;
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
			File saveDir = new File(FileuploadController.ATTACHS_DIR + uploadPath);
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
	

}
