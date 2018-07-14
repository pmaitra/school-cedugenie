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
<title>Create Ledger</title>
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
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Ledger
	</h1>
</div>
	<form:form name="" action="createLedger.html" method="POST" >
	
		<c:forEach var="gfl" items="${parentLedgerList}">
			<input type="hidden" name="oldLedgerNames" value="${gfl.ledgerName}">
		</c:forEach>
		
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Ledger Name :: </th>
				<td><input type="text" name="ledgerName" id="ledgerName" class="textfield" onblur="checkLedgerName(this);" ></td>
			</tr>
			<tr>
				<th>Parent Ledger :: </th>
				<td>
					<select name="parentLedgerCode" id="parentLedgerCode" class="defaultdropdown" >
						<option value="">Select</option>
						<c:if test="${parentLedgerList ne null}">
							<c:forEach var="l" items="${parentLedgerList}">
								<option value="${l.ledgerCode}">${l.ledgerName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<th>Parent  Group:: </th>
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
				<th>Opening Balance :: </th>
				<td>
					<select name="openingDrCr" class="defaultdropdown" style="width: 50px;">
						<option value="DR">Dr</option>
						<option value="CR">Cr</option>
					</select>
					<input type="text" name="openingBal" id="openingBal" class="textfield" >
				</td>
			</tr>
		</table>
		
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input name="" class="clearbtn" type="reset" value="CLEAR">
			<input name="" class="greenbtn" type="submit" value="SUBMIT" id="submit" onclick="return validateLedger();">	
		</div>
	</form:form>
</body>
</html>