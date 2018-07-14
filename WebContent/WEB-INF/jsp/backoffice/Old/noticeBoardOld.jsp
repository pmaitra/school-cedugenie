<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
     <%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Event Type" />
<meta name="keywords" content="Event Type" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>
<c:if test="${noticeBoard eq null}">
		Create Notice
	</c:if>
	<c:if test="${noticeBoard ne null}">
		Update Notice
	</c:if>
</title>
<link rel="stylesheet" type="text/css" href="/icam/css/backoffice/eventType.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;
		<c:if test="${noticeBoard eq null}">
			Create Notice
		</c:if>
		<c:if test="${noticeBoard ne null}">
			Update Notice
		</c:if>
		</h1>
</div>	

<c:if test="${ErrorMessage ne null}">
	<div class="errorbox" id="errorbox" style="visibility: visible;">
		${ErrorMessage}
	</div>
</c:if>
<c:if test="${SuccessMessage ne null}">
	<div class="successbox" id="successbox" style="visibility: visible;">
		${SuccessMessage}
	</div>
</c:if>

<form:form id="create" name="create" action="createNotice.html" method="POST">		
<table id="tabdata" cellspacing="0" cellpadding="0" class="midsec">	
	<tr>
		<th>Notice Name :: </th>
		<td>
			<input type="hidden" class="noticeCode" name="noticeCode" value="${noticeBoard.noticeCode}" />
			<input id ="noticeName" class="noticeName" name="noticeName" value="${noticeBoard.noticeName}"/>
			<select id="notificationType" name="notificationType" class="defaultselect">
				<option value="">Please Select</option>
				<option>Notification</option>
				<option>Alert</option>
				<option>Both</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Notice Description :: </th>
		<td>
		<textarea rows="7" cols="98"  class="txtarea" id ="noticeDesc" class="noticeDesc" name="noticeDesc" >${noticeBoard.noticeDesc}</textarea>
		</td>
	</tr>
	</table>
	<c:if test="${noticeBoard eq null}">
		<button class="submitbtn" type="submit" id="submit" name="submit">Submit</button>
	</c:if>
	<c:if test="${noticeBoard ne null}">
		<button class="submitbtn" type="submit" id="update" name="update">Update</button>
	</c:if>
	
	<button class="clearbtn" id="clear" type="reset">Reset</button>
	
</form:form>
<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
</div>		
</body>
</html>