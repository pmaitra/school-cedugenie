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
<meta name="description" content="Page To Add leave Category" />
<meta name="keywords" content="Page To Add leave Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Leave Category</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/backoffice/leaveCategory.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script type="text/javascript" src="/icam/js/backoffice/leaveTypeAddDelete.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/leaveCategory.js"></script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Assign Leave Category
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div> 
	
	<form name="leaveCategoryForm">
	<table id="categoryDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				Leave Type ::
			</th>
			<td>
				<input type="text" id="leaveType" name="leaveType" value="" class="textfield1"  />
			</td>
			<td>					
				<img src="/icam/images/minus_icon.png" onclick="deleteRow(this);">	
			</td>
		</tr>
		<tr>
			<td colspan="5"><input class="addbtn" type="button"  id="add" name="add" onclick="addRow();"/></td>
		</tr>
	</table>
	<div class="btnsarea01">
		<input type="submit" id="submit" name="submit" value="Submit" onclick="submitAction();" class="submitbtn"/>
	</div>	
	<table id="previousCategoryDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="2">
				:: Previous Leave Type ::
			</th>			
		</tr>
		</thead>
		
	</table>
	<div class="btnsarea01">
		<input type="button" id="editPre" name="editPre" value="Edit"  class="editbtn" onclick="showenable();"/>
		<input type="submit" id="deletePre" name="deletePre" value="Delete" disabled="disabled"  onclick="deleteAction(); deleteRows();" class="clearbtn"/>
		<input type="submit" id="submitPre" name="submitPre" value="Submit" disabled="disabled"  class="submitbtn"/>
	</div>
	<br>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<br>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>	
	<input type="hidden" id="hiddenleaveType" name="hiddenleaveType" value="" />
</form>
</body>
</html>