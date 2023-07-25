package com.ikik.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 사용하고싶어서 Componet로 bean으로 등록해놓고 자동주입
@Component
public class ApiExamMemberProfile {
// 네이버 API 예제 - 회원프로필 조회


//    public static void main(String[] args) {
    public Map<String, Object> getMemberProfile(String access_token) {
//        String token = "AAAAOOjTRJVyatX0kpa3AOW7vmBncOc_hWc6Vgz9jvftnbL7Bdh0y41XOe-GzEJP4kkQr5mQOK4d17YMKMfgz3zRHg4"; // 네이버 로그인 접근 토큰;
        String token = access_token; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);

        // json문자열을 Map으로 변환
        // response값 에러
        // Exception in thread "main" java.lang.ClassCastException: class java.util.LinkedHashMap cannot be cast to class java
//        Map<String, String> map = new HashMap<String, String>();
        // 사용자에 대한 정보
        Map<String, Object> map = new HashMap<String, Object>();
        
        // jackson 라이브러리 사용
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
        	// responseBody 읽어서 map 반환
			map = objectMapper.readValue(responseBody, Map.class);
			System.out.println("responseBody" + responseBody);
			System.out.println("resultcode : " + map.get("resultcode"));
			System.out.println("message : " + map.get("message"));
			// Map 안에 또다른 Map이 들어가있어서 오류가 납니다.
			System.out.println("response : " + map.get("response"));
			
			Map<String, String> response = (Map<String, String>)map.get("response");
			
			response.get("name");
			response.get("id");
			response.get("age");
			response.get("gender");
			response.get("email");
			response.get("nickname");
			response.get("mobile");
			response.get("birthday");
			response.get("birthyear");
			
			return map;
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return map;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
