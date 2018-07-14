<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Facility</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
			<h2>Add Facility</h2>
	</header>
			<div class = "content-padding">
				<div class="row">
						<c:choose>
							<c:when test="${socialCategoryList eq null && socialCategoryList.size() eq 0}">
								<div class="alert alert-danger">
									<strong>You've to create social category before adding facility.</strong>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-6 col-md-offset-3">
									<form id="addFacility" name="addFacility" action="addFacility.html" method="post">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title">Add Facility</h2>
											</header>
											<div style="display: block;" class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Facility Name</label>
															<div class="">
																<input type="text" class="form-control" id="facilityName" name="facilityName" pattern="^[a-zA-z0-9 ]+$" title="Facility name can consist of alphabetical characters and spaces only" required>
															</div>
														</div>
													</div>
													<div class="col-md-4">
				                                        <div class="form-group">
															<label class="control-label">Description</label>
															<div class="">
																<input type="text" class="form-control" id="facilityDesc" name="facilityDesc" pattern="^[a-zA-z0-9 ]+$" title="Facility description can consist of alphabetical characters and spaces only" required>
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Payment Status</label>
															<div>
																<label class="radio-inline radio-primary">
																	<input type="radio" name="ispaid" value="true" id="paid">Paid
																</label>
																<label class="radio-inline radio-primary">
																	<input type="radio" name="ispaid" value="false" id="unpaid" checked ="checked">Unpaid
																</label>
															</div>
														</div>
													</div>
												</div>
												<hr>
		                                        <div class="form-group col-sm-6 col-md-offset-3" style="display:none" id="chargeTable">
		                                           <table class="table table-bordered table-striped mb-none">
		                                                <thead>
		                                                    <tr>
		                                                        <th style="background:#eee; text-align:center;">Category</th>
		                                                        <th style="background:#eee; text-align:center;">Charge(Yearly)</th>
		                                                    </tr>
		                                                </thead>
		                                                <tbody>
		                                                	<c:forEach var="socialCategoryList" items="${socialCategoryList}">
		                                                    <tr>
		                                                        <td>
																	<input type="hidden" id = "category" name="category" value="${socialCategoryList.socialCategoryCode}" />
																	${socialCategoryList.socialCategoryName}
																</td>
		                                                        <td><input type="text" class="form-control" name="${socialCategoryList.socialCategoryCode}" onfocus="return removeZero(this);" value = "0.00" onblur = "return setZero(this);" required ></td>
		                                                    </tr>
		                                                    </c:forEach>
		                                                </tbody>
		                                            </table>
		                                          	<%-- <input type="hidden" id="socialCategorySize" value = "${socialCatgeoryListSize}"> --%>
		                                        </div>
											</div>
											<footer style="display: block;" class="panel-footer">
												<button class="submitbtn btn btn-primary" id="submit" name="submit">Submit </button>
												<button type="reset" class="btn btn-default">Reset</button>
											</footer>
										</section>
		                            </form>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
			</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$('input[name=ispaid]').click(function(){
		if(this.id=="paid")
		{
			$('#chargeTable').css('display','block');	
		}
		else
		{
			$('#chargeTable').css('display','none');
		}
	});
	
	function removeZero(tBox){
		if(tBox.value=="0.00"){
			tBox.value="";
		}
	}
	
	function setZero(tBox){
		if(tBox.value==""){
			tBox.value="0.00";
		}
		if(isNaN(tBox.value)){
			alert("Please enter a valid value(Numeric)");
			tBox.value="0.00";
		}else{
			var p=parseFloat(tBox.value);
			if(p<0.0){
				alert("Please enter a no greater than zero");
				tBox.value="0.00";
			}
			else if(p=-0.0){
				alert("Please enter a no greater than zero");
				tBox.value="0.00";
			}
		}
	}
	
	/* $("#submit").click(function(){
		alert("listSize:"+($("#socialCategorySize").val));
		returns false;
	}); */
</script>
</body>
</html>