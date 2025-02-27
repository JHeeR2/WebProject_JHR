<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 수정</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
    <script src="../js/stars.js"></script>
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
		.input-wrapper {
		    display: flex;
		    flex-direction: column;
		    gap: 5px;
		}
		
		.message-container {
		    font-size: 0.9em;
		    min-height: 20px; /* 메시지가 없을 때도 공간 유지 */
		}
    </style>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>회원정보 수정</h2>
        <span style="color:red;">
        <%= request.getAttribute("RegisterErrMsg")==null? "" : request.getAttribute("RegisterErrMsg") %>
       	</span>
        <p style="font-size: 0.8em; color: var(--space-accent);">
            <span class="required-asterisk">아이디와 이름은 수정할 수 없습니다. 관리자에게 문의하세요</span>
        </p>
        <form name="InfoEditForm" method="post" action="userInfoEdit.do" onsubmit="return validateForm(this);">
            <table>
                <tr>
                    <td>아이디</td>
                    <td>
                    	<div class="input-wrapper">
            				<div class="input-group">
                				<input type="text" id="user_id" name="user_id" value="${ user.id }" readonly>
            				</div>
        				</div>
                    </td>
                </tr>
                <tr>
                    <td>새 비밀번호</td>
                    <td><input type="password" id="user_pw" name="user_pw" placeholder=" 새 비밀번호" required></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td>
						<div class="input-wrapper">
            				<div class="input-group">
                				<input type="email" id="user_email" name="user_email" value="${user.email}" required>
            				</div>
        				</div>
                    </td>
                </tr>
                <tr>
                    <td>닉네임</td>
                    <td>
						<div class="input-wrapper">
            				<div class="input-group">
                				<input type="text" id="user_nick" name="user_nick" value="${user.nickname}" required>
            				</div>
        				</div>
                    </td>
                </tr>
                <tr>
                    <td>생일</td>
                    <td><input type="date" id="birthday" name="user_birthday" value="${user.birthday}"></td>
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
            <button type="submit" class="button">회원정보 수정하기</button>
        </form>
    </div>

</body>
</html>