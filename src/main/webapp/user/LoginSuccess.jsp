<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 성공</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <style>
        .container {
            text-align: center;
            padding: 40px;
        }
        h2 {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2><%= session.getAttribute("UserNick")%>님, 환영합니다</h2>
        <button class="button" onclick="window.location.href='../index.jsp'">게시판 바로가기</button>
        <br />
        <a href ="Logout.jsp">로그아웃</a>
    </div>
    <script src="../js/stars.js"></script>
</body>
</html>