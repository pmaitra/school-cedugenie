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
<title>Student Promotion</title>
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
<header class="page-header">
	<h2>Student Promotion</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:if test="${insertUpdateStatus eq 'success'}"> 
			<div class="alret alert-success" >
				<span>Updation Successful</span>	
			</div>
		</c:if>
		<c:if test="${insertUpdateStatus eq 'fail'}"> 
			<div class="alret alert-danger">
				<span>Updation failed!!</span>	
			</div>
		</c:if>
		<c:choose>
			<c:when test="${standardList eq null || empty standardList}">
				<div class="alert alert-danger">
					<strong>Standard Not Found.</strong>
				</div>
			</c:when>
		<c:otherwise>
			<div class="row">
				<form method="POST" action="updateStudentPromotion.html" novalidate>
					<div class="col-md-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Student Promotion</h2>										
							</header>
							<div style="display: block;" class="panel-body" id="subjectDetailTable">
								<div class="form-group">
                               		<label class="control-label">From Standard<span class="required" aria-required="true">*</span></label>
                               		<select class="form-control" id="standard" name="standard" required>
                                    	<option value="">Select...</option>
                                  			<c:forEach var="standard" items="${standardList}" varStatus="i">
												<option value="${standard.standardCode}">${standard.standardName}</option>
											</c:forEach>
                                   	</select>
                           		</div>
                           		<div class="form-group">
                               		<label class="control-label">From Section<span class="required" aria-required="true">*</span></label>
                               		<select class="form-control" id="section" name="section" required>
                                    	<option value="">Select...</option>
                                   	</select>
                           		</div>
                           		<div class="form-group">
                               		<label class="control-label">Type<span class="required" aria-required="true">*</span></label>
                               		<select class="form-control" id="type" name="type" required>
                                    	<option value="">Select...</option>
                                    	<option value="PROMOTE">PROMOTE</option>
										<option value="PASSOUT">PASSOUT</option>
                                   	</select>
                           		</div>
                           		<div class="form-group" id="toStandardDiv" style="display: none">
                               		<label class="control-label">To Standard<span class="required" aria-required="true">*</span></label>
                               		<select class="form-control" id="toStandard" name="toStandard" required>
                                    	<option value="">Select...</option>
                                  			<c:forEach var="standard" items="${standardList}" varStatus="i">
												<option value="${standard.standardCode}">${standard.standardName}</option>
											</c:forEach>
                                   	</select>
                           		</div>
                           		<div class="form-group" id="toSectionDiv" style="display: none">
                               		<label class="control-label">To Section<span class="required" aria-required="true">*</span></label>
                               		<select class="form-control" id="toSection" name="toSection" required>
                                    	<option value="">Select...</option>
                                   	</select>
                           		</div>
							</div>
						</section>
					</div>
					<div class="alert alert-danger" style="display: none">
						<strong>No Student left</strong>
					</div>
					<div class="col-md-8" id ="studentPromotionDiv" style= "display:none">
						<section class="panel">
							<header class="panel-heading">
	                            <div class="panel-actions">
	                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                            </div>
	                            <h2 class="panel-title">Students</h2>
	                        </header>
	                        <div class="panel-body">
	                        	<table class="table table-bordered table-striped mb-none" id="studentList">
	                        		<thead>
	                        			<tr>
	                        				<th><input type="checkbox" onchange="checkAllCheckBox(this, 'rollNumber');"></th>
	                        				<th>Roll Number</th>
	                        				<th>Name</th>
	                        				<th>Pass/Fail</th>
	                        				<th>Fees</th>
	                        			</tr>
	                        		</thead>
	                        		<tbody></tbody>
	                        	</table>
	                        </div>
	                        <footer style="display: block;" class="panel-footer">
								<button type="submit" class="btn btn-primary" onclick="return saveStudentPromotion();">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
						<div class="alert alert-danger" id="warningbox" style="display:none">
							<span id="warningmsg"></span>	
						</div>
					</div>
				</form>
			</div>
		</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/createStudentPromotion.js"></script>
<script src="/cedugenie/js/common/checkAllCheckBox.js"></script>
</body>
</html>