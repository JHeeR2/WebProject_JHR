<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유 게시판 글 수정</title>
    <link rel="stylesheet" href="../css/board-style.css">
    <link rel="stylesheet" href="../css/navigation-style.css">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
    <div class="space-background"></div>
    <div class="stars" aria-hidden="true"></div>
    <div class="container">
        <h2>자유 게시판 글 수정</h2>
        <form name="writeFrm" method="post" action="../freeboard/FreeboardEdit.do" onsubmit="return validateForm(this);">
       	<input type="hidden" name="idx" value="${ dto.idx }" />
       	<input type="hidden" name="id" value="${ dto.user_id }">
        <div class="button-group">
        	<button type="reset" class="button">모두 지우기</button>
        	<button type="button" class="button" onclick="location.href='FreeboardList.do'">목록 바로가기</button>
        </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title"  value="${ dto.title }" required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" required>${ dto.content }</textarea>
            </div>
                <button type="submit" class="button">작성 완료</button>
        </form>
    </div>

    <script src="../js/stars.js"></script>
    <script>
        function validateForm(form) {
            if (form.title.value.trim() === "") {
                alert("제목을 입력하세요.");
                form.title.focus();
                return false;
            }
            if (form.content.value.trim() === "") {
                alert("내용을 입력하세요.");
                form.content.focus();
                return false;
            }
            return true;
        }
    </script>
</body>
</html>