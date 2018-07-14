<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Retired Book Details</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/library/retirementBookIdList.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }/* .mb-md{
       	   display: none;
       } */
</style>
</head>
<body>


					<div class="row" >
						
						<form:form method="POST" id="sflcontents" name="sflcontents" action="retiredBookList.html" >
							<div class="col-md-12" >
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Book Details</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="rbidl" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Book Code</th>
															<th>Book Name</th>
															<th>Author</th>
															<th>Publisher</th>
															<th>Edition</th>
															<th>ISBN</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        <tr class="gradeX">
															<td><c:out value="${ViewRetiredBookDetails.bookCode}"/></td>
															<td><c:out value="${ViewRetiredBookDetails.bookName}"/></td>
															<td>
															<c:forEach var="bookAuthor" items="${ViewRetiredBookDetails.bookAuthorList}">
																<c:out value="${bookAuthor.authorFullName}"/> ; 
															</c:forEach>
															</td>
															<td><c:out value="${ViewRetiredBookDetails.bookPublisher.bookPublisherName}"/></td>
															<td><c:out value="${ViewRetiredBookDetails.bookEdition}"/></td>
															<td><c:out value="${ViewRetiredBookDetails.bookIsbn}"/></td>
													</tr>
		                                        </tbody>
		                                    </table>
		                                </div>
	                              
	                            </section>
							</div>
							
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Book's Existing Period</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table  class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Book Id</th>
															<th>Entry Date</th>
															<th>Retirement Date</th>
															<th>price</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="bookIdentifier" items="${ViewRetiredBookDetails.bookIdList}">
														<tr class="gradeX">
															<td><c:out value="${bookIdentifier.bookId}"/></td>
															<td><c:out value="${bookIdentifier.newBookEntryDate}"/></td>
															<td><c:out value="${bookIdentifier.bookRetirementDate}"/></td>
															<td><c:out value="${bookIdentifier.price ne null ? bookIdentifier.price: 'N/A' }"/></td>
														</tr>
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                                
	                            </section>
							</div>
				</form:form>
			</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>