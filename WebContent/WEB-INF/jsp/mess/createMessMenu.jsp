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
<title>Mess Menu</title>
<%@ include file="/include/include.jsp" %>
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
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
		<h2>Mess Menu</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<c:choose>
				<c:when test="${menuTimeList eq null || empty menuTimeList}">
					<div class="alert alert-danger">
						<strong>No Mess Menu Time Found.</strong>
					</div>
				</c:when>
				<c:otherwise>
					<form action="submitMessMenuDetails.html" method="post">
						<div class="col-md-6 col-md-offset-3"> 
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Menu And Date</h2>
								</header>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Mess Menu Name</label>
												<input type="text" class="form-control" id = "messMenuRoutineName" name = "messMenuRoutineName" pattern = "^[a-zA-Z0-9\-\s]+$" title="Mess Menu Name accepts alphanumeric and spaces only"required/>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Start Date</label>
												<div class="input-group">
													<span class="input-group-addon">
                                                    	<i class="fa fa-calendar"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id = "startDate" name = "startDate" required/>
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
	                                                <input type="text" class="form-control" id = "endDate" name = "endDate" readonly="readonly"/>
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
												<c:forEach var="menuTime" items="${menuTimeList}">
													<th>
														${menuTime.messMenuTimeName}
														<input type="hidden" name="messMenuTime" id="messMenuTime" value="${menuTime.messMenuTimeDesc}"/>
													</th>
												</c:forEach>
											</tr>
										</thead>
										<tbody class="gradeC">
											<tr>
												<td>
													Monday
													<input type="hidden" name="days" id="days" value="Monday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Tuesday
													<input type="hidden" name="days" id="days" value="Tuesday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Wednesday
													<input type="hidden" name="days" id="days" value="Wednesday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Thursday
													<input type="hidden" name="days" id="days" value="Thursday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Friday
													<input type="hidden" name="days" id="days" value="Friday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Saturday
													<input type="hidden" name="days" id="days" value="Saturday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td>
													Sunday
													<input type="hidden" name="days" id="days" value="Sunday">
													<c:forEach var="time" items="${menuTimeList}">
														<td><textarea class="form-control" rows="2" cols="2" id="${time.messMenuTimeCode}" name="${time.messMenuTimeDesc}" required></textarea></td>
													</c:forEach>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<footer style="display: block;" class="panel-footer">
									<button class="btn btn-primary" type="submit" id="submit" name="submit">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
							</section>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="/icam/js/mess/createMessMenu.js"></script>
</body>
</html>