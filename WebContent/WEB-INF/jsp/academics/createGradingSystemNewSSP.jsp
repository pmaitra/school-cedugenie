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
<title>Grading System</title>

<link rel="stylesheet" href="/cedugenie/css/academics/createGradingSystem.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/cedugenie/js/academics/createGradingSystemNew.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Grading System
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="subjectForm" id="subjectForm" method="POST" action="editGradingSystemNew.html" >
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
			<table class="midsec2" cellspacing="0" cellpadding="0">
			<tr><td colspan="2">
				<table class="midsec" cellspacing="0" cellpadding="0">
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
					</tr>
				</table>
				</td></tr><tr><td>
				<table cellspacing="0" cellpadding="0" class="midsec1">
					<tr><th colspan="3">Scholastic Areas : Part 1</th></tr>
					<tr><th colspan="3">...</th></tr>
					<tr><th colspan="3">(Grading On 8 Point Scale)</th></tr>					
					<tr>
						<th>Marks Range</th>
						<th>Grade</th>
						<!-- <th>Grade Point</th> -->
					</tr>
					<tr>
						<td>91 - 100<input type="hidden" name="Srange" value="91-100"></td>
						<td><input type="text" class="textfield1" name="Sgrade91-100" id="Sgrade91-100"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint91-100" id="Spoint91-100"></td> -->
					</tr>
					<tr>
						<td>81 - 90<input type="hidden" name="Srange" value="81-90"></td>
						<td><input type="text" class="textfield1" name="Sgrade81-90" id="Sgrade81-90"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint81-90" id="Spoint81-90"></td> -->
					</tr>
					<tr>
						<td>71 - 80<input type="hidden" name="Srange" value="71-80"></td>
						<td><input type="text" class="textfield1" name="Sgrade71-80" id="Sgrade71-80"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint71-80" id="Spoint71-80"></td> -->
					</tr>
					<tr>
						<td>61 - 70<input type="hidden" name="Srange" value="61-70"></td>
						<td><input type="text" class="textfield1" name="Sgrade61-70" id="Sgrade61-70"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint61-70" id="Spoint61-70"></td> -->
					</tr>
					<tr>
						<td>51 - 60<input type="hidden" name="Srange" value="51-60"></td>
						<td><input type="text" class="textfield1" name="Sgrade51-60" id="Sgrade51-60"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint51-60" id="Spoint51-60"></td> -->
					</tr>
					<tr>
						<td>41 - 50<input type="hidden" name="Srange" value="41-50"></td>
						<td><input type="text" class="textfield1" name="Sgrade41-50" id="Sgrade41-50"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint41-50" id="Spoint41-50"></td> -->
					</tr>
					<tr>
						<td>33 - 40<input type="hidden" name="Srange" value="33-40"></td>
						<td><input type="text" class="textfield1" name="Sgrade33-40" id="Sgrade33-40"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint33-40" id="Spoint33-40"></td> -->
					</tr>
					
					<tr>
						<td>32 & Below<input type="hidden" name="Srange" value="0-32"></td>
						<td><input type="text" class="textfield1" name="Sgrade0-32" id="Sgrade0-32"></td>
						<!-- <td><input type="text" class="textfield1" name="Spoint0-20" id="Spoint0-20"></td> -->
					</tr>
				</table>
				</td><td>
				<table  id="" cellspacing="0" cellpadding="0" class="midsec1">
					<tr><th colspan="3">Co-Scholastic Areas : Part 2</th></tr>
					<tr><th colspan="3">Co-Curricular Activities : Part 3</th></tr>
					<tr><th colspan="3">(Grading On 5/3 Point Scale)</th></tr>
					<tr>
						<th>Grade Point Range</th>
						<th>Grade</th>
						<!-- <th>Grade Point</th> -->
					</tr>
					<tr>
						<td>4.1 - 5.0<input type="hidden" name="Crange" value="4.1-5.0"></td>
						<td><input type="text" class="textfield1" name="Cgrade4.1-5.0" id="Cgrade4.1-5.0"></td>
						<!-- <td><input type="text" class="textfield1" name="Cpoint4.1-5.0" id="Cpoint4.1-5.0"></td> -->
					</tr>
					<tr>
						<td>3.1 - 4.0<input type="hidden" name="Crange" value="3.1-4.0"></td>
						<td><input type="text" class="textfield1" name="Cgrade3.1-4.0" id="Cgrade3.1-4.0"></td>
						<!-- <td><input type="text" class="textfield1" name="Cpoint3.1-4.0" id="Cpoint3.1-4.0"></td> -->
					</tr>
					<tr>
						<td>2.1 - 3.0<input type="hidden" name="Crange" value="2.1-3.0"></td>
						<td><input type="text" class="textfield1" name="Cgrade2.1-3.0" id="Cgrade2.1-3.0"></td>
						<!-- <td><input type="text" class="textfield1" name="Cpoint2.1-3.0" id="Cpoint2.1-3.0"></td> -->
					</tr>
					<tr>
						<td>1.1 - 2.0<input type="hidden" name="Crange" value="1.1-2.0"></td>
						<td><input type="text" class="textfield1" name="Cgrade1.1-2.0" id="Cgrade1.1-2.0"></td>
						<!-- <td><input type="text" class="textfield1" name="Cpoint1.1-2.0" id="Cpoint1.1-2.0"></td> -->
					</tr>
					<tr>
						<td>0.1 - 1.0<input type="hidden" name="Crange" value="0.1-1.0"></td>
						<td><input type="text" class="textfield1" name="Cgrade0.1-1.0" id="Cgrade0.1-1.0"></td>
						<!-- <td><input type="text" class="textfield1" name="Cpoint0.1-1.0" id="Cpoint0.1-1.0"></td> -->
					</tr>
				</table>
				</td></tr></table>
				<div class="btnsarea01">
					<input type="submit" onclick="return saveGradingSystem();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>