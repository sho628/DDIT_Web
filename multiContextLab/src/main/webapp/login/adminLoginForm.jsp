<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color: red;
	}
</style>
</head>
<body>
<c:set var="authFailure" value="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION }" />
<c:if test="${not empty authFailure }">
	<div class="error">
		${authFailure.message }
	</div>
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>
<h4>관리자용</h4>
<form name="loginForm" action="${pageContext.request.contextPath }/admin/loginCheck.do" method="post">
	<ul>
		<li>
			아이디 : <input required data-msg-required="필수 데이터" type="text" name="mem_id" value="${failId }"/>
		</li>
		<li>
			비밀번호 : <input required type="text" name="mem_pass" />
			<input type="submit" value="로그인" />
		</li>
	</ul>
</form>
</body>
</html>





