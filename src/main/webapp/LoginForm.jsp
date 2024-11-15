<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="space-theme.css">
    <script src="stars.js"></script>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>로그인</h2>
        <form name="loginForm" method="post" action="login.do" onsubmit="return validateForm(this);">
            <div class="form-group">
                <label for="username">아이디</label>
                <input type="text" id="username" name="username" placeholder="아이디를 입력해주세요" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요" required>
            </div>
            <button type="submit" class="button">로그인</button>
            <div class="register-link">
                <a href="Register.jsp">아직 계정이 없으신가요?</a>
            </div>
        </form>
    </div>
    

    <script>
        function validateForm(form) {
            if (form.username.value.trim() === "") {
                alert("아이디를 입력하세요.");
                form.username.focus();
                return false;
            }
            if (form.password.value.trim() === "") {
                alert("비밀번호를 입력하세요.");
                form.password.focus();
                return false;
            }
            return true;
        }

    </script>
</body>
</html>