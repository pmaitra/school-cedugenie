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
<title>Edit Mess Menu Details</title>
<%@ include file="/include/include.jsp" %>
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
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
		<h2>Edit Mess Menu Details</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<c:if test="${successStatus != null}">
				<div class="alert alert-success" id="successbox" style="display:block">
					<span id="successmsg">Menu Updated Successfully.</span>	
				</div>
			</c:if>
			<c:if test="${failStatus != null}">
				<div class="alert alert-danger" id="errorbox" style="display:block">
					<span id="errormsg">Menu Updation Failed.</span>	
				</div>
			</c:if>
			<form name="updateMessMenuDetails" method="POST" action="updateMessMenuDetails.html">
				<div class="col-md-6 col-md-offset-3"> 
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Menu And Date</h2>
						</header>
						<div class="panel-body">
							<div class="row" id="messMenuHeadingTable">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Mess Menu Name</label>
										<input type="text" class="form-control" id = "messMenuRoutineName" name = "messMenuRoutineName" pattern = "^[a-zA-Z0-9\-\s]+$" title="Mess Menu Name accepts alphanumeric and spaces only"required value="${messMenuRoutineDetails.messMenuRoutineName}" readonly="readonly"/>
										<input type="hidden" name = "messMenuRoutineCode" id = "messMenuRoutineCode" value = "${messMenuRoutineDetails.messMenuRoutineCode}">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Start Date</label>
										<div class="input-group">
											<span class="input-group-addon">
                                               	<i class="fa fa-calendar"></i>
                                            </span>
                                            <input type="text" class="form-control" id = "startDate" name = "startDate" value="${messMenuRoutineDetails.startDate}" readonly="readonly" required/>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">End Date</label>
										<div class="input-group">
											<span class="input-group-addon">
                                               	<i class="fa fa-calendar"></i>
                                            </span>
                                            <input type="text" class="form-control" id = "endDate" name = "endDate" value="${messMenuRoutineDetails.endDate}" readonly="readonly"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
				<div id="warningbox" class="col-md-12" style="display: none">
					<div class="alert alert-danger">
						<span id="warningmsg"></span>
					</div>
				</div>
				<div class="col-md-12">
					<section class="panel">
						<header class="panel-heading">
						    <h2 class="panel-title">Menu Details</h2>
						</header>
                        <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id="messMenuDetailsTable">
								<thead>
									<tr>
										<th>Days</th>
										<c:forEach var="mmt" items="${messMenuList}">
											<th>
												${mmt.messMenuTimeName}
												<input type="hidden" name = "messMenuTime" id = "messMenuTime" value = "${mmt.messMenuTimeDesc}">
											</th>
										</c:forEach>
									</tr>
								</thead>
								<tbody class="gradeC">
									<c:forEach var="routineSlab" items="${messMenuRoutineDetails.routineSlabList}">
										<tr>
											<td>
												${routineSlab.routineSlabName}<input type="hidden" name = "days" id = "days" value = "${routineSlab.routineSlabName}">
											</td>
											<c:forEach var="ttm" items="${routineSlab.messMenuTimeList}">
												<td>
													<textarea class="form-control" rows="2" cols="2" id="${ttm.messMenuTimeCode}" name="${ttm.messMenuTimeDesc}" readonly="readonly" required>${ttm.messMenuValue}</textarea>
												</td>
											</c:forEach>
										<td>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-danger" type="button" id="edit" onclick="makeMessMenuDetailsEditable();">Edit</button>
							<button class="btn btn-primary" type="submit" id="submit" name="submit" style="display:none">Submit</button>
							<button type="reset" id="reset" class="btn btn-default" style="display:none">Reset</button>
						</footer>
					</section>
				</div>
			</form>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="/cedugenie/js/mess/createMessMenu.js"></script>
</body>
</html>