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
<meta name="description" content="Page to Edit Mess Menu Details" />
<meta name="keywords" content="Mess Menu" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Mess Menu Details</title>

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
			<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Update Fail!</span>	
			</div>
	</c:if>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
	</div>
	
	<form:form name="updateMessMenuDetails" method="POST" action="updateMessMenuDetails.html">				
		
			
		<table id="messMenuHeadingTable" cellspacing="0" cellpadding="0" class="midsec">				
			<tr>	
				<th>Mess Menu Name  :: </th>				
				<td>
				<input type="text" class="textfield" name="messMenuRoutineName" id="messMenuRoutineName" value="${messMenuRoutineDetails.messMenuRoutineName}" readonly="readonly"/>
				<input type="hidden" name = "messMenuRoutineCode" id = "messMenuRoutineCode" value = "${messMenuRoutineDetails.messMenuRoutineCode}">
				</td>				
			</tr>			
			<tr>				
				<th>Duration Start :: </th>
				<td><input type="text" class="textfield" name="startDate" value="${messMenuRoutineDetails.startDate}" id="startDate" disabled="disabled"/></td>
			</tr>
			<tr>				
				<th>Duration End :: </th>
				<td><input type="text" class="textfield" name="endDate" value="${messMenuRoutineDetails.endDate}"  id="endDate" disabled="disabled"/></td>
			</tr>			
		</table>
		
   	  	<table cellspacing="0" cellpadding="0" class="midsec1" id="messMenuDetailsTable">   	  		
   	  		<tr>   	
   	  			<td>Days</td>  			
				<c:forEach var="mmt" items="${messMenuList}">	
				<th>
					${mmt.messMenuTimeName}
					<input type="hidden" name = "messMenuTime" id = "messMenuTime" value = "${mmt.messMenuTimeDesc}">
				</th>	
				</c:forEach>				
			</tr>
			<c:forEach var="routineSlab" items="${messMenuRoutineDetails.routineSlabList}">
				<tr>				
				<td>${routineSlab.routineSlabName}<input type="hidden" name = "days" id = "days" value = "${routineSlab.routineSlabName}"></td>
				<c:forEach var="ttm" items="${routineSlab.messMenuTimeList}">					
					<td><textarea id="${ttm.messMenuTimeCode}" name="${ttm.messMenuTimeDesc}" class="txtarea" readonly="readonly" >${ttm.messMenuValue} </textarea></td>
				</c:forEach>						
			</tr>			
			</c:forEach>				
		</table>				
		<table>
			<tr>
				<td><input id="submit" class="submitbtn" type="submit" value="Submit"></td>
				<td><input id="clear" class="clearbtn" type="reset" value="Clear"></td>
				<td><input id="edit" class="editbtn" type="button" value="Edit"  onclick="makeMessMenuDetailsEditable();"></td>
			</tr>
		</table>
		
		</form:form>
	
	
</body>
</html>