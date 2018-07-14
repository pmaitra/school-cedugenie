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
<title>Standard & Sections</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2>Standard & Sections</h2>
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
			<c:if test="${insertUpdateStatus eq 'mapped'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
		
					<div class="row">
						<div class="col-md-4">
							<form action="addStandard.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Standard & Section</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Standard Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="standardName" name="standardName" class="form-control" type="text" pattern = "[A-Za-z\s]{1,50}"  title="Programme Name Contains Charecter" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Section: <span class="required" aria-required="true">*</span> </label>
											<div class="col-sm-7">
												<input id="sections" name="sections" class="form-control" type="text" placeholder="Comma separated values" pattern = "[A-Z a-z,]{1,}" title = "Batch is a Comma Separrated Field" required>
											</div>
										</div>
									</div>
									<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
			  							<span></span>	
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" onclick="return validating()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<c:choose>
							<c:when test="${standardList eq null || empty standardList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">Standard Not Created Yet</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>
						<div class="col-md-8">
							<form name="editStandard" id="editStandard" action="editStandard.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="newProgramName" id="newProgramName">
							<input type="hidden" name="newBatchName" id="newBatchName">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Existing Standard & Section</h2>
							</header>
							<div class="panel-body" id="datatable-editable">
								<c:forEach var="standard" items="${standardList}" varStatus="i">
								<input type="hidden" name="oldStandardNames" value="${standard.standardCode}">
								</c:forEach>
								<table class="table table-bordered table-striped mb-none"  id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Standard</th>
											<th>Section</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="standard" items="${standardList}" varStatus="i" >
										<tr>
											<td>
												<input type="hidden" name="standardId${i.index}" value="${standard.standardId}">
												
												<input type="hidden" id="standardCode${i.index}" name="standardCode${i.index}" value="${standard.standardCode}">
												<input type="hidden" id="standardName${i.index}" name="standardName${i.index}" class="form-control" value="${standard.standardName}" readonly />
												${standard.standardName}
											</td>
											<td>
												<c:forEach var="section" items="${standard.sectionList}" varStatus="j">
													<c:if test="${section.sectionName ne 'NA'}">
														 <input type="hidden" id="oldSectionNames${i.index}" name="oldSectionNames${i.index}" class="batchClass"value="${section.sectionName}" readonly />  
														${section.sectionName}
													</c:if>
												</c:forEach>
												</td>
											<td class="actions">
												<c:if test="${standard.status eq 'f'}">
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showProgramGroupDetails('${i.index}')"><i class="fa fa-pencil"></i></a>
													<a href="inactiveStandard.html?serialId=${standard.standardId}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
												</c:if>
												<%-- <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="hidden on-editing save-row" id="save${i.index}" ><i class="fa fa-save"></i></a> --%>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
						<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                              <section class="panel">
                                  <header class="panel-heading">
                                      <!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
                                  </header>
                                  <div class="panel-body">
                                      <table class="table table-bordered table-striped mb-none" id = "programGroupDetails">
                                          <thead>
                                              <tr>
                                                  <th>Standard Name</th>
                                                  <th>Section</th>
                                              </tr>
                                          </thead>
                                          <tbody>
                                          </tbody>
                                      </table>
                                     	<div class="alert alert-warning" id="javascriptmsg" style="display: none">
											<span></span>	
										</div>
                                   </div>
                                   <footer class="panel-footer">
                                       <div class="row">
                                           <div class="col-md-12 text-right">
                                               <button class="btn btn-success " id="updateProgramGroup">Update</button>
                                              <button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>

                                          </div>
                                      </div>
                                  </footer>
                              </section>
		                  </div> 
					</form>
				</div>
			</c:otherwise>
			</c:choose>	
		</div>     
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createStandard.editable.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/academics/createStandard.js"></script> 

</body>
</html>