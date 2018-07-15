<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Social Category Wise Class Strength Report" />
<meta name="keywords" content="Social Category Wise Class Strength Report" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Social Category Wise Class Strength</title>
<link rel="stylesheet" href="/cedugenie/css/report/socialCategoryWiseClassStrength.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<link rel="stylesheet" href="/cedugenie/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/cedugenie/Cal/jsDatePick_ltr.min.css">
<script src="/cedugenie/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/cedugenie/Cal/zebra_datepicker.js"></script>
<script>
function validateForm() {
	
	if($("#standard").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Standard";
		return false;
	}
		
	if($("#standard").val()=='ALL'){
		var all_values = [];
		$('#standard option').map( function() { 
		    if (this.value.length) { 
		    	if(this.value!='ALL'){
		    		all_values.push(this.value);
		    	}		       
		    }
		});
		$("#standard").val(all_values);
	}	
	return true;
}

</script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Social Category Wise Class Strength
		</h1>
</div>

<c:if test="${message != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">${message}</span>	
		</div>
</c:if>
<form:form id="generateSocialCategoryWiseClassStrengthReport" name="generateSocialCategoryWiseClassStrengthReport" method="POST" action="generateSocialCategoryWiseClassStrengthReport.html">		
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td>Class<img class="required" src="/cedugenie/images/required.gif" alt="Required"></td>
			<td>
				<select name="standard" id="standard" class="defaultMultipleSelect1" multiple="multiple">
					<option value="">Please Select</option>
 					<c:forEach var="stndrd" items="${standardList}">
						<option value="<c:out value="${stndrd.standardName}"/>"><c:out value="${stndrd.standardName}"/></option>
 					</c:forEach> 
 					<option value="ALL">Select All</option>
				</select>
			</td>
		</tr>
	</table>
			<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" onclick="return validateForm();"/>
	
</form:form>
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>