<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Log Notification" />
<meta name="keywords" content="Logging Notification" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Logging Notification</title>

<link rel="stylesheet" href="/icam/css/administrator/loggingNotificationSetUp.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script>
function validate(){
	var chk=getElementsByClassName("chk");
	var defaultMultipleSelect1=getElementsByClassName("defaultMultipleSelect1");
	var defaultMultipleSelect2=getElementsByClassName("defaultMultipleSelect2");
	
	for(var i=0;i<chk.length;i++){
		var value1=defaultMultipleSelect1[i].value;
		var value2=defaultMultipleSelect2[i].value;
		
		if(chk[i].checked){
			if(value1 == "" && value2 == ""){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Please Select Urgent Or Normal";
				return false;
			}
		}else{
			if(value1 != "" && value2 != ""){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Please Check notification Checkbox";
				return false;
			}
		}
	}
	return true;
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Logging Notification
	</h1>
</div>
<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="infomsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
</c:if>
<form:form action="updateLoggingNotificationSetUp.html" method="POST">	
	<c:choose>
		<c:when test="${moduleForNotification eq null }">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Data Not Found</span>	
			</div>						
		</c:when>
		
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="5">
				Module Name :: ${moduleForNotification.moduleName}
				<input type="hidden" value="${moduleForNotification.moduleName}" name="moduleName">
			</th>
		</tr>
		<tr>
			<th>Functionality Name</th>
			<th>Task</th>
			<th>Activity Log</th>
			<th>Notification</th>
			<th>Notification Level</th>
		</tr>
		<c:forEach var="functionality"  items="${moduleForNotification.functionalityList}" varStatus="flag0">
		<tr>
			<th style="border-top: thick; border-top-style: double; border-top-color: black;">
				${functionality.functionalityName}
				<input type="hidden" readonly="readonly" name="functionalityList[${flag0.index}].functionalityName" size="30" value="${functionality.functionalityName}">
			</th>
			<th colspan="3" style="border-top: thick; border-top-style: double; border-top-color: black;"></th>
			<th style="border-top: thick; border-top-style: double; border-top-color: black;">
				URGENT &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		    	NORMAL
			</th>
		</tr>





		<c:forEach var="loggingAspect"  items="${functionality.loggingAspectList}" varStatus="flag1">
		<tr>
			<td></td>
			<td>
				<input type="hidden" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].methodName"  value="${loggingAspect.methodName}">
				<input type="hidden" value="${loggingAspect.task}">
				<b>${loggingAspect.task}</b>
			</td>
			<td>
				<c:choose>
    				<c:when test="${loggingAspect.activityLog.equals(true)}">
        				<input type="checkbox" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].activityLog" checked="checked">
    				</c:when>
    				<c:otherwise>
       	 				<input type="checkbox" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].activityLog" >
    				</c:otherwise>
				</c:choose>									
			</td>
			<td>
				<c:choose>
    				<c:when test="${loggingAspect.notification.equals(true)}">
        				<input type="checkbox" class="chk" checked="checked" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].notification" >
    				</c:when>
    				<c:otherwise>
       	 				<input type="checkbox" class="chk" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].notification" >
    				</c:otherwise>
				</c:choose>
			</td>
			<td>
				<select multiple name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].urgentNotificationResourceList[${flag1.index}].userId" class="defaultMultipleSelect1">
				 <c:forEach var="urgent"  items="${loggingAspect.urgentNotificationResourceList}" varStatus="flag2" >
				 	<option value="${urgent.code}" <c:if test="${urgent.status!=null}">selected="selected"</c:if> itemLabel="isicClassName">${urgent.userId}</option>
				 </c:forEach> 
				</select> &nbsp;&nbsp;
				
				<select multiple name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].normalNotificationResourceList[${flag1.index}].userId" class="defaultMultipleSelect2">
				  <c:forEach var="normal"  items="${loggingAspect.normalNotificationResourceList}" >
				 	<option value="${normal.code}" <c:if test="${normal.status!=null}">selected="selected"</c:if> itemLabel="isicClassName">${normal.userId}</option>
				 </c:forEach>
				</select> 
			</td>
		</tr>
		</c:forEach>		
		</c:forEach>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
		<input type="submit" value="Update" class="submitbtn"/>
		<input type="reset" value="Clear" class="clearbtn"/>
	</div>
	
	</c:otherwise>
</c:choose>
</form:form>
</body>
</html>