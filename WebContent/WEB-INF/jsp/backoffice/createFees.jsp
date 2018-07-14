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

<script type="text/javascript">
function makeEditable(rowId){
	rowId = rowId.replace("edit","");
	document.getElementById("feesName"+rowId).removeAttribute("readonly");
};
function saveData(rowId){
	rowId = rowId.replace("save","");
	document.getElementById("saveId").value = rowId;
	var validateStatus = preventDuplicateFeesName("feesName"+rowId);
	if(validateStatus == true){
		document.editFeesForm.submit();
	}
	
};
</script>
</head>
<body>

			<c:if test="${insertUpdateStatus ne null}">
			<div class="alert alert-success">
				<strong>${insertUpdateStatus}</strong>	
			</div>
			</c:if>

					<div class="row">
						<form name="feesForm" id="feesForm" action="addFees.html" method="POST">
							<div class="col-md-5" id="feesDetail" >
						  		<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Fees Name</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="form-group">
                                            <label class="control-label">Fees Name</label>
                                            <input type="text" class="form-control" id="feesName" name="feesName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Fees name can consist of alphabetical characters and spaces only" required>
                                        </div>
                                    </div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick="return addFees();">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </div>
						</form>
						<c:choose>
						<c:when test="${feesList eq null || empty feesList}">
							<div class="btnsarea01" >
								<div class="alert alert-danger">
									<strong>Fees Not Created Yet.</strong>
								</div>
							</div>
						</c:when>
						<c:otherwise>
						
						<div class="col-md-7">	
						<form name="editFeesForm" id="editFeesForm" action="editFees.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Fees Name</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                        <thead>
                                            <tr>
                                                <th>Fees Name</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="fees" items="${feesList}" varStatus="i">
	                                            <tr class="gradeX">
	                                                <td>
	                                                	<input type="hidden" name="oldFeesNames${i.index}" value="${fees.feesCode}">
	                                                	<input type="hidden" name="oldFeesNamesForDuplicateCheck" value="${fees.feesName}">
	                                                	<input type="text" class="form-control" id="feesName${i.index}" name="newFeesName${i.index}" readonly="readonly" value="${fees.feesName}" required>
                                                	</td>
	                                                <td class="actions">
	                                                    <a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a>
														<a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
														<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
	                                                </td>
	                                            </tr>
                                         	</c:forEach>   
                               			</tbody>
                                    </table>
                                </div>
                            </section>
                            </form>
						</div>
					</c:otherwise>
					</c:choose>
				</div>	
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/createFees.editable.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/createFees.js"></script>
</body>
</html>