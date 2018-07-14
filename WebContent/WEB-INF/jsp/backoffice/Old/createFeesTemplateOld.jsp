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
<title>Create Fees Template</title>

<link rel="stylesheet" href="/icam/css/backoffice/createFeesTemplate.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createFeesTemplate.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Fees Template
	</h1>
</div>
	<c:choose>
		<c:when test="${feesList eq null || empty feesList || socialCategoryList eq null || empty socialCategoryList || standardList eq null || empty standardList}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<c:if test="${feesList eq null || empty feesList}">
						<span id="errormsg">Fees Not Created Yet</span>	
					</c:if>
					<c:if test="${socialCategoryList eq null || empty socialCategoryList}">
						<span id="errormsg">Social Category Not Created Yet</span>	
					</c:if>
					<c:if test="${standardList eq null || empty standardList}">
						<span id="errormsg">Standard Not Created Yet</span>	
					</c:if>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form name="feesTemplateForm" id="feesTemplateForm" method="post" action="addFeesTemplate.html">
				<table id="feesDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Template Name ::
						</th>
						<td>
							<input type="text" id="templateName" name="templateName" class="textfield1"  />
						</td>
					</tr>
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							<select id="standard" name="standard" size="1" class="defaultselect">
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							Applied ::
						</th>
						<td>
							<input type="radio" name="applied" value="TRUE" checked="checked" />Yes &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="applied" value="FALSE" /> No
						</td>
					</tr>
				</table>
				<table id="templateCharge" class="midsec1" cellspacing="0" cellpadding="0">
					<tr>
						<th>FEES</th>
						<c:forEach var="socialCategory" items="${socialCategoryList}" varStatus="i">
							<th>
								${socialCategory.socialCategoryName}
								<input type="hidden" name="socialCategoryNames" value="${socialCategory.socialCategoryCode}">
							</th>
						</c:forEach>
					</tr>
					<c:forEach var="fees" items="${feesList}" varStatus="i">
						<tr>
							<td>
								${fees.feesName}
								<input type="hidden" name="feesNames" value="${fees.feesCode}">
							</td>
							<c:forEach var="socialCategory" items="${socialCategoryList}" varStatus="i">
								<td><input type="text" id="" name="${fees.feesCode}${socialCategory.socialCategoryCode}" value="0.00" class="textfield2"  /></td>
							</c:forEach>
						</tr>
					</c:forEach>			
				</table>
				<c:forEach var="feesTemplate" items="${feesTemplateList}" varStatus="i">
					<input type="hidden" name="oldFeesTemplateName" value="${feesTemplate.templateCode}">
				</c:forEach>
				<div class="btnsarea01">
					<input type="submit" value="Submit" class="submitbtn" onclick="return validate();"/>
				</div>
			</form>
		</c:otherwise>	
	</c:choose>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>