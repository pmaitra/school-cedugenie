<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Staff Retirement</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src= "/icam/js/erp/staffRetirement.js" ></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
	<header class= "page-header">		<!-- Added by Saif 29-03-2018 -->
		<h2>Staff Retirement</h2>
	</header>
		<div class = "content-padding">
			<c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Employee Successfully Retired</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alret alert-danger" >
					<strong>Employee Retirement Failed</strong>	
				</div>
			</c:if>		
			</c:if>
			<div class="row">
				<div class="col-md-12">
				  	<form:form method="POST" action="submitStaffRetirement.html">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>

								<h2 class="panel-title">Create Staff Retirement</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label">User Id</label>
                                                  <select class="form-control" name="resource.userId" id="staffUserId" required>
                                                      <option value="">Select...</option>
                                                      <c:forEach var="staffUserId" items="${resourceStaffUserIdList}">
													<c:if test="${fn:length(fn:trim(staffUserId.userId)) != 0}">										
														<option value="<c:out value="${staffUserId.userId}"/>"><c:out value="${staffUserId.userId}"/></option>
													</c:if>
												</c:forEach>
                                                  </select>
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Code</label>
                                                  <input type="text" class="form-control" id="staffCode" name="staffCode" readonly="readonly" placeholder="">
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Name</label>
                                                  <input type="text" class="form-control" id="staffName" name="staffName" readonly="readonly" placeholder="">
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Employee Type</label>
                                                  <input type="text" class="form-control" id="employeeType" name="employeeType.employeeTypeName" readonly="readonly" placeholder="">
                                              </div>

                                          </div>
                                          <div class="col-md-6">  
                                              <div class="form-group">
                                                  <label class="control-label">Designation</label>
                                                  <input type="text" class="form-control" id="designation" name="designation.designationName" readonly="readonly" placeholder="">
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Job Type</label>
                                                  <input type="text" class="form-control" id="jobType" name="jobType.jobTypeName" readonly="readonly" placeholder="">
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Date Of Join</label>
                                                  <input type="text" class="form-control" id="doj" name="dateOfJoining" readonly="readonly" placeholder="">
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Date Of Retirement</label>
                                                  <input type="text" class="form-control" id="dor" name="dateOfRetirement" readonly="readonly" placeholder="">
                                              </div>
                                          </div>    
                                      </div> 
                                      <hr>
                                      <div class="row">
                                          <div class="col-md-6">
                                              <div class="form-group">
                                                  <label class="control-label">Mode Of Retirement <span class="required" aria-required="true">*</span></label>
                                                  <select class="form-control" name="modeOfRetirement" id="modeOfRetirement">
                                                      <option value="">Select...</option>
                                                      <option value="RETIRE">RETIRE</option>
												<option value="RESIGN">RESIGN</option>
                                                  </select>
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Actual Date Of Retirement <span class="required" aria-required="true">*</span></label>
                                                  <div class="input-group">
                                                      <span class="input-group-addon">
                                                          <i class="fa fa-calendar"></i>
                                                      </span>
                                                      <input type="text" class="form-control" data-plugin-datepicker="" name="actualDateOfRetirement" id=actualDateOfRetirement readonly="readonly">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="control-label">Reason <span class="required" aria-required="true">*</span></label>
                                                  <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="reasonOfRetirement" id="reasonOfRetirement"></textarea>
                                              </div>
                                          </div>
                                      </div>
							</div>
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" type="submit" id="submitButton" onclick="return staffRetirementValidation();">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
                        </form:form>
				</div>						
			</div>  
		</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html> 