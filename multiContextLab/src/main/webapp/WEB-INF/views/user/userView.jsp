<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>일반유저용 UI 페이지</h4>
<a href="<c:url value='/login/logout.do'/>">로그아웃</a><br/>
<img src="<c:url value='/resources/images/cat3.png'/>" />
</body>
</html>