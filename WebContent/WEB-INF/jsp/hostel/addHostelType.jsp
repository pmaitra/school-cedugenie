<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Residence Type</title>
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
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Create Residence Type</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
		<div class="col-md-5">
			<form:form name="submitHostelType" action="submitHostelType.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>	
					<h2 class="panel-title">Create New Residence Type</h2>										
					</header>
					<div style="display: block;" class="panel-body">	                                   										
                    <div class="form-group">
                        <label class="control-label">Enter Residence Type:<span class="required" aria-required="true">*</span></label>
                        <input type="text" class="form-control" name="hostelTypeName" id="hostelTypeName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="HostelType name can consist of alphabetical characters and spaces only" required>
                    </div>
                    
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDepartmentForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	    	</form:form>
		</div>
		
		
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
				  <strong>Residence Type successfully created.</strong> 
				</div>

			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">				
				<div class="alert alert-danger">
				  <strong>Residence Type  creation failed.</strong> 
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">			
			<c:if test="${updateResponse eq 'Success'}">				
				<div class="alert alert-success">
				  <strong>Residence Type  successfully updated.</strong> 
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
				  <strong>Residence Type  updation failed.</strong> 
				</div>
			</c:if>		
		</c:if>					
		<c:choose>
			<c:when test="${hostelTypeListNew eq null || empty hostelTypeListNew}">			
				<div class="alert alert-danger">
				  <strong>No Residence Type  created yet.</strong> 
				</div>
			</c:when>
		<c:otherwise>
			<div class="col-md-7">
				<input type="hidden" name="saveId" id="saveId">	
                    <section class="panel">
                        <header class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            </div>

                            <h2 class="panel-title">View Residence Type</h2>
                        </header>
                        <div class="panel-body">
                            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                <thead>
                                    <tr>
                                        <th>Residence Type Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="hostel" items="${hostelTypeListNew}" varStatus="i">
	                                <tr>
										<td>
											<input type="text" class="form-control" id="hostelTypeName${i.index}" name="hostelTypeName${i.index}" value="${hostel.hostelTypeName}" readonly>
										</td>
										</tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                		</div>
                    </section>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	</div>
		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html> 