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
<meta name="description" content="Page to Edit Ticket" />
<meta name="keywords" content="Edit Ticket" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Ticket</title>

<link rel="stylesheet" href="/icam/css/ticketing/editTicket.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/ticketing/editTicket.js"></script>

<script>
function editable()
{
	document.getElementById("status").removeAttribute("disabled");
	document.getElementById("fileData").removeAttribute("disabled");
	document.getElementById("description").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
	//document.getElementById("affectedUser").removeAttribute("disabled");
	//document.getElementById("reportedBy").removeAttribute("readonly");
	//document.getElementById("ticketServiceName").removeAttribute("disabled");
}
function outwardEditable(){
	document.getElementById("fileData").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
}
function outwardEditableOnSubmit(){
	document.getElementById("status").removeAttribute("disabled");
	document.getElementById("affectedUser").removeAttribute("disabled");
}
</script>

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
	
	
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Ticket
	</h1>
</div>
<c:choose>
	<c:when test="${ticket eq null}">	
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Ticket Found to Edit</span>	
			</div>		
	</c:when>
<c:otherwise>
<form:form modelAttribute="FORM" method="POST" id="updateTicket" name="updateTicket" action="updateTicket.html" enctype="multipart/form-data">
	<table id="hostelDetails" class="midsec" >
		<tr>
			<th>Ticket Code :: </th>			
			<td><input type="text" name="ticketCode" id="ticketCode" value="${ticket.ticketCode}" readonly="readonly"   class="textfield1"></td>
		</tr>
				<tr>
			<th>Service Type :: </th>			
			<td>
				<input type="text" name="ticketService.ticketServiceName" id="ticketServiceName" value="${ticket.ticketService.ticketServiceName}" readonly="readonly"   class="textfield1">
			</td>
		</tr>
		<c:if test="${resourceType ne 'STUDENT'}">
		<tr>
			<th>Affected End User :: </th>			
			<td>
				<input type="text" name="affectedUser" id="affectedUser" value="${ticket.affectedUser}" readonly="readonly"  class="textfield1">
			</td>
		</tr>
		</c:if>
		<tr>
			<th>Reported By :: </th>			
			<td><input type="text" name="reportedBy" id="reportedBy" value="${ticket.reportedBy}" readonly="readonly"  class="textfield1"></td>
		</tr>
		<tr>
			<th>Status :: </th>						
			<td>
			<c:if test="${ticketStatusList != null}">
				<select name="status" id="status" disabled="disabled" class="defaultselect">
					<c:forEach var="ticketStatus" items="${ticketStatusList}" >
						<c:if test="${ticketStatus.ticketStatusCode eq ticket.status}">
							<option value="${ticketStatus.ticketStatusCode}" >${ticketStatus.ticketStatusName}</option>
						</c:if>
					</c:forEach>
					
					 <c:forEach var="ticketStatus" items="${ticketStatusList}" >
						<c:if test="${ticketStatus.ticketStatusCode ne ticket.status}">
							<option value="${ticketStatus.ticketStatusCode}">${ticketStatus.ticketStatusName}</option>
						</c:if>
					</c:forEach>
				</select>
			</c:if>
			</td>
		</tr>
			<tr>
			<th>Ticket Summary :: </th>			
			<td>
				<input type="text" name="ticketSummary" id="ticketSummary" value="${ticket.ticketSummary}" readonly="readonly" class="textfield1"/>
			</td>			
		</tr>
		<tr>
			<th>Description :: </th>			
			<td>
				<textarea name="description"  id="description"  readonly="readonly" class="txtarea" cols="50" rows="10" class="txtarea">${ticket.description}
				</textarea>					
			</td>
		</tr>
		<tr>	
			<th>
					Upload Related Document
			</th>					
			<td><input type="file" name="uploadFile.ticketingRelatedFile" disabled="disabled" id="fileData"/><img class="addFileClassName" src="/icam/images/plus_icon.png" /></td>
		</tr>
		<tr>
		<th>Attached Documents :: </th>
			
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${ticket.attachmentList eq null || ticket.attachmentList.size()==0}">												
							<tr><th>No Attachment Found</th></tr>													
						</c:when>
					<c:otherwise>
						<tr><th>Download Attachments</th></tr>
						<c:if test="${ticket.attachmentList != null}">
							<c:forEach var="attachment" items="${ticket.attachmentList}" >
								<tr>								
									<td><a href="downloadTicketRelatedAttachments.html?ticketNumber=<c:out value="${ticket.ticketCode}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>&affectedUser=<c:out value="${ticket.affectedUser}"></c:out>">${attachment.attachmentName}</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</c:otherwise>
					</c:choose>						
				</table>
			</td>
		</tr>
		<tr>
			<th>Previous Comment :: </th>
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${ticket.commentList eq null || ticket.commentList.size()==0}">
							<tr><td>No Comment Given Yet</td></tr>
						</c:when>
					<c:otherwise>
						<tr><th>Date</th><th>Comment By</th><th>Comments</th></tr>
						<c:if test="${ticket.commentList != null}">
							<c:forEach var="commentList" items="${ticket.commentList}" >
								<tr>
									<td>${commentList.ticketCommentDate}</td>
									<td>${commentList.updatedBy}</td>
									<td><textarea class="txtarea" readonly="readonly">${commentList.comment}</textarea></td>
								</tr>
							</c:forEach>
						</c:if>
					</c:otherwise>
					</c:choose>						
				</table>
			</td>
		</tr>		
		<tr>
			<th>
				Add New Comment :: 
			</th>
			<td>
				<textarea name="comment" id="comment" class="txtarea" readonly="readonly"></textarea>
			</td>
		</tr>		
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>	
		<c:choose>
			<c:when test="${sessionScope.sessionObject.userId ne ticket.reportedBy}">
				<input type="button" id="edit" class="editbtn" value="Edit" onclick="editable();" >		
				<input type="submit" name="update" id="updateDB" class="submitbtn" value="Update" disabled="disabled">
			</c:when>
			<c:otherwise>
				<input type="button" id="edit" class="editbtn" value="Edit" onclick="outwardEditable();" >		
				<input type="submit" name="update" id="updateDB" class="submitbtn" value="Update" onclick="return outwardEditableOnSubmit();" disabled="disabled">
			</c:otherwise>
		</c:choose>	
		
	</div>
</form:form>
</c:otherwise>
</c:choose>
</body>
</html>