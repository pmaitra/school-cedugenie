<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Retirement Book List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Books For Retire</h2>
</header>
<div class="content-padding">
	<c:choose>
		<c:when test="${bookListFromDB==null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsg">Books not Found</span>	
				</div>
			</div>
		</c:when>
	<c:otherwise>
	<form:form id="sflcontents" name="retirementBookIdList">
		<section role="main" class="content-body">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title">Retirement Book List</h2>
				</header>
				<div class="panel-body">
					<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
						<thead>
							<tr>
								<th>Select</th>
								<th>Book Code</th>
								<th>Book Name</th>
								<th>Total Stock</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="book" items="${bookListFromDB}">
							<tr class="gradeC">
								<td><input type="radio" name="bookCode" value="${book.bookCode}"/></td>
								<td><c:out value="${book.bookCode}"/></td>
								<td><c:out value="${book.bookName}"/></td>
								<td><c:out value="${book.totalNumberOfBookCopies}"/></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<footer style="display: block;" class="panel-footer">
                        <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary" name="next" id="nextbutton" value="next" onclick=" return onCheckBoxSubmit();">Next </button>
                        <button type="button" id="Back" class="mb-xs mt-xs mr-xs btn btn-danger" value="Retired Books" onclick=" window.location='retiredBookList.html' ">Retired Books</button>
                    </footer>
				</section>			
			</section>
		</form:form>
	</c:otherwise>
</c:choose>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/retirementBookCodeList.js"></script>
</body>
</html>