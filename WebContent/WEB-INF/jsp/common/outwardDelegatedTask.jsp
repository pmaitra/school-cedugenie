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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<!-- <script type="text/javascript" src="/icam/js/admission/currentOpeningList.js"></script> -->
</head>
<body>

							
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Allocated Task</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
															<th>Delegated On</th>
															<th>Delegated By</th>
															<th>Present User</th>
															<th>Task</th>
															<th>Status</th>
														
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="admissionFormlist" items="${delegatedTaskList}">
			                                        		
																	<tr >
																		<td>
																			<c:out value="${admissionFormlist.activationTime}"></c:out>
																		</td>
																		<td><c:out value="${admissionFormlist.createdById}"></c:out></td>
																		<td><c:out value="${admissionFormlist.taskOwnerName}"></c:out></td>
																		<td><c:out value="${admissionFormlist.taskDesc}"></c:out></td>
																		<td><c:out value="${admissionFormlist.status}"></c:out>
																		<%--<td class="actions">
																			 <form:form method="POST" action="generateAdmissionForm.html"> --%>
																			<%-- 	<input type="hidden" name="courseClass" value="<c:out value="${admissionFormlist.courseClass}"/>"/>
																				<input type="hidden" name="admissionFormYear" value="<c:out value="${admissionFormlist.admissionFormYear}"/>"/>
																				
																				<input type="hidden" name="courseName" value="<c:out value="${admissionFormlist.courseName}"/>"/>
																				<input type="hidden" name="courseType" value="<c:out value="${admissionFormlist.courseType}"/>"/>
																				<input type="hidden" name="admissionFormSubmissionLastDate" value="<c:out value="${admissionFormlist.admissionFormSubmissionLastDate}"/>" />
																				<input type="hidden" name="admissionDriveName" value="<c:out value="${admissionFormlist.admissionDriveName}"/>" />
																				<!-- <button type="submit" style="border: 0; background: transparent;">
																					<img src="/icam/images/blank_page.png" style="border-style: none; width:50px; height:50px;">
																				</button> -->
																				<input type="hidden" name="control" value="admissionForm"/>
																				<a href="generateAdmissionForm.html?courseClass=${admissionFormlist.courseClass}&admissionFormYear=${admissionFormlist.admissionFormYear}&courseName=${admissionFormlist.courseName}&courseType=${admissionFormlist.courseType}&admissionFormSubmissionLastDate=${admissionFormlist.admissionFormSubmissionLastDate}&admissionDriveName=${admissionFormlist.admissionDriveName}&control=admissionForm" class="on-default"><i class="fa fa-file-text"></i></a>
																			</form:form> 
																		</td>--%>
																	</tr>
																	
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
						

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>