<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Admission On Process</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
</head>
<body>
	<header class="page-header">
		<h2>Admission On Process</h2>
	</header>
	<div class="content-padding">
		<div class="col-md-12">
	       	<section class="panel">
	           	<header class="panel-heading">
					<h2 class="panel-title"> Admission On Process List</h2>
	            </header>
	              	<div class="panel-body">
	                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
	                        <thead>
	                            <tr>
	                              <!--   <th>Class</th> -->
									<th>Year</th>
									<th>Drive Name</th>
									<th>Course Name</th>
									<th>Course Type</th>
									<th>Course Duration</th>
									<th>Number Of Openings</th>
	                            </tr>
	                      	 </thead>
	                         <tbody>
	                           	<c:forEach var="admissionForm" items="${AdmissionsOnProcessList}">	
	                        		<tr class="gradeX">
								<%-- <td>
									<a  href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>"target="blank"><c:out value="${admissionForm.courseClass}"/></a>
								</td> --%>
								<td>
									<a  href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.admissionFormYear}"/></a>
								</td>
								<td>
									<a href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.admissionDriveName}"/></a>
								</td>
								<td>
									<a  href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.courseName}"/></a>
								</td>
								<td>
									<a  href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.courseType}"/></a>
								</td>
								<td>
									<a href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.courseDuration}"/></a>
								</td>
								<td>
									<a   href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>" target="blank"><c:out value="${admissionForm.noOfOpenings}"/></a>
									</td>
								</tr>
								</c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	             </section>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

</body>
</html>