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
<title>Create Exam</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Create Exam</h2>
</header>
<div class="content-padding">
	<c:if test="${insertUpdateStatus eq 'success'}">
		<div class="alert alert-success">
			<strong>${msg}</strong>
		</div>
	</c:if>
	
	<c:if test="${insertUpdateStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>${msg}</strong>
		</div>
	</c:if>
	<div class="row">
		<div class="col-md-12">
			<form:form action="submitExam.html" method="POST">
		 	<c:choose>
				<c:when test="${classListFromDB eq null || classListFromDB.size() eq 0}">
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">No Standard Found </span>	
					</div>						
				</c:when>
			<c:otherwise>
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Create Exam</h2>										
					</header>
					<c:forEach var="course" items="${courseListFromDB}">
						<input type="hidden" name="className1"  id= "className1" value="${course.courseName}">
					</c:forEach>
					<div style="display: block;" class="panel-body">
	                    <div class="row">
                       		<form action="submitExam.html" method="POST">
	                        	<div class="col-md-12">
	                            	<table class="table table-bordered table-striped mb-none" id = "examTab">
		                                <thead>
		                                    <tr>
		                                        <th>Standard</th>
		                                        <th>Exam</th>
		                                        <th>Subject</th>
		                                        <!-- <th>Exam Type</th> -->
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td>
                                             		<select class="form-control" name="className" id = "className0"  required>
	                                                	<option value="">Select...</option>
	                                                 	<c:forEach var="course" items="${courseListFromDB}">
															<option value="${course.courseCode}">${course.courseName}</option>					
														</c:forEach>
                                                 	</select>
                                             	</td>
                                              	<td>
													<input  class="form-control"  type="text" id="examName0" name="examName" title="Exam Name Contains Charecter" required/>
												</td>
												<td>
											 		<select class="form-control" name="courseNames" id = "courseName0"  required>
														<option value="">Select...</option>
													</select>
											    </td>
	    										<%-- <td>
                                                	<select class="form-control" name="examType" id = "examType0" required >
                                                    	<option value="">Select...</option>
                                                      		<c:forEach var="examType" items="${examTypeListFromDB}">
																<option value="${examType.examTypeCode}">${examType.examTypeName}</option>					
															</c:forEach>
		                                            </select>
		                                        </td> --%>
		                                    </tr>
		                                </tbody>
	                            	</table>
	                        	</div>
	                        </form>
	                    </div>
					</div>
					<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
 						<span></span>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type = "submit" class="btn btn-primary" id="submit" onclick=" return validatingExam()">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
			</c:otherwise>
			</c:choose>
        	</form:form>
		</div>
	</div>
	<div class="row">   
		<form name="editExam" id="editExam" action="editExam.html" method="post">
			<input type="hidden" name="saveId" id="saveId">
			<input type="hidden" name="getExamName" id="getExamName">
            	<div class="col-md-12">
                	<section class="panel">
                    	<header class="panel-heading">
                            <h2 class="panel-title">Exam List</h2>
                        </header>
                       	<div class="panel-body">
                       		<c:forEach var="exam" items="${examListDB}" varStatus="i">
								<input type="hidden" name="oldExamNames" value="${exam.examName}">
							</c:forEach>
							<%-- <c:forEach var="examType" items="${examTypeListFromDB}">
								<input type="hidden" name="examTypeNames" value ="${examType.examTypeName}">
							</c:forEach> --%>
							<c:forEach var="exam" items="${examListDB}">
								<input type="hidden" name="oldProgNames" value ="${exam.standard.standardName}">
							</c:forEach>
							<c:forEach var="course" items="${courseListFromDB}">
								<input type="hidden" name="newProgName" value ="${course.courseName}">
							</c:forEach>
                            	<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
                              		<thead>
                               			<tr>
                                           	<th>Standard</th>
                                            <th>Subject</th>
											<th>Exam</th>
											<!-- <th>Exam Type</th> -->
											<th>Actions</th>
                                       	</tr>
                                   	</thead>
                                   	<tbody>
                              			<c:forEach var="exam" items="${examListDB}" varStatus="i">
											<tr>
												<td>
													${exam.standard.standardName}
													<input type="hidden" id="standardCode${i.index}" name="courseCode${i.index}" class="form-control" value="${exam.standard.standardCode}" readonly />
												</td>
												<td>
													<input type="hidden" id="courseName${i.index}" name="courseNames" class="form-control" value="${exam.course.courseCode}" readonly />
													${exam.course.courseName}
												</td>
												<td><c:out value="${exam.examName}"></c:out>
													<input type="hidden" id="examCode${i.index}" name="examCode${i.index}" class="form-control" value="${exam.examCode}" readonly />
												</td>
												<%-- <td>
													<select id="examTypeName${i.index}" name="examTypeName${i.index}" class="form-control" disabled >
														<c:forEach var="examType" items="${examTypeListFromDB}">
															<option value="${examType.examTypeName}" ${examType.examTypeName eq exam.examTypeName?'selected':value}>${examType.examTypeName}</option>
														</c:forEach>
													</select>
												</td> --%>
												<td class="actions">
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showExamDetails('${i.index}','${exam.standard.standardName}','${exam.course.courseName}','${exam.examName}')"><i class="fa fa-pencil"></i></a>
													<a href="inactiveExam.html?examCode=${exam.examCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a> 
												</td>
											</tr>
										</c:forEach>
                                   	</tbody>
                               	</table>
                           	</div>
                   	</section>
                   	<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                    	<section class="panel">
	                        <header class="panel-heading">
	                        	<h2 class="panel-title">Exam Details</h2>
	                        </header>
                            <div class="panel-body">
                                <table class="table table-bordered table-striped mb-none" id = "approverGroupDetails">
                                    <thead>
                                        <tr>
                                            <th>Standard</th>
                                            <th>Subject</th>
                                            <th>Exam Name</th>
                                            <!-- <th>Exam Type Name</th> -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="updateTerms" class="btn btn-success">Update</button>
										<button class="btn btn-danger modal-dismiss">Cancel</button>
									</div>
								</div>
							</footer>
                   		</section>
                	</div>
				</div>
           	</form>                            
   		</div>
</div>
<%-- <select id="roleNameDefault" style="visibility: collapse;" class = "form-control">
	<option value="">Select...</option>
	<c:forEach var="role" items="${roleList}">
		<option value="${role.roleCode}">${role.roleName}</option>					
	</c:forEach>
</select> --%>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/academics/createExam.js"></script>	
<script type="text/javascript" src="/cedugenie/js/academics/createExam.editable.js"></script>	
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>		
</body>
</html>