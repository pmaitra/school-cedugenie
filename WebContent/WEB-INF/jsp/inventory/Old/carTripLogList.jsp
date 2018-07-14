<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/carTripLogListPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Books" />
<meta name="keywords" content="List Books" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Car Trip Log List</title>

<link rel="stylesheet" href="/icam/css/inventory/carTripLogList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Car Trip Log List &emsp; &emsp; &emsp; &emsp;
	</h1>
</div>


	<c:choose>
		<c:when test="${carTripLogPagedListHolder eq null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Car Trip Log Not Found</span>	
				</div>
			</div>
		</c:when>
	<c:otherwise>	
   	  	<table cellspacing="0" cellpadding="0" class="midsec1" id="vendorList">
	   		<tr>
	   			<th>Car</th>
	   			<th>Date</th>
				<th>Specific Nature Of Duty</th>
				<th>From</th>
				<th>To</th>
				<th>Kilometers Rides</th>
				<th>Mileage/ KM Rds</th>
			</tr>	
			
			<c:forEach var="ctl" items="${carTripLogPagedListHolder.pageList}">	
				<tr>
					<td>
						${ctl.carDetails.carDetailsName}
					</td>			
					<td>
						${ctl.journeyDate}
					</td>
					<td>
						${ctl.natureOfDuty}
					</td>
					<td>
						${ctl.rideStartFrom}
					</td>
					<td>
						${ctl.rideEndsTo}
					</td>
					<td>
						${ctl.kilometerRides}
					</td>
					<td>
						${ctl.mileagePerKm}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${carTripLogPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>		
	</c:otherwise>
	</c:choose>
</body>
</html>