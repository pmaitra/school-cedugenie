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
<c:url value="/academicsAssetConsumptionListPagination.html" var="pagedLink">
	<c:param name="p" value="~"/>
	<c:param name="assetName" value="${assetName}"/>
	<c:param name="assetID" value="${assetId}"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Asset Consumption List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Asset Consumption List</title>

<link rel="stylesheet" href="/cedugenie/css/academics/labAssetConsumptionList.css" />

<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/Cal/default.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/labAssetConsumptionList.js"></script>

	<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 
			 $('#startDate').Zebra_DatePicker();
			 $('#startDate').Zebra_DatePicker({
			 	format: 'd/m/Y',
			 	direction: false
			 });
		
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
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Asset Consumption List
	</h1>
</div>

	<form:form method="POST" action="getAssetConsumptionList.html">
		<table id="assetConsumptionListTable" cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Asset :: </th>
				<th>${assetName}<input type="hidden" class="textfield" name="asset.assetName" id="assetName" value="${assetName}"/></th>				
			</tr>		
			<tr>
				<th>From Date :: </th>
				<td><input type="text" class="textfield" name="startDate" id="startDate"/></td>				
			</tr>		
			<tr>	
				<th>To Date :: </th>
				<td><input type="text" class="textfield" name="endDate" id="endDate"/></td>								
			</tr>	
			<tr>
				<td colspan="2">
					<input id="clear" class="clearbtn" type="reset" value="Clear">					
					<input id="submit" class="submitbtn" type="submit" value="Submit">
				</td>
			</tr>
		</table>
		<input type="hidden" class="textfield" name="asset.assetId" id="assetId" value="${assetId}"/>
	</form:form>
	
	
	<c:if test="${showConsumptionList == 'showConsumptionList'}">
		<c:choose>		
			<c:when test="${assetConsumptionPagedListHolder == null || empty assetConsumptionPagedListHolder}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="warningbox" id="warningbox2" style="visibility: visible;">
						<span id="warningmsg">No Consumption History For This Asset !</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>	
	   	  		<table cellspacing="0" cellpadding="0" class="midsec1" id="assetListTable">   	  		
	   	  			<tr>
			   			<th>From</th>
						<th>To</th>
						<th>Consumed Unit</th>
					</tr>   	  		
					<c:forEach var="asst" items="${assetConsumptionPagedListHolder.pageList}">	
					<tr>
						<td>
							${asst.startDate}
						</td>			
						<td>
							${asst.endDate}
						</td>
						<td>
							${asst.unit}
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="3" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${assetConsumptionPagedListHolder}" pagedLink="${pagedLink}"/></td>
					</tr>			
				</table>
			</c:otherwise>
		</c:choose>
	</c:if>

	<div class="btnsarea01">
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
	</div>


</body>
</html>