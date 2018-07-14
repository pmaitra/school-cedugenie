<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Salary Templates" />
<meta name="keywords" content="Salary Templates" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Salary Templates</title>
<link rel="stylesheet" href="/icam/css/erp/viewSalaryTemplates.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/icam/css/common/pagination.css">
<script src= "/icam/js/common/validateSearch.js" type="text/javascript"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Salary Templates
		</h1>
	</div>	
	<c:choose>
		<c:when test="${salaryTemplateList eq null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Salary Templates Found</span>	
			</div>								
		</c:when>
	<c:otherwise>	
	<table id="fixList" cellspacing="0" cellpadding="0" class="midsec1">		
		<tr>
	       <th>Salary Templates Name</th>
		   <th>Salary Templates Type</th>	
		   <th>Salary Templates Description</th>
		   <th>Details</th>		  
		</tr>	
	    <c:forEach var="stl" items="${salaryTemplateList}"> 
			<tr>
		       <td align="center">${stl.salaryTemplateName}</td>
			   <td align="center">${stl.salaryTemplateType}</td>	
			   <td align="center">${stl.salaryTemplateDesc}</td>
			   <td><a href="viewSalaryTemplateDetails.html?salaryTemplateCode=${stl.salaryTemplateCode}&salaryTemplateType=${stl.salaryTemplateType}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
			</tr>
		`</c:forEach>				
	</table>		
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
	</div>		

</c:otherwise>
</c:choose>
</body>
</html>