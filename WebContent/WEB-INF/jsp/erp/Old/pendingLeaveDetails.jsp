<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Leave Request</title>
<link rel="stylesheet" href="/icam/css/erp/pendingLeaveDetails.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/erp/pendingLeaveDetails.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Pending Leave Details
		</h1>
</div>
  <form action="leaveResponse.html" method="post">
  <input type="hidden" name="leaveId" value="${taskDetails.taskId}">
		<table cellspacing="0" cellpadding="0" class="midsec">			
			<tr>
		      <th>Request ID</th>
		      <td><input type="text" class="textfield2" name="taskCode" id="taskCode" value="${taskDetails.taskCode}" readonly="readonly" /></td>
		      <th>Applied On</th>
		      <td><input type="text" class="textfield2" name="appliedDate" id="appliedDate" value="${taskDetails.activationTime}" readonly="readonly" /></td>
		    </tr>
		    <tr>
		      <th>User Id</th>
		      <td><input type="text" class="textfield2" name="userId" id="userId" value ="${taskDetails.createdById}" readonly="readonly" /></td>
			  <th>User Name</th>
		      <td><input type="text" class="textfield2" name="username" id="username" value ="${taskDetails.taskOwnerName}" readonly="readonly" /></td>
		    </tr>		    
		  	<tr>
		      <th>Title</th>
		      <td><input type="text" class="textfield2" name="title" id="title" value="${taskDetails.taskName}" readonly="readonly" /></td>
		      <th>Requested Leave Type</th>
		      <td><input type="text" class="textfield2" name="leaveType" id="leaveType" value="${taskDetails.leave.leaveType}" readonly="readonly" /></td>
		   </tr>		    
		   <tr>
		      <th>Leave Start Date</th>
		      <td><input type="text" class="textfield2" name="startDate" id="startDate" value="${taskDetails.leave.startDate}" readonly="readonly" /></td>
		      <th>Leave End Date</th>
		      <td><input type="text" class="textfield2" name="endDate" id="end" value="${taskDetails.leave.endDate}" readonly="readonly" /></td>
		   </tr>
		   <tr>
		      <th>Total Leave Requested</th>
		      <td><input type="text" class="textfield2" name="totalRequestedLeave" id="totalRequestedLeave" value="${taskDetails.leave.totalRequestedLeave}" readonly="readonly" /></td>
		      </tr>
		    <tr>
		      <th>Description Provided</th>
		      <td colspan="3"><textarea name="desc" id="desc" class="txtarea" readonly="readonly" rows="1" cols="70">${taskDetails.leave.desc}</textarea></td>
		    </tr>				
		    <tr>
		      <th>Remarks</th>
		      <td colspan="3"><textarea name="remarks" id="remarks" class="txtarea" rows="1" cols="70"></textarea></td>
		    </tr>
		    <c:if test="${allFiles ne null}">
		    	<tr>
		    		<th>Attached Files</th>
		    		<td>
			    		<c:forEach var="leaves" items="${allFiles}">
			    			<a href="downloadFile.html?fullPath=${leaves.allFilesPath}"><c:out value="${leaves.fileName}"/></a><br>
			    		</c:forEach>
			    	</td>
		    	</tr>
		    </c:if>
		    
		    <c:if test="${taskDetails.attachmentList ne null}">
		    <th>Attached Documents :: </th>
		    	<td>
		    	<table class="midsec">
		    	<tr><th>Download Attachments</th></tr>		    						
					<c:forEach var="attachment" items="${taskDetails.attachmentList}" >
						<tr>								
							<td><a href="downloadTaskDetailsAttachments.html?leaveRequestNumber=<c:out value="${taskDetails.taskCode}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>">${attachment.attachmentName}</a></td>
						</tr>
					</c:forEach>
					</table>
				</td>				
		    </c:if>	    
		    
			<tr>
				<th>Decision</th>
				<td>
					<select id="decision" name="decision" class="defaultselect" onchange="resetAssignLeaveToZero(this);">
						<option value="">Select...</option>
						<option value="SCHEDULED">ACCEPTED</option>
						<option value="REJECTED">REJECTED</option>
<%-- 						<c:if test="${allFiles eq null  || allFiles.size()== 0}"> --%>
<!-- 							<option value="REQUIRED">NEED MORE INFORMATION</option> -->
<%-- 						</c:if> --%>
					</select>
				</td>
			</tr>
<!-- 			<tr> -->
<!-- 			<th>EXCESS LEAVE</th> -->
<!-- 			<td> -->
<!-- 			<input type="text" class="textfield2" name="excessLeave" id="excessLeave" value="0" style="width:50px;" onClick="clearExcessLeaveOnClick()"/> -->
<!-- 			</td> -->
<!-- 			</tr> -->
	  	</table>
	  	<table cellspacing="0" cellpadding="0" class="midsec1" id="availableLeaveDetailsTab">
		<tr><th colspan="7" style="text-align: center;">${taskDetails.createdById}'s Leave Details</th></tr>
		<tr>
			<th>Leave Type</th>
			<th>Encashable</th>
			<th>Alloted Leave</th>
			<th>Can Apply On Stretch</th>
			<th>Available Leave</th>
			<th>Assign For(days)</th><th>Revised Available Leave</th></tr>
		
			<c:if test= "${staffLeaveDetails ne null && staffLeaveDetails.size() ne 0}">
				<c:forEach var="leave" items="${staffLeaveDetails}" varStatus="indx">
					<tr>
						<td>
							${leave.leaveType}
							<input type="hidden" name="leaveList[${indx.index}].leaveType" value="${leave.leaveType}" readonly="readonly"/>
						</td>
						<td>${leave.encashable}</td>
						<td>${leave.totalAvailLeave}</td>									
						<td>${leave.canApplyOnStretch}</td>	
						<td>
							${leave.remainingLeave}
							<input type="hidden" name="remainingLeave" id="remainingLeave${indx.index}" value="${leave.remainingLeave}" readonly="readonly"/>
						</td>
						<td><input type="text" class="textfield1" name="leaveList[${indx.index}].totalRequestedLeave" id="applyingLeave${indx.index}" value="0" style="width:50px;" onClick="clearApplyingLeaveOnClick(${indx.index})" onblur="calculateRevisedLeave(${indx.index})"/></td>
						<td>
							<input type="text" class="textfield2" name="remainingLeave" id="revisedRemainingLeave${indx.index}" readonly="readonly" value="${leave.totalAvailLeave}" style="width:50px;"/>
							<input type="hidden" id="${indx.index}" value="${leave.totalAvailLeave}"/>
						</td>		
					</tr>						
				</c:forEach>
			</c:if>
	</table>
	  	<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
				<input type="submit" id="submit" class="submitbtn" name="submit" value="Submit" onclick="return validateForm();"/>	
		</div>
	  	  	
  </form>
</body>
</html>