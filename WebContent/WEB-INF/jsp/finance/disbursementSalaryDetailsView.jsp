<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Staff Salary Details" />
<meta name="keywords" content="Staff Salary Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Disbursement Salary Details</title>
<link rel="stylesheet" href="/cedugenie/css/finance/disbursementSalaryDetailsView.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/cedugenie/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/cedugenie/Cal/jsDatePick_ltr.min.css">
<script src="/cedugenie/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/cedugenie/Cal/zebra_datepicker.js"></script>

<script src= "/cedugenie/js/finance/disbursementSalaryDetailsView.js" type="text/javascript"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Disbursement Salary Details
		</h1>
</div>
	<input type="hidden" id="month" value="${month}">
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="2">Month :: ${month}</th>
		</tr>
		<tr>
			<th colspan="2" class="row">
				Employee<img class="required" src="/cedugenie/images/required.gif" alt="Required"> :: 
				<select id="employeeCode" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="staffCode" items="${staffCodeList}">
						<c:if test="${fn:length(fn:trim(staffCode.employeeCode)) != 0}">										
							<option value="<c:out value="${staffCode.employeeCode}"/>"><c:out value="${staffCode.resource.name}"/></option>
						</c:if>
					</c:forEach>
				</select>
			</th>
		</tr>
		<tr>
			<td>
			
				<table cellspacing="0" cellpadding="0" class="midsec">
				   <th colspan="2"><b>EARNING</b></th>
					<tr>
						<th>GRADE PAY</th>				
						<td><span id="gradePay">0</span></td>	
					</tr>
					<tr>
						<th>Basic</th>		
						<td><span id="basic">0</span></td>
					</tr>
					<tr>
						<th>D A</th>		
						<td><span id="da">0</span></td>
					</tr>
					<tr>
						<th>S M A/H M A</th>		
						<td><span id="smaHma">0</span></td>
					</tr>
					<tr>
						<th>T P T L</th>		
						<td><span id="tptl">0</span></td>
					</tr>
					<tr>
						<th>M A</th>		
						<td><span id="ma">0</span></td>
					</tr>
					<tr>
						<th>S A</th>		
						<td><span id="sa">0</span></td>
					</tr>
					<tr>
						<th>Arrear</th>		
						<td><span id="arrear">0</span></td>
					</tr>
					<tr>
						<th>Misc. Inc</th>		
						<td><span id="miscInc">0</span></td>
					</tr>
					<tr>
						<th>E D</th>		
						<td><span id="ed">0</span></td>
					</tr>
					<tr>
						<th>N P S</th>		
						<td><span id="nps">0</span>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table cellspacing="0" cellpadding="0" class="midsec">
				<th colspan="2"><b>DEDUCTION</b></th>
					<tr>
						<th>G P F</th>		
						<td><span id="gpf">0</span></td>
					</tr>
					<tr>
						<th>C P F</th>		
						<td><span id="cpf">0</span></td>
					</tr>
					<tr>
						<th>N P S Both</th>		
						<td><span id="npsBoth">0</span></td>
					</tr>
					<tr>
						<th>W C</th>		
						<td><span id="wc">0</span></td>
					</tr>
					<tr>
						<th>E C</th>		
						<td><span id="electricCharge">0</span></td>
					</tr>
					<tr>
						<th>I P</th>		
						<td><span id="ip">0</span></td>
					</tr>
					<tr>
						<th>P.F.L</th>		
						<td><span id="pfl">0</span></td>
					</tr>
					<tr>
						<th>F.A</th>		
						<td><span id="fa">0</span></td>
					</tr>
					<tr>
						<th>G I P</th>		
						<td><span id="gip">0</span></td>
					</tr>
					<tr>
						<th>P T</th>		
						<td><span id="pt">0</span></td>
					</tr>
					<tr>
						<th>I.T</th>
						<td><span id="it">0</span></td>
					</tr>
					<tr>
						<th>Misc. Expenses</th>		
						<td><span id="miscExpenses">0</span></td>
					</tr>
				</table>				
			</td>
		</tr>
		<tr>
			<td>
				Gross Total :: <span id="gross" style="color: blue; text-align: right;">0</span>
			</td>
			<td>
				Total Deduction :: <span id="total" style="color: blue; text-align: right;">0</span>
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				Net Total :: <span id="netSalary" style="color: blue;">0</span>
			</td>
		</tr>
		
		
	</table>

				
	
	
	
	
	

	
</body>
</html>