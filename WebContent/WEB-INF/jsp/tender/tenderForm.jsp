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
       }
      /*  .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       } */
</style>
<!-- <link href="/icam/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> --> 
</head>
<body>
<header class="page-header">
	<h2>Tender Form</h2>
</header>
<div class="content-padding">
		<div class="row">				
			<%-- <c:if test="${ticket != null}">		 --%>	
				<c:if test="${status == 'success'}">
					<div class="alert alert-success">
						<strong></strong>	
					</div>					
				</c:if>
				<c:if test="${status == 'fail'}">
					<div class="alert alert-danger">
						<strong></strong>	
					</div>					
				</c:if>
			<%-- </c:if> --%>
		
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form modelAttribute="FORM" method="POST" id="submitTender" name="submitTender" action="submitTender.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Tender Form</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Reference Number <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" name="tenderReferenceNumber" id="tenderReferenceNumber" value="${tenderReferenceNumber}" placeholder="" required>                                                    
                                                </div>
                                            </div>
                                             <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Department <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="department" id="department" required>
                                                        <option value="">Select...</option>
															<c:forEach var="dept" items="${departmentList}" >
																<option value="${dept.departmentCode}">${dept.departmentName}</option> 
																<%-- <option value="${dept.departmentCode}" ${dept.departmentCode eq departmentObj.departmentCode?'selected':value} >${dept.departmentName}</option> --%>
															</c:forEach>
															
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Type <span aria-required="true" class="required">*</span></label>                                                
                                                        	 <select class="form-control" name="tenderType" id="tenderType" required>
	                                                        	<option value="">Select...</option>
	                                                       		
																 <c:forEach var="tenderType" items="${tenderTypeList}" >
																	<option value="${tenderType.tenderTypeCode}">${tenderType.tenderType}</option>
																</c:forEach> 
														
                                                   			 </select>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                          
                                           <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Category <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="tenderCategory" id="tenderCategory" required>
                                                        <option value="">Select...</option>
															<c:forEach var="tenderCategory" items="${tenderCategoryList}" >
																<option value="${tenderCategory.tenderCategoryCode}">${tenderCategory.tenderCategoryName}</option>
															</c:forEach> 
                                                    </select>
                                                </div>
                                            </div> 

                                           <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Sub Category <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="tenderSubCategory" id="tenderSubCategory" required onchange="getAllCommodityList(0)">
                                                        <option value="">Select...</option>
															<%-- <c:forEach var="job" items="${categoryList}" >
																<option value="${job.taskCode}">${job.taskName}</option>
															</c:forEach> --%>
                                                    </select>
                                                </div>
                                            </div> 
                                            
                                            
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender SPOC <span aria-required="true" class="required">*</span></label>                                                
                                                   <input type="text" class="form-control" name="tenderSPOC" id="tenderSPOC">
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                          
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Open Date <span aria-required="true" class="required">*</span></label>
                                                    <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="tenderOpenDate" id="tenderOpenDate"  data-plugin-datepicker="" required /> 
		                                           	</div>
		                                        </label>                                                    
                                                </div>
                                            </div>

                                            
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Tender Close Date <span aria-required="true" class="required">*</span></label>
                                                    <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="tenderCloseDate" id="tenderCloseDate"  data-plugin-datepicker="" data-date-start-date="0d" required /> 
		                                           	</div>
		                                        </label>                                                    
                                                </div>
                                            </div>
                                            
                                        </div>
                                        <hr>
                                        <div class="col-md-16 alert alert-danger" id="msgDiv" style="display:none">
                                        	<span id ="msg"></span>
                                        </div>
                                        <div class="col-md-16">
                                       <!-- here the commodity details will be displayed -->
	                                       <table class="table table-bordered table-striped mb-none" id="tenderTable">
	                                        <thead>
	                                            <tr>
	                                                <th>Commodity Name</th>
	                                                <th>Unit</th>
	                                                <th>Quantity</th>
	                                                <th>Add</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="tenderTableBody">
                                            <tr>
                                            	<td>
                                            		<select class="form-control" name="commodityName" id="commodityName_0" required onchange = "getCommodityUnit(this)">
                                                        <option value="">Select...</option>
                                                    </select>
                                                   
                                            	</td>
                                                <td><input type="text" class="form-control" id="unit0" name="unit" placeholder="" readonly></td>
                                                <td><input type="text" class="form-control" id="quantity0" name="quantity" placeholder="" ></td>
                                                <td><a class="mb-xs mt-xs mr-xs modal-basic btn btn-info" href="javascript:addrows()" id="addrow" >Add</a></td>
                                             
                                             </tr>
                                            
                                        </tbody>
	                                        </table>
                                        </div>
                                        
                                        <div class="row">
                                        	<hr>
                                            <div class="col-md-4">
                                               <div class="form-group">
                                                <label class="control-label">Upload Related Document </label>
                                                <table>
                                                    <tr>
                                                        <td><input type="file" name="uploadFile.tenderRelatedFile" ></td>
                                                        <td><input id="addFile2" class="addFileClassName" type="button" value="ADD"/></td>
                                                    </tr>
                                                    <!-- tr>
                                                        <td><input type="file"></td>
                                                        <td><a style="margin:10px;" href="#" class="on-default remove-row"><i class="fa fa-plus-square"></i></a></td>
                                                    </tr> -->
                                                </table>                                                    
                                               </div>
                                            </div>
                                        </div>
                                        
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary"  type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>	
                        
					</div>	
				</div>
			</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/tender/tenderForm.js"></script>
<!--  <script src="/icam/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>-->

<script type="text/javascript">
$('.datepicker').datepicker({
    format: 'dd/mm/yyyy',
    endDate: '+0d',
    autoclose: true
});
</script>
</body>
</html>