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
<c:url value="/listHostelFacilityPagination.html" var="pagedLink">
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
<title>Hostel Facility List</title>

<link rel="stylesheet" href="/cedugenie/css/hostel/listHostelFacility.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/hostel/listHostelFacility.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Hostel Facility List
	</h1>
</div>
<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
			<span id="infomsg1">${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>
<c:choose>	
	<c:when test="${pagedListHolder eq null || empty pagedListHolder}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Hostel Facility List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<form:form id="getHostelFacilityDetails" name="getHostelFacilityDetails" action="getHostelFacilityDetails.html" method="POST">
		
		<table id="add"  cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Search Parameter</th>
				<th>Search Value</th>
				<th>
				</th>
			</tr>
			<tr>			
				<td>
					<select id="searchKey" name="searchKey" class="defaultselect">
						<option value="">Please Select</option>
						<option value="FacilityName">Facility Name</option>
						<option value="Hostel">Hostel</option>
					</select>
				</td>
				<td>
					<input type="text" class="textfield2" name="searchValue" id="another"/>
				</td>
				<td>
					<input type="submit" name="searchSubmit" id="search" onclick="return validateSearch('searchKey','another','warningbox','warningmsg');" value="Search" class="editbtn">
				</td>
			</tr>		
		</table>
		
		<table  id="facilityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th><input type="radio" disabled="disabled"></th>
				<th>Facility Name</th>
				<th>Hostel</th>
			</tr>
			<c:forEach var="hostelFacility" items="${pagedListHolder.pageList}">
				<tr>
					<td>
						<input type="radio" name="hostelFacilityCode" value="${hostelFacility.hostelFacilityCode}" />
					</td>
					<td>
						${hostelFacility.hostelFacilityName}
					</td>
					<td>
						<c:forEach var="hostel" items="${hostelFacility.hostelList}">
							* ${hostel.hostelName} *&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
			<tr>
 				<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>	
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input type="submit" class="submitbtn" value="Submit" onclick="return valradio('hostelFacilityCode', 'warningbox', 'warningmsg');">
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>