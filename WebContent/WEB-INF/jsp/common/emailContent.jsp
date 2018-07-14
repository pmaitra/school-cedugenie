<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- ADDED JSTL TAG LIBRARY -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Schedule New Admission Form" />
<meta name="keywords" content="Schedule New Admission Form" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Email</title>

<link rel="stylesheet" href="/cedugenie/css/common/emailContent.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="all" href="/cedugenie/Cal/default.css" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src= "/cedugenie/Cal/zebra_datepicker.js" type="text/javascript"></script>

<script src= "/cedugenie/js/common/selectall.js" type="text/javascript"></script>
<script>

function getReadyForReply(){
	document.getElementById("emailDetailsDesc").removeAttribute('readonly');
	document.getElementById("emailDetailsSubject").value='Re: '+document.getElementById("emailDetailsSubject").value;
	var sender = document.getElementById("emailDetailsSender").value;
	var receiver = document.getElementById("emailDetailsReceiver").value;
	document.getElementById("emailDetailsSender").value=receiver;
	document.getElementById("emailDetailsReceiver").value=sender;
	document.getElementById("reply").style.visibility="collapse";
	document.getElementById("send").style.visibility="visible"; 
}


</script>
</head>
<body>
	<div class="ttlarea">	
			<h1>
				<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Email Details
			</h1>
		</div>
<c:choose>
	<c:when test="${emailDetails == null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;" >
			<span id="errormsg">Email details not found</span>	
		</div>
	</c:when>
	<c:otherwise>
		<form:form method="POST" id="emailDetails" name="emailDetails" action="replyAndDeletEmail.html">
			
					<table id="naftable" cellspacing="0" cellpadding="0" class="midsec">
						<tr>
							<th><a href="getEmailDetails.html">Inbox</a>(${emailDetails.noOfMail})</th>
							<th><a href="getSentEmailDetails.html">Sent</a></th>
							<th colspan="2"></th>
						</tr>
						<tr>
							<th>Subject</th>
							<td>
						
								<input type="text" class="textfield1" name="emailDetailsSubject" id="emailDetailsSubject" value="${emailDetails.emailDetailsSubject}">
								<input type="hidden" readonly="readonly" name="emailDetailsCode" value="${emailDetails.emailDetailsCode}">
							</td>		
						</tr>
						<tr>
							<th>From</th>
							<td>
								<input type="text" class="textfield1" readonly="readonly" id="emailDetailsSender" name="emailDetailsSender" value="${emailDetails.emailDetailsSender}">
							</td>	
						</tr>
						<tr>
							<th>To</th>
							<td>
								<input type="text" class="textfield1" readonly="readonly" id="emailDetailsReceiver" name="emailDetailsReceiver" value="${emailDetails.emailDetailsReceiver}">
							</td>
						</tr>	
						<tr>
							<th></th><td><textarea class ="classtextarea" id="emailDetailsDesc" name="emailDetailsDesc" rows="15" cols="100" readonly>${emailDetails.emailDetailsDesc}</textarea></td>		
						</tr>
						<tr>
							<th></th>
							<th>
							<c:if test="${emailItem eq 'Inbox'}">	
								<input type="submit" class="clearbtn" name="send" id="send" value="Send" style="visibility: collapse">
								<input type="submit" class="submitbtn" name="delete" id="delete" value="Delete">
								<input type="button" class="editbtn" name="reply" id="reply" value="Reply" onclick="getReadyForReply();">
							</c:if>
							</th>
							<th></th>	
						</tr>
					</table>
					</form:form>
				</c:otherwise>
			</c:choose>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>				
			</div>

</body>
</html>
		