<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <script src="../js/stars.js"></script>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>로그인</h2>
        <span style="color:red;">
        	<%= request.getAttribute("LoginErrMsg")==null? "" : request.getAttribute("LoginErrMsg") %>
       	</span>
        <form name="loginForm" method="post" action="login.do" onsubmit="return validateForm(this);">
            <div class="form-group">
                <label for="userid">아이디</label>
                <input type="text" id="user_id" name="user_id" placeholder="아이디를 입력해주세요" required>
            </div>
            <div class="form-group">
                <label for="userpass">비밀번호</label>
                <input type="password" id="user_pw" name="user_pw" placeholder="비밀번호를 입력해주세요" required>
            </div>
            <button type="submit" class="button">로그인</button>
            <div class="register-link">
                <a href="RegisterForm.jsp">아직 계정이 없으신가요?</a>
            </div>
        </form>
    </div>
    

    <script>
        function validateForm(form) {
            if (form.user_id.value.trim() === "") {
                alert("아이디를 입력하세요.");
                form.user_id.focus();
                return false;
            }
            if (form.user_pw.value.trim() === "") {
                alert("비밀번호를 입력하세요.");
                form.user_pw.focus(); 
                return false;
            }
            return true;
        }

    </script>

</body>
</html>