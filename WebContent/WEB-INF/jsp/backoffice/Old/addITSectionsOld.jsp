<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add I.T Sections" />
<meta name="keywords" content="Add I.T Sections" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add I.T Sections</title>
<link rel="stylesheet" href="/sms/css/backoffice/createAcademicYear.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script>
function editable(){
	document.getElementById('clearButton').removeAttribute('disabled');
	document.getElementById('submitButton').removeAttribute('disabled');

	var itSectionCode=document.getElementsByName("itSectionCode");
	for(var i=0;i<itSectionCode.length;i++){
			if(itSectionCode[i].checked==true){
				var code=itSectionCode[i].id;
				document.getElementById('itSectionName'+code).removeAttribute("disabled");
				document.getElementById('itSectionDesc'+code).removeAttribute("disabled");
				}
		}	
};

var fyID;
function validateEdit(){
	var itSectionCode=document.getElementsByName("itSectionCode");	
	
	for(var i=0;i<itSectionCode.length;i++){
			if(itSectionCode[i].checked==true){
				fyID=itSectionCode[i].id;				
				}
		}
	
	var itSectionName = document.getElementById("itSectionName"+fyID).value;
	if(itSectionName == ""){
		document.getElementById("warningbox1").style.visibility = 'visible';
		document.getElementById("warningmsg1").innerHTML =  "Please Provide I.T Section Name";
		return false;
	}
	
	
}


function validateFrom(){
	var itSectionName=document.getElementById("itSectionName").value;	
		
	if(itSectionName == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "I.T Section Name Cannot Be Empty.";
		return false;
	}
	
	
	var itSectionNameClass=document.getElementsByClassName("itSectionName");
	var status= false;
	for(var varf=0;varf<itSectionNameClass.length;varf++){
		if(itSectionNameClass[varf].value==itSectionName){
			status= true;
			}
		}
	if(status){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "I.T Section Name Already Exists";
		return false;
		}
}
</script>
</head>	
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add I.T Sections
	</h1>
</div>

<form:form method="POST" action="createITSections.html">	
		<table  cellspacing="0" cellpadding="0" class="midsec" >
			<tr>
				<th>Add I.T Section<img class="required" src="/sms/images/required.gif" alt="Required"></th>				
				<td>
					<input class="textfield2" name="itSectionName" id="itSectionName" />
				</td>
			</tr>
			<tr>
				<th>Add I.T Section Description</th>				
				<td>
					<textarea wrap="hard"class="textarea" name="itSectionDesc" id="itSectionDesc" cols="35" ></textarea>
				</td>
			</tr>			
		</table>
		<input type="reset" value="Clear" class="clearbtn">		
		<input type="Submit" name="submit" class="submitbtn" value="submit" onclick="return validateFrom();" />
		<br/>		
		<div class="warningbox" id="warningbox">
			<span id="warningmsg"></span>	
		</div>
		<br/>
		<c:if test="${failuremsg ne null}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">${failuremsg}</span>	
			</div>					
		</c:if>		
		<c:if test="${successmsg ne null}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg">${successmsg}</span>	
			</div>					
		</c:if>		 
</form:form>
<br/>
<c:choose>
		<c:when test="${itSectionList eq null || fn:length(itSectionList) lt 1 }">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No I.T Sections Added Yet.</span>	
			</div>					
		</c:when>
<c:otherwise>
<form:form method="POST" action="updateITSection.html">
		<table  cellspacing="0" cellpadding="0" class="midsec1" >
			<tr>
				<th>Select</th>
				<th>I.T Section Name</th>
				<th>I.T Section Description</th>				
			</tr>
			<c:forEach var="ITSection" items="${itSectionList}" varStatus="indexVal" >
				<tr>
					<td><input type="radio" name="itSectionCode" id="${indexVal.index}" class="itSectionCode" value="${ITSection.itSectionCode}"></td>
					<td>
						<input type="text" class="itSectionName" id="itSectionName${indexVal.index}" name="itSectionName" value="${ITSection.itSectionName}" disabled="disabled" />
					</td>
					<td>
						<textarea wrap="hard" class="textarea" name="itSectionDesc" id="itSectionDesc${indexVal.index}"  disabled="disabled" cols="35">
							<c:out value="${ITSection.itSectionDesc}" />
						</textarea>
					</td>					
				</tr>
			</c:forEach>
		</table>	
		
		<div class="warningbox" id="warningbox1">
			<span id="warningmsg1"></span>	
		</div>
		<br/>
		<input type="reset" value="Clear" class="clearbtn" id="clearButton" disabled="disabled">		
		<input type="Submit" name="submit" class="submitbtn" value="submit" id="submitButton" disabled="disabled" onclick="return validateEdit();" />
		<input type="button" name="edit" class="editbtn" value="Edit" onclick="editable();"/>
		
</form:form>

</c:otherwise>
</c:choose>
</body>
</html>