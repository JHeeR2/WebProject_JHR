<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <style>
        .container {
            width: 500px;
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
        }
        td {
            padding: 5px;
        }
        td:first-child {
            width: 30%;
            color: var(--space-accent);
        }
        input, select {
            width: 100%;
            box-sizing: border-box;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: rgba(255, 255, 255, 0.1);
            color: var(--space-white);
            font-family: 'Orbitron', sans-serif;
        }
        select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            background-image: url('data:image/svg+xml;utf8,<svg fill="%23ffffff" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>');
            background-repeat: no-repeat;
            background-position-x: calc(100% - 10px);
            background-position-y: 50%;
        }
        select option {
            background-color: var(--space-blue);
            color: var(--space-white);
        }
        .login-link {
            text-align: center;
        }
        .login-link a {
            color: var(--space-white);
        }
        .required-asterisk {
            color: #FFD700;
            margin-left: 2px;
        }
        .input-group {
            display: flex;
            gap: 10px;
        }
        .input-group input {
            flex-grow: 1;
        }
        .check-button {
            padding: 10px;
            background-color: var(--space-accent);
            color: var(--space-black);
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-family: 'Orbitron', sans-serif;
            font-size: 0.8em;
            white-space: nowrap;
        }
    </style>
</head>
<body>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>회원가입</h2>
        <span style="color:red;">
        <%= request.getAttribute("RegisterErrMsg")==null? "" : request.getAttribute("RegisterErrMsg") %>
       	</span>
        <p style="font-size: 0.8em; color: var(--space-accent);">
            <span class="required-asterisk">*</span>는 필수 입력 항목입니다
        </p>
        <form name="registerForm" method="post" action="register.do" onsubmit="return validateForm(this);">
            <table>
                <tr>
                    <td>아이디 <span class="required-asterisk">*</span></td>
                    <td>
                        <div class="input-group">
                            <input type="text" id="id" name="user_id" placeholder="아이디" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>비밀번호 <span class="required-asterisk">*</span></td>
                    <td><input type="password" id="password" name="user_pw" placeholder="비밀번호" required></td>
                </tr>
                <tr>
                    <td>비밀번호 확인 <span class="required-asterisk">*</span></td>
                    <td><input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" required></td>
                </tr>
                <tr>
                    <td>이름 <span class="required-asterisk">*</span></td>
                    <td><input type="text" id="name" name="user_name" placeholder="이름" required></td>
                </tr>
                <tr>
                    <td>이메일 <span class="required-asterisk">*</span></td>
                    <td>
                        <div class="input-group">
                            <input type="email" id="email" name="user_email" placeholder="이메일" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>닉네임 <span class="required-asterisk">*</span></td>
                    <td>
                        <div class="input-group">
                            <input type="text" id="nickname" name="user_nick" placeholder="닉네임" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>생일</td>
                    <td><input type="date" id="birthday" name="user_birthday"></td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>
                        <select id="gender" name="user_gender">
                            <option value="">성별 선택</option>
                            <option value="남">남</option>
                            <option value="여">여</option>
                            <option value="기타">기타</option>
                            <option value="비공개">비공개</option>
                        </select>
                    </td>
                </tr>
            </table>
            <button type="submit" class="button">가입하기</button>
            <div class="login-link">
                <a href="LoginForm.jsp">이미 계정이 있으신가요?</a>
            </div>
        </form>
    </div>

    <script src="../js/stars.js"></script>
    <script>
        function validateForm(form) {
            if (form.password.value !== form.confirmPassword.value) {
                alert("비밀번호가 일치하지 않습니다.");
                form.confirmPassword.focus();
                return false;
            } 
            return true;
        }


    </script>
</body>
</html>