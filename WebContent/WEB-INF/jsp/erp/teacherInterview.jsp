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
<title>Teacher Interview</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">		<!-- Added by Saif 29-03-2018 -->
		<h2>Teacher Interview</h2>
	</header>
		<div class = "content-padding">
			<%-- <c:choose>
			<c:when test="${insertStatus == null}">
			</c:when>
			<c:otherwise>
				<c:if test="${insertStatus != null}"> --%>
					<c:if test="${insertStatus == 'success'}">
						<div class="alert alert-success">
								<strong>Data Successfully Inserted</strong>	
						</div>			
					</c:if>
			
					<c:if test="${insertStatus == 'fail'}">
						<div class="alert alert-danger" >
										<strong>Problem Occur While Saving</strong>	
						</div>			
					</c:if>
				
				<%-- </c:if>
			</c:otherwise>
		</c:choose>	 --%>
		
		
		<%-- <c:choose>
			<c:when test="${staffCandidateId == null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
							<span id="errormsg">No Candidate Found For Interview </span>	
				</div>
			</c:when>
		<c:otherwise> --%>
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
                                                 <label class="control-label">Candidate ID<span class="required" aria-required="true">*</span></label>
                                                  <select name='employeeCode' id="candidateId" class="form-control" required>
													<option value="">Select.. </option>
													<c:forEach var="candidateId" items="${staffCandidateId}">
														<option value="<c:out value="${candidateId.employeeCode}"/>"><c:out value="${candidateId.employeeCode}"/></option>									
													</c:forEach>		
												</select>                                                
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Candidate Name </label>
                                                 <input class="form-control" name="name" id="name" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Highest Qualification</label>
                                                 <input class="form-control" type="text" name="qualification.qualificationName" id="qualification" readonly>
                                             </div>
                                             <div class="form-group">                                             	    
                                             	 <label class="control-label">Subject Specialization </label>  
                                             	 <input class="form-control" type="text" name="qualification.subjectSpecilization" id="specialization" readonly>                                          
                                             </div>                                             
                                              <div class="form-group">
                                       	     	 <label class="control-label">Referred By </label>  
                                             	 <input class="form-control" type="text" name="referredBy" id="referredBy" readonly>                                          
                                             </div>
                                         </div>
                                         <div class="col-md-3">                                      			 	
											<table class='table table-bordered table-striped mb-none dataTable no-footer' id="dataTable">
												<tr>						
													<th>Subject<span class="required" aria-required="true">*</span></th>		
													<th>Marks<span class="required" aria-required="true">*</span></th>
													<th><input type="button" class="addbtn" value="Add" onclick="if(validate()) addRow('dataTable')" ></th>
												</tr>
											    <tr>				           
										           <td>
										           		<input type="text" name="subject" class="form-control" />
										           	</td>
													<td>
														<input type="text" name="subMarks" class="form-control" />
													</td>
													<td>
														<img name="chk" src="/cedugenie/images/minus_icon.png" onclick="deleteRow(this);" />
													</td>							
											     </tr>
										   	</table>						
                                         </div>                               
                                         
                                        <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="control-label">Comment</label>
                                                	<textarea id="strComment" name="comment" rows="4" data-plugin-maxlength="" maxlength="140" class="form-control"></textarea>
													<input type="hidden" name="control" value="SubmitInterviewResult">
                                                </div>
                                                <div>
                                                	<input type="radio" id="strStatus" name="status" class="css-checkbox" value="SELECTED" />
													<label for="strStatus" class="css-label">SELECTED</label>
													
													<input type="radio" id="strStatus1" name="status" class="css-checkbox" value="NOTSELECTED" />
													<label for="strStatus1" class="css-label">NOT SELECTED</label>		
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
		<%-- </c:otherwise>
		</c:choose> --%>
		</div>
		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src= "/cedugenie/js/erp/teacherInterviewResult.js" type="text/javascript"></script>
</body>
</html>