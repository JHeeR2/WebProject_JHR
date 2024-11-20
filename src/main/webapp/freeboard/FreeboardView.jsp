<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유 게시판</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <table>
            <tr>
                <th>번호</th>
                <td>1</td>
                <th>작성자</th>
                <td>우주인A</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>2023-06-10</td>
                <th>조회수</th>
                <td>75</td>
            </tr>
            <tr>
                <th>제목</th>
                <td colspan="3">우주 여행 경험 공유</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3" class="content">
                    우주 여행은 정말 놀라운 경험이었습니다. 무중력 상태에서 떠다니는 느낌, 지구를 내려다보는 광경, 그리고 끝없는 우주의 신비로움... 이 모든 것이 저를 깊이 감동시켰습니다. 여러분도 기회가 된다면 꼭 경험해보시기 바랍니다!
                    
                </td>
            </tr>
        </table>
        <div style="text-align: center; margin-top: 20px;">
            <button type="button" class="button" onclick="location.href='edit.html';">수정하기</button>
            <button type="button" class="button" onclick="location.href='delete.html';">삭제하기</button>
            <button type="button" class="button" onclick="location.href='index.html';">목록 바로가기</button>
        </div>
    </div>
    <script src="../js/stars.js"></script>
</body>
</html>