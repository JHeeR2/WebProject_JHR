<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자료실 게시판 - 글 보기</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>자료실 게시판 - 글 보기</h2>

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
                    <c:if test="${ not empty dto.ofile }">
                    <br />
        		<c:choose>
        			<c:when test = "${ mimeType eq 'img' }" >
        			<!-- 이미지를 출력한다.  -->
        				<div class="media-container">
        				<br><img src="../Uploads/${ dto.sfile }" style="max-width:600%;"/>
        				</div>
        			</c:when>
        			<c:when test = "${ mimeType eq 'audio' }" >
        				<div class="media-container">
        				<br><audio controls="controls">
        					<source src="../Uploads/${ dto.sfile }" type="audio/mp3"/>
        				</audio> 
        				</div>
        			</c:when>
        			<c:when test = "${ mimeType eq 'video' }" >
        				<div class="media-container">
        				<br><video controls>
        					<source src="../Uploads/${ dto.sfile }" type="video/mp4"/>
        				</video>
        				</div> 
        			</c:when>
        			<c:otherwise>
        				<a href="../multiboard/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
        				[다운로드]  
        				</a>
        			</c:otherwise>
        		</c:choose>
        	</c:if>	
                </td>
            </tr>
            
            <!--  첨부파일  -->
            <tr>
                <th>첨부파일</th>
                <td>
                	<!-- 첨부한 파일이 있다면 다운로드 링크를 출력한다.  -->
        			<c:if test="${ not empty dto.ofile }">
        			${ dto.ofile }
        			<!-- 링크는 반드시 공백없이 한줄로 작성해야 한다. -->
        			<a href="../multiboard/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">
        			[다운로드]  
        			</a>
        			</c:if>
                </td>
                <th>다운로드수</th>
                <td>${ dto.downcount }</td>
            </tr>
        </table>
        <div style="text-align: center; margin-top: 20px;">
        	<!-- 글 작성자와 로그인 한 사람이 같을 때만 -->
        	<c:if test="${ user_id eq dto.user_id }">
        	<button type="button" onclick="location.href='../multiboard/MultiEdit.do?idx=${ param.idx }';">
                수정하기
            </button>
            <button type="button" onclick="deleteConfirm(${ param.idx });">
				삭제하기
			</button>
            </c:if>
            <button type="button" onclick="location.href='../multiboard/MultiList.do';">
                목록 바로가기
            </button>

        </div>
    </div>
    <script src="stars.js"></script>
    <script>
	function deleteConfirm(idx){
		let c = confirm("게시물을 삭제할까요?")
		if(c==true){
			location.href="../multiboard/delete.do?idx="+idx;
		}
	}
	</script>
</body>
</html>