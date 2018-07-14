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

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>

			<c:if test="${submitResponse ne null}">				
				<c:if test="${submitResponse eq 'Success'}">
					<div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Fixation Of Pay Successfully Created</span>	
					</div>
				</c:if>
				<c:if test="${submitResponse eq 'Fail'}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg" style="visibility:visible;">Fixation Of Pay Creation Failed</span>	
					</div>
				</c:if>		
			</c:if>	

					<div class="row">
						<div class="col-md-12">
						  	<form:form name="fixationOfPay" action="submitFixationOfPay.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

									<h2 class="panel-title">Fixation Of Pay</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-5">
                                            <div class="form-group">
                                                <label class="control-label" name="salaryTemplateCode" id="salaryTemplateCode">Template Name</label>
                                                <select class="form-control">
                                                    <option value="">Select...</option>
                                                    <c:forEach var="salaryTemplate" items="${salaryTemplateList}"> 
													 	<option value="<c:out value="${salaryTemplate.salaryTemplateCode}"/>"><c:out value="${salaryTemplate.salaryTemplateName}"/></option>
													</c:forEach>
                                                </select>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Pay Band Name</label>
                                                <input type="text" class="form-control" name="fixationOfPayName" id="fixationOfPayName" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Pay Band Start Range</label>
                                                <input type="text" class="form-control" name="fixationOfPayStartRange" id="fixationOfPayStartRange" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Pay Band End Range</label>
                                                <input type="text" class="form-control" name="fixationOfPayEndRange" id="fixationOfPayEndRange" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <table class="table table-bordered table-striped mb-none">
                                                <thead>
                                                    <tr>
                                                        <th>Appointments to Posts with Grade pay</th>
                                                        <th>Pay in the Pay Band</th>
                                                        <th>Total</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>											
                                                        <td>
                                                        	<input type="text" class="form-control" name = "appointmentToPostsWithGradePay" id="appointmentsToPostsWithGradePay" placeholder="" value="0" onfocus="{this.value='';}" onblur="setZero(this);">
                                                       	</td>
                                                        <td>
                                                        	<input type="text" class="form-control" name = "payInThePayBand" id="payInThePayBand" placeholder="" value="0" onfocus="{this.value='';}" onblur="setZero(this);">
                                                       	</td>
                                                        <td>
                                                        	<input type="text" class="form-control" name = "totalAmount" id="totalAmount" placeholder="" value="0" onfocus="{this.value='';}" onblur="setZero(this);">
                                                       	</td>
                                                        <td>
                                                            <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick="addrows();" type="button">Add</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateFixationOfPay();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>	
					



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>