<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Standard Pay Scales" />
<meta name="keywords" content="Create Gate Pass" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Gate Pass</title>
<link rel="stylesheet" href="/icam/css/inventory/createGatePass.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/createGatePass.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Gate Pass
		</h1>
	</div>
					
	<form:form name="submitGatePass" action="submitGatePass.html" method="POST">		
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
		<div>
			<c:if test="${submitResponse ne null}">				
				<c:if test="${submitResponse eq 'Success'}">
					<div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Gate Pass Successfully Created</span>	
					</div>
				</c:if>
				<c:if test="${submitResponse eq 'Fail'}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg" style="visibility:visible;">Gate Pass Creation Failed</span>	
					</div>
				</c:if>		
			</c:if>
		</div>
				
		<div id="divGatePass">						
			<table id="tableGatePass" cellspacing="0" cellpadding="0" class="midsec1" border="1">					
				<tr>					
					<th>
						Item
					</th>
					<th>
						Quantity
					</th>
					<th>
						Remarks
					</th>
					<th>
					</th>
				</tr>
				<tr>					
					<td>					
						<input type="text" class="textfield1" name = "itemName" id="itemName0">
					</td>					
					<td>
						<input type="text" class="textfield1" name = "quantity" id="quantity0"  >
					</td>
					<td>
						<input type="text" class="textfield1" name = "remarks" id="remarks0" >	
					</td>
					<td>
						
					</td>
				</tr>	
				<tr>
					<td colspan="7">
						<input type="button" class="addbtn" onclick="addrows();">
					</td>
				</tr>
			</table>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
				<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateGatePass();"/>
				<input type="reset" class="clearbtn" value="clear" />				
			</div>
			<input type="hidden" id ="hidVal" name="hidVal" value=""/>
		</div>			
	</form:form>
	
</body>
</html>