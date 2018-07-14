<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
	
<head>

	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Teacher Subject Mapping</title>
	<%@ include file="/include/include.jsp" %>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
<!-- 	<script type="text/javascript">
		
		
	</script> -->
</head>
	<body>
		<div class="btnsarea01">
		<c:if test="${updateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>Created Successful.</strong>
				</div>
			</c:if>
			<c:if test="${updateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>Creation Failed.</strong>
				</div>
			</c:if>
		</div>
			<c:choose>
			<c:when test="${staffTeacherIdSubjects eq null }">
				<div class="btnsarea01" >
					<%-- <div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
							<span id="errormsg">Subject Group Not Found</span>	
						</c:if>
						<c:if test="${subjectList eq null || empty subjectList}">
							<span id="errormsg">Subject Not Found</span>	
						</c:if>
						<c:if test="${standardList eq null || empty standardList}">
							<span id="errormsg">Standard Not Found</span>	
						</c:if>
					</div> --%>
				</div>
			</c:when>
			<c:otherwise>
			<form name="subjectForm" id="subjectForm" method="POST" action="submitTeacherSubjectMapping.html" >
				<!-- start: page -->
					 <div class="row">
						<div class="col-md-4 col-md-offset-4">
						  <form id="form1">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Teacher Subject Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										
                                        <div class="form-group">
                                            <label class="control-label">Teacher Id<span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" id="teacherId" name="teacherId" required>
                                            <option value="">Select</option>
                                               <c:forEach var="teacherId" items="${staffTeacherIdSubjects.resourceList}" varStatus="i">
													<option value="${teacherId.userId}">${teacherId.userId}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Teacher Name<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" id="teacherName" name="teacherName" readonly="readonly">
                                        </div>
                                         <div class="form-group">
                                            <label class="control-label">Teacher Designation<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" id="designation" name="designation" readonly="readonly">
                                        </div>
                                         <div class="form-group">
                                            <label class="control-label">Job Type<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" id="jobType" name="jobType" readonly="readonly">
                                        </div>
                                        <!--  <div class="form-group">
                                            <label class="control-label">Teaching Level<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" id="teachingLevel" name="teachingLevel.teachingLevelName" readonly="readonly">
                                        </div>   -->
									</div>
								</section>
                            </form>
						</div>
						<div class="col-md-12">						  
								
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
							  									<span> </span>	
														</div>
						
								<h2 class="panel-title"> Course Subject Mapping</h2>
							</header>
							<div class="panel-body">
							<c:set var="i" value="0" scope="page" />
							<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="j">
							 <c:if test="${i eq 0}">
								<div class="row">
							</c:if>
							<c:set var="i" value="${i+1}" scope="page" />
                                
                                    <div class="col-md-3">
                                        <div class="well well-sm info no-margin">
                                            <div>
                                                Subject Name :: ${subjectGroup.subjectGroupName}
                                            </div>
                                        </div>
                                        <table class="table table-bordered table-striped mb-none">
                                            <thead>
                                                <tr>
                                                    <th>Select</th>
                                                    <th>Subject Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="subject" items="${subjectList}" varStatus="k">
                                            	<c:if test="${subject.subjectGroup eq subjectGroup.subjectGroupName}">
	                                                <tr>
	                                                    <td><input type="checkbox" class="subjectsCheckBox" id="${subject.subjectName}" name="subjects" value="${subject.subjectName}"></td>
	                                                    <td>${subject.subjectName}</td>
	                                                </tr>
	                                           </c:if>
                                               </c:forEach>
                                            </tbody>
                                        </table>								    
                                    </div>
                                
                                <c:if test="${i eq 4}">
									<c:set var="i" value="0" scope="page" />
                                	</div>
									<hr>
								</c:if>
                               </c:forEach>
								<c:if test="${i ne 4}">
                                	</div>
								</c:if>
								<footer style="display: block;" class="panel-footer">
	                                <button class="btn btn-primary" type = "submit" onclick = "return validate()">Submit </button>
	                                <button type="reset" class="btn btn-default">Reset</button>
	                            </footer>
								</section>
							</div>
                            
                            <div id="oldSubjects" style="display: none;"></div>
			</form>
			</c:otherwise>
			</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/teacherSubjectMapping.js"></script>
</body>


</html>