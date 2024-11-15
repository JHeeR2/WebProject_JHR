<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="space-theme.css">
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2 >로그인</h2>
        <form name="loginForm" method="post" action="#" onsubmit="return validateForm(this);">
            <div class="form-group">
                <label for="username">아이디</label>
                <input type="text" id="username" name="username" placeholder="아이디를 입력해주세요" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요" required>
            </div>
            <button type="submit" class="button">로그인</button>
        </form>
        <div class="register-link">
            <a href="register.html">아직 계정이 없으신가요?</a>
        </div>
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

        function createStars() {
            const starsContainer = document.querySelector('.stars');
            for (let i = 0; i < 100; i++) {
                const star = document.createElement('div');
                star.className = 'star';
                star.style.width = `${Math.random() * 3}px`;
                star.style.height = star.style.width;
                star.style.left = `${Math.random() * 100}%`;
                star.style.top = `${Math.random() * 100}%`;
                star.style.animationDelay = `${Math.random() * 3}s`;
                starsContainer.appendChild(star);
            }
        }
        createStars();
    </script>
    <style>
        .register-link a {
            color: var(--space-white);
        }

        .container {
            width: 350px;
            padding: 30px;
        }

        input {
            padding: 12px;
            font-size: 16px;
        }

        .button {
            padding: 12px 24px;
            font-size: 16px;
        }

        h2 {
            font-size: 28px;
            margin-bottom: 30px;
        }
    </style>
</body>
</html>