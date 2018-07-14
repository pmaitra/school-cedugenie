<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8"/>
		<title>::Book Stock::</title>
		<link href="./css/welcome.css" rel="stylesheet" type="text/css">
		<script src= "js/image.js" type="text/javascript"></script>
		<script src= "js/searchval.js" type="text/javascript"></script>
		<script src= "js/iframeheight.js" type="text/javascript"></script>
	</head>
		<body>
		<c:choose>
			<c:when test="${viewBookRequisitionList==null}">
				<h3>Data not found......</h3>
			</c:when>
		<c:otherwise>
			<div id="requisitionDetails" name="requisitionDetails">
					
							<table id="sfltable" border="1">							
								<tr>
									<th>Requisition ID</th>
									<th>Open Date</th>
									<th>Closed Date</th>
								</tr>
								<tr>
									<td>
										${viewBookRequisitionList.bookRequisitionCode}
									</td>
									<td>
										${viewBookRequisitionList.bookRequisitionOpenDate}
									</td> 
									<td>
										${viewBookRequisitionList.bookRequisitionCloseDate}
									</td>
								</tr>
							</table>
							
							 <c:forEach var="bookRequisitionDetails" items="${viewBookRequisitionList.bookRequisitionDetailsList}">
							 <table id="sfltable" border="1">
								<tr>									
									<th>Book Name</th><td><input readonly="readonly" id="bookName" name="bookName"  value="${bookRequisitionDetails.bookName}">  </td>
									<th>Total Order</th><td><input readonly="readonly" id="totalOrder" name="totalOrder"  value="${bookRequisitionDetails.numberOfBooksRequisitioned}"></td>
								</tr>
								<tr>
									<th colspan="1">Quantity Received</th>
									<th colspan="1" align="left">Receiving Date</th>
								</tr>								
								<c:forEach var="bookRequisitionReceivedDates" items="${bookRequisitionDetails.bookRequisitionReceivedDatesList}">
								<tr>									
									<td align="center">
										${bookRequisitionReceivedDates.numberOfBooksReceived}
									</td>
									<td colspan="1" align="center">
										${bookRequisitionReceivedDates.dateBooksReceived}
									</td>
									<td>
										<input type="hidden" name="bookRequisitionReceivedDates" value="${bookRequisitionReceivedDates.bookRequisitionReceivedDatesId}">
										<c:choose>
											<c:when test="${bookRequisitionReceivedDates.addedToStock eq 'true'}">
												<td align="center">Add To Catalog</td>
											</c:when>	
											<c:otherwise>
												<td align="center"><a href="addToCatalog.html?bookName=${bookRequisitionDetails.bookName}&requisitionCode=${viewBookRequisitionList.bookRequisitionCode}&receivedCopy=${bookRequisitionReceivedDates.numberOfBooksReceived}&receivedCode=${bookRequisitionReceivedDates.bookRequisitionReceivedDatesId}">Add To Catalog</a></td>
											</c:otherwise>
										</c:choose>
									</td>
									
								</tr>								
								</c:forEach> 
							</table>
							</c:forEach>  
							
								
									
										<button  type="button" style="float:left; margin-left:25%; border: 0; background: transparent"  onmouseover="change('back','images/back.png');" onmouseout="change1('back','images/back1.png');"  onclick="window.location='viewRequisition.html' ">
											<img id="back" style="float:left; margin-left:25%; border-style: none; width:80px; height:40px; " src="images/back1.png" alt="Back" />
										</button>	
									
							
						<table id="sfltable">
						</table>
								<span id="snierror" name="snierror"></span>
						
					
							</c:otherwise>
		</c:choose>
		</body>
</html>
