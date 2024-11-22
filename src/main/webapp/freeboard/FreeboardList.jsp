<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판 목록</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="/Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h1>자유 게시판- 목록보기</h1>
		<!-- 검색 폼 -->
		<form method="get" class="search-form">
		<table>
		<tr>
			<td align="center">
				<select name="searchField">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="nickname">작성자</option>
				</select>
				<input type="text" name="searchWord" />
				<input type="submit" value="검색하기" />
			</td>
		</tr>

		</table>
		</form>
		
		<!-- 목록 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th width="*">제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
<c:choose>
	<c:when test="${ empty boardLists }">
		<tbody>
			<tr>
				<td colspan="6" align="center">
					등록된 게시물이 없습니다.
				</td>
			</tr>
		</tbody>
	</c:when>
	<c:otherwise>
		<c:forEach items="${ boardLists }" var = "row" varStatus="loop">       
            <tbody>
                <tr>
                	<!-- 번호 -->
                    <td>${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index) }</td>
                    <!--  제목 -->
                    <td>
                    	<a href="../multiboard/MultiView.do?idx=${ row.idx }">${ row.title }</a>
                    </td>
                    <!-- 작성자 -->
                    <td>${ row.nickname }</td>
                    <!-- 조회수 -->
                    <td>${ row.visitcount }</td>
                    <!-- 작성일 -->
                    <td>
                    <fmt:parseDate value = "${ row.postdate }" var="postdateValue" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <fmt:formatDate value="${ postdateValue }" pattern="yy-MM-dd"/>
                    </td>
                </tr>
            </tbody>
		</c:forEach>
	</c:otherwise>
</c:choose>
			<!-- 링크 수정하기-->
        	<!-- 하단 메뉴 -->
			<tr align="center">
        		<td>
        		</td>
        		<td colspan="3">
        			<div class="pagination">
        				${ map.pagingImg }
        			</div>
        		</td>
        		<td colspan="1">
        		<a href="../multiboard/MultiList.do" class="new-post">목록보기</a>
        		<a href="../multiboard/MultiWrite.do" class="new-post">글쓰기</a>
        		</td>
        	</tr>
</table>
    </div> 
    <script src="../js/stars.js"></script>
</body>
</html>