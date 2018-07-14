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
<meta name="description" content="Page to Service Type" />
<meta name="keywords" content="Service Type" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Service Type</title>

<link rel="stylesheet" href="/icam/css/ticketing/serviceType.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/ticketing/serviceType.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Service Type
	</h1>
</div>
<form:form method="POST" id="serviceType" name="serviceType" action="serviceType.html" >	
	<table cellspacing="0" cellpadding="0" class="midsec" id="addServiceType">
		<tr>
			<th>
				Service Category<img class="required" src="/icam/images/required.gif" alt="Required">
			</th>
			<td>
				<input type="text" name="ticketServiceName" id="ticketServiceName" class="textfield1">
			</td>
		</tr>
		
		<tr>
			<th>
				Department<img class="required" src="/icam/images/required.gif" alt="Required">
			</th>			
			<td>
				<select name="department.departmentCode" id="department"  class="defaultselect">
				  <option value="">Select...</option>
					<c:if test="${departmentFromDB != null}">
					<c:forEach var="dept" items="${departmentFromDB}" >
						<option value="${dept.departmentCode}">${dept.departmentName}</option>
					</c:forEach>
					</c:if>
				</select> 
			</td>	
		</tr>
		
		<tr>
			<th>
				Service Owner<img class="required" src="/icam/images/required.gif" alt="Required">
			</th>			
			<td>
				<select name="ticketServiceOner.userId" id="userId"  class="defaultselect" disabled="disabled">
				  <option value="">Select...</option>
				</select> 
			</td>	
		</tr>		
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="cwarningbox" >
			<span id="cwarningmsg"></span>	
		</div>		
		<input type="submit" class="submitbtn" value="SUBMIT" onclick="return checkServiceName();"/>
	</div>
</form:form>
<p/><br/>
<form:form id="editServiceType" name="editServiceType" action="updateServicetype.html" method="post">
	<c:choose>
		<c:when test="${serviceTypeList eq null || serviceTypeList.size() eq 0}">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Service Type created Yet. Please Create New.</span>	
			</div>						
		</c:when>
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1" id="listServiceType">
		<tr>
			<th><input type="radio" disabled="disabled"/></th>
			<th>Service Name</th>
			<th>Service Owner</th>		
			<th>Department</th>			
		<tr>
		<c:forEach var="serviceTypeList" items="${serviceTypeList}" varStatus="rowCount">
			<tr>
				<td><input type="radio" name="radio" value="${serviceTypeList.ticketServiceName}" /></td>
				<td><input type="text" name="${serviceTypeList.ticketServiceName}" value="${serviceTypeList.ticketServiceName}" readonly="readonly" class="textfield1"/></td>
				<td>
					<select id="serviceName${rowCount.index}" name="${serviceTypeList.ticketServiceName}" disabled="disabled" class="defaultselect">
						<c:forEach var="userIdList" items="${serviceTypeList.resourceList}" >
							<c:if test="${userIdList.userId eq serviceTypeList.ticketServiceOner.userId}">
								<option value="${userIdList.userId}" >${userIdList.userId}</option>
							</c:if>
						</c:forEach>
						 <c:forEach var="userIdList" items="${serviceTypeList.resourceList}" >
							<c:if test="${userIdList.userId ne serviceTypeList.ticketServiceOner.userId}">
								<option value="${userIdList.userId}">${userIdList.userId}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>				
				<td>
					<select onchange="check(this);" class="serviceListDepartment" id="serviceListDepartment${rowCount.index}" name="${serviceTypeList.ticketServiceName}" disabled="disabled">							
						<c:forEach var="department" items="${departmentFromDB}" >
							<c:if test="${department.departmentCode eq serviceTypeList.department.departmentCode}">
								<option value="${department.departmentCode}" >${department.departmentName}</option>
							</c:if>
						</c:forEach>
						
						 <c:forEach var="department" items="${departmentFromDB}" >
							<c:if test="${department.departmentCode ne serviceTypeList.department.departmentCode}">
								<option value="${department.departmentCode}" >${department.departmentName}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				
			</tr>	
		</c:forEach>	
	</table>

	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="button" id="edit" class="editbtn" value="Edit" onclick="editRow();" >
		<input type="submit" name="delete" id="deleteDB" value="Delete" class="submitbtn" onclick="return verifyToDelete();" >	
		<input type="submit" name="update" id="updateDB" class="clearbtn" value="Update" onclick="return checkNameToUpdate();" disabled="disabled">
	</div>
</c:otherwise>
</c:choose>
</form:form>
</body>
</html>