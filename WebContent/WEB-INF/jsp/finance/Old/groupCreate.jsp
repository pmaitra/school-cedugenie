<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Create Group" />
<meta name="keywords" content="Create Group" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Group</title>
<link rel="stylesheet" type="text/css" href="/icam/css/finance/groupAndLedgerCreate.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/finance/groupAndLedgerCreate.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Group
	</h1>
</div>
	<form:form name="" action="createGroup.html" method="POST" >
	
		<c:forEach var="gfl" items="${parentGroupList}">
			<input type="hidden" name="oldGroupNames" value="${gfl.groupName}">
		</c:forEach>
		
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th> Name<img class="required" src="/icam/images/required.gif" alt="Required">:: </th>
				<td><input type="text" name="groupName" id="groupName" class="textfield" onblur="checkGroupName(this);" ></td>
			</tr>
			<tr>
				<th>Parent<img class="required" src="/icam/images/required.gif" alt="Required"> :: </th>
				<td>
					<select name="parentGroupCode" id="parentGroupCode" class="defaultdropdown" >
						<option value="">Select</option>
						<c:if test="${parentGroupList ne null}">
							<c:forEach var="gfl" items="${parentGroupList}">
								<option value="${gfl.groupCode}">${gfl.groupName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>Group Type <img class="required" src="/icam/images/required.gif" alt="Required">:: </th>
				<td>
					<select name="groupTypeCode" id="groupTypeCode" class="defaultdropdown" >
						<option value="">Select</option>
						<c:if test="${groupTypeList ne null}">
							<c:forEach var="gfl" items="${groupTypeList}">
								<option value="${gfl.groupTypeCode}">${gfl.groupTypeName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
		</table>
		
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input class="clearbtn" type="reset" value="CLEAR">
			<input class="submitbtn" type="submit" value="SUBMIT" id="submit" onclick="return validateGroup();">	
		</div>
	</form:form>
</body>
</html>