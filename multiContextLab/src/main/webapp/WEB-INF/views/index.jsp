<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
	<body>
		<security:authorize access="isFullyAuthenticated()">
			<security:authentication property="principal" var="authMember"/>
			<h4>${authMember }</h4>
			<a href="<c:url value='/login/logout.do'/>">로그아웃</a>
		</security:authorize>
		<security:authorize access="!isFullyAuthenticated()">
			<h4>
				<a href="${pageContext.request.contextPath }/login/adminLoginForm.jsp">관리자용 로그인페이지</a><br />
				<a href="${pageContext.request.contextPath }/login/userLoginForm.jsp">일반유저용 로그인페이지</a>
			</h4>
		</security:authorize>
		<h4>
			<a href="<c:url value='/admin'/>">관리자 페이지</a><br />
			<a href="<c:url value='/user'/>">일반유저 페이지</a>
		</h4>
		<img src="<c:url value='/resources/images/cat1.jpg'/>" />
	</body>
</html>









