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
<link rel="stylesheet" href="/icam/css/erp/leaveRequest.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/erp/leaveRequest.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Leave Request
		</h1>
</div>
<c:choose>
			<c:when test="${staffLeaveDetails==null  || staffLeaveDetails.size()== 0}">
				<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
					<span id="infomsg">No Leave Type Present</span>
				</div>					
			</c:when>
<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr><th colspan="5" style="text-align: center;">My Leave Details</th></tr>
		<tr><th>Leave Type</th><th>Encashable</th><th>Alloted Leave</th><th>Can Apply On Stretch</th><th>Available Leave</th></tr>
		
			<c:if test= "${staffLeaveDetails ne null && staffLeaveDetails.size() ne 0}">
				<c:forEach var="leave" items="${staffLeaveDetails}">
					<tr>
						<td>${leave.leaveType}</td>
						<td>${leave.encashable}</td>
						<td>${leave.totalAvailLeave}</td>									
						<td>${leave.canApplyOnStretch}</td>	
						<td>${leave.remainingLeave}</td>		
					</tr>						
				</c:forEach>
			</c:if>
	</table>


  <form:form modelAttribute="FORM" action="leaveRequest.html" method="POST" enctype="multipart/form-data">
  <input type="hidden" name="remainingLeave" id="remainingLeave">
   <input type="hidden" id="totalRequestedLeave" name="totalRequestedLeave"/>	  	
   		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Subject<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" class="textfield2" name="title" id="title"/></td>
			</tr>		
			<tr>
				<th >Start Date<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" class="textfield2" name="startDate" id="startDate"/></td>
			</tr>    
			<tr>
				<th>End Date<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<input class="textfield2" type="text" name="endDate" id="endDate" />
					<b><span id="requestedNumberOfLeave" style="color:red;margin-left: 5%;"></span></b>
				</td>
				
			</tr>			
			<tr>
				<th>Select Leave Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
				<select id="leaveType" name="leaveType" class="defaultselect">
					<option value="">Please Select</option>
						<c:forEach var="leave" items="${staffLeaveDetails}">	
						<option value="${leave.leaveType}">${leave.leaveType}</option>
					</c:forEach>
				</select>
					<b><span id="leaveTypeName" style="color:red;margin-left: 5%;"></span></b>
				</td>
			</tr>
			<tr>
				<th>Description<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><textarea name="desc" id="desc" class="txtarea"></textarea></td>
			</tr>	
			<tr>	
			<th>
					Upload Related Document
			</th>					
			<td><input type="file" name="fileData"/></td><td><img class="addFileClassName" src="/icam/images/plus_icon.png" /></td>
		</tr>		
		</table>
		<p/>		
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
			<c:if test="${message ne null}">
				<c:if test="${message ne 'fail'}">
					<div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg">Leave Request Submitted Successfully. Your Request ID is ${message}</span>	
					</div>
				</c:if>
				<c:if test="${message eq 'fail'}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg">Leave Request Failed</span>	
					</div>
				</c:if>
			</c:if>
			<input type="submit" id="submit" name="submit" value="Submit" onclick="return showdaydiff();" class="submitbtn"/>
		</div>	 
  </form:form>
</c:otherwise>		
		</c:choose>
</body>
</html>