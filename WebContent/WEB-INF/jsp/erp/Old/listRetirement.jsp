<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listretirementPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Retirement" />
<meta name="keywords" content="Create List Retirement" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Retirement</title>
<link rel="stylesheet" href="/icam/css/erp/listRetirement.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/icam/css/common/pagination.css">
<script src= "/icam/js/common/validateSearch.js" type="text/javascript"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'retList', checkbox[i]);
		}
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'retList', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'retList', checkbox[i]);
			}
		}
		
	}
</script>
</head>
<script>
function validate(){
	var count=0;
	var radio=document.getElementsByTagName("input");
	for(var i=1;i<radio.length;i++){		
		if(radio[i].type=="radio"){			
			if(radio[i].checked)			
				count=1;
			
		}
	}
	if(count==0){
		document.getElementById("warningbox").style.visibility='visible';
	 	document.getElementById("warningbox").innerHTML="Please Select An Option";
		return false;
	}
	else
		return true;
}
</script>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Teacher Interview List
		</h1>
	</div>
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="User Id" onclick="ShowHideField('User Id', 'retList', this)" checked="checked" />
			<label for="User Id">User Id</label><br>
	    <input type="checkbox" class="listShowHide" value="Name" onclick="ShowHideField('Name', 'retList', this)" checked="checked" />
			<label for="Name">Name</label><br>
	    <input type="checkbox" class="listShowHide" value="Designation" onclick="ShowHideField('Designation', 'retList', this)" checked="checked" />
			<label for="Designation">Designation</label><br>
	    <input type="checkbox" class="listShowHide" value="Join Date" onclick="ShowHideField('Join Date', 'retList', this)" checked="checked" />
			<label for="Join Date">Join Date</label><br>
	    <input type="checkbox" class="listShowHide" value="Retirement Date" onclick="ShowHideField('Retirement Date', 'retList', this)" checked="checked" />
			<label for="Retirement Date">Retirement Date</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder eq null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Retirement List Found</span>	
		</div>								
	</c:when>
<c:otherwise>			
<form:form method="POST" action="editRetirement.html">
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>	
			<td>
				<select name="query" id="query" class="defaultselect">
					<option value="Select" selected>Please Select...</option>
					<option value="UserId">User Id</option>
					<option value="Name">Name</option>	
					<option value="Designation">Designation</option>			
					<option value="DateOfJoin">Date Of Join</option>
					<option value="DateOfRetirement">Date Of Retirement</option>				
				</select>
			</td>		
			<td>
				<input type="text" class="textfield2" name="data"  id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}else{this.style.color='blue';}" />
			</td>
			<td>	
				<input type="submit" class="editbtn" id="submitSearch" name="submitSearch" value="Search" onclick="return validateSearch('query','data','warningbox','warningmsg');"  />	
			</td>
		</tr>
	</table>
</form:form>
	<table id="retList" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Designation</th>
			<th>Join Date</th>
			<th>Retirement Date</th>
			<th>Mode Of Retirement</th>
		</tr>
		<c:forEach var="staff" items="${pagedListHolder.pageList}"> 
			<tr>
		       <td>${staff.employeeCode}</td>
			   <td><c:out value="${staff.resource.firstName} ${staff.resource.middleName} ${staff.resource.lastName}"/></td>
			   <td>${staff.designation.designationName}</td>
			   <td>${staff.dateOfJoining}</td>		
			   <td>${staff.dateOfRetirement}</td>
			   <td>${staff.modeOfRetirement}</td>
			</tr>
		</c:forEach>
			<tr>
				 <td id="toolbar" colspan="7"><c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
		
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
			<c:if test="${staffRetirementList[0].status eq 'success'}">
				<div class="successbox" id="successbox"  style="visibility:visible;">
					<span id="successmsg">Data Successfully updated</span>	
				</div>				
			</c:if>
			<c:if test="${staffRetirementList[0].status eq 'fail'}">
				<div class="errorbox" id="errorbox"  style="visibility:visible;">
					<span id="errormsg">Update failed</span>	
				</div>				
			</c:if>	
	</div>		

</c:otherwise>
</c:choose>
</body>
</html>