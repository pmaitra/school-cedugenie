<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Configure Working Days" />
<meta name="keywords" content="Configure Working Days" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Configure Working Days</title>

<link rel="stylesheet" href="/sms/css/backoffice/configureWorkingDays.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/csommon/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="all" href="/sms/Cal/default.css" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/backoffice/workingDays.js"></script>
<script type="text/javascript" src="/sms/js/backoffice/adddelete.js"></script>
<script type="text/javascript" src="/sms/js/backoffice/configureWorkingDays.js"></script>
<script src= "/sms/Cal/zebra_datepicker.js" type="text/javascript"></script>
<script type="text/javascript" src="/sms/js/common/iframeHeight.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Configure Working Days
	</h1>
</div>
<form name="congspec" action="listConfigureWorkingDays.html" method="POST">
<table class="midsec" cellspacing="0" cellpadding="0">
	<tr>
		<th>
			Course ::
		</th>
		<td>
			<c:choose>
			<c:when test="${CourseList eq null && CourseList.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Course Found</span>	
				</div>		
			</c:when>
			<c:otherwise>
				<select id="courseId" name="courseId" class="defaultselect">
					<option value="">select</option>
				<c:forEach var="courses" items="${CourseList}">
					<option value="<c:out value="${courses.courseCode}"></c:out>"><c:out value="${courses.courseName}"></c:out></option>
				</c:forEach>
				</select>
			</c:otherwise>
			</c:choose>
		</td>
		<th>
			Term ::
		</th>
		<td>
			<select id="sterm" name="sterm" class="defaultselect">
				<option value="">select</option>
			</select>
		</td>
	</tr>
</table>
<table class="midsec" id="showdiv" style="visibility: collapse;" cellspacing="0" cellpadding="0">
	<tr>
		<th>
			Start Date :: 
		</th>
		<td>
			<input type="text" name="strdate" id="strdate" readonly="readonly" value="" class="textfield1"/>
		</td>
		<th>
			End Date :: 
		</th>
		<td>
			<input type="text" name="enddate" id="enddate" readonly="readonly" value="" class="textfield1"/>
		</td>
	</tr>
	<tr>
		<th>
			Holidays For :: 
		</th>
		<th colspan="3">
			<input type="checkbox" id="mon" name="Monday" value="1" class="checkedHoliday"/>Monday
			<input type="checkbox" id="tue" name="Tuesday" value="2" class="checkedHoliday"/>Tuesday
			<input type="checkbox" id="wed" name="Wednesday" value="3" class="checkedHoliday"/>Wednesday
			<input type="checkbox" id="thurs" name="Thursday" value="4" class="checkedHoliday"/>Thursday
			<input type="checkbox" id="fri" name="Friday" value="5" class="checkedHoliday"/>Friday
			<input type="checkbox" id="sat" name="Saturday" value="6" class="checkedHoliday"/>Saturday
			<input type="checkbox" id="sun" name="Sunday"  value="0" class="checkedHoliday"/>Sunday
		</th>
	</tr>
	<tr>
		<th>
			Public Holidays ::
		</th>
		<td>
		</td>
		<td>
			<table id="tabdata" class="midsec">
				<tr>
					<td>
						<input type="text" id="inputDate1" name="inputDateA" class="textfield1" readonly="readonly" onblur="validateDate();" value=""/>
						<input type="hidden" name="mode" id="mode" value="Public"/>
					</td>
					<td>					
						<img src="/sms/images/minus_icon.png" onclick="deleteRow(this);">	
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="moreDates" type="button" class="addbtn" onclick="addRow();" />
					</td>
				</tr>
			</table>
		</td>
		<td>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td>
		</td>
		<td>
		</td>
		<td>
			<input type="button" id="bt" name="bt" value="calculate" name="Calculate" class="submitbtn" onclick="showdivone();">
		</td>
	</tr>
</table> 
<table id="showdivone" style="visibility: collapse;" class="midsec" cellspacing="0" cellpadding="0">
	<tr>	
		<th>	
			Total Working Days ::
		</th>
		<td>
			<input type="text" id="tworking" name="tworking" readonly="readonly" class="textfield1">
		</td>
	</tr>
	<tr>
		<th>
			Total Holidays ::
		</th>
		<td>
			<input type="text" id="tholi" name="tholi" readonly="readonly" class="textfield1" >
		</td>
	</tr>
</table>
<div class="btnsarea01">
	<input type="submit" value="submit" id="submitbutton" class="submitbtn"/>
</div>
<br>
<br>
<div class="warningbox" id="warningbox" >
	<span id="warningmsg"></span>	
</div>
<div>
</div>
<input type="hidden" id="checkHoliday" name="checkHoliday" value=""/>
<input type="hidden" id="countHoliPerTerm" name="countHoliPerTerm" value=""/>
</form>

</body>

</html>