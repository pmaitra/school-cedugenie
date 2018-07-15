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
<meta name="description" content="Page to View Updated Ticket" />
<meta name="keywords" content="View Updated Ticket" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Updated Ticket</title>

<link rel="stylesheet" href="/cedugenie/css/ticketing/viewClosedTicket.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
<script>
function editable()
{
	document.getElementById("affectedUser").removeAttribute("disabled");
	document.getElementsByName("status").removeAttribute("disabled");
	document.getElementsByName("status").removeAttribute("disabled");
	document.getElementById("ticketServiceName").removeAttribute("disabled");
	document.getElementById("description").removeAttribute("readonly");
	document.getElementById("submit").removeAttribute("disabled");
	document.getElementById("reset").removeAttribute("disabled");
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Updated Ticket
	</h1>
</div>
<form>
	<c:choose>
		<c:when test="${ticket eq null}">			
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Updated Ticket Found</span>	
			</div>			
		</c:when>
	<c:otherwise>
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">${ticket.queryStatus}</span>	
			</div>
	<table id="ticketsDetails" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>Ticket Code :: </th>			
			<td>${ticket.ticketCode}</td>
		</tr>
		<c:if test="${resourceType ne 'STUDENT'}">
		<tr>
			<th>Affected End User :: </th>			
			<td>${ticket.affectedUser}</td>
		</tr>
		</c:if>
		<tr>
			<th>Reported By :: </th>			
			<td>${ticket.reportedBy}</td>
		</tr>
		<tr>
			<th>Status :: </th>			
			<td>${ticket.status}</td>
		</tr>
		<tr>
			<th>Service Type :: </th>			
			<td>${ticket.ticketService.ticketServiceName}</td>
		</tr>
		<tr>
			<th>Ticket Summary :: </th>			
			<td>${ticket.ticketSummary}</td>
		</tr>
		<tr>
			<th>Description :: </th>			
			<td><textarea name="description" id="description" readonly="readonly" cols="50" rows="10" class="txtarea">${ticket.description}</textarea></td>
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
			<th>Comment :: </th>
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${ticket.commentList eq null || ticket.commentList.size()==0}">
							<tr><td>No Comment Given</td></tr>
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
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
				<c:choose>
			<c:when test="${sessionScope.sessionObject.userId ne ticket.reportedBy}">
					<a href="inwardListTicket.html" target="frame"><input type="button" class="submitbtn" value="Back" /></a>
			</c:when>
			<c:otherwise>
				 <a href="listTicket.html" target="frame"><input type="button" class="submitbtn" value="Back" /></a>
			</c:otherwise>
		</c:choose>	
	</div>
</c:otherwise>
</c:choose>
</form>

</body>
</html>