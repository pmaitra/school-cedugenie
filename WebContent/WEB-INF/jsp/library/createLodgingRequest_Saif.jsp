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
<title>Create Book Request</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
		<header class="page-header">
			<h2>Create Book Request</h2>
		</header>
		<div class="content-padding">
			<div class="row">
				<c:choose>
					<c:when test="${pagedListHolder==null}">
						<div class="btnsarea01" style="visibility: visible;">
							<div class="errorbox" id="errorbox"  style="visibility: visible;">
								<span id="errormsg">No Books Found</span>	
							</div>
						</div>
					</c:when>
				<c:otherwise>
					<form:form method="POST" id="sflcontents" name="createLodgingRequest">
						<!-- <input type="hidden" id="maxNoOfBooksPerRequest" name="maxNoOfBooksPerRequest" value="1000" > -->
						
							<input type="hidden" id="maxNoOfBooksPerRequest" name="maxNoOfBooksPerRequest" value="${maxNoOfBooksPerRequest}">
						
						<div class="col-md-12">
	                       <section class="panel">
                                <header class="panel-heading">
                                    <h2 class="panel-title">Books</h2>
                                </header>
	                                <div class="panel-body">
	                                    <table id ="clr" class="table table-bordered table-striped mb-none">
	                                        <thead>
	                                            <tr>
	                                                <th>Select</th>
													<th>Book Code</th>
													<th>Book Name</th>
													<th>Author</th>
													<th>Publisher</th>
													<th>Edition</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        <c:forEach var="lodgingRequest" items="${pagedListHolder.pageList}">
												<tr class="gradeX">
													<c:if test="${role eq 'SUPER ADMIN'}">
														<td>
															<input class = "checkClass" type="checkbox" name="bookRequestBookCode" id= "bookRequestBookCode" value="${lodgingRequest.bookCode}" onchange="go(this);" disabled/>
														</td>
													</c:if>
													<c:if test="${role ne 'SUPER ADMIN'}">
														<td>
															<input class = "checkClass" type="checkbox" name="bookRequestBookCode" value="${lodgingRequest.bookCode}" onchange="go(this);"/>
														</td> 
													</c:if>
													<td>
														<c:out value="${lodgingRequest.bookCode}"/>
													</td>
													<td>
														<c:out value="${lodgingRequest.bookName}"/>
													</td>
													<td>
														<c:forEach var="bookAuthor" items="${lodgingRequest.bookAuthorList}">
															<c:out value="${bookAuthor.authorFullName}"/> ; 
														</c:forEach>
													</td>
													<td>
														<c:out value="${lodgingRequest.bookPublisher.bookPublisherName}"/>
													</td>
													<td>
														<c:out value="${lodgingRequest.bookEdition}"/>
													</td>
												</tr>
												</c:forEach>
	                                        	</tbody>
	                                    	</table>
			                                </div>
	                            </section>
							</div>
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Request Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Selected Books</label>
											<ul id="names"></ul>
										</div>
										<div>
										<input type="hidden" class="textfield" name="role" id ="role" value="${sessionObject.getCurrentRoleOrAccess()}">
										</div>
										<c:if test="${role eq 'SUPER ADMIN' || role eq 'LIBRARY ADMINISTRATOR'}">
											<div class="form-group">
												<label class="control-label">Requested By</label>
												<%-- <input type="hidden" class="textfield" name="userId" id ="userId" value="${sessionScope.sessionObject.userId}"/>
			  									 --%><input type="text" class= "form-control" align="left" name="bookRequestedBy" id = "bookRequestedBy" readonly="readonly" value="${sessionScope.sessionObject.userId}" />
											</div>
											<div class="form-group">
												<label class="control-label">Requested For</label>
												<input type="hidden" class = "textfield" name= "newAppliedBy" id="newAppliedBy"/>
			  									<input type="text" class= "form-control" align="left" name="bookRequestedFor" id ="bookRequestedFor" />
											</div>
										</c:if>
										<div class="form-group">
											<label class="control-label">Book Request ID</label>
		  									<input type="text" class= "form-control" align="left" name="bookRequestCode" readonly="readonly" value="${lastBookRequestId}" />
										</div>
										 <div class="form-group">
											<!-- <label class="control-label">User Name (ID)</label> -->
											<input type="hidden" class="textfield" name="userId" id ="userId" value="${sessionScope.sessionObject.userId}"/>
										</div>
 									</div>
 									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitForm" value= "SUBMIT" >Submit</button>
									</footer>
								</section>
							</div>
						</form:form>
					</c:otherwise>
					</c:choose>
				</div>
			</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/createLodgingRequest.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
</body>
</html>