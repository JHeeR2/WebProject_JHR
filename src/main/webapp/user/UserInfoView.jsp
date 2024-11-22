<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
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
        .update-link {
            text-align: center;
        }
        .update-link a {
            color: var(--space-white);
        }
    </style>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>마이페이지</h2>
        <p style="font-size: 0.8em; color: var(--space-accent);">
        </p>
            <table>
                <tr>
                    <td>아이디</td> <td>${ user_id }</td>
                </tr>
                <tr>
                    <td>이름</td> <td>${ user.name }</td>
                </tr>
                <tr>
                    <td>이메일</td> <td>${ user.email }</td>
                    
                </tr>
                <tr>
                    <td>닉네임</td> <td>${ user_nick }</td>
                </tr>
                <tr>
                    <td>생일</td>
                    <td>
                    <c:choose> 
                    	<c:when test="${ not empty user.birthday}">
                    		<fmt:parseDate value = "${ user.birthday }" var="birthDValue" pattern="yyyy-MM-dd HH:mm:ss"/>
                    		<fmt:formatDate value="${ birthDValue }" pattern="yyyy년 MM월 dd일"/>
                    	</c:when>
                    	<c:otherwise>생일등록 x</c:otherwise>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td>
                    <c:choose> 
                    	<c:when test="${ not empty user.gender}">
                    		${ user.gender }
                    	</c:when>
                    	<c:otherwise>성별등록 x</c:otherwise>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>가입일</td> <td><fmt:formatDate value="${ user.regidate  }" pattern="yyyy년 MM월 dd일"/></td>
                </tr>
            </table>
            <div class="update-link">
                <a href="UserInfoPass.do">내 정보 수정하기</a>
            </div>
    </div>
    
</body>
</html>