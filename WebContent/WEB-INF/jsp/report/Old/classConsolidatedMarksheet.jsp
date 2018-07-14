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
<title>Consolidated Mark sheet</title>
<link rel="stylesheet" href="/icam/css/report/studentMarksheet.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/report/consolidatedMarksheet.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Consolidated Class Mark sheet
		</h1>
</div>
<c:if test="${message ne null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">${message}</span>
		</div>					
</c:if>
<c:choose>
			<c:when test="${academicYearList eq null  || standardList eq null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg">No Academic Year /Standard / Exam  Found</span>
				</div>					
			</c:when>
<c:otherwise>
  <form action="getConsolidatedResult.html" method="post">
  	 <table cellspacing="0" cellpadding="0" class="midsec">	
  	 	<tr>
				<th>Academic Year</th>
				<td>
				<select id="academicYearName" name="academicYearCode" class="defaultselect">
					<option value="">Please Select</option>
					<c:forEach var="academicYear" items="${academicYearList}">	
						<option value="${academicYear.academicYearCode}">${academicYear.academicYearName}</option>
					</c:forEach>
				</select>					
				</td>
		</tr>		
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
		<tr>
			<th>Select Examination </th>
			<td>
				<select id="examName" name="examName" class="defaultselect">
					<option value="">Please Select</option>
				</select>				
			</td>
		</tr>		
	</table>
	<br/>
	<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
	</div><br/>
	<div class="btnsarea01">
			<input type="submit" id="submitGrade" name="submitGrade" value="Submit For Grade" onclick="return validateForm();" class="greenbtn" style="visibility:collapse;"/>
			<input type="submit" id="submitGradePoint" name="submitGradePoint" value="Submit for GradePoint" onclick="return validateForm();" class="submitbtn" />
			
			<input type="reset" class="clearbtn" value="Clear">		
			<input type="submit" name="excel" id="excel" class="editbtn" value="Generate Excel">					
	</div>	
  </form>  
</c:otherwise>
</c:choose>
</body>
</html>