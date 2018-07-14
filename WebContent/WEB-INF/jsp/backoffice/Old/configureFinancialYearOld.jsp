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
<meta name="description" content="Page to Create FinancialYear" />
<meta name="keywords" content="Create FinancialYear" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Academic Year</title>
<link rel="stylesheet" href="/sms/css/backoffice/createAcademicYear.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sms/Cal/default.css"/>
<script src="/sms/Cal/zebra_datepicker.js"></script>
<script>
$(document).ready(function() {	  
	 $('.sessionStartDate').Zebra_DatePicker();
	 $('.sessionEndDate').Zebra_DatePicker();
	 
	 $('.sessionStartDate').Zebra_DatePicker({
	 	  format: 'd/m/Y',
	 	  pair:$('.sessionEndDate')
	 	});
	 $('.sessionEndDate').Zebra_DatePicker({
	  format: 'd/m/Y',
	  direction: 1
	});
});

function editable(){
	document.getElementById('clearButton').removeAttribute('disabled');
	document.getElementById('submitButton').removeAttribute('disabled');

	var financialYearCode=document.getElementsByName("financialYearCode");
	for(var i=0;i<financialYearCode.length;i++){
			if(financialYearCode[i].checked==true){
				var code=financialYearCode[i].id;
				document.getElementById('financialYearName'+code).removeAttribute("disabled");
				}
		}
	
};

var fyID;
function validateEdit(){
	var financialYearCode=document.getElementsByName("financialYearCode");	
	
	for(var i=0;i<financialYearCode.length;i++){
		if(financialYearCode[i].checked==true){
			fyID=financialYearCode[i].id;				
		}
	}
	var financialYearName = document.getElementById("financialYearName"+fyID).value;
	if(financialYearName == ""){
		document.getElementById("warningbox1").style.visibility = 'visible';
		document.getElementById("warningmsg1").innerHTML =  "Please Provide Financial Year Name";
		return false;
	}
	
/*	var editStatus=false;
 var financialYearClass=document.getElementsByClassName("financialYearName");	
	for(var varf=0;varf<financialYearClass.length;varf++){
		if(financialYearClass[varf].value==financialYearName){
			editStatus= true;
			}
		}
	
	if(editStatus){
		document.getElementById("warningbox1").style.visibility = 'visible';
		document.getElementById("warningmsg1").innerHTML =  "Financial Year Name Already Exists";
		return false;
		}*/
}


function validateFrom(){
	var financialYearName=document.getElementById("financialYearName").value;	
	var yearStartDate= document.getElementById("sessionStartDate").value;
	var yearEndDate= document.getElementById("sessionEndDate").value;
	
	if(yearStartDate == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "Start date can not be empty.";
		return false;
	}
	
	if(yearEndDate == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "End date can not be empty.";
		return false;
	}
	if(financialYearName == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "Please Provide Financial Year Name";
		return false;
	}
	var financialYearClass=document.getElementsByClassName("financialYearName");
	var status= false;
	for(var varf=0;varf<financialYearClass.length;varf++){
		if(financialYearClass[varf].value==financialYearName){
			status= true;
			}
		}
	if(status){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML =  "Financial Year Name Already Exists";
		return false;
		}
}
</script>
</head>	
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Configure Financial Year
	</h1>
</div>

<form:form method="POST" action="createNewFinancialYear.html">	
		<table  cellspacing="0" cellpadding="0" class="midsec" >
			<tr>
				<th>Financial Year Start Date<img class="required" src="/sms/images/required.gif" alt="Required"></th>
				<td>
					<input name="yearStartDate" id="yearStartDate" class="yearStartDate" onclick="document.getElementById('infomsgbox1').style.visibility = 'collapse';"/>
				</td>
			</tr>
			<tr>
				<th>Financial Year End Date<img class="required" src="/sms/images/required.gif" alt="Required"></th>
				<td>
					<input  name="yearEndDate" id="yearEndDate"  class="yearEndDate" onclick="document.getElementById('infomsgbox1').style.visibility = 'collapse';" />
				</td>
			</tr>
			<tr>
				<th>Financial Year Name<img class="required" src="/sms/images/required.gif" alt="Required"></th>
				<td>
					<input class="textfield2" name="financialYearName" id="financialYearName" onclick="document.getElementById('infomsgbox1').style.visibility = 'collapse';" />
				</td>
			</tr>			
		</table>
		<input type="reset" value="Clear" class="clearbtn">		
		<input type="Submit" name="submit" class="submitbtn" value="submit" onclick="return validateFrom();" />
		<br/><br/>
		<div class="infomsgbox" id="infomsgbox1" style="visibility:visible;">
				<span id="infomsg">Financial Year once created can't be deleted and edited(except name).</span>	
		</div>
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
		<c:when test="${financialYearList eq null || fn:length(financialYearList) lt 1 }">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Financial Year Created Yet.</span>	
			</div>					
		</c:when>
<c:otherwise>
<form:form method="POST" action="updateFinancialYear.html">
		<table  cellspacing="0" cellpadding="0" class="midsec1" >
			<tr>
				<th>Select</th>
				<th>Financial Year Name</th>
				<th>Financial Year Start Date</th>
				<th>Financial Year End Date</th>
				<th>Financial Year Status</th>
			</tr>
			<c:forEach var="financialYear" items="${financialYearList}" varStatus="indexVal" >
				<tr>
					<td><input type="radio" name="financialYearCode" id="${indexVal.index}" class="financialYearCode" value="${financialYear.financialYearCode}"></td>
					<td>
						<input class="financialYearName" id="financialYearName${indexVal.index}" name="financialYearName" value="${financialYear.financialYearName}" disabled="disabled" />
					</td>
					<td>
						<input class="textfield2" id=""  value="${financialYear.sessionStartDate}" readonly="readonly" />
					</td>
					<td>
						<input class="textfield2" id=""  value="${financialYear.sessionEndDate}" readonly="readonly" />
					</td>
					<td>
						<input class="textfield2"  id=""  value="${financialYear.yearStatus}" readonly="readonly" />
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