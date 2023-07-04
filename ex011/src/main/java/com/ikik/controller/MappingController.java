package com.ikik.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikik.vo.Member;
import com.ikik.vo.MemberList;

/**
 *  스프링 MCV 에서 제공하고 있는 어노테이션을 이용하여 Controller를 작성 해봅시다.
 * 
 *  톰켓 서버를 실행하면 web.xml파일을 설정을 읽어 서버를 시작합니다.
 *  web.xml 파일에 기술되어 있는
 *  servlet-context.xml 파일의
 *  component-scan에 등록된 패키지 탐색하며
 *  클래스를 조사하고 객체설정에 사용되는 어노테이션들을 가진 클래스를
 *  → 객체로 생성하고 관리합니다
 *  
 *  MVC에서 사용되는 어노테이션을 학습해봅시다.
 *  
 *  🥧🥧 Controller
 *  해당 클래스의 인스턴스를 스프링의 빈으로 등록하고 컨트롤러로 사용
 *  
 *  스프링 MVC Controller의 장점
 *  
 *  1. 파라메터를 자동 수집
 *  2. URL매핑을 메서드단위로 처리 (post, get 매핑 다 줄수있음)
 *  3. 화면에 전달할 데이터는 Model에 담아 주기만 하면 됨 (Model은 데이터를 화면까지 전달해주기 위한 객체)
 *  4. 간단한 페이지 전환(forward, redirect)
 *  5. 상속/인터페이스 방식 대신에 어노테이션만으로도 필요한 설정 가능
 *  
 * @author user
 *
 */
@RequestMapping("/mapping/*")
// mapping의 하위에 존재한다고 생각하면됩니다. 클래스에 지정된 것이 먼저나오고 그다음 지정입니다.
@Controller
public class MappingController {
	
	/**
	 * RequestMapping
	 * 	클래스의 상단에 적용시 현재 클래스의 모든 메서드들의 기본 URL경로를 지정
	 * 	메서드의 상단에 적용시 메서드의 URL경로를 지정
	 * 
	 * 	get방식과 post방식을 모두 처리하고 싶은 경우, 배열로 받을 수 있습니다.
	 * 
	 * 	/mapping/requestMapping URI을 GET메서드로 호출하면 해당 메서드가 실행 됩니다.
	 * @return
	 */
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String requestMapping() {
		
		return "mapping";
	}
	
	/**
	 * 
	 *	/mapping/requestMapping URI을 GET메서드로 호출하면 해당 메서드가 실행 됩니다.
	 * @return
	 */
	// value가 경로주는겁니다
	@RequestMapping(value="/requestMapping"
					, method = {RequestMethod.GET, RequestMethod.POST})
	public String requestMapping2() {
		
		System.out.println("/requestMapping 호출");
		
		return "mapping";
	}
	
	
	/**
	 * 스프링 4.3이후에는 GetMapping, PostMapping등으로 간단히 표현 가능
	 * 어노테이션 사용이 불가능 할 경우 스프링의 버전을 확인 합니다.
	 * 
	 * GetMapping
	 * 	Get방식의 요청을 처리 합니다.
	 * 
	 * 파라메터의 자동수집
	 * 	RequestParam 어노테이션을 이용하면 기본타입의 데이터를 지정한 타입으로 받을수 있습니다.
	 * 	단, 타입이 불일치 하는 경우 400오류가 발생 할 수 있습니다.
	 * 
	 * 	VO객체를 지정 할 경우, 객체를 생성 후 파라메터를 name속성과 일치하는 필드에 세팅 해줍니다. (넘어온 파라메터를 field에 세팅해주는것)
	 * 	단, 타입이 불일치 하는 경우 400오류가 발생 할 수 있습니다.
	 * 
	 * ?name=익익&age=77 → 파라메터가 매핑이 되어 출력되는것을 볼수있습니다.
	 * name 속성의 값을 매핑. age 속성의 값을 매핑. 지정된 변수타입외에 작성될경우 오류가 발생.
	 * @return
	 */
	
	// 매핑이 잘 됐으면 return한 페이지가 잘 나올겁니다.
	@GetMapping("/getMapping")
	// 리퀘스트를 등록하지 않고 파라메터를 처리할수있습니다.
	public String getMapping(@RequestParam("name") String name
								, @RequestParam("age") int age
								, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		return "mapping";
	}
	
	/**
	 * 파라메터를 Vo, Dto에 수집한 경우, 별도의 저장 없이 화면까지 전달 됩니다.
	 * 
	 * 화면에 값을 전달 하고 싶은 경우
	 * Model객체를 매개변수로 받아 addAttribute() 메서드를 이용합니다.
	 * model.addAttribute("이름", 값);
	 * 
	 * @return
	 */
	
	// Model은 message를 출력할수도있고 그 외에 객체들도 출력 가능.
	// 서블릿도 받을수있음 타입 맞춰서 request객체, response객체..
	// 서블릿을 제어하는.. 굳이.. 대부분 스프링 MVC에서 구현 해놓은게 많음.
	@GetMapping("getMappingVO")
	public String getMappingVO(Member member
								, Model model) {
		
		model.addAttribute("message", "파라메터 자동수집!!!");
		
		return "mapping";
	}
	
	@GetMapping("getMappingArr")
	public String getMappingArr(@RequestParam("ids") String[] ids) {
		
		for(String id : ids) {
			System.out.println("ids : " + id);
		}
		return "mapping";
	}
	
	@GetMapping("getMappingList")
	public String getMappingArr(@RequestParam("ids") List<String> ids) {
		
		/**
		 * ForEach : 익명의 함수를 이용한 컬렉션의 반복처리
		 * collection.forEach(변수 -> 반복처리(변수))
		 */
		ids.forEach(id -> {
			System.out.println("ids" + id);
		});
		return "mapping";
	}
	
	@GetMapping("getMappingMemberList")
	public String getMappingMemberList(MemberList list) {
		System.out.println(list);
		return "mapping";
		
	}
	
}
