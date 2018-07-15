<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
			<c:when test="${bookList == null}">
				<div class="alert alert-danger" id="errorbox" style="display: block;">
					<span id="errormsg">No Books Found</span>	
				</div>
			</c:when>
		<c:otherwise>
			<form:form method="POST" id="sflcontents" name="createLodgingRequest">
				<!-- <input type="hidden" id="maxNoOfBooksPerRequest" name="maxNoOfBooksPerRequest" value="1000" > -->
				<c:if test="${maxNoOfBooksPerRequest!=null}">
					<input type="hidden" id="maxNoOfBooksPerRequest" name="maxNoOfBooksPerRequest" value="${maxNoOfBooksPerRequest}">
				</c:if>
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
										<th>Accession Number</th>
										<th>Book Name</th>
										<th>Author(s)</th>
										<th>Class No</th>
										<th>Publisher</th>
										<th>Volume</th>
                                  	</tr>
                              	</thead>
                              	<tbody>
                              		<c:forEach var="lodgingRequest" items="${bookList}">
										<tr class="gradeX">
											<td>
												<input class = "checkClass" type="checkbox" name="bookRequestBookCode" value="${lodgingRequest.accessionNumber}" onchange="go(this);" />
											</td>
											<td>
												${lodgingRequest.accessionNumber}
											</td>
											<td>
												${lodgingRequest.bookName}
											</td>
											<td>
												<c:forEach var="bookAuthor" items="${lodgingRequest.bookAuthorList}">
													${bookAuthor.authorFullName}
												</c:forEach>
											</td>
											<td>
												${lodgingRequest.classNo}
											</td>
											<td>
												${lodgingRequest.bookPublisher.bookPublisherName}
											</td>
											<td>
												${lodgingRequest.volume}
											</td>
										</tr>
									</c:forEach>
                              	</tbody>
                          	</table>
                   		</div>
               		</section>
				</div>
				<div class="alert alert-danger" id="errorMsgDiv" style="display:none">
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
							<div class="form-group">
								<label class="control-label">Book Request ID</label>
								<input type="text" class= "form-control" align="left" name="bookRequestCode" readonly="readonly" value="${lastBookRequestId}" />
							</div>
							<div class="form-group">
								<!-- <label class="control-label">User Name (ID)</label> -->
								<input type="hidden" class="textfield" name="userId" id ="userId" value="${sessionScope.sessionObject.userId}"/>
							</div>
						</div>
						<footer style="display: none" id="footerDiv" class="panel-footer">
							<button class="btn btn-primary" type="submit" id="submitForm">Submit</button>
						</footer>
					</section>
				</div>
			</form:form>
		</c:otherwise>
		</c:choose>
	</div>
</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/library/createLodgingRequest.js"></script>
</body>
</html>