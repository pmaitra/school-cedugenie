<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Access Type - Contact Mapping" />
<meta name="keywords" content="Access Type - Contact Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Read Archive Record</title>
<link rel="stylesheet" href="/icam/css/administrator/readArchivedData.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
 <script>
			window.onload=function(){
				var d = new Date();
				var aYear="";
				if(d.getMonth()>=3)
					aYear=d.getFullYear()+"-"+(d.getFullYear()+1);
				else if(d.getMonth()<3)
					aYear=(d.getFullYear()-1)+"-"+d.getFullYear();
				alert("Current Academic Year ::"+aYear);
			};
</script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Read Archive Record
	</h1>
</div>
	<form action="readArchivedDataPost.html" method="post">
		<table cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Academic Year<img class="required" src="/icam/images/required.gif" alt="Required">   </th>
				<td>
					<input type="text" name="academicYear" id="academicYear" class="textfield1">
				</td>
			</tr>
			<tr>
				<th>Resource Type<img class="required" src="/icam/images/required.gif" alt="Required">   </th>			
				<td>
					<select name="resourceType" id="resourceType" class="defaultselect">
						<option value="Student">Student</option>
		 				<option value="Staff">Staff</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>User/Registration ID<img class="required" src="/icam/images/required.gif" alt="Required">   </th>
				<td>
					<input type="text" name="id" id="id" class="textfield1">
				</td>
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>		
			<input type="submit" class="submitbtn" value="Fetch Record" onclick="return validate();"/>
		</div>
	</form>
</body>
</html>