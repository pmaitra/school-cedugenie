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
	<c:if test="${insertUpdateStatus eq 'success' }">
		<div class="alert alert-success" id="successboxmsgbox">
			<strong> ${msg}</strong>	
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus ne null && insertUpdateStatus eq 'delete' }">
		<div class="alert alert-danger" id="errormsgbox">
			<strong>Deleted Successfully </strong>	
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus eq 'fail' }">
		<div class="alert alert-danger" id="errormsgbox">
			<strong>${msg} </strong>	
		</div>
	</c:if>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						 	 <form:form  name="teacherClassSubjectMapping" id="teacherClassSubjectMapping" action="teacherClassSubjectMapping.html" method="POST">
						 
							
								
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Teacher Class Mapping</h2>										
									</header>
									<%-- <input type="hidden" class="form-control" id="moduleCodeHidden" name = "moduleCodeHidden" value = "${moduleCode}">
									<input type = "hidden" class="form-control" name="updatedBy" id="updatedBy" value = "${updatedBy}" > --%>
									<div style="display: block;" class="panel-body">
                                        <input type="hidden" id="jsonData">
										<div class="form-group">
                                            <label class="control-label">Teacher's Name <span class="required" aria-required="true">*</span></label>
                                            <select class="col-sm-5 form-control" name="teacherId" id="teacherId" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="teacher" items="${teacherList}">
													<option value="${teacher.userId}">${teacher.name}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Subject <span class="required" aria-required="true">*</span></label>
                                            <select class="col-sm-5 form-control" name="subject" id="subject" required>
                                                <option value="">Select...</option>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Course</label>
                                            <select class="col-sm-5 form-control" name="course" id="course" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="course" items="${courseList}">
													<option value="${course.courseCode}">${course.courseName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                       <div class="form-group">
                                            <label class="control-label">Section</label>
                                            <select class="col-sm-5 form-control" name="section" id="section" required>
                                                <option value="">Select...</option>
                                            </select>
                                        </div>  
                                       <!--  <div class="form-group">
                                            <label class="control-label">No Of Class</label>
                                              <input type="text" class="col-sm-5 form-control" id="noOfClass" name="noOfClass">
                                        </div>  --> 
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           <%--  </c:otherwise>
                            </c:choose> --%>
                            </form:form>
                            
						</div>
						
					</div>	
					<div class="col-md-12">
						<%-- <form name="editTeacherClassMapping" id="editTeacherClassMapping" action="editTeacherClassMapping.html" method="post">	 --%>
                           	<input type="hidden" name="saveId" id="saveId">
                           	<input type="hidden" name="statusValue" id="statusValue">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Teachers</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                        <thead>
                                            <tr>
                                                <th>Teacher Name</th>
                                                <th>Subject</th>
                                                <th>Course</th>
                                                <th>Section</th> 
                                                <!-- <th>No Of Days</th>  --> 
                                                <th>Actions</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach var="teacher"  items="${teacherSubjectMappingList}" varStatus="i">
	                                                <tr>
	                                                	
	                                                    <td>
	                                                    	<input type="hidden" name="rowId${i.index}" id="rowId${i.index}" value="${teacher.serialId}"/>
	                                                    	<input type="hidden" name="teacherId${i.index}" id="teacherId${i.index}" value="${teacher.status}"/>${teacher.teacherName}</td>
	                                                    <td><input type="hidden" name="subject${i.index}" id="subject${i.index}" value="${teacher.subject}"/>${teacher.subject}</td>
	                                                    <td><input type="hidden" name="standard${i.index}" id="standard${i.index}" value="${teacher.standardName}"/>${teacher.standardName}</td>
	                                                    <td><input type="hidden" name="section${i.index}" id="section${i.index}" value="${teacher.sectionName}"/>${teacher.sectionName}</td>
	                                                    <%-- <td><input type="text" id="noOfClass${i.index}" name="noOfClass${i.index}" class="form-control" value="${teacher.noOfClass}" readonly /></td> --%>
 	                                                    <td class="actions">
															<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
															<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
															<a href="#" class="on-default edit-row" id="edit${i.index}" ><i class="fa fa-pencil"></i></a> --%>
															<a href="inactiveTeacherClassSubjectMapping.html?serialId=${teacher.serialId}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
														 </td>
	                                                </tr>
	                                              </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                           <%-- </form> --%>
						</div>



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/erp/createteacherClassSubjectMapping.editable.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/teacherClassMapping.js"></script>
</body>
</html>