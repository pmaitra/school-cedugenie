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
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Student Subject Mapping</title>

<link rel="stylesheet" href="/icam/css/backoffice/createStudentPromotion.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/checkAllCheckBox.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createStudentPromotion.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Student Promotion
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form method="POST" action="updateStudentPromotion.html" >
		<c:choose>
			<c:when test="${standardList eq null || empty standardList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${standardList eq null || empty standardList}">
							<span id="errormsg">Standard Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<a onClick="window.open('getViewPendingPromotion.html','_self')"><input type="button" value="View Pending Promotion" class="editbtn"></a>
				<br>
				<table id="subjectDetailTable" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							<select id="standard" name="standard" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
						<th>
							Section ::
						</th>
						<td>
							<select id="section" name="section" size="1" class="defaultselect">
								<option value="">Select</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<th colspan="2">
						<select id="type" name="type" size="1" class="defaultselect">
							<option value="PROMOTE">PROMOTE/DETAIN</option>
							<option value="PASSOUT">PASSOUT</option>
						</select>
						</th>
						<td></td>
					</tr>
					
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							<select id="toStandard" name="toStandard" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
						<th>
							Section ::
						</th>
						<td>
							<select id="toSection" name="toSection" size="1" class="defaultselect">
								<option value=''>Select</option>								
							</select>
						</td>
					</tr>
				</table>
				<table id="studentList" class="midsec1" cellspacing="0" cellpadding="0">					
					<tr>
						<th><input type="checkbox" onchange="checkAllCheckBox(this, 'rollNumber');"></th>
						<th>Roll Number</th>
						<th>Name</th>
						<th>Pass/Fail</th>
						<th>Fees</th>
					</tr>
				</table>
				<div class="btnsarea01">
					<input type="submit" onclick="return saveStudentPromotion();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>