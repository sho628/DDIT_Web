<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<ul>
	<li>
		<a href="${cPath }/">INDEX</a>
	</li>
	<security:authorize url="/member/memberList.do">
		<li>
			<a href="${cPath }/member/memberList.do">회원관리</a>
		</li>
	</security:authorize>
	<security:authorize url="/prod/prodList.do">
		<li>
			<a href="${cPath }/prod/prodList.do">상품관리</a>
		</li>
	</security:authorize>
	<security:authorize url="/buyer/buyerList.do">
		<li>
			<a href="${cPath }/buyer/buyerList.do">거래처관리</a>
		</li>
	</security:authorize>
	<security:authorize url="/board/boardList.do">
		<li>
			<a href="${cPath }/board/boardList.do">게시판</a>
		</li>
	</security:authorize>
	<security:authorize url="/emp/employeeList.do">
		<li>
			<a href="${cPath }/emp/employeeList.do">Employee(DataTable)</a>
		</li>
	</security:authorize>
	<security:authorize url="/emp/employeeList_FT.do">
		<li>
			<a href="${cPath }/emp/employeeList_FT.do">Employee(FancyTree)</a>
		</li>
	</security:authorize>
	<security:authorize url="/formtag/test">
		<li>
			<a href="${cPath }/formtag/test">Spring form taglib</a>
		</li>
	</security:authorize>
</ul>
