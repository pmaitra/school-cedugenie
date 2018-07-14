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
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>

		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Leave Structure Successfully Updated</strong>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				
				<div class="alert alert-danger">
					<strong>Leave Structure Updation Failed</strong>	
				</div>
			</c:if>		
		</c:if>	
				
		<c:choose>	
			<c:when test="${AcademicYear eq null || empty AcademicYear || leavetypes eq null || empty leavetypes}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${AcademicYear eq null || empty AcademicYear}">
							<span id="errormsg">Academic Year is not assigned</span>	
						</c:if>
						<c:if test="${leavetypes eq null || empty leavetypes}">
							<span id="errormsg">Leave Types not found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
		<c:otherwise>
	
		<div class="row">
		<form name="form1" id = "leaveStructureForm "action="listLeaveStructure.html" method="POST">
			<div class="col-md-3">
				<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Assign Leave Structure</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                            <div class="form-group">
                                 <label class="control-label">Academic Year</label>
                                 <select class="form-control" id ="year" name="academicYear.academicYearName">
                                     <option value="">Select...</option>
	                                     <c:forEach var="year" items="${AcademicYear}">
											<option value="<c:out value="${year.academicYearName}"></c:out>"><c:out value="${year.academicYearName}"></c:out></option>
										 </c:forEach>
                                 </select>
                             </div> 
                             <div class="form-group">
                                 <label class="control-label">Employee Type</label>
                                 <select class="form-control" id ="employeeTypeName" name="employeeType.employeeTypeName">
                                     <option value="">Select...</option>
                                     <%-- <c:forEach var="etl" items="${employeeTypeList}">
										<option value="<c:out value="${etl.employeeTypeCode}"></c:out>">
											<c:out value="${etl.employeeTypeName}"></c:out>
										</option>
									</c:forEach> --%>
									<c:forEach var="resourceType" items="${resourceTypes}" >
										<option VALUE="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
									</c:forEach>
                                 </select>
                             </div> 
                             <div class="form-group">
                                 <label class="control-label">Job Type</label>
                                 <select class="form-control" id ="jobTypeName" name="jobType.jobTypeName">
                                     <option value="">Select...</option>
                                     <c:forEach var="jt" items="${jobTypeList}">
										<option value="<c:out value="${jt.jobTypeCode}"></c:out>">
											<c:out value="${jt.jobTypeName}"></c:out>
										</option>
									</c:forEach>
                                 </select>
                             </div>                 
						</div>
					</section>
                </div>
			<div class="col-md-9" style="visibility: collapse;" id="details">
                        
                         <section class="panel">
                             <header class="panel-heading">
                                 <div class="panel-actions">
                                     <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                 </div>

                                 <h2 class="panel-title">View Leave Structure</h2>
                             </header>
                             <div class="panel-body">

                                 <table class="table table-bordered table-striped mb-none"  >
                                     <thead>
                                         <tr>
                                             <th>Leave Type</th>
                                             <th>Duration(Days)</th>
                                             <!-- <th>Limitation</th> -->
                                             <th>Encashment</th>
                                             <th>Carry Forward</th>
	                                     </tr>
                                     </thead>
                                     <tbody>   
                                     	<c:forEach var="types" items="${leavetypes}">                                         
                                         <tr>                                                
                                             <td>
                                             	<input type="text" class="form-control" id="leaveType" name="leaveType" value="${types.leaveCategoryName}" readonly="readonly">
                                           		<input type= "hidden" name= "oldLeaveType" id= "oldLeaveType">	<!-- added by Saif 20-03-2018 -->
                                            </td>
                                             <td>
                                             	<input type="text" class="form-control textfield2" id="${types.leaveCategoryName}leaveDuration" required name="leaveDuration" placeholder="0" onfocus="removeZero(this);" onblur="setZero(this);">
                                            	<input type= "hidden" name= "oldLeaveDuration" id= "oldLeaveDuration">	<!-- added by Saif 20-03-2018 -->
                                            </td>
                                           <%--   <td>
                                             	<input type="text" class="form-control textfield2" id="${types.leaveCategoryName}leaveLimit" required name="leaveLimit" placeholder="0" onfocus="removeZero(this);" onblur="setZero(this);">
                                            </td> --%>
                                            <td>
												<select id="${types.leaveCategoryName}leaveEncashment" name="leaveEncashment" class="form-control defaultselect1">
													<option value="false">Not Allowed</option>
													<option value="true">Allowed</option>
												</select>
												<input type= "hidden" name= "oldLeaveEncashment" id= "oldLeaveEncashment">	<!-- added by Saif 20-03-2018 -->
											</td>
											<td>
												<select id="${types.leaveCategoryName}leaveCarryForward" name="leaveCarryForward" class="form-control defaultselect1">
													<option value="false">No</option>
													<option value="true">Yes</option>
												</select>
												<input type= "hidden" name= "oldLeaveCarryForward" id= "oldLeaveCarryForward">
											</td>
                                         </tr>
                                         </c:forEach>
                                     </tbody>
                                 </table>
                             </div>
                             <footer style="display: block;" class="panel-footer">
                                 <button class="btn btn-primary" type="submit" id="submit" name="submit" onclick="return validate();">Submit </button>
                                 <button type="reset" class="btn btn-default">Reset</button>
                             </footer>
                             <input type="hidden" id="status" name="status" value="INSERT">
                         </section>
					</div>                   
                </form>
			</div>	
		</c:otherwise>
		</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/leaveStructure.js"></script>
</body>
</html>