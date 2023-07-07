<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@include file="./header.jsp" %>
	<!-- 상세보기 -->
	<div id="page-wrapper">
	<p></p>
	<div class="row">
          <div class="col-lg-4">
              <div class="panel panel-default">
                  <div class="panel-heading">
                      ${book.title }
                  </div>
                  <div class="panel-body">
                      <p>book : ${book }</p>
                  </div>
                  <div class="panel-footer">
                      ${book.author }
                  </div>
              </div>
          </div>
    </div>
    </div>
	<!-- 상세보기-->
	
<%@include file="./footer.jsp" %>