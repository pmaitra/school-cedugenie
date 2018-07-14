<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Generate Ticket" />
<meta name="keywords" content="Generate Ticket" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Generate Ticket</title>

<link rel="stylesheet" href="/icam/css/ticketing/generateTicket.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/ticketing/generateTicket.js"></script>

<script type='text/javascript'>
	CharacterCount = function(TextArea,FieldToCount){
		var myField = document.getElementById(TextArea);
		var myLabel = document.getElementById(FieldToCount);
		if(!myField || !myLabel){return false}; // catches errors
		var MaxChars =  myField.maxLengh;
		if(!MaxChars){MaxChars =  myField.getAttribute('maxlength') ; }; 	if(!MaxChars){return false};
		var remainingChars =   MaxChars - myField.value.length
		myLabel.innerHTML = remainingChars+" Characters Remaining of Maximum "+MaxChars
	}
	setInterval(function(){CharacterCount('description','CharCountLabel1')},55);
	
	function clearContents(element) {
		  element.value = '';
		}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Generate Ticket
	</h1>
</div>
<div class="btnsarea01">		
	<c:if test="${ticket != null}">			
		<c:if test="${ticket.status == 'success'}">
			<div class="successbox" id="successbox"  style="visibility:visible;">
				<span id="successmsg">Ticket Raised Successfully. Your ticket ID is - ${ticket.ticketRecId}</span>	
			</div>					
		</c:if>
		<c:if test="${ticket.status == 'fail'}">
			<div class="errorbox" id="errorbox"  style="visibility:visible;">
				<span id="errormsg">Problem Occur While Raising Ticket</span>	
			</div>					
		</c:if>
	</c:if>
</div>
<form:form modelAttribute="FORM" method="POST" id="generateTicket" name="generateTicket" action="generateTicket.html" enctype="multipart/form-data">
	<table id="ticketDetails" cellspacing="0" cellpadding="0" class="midsec" >
		<tr>
			<th>Reported By<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td><input type="text" name="reportedBy" id="reportedBy" value="${reportedBy}" readonly="readonly" class="textfield1"></td>
		</tr>
		<c:if test="${resourceType ne 'STUDENT'}">
		<tr>
			<th>Affected End User<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<select name="affectedUser" id="affectedUser" class="defaultselect">
				  <option value="">Select...</option>
					<c:if test="${userIdList != null}">
					<c:forEach var="userIdList" items="${userIdList}" >
						<option value="${userIdList.userId}">${userIdList.userId}</option>
					</c:forEach>
					</c:if>
				</select> 
			</td>
		</tr>
		</c:if>
		<tr>
			<th>Status<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td><input type="radio" name="status" id="status" checked="checked" value="OPEN" >OPEN</td>
		</tr>
		<tr>
			<th>Service Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<select name="ticketService.ticketServiceName" id="ticketServiceName" class="defaultselect">
					<option value="">Select...</option>
					<c:if test="${userIdList != null}">
					<c:forEach var="serviceType" items="${serviceTypeList}" >
						<option value="${serviceType.ticketServiceName}">${serviceType.ticketServiceName}</option>
					</c:forEach>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<th>Ticket Summary<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<input type="text" name="ticketSummary" id="ticketSummary" class="textfield1"/>
			</td>			
		</tr>
		<tr>
			<th>Description<img class="required" src="/icam/images/required.gif" alt="Required"></th>	
			<td>
				<textarea onfocus="clearContents(this);" name="description"  id="description" cols="50" rows="10" maxlength='500' class="txtarea">maxlength is set of 500 letters.
				</textarea>					
			</td>					
		</tr>
		<tr>
		<td colspan="2"><span id='CharCountLabel1' style="float: right;color: red; font-size: 14px;"></span></td>
		</tr>		
		<tr>	
			<th>
					Upload Related Document
			</th>					
			<td><input type="file" name="uploadFile.ticketingRelatedFile"/></td><td><img class="addFileClassName" src="/icam/images/plus_icon.png" /></td>
		</tr>
	</table>	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" class="submitbtn" value="SUBMIT" onclick="return validate();"/>
	</div>
</form:form>

</body>
</html>