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
<meta name="description" content="Page to LPG Cylinder Refill Report" />
<meta name="keywords" content="Student Report For LPG Cylinder Refill Report" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>LPG Cylinder Refill Report</title>
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
		 $('#startDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	  direction: false,
		 	  pair: $('#endDate')
		 	});
		 $('#endDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	  direction: 1
		 	});
		 
		 $("#startDate").click(function() {
			 document.getElementById("endDate").value='';
	 	});
		$("#submit").click(function() {
			document.getElementById("warningbox").style.visibility="collapse";
			document.getElementById("warningmsg").innerHTML="";
			startDate = document.getElementById("startDate").value;
			if(startDate == ""){	
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please select start date";
				return false;
			}
	 	});
});
</script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;LPG Cylinder Refill Report
		</h1>
</div>
<c:if test="${message ne null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">${message}</span>
		</div>					
</c:if>

  <form action="createLPGCylinderRefillingReport.html" method="post">
	<table cellspacing="0" cellpadding="0" class="midsec">	
  	 	<tr>
				<th colspan="2" style="text-align: center;">Please Select Date Range</th>			
		</tr>
		<tr>
			<th>Start Date <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
			<td><input type="text" id="startDate" name="startDate" class="textfield1"></td>
		</tr>
		<tr>
			<th>End Date</th>
			<td><input type="text" id="endDate"  name="endDate" class="textfield1"></td>
		</tr>
		<tr>
			<th>LPG Company :: </th>
			<td>
				<select name="lpgDetails.lpgDetailsId" id="companyName" class="defaultselect">
					<option value="0">Select...</option>
						<c:forEach var="lpgCompany" items="${lpgCompanyList}">
							<option value="${lpgCompany.lpgDetailsId}">${lpgCompany.companyName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Distributors :: </th>
			<td>
				<select name="lpgDetails.vendor.vendorId" id="vendorName" class="defaultselect">
					<option value="0">Select...</option>
					<c:forEach var="lpgDistributor" items="${lpgDistributorList}">
							<option value="${lpgDistributor.vendorId}">${lpgDistributor.vendorName}</option>
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
			<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn"/>
			<input type="reset" class="clearbtn" value="Clear">							
	</div>
  </form> 
</body>
</html>