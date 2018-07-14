<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<header class= "page-header">		<!-- Added by Saif 29-03-2018 -->
		<h2>Manual Leave Update</h2>
	</header>
	
		<div class = "content-padding">
			<c:if test="${updateResponse ne null}">				
				<c:if test="${updateResponse eq 'Success'}">
					<div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Manual Leave Updated</span>	
					</div>
				</c:if>
				<c:if test="${updateResponse eq 'Fail'}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg" style="visibility:visible;">Manual Leave Update Fail</span>	
					</div>
				</c:if>		
			</c:if>

					<div class="row">
						<div class="col-md-12">
						  	<form action="updateManualLeaveResponse.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Manual Leave Update</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">User Id <span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="userId" id="staffUserId">
                                                    <option value="">Select...</option>
                                                    <c:forEach var="staffUserId" items="${resourceStaffUserIdList}">
														<c:if test="${fn:length(fn:trim(staffUserId.userId)) != 0}">										
															<option value="<c:out value="${staffUserId.userId}"/>"><c:out value="${staffUserId.userId}"/></option>
														</c:if>
													</c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Title</label>
                                                <input type="text" class="form-control" name="title" id="title" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Leave Start Date</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" data-plugin-datepicker="" class="form-control" name="startDate" id="startDate" onblur ="showRequestLeaveCount()">
                                                </div>
                                            </div>                                           
                                            <div class="form-group">
                                                <label class="control-label">Leave End Date</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" data-plugin-datepicker="" class="form-control" name="endDate" id="endDate" onblur ="showRequestLeaveCount()">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">  
                                            <div class="form-group">
                                                <label class="control-label">Total Leave Taken</label>
                                                <input type="text" class="form-control" name="totalRequestedLeave" id="totalRequestedLeave" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Description Provided </label>
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="140" name="desc" id="desc"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Remarks</label>
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="140" name="remarks" id="remarks"></textarea>
                                            </div>
                                        </div>
									</div>
									<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none" id="availableLeaveDetailsTab" style="visibility: collapse;">
										<tr><th colspan="7" style="text-align: center;">Leave Details</th></tr>
										<tr>
											<th>Leave Type</th>
											<th>Encashable</th>
											<th>Alloted Leave</th>
											<!-- <th>Duration</th> -->
											<th>Available Leave</th>
											<th>Assign For(days)</th>
											<th>Revised Leave</th>
										</tr>
									</table>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" onclick="return validateManualLeaveDetails();">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
		</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/erp/manualLeaveUpdate.js"></script>
<script type="text/javascript" src="/icam/js/erp/leaveRequest.js"></script>
</body>
</html>