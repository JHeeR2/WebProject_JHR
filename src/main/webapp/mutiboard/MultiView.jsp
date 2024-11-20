<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 첨부형 게시판 - 우주 테마</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>자료실 게시판</h2>

        <table>
            <tr>
                <th>번호</th>
                <td></td>
                <th>작성자</th>
                <td></td>
            </tr>
            <tr>
                <th>작성일</th>
                <td></td>
                <th>조회수</th>
                <td></td>
            </tr>
            <tr>
                <th>제목</th>
                <td colspan="3"></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3" class="content">
                    
                    <div class="media-container">
                      
                    </div>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td>
                    <a href="#" class="button">다운로드</a>
                </td>
                <th>다운로드수</th>
                <td></td>
            </tr>
        </table>
        <div style="text-align: center; margin-top: 20px;">
            <button type="button" class="button" onclick="location.href='edit.html';">수정하기</button>
            <button type="button" class="button" onclick="location.href='delete.html';">삭제하기</button>
            <button type="button" class="button" onclick="location.href='index.html';">목록 바로가기</button>
        </div>
    </div>
    <script src="stars.js"></script>
</body>
</html>