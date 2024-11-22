<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
    <link rel="stylesheet" href="../css/space-theme.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
    <script src="../js/stars.js"></script>
<script type="text/javascript">
    function validateForm(form) {
        if (form.pass.value == "") {
            alert("비밀번호를 입력하세요.");
            form.pass.focus();
            return false;
        }
    }
</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
	<div class="container">    
		<h2>회원 정보수정 전 비밀번호 확인</h2>
		<p align="center">비밀번호 확인을 진행해야 정보 수정이 가능합니다<p>
		<form name="writeFrm" method="post" action="../user/UserInfoPass.do" onsubmit="return validateForm(this);">
		<input type="hidden" name="user_id" value="${ session.user_id }" />
		 <div class="form-group">
                <label for="userpass">비밀번호</label>
                <input type="password" id="user_pw" name="user_pw" placeholder="비밀번호를 입력해주세요" required>
            </div>
		            <button type="submit" class="button">검증하기</button>
		            <div class="register-link">
                <a href="UserInfoView.do">돌아가기</a>
            </div>   
		</form>
	</div>
</body>
</html>