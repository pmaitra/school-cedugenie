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
<meta name="description" content="Page to Income Tax Salary Slab" />
<meta name="keywords" content="Income Tax Salary Slab" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create And Edit IT Section Details</title>
<link rel="stylesheet" href="/sms/css/erp/incomeTaxSalarySlab.css" />

<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createITSectionDetails.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create / Edit Rebates Under IT Sections
		</h1>
	</div>
	
	<c:choose>
		<c:when test="${itSectionList == null}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">No IT Section Found</span>	
 			</div>
		</c:when>	
		<c:otherwise>	
			<form:form name="createITSectionRebates" action="createITSectionRebates.html" method="POST">		
				
				<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
					<tr>
						<th>
							IT Section
						</th>
						<td>							
							<select name="itSectionCode" id="itSectionCode" class="defaultselect">
								<option value="">Select...</option>
								<c:forEach var= "itSec" items="${itSectionList}">									
									<option value="${itSec.itSectionCode}">${itSec.itSectionName}</option>					
								</c:forEach>
							</select>
						</td>			
					</tr>	
				</table>
				
				<div id="itSecDetailInputDiv" style="visibility:collapse;">
					<table id="itSecDetail" cellspacing="0" cellpadding="0" class="midsec1" border="1">
						<tr>
							<th colspan="2"></th>
						</tr>		
						<tr>
							<th>
								Rebate Name
							</th>	
							<th>
								Delete
							</th>
						</tr>
						<tr>
							<td>
								<input type="text" class="textfield1" id="itSectionDetailsName0" name="itSectionDetailsName">
							</td>
							<td>
								<input type="image" name="deleteButton" onclick="return deleteRow(this);" src="/sms/images/minus_icon.png">
							</td>	
						</tr>
						<tr>
							<td colspan="2">
								<input type="button" class="addbtn" onclick="addrows();">
							</td>
						</tr>		
					</table>
					
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" />
							
				</div>
						
			</form:form>
		
						
			<form:form name="editITSectionRebates" action="editITSectionRebates.html" method="POST">
			
				<div id="itSecDetailEditDiv" style="visibility:collapse;">
					<table id="itSecDetailEdit" cellspacing="0" cellpadding="0" class="midsec1" border="1">
					
					</table>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" disabled="disabled"/>
					<input type="button" id="editButton"  value="edit" class="editbtn" onclick="return removeDisabled();">
				</div>						
			</form:form>
			
			<c:if test="${submitResponse ne null}">	
				<div class="successbox" id="successbox" style="visibility:visible;">
					${submitResponse}	
				</div>
			</c:if>		
			
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>		
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>		
			
			
		</c:otherwise>
	</c:choose>
			
</body>
</html>