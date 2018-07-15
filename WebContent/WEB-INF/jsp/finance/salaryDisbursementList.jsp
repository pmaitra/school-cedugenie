<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listStudentsPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Asset List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Salary Disbursement List</title>

<link rel="stylesheet" href="/cedugenie/css/finance/salaryDisbursementList.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/salaryDisbursementList.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Salary Disbursement List
	</h1>
</div>


<c:choose>
	<c:when test="${month eq 'NA'}">
		<form method="GET" action="getSalaryDisbursementList.html" >
			<table class="midsec" class="midsec" cellspacing="0" cellpadding="0">
				<tr>
					<th>
						Month::
					</th>
					<td>
						<input type="text" id="month" name="month" class="textfield1" placeholder="MM/YYYY"  />
					</td>
					<td>
						<input type="submit" value="Get" class="greenbtn"/>
					</td>
				</tr>
			</table>
		</form>
	</c:when>
	<c:otherwise>
		<c:choose>	
			<c:when test="${null eq salaryDisbursementList || empty salaryDisbursementList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">Employee Type List Not Found</span>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<form:form id="studentListForm" name="studentListForm" action="getStudentDetails.html" method="POST">
					<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
						<tr>
							<th colspan="6">Month :: ${month}</th>
						</tr>
						<tr>
							<th>Employee Type</th>
							<th>Total</th>
							<th>Remeaning</th>
							<th>Uploaded</th>
							<th>View/Update</th>
						</tr>
						<c:forEach var="sal" items="${salaryDisbursementList}">
							<tr>
								<td>
									${sal.resourceType}
								</td>
								<td>							
									${sal.total}
								</td>
								<td>
									${sal.remaining}
								</td>
								<td>
									${sal.updated}
								</td>
								<td>
									<c:if test="${sal.remaining gt 0}">
										<a onClick="window.open('openDisbursementSalaryDetails.html?resourceType=${sal.resourceType}&month=${month}&type=Insert','_self')"><input type="button" value="UPDATE" class="editbtn"></a>
									</c:if>
									<c:if test="${sal.updated gt 0}">
										<a onClick="window.open('openDisbursementSalaryDetails.html?resourceType=${sal.resourceType}&month=${month}&type=View','_self')"><input type="button" value="VIEW" class="editbtn"></a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</form:form>
			</c:otherwise>
		</c:choose>
			
	</c:otherwise>
</c:choose>







</body>
</html>