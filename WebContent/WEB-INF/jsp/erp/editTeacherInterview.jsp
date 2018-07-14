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
<title>Edit Teacher Interview Result</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>		
		<c:choose>
			<c:when test="${staffTeacherInterview == null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
							<span id="errormsg">No Candidate Found For Interview </span>	
				</div>
			</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form method="POST" id="aircontent" name="aircontent"  action="submitTeacherInterviewDetails.html" >
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Teacher Interview Result</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
                                             <div class="form-group">
                                                 <label class="control-label">Candidate ID </label>
                                                 <input class="form-control" name="employeeCode" id="candidateId" value="${staffTeacherInterview.employeeCode}" readonly>                                                 
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Candidate Name </label>
                                                 <input class="form-control" name="name" id="name" value="${staffTeacherInterview.resource.firstName} ${staffTeacherInterview.resource.middleName} ${staffTeacherInterview.resource.lastName}" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Highest Qualification</label>
                                                 <input class="form-control" type="text" name="qualification.qualificationName" id="qualification" value="${staffTeacherInterview.qualification.qualificationName}" readonly>
                                             </div>
                                             <div class="form-group">                                             	    
                                             	 <label class="control-label">Subject Specialization </label>  
												 <input class="form-control" type="text" name="qualification.specialization" id="specialization" value="${staffTeacherInterview.qualification.specialization}" readonly>
										  		 <input type="hidden" name="control" value="SubmitInterviewResult"> 
										  	</div>                                            
                                              <div class="form-group">
                                       	     	 <label class="control-label">Referred By </label>  
                                             	 <input class="form-control" type="text" name="referredBy" id="referredBy" value="${staffTeacherInterview.referredBy}" readonly>                                          
                                             </div>
                                         </div>
                                         <div class="col-md-3">
                                         <table class="table table-bordered table-striped mb-none" id="teacherInterviewid">
                                          	<tr>			
											<td colspan="2">			 	
												<table>
													<tr>						
														<th>Subject</th>		
														<th>Marks</th>
														<!-- <th>Delete</th> -->
													</tr>
												  	<c:forEach var="marks" items="${staffTeacherInterview.marksList}">
											       		 <tr>				           
											            <td> 
											            	<input class="form-control" type="text" name="subject" value="${marks.subjectName}"/> 
											            </td>
														<td> 
															<input class="form-control" type="text" name="subMarks" value="${marks.avgMarks}"/> 
														</td>
														<%--  <td>
															<img name="chk" value="${marks.subjectName}" src="/sms/images/minus_icon.png" onclick="deleteRow(this);" />
														</td> --%>
														 </tr>
													</c:forEach>
											   	</table>
											   	</td>
											   	<!-- <input type="button" class="addbtn"  onclick="if(validate()) addRow('dataTable')" >	 -->
											   	<!-- <input type="button" class="addbtn" value="Add" onclick="if(validate()) addRow('dataTable')" >		 -->								
										</tr>         
									</table>                           
                                         </div>                               
                                         
                                        <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="control-label">Comment</label>
                                                	<textarea id="strComment" class="txtarea" name="comment">${staffTeacherInterview.comment}</textarea>
                                                </div>
                                                <div> 
                                                	 <label class="control-label">Decision</label>                                                			
													<c:choose>
														<c:when test="${staffTeacherInterview.interviewStatus eq 'SELECTED'}">									
															<input type="radio" id="strStatus" name="status" class="css-checkbox" value="SELECTED" checked="checked" />
															<label for="strStatus" class="css-label">SELECTED</label>
														</c:when>
														<c:otherwise>									
															<input type="radio" id="strStatus" name="status" class="css-checkbox" value="SELECTED" />
															<label for="strStatus" class="css-label">SELECTED</label>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when test="${staffTeacherInterview.interviewStatus eq 'NOTSELECTED'}">									
															<input type="radio" id="strStatus1" class="css-checkbox" name="status" value="NOTSELECTED" checked="checked" />
															<label for="strStatus1" class="css-label">NOT SELECTED</label>
														</c:when>
														<c:otherwise>									
															<input type="radio" id="strStatus1" class="css-checkbox" name="status" value="NOTSELECTED"/>
															<label for="strStatus1" class="css-label">NOT SELECTED</label>
														</c:otherwise>
													</c:choose>															
                                                </div>   
                                       	</div>                                
                                    </div>
							</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" value="SUBMIT" onclick="return valir();">Submit </button>
							<button type="reset" class="btn btn-default">Reset</button>
						</footer>
					</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
		</c:choose>
			
		




<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src= "/icam/js/erp/teacherInterviewResult.js" type="text/javascript"></script>
</body>
</html>