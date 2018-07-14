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
	<title>Set Promotional Exam</title>
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
</head>
<body>
		<c:if test="${insertUpdateStatus ne null}">
			<div class="alert alert-success" id="infomsgbox1"  >
				<strong>${insertUpdateStatus}</strong>	
			</div>
		</c:if>
		<c:if test="${msg eq 'success'}">
			<div class="alert alert-success"  >
				<strong>Updated SuccessFully</strong>	
			</div>
		</c:if>
			<c:if test="${msg eq 'fail'}">
			<div class="alert alert-success">
				<strong>Failed To Update</strong>	
			</div>
		</c:if>
		<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form action="submitPromotionalExam.html" method="post" class="form-horizontal">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Set Promotional Exam </h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<%-- <div class="form-group">
											<label class="col-sm-4 control-label">Class Name</label>
											<div class="col-sm-8">
												<select class="form-control" id="standard" name="standard" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="standard" items="${standardList}">	
														<option value="${standard.standardCode}">${standard.standardName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>  --%>
                                        <div class="form-group">
											<label class="col-sm-4 control-label">Programme Name </label>
											<div class="col-sm-8">
												<select class="form-control" id="course" name="course" required>
                                                    <option value="">Select...</option>
                                                     <c:forEach var="course" items="${courseList}">	
														<option value="${course.courseCode}">${course.courseName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label">Select Examination </label>
											<div class="col-sm-8">
												<select class="form-control" id="exam" name="exam" required>
                                                    <option value="">Select...</option>
                                                </select>
											</div>
										</div>   
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
					</div>	
					<div class="row">   
						<form name="editPromotionalExam" id="editPromotionalExam" action="editPromotionalExam.html" method="post">
						<input type="hidden" name="saveId" id="saveId">
                            <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Promotional Exam List</h2>
	                                </header>
	                                	<div class="panel-body">
		                                	<c:forEach var="exam" items="${examListDB}" varStatus="i">
												<input type="hidden" name="oldExamNames" value="${exam.examCode}">
											</c:forEach>
			                                    <table id="datatable-editable" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                            <th>Class</th>
			                                            	<th>Course</th>
			                                            	<th>Exam Name</th>
															<th>Actions</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="exam" items="${examListDB}" varStatus="i">
			                                        		
																	<tr>
																		<td>
																			${exam.standardCode}
																			<input type="hidden" id="standardCode${i.index}" name="standardCode${i.index}" class="form-control" value="${exam.standardCode}" readonly />
																			<input type="hidden" id="serialId${i.index}" name="serialId${i.index}" class="form-control" value="${exam.serialId}" readonly />
																		</td>
																		<td>${exam.termCode}
																		<input type="hidden" id="courseCode${i.index}" name="courseCode${i.index}" class="form-control" value="${exam.termCode}" readonly /></td>
																		<td>
																			<select id="examName${i.index}" name="examName${i.index}" class="form-control" disabled>
																				<c:forEach var="examName" items="${examList}">
																				<%-- 	<c:forEach var="course" items="${examName.courseList}">
																						<c:if test="${course.standard eq exam.standardCode}"> --%>
																							<option value="${examName.examName}" ${examName.examName eq exam.examName?'selected':value}>${examName.examName}</option>
																						<%-- </c:if>
																					
																					</c:forEach> --%>
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/academics/setPromotionalExam.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/promotionalExam.editable.js"></script>
</body>


</html>