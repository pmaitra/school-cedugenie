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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/academics/createExamMarks.js"></script> 
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>

                    <div class="row">
						<div class="col-md-4">
							<form name="subjectForm" id="subjectForm" method="POST" action="editExamMarks.html" >
								<c:choose>
									<c:when test="${standardList eq null || empty standardList}">
										<div class="btnsarea01" >
											<div class="errorbox" id="errorbox" style="visibility: visible;">
						
												<c:if test="${standardList eq null || empty standardList}">
													<span id="errormsg">Standard Not Found</span>
												</c:if>
											</div>
										</div>
									</c:when>
									<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Set Exam Marks</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Standard</label>
											<div class="col-sm-7">
												<select class="form-control" id="standard" name="standard">
                                                    <option value="">Select...</option>
	                                                    <c:forEach var="standard" items="${standardList}" varStatus="i">
															<option value="${standard.standardCode}">${standard.standardName}</option>
														</c:forEach>
                                                </select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Exam</label>
											<div class="col-sm-7">
												<select class="form-control" id="exam" name="exam">
                                                    <option value="">Select...</option>
                                                </select>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								</c:otherwise>
								</c:choose>
                            </form>
						</div>
						<div class="col-md-8">						  
								
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Set Exam Marks</h2>
							</header>
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="subjectTable">
									<thead>
										<tr>
                                            <th>Subject</th>
											<th>Theory</th>
											<th>Theory Pass</th>
                                            <th>Practical</th>
                                            <th>Practical Pass</th>
                                            <th>Total</th>
                                            <th>Pass</th>
										</tr>
									</thead>
									<tbody>
										<!-- <tr>
                                            <td>HINDI LOWER</td>
                                            <td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
											<td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
                                            <td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
                                            <td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
                                            <td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
                                            <td><input name="standardName" class="form-control" type="text" placeholder="0" style="width:60px;"></td>
										</tr> -->
									</tbody>
								</table>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button class="btn btn-primary">Submit </button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>
						</section>
						</div>
					</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>