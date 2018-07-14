<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/lendingHistoryPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
	 <c:param name="strBookCode" value="${strBookCode}"/>
</c:url>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Book Profile" />
<meta name="keywords" content="View Book Profile" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Book Profile</title>

<link rel="stylesheet" href="/sms/css/library/viewBookProfile.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/viewBookProfile.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Book Profile
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	
	<input type="checkbox" class="listShowHide" value="Type" onclick="ShowHideField('Type', 'vbp', this)" checked="checked"/>
	<label for="Type">Type</label><br>
	
    <input type="checkbox" class="listShowHide" value="Code" onclick="ShowHideField('Code', 'vbp', this)" checked="checked"/>
	<label for="Code">Code</label><br>
	
	<input type="checkbox" class="listShowHide" value="Name" onclick="ShowHideField('Name', 'vbp', this)" checked="checked" />
	<label for="Name">Name</label><br>
	
	<input type="checkbox" class="listShowHide" value="Edition" onclick="ShowHideField('Edition', 'vbp', this)" checked="checked" />
	<label for="Edition">Edition</label><br>
	
	<input type="checkbox" class="listShowHide" value="Author" onclick="ShowHideField('Author', 'vbp', this)" checked="checked" />
	<label for="Author">Author</label><br>
	
	<input type="checkbox" class="listShowHide" value="Publisher" onclick="ShowHideField('Publisher', 'vbp', this)" checked="checked" />
	<label for="Publisher">Author</label><br>
	
</div>
<c:choose>
	<c:when test="${BookProfile==null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg">No Books Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>		
		<div id="viewBookProfile" >
		
			<input type="hidden" name="strBookCode" value="${strBookCode}">
		
			<form:form method="POST" id="sicontents" name="sicontents" action="">
				<table id="sitable" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<td colspan="4"> :: Book Profile :: </td>
					</tr>
				</table>
				<table id="vbp" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th>Type</th>
						<th>Code</th>
						<th>Name</th>
						<th>Edition</th>
						<th>Author</th>
						<th>Publisher</th>
					</tr>
					<tr>
					
						<td>
							${BookProfile.bookType}
						</td>
						<td>
							<c:out value="${BookProfile.bookCode}"/> 
						</td>
						<td>
							<c:out value="${BookProfile.bookName}"/>
						</td>
						<td>
						 	${BookProfile.bookEdition}
						</td>
						<td>
							<c:if test="${BookProfile.bookAuthorList ne null}">
								<c:forEach var="bookAuthor" items="${BookProfile.bookAuthorList}">
									<c:out value="${bookAuthor.authorFullName}"></c:out> ; 
								</c:forEach>
							</c:if>
						</td>
						<td>
						 	${BookProfile.bookPublisher.bookPublisherName}
						</td>
						
					</tr>
				</table>
				<c:choose>		
					<c:when test="${pagedListHolder!=null}">						
						<table id="lhis" cellspacing="0" cellpadding="0" class="midsec1">
						<tr>
								<th colspan="5"> :: Lending History :: </th>
							</tr>
							<tr>
								<th>Lender</th>
								<th>Id</th>
								<th>Rating</th>
								<th>Comments</th>
								<th>Details</th>
							</tr>
							 <c:forEach var="lendingHistory" items="${pagedListHolder.pageList}">
								<tr>
									<td>
										<c:out value="${lendingHistory.bookIssuedTo.name}"/>
									</td>
									<td>
										<c:out value="${lendingHistory.bookIssuedTo.userId}"/><input type="hidden" name="userId" readonly="readonly" value="${lendingHistory.bookIssuedTo.userId}"/>
									</td>
									<td>
										<c:out value="${lendingHistory.bookRating.bookRatingDesc}"/><input type="hidden" name="bookCode" readonly="readonly" value="${BookProfile.bookCode}"/>
									</td>
									<td>
										<c:out value="${lendingHistory.bookRating.bookRatingComments}"/>
									</td>
									<td>
										<button type="button" name="dateDetails" class="clearbtn">Details</button>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
							</tr>
						</table>
					</c:when>
					<c:otherwise>
						<div class="btnsarea01" style="visibility: visible;">
							<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
								<span id="infomsg">No Lending History Available For This Book</span>	
							</div>
						</div>
					</c:otherwise>
				</c:choose><br/>
				<input type="button" class=submitbtn value="BACK" onclick="window.location='viewBookStock.html' ">
			</form:form>
		</div>
		<div id="popup_box"> 
			<table id="dateTable" cellspacing="0" cellpadding="0" class="midsec">
				<thead>
				</thead>	
				<tbody>
				</tbody>
			</table>
			<center>
			   <a id="popupBoxNo"><button type=button id="ok" class="editbtn" >OK</button></a>
			</center>
		</div>
	</c:otherwise>
</c:choose>
</body>
</html>