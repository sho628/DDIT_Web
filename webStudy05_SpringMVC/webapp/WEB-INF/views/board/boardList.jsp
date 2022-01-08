<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 
<table class="table table-bordered">
	<thead>
		<tr>
			<th><spring:message code="board.rnum" /></th>
			<th><spring:message code="board.boTitle" /></th>
			<th><spring:message code="board.boWriter" /></th>
			<th><spring:message code="board.boHit" /></th>
			<th><spring:message code="board.boRec" /></th>
			<th><spring:message code="board.boDate" /></th>
		</tr>
	</thead>
	<c:set var="boardList" value="${pagingVO.dataList }" />
	<tbody>
		<c:choose>
			<c:when test="${not empty boardList }">
				<c:forEach items="${boardList }" var="board">
					<tr>
						<td>${board.rnum }</td>
						<td>
							<c:url value="/board/boardView.do" var="viewURL">
								<c:param name="what" value="${board.boNo }" />
							</c:url>
							<a href="${viewURL }">${board.boTitle }</a>[${board.repCnt }]
							<c:forEach begin="1" end="${board.atchCnt }">
								<img src="${pageContext.request.contextPath }/resources/images/attatchment.png" style="width: 10px; height: 10px;"/>
							</c:forEach>
						</td>
						<td>${board.boWriter }</td>
						<td>${board.boHit }</td>
						<td>${board.boRec }</td>
						<td>${board.boDate }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">검색 조건에 맞는 게시글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
					${pagingVO.pagingHTML }
				</div>
				<div id="searchUI" class="form-inline">
					<select name="searchType" class="form-control mr-3">
						<option value>전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord"  class="form-control mr-3"/>
					<input type="button" value="검색" id="searchBtn"  class="btn btn-primary mr-3"/>
					<input type="button" value="새글쓰기" class="linkBtn btn btn-secondary" 
						data-gopage="${cPath }/board/boardInsert.do"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
	<input type="hidden" name="page" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/custom/paging.js"></script>
<script type="text/javascript">	
	$("[name='searchWord']").val("${searchVO.searchWord}");
	$("[name='searchType']").val("${searchVO.searchType}")
	let searchForm = $("#searchForm").paging();
</script>
