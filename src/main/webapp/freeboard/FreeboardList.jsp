<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="/Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h1>자유게시판 목록보기</h1>
        <!--  검색 폼 -->
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
	    
	    <!--  목록 테이블  -->
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>
<c:choose>
	<c:when test="${ empty fboardList }">
		<tr>
			<td colspan="5" align="center">
				등록된 게시물이 없습니다.
			</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${ fboardList }" var="row" varStatus="loop">
            <tbody>
                <tr>
                    <td align="center">${ map.totalCount - loop.index }</td>
                    <td align="left">
                    	<!-- 게시물 열람할 수 있도록 수정해야함 -->
                    	<a href="#">
                    	${ row.title }</a>
                    </td>
                    <td>${ row.user_nick }</td>
                    <td>${ row.postdate }</td>
                    <td>${ row.visitcount }</td>
                </tr>
            </tbody>
		</c:forEach>
	</c:otherwise>
</c:choose>
        </table>
        <a href="FreeboardWrite.do" class="new-post">새 게시물 작성</a>
    </div>
    <script src="../js/stars.js"></script>
</body>
</html>
