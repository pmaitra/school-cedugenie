<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
	
<head>

	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Standard Subject Mapping</title>
	<%@ include file="/include/include.jsp" %>
	
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
</head>
<body>
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>
	<c:choose>
		<c:when test="${examTypeListFromDB eq null}">			
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">Data Not Found...</span>
			</div>
		</c:when>
	<c:otherwise>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
		  
			<form action="submitCreateExamTypeName.html" method="post">
			<section class="panel">
			<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Exam Type</h2>										
					</header>
				<div style="display: block;" class="panel-body">
				
				<table class="table table-bordered table-striped mb-none" style="visibility: visible;">	
			  	 	<tr>
							<th colspan="1" style="text-align: center;">Exam Type Name</th>	
							<th colspan="1" style="text-align: center;">Exam Type</th>				
					</tr>
					<c:forEach var="examTypeList" items="${examTypeListFromDB}" >
						<tr>
							<td><input type="text" name="examTypeName" value="${examTypeList.examTypeName}" class="form-control" pattern = "[A-Za-z\s]{1,50}"  title="Only Contains Charecter Value" required/></td>
							<td>
								${examTypeList.examTypeCode}
								<input type="hidden" name="examType" value="${examTypeList.examTypeCode}" />
							</td>
							
						</tr>	
					<br>
					</c:forEach>
				</table>
				
				
				</div>
				<footer style="display: block;" class="panel-footer btnsarea01">
						<input type="submit" id="submit" name="submit" value="Submit" onclick="return validateFormStaff();" class="submitbtn btn btn-primary"/>
						<input type="reset" class="clearbtn btn btn-default" value="Clear">	
												
				</footer>
				</section>
			  </form>
		</div>
	</div>
	</c:otherwise>
	</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>