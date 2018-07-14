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
<title>Admitted Drive List</title>
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
	                            <section class="panel">
	                                <header class="panel-heading">
										 <h2 class="panel-title">Admitted Drive List </h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Admission Drive</th>
			                                                <th>Year</th>
															<!-- <th>Standard</th> -->
															<th>Course</th>
															<th>Course Type</th>
															<th>Drive Start Date</th>
															<th>Form Submission Last Date</th>
															<th>Expected End Date</th>
															<th>Drive Actual End Date</th>
															<th>No. Of Openings</th>
															<!-- <th>No Of Admitted</th> -->
															<th>Status</th>	
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="admittedDrive" items="${admittedDriveList}">
			                                        		
																	<%-- <tr class="gradeX" onClick="window.open('historyAdmissionOnProcess.html?courseClass=<c:out value="${driveName.courseClass}"/>&year=<c:out value="${driveName.admissionFormYear}"/>&driveName=<c:out value="${driveName.admissionDriveName}"/>'),'_blank'" style="cursor:pointer;">
																		<td>
																			<c:out value="${driveName.admissionDriveName}"/>
																		</td>
																		<td><c:out value="${driveName.courseClass}"/></td>
																		<td><c:out value="${driveName.courseName}"/></td>
																		<td><c:out value="${driveName.noOfOpenings}"/></td>
																		<td><c:out value="${driveName.status}"/></td>
																	</tr> --%>
																	<tr>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${admittedDrive.admissionDriveName}"/></a>								
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${admittedDrive.admissionFormYear}"/></a>
																</td>
																<%-- <td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${admittedDrive.courseClass}"/></a>
																</td> --%>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${admittedDrive.courseName }" /></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.courseType}"/></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.admissionDriveStartDate}"/></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.admissionFormSubmissionLastDate}"/></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.admissionDriveExpectedEndDate}"/></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.admissionDriveActualEndDate}"/></a>
																</td>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.noOfOpenings}"/></a>
																</td>
																<%-- <td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${ admittedDrive.admissionFormYear }"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${admittedDrive.noOfAdmittedStudents}"/></a>
																</td> --%>
																<td>
																	<a class="admittedDrives" href="admittedDriveListDetails.html?courseClass=<c:out value="${admittedDrive.courseClass}"/>&year=<c:out value="${admittedDrive.admissionFormYear}"/>&drivename=<c:out value="${admittedDrive.admissionDriveName}"/>" target=""><c:out value="${ admittedDrive.status}"/></a>
																</td>
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