<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Vendor" />
<meta name="keywords" content="Mess Menu" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Mess Menu Details</title>

<link rel="stylesheet" href="/icam/css/inventory/messMenu.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/messMenu.js"></script>
	<script src="/icam/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 $('#startDate').Zebra_DatePicker();				 
			
			 $('#startDate').Zebra_DatePicker({
			 	  format: 'd/m/Y',
			 	 direction: false
			 	});
		});
		 
		 $(document).ready(function() {
			 $('#endDate').Zebra_DatePicker();				 
			
			 $('#endDate').Zebra_DatePicker({
			 	  format: 'd/m/Y',
			 	 direction: false
			 	});
		});
	</script>	
</head>
<body>

	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Mess Menu Details
		</h1>
	</div>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Successfully Inserted!</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Fail To Insert</span>	
			</div>
	</c:if>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
	</div>
	
	
	<c:choose>
	<c:when test="${messMenuList eq null || empty messMenuList}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Menu Time Table Found </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	
	
	<form:form name="submitMessMenuDetails" method="POST" action="submitMessMenuDetails.html">				
		
			
		<table id="messMenuHeadingTable" cellspacing="0" cellpadding="0" class="midsec">				
			<tr>	
				<th>Mess Menu Name  :: </th>
				<td><input type="text" class="textfield" name="messMenuRoutineName" id="messMenuRoutineName"/></td>				
			</tr>			
			<tr>				
				<th>Duration Start :: </th>
				<td><input type="text" class="textfield" name="startDate" id="startDate"/></td>
			</tr>
			<tr>				
				<th>Duration End :: </th>
				<td><input type="text" class="textfield" name="endDate" id="endDate"/></td>
			</tr>			
		</table>
		
   	  	<table cellspacing="0" cellpadding="0" class="midsec1" id="messMenuDetailsTable">   	  		
   	  		<tr>   	
   	  			<td>Days</td>  			
				<c:forEach var="mmt" items="${messMenuList}">	
				<td>
					${mmt.messMenuTimeName}
					<input type="hidden" name = "messMenuTime" id = "messMenuTime" value = "${mmt.messMenuTimeDesc}">
				</td>	
				</c:forEach>				
			</tr>			
			<tr>
				<td>Monday<input type="hidden" name = "days" id = "days" value = "Monday"></td>				
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			<tr>
				<td>Tuesday<input type="hidden" name = "days" id = "days" value = "Tuesday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			
			<tr>
				<td>Wednesday<input type="hidden" name = "days" id = "days" value = "Wednesday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			<tr>
				<td>Thursday<input type="hidden" name = "days" id = "days" value = "Thursday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			
			<tr>
				<td>Friday<input type="hidden" name = "days" id = "days" value = "Friday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			<tr>
				<td>Saturday<input type="hidden" name = "days" id = "days" value = "Saturday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>
			
			<tr>
				<td>Sunday<input type="hidden" name = "days" id = "days" value = "Sunday"></td>
				<c:forEach var="mmt" items="${messMenuList}">														
					<td><textarea id="${mmt.messMenuTimeCode}" name="${mmt.messMenuTimeDesc}" class="txtarea" ></textarea></td>				
				</c:forEach>						
			</tr>				
		</table>		
		
		<table>
			<tr>
				<td><input id="submit" class="submitbtn" type="submit" value="Submit" onclick="return validateMessMenuDetails();"></td>
				<td><input id="clear" class="clearbtn" type="reset" value="Clear"></td>
			</tr>
		</table>		
		</form:form>
	</c:otherwise>
	</c:choose>
	
	
</body>
</html>