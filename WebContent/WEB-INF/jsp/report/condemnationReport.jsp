<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Class Course Result" />
<meta name="keywords" content="Student Report For Class Course Result" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Condemnation Report</title>
<link rel="stylesheet" href="/cedugenie/css/report/studentMarksheet.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
	<script>
	 $(document).ready(function() {
		 $('#startDate').Zebra_DatePicker();				 
		 $('#endDate').Zebra_DatePicker();
		 $('#startDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	 direction: false,
		 	 pair: $('#endDate')
		 	});
		 $('#endDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	 direction: 1		 	
		 	});
});
</script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Condemnation Report
		</h1>
</div>
<c:if test="${message ne null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">${message}</span>
		</div>					
</c:if>
<c:if test="${inventorySessionList eq null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">No Inventory Session Created</span>
		</div>					
</c:if>
<c:choose>
		<c:when test="${departmentList eq null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Department List Found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
  <form action="getCondemnationReport.html" method="post">
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Department Name :: <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
			<td>
					<select name="department.departmentCode" id="departmentCode" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="department" items="${departmentList}">
							<option value="${department.departmentCode}">${department.departmentName}</option>
						</c:forEach>
					</select>
			</td>
		</tr>	
  	 	<tr>
			<th>Inventory Session :: <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
			<td>
					<select name="year" id="year" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="inventorySession" items="${inventorySessionList}">
							<option value="${inventorySession.academicYearCode}">${inventorySession.academicYearName}</option>
						</c:forEach>
					</select>
			</td>
		</tr>	
	</table>
	<br/>
	<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
	</div><br/>
	<div class="btnsarea01">
			<input type="submit" id="submit" name="submit" value="Submit" onclick="return validateFormStaff();" class="submitbtn"/>
			<input type="reset" class="clearbtn" value="Clear">							
	</div>
  </form>
 </c:otherwise>
 </c:choose> 
</body>
</html>