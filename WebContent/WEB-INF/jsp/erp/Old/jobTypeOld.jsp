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
<title>Add Job Type</title>
<link rel="stylesheet" href="/icam/css/erp/jobType.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/erp/jobType.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Job Type
		</h1>
	</div>
		
	<form:form name="submitJobType" action="submitJobType.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Job Type ::
				</th>						
			</tr>				
			<tr>
				<th>
					Enter Job Type Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="jobTypeName" id="jobTypeName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateJobTypeForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
		
		<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Job Type Successfully Created.</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Job Type Creation Failed.</span>	
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">	
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Job Type Successfully Updated.</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Job Type Updatation Failed.</span>	
				</div>
			</c:if>			
		</c:if>			
		<div class="infomsgbox" id="infomsgbox">
			<span id="infomsg"></span>	
		</div>
						
						
	<c:choose>
		<c:when test="${jobTypeList == null ||  empty jobTypeList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Job Type Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editJobType" action="editJobType.html" method="POST">		
					
				<table id="editJobType" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="2">
							View / Edit Job Type
						</th>
					</tr>				
					<tr>
						<th>
							Select
						</th>
						<th>
							Job Type Name
						</th>
					</tr>
					<c:forEach var="jobType" items="${jobTypeList}" varStatus="i">
						<tr>
							<td><input type="radio" name="jobTypeCode" id="radioJobType${i.index}" value="${jobType.jobTypeCode}" /></td>
							<td>
								<input type="hidden" name="oldJobTypeNames" value="${jobType.jobTypeName}">
								<input type="text" class="textfield1" id="textJobType${i.index}" name="jobTypeName" value="${jobType.jobTypeName}" disabled="disabled"> 
							</td>							
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="2">
							<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" disabled="disabled" onclick="return validateEditJobTypeForm();"/>
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('jobTypeCode','warningbox','warningmsg');">
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
	</div>
		
</body>
</html>