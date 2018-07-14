<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/issuedBookListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Book" />
<meta name="keywords" content="Add Book" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Return Book Details</title>

<link rel="stylesheet" href="/sms/css/library/returnBookDetails.css" />
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
<script type="text/javascript" src="/sms/js/library/returnBookDetails.js"></script>
</head>
<body>

<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Return Book Details		
	</h1>
</div>
<c:set var="count" value="${count + 1}" scope="page"/>
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>	
		<input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'rbdet', this)" checked="checked" />
		<label for="Book Code">Book Code</label><br>
		<input type="checkbox" class="listShowHide" value="Book ID" onclick="ShowHideField('Book ID', 'rbdet', this)" />
		<label for="Book ID">Book ID</label><br>
		<input type="checkbox" class="listShowHide" value="Issued Date" onclick="ShowHideField('Issued Date', 'rbdet', this)"  checked="checked"/>
		<label for="Issued Date">Issued Date</label><br>
		<input type="checkbox" class="listShowHide" value="Expected Return Date" onclick="ShowHideField('Expected Return Date', 'rbdet', this)"  checked="checked"/>
		<label for="Expected Return Date">Expected Return Date</label><br>
		<input type="checkbox" class="listShowHide" value="Actual Return Date" onclick="ShowHideField('Actual Return Date', 'rbdet', this)" />
		<label for="Actual Return Date">Actual Return Date</label><br>
		<input type="checkbox" class="listShowHide" value="Extended Days" onclick="ShowHideField('Extended Days', 'rbdet', this)" />
		<label for="Extended Days">Extended Days</label><br>
		<input type="checkbox" class="listShowHide" value="Penalty" onclick="ShowHideField('Penalty', 'rbdet', this)"  checked="checked"/>
		<label for="Penalty">Penalty</label><br>
		<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'rbdet', this)" checked="checked" />
		<label for="Status">Status</label><br>
	</div>
		
							
	<c:set var="count" value="0" scope="page" />
	<table id="login-box" cellspacing="0" cellpadding="0" class="midsec">
	<form:form method="post" class="signin" name="submitPopup">
		
			<tr>
				<th>Book Code</th>
				<td>
					<input id="bookCode1" name="bookCode" class="textfield" type="text" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>Comment</th>
				<td>
					<textarea cols="23" rows="1" class="txtarea" id="comment" name="comment" placeholder="Comment"> </textarea>
				</td>
			</tr>
			<tr>
				<th>Rating</th>
				<td>
					<select name='rating' id="rating" class="defaultselect">
						<option value="">Rating...</option>	
						<option value="0">0</option>
						<option value="1">1</option> 
						<option value="2">2</option> 
						<option value="3">3</option> 
						<option value="4">4</option> 
						<option value="5">5</option> 
						<option value="6">6</option> 
						<option value="7">7</option> 
						<option value="8">8</option> 
						<option value="9">9</option> 
						<option value="10">10</option> 
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input value="Cancel" class="editbtn" id="sub1" type="submit"  onclick="return hidePopup(); " />
					<input value="Submit" class="submitbtn" id="sub1" type="submit"  onclick="return submitPopupValues(); " />
				</td>
			</tr>
	</form:form>
	</table>
	
	<form:form method="POST" id="sflcontents" name="userIdForReturnBook" action="submitUserIdForReturnBook.html">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>User ID<img class="required" src="/sms/images/required.gif" alt="Required"></th>
				<td>
					<input type="text" class="textfield" name="userId" id="userId" value=""/>
				</td>
				<td>
				  	<input class="submitbtn" type="submit" value="SUBMIT" >
				</td>
			</tr>
		</table>
	</form:form>
	
	
<%-- 	<c:if test="${pagedListHolder==null}"> --%>
<!-- 		<div class="btnsarea01"> -->
<!-- 			<div class="errorbox" id="errorbox"  style="visibility:visible;"> -->
<!-- 				<span id="errormsg">Books Not Available</span>	 -->
<!-- 			</div> -->
<!-- 		</div> -->
<%-- 	</c:if> --%>
	<c:choose>
		<c:when test="${pagedListHolder == null}" >
			<div class="btnsarea01">
				<div class="infomsgbox" id="infomsgbox"  style="visibility: visible;">
					<span id="infomsg"style="visibility: visible;">No books available</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
				
			<form:form method="POST" id="sflcontents" name="bookAllocation" action = "submitReturnBookDetails.html">
				<table cellspacing="0" cellpadding="0" class="midsec">
					<tr>
						<th>
							Issued Book Details For User ID :: <i>${pagedListHolder.pageList[0].bookIssuedTo.userId}</i>
							<input type="hidden" name="userId" value="${pagedListHolder.pageList[0].bookIssuedTo.userId}"/>
						</th>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" class="midsec1" id="rbdet">
					<tr>
							<th>Book Allocation Code</th>	
							<th>Book Code</th>						
							<th>Book ID</th>
							<th>Issued Date</th>
							<th>Expected Return Date</th>
							<th>Actual Return Date</th>	
							<th>Extended Days</th>		
							<th>Penalty</th>
							<th>Status</th>
							<th>Rating</th>
					</tr>
				<c:forEach var="bookAllocation" items="${pagedListHolder.pageList}">					
						<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
							<tr>
								<td>
									${bookAllocation.bookAllocationCode}
								</td>	
								<td>
									${bookAllocationDetails.bookCode}
									<input type="hidden" id="bookCode" name="bookCode" value="${bookAllocationDetails.bookCode}"/>
								</td>				
								<td>
									${bookAllocationDetails.bookId}
								</td>
								<td>
									${bookAllocationDetails.bookIssueDate}
								</td>
								<td>
									${bookAllocationDetails.bookReturnDate}
								</td>
								<td>
									${bookAllocationDetails.actualReturnDate}
								</td>
								<td>
									${bookAllocationDetails.noOfDaysExtend}
								</td>						
								<td>
									${bookAllocationDetails.penalty}
								</td>
								<td>
									${bookAllocationDetails.status}
								</td>
								<c:choose>
									<c:when test="${bookAllocationDetails.actualReturnDate != null}">
										<td>
											<input type="checkbox" name="allocatedBookId" disabled="disabled" />
											Rating
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<input type="checkbox" name="allocatedBookId" value="${bookAllocationDetails.bookId}#${bookAllocation.bookAllocationCode}"/>
											<a href="#" name="${bookAllocationDetails.bookCode}" onclick="showHide(this);" class="returnBookDetails-popupWindow" >Rating</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
				</c:forEach>
				
					<tr>
						<td colspan="9" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
					</tr>
					 
				</table>
				<input type="hidden" id="cnt" value="${count}"/>
				<input type="hidden" name="strRating" id="strRating" />
				<input type="hidden" name="strComment" id="strComment" />
				<input type="hidden" name="strBookCode" id="strBookCode" />
				<input class="submitbtn" id="submitButton" type="submit" value="SUBMIT" >
			</form:form>
		</c:otherwise>
	</c:choose>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</div>
</body>
