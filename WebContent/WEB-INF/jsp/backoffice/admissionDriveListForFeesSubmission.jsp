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
</head>
<body>

							
							<div class="col-md-12">
								<c:choose>
									<c:when test="${admissionsOnProcessListFromDb eq null}">
										<div class="alert alert-danger">
											<strong>No Admission Drive Found.</strong>
										</div>
									</c:when>
								<c:otherwise>
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Available Drive List For Fees Submission</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <!-- <th>Class</th> -->
															<th>Year</th>
															<th>Drive</th>
															<th>Course</th>
															<th>Course Type</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        <c:forEach var="admissionForm"  items="${admissionsOnProcessListFromDb}">
			                                        		
															<tr class="gradeX">
																<%-- <td>
																	<a href="selectedCandidatesForFeesSubmission.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&courseName=<c:out value="${admissionForm.courseName}"/>"><c:out value="${admissionForm.courseClass}"/></a>
																</td> --%>
																<td>
																	<a  href="selectedCandidatesForFeesSubmission.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&courseName=<c:out value="${admissionForm.courseName}"/>"><c:out value="${admissionForm.admissionFormYear}"/></a>
																</td>
																<td>
																	<a href="selectedCandidatesForFeesSubmission.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&courseName=<c:out value="${admissionForm.courseName}"/>"><c:out value="${admissionForm.admissionDriveName}"/></a>
																</td>
																<td>
																	<a href="selectedCandidatesForFeesSubmission.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&courseName=<c:out value="${admissionForm.courseName}"/>"><c:out value="${admissionForm.courseName}"/></a>
																</td>
																<td>
																	<a href="selectedCandidatesForFeesSubmission.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&courseName=<c:out value="${admissionForm.courseName}"/>"><c:out value="${admissionForm.courseType}"/></a>
																</td>
															</tr>
																	
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
			                            </section>
			                        </c:otherwise>
		                        </c:choose>    
							</div>
						



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>