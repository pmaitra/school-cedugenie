<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>Manual Leave Update</title>
<link rel="stylesheet" href="/icam/css/erp/manualLeaveUpdate.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/erp/manualLeaveUpdate.js"></script>
<script type="text/javascript" src="/icam/js/erp/leaveRequest.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Manual Leave Update
		</h1>
</div>
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
  <form action="updateManualLeaveResponse.html" method="post">
	<table cellspacing="0" cellpadding="0" class="midsec">	
		<tr>
			<th>User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="userId" id="staffUserId" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="staffUserId" items="${resourceStaffUserIdList}">
						<c:if test="${fn:length(fn:trim(staffUserId.userId)) != 0}">										
							<option value="<c:out value="${staffUserId.userId}"/>"><c:out value="${staffUserId.userId}"/></option>
						</c:if>
					</c:forEach>
				</select>
			</td>	
		</tr>		   	    
	  	<tr>
	      <th>Title</th>
	      <td><input type="text" class="textfield2" name="title" id="title" /></td>
	    </tr>		    
	   <tr>
	      <th>Leave Start Date</th>
	      <td><input type="text" class="textfield2" name="startDate" id="startDate" onblur ="showRequestLeaveCount()" /></td>
	      <th>Leave End Date</th>
	      <td><input type="text" class="textfield2" name="endDate" id="endDate" onblur ="showRequestLeaveCount()"/></td>
	   </tr>
	   	   <tr>
	      		<th>Total Leave Taken</th>
	      		<td><input type="text" class="textfield2" name="totalRequestedLeave" id="totalRequestedLeave" /></td>
	      </tr>
	    <tr>
	      <th>Description Provided</th>
	      <td colspan="3"><textarea name="desc" id="desc" class="txtarea" rows="1" cols="70"></textarea></td>
	    </tr>				
	    <tr>
	      <th>Remarks</th>
	      <td colspan="3"><textarea name="remarks" id="remarks" class="txtarea" rows="1" cols="70"></textarea></td>
	    </tr>		
  	</table>	  	
	  	
	<table cellspacing="0" cellpadding="0" class="midsec1" id="availableLeaveDetailsTab" style="visibility: collapse;">
		<tr><th colspan="7" style="text-align: center;">Leave Details</th></tr>
		<tr>
			<th>Leave Type</th>
			<th>Encashable</th>
			<th>Alloted Leave</th>
			<th>Duration</th>
			<th>Available Leave</th>
			<th>Assign For(days)</th>
			<th>Revised Leave</th>
		</tr>
	</table>
	  	<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
				<input type="submit" id="submit" class="submitbtn" name="submit" value="Submit" onclick="return validateManualLeaveDetails();"/>	
		</div>	  	  	
  </form>
</body>
</html>