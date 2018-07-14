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
<title>Staff Details List</title>
<link rel="stylesheet" href="/icam/css/report/studentMarksheet.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/report/studentNominalRoll.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Staff Details List
		</h1>
</div>
<c:if test="${message ne null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">${message}</span>
		</div>					
</c:if>
<c:choose>
			<c:when test="${resourceTypeList eq null }">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg">No Resource Type Found</span>
				</div>					
			</c:when>
<c:otherwise>
  <form action="getStaffDetailsList.html" method="post">
  	 <table cellspacing="0" cellpadding="0" class="midsec">	
  	 	<tr>
				<th>Resource Type</th>
				<td>
				<select id="resourceTypeName" name="resource.resourceType.resourceTypeCode" class="defaultselect">
					<option value="">Please Select</option>
					<c:forEach var="resourceType" items="${resourceTypeList}">	
						<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
					</c:forEach>
				</select>					
				</td>
		</tr>	
	</table>
	
	<table cellspacing="0" cellpadding="0" class="midsec" style="visibility: collapse;">	
  	 	<tr>
				<th colspan="2" style="text-align: center;">Select Columns</th>			
		</tr>
		<tr>
			<td><input type="checkbox" name="resource.userId" value="userID">&nbsp;&nbsp;User ID</td>
			<td><input type="checkbox" name="employeeCode" value="empCode" checked="checked">&nbsp;&nbsp;Employee Code</td>
		</tr>	
		<tr>
			<td><input type="checkbox" name="resource.name" value="designation" checked="checked">&nbsp;&nbsp;Name</td>
			<td><input type="checkbox" name="designation.designationCode" value="designation" checked="checked">&nbsp;&nbsp;Designation</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="resource.gender" value="gender" checked="checked">&nbsp;&nbsp;Gender</td>			
			<td><input type="checkbox" name="resource.category" value="category" checked="checked">&nbsp;&nbsp;Category</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><input type="checkbox" name="qualificationSummary" value="qualification">&nbsp;&nbsp;Qualification</td> -->
<!-- 			<td><input type="checkbox" name="resource.dateOfBirth" value="dob">&nbsp;&nbsp;Date Of Birth</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><input type="checkbox" name="dateOfJoining" value="doj">&nbsp;&nbsp;Date Of Joining</td> -->
<!-- 			<td><input type="checkbox" name="dateOfRetirement" value="dor">&nbsp;&nbsp;Date Of Retirement</td> -->
<!-- 		</tr> -->
		<tr>
			<td><input type="checkbox" name="resource.religion" value="religion">&nbsp;&nbsp;Religion</td>
			<td><input type="checkbox" name="resource.mobile" value="contact">&nbsp;&nbsp;Contact Details</td>			
		</tr>
	</table>
	<br/>
	<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
	</div><br/>
	<div class="btnsarea01">
			<input type="submit" id="submit" name="submit" value="Submit" onclick="return validateFormStaff();" class="submitbtn"/>
			<input type="reset" class="clearbtn" value="Clear">	
			<input type="submit" name="excel" id="excel" class="editbtn" value="Generate Excel" onclick="return validateFormStaff();">						
	</div>
  </form>  
</c:otherwise>
</c:choose>
</body>
</html>