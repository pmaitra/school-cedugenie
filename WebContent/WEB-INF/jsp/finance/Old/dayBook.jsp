<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="profitAndLoss" />
<meta name="keywords" content="profitAndLoss" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Day Book</title>

<link rel="stylesheet" type="text/css" href="/icam/css/finance/financialOutput.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script>
$(document).ready(function() {
	 $('#fromDate').Zebra_DatePicker();
	 $('#toDate').Zebra_DatePicker();
	 
	 $('#fromDate').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
	 $('#toDate').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
	 
	 
	 $("#get").click(function(){	 
		 
			$.ajax({			
		    url: '/icam/getDayBook.html',
		    	dataType: 'json',
		    	//data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val()+ ($("#toDate").val()),
		    	data: "from=" + ($("#fromDate").val())+ "&to=" + ($("#toDate").val())+ "&ledger=" + ($("#ledger").val()),
		    	success: function(data) {
		    		alert(data);
		  			document.getElementById("tbDiv").innerHTML=data; 		
		    	}
			});
	 });
 });
 </script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;DayBook
	</h1>
</div>
	<form:form method="POST" action="getCashBook.html" target="_blank" >
		<table id="passbookTable" cellspacing="0" cellpadding="0" class="midsec">
			
			<tr>
				<th>From Date</th>
				<td>
					<input type="text" name="fromDate" id="fromDate" class="date" >
				</td>
			</tr>
			<tr>
				<th>To Date</th>
				<td>
					<input type="text" name="toDate" id="toDate" class="date" >
				</td>				
			</tr>
			<tr>
				<th>ledger</th>
				<td>
					<select name="ledger" id="ledger" class="defaultdropdown" >
						<option value="">Select</option>
						<c:if test="${ledgerList ne null}">
							<c:forEach var="ledger" items="${ledgerList}">
								<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
							<input type="button" value="GET" id="get" class="submitbtn"/>
				</th>
			</tr>
		</table>
		<div id="tbDiv">
		</div>
	</form:form>
</body>
</html>