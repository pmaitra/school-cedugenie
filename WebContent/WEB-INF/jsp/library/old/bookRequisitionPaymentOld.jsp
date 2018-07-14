<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Requisition</title>
		<link href = "css/welcome.css" rel="stylesheet" />
		<script src= "js/image.js" type="text/javascript"></script>
		<script src= "js/iframeheight.js" type="text/javascript"></script>
		<script src= "js/addDeleteBookRequisition.js" type="text/javascript"></script>
		<script src= "js/validateBookRequisition.js" type="text/javascript"></script>
		<script>
		 function validateForm(){
			 document.getElementById("error").innerHTML="";
			 var totalPrice = document.getElementById("totalPrice").value.replace(/^\s+|\s+$/g,'');
			
			if(totalPrice==""){
				document.getElementById("error").innerHTML="Please Enter Receive Amount";
				return false;
			}
			if(isNaN(totalPrice)){
				document.getElementById("error").innerHTML="Receive Amount should numeric";
				return false;
			}
			if(totalPrice<=0){
				document.getElementById("error").innerHTML="Receive Amount should be greater than Zero ";
				return false;
			}
			if(totalPrice>document.getElementById("amount").value){
				document.getElementById("error").innerHTML="Receive Amount should not be greater than Total Amount ";
				return false;
			}
			var x=document.getElementsByName("paymentMode");
			var a=0;
			for(var i=0;i<x.length;i++){
				if(x[i].checked){
					a=1;
				}
			}
			if(a==0){ 
			 document.getElementById("error").innerHTML="Please Select Payment Mode";
			 return false;
			}
		return true;
		 }
		</script>
</head>
<body>	
	<c:set var="bookRequisitionTotalAmount" value="0" scope="page" />
	 <form:form method="POST" id="" name=""  action="paymentBookRequisition.html" onSubmit="return validateForm()">
		<table id="booktable" style="margin-left: 10%;" border="1">				
			<tr><th>Requisition ID</th><th>::</th><td><input readonly="readonly" type="text" id="bookRequisitionCode" name="bookRequisitionCode" value="${bookRequisition.bookRequisitionCode}"/></td></tr>
			<tr><th>Vendor Name</th><th>::</th>
				<td><input readonly="readonly" type="text" id="vendorName" name="vendor.vendorName" value="${bookRequisition.vendor.vendorName}"/></td>
			</tr>		
			<tr><td colspan="5">
			<table id="dataTable" width="350px" border="1">
			<tr>
			<th>Book Name</th>		
			<th>Author</th>
			<th>Edition</th>
			<th>Publisher</th>		
			<th>Quantity</th>
			<th>Rate</th>
			<th>Total Price</th>
			</tr>
			
			<c:forEach var="bookRequisitionDetails" items="${bookRequisition.bookRequisitionDetailsList}">
			
			<tr>
				<td> <input type="text" name="bookName" value="${bookRequisitionDetails.bookName}" readonly="readonly"/> </td>
				<td> <input type="text" name="bookAuthor"  value="${bookRequisitionDetails.bookAuthor}" readonly="readonly"/></td>
				<td> <input type="text" name="bookEdition"  value="${bookRequisitionDetails.bookEdition}" readonly="readonly"/> </td>
				<td> <input type="text" name="bookPublisher"  value="${bookRequisitionDetails.bookPublisher}" readonly="readonly"/></td>
				<td> <input type="text" name="numberOfBooksRequisitioned" value="${bookRequisitionDetails.numberOfBooksRequisitioned}"  readonly="readonly"/></td>
				<td> <input type="text" name="bookPriceRate" value="${bookRequisitionDetails.rate}"  readonly="readonly"/></td>
				<td> <input type="text" name="bookTotalPrice" value="${bookRequisitionDetails.totalPrice}"  readonly="readonly"/></td>
			</tr>
			<c:set var="bookRequisitionTotalAmount" value="${bookRequisitionTotalAmount+bookRequisitionDetails.totalPrice}" scope="page" />	
			</c:forEach>
			<tr>
				<th>Total Amount: </th>
				<td>
					<input type="text" name="amount" id="amount" value="${bookRequisitionTotalAmount}" readonly="readonly"/>
				</td>
				<th>Receive Amount: </th>
				<td>
					<c:choose>
						<c:when test="${bookRequisition.totalPrice eq 0}">
							<input type="text" name="totalPrice" id="totalPrice" value=" " />
						</c:when>
						<c:otherwise>
							<input type="text" name="totalPrice" id="totalPrice" value="${bookRequisition.totalPrice} " readonly="readonly"/>
						</c:otherwise>
					</c:choose>
					
				</td>
				
					<th>Payment Mode: </th>
				<td>
					<input type="radio" name="paymentMode" value="CASH">CASH
					<input type="radio" name="paymentMode" value="BANK">CHEQUE
				</td>
				
		</table>
		</td></tr>
		<tr><td colspan="3">
		<c:if test="${bookRequisition.totalPrice eq 0}">
			<c:choose>
				<c:when test="${bookRequisition.totalNumberOfBooksRequisitioned gt bookRequisition.totalNumberOfBooksReceived}">
					<h2 style="color: red;">Payment should not be done because all books are not received. </h2>
				</c:when>
				<c:otherwise>
					<button type="reset" style="border: 0; background: transparent" onmouseover="change('clear','images/clear.png');" onmouseout="change1('clear','images/clear1.png');"  >
					<img id="clear" style="border-style: none; width:80px; height:40px; " src="images/clear1.png" alt="Clear" />	
					</button>
			
					<button type="submit" style="border: 0; background: transparent"  value="SUBMIT" onmouseover="change('submit','images/submit.png');" onmouseout="change1('submit','images/submit1.png');" >
					<img id="submit" style="border-style: none; width:80px; height:40px;  " src="images/submit1.png" alt="" />
					</button>				
				</c:otherwise>
			</c:choose>
		</c:if>
		</td></tr>
		</table>		
	</form:form>
	<span id="error" style="margin-left: 25%"></span>
</body>
</html>