<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="nav-container">
	<ul class="nav-list">
		<% if (session.getAttribute("user_id") == null) {%>
			<li class="nav-item"><a href="../user/LoginForm.jsp" class="nav-link">로그인</a></li>
			<li class="nav-item"><a href="../user/RegisterForm.jsp" class="nav-link">회원가입</a></li>
		<% } else { %>
			<li class="nav-item"><a href="../user/Logout.jsp" class="nav-link">로그아웃</a></li>
            <li class="nav-item"><a href="../user/UserInfoView.do" class="nav-link">마이페이지</a></li>
        <% } %>
            <li class="nav-item"><a href="../freeboard/FreeboardList.jsp" class="nav-link">자유게시판</a></li>
            <li class="nav-item"><a href="#" class="nav-link">Q&A게시판</a></li>
            <li class="nav-item"><a href="../multiboard/MultiList.jsp" class="nav-link">자료실게시판</a></li>
	</ul>
</nav>
