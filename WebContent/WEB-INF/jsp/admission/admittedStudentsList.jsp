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
<script type="text/javascript" src="/cedugenie/js/admission/currentOpeningList.js"></script>
</head>
<body>

							
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Admitted Student List</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Year</th>
															<th>Course Name</th>
															<th>Course Type</th>
															<th>Form ID</th>
															<th>First Name</th>
															<th>Middle Name</th>
															<th>Last Name</th>
															<th>Contact Number</th>
															<th>Email ID</th>
															<th>Date of Admission</th>
															<th>Status</th>	
			                                            </tr>
			                                        </thead>
			                                        <tbody>
														<c:forEach var="admittedStudentListDetails" items="${admittedDriveListDetails}">
																	<tr>
																		<td ><c:out value="${admittedStudentListDetails.admissionYear}"/></td>
																		<td><c:out value="${admittedStudentListDetails.courseName}"/></td>
																		<td><c:out value="${admittedStudentListDetails.courseType}"/></td>
																		<td><c:out value="${admittedStudentListDetails.formId}"/></td>
																		<td><c:out value="${admittedStudentListDetails.candidateFirstName}"/></td>
																		<td><c:out value="${admittedStudentListDetails.candidateMiddleName}"/></td>
																		<td><c:out value="${admittedStudentListDetails.candidateLastName}"/></td>
																		<td><c:out value="${admittedStudentListDetails.candidateContactNo}"/></td>
																		<td><c:out value="${admittedStudentListDetails.candidateEmail}"/></td>	
																		<td><c:out value="${admittedStudentListDetails.paymentDate}"/></td>	
																		<td><c:out value="${admittedStudentListDetails.formStatus}"/></td>	
																	</tr>
															</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
						


<script src="/cedugenie/js/admission/viewMeritList.js"></script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>