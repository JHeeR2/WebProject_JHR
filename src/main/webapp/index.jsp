<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>우주 게시판</title>
    <link rel="stylesheet" href="css/board-style.css">
    <link rel="stylesheet" href="css/navigation-style.css">
</head>
<body>
	<jsp:include page="/Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h1>우주 게시판</h1>
        <div class="search-container">
            <input type="text" placeholder="검색..." aria-label="게시물 검색">
            <button>검색</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>3</td>
                    <td>화성 탐사 일지</td>
                    <td>우주인A</td>
                    <td>2023-06-03</td>
                    <td>75</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>목성의 위대한 붉은 점</td>
                    <td>우주인B</td>
                    <td>2023-06-02</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>달 기지 건설 계획</td>
                    <td>우주인C</td>
                    <td>2023-06-01</td>
                    <td>100</td>
                </tr>
            </tbody>
        </table>
        <a href="#" class="new-post">새 게시물 작성</a>
    </div>
    <script src="stars.js"></script>
</body>
</html>