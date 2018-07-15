<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Assign House Master</title>
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
		<h2>Assign House Master</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
			<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>House Master Assigned Successfully.</strong>
				</div>
			</c:if>
			
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>House Master Assigning Failed.</strong>
				</div>
			</c:if>
			<c:choose>
			<c:when test="${null eq academicYearList || empty academicYearList}">
				<div class="alert alert-danger" >
					<strong>Academic Year Not Found</strong>
				</div>
			</c:when>
			<c:otherwise>
			<form action="submitAssignedHouseMaster.html" method="POST">
				<!-- <input type="hidden" name="type" value="create">
				<input type="hidden" name="length" id="length"> -->
				<div class="col-md-4 col-md-offset-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Academic Year</h2>
						</header>
						<div style="display: block;" class="panel-body">
                        	<div class="form-group">
                           		<label class="control-label">Academic Year<span class="required" aria-required="true">*</span></label>
                                <select class="form-control" id="academicYear" name="academicYear" required>
                                	<option value="">Select..</option>
                              	 	<c:forEach var="year" items="${academicYearList}" varStatus="i">
										<option value="${year.academicYearCode}">${year.academicYearName}</option>
									</c:forEach>
                                </select>
                            </div>
						</div>
						<div class="alert alert-warning" id="warningbox" style="display:none">
							<span id="warningmsg"></span>	
						</div>
					</section>
				</div>
               	<div class="col-md-12">
                	<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">Set House Master</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none">
								<thead>
									<tr>
										<th>House</th>
										<th>House Master</th>
										<th>Attached House Master</th>
									</tr>
								</thead>
								<tbody id="houseMasterDataTable">
								</tbody>
							</table>
						</div>
                         <footer style="display: block;" class="panel-footer">
                             <button type="submit" class="btn btn-primary" onclick="return validateAssignHouseMaster()">Submit</button>
                             <button type="reset" class="btn btn-default">Reset</button>
                         </footer>
                        </section>
                    </div>
				</form>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/assignHouseMaster.js"></script>
</body>
</html>