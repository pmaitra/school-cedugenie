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
<title>Co Scholastic List</title>

<link rel="stylesheet" href="/cedugenie/css/academics/listCoScholasticResult.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/listCoScholasticResult.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Co Scholastic List
	</h1>
</div>

<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" style="visibility: visible;" >
			<span>${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>
<c:choose>	
	<c:when test="${null eq resultStatusList || empty resultStatusList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">
					<c:if test="null eq descriptiveIndicatorHeadList || empty descriptiveIndicatorHeadList">Student Not Found
					<c:if test="null ne status">${status}PPP</c:if>
				</c:if>
				</span>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<form:form id="studentListForm" name="studentListForm" action="getStudentDetails.html" method="POST">
			<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Standard</th>
					<th>Section</th>
					<th>Total</th>
					<th>Uploaded</th>
					<th>Remeaning</th>
					<th>Update</th>
				</tr>
				<c:forEach var="status" items="${resultStatusList}">
					<tr>
						<td>
							${status.standard}
						</td>
						<td>							
							${status.section}
						</td>
						<td>
							${status.total}
						</td>
						<td>
							${status.completed}
						</td>
						<td>
							${status.total-status.completed}
						</td>
						<td>
							<c:if test="${status.total-status.completed gt 0}">
								<a onClick="window.open('createCoScholasticResultNew.html?standard=${status.standard}&section=${status.section}','_self')"><input type="button" value="INSERT" class="editbtn"></a>
							</c:if>
							<c:if test="${status.completed gt 0}">
								<a onClick="window.open('updateCoScholasticResult.html?standard=${status.standard}&section=${status.section}','_self')"><input type="button" value="VIEW/UPDATE" class="editbtn"></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>