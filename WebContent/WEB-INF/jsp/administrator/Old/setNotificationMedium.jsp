<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Setup Notification Medium" />
<meta name="keywords" content="Notification Medium Setup" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Notification Medium Setup</title>

<link rel="stylesheet" href="/icam/css/administrator/setNotificationMedium.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
function editableField(){
	var inputs = document.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type == "checkbox") {
			inputs[i].disabled = false;
		}  
	}
	document.getElementById("clearNotificationMedium").removeAttribute('disabled');
	document.getElementById("update").removeAttribute('disabled');
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Notification Medium Setup
	</h1>
</div>
	<c:choose>
		<c:when test="${notificationMediumList eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Notification Type And Notification Medium Not Available</span>	
			</div>						
		</c:when>
	<c:otherwise>
		<c:set var="notificationMediumListSize" value="0" scope="page" />	
		<form:form method="POST" id="createClassDb" name="" action="updateNotificationMediums.html">
			<table id="notificationMediumList"  cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>
						Notification Type
					</th>
					<c:if test="${notificationMediumList[0].notificationMediums ne null}">
						<c:set var="notificationMediumSize" value="${notificationMediumList[0].notificationMediums.size()}" scope="page" />
						<c:forEach var="notificationMedium" items="${notificationMediumList[0].notificationMediums}">
							<th>
								${notificationMedium.notificationMediumName}
							</th>
						</c:forEach>
					</c:if>
			  	</tr>
				<c:forEach var="notification" items="${notificationMediumList}" varStatus="count">
					<tr>
						<td>
							<input type="text" class="textfield" name="notificationLevelName" value="${notification.notificationLevel}" readonly="readonly"/>
						</td>
						<c:if test="${notificationMediumList[0].notificationMediums ne null}">
							<c:forEach var="notificationMedium" items="${notification.notificationMediums}">
								<td>
									<c:choose>
										<c:when test="${notificationMedium.status eq null}">
											<input type="checkbox"  name="${notification.notificationLevel}" id="notificationMedium" value="${notificationMedium.notificationMediumName}" disabled="disabled" />
										</c:when>
										<c:otherwise>
											<input type="checkbox"  name="${notification.notificationLevel}" id="notificationMedium" value="${notificationMedium.notificationMediumName}" checked="checked" disabled="disabled"/>
										</c:otherwise>
									</c:choose>	
								</td>
							</c:forEach>
						</c:if>	
					</tr>
				</c:forEach>
			</table>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>			
				<button class="editbtn" id="editNotificationMedium" type="button"  onclick="return editableField();" >Edit</button>
				<button class="submitbtn" type="submit" id="update" disabled="disabled">Update</button>
				<button class="clearbtn" id="clearNotificationMedium" type="reset" disabled="disabled">Undo</button>
			</div>			
		</form:form>
	</c:otherwise> 
</c:choose>
</body>
</html>