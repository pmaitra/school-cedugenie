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
<title>Create Book Requisition</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Create Book Requisition</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<form:form method="POST" id="" name=""  action="addRequisition.html" >
                 <div class="col-md-12">						  
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								Requisition ID :${strRequisitionUpComingId.bookRequisitionCode} 
								<input type="hidden" id="name" name="bookRequisitionCode" value="${strRequisitionUpComingId.bookRequisitionCode}"/>
								<!-- <a href="#" class="mb-xs mt-xs mr-xs modal-basic btn btn-success" type="button" id ="addRequisitionbButton" onclick = "addRowForRequisition('booktable');">Add</a> -->
								<button type="button" class="mb-xs mt-xs mr-xs modal-basic btn btn-success" id="addRequisitionbButton">Add</button>
							</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="booktable">
									<thead>
										<tr>
                                            <th>Book Name</th>
                                            <th>Author</th>
											<th>Edition</th>
											<th>Genre</th>
											<th>Department</th>
                                            <th>Publisher</th>
                                            <th>Quantity</th>
                                            <th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<tr>
                                            <td>
                                            	<input type="text" class="form-control bookNameClass" id="bookName0" name="bookName" value="${bookDetails.bookName}">
                                           	</td>
                                            <td>
                                                <table>
                                                	<c:choose>
															<c:when test="${bookDetails==null}">
																<tr>
		                                                        <td>
		                                                        	<input type="text" class="form-control" id="bookAuthor0" name="bookAuthor" onfocus="getAuthorName(this);" onblur="submitAction(this);" value="">
		                                                       		<a class="on-default " href="#" style="margin:10px;"><i class="fa fa-plus-square" onclick="addAuthorName(this);"></i></a>
		                                                       	</td>
		                                                       	</tr>
		                                                    </c:when>	
														<c:otherwise> 
															<c:forEach var="author" items="${bookDetails.bookAuthorList}" varStatus = "status">
																<tr>
																<td>
		                                                        	<input type="text" class="form-control" id="bookAuthor${status.index}" name="bookAuthor" onfocus="getAuthorName(this);" onblur="submitAction(this);" value="${author.authorFullName}">
		                                                       	</td>
		                                                       	</tr>
		                                                    </c:forEach>
		                                                    	<!-- <td>
		                                                        	<a class="on-default remove-row" href="#" style="margin:10px;"><i class="fa fa-plus-square" onclick="addAuthorName(this);"></i></a>
		                                                       	</td> -->
		                                                 </c:otherwise> 
		                                                </c:choose> 
                                                </table>
                                            </td>
											<td>
												<input type="text" class="form-control" id="bookEdition0" onfocus="getEdition(this);" name="bookEdition" value="${bookDetails.bookEdition}">
											</td>
											<td>
												<div class="form-group">
	                                       			<select class="form-control" name="genreName" id="genreName0" required>
	                                           			<option value="">Select...</option>
	                                          				<c:forEach var="genre" items="${genreList}">
																<option value="${genre.genreCode}">${genre.genreName}</option>
															</c:forEach>
													</select>
	                                   			</div>
                                   			 </td>
                                   			 <td>
												 <div class="form-group">
	                                          		<select class="form-control" name="departmentName" id="departmentName0">
	                                            		<option value="">Select...</option>
	                                           			 <c:forEach var="department" items="${departmentList}">
															<option value="${department.departmentCode}">${department.departmentName}</option>
														</c:forEach>
													</select>
	                                   			 </div>
                                   			 </td>
                                            <td>
                                            	<input type="text" class="form-control" id="bookPublisher0" onfocus="getPublisherName(this);" name="bookPublisher" value="${bookDetails.bookPublisher.bookPublisherName}">
                                           	</td>
											<td>
												<input type="text" class="form-control" id="numberOfBooksRequisitioned0" value="" name="numberOfBooksRequisitioned" id="numberOfBooksRequisitioned0" >
											</td>
                                            <td>
                                            	<c:choose>
													<c:when test="${bookDetails==null}">
														<input type="hidden" id="hiddenVal" name="hiddenval" value=" "/>
													</c:when>	
												<c:otherwise>
														<c:forEach var="author" items="${bookDetails.bookAuthorList}" varStatus = "status">
															<c:choose>
																<c:when test="${concatAuthor != ''}">
																	<c:set var="concatAuthor" value="${concatAuthor}~${author.authorFullName}" scope="page" />	
																</c:when>	
																<c:otherwise>
																	<c:set var="concatAuthor" value="${author.authorFullName}" scope="page" />	
																</c:otherwise>
															</c:choose>
														</c:forEach>
													<input type="hidden" id="hiddenVal" name="hiddenval" value="${concatAuthor}"/>
												</c:otherwise>
												</c:choose>	
                                            	<button type="button" class = "mb-xs mt-xs mr-xs btn btn-danger" onclick='deleteRow1(this);'>X</button>
                                           	</td>
										</tr>
									</tbody>
								</table>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary" onclick="return validateBookRequisition();" value="SUBMIT">Submit</button>
                                <button type="button" class="mb-xs mt-xs mr-xs btn btn-warning" onclick ="window.location='viewBookStock.html'">Cancel Requisition</button>
                            </footer>
						</section>
					</div>
                </form:form>
            </div>	
		</div>			
	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/library/createRequisition.js"></script>
</body>
</html>