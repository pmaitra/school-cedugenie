<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listTaskHistoryPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Pending Task" />
<meta name="keywords" content="List Pending Task" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Task History</title>
<link rel="stylesheet" href="/icam/css/erp/listTaskHistory.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'taskHist', checkbox[i]);
		}
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'taskHist', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'taskHist', checkbox[i]);
			}
		}
		
	}
</script>
</head>
<body>
	<div class="ttlarea">
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Task History
		</h1>
	</div>
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Serial No." onclick="ShowHideField('Serial No.', 'taskHist', this)" checked="checked" />
		<label for="Serial No.">Serial No.</label><br>
		<input type="checkbox" class="listShowHide" value="User ID" onclick="ShowHideField('User ID', 'taskHist', this)" checked="checked" />
		<label for="User ID">User ID</label><br>
		<input type="checkbox" class="listShowHide" value="Task Title" onclick="ShowHideField('Task Title', 'taskHist', this)" checked="checked" />
		<label for="Task Title">Task Title</label><br>
		<input type="checkbox" class="listShowHide" value="Creation Date" onclick="ShowHideField('Creation Date', 'taskHist', this)" checked="checked" />
		<label for="Creation Date">Creation Date</label><br>
		<input type="checkbox" class="listShowHide" value="From" onclick="ShowHideField('From', 'taskHist', this)" checked="checked" />
		<label for="From">From</label><br>
		<input type="checkbox" class="listShowHide" value="To" onclick="ShowHideField('To', 'taskHist', this)" checked="checked" />
		<label for="To">To</label><br>
		<input type="checkbox" class="listShowHide" value="Remarks" onclick="ShowHideField('Remarks', 'taskHist', this)" checked="checked" />
		<label for="Remarks">Remarks</label><br>
		<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'taskHist', this)" checked="checked" />
		<label for="Status">Status</label><br>
	</div>	
	<c:choose>
		<c:when test="${empty pagedListHolder}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Task History is available</span>	
		</div>					
		</c:when>
		<c:otherwise>

			
			
			<table id="taskHist" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>From Date</th>
					<th>To Date</th>
					<th>No Of Days</th>
					<th>Description</th>
					<th>Details</th>							
				</tr>
				<c:forEach var="task" items="${pagedListHolder.pageList}">
				<tr>
					<td>${task.startDate}</td>
					<td>${task.endDate}</td>
					<td>${task.numberofDayRequestedFor}</td>
					<td>${task.taskDesc}</td>
					<td>
						<c:choose>
							<c:when test="${task.leaveList == null || empty task.leaveList}">
								<div class="btnsarea01" style="visibility: visible;">
									<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
										<span id="infomsg">Levae Type Not Found.</span>	
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:forEach var="leave" items="${task.leaveList}">
									<p style="margin:0em;">${leave.leaveType}(${leave.totalRequestedLeave})</p>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>						
				</c:forEach>
				<tr>
					<td colspan="8" id="toolbar">Displaying ${first} to ${last} of ${total} items<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
			</table>
		</c:otherwise>		
	</c:choose>	
</body>
</html>