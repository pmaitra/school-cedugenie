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
	<h2>Class Teacher</h2>
</header>
<div class="content-padding">
	<div class= "row">
		<c:if test="${status eq 'success' }">
		<div class="alert alert-success">
			<strong> ${msg}</strong>	
		</div>
		</c:if>
		<c:if test="${status eq 'fail' }">
			<div class="alert alert-danger">
				<strong>${msg} </strong>	
			</div>
		</c:if>
			<div class= "row">
				<form name="createClassTeacher" id="createClassTeacher" method="POST" action="createClassTeacher.html" novalidate>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Class Teacher</h2>										
								</header>
								<div style="display: block;" class="panel-body" >                                       
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Standard</label>
	                                            <select class="form-control" id="standardCode" name="standardCode" required>
	                                            	<option value="">Select..</option>
	                                                <c:forEach var="standard" items="${standardList}" varStatus="i">
														<option value="${standard.standardCode}">${standard.standardName}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Section</label>
	                                            <select class="form-control" id="section" name="section"  required>
	                                            	<option value="">Select..</option>
	                                            </select>
	                                        </div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Teacher User Id</label>
	                          					<input type="text" class="form-control" name="desc" id="desc" placeholder="" required>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Teacher Name</label>
	                          					<input type="text" class="form-control" name="userName" id="userName" placeholder="" disabled required>
											</div>
										</div>
									</div>
								</div>
								<footer style="display: block;" class="panel-footer">
									<button class="btn btn-primary" type="submit">Submit </button>
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
							</section>
						</div>
					</div>
				</form>
			</div>
			<div class= "row">
				<div class="col-md-8 col-md-offset-2">
							<form name="editCommodity" id="editCommodity" action="editCommodity.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Class Teacher List</h2>
                                </header>
                                
                               <%--  <c:choose>
									<c:when test="${commodityList eq null}">
										<span>${message}</span>	
									</c:when>
									<c:otherwise> --%>
										<div class="panel-body">
											<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
												<thead>
													<tr>
														<th>Standard</th>
														<th>Section</th>
														<th>Teacher</th>
														<th>Academic Year</th>
														<!-- <th>Actions</th> -->
													</tr>
												</thead>
												<tbody>
													<c:forEach var="classTeacher" items="${classTeacherList}" varStatus="i">
														<tr class="gradeX">
															<td>
																<%-- <input type="hidden" id="oldCommodityCode${i.index}" name="oldCommodityCode${i.index}" value="${commodity.commodityCode}" />
																<input type="hidden" class="form-control" id="newCommodityName${i.index}" name="newCommodityName${i.index}" value="${commodity.commodityName}" disabled onblur="checkCommodityName(this,'oldCommodityCode${i.index}');"/> --%>
																${classTeacher.standard}
															</td>
															<td>
																<%-- <input type="hidden" class="form-control" id="commodityDesc${i.index}" name="commodityDesc${i.index}" value="${commodity.commodityDesc}" />	<!--Changes by Saif 23-03-2018 all disabled are removed from hidden--> --%>
																${classTeacher.desc}
															</td>
															<td>
																<%-- <input type="hidden" class="form-control" id="modelNo${i.index}" name="modelNo${i.index}" value="${commodity.modelNo}" /> --%>
																${classTeacher.name}
															</td>
															<td>
																<%-- <input type="hidden" class="form-control" id="inStock${i.index}" name="inStock${i.index}" value="${commodity.inStock}"/> --%>
																${classTeacher.academicYear}
															</td>
															
															<%-- <td class="actions">
																<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateCommodity('${i.index}')"><i class="fa fa-pencil"></i></a>
															</td> --%>
														</tr>
													</c:forEach>
												
												</tbody>
											</table>
										</div>
									<%-- </c:otherwise>
                                </c:choose> --%>
                            </section>
                            <!-- popup Window code -->
								<!-- <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px">
									<section class="panel">
										<header class="panel-heading">
											<h2 class="panel-title">Approver Group Name - PO_Approver</h2>
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateCommodityDetails">
												<thead>
													<tr>
														<th>Commodity Name</th>
														<th>Commodity Desc</th>
														<th>In Stock</th>
														<th>Threshold</th>
														<th>Commodity Type</th>
														<th>Commodity Sub Type</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateCommodity" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div> -->
                            </form>
						</div>
			</div>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/createClassTeacher.js"></script>
</body>
</html>