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
<title>Upload Result</title>
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
		<h2>Create Grading System</h2>
	</header>
	<div class="content-padding">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="alert alert-info" id="infomsgbox1">
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
		<c:choose>
			<c:when test="${standardList eq null || empty standardList}">
				<div class="alert alert-danger">
					<strong>Standard not found.</strong>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
					  	<form name="subjectForm" id="subjectForm" method="POST" action="editGradingSystemNew.html" >
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Standard & Grade</h2>										
								</header>
								<div style="display: block;" class="panel-body">
	                               <div class="col-md-6 col-md-offset-3">
	                                    <div class="form-group">
	                                        <label class="control-label">Standard</label>
	                                        <select class="form-control" id="standard" name="standard">
	                                            <option value="">Select...</option>
	                                            <c:forEach var="standard" items="${standardList}" varStatus="i">
													<option value="${standard.standardCode}">${standard.standardName}</option>
												</c:forEach>
	                                        </select>
	                                    </div>
	                               	</div>
                                   	<div class="col-md-12">
                                   	<hr>
	                               		<table class="table table-bordered table-striped mb-none">
	                                   		<tbody>
	                                   			<tr>
	                                    			<td>Scholastic Areas : Part 1</td>
	                                    			<td>Co-Scholastic Areas : Part 2</td>
	                                    		</tr>
	                                    		<tr>
	                                    			<td>--</td>
	                                    			<td>Co-Curricular Activities : Part 3</td>
	                                    		</tr>
	                                   		</tbody>
	                                   		<tfoot>
	                                   			<tr>
	                                   				<td>Grading on 8 Point Scale</td>
	                                   				<td>Grading on 5/3 Point Scale</td>
	                                   			</tr>
	                                   		</tfoot>
	                                   	</table>
	                                   	<table class="table table-bordered table-striped mb-none">
	                                   		<thead>
	                                   			<tr>
	                                   				<th>Marks Range</th>
	                                   				<th>Grade</th>
	                                   				<th>Grade Point Range</th>
	                                   				<th>Grade</th>
	                                   			</tr>
	                                   		</thead>
	                                   		<tbody>
	                                   			<tr>
	                                   				<td>
	                                   					91 - 100
	                                   					<input type="hidden" name="Srange" value="91-100">	
	                              					</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade91-100" id="Sgrade91-100" style="width: 60px;">
	                                 				</td>
	                                   				<td>
	                                   					4.1 - 5.0
	                                   					<input type="hidden" name="Crange" value="4.1-5.0">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Cgrade4.1-5.0" id="Cgrade4.1-5.0" style="width: 60px;">
	                                  				</td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					81 - 90
	                                   					<input type="hidden" name="Srange" value="81-90">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade81-90" id="Sgrade81-90" style="width: 60px;">
	                                   				</td>
	                                   				<td>
	                                   					3.1 - 4.0
	                                   					<input type="hidden" name="Crange" value="3.1-4.0">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Cgrade3.1-4.0" id="Cgrade3.1-4.0" style="width: 60px;">
	                                   				</td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					71 - 80
	                                   					<input type="hidden" name="Srange" value="71-80">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade71-80" id="Sgrade71-80" style="width: 60px;">
	                                  				</td>
	                                   				<td>
	                                   					2.1 - 3.0
	                                   					<input type="hidden" name="Crange" value="2.1-3.0">	
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Cgrade2.1-3.0" id="Cgrade2.1-3.0" style="width: 60px;">
	                                   				</td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					61 - 70
	                                  					<input type="hidden" name="Srange" value="61-70">
	                                  				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade61-70" id="Sgrade61-70" style="width: 60px;">
	                                  				</td>
	                                   				<td>
	                                   					1.1 - 2.0
	                                   					<input type="hidden" name="Crange" value="1.1-2.0">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Cgrade1.1-2.0" id="Cgrade1.1-2.0" style="width: 60px;">
	                                  				</td>
	                                   			</tr>                                   			
	                                   			<tr>
	                                   				<td>
	                                   					51 - 60
	                                   					<input type="hidden" name="Srange" value="51-60">
	                              					</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade51-60" id="Sgrade51-60" style="width: 60px;">
	                                 				</td>
	                                   				<td>
	                                   					0.1 - 1.0
	                                   					<input type="hidden" name="Crange" value="0.1-1.0">
	                                   				</td>
	                                   				<td><input type="text" class="form-control textfield1" name="Cgrade0.1-1.0" id="Cgrade0.1-1.0" style="width: 60px;"></td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					41 - 50
	                                   					<input type="hidden" name="Srange" value="41-50">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade41-50" id="Sgrade41-50" style="width: 60px;">
	                                   				</td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					33 - 40
	                                   					<input type="hidden" name="Srange" value="33-40">
	                                   				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade33-40" id="Sgrade33-40" style="width: 60px;">
	                                   				</td>
	                                   			</tr>
	                                   			<tr>
	                                   				<td>
	                                   					32 & Below
	                                   					<input type="hidden" name="Srange" value="0-32">
	                                  				</td>
	                                   				<td>
	                                   					<input type="text" class="form-control textfield1" name="Sgrade0-32" id="Sgrade0-32" style="width: 60px;">
	                                  				</td>
	                                   			</tr>
	                                   		</tbody>
	                                   	</table>
	                                </div>
								</div>
								<footer style="display: block;" class="panel-footer">
									<button type="submit" class="btn btn-primary" onclick="return saveGradingSystem();">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
								<div class="alert alert-warning" id="warningbox" style="display:none">
									<span id="warningmsg"></span>
								</div>
							</section>
                       	</form>
					</div>						
				</div>
			</c:otherwise>
		</c:choose>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/academics/createGradingSystemNew.js"></script> 	
</body>
</html>