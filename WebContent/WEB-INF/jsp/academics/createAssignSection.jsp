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
<title>Assign Section</title>
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
			<h2>Assign Section</h2>
		</header>
		<div class="content-padding">
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
				<c:when test="${standardList eq null || empty standardList}">
					<div class="btnsarea01" >
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<c:if test="${standardList eq null || empty standardList}">
								<span id="errormsg">Courses Not Found</span>	
							</c:if>
						</div>
					</div>
				</c:when>
			<c:otherwise>
			<div class="row">	
				<form action="insertAssignSection.html" method="post">
					<div class="col-md-4 col-md-offset-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>

								<h2 class="panel-title">Assign Section</h2>										
							</header>
							
							<div style="display: block;" class="panel-body">
								<div class="form-group">
									<label class="col-sm-5 control-label">Standard<span class="required" aria-required="true">*</span></label>
									<div class="col-sm-7">
										<select class="form-control" id="standard" name="standard" required>
                                                  <option value="0">Select...</option>
                                                  <c:forEach var="standard" items="${standardList}" varStatus="i">
												<option value="${standard.standardCode}">${standard.standardName}</option>
											</c:forEach>
                                              </select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-5 control-label">Section<span class="required" aria-required="true">*</span> </label>
									<div class="col-sm-7">
										<select class="form-control" id="section" name="section" required> 
										<option value="0">Select...</option>
                                              </select>
									</div>
								</div>
								<!-- /*Added By ranita.sur on 03082017 for getting the strength of Student*/ -->
								<div class="form-group">
                              			<label class="col-sm-5 control-label">Strength :</label>
                              			<div class="col-sm-7">
                              			<input  type="text" class="form-control" id="strength" name="strength" readonly>
                              			</div>
                          			</div>
							</div>
						</section>	
				</div>
				<div class="col-md-12">	
					<section class="panel">		
					<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
						 <span></span>	
					</div>
					<div class="panel-body">
						<c:forEach var="subject" items="${subjectList}" varStatus="i">
							<input type="hidden" name="oldSubjectNames" value="${subject.subjectName}">
						</c:forEach>
						<table class="table table-bordered table-striped mb-none">
							<thead>
								<tr>
									<th>Select</th>
									<th>Roll Number</th>
									<th>Name</th>
								</tr>
							</thead>
							<tbody id = "sectionbody">
							</tbody>
						</table>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="submit" class="btn btn-primary" onclick = "return validate()">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
					</section>
				</div>
           	</form>
		 </div> 
	</c:otherwise>
	</c:choose>	
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/createHostel.editable.js"></script>
<script src="/cedugenie/js/academics/createAssignSection.js"></script>
</body>
</html>