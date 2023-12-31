package com.ikik.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring Interceptor
 * 		HTTP 요청 처리 과정에서 요청을 가로채고 처리 전후에 추가 작업을 수행
 * 		인터셉터는 컨트롤러(Controller)에 진입하기 전, 컨트롤러 실행 후
 * 		, 뷰(View) 렌더링 전 등 다양한 시점에서 동작
 * 		사용하여 요청의 처리 흐름을 제어하거나 조작 할 수 있습니다.
 * 
 * 		인증 및 권한 체크로직을 작성해봅시다.
 * @author user
 *
 */

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	/**
	 * 로그인이 되어 있지 않은 경우 로그인 페이지로 이동
	 * preHandle : 컨트롤러 실행전 실행
	 * return : boolean 값
	 * 			 true : 요청컨트롤러 실행
	 * 			 false : 요청컨트롤러 실행 하지 않음
	 */
	// 구현체 이다보니 HandlerInterceptor 가 가지고있는 객체를 오버라이드해서 사용
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId") !=null
				&& !session.getAttribute("userId").equals("")) {
			return true;
		} else {
			// 인터셉터에서는 한글처리를 자동으로 해주지 않기때문에 Encoding 해줘야 합니다.
			String msg = URLEncoder.encode("로그인 후 사용가능한 메뉴 입니다.", "UTF-8");
			response.sendRedirect("/login?msg="+msg);
			return false;
		}
	}
	
	/* 오버라이드해서 재정의
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	*/

}
