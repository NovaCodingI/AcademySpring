<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@include file="./header.jsp" %>
	<!-- 게시판 -->
	<div id="page-wrapper">
	    <div class="row">
	        <div class="col-lg-12">
	            <h1 class="page-header"></h1>
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    <!-- /.row -->
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    	도서목록
	                </div>
	                <!-- 검색폼 -->
                	<form name="searchForm" action="/book/list">
                	<input type="hidden" name="pageNo" value="1">	
	                <%-- 버전이 안맞아서 깨져서 보여요 
	                <%@include file="../common/searchForm.jsp" %>
	                --%>
					<div class="form-inline text-center">
	                <p></p>
						<div class="form-group">
							<select class="form-control" name='searchField'>
							    <option value='title' ${pageDto.cri.searchField == 'title' ? 'selected' :'' }>도서명</option>
							    <option value='author' ${pageDto.cri.searchField == 'author' ? 'selected' :'' }>작가</option>
							</select>
						</div>
					<div class="form-group">
					    <input class="form-control" name='searchWord' value="${pageDto.cri.searchWord }"> 
					</div>
						<button type="submit" class="btn btn-default">검색</button>
					</div>
	                </form>
	                <!-- 검색폼끝 -->
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
	                        <thead>
	                            <tr>
	                                <th></th>
	                                <th>번호</th>
	                                <th>도서명</th>
	                                <th>작가</th>
	                                <th>대여여부</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <c:forEach items="${list }" var="vo">
	                            <tr class="odd gradeX">
	                                <td><input type="checkbox"></td>
	                                <td>${vo.no }</td>
	                                <td>${vo.title }</td>
	                                <td>${vo.author }</td>
	                                <td class="center">${vo.rentynStr }</td>
	                            </tr>
	                        </c:forEach>
	                        </tbody>
	                    </table>
	                    <div class="text-center">
	           <%@include file="../common/pageNavi.jsp" %>
	           			</div>
	                    <!-- /.table-responsive -->
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    
	</div>
	<!-- 게시판 끝-->
	
<%@include file="./footer.jsp" %>