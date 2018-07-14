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
<title>Student Nominal Roll</title>
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
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Nominal Roll
		</h1>
</div>
<c:if test="${message ne null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">${message}</span>
		</div>					
</c:if>
  <form action="getStudentNominalRoll.html" method="post">  
  
  <table cellspacing="0" cellpadding="0" class="midsec">	
  	 	<tr>
				<th>Select Class</th>
				<td>				
				<select id="standardName" name="standardCode" class="defaultselect">
					<option value="">Please Select</option>
					<c:forEach var="standard" items="${standardList}">	
						<option value="${standard.standardCode}">${standard.standardName}</option>
					</c:forEach>
				</select>					
				</td>
		</tr>		
		<tr>
			<th>Select Section</th>
			<td>
				<select id="sectionName" name="sectionCode" class="defaultselect">
					<option value="">Please Select</option>					
				</select>					
			</td>
		</tr>			
	</table>	
	<table cellspacing="0" cellpadding="0" class="midsec" style="visibility: visible;">	
  	 	<tr>
				<th colspan="2" style="text-align: center;">Select Columns</th>			
		</tr>
		<tr>
			<td><input type="checkbox" name="column" value="roll" checked="checked">&nbsp;&nbsp;Roll Number</td>
			<td><input type="checkbox" name="column" value="name" checked="checked">&nbsp;&nbsp;Student Name</td>
		</tr>	
		<tr>
			<td><input type="checkbox" name="column" value="house" checked="checked">&nbsp;&nbsp;House</td>
			<td><input type="checkbox" name="column" value="category" >&nbsp;&nbsp;Category</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="column" value="dob">&nbsp;&nbsp;Date Of Birth</td>
			<td><input type="checkbox" name="column" value="stateOfDomicile">&nbsp;&nbsp;State Of Domicile</td>
		</tr>
		<tr>
			<td><input type="checkbox" name="column" value="fName">&nbsp;&nbsp;Parent's Name</td>
			<td><input type="checkbox" name="column" value="secondLanguage">&nbsp;&nbsp;Second Language</td>
<!-- 			<td><input type="checkbox" name="column" value="mName">&nbsp;&nbsp;Mother's Name</td> -->
		</tr>
<!-- 		<tr> -->
			
<!--  			<td><input type="checkbox" name="column" value="doa">&nbsp;&nbsp;Date Of Admission</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td colspan="2"><input type="checkbox" name="column" value="contact">&nbsp;&nbsp;Contact Details</td>			 -->
<!-- 		</tr> -->
	</table>
	<br/>
	<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
	</div><br/>
	<div class="btnsarea01">
			<input type="submit" id="submit" name="submit" value="Submit" onclick="return validateForm();" class="submitbtn"/>
			<input type="reset" class="clearbtn" value="Clear">		
			<input type="submit" name="excel" id="excel" class="editbtn" value="Generate Excel" onclick="return validateForm();" >					
	</div>	
		
		
  </form> 
</body>
</html>