<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 실패</title>
    <link rel="stylesheet" href="space-theme.css">
    <style>
        .container {
            text-align: center;
            padding: 40px;
        }
        h2 {
            margin-bottom: 30px;
            color: #ff4136; /* Red color for error message */
        }
    </style>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>회원 가입에 실패했습니다</h2>
        <button class="button" onclick="location.href='register.jsp'">회원가입 다시 시도</button>
    </div>
    <script src="stars.js"></script>
</body>
</html>