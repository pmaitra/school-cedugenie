<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Lpg Company</title>
<link rel="stylesheet" href="/icam/css/inventory/addLpgCompany.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/inventory/addLpgCompany.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Lpg Company
		</h1>
	</div>
	
	<c:if test="${successStatus != null}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="infomsg" style="visibility:visible;">Successfully Updated!</span>	
			</div>
		</c:if>
		<c:if test="${failStatus != null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">${failStatus}</span>	
				</div>
		</c:if>
		
	<form:form name="addLpgCompany" action="addLpgCompany.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Add New Lpg Company ::
				</th>						
			</tr>				
			<tr>
				<th>
					Lpg Company Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield" name="companyName" id="companyName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="CREATE" id="submitButton" onclick="return validateAddLpgCompanyForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
			
						
	<c:choose>
		<c:when test="${lpgCompanyList == null ||  empty lpgCompanyList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No LPG Company added yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="updateLpgCompany" action="updateLpgCompany.html" method="POST">		
					
				<table id="tableUpdateLpgCompany" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="5">View / Update LPG Company</th>
					</tr>				
					<tr>
						<th>Select</th>
						<th>LPG Company Name</th>
					</tr>
					<c:forEach var="lpgCompany" items="${lpgCompanyList}" varStatus="i">
						<tr>
							<td><input type="radio" name="lpgDetailsId" id="radioLpgCompany${i.index}" value="${lpgCompany.lpgDetailsId}" onClick="return removeDisabled('lpgDetailsId','warningbox','warningmsg');"/></td>
							<td>
								<input type="hidden"  id="oldLpgCompany${i.index}" value="${lpgCompany.companyName}" readonly="readonly"> 
								<input type="text" class="textfield" id="textLpgCompany${i.index}" name="companyName" value="${lpgCompany.companyName}" disabled="disabled"> 
							</td>	
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="5">
							<input type="submit" class="greenbtn" value="UPDATE" id="submitt" disabled="disabled" onclick="return validateUpdateLpgCompanyForm();" disabled="disabled" />
						</td>
					</tr>
				</table>										
			</form:form>
		</c:otherwise>		
	</c:choose>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>	
		<div class="infomsgbox" id="infomsgbox">
		<span id="infomsg"></span>	
	</div>			
	</div>
		
</body>
</html>