<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Q&A 게시판 - 글 보기</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>Q&A 게시판 - 글 보기</h2>

        <table> 
            <tr>
                <th>번호</th>
                <td>${ dto.idx }</td>
                <th>작성자</th>
                <td>${ dto.nickname }</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${ dto.postdate }</td>
                <th>조회수</th>
                <td>${ dto.visitcount }</td>
            </tr>
            <tr>
                <th>제목</th>
                <td colspan="3">${ dto.title }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3" class="content">
                	${ dto.content }
                </td>
            </tr>
        </table>
        <div style="text-align: center; margin-top: 20px;">
        	<!-- 글 작성자와 로그인 한 사람이 같을 때만 -->
        	<c:if test="${ user_id eq dto.user_id }">
        	<button type="button" onclick="location.href='../qna/QEdit.do?idx=${ param.idx }';">
                수정하기
            </button>
            <button type="button" onclick="deleteConfirm(${ param.idx });">
				삭제하기
			</button>
            </c:if>
            <button type="button" onclick="location.href='../qna/QList.do';">
                목록 바로가기
            </button>

        </div>
		    </div>
    <script src="stars.js"></script>
    <script>
	function deleteConfirm(idx){
		let c = confirm("게시물을 삭제할까요?")
		if(c==true){
			location.href="../qna/delete.do?idx="+idx;
		}
	}
	</script>
</body>
</html>