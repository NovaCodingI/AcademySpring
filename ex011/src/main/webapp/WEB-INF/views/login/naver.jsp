<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  아이디 : ${id } <br>
  이름 : ${name } <br>
  나이 : ${age } <br>
  성별 : ${gender } <br>
  이메일 : ${email } <br>
  닉네임 : ${nickname } <br>
  휴대전화번호 : ${mobile } <br>
  생일 : ${birthday } <br>
  생년 : ${birthyear } <br>
 
  <%
    String clientId = "mHMIV3Pl48fvMu199eMZ";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:8080/login/naver_callback", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    
    
    // 요청 URL -> 네이버로그인 및 사용자정보 제공동의 -> 콜백으로 코드를 제공
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
  <a href="<%=apiURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
  </body>
</html>