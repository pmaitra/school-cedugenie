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
<title>Book Allocated Details</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
				<div class= "col-md-12">
			
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Assigned Books</h2>
							</header>
							<div class="panel-body">
							<c:choose>
								<c:when test="${bookAllocatedList eq null}">
									<div class="alert alert-danger">
										<strong>No Books assigned template found!</strong>
									</div>
								</c:when>
							<c:otherwise>
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											
											<th>User Id</th>
											<th>Book Allocation Id</th>
											
											<th>Expected Book Return Date</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bookDetails" items="${bookAllocatedList}" varStatus="i">
											<tr class="gradeC">
											
												<!-- <td>${bookDetails. bookIssueComment}</td> -->
												<td><input type="hidden" id="userId" name="userId${i.index}" readonly="readonly" value="${bookDetails.bookAllocationObjectId}"/>
												${bookDetails.bookAllocationObjectId}</td>
												<%-- <td><a href="viewStudentFeesTemplateAmountDetails.html?courseName=${tempAmount.course.courseName}&templateName=${tempAmount.studentFeesTemplateName}&templateCode=${tempAmount.studentFeesTemplateCode}"><input type="button" class="btn btn-primary" value="Edit"></a></td>
												<td><a class="on-default remove-row" href="deleteStudentFeesTemplateAmountDetails.html?courseName=${tempAmount.course.courseName}" ><i class="fa fa-trash-o"></i></a></td> --%>
												<td>${bookDetails.bookAllocationCode }</td>
												<!--<td>${bookDetails.bookIssueDate} </td>-->
												<td>${bookDetails.bookReturnDate} </td>
												<td><a href="libraryFineReceiveDetails.html?userId=${bookDetails.bookAllocationObjectId}"><button type="submit" id="detailsButton" class="btn btn-primary" >Get Fine Details</button></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
							</c:choose>
							</div>
						</section>
					</section>
				 
				</div>
				
						
                           
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
var bookCode="";
var bookId="";
function submitPopupValues(bookid,bookcode,penalty) {
	document.getElementById("strBookAllocation").value = bookcode;		
	document.getElementById("strBookId").value = bookid;
	document.getElementById("strPenalty").value = penalty;
	return true;
}
</script>
</body>
</html>