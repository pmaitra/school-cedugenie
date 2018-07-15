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
<title>Edit Book</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

			<header class="page-header">
				<h2>Edit Magazine</h2>
			</header>
			<div class="content-padding">
					<div class="row">
						<div class="col-md-12">
						  	<form:form method="POST" action="updateMagazine.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Magazine</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                       <div class="row">
                                       		<div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Code</label>
                                                    <input type="text" class="form-control" id="magazineCode" name="magazineCode" readonly="readonly" value="${magazineDetails.magazineCode}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Name <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="magazineName" name="magazineName" readonly="readonly" value="${magazineDetails.magazineName}" pattern="^[a-zA-Z ]*$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Category</label>
                                                    <input type="text" class="form-control" name="magazineCategory" id="magazineCategory" readonly="readonly" value="${magazineDetails.magazineCategory}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Period(Days) <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="magazinePeriod" name="magazinePeriod" readonly="readonly" value="${magazineDetails.magazinePeriod}" pattern="^[0-9]+$" required>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Price <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="magazineCost" name="magazineCost" readonly="readonly" value="${magazineDetails.magazineCost}" pattern="^[+]?[0-9]+(\.[0-9]+)?$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Entry Date <span aria-required="true" class="required">*</span></label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="magazineEntryDate" name="magazineEntryDate" readonly="readonly" value="${magazineDetails.magazineEntryDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Receive Date <span aria-required="true" class="required">*</span></label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="magazineReceiveDate" name="magazineReceiveDate" readonly="readonly" value="${magazineDetails.magazineReceiveDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Publisher <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="magazinePublisher.magazinePublisherName" name="magazinePublisher.magazinePublisherName" readonly="readonly" value="${magazineDetails.magazinePublisher.magazinePublisherName}" pattern="^[a-zA-Z ]*$" onfocus="getPublisherName(this.value);" required>
                                                </div>
                                             </div>
                                            <div class="col-md-4">
                                                
                                                <div class="form-group">
                                                    <label class="control-label">Bill No <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="magazineBillNo" name="magazineBillNo" readonly="readonly" value="${magazineDetails.magazineBillNo}" pattern="^[a-zA-Z0-9 -/]*$" required>
                                                </div>
                                                 <div class="form-group">
                                                    <label class="control-label">Bill Date <span aria-required="true" class="required">*</span></label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="magazineBillDate" name="magazineBillDate" readonly="readonly" value="${magazineDetails.magazineBillDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                            </div>
                                            
                                            </div>
                                        </div>
									
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" value="SUBMIT" id="submitForm" disabled="disabled" onclick="return validateEditForm();">Submit </button>
										<button class="btn btn-primary" type="button" id="Edit" name="Edit" value="EDIT" onClick="activeForm();">Edit</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
				</div>
				
	

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/library/editMagazine.js"></script>
</body>
</html>