<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Retirement" />
<meta name="keywords" content="View Fixation Of Pay" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Fixation Of Pay</title>
<link rel="stylesheet" href="/cedugenie/css/erp/viewFixationOfPayDetails.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/cedugenie/css/common/pagination.css">
<script src= "/cedugenie/js/common/validateSearch.js" type="text/javascript"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/showHideField.js"></script>

</head>
<script>
function validate(){
	var count=0;
	var radio=document.getElementsByTagName("input");
	for(var i=1;i<radio.length;i++){		
		if(radio[i].type=="radio"){			
			if(radio[i].checked)			
				count=1;
			
		}
	}
	if(count==0){
		document.getElementById("warningbox").style.visibility='visible';
	 	document.getElementById("warningbox").innerHTML="Please Select An Option";
		return false;
	}
	else
		return true;
}
</script>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Fixation Of Pay
		</h1>
	</div>	
	<c:choose>
		<c:when test="${salaryTemplate eq null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Fixation Of Pay Found</span>	
			</div>								
		</c:when>
	<c:otherwise>	
	
	<table cellspacing="0" cellpadding="0" class="midsec" border="1">					
		<tr>
			<th>
				Template Name
			</th>
			<td>
				${salaryTemplate.salaryTemplateName}
			</td>
		</tr>
		<tr>
			<th>
				Template Type
			</th>
			<td>
				${salaryTemplate.salaryTemplateType}
			</td>
		</tr>
		
		<tr>
			<th>
				Template Description
			</th>
			<td>
				${salaryTemplate.salaryTemplateDesc}
			</td>
		</tr>			
	</table>		
	<table id="fixList" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>			
			<th colspan="3">Fixation Of Pay On initial Appointment</th>			
		</tr>
		<c:forEach var="fix" items="${salaryTemplate.fixationOfPayList}"> 
			<tr>
		       <td colspan="3" style="color: red ">${fix.fixationOfPayName}(${fix.fixationOfPayStartRange}- ${fix.fixationOfPayEndRange})</td>
		    </tr>
		    <tr>
		       <th>Appointment To Posts <br> With Grade Pay</th>
			   <th>Pay In The Pay Band</th>	
			   <th>Total Amount</th>
			</tr>	
		    <c:forEach var="fixDetails" items="${fix.fixationOfPayDetailsList}"> 
				<tr>
			       <td align="center">${fixDetails.appointmentToPostsWithGradePay}</td>
				   <td align="center">${fixDetails.payInThePayBand}</td>	
				   <td align="center">${fixDetails.totalAmount}</td>
				</tr>
		`</c:forEach>
		</c:forEach>			
	</table>		
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<c:if test="${staffRetirementList[0].status eq 'success'}">
			<div class="successbox" id="successbox"  style="visibility:visible;">
				<span id="successmsg">Data Successfully updated</span>	
			</div>				
		</c:if>
		<c:if test="${staffRetirementList[0].status eq 'fail'}">
			<div class="errorbox" id="errorbox"  style="visibility:visible;">
				<span id="errormsg">Update failed</span>	
			</div>				
		</c:if>	
	</div>		

</c:otherwise>
</c:choose>
</body>
</html>