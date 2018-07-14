<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%--  <%@ include file="/file/sessionDataForChildPages.txt"%> --%>
<c:url value="/addVendorItemPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Notifications" />
<meta name="keywords" content="View Notifications" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Notifications</title>
<link rel="stylesheet" href="/cedugenie/css/common/viewNotification.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<link rel="stylesheet" href="/cedugenie/css/common/popup.css">
</head>
<script>
window.onload = function(){
	document.getElementById('dialog').style.display = 'none';
	
};	
	$(document).ready(function() {
		$(".notificationLink").each(function(){		
			$(this).click(function(){
				var notificationId=this.id;
				$.ajax({
					url:'/cedugenie/getNotificationDetails.html',
					data:"notificationId=" + notificationId,
					dataType: 'json',
					success: function(data){
						deleteRow('dialogTab');
						var table = document.getElementById('dialogTab');
						if(data!=""){
							$('b').contents().unwrap();
							var notificationDetails=data.split('#');
							var x = table.rows[0].cells;
							x[0].innerHTML="From :: "+notificationDetails[1];
				            var rowCount = table.rows.length;	
				            var row = table.insertRow(rowCount);
				            var cell1 = row.insertCell(0);
				            cell1.innerHTML = notificationDetails[2];
				           
				            row = table.insertRow(rowCount+1);
				            cell1 = row.insertCell(0);
				            document.getElementById("replyTo").value=notificationDetails[1];
				            
				            var button=document.createElement("input");
				            button.type='submit';
				            button.value='Reply';
				            button.setAttribute("onclick","document.getElementById('replyNotification').submit();");
				            cell1.appendChild(button);
						}
						$(function() {
							 document.getElementById('dialog').style.display = 'block';
							$( "#dialog" ).dialog({
								height: 300,
								modal: false
							});
						});
					}
				});
		});
		});
	});
		
	 function deleteRow(tableID) {
         try {
	         var table = document.getElementById(tableID);
	         var rowCount = table.rows.length;
	         for(var i=2; i<rowCount; i++) {
	                 table.deleteRow(i);
	                 rowCount--;
	                 i--;
	          }
         }catch(e) {
             alert(e);
         }
     }
	 
	 
	 function removeBold(trID) {
		 var tr=document.getElementById(trID);
		 var b=tr.getElementsByTagName("b");
		 for(var i=b.length-1;i>=0;i--){
			 var cnt = $(b[i]).contents();
			 $(b[i]).replaceWith(cnt);
		 }	
	}
</script>

<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Notifications
		</h1>
</div>
<br/>
	<c:choose>
	 <c:when test="${notificationList == null  && fn:length(notificationList) lt 1}">
		  <div class="errorbox" id="errorbox"  style="visibility:visible;">
			<span id="errormsg">No notification available for you</span>	
		  </div>  				
  	</c:when> 
    <c:otherwise>		
		<table cellspacing="0" cellpadding="0" class="midsec1">			
			<tr>
				<th>From</th><th>Subject</th><th>On Date/Time</th>
			</tr>
			<c:forEach var="notif" items="${notificationList}" varStatus="i">	
			<tr id="trID${i.index}">
			<c:choose>
				<c:when test="${notif.status == 'A'}">
				
					<td>
						<b>
						<a href="#" id="${notif.notificationId}" class="notificationLink" onclick="removeBold('trID${i.index}');">
						 	<input type="hidden"  name="notificationSender" value="${notif.notificationSender}"/>
							${notif.notificationSender}
						</a>
						</b>					
					</td>
					<td>
						<b>
						<a href="#" id="${notif.notificationId}" class="notificationLink" onclick="removeBold('trID${i.index}');">
						${notif.notificationSubject}
						</a>
						</b>
					</td>
					<td>
						<b>
						<a href="#" id="${notif.notificationId}" class="notificationLink" onclick="removeBold('trID${i.index}');">
							<input type="hidden"  name="notificationId" value="${notif.notificationId}"/>
							${notif.notificationDate}
						</a>
						</b>					
					</td>
				</c:when>	
					<c:otherwise>
					<td>
						<a href="#" id="${notif.notificationId}" class="notificationLink">
							<input type="hidden"  name="notificationSender" value="${notif.notificationSender}"/>
							${notif.notificationSender}
						</a>							
					</td>
					<td>
						<a href="#" id="${notif.notificationId}" class="notificationLink" onclick="removeBold('trID${i.index}');">
						${notif.notificationSubject}
						</a>
					</td>
					<td>	
						<a href="#" id="${notif.notificationId}" class="notificationLink">
							<input type="hidden"  name="notificationId" value="${notif.notificationId}"/>
							${notif.notificationDate}
						</a>
					</td>
					</c:otherwise>
					</c:choose>
			</tr>
			</c:forEach>		
			</table>				
		
	<form:form name="replyNotification" id="replyNotification" method="GET" action="createNotification.html">
		<input type="hidden" name="replyTo" id="replyTo" />
		<div id="dialog" title="notification_details" >
			<table id="dialogTab"  cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th></th>	
			</tr>
			<tr>
				<th>Message</th>
			</tr>
			</table>
		</div>
	</form:form>		
	 </c:otherwise>
	</c:choose>		
</body>
</html>