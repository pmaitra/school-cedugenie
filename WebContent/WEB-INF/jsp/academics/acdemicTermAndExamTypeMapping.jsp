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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

</head>
<body>
				<div class="row">
					<c:if test="${successMessage ne null}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<strong>${successMessage}</strong>	
						</div>
					</c:if>
			
					<c:if test="${errorMessage ne null}">
							<div class="alert alert-danger" id="errormsgbox" >
								<strong>${errorMessage}</strong>	
							</div>
					</c:if>
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

										<h2 class="panel-title">Term And Exam Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
                                           <form name="termExamTypeMap" id="termExamTypeMap" action="submitAcdemicTermAndExamTypeMapping.html" method="POST">
                                            <div class="col-md-12">
                                                <table class="table table-bordered table-striped mb-none" id = "acdemicTermAndExamTypeMappingTab">
                                                    <thead>
                                                        <tr>
                                                            <th>Standard Name</th>
                                                            <th>Course Name</th>
                                                            <th>Examination Type Name</th>
                                                            <th>Exam</th>
                                                            <th>Term</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <select class="form-control" name="className" id = "className0" onchange="getCourse(this);" required>
                                                                    <option value="">Select...</option>
	                                                                    <c:forEach var="klass" items="${classListFromDB}">
																			<option value="${klass.standardCode}">${klass.standardName}</option>					
																		</c:forEach>
                                                                </select>
                                                            </td>
                                                             <td>         
																<select id="courseNames0" class="defaultselect" name="courseName" onchange="getExamType(this);"> 
				            										<option value="">Select...</option>        
		      												   </select>
															</td>
														     <td>
                                                                <select class="form-control" name="examType" id = "examType0" onchange="getExam(this);" required >
                                                                    <option value="">Select...</option>    
                                                                </select>
                                                            </td>
                                                            <td>
																<select name="examName" class="defaultselect" id="examName0">
														           	<option value="">Select</option>           	     
														        </select>		
															</td>	
                                                            <td>
																<select name="academicTerm" class="defaultselect" id="academicTerm0">
														           	<option value="">Select</option>           	     
														        </select>		
															</td>	
                                                            <td><!-- <a class="on-default remove-row" href="#" onclick="deleteRow(this);"><i class="fa fa-trash-o"></i></a> --></td>
                                                        </tr>
                                                    </tbody>                                                    
                                                </table>
                                                <a class="mb-xs mt-xs mr-xs modal-basic btn btn-info pull-right" href="#modalInfo" id="addrow" onclick="addExamRow();">Add</a>
                                            </div>
                                            </form>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type = "submit" class="btn btn-primary" id="submit" >Submit </button>
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
                            <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Exam List</h2>
	                                </header>
	                                	<div class="panel-body">
		                                	<c:forEach var="exam" items="${examListDB}" varStatus="i">
												<input type="hidden" name="oldExamNames" value="${exam.examName}">
											</c:forEach>
			                                    <table id="datatable-editable" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                            	<th>Class Name 	</th>
			                                                <th>Course </th>
															<th>Exam Name</th>
															<th>Exam Type</th>
															<th>Exam Type Name</th>
															<th>Actions</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="exam" items="${examListDB}" varStatus="i">
			                                        		
																	<tr >
																		<td>
																			${exam.standard.standardName}
																			<input type="hidden" id="standardCode${i.index}" name="courseCode${i.index}" class="form-control" value="${exam.standard.standardName}" readonly />
																		</td>
																		<td>
																			<select multiple data-plugin-selectTwo class="form-control populate" id = "courseName${i.index}" name = "courseNames" style = "width:307px">
																				<c:forEach var="courseList" items="${exam.courseList}">
																					<option value = "${courseList.courseCode}">${courseList.courseName}</option>
																				</c:forEach>
																			</select>
																		</td>
																		<td><c:out value="${exam.examName}"></c:out></td>
																		<td><c:out value="${exam.examTypeName}"></c:out></td>
																		<td>
																			<select id="examTypeName${i.index}" name="examTypeName${i.index}" class="form-control" disabled>
																				<c:forEach var="examType" items="${examTypeListFromDB}">
																					<option value="${examType.examTypeName}" ${examType.examTypeName eq exam.examTypeName?'selected':value}>${examType.examTypeName}</option>
																				</c:forEach>
																			</select>
																		</td>
																		<td class="actions">
																			<a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
																			<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
																			<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a>
																		</td>
																	</tr>
																	
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
                           </form>                                               
                     </div>
<select id="roleNameDefault" style="visibility: collapse;" class = "form-control">
	<option value="">Select...</option>
	<c:forEach var="role" items="${roleList}">
		<option value="${role.roleCode}">${role.roleName}</option>					
	</c:forEach>								
</select>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>	
<script type="text/javascript" src="/cedugenie/js/academics/acdemicTermAndExamTypeMapping.js"></script>			
</body>
</html>