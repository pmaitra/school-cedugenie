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
	rowId=rowId.replace("edit","");
	document.getElementById("textDesig"+rowId).removeAttribute("disabled");	
	document.getElementById("employeeTypeName"+rowId).removeAttribute("disabled");
};

function saveData(rowId){
	rowId=rowId.replace("save","");
	alert("In saveData :: "+rowId);
	document.getElementById("saveId").value=rowId;
	//window.location="editHostel.html?saveId="+rowId;
	document.editDesignationForm.submit();
};
</script>
</head>
<body>
	<header class="page-header">
		<h2>Create Group</h2>
	</header>
	<div class="content-padding">
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Insertion successful'}">
				<div class="alert alert-success">
					<strong>Successfully Created</strong>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Insertion Failed'}">
				<div class="alert alert-danger">
					<strong>Creation Failed</strong>	
				</div>
			</c:if>	
			
		</c:if>
		<c:if test="${status ne null}">
			<div class="alert alert-success">
				<strong>${status}</strong>	
			</div>
		</c:if>	
			<div class="alert alert-danger" id="warningDiv" style="display: none">
				<span id="warningMsg"></span>	
			</div>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
		  	<form:form name="" action="createGroup.html" method="POST" >
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Create Group</h2>										
					</header>
					<div style="display: block;" class="panel-body">
                                 <div class="form-group">
                                     <label class="control-label">Name</label>                                     
                                     <input type="text" name="groupName" id="groupName" class="form-control" onblur="checkGroupName(this);" >
                                 </div>    
                                 <div class="form-group">
                                     <label class="control-label">Parent</label>
                                     <select name="parentGroupCode" id="parentGroupCode" class="form-control" >
                                        <option value="">Select</option>
										<c:if test="${parentGroupList ne null}">
											<c:forEach var="gfl" items="${parentGroupList}">
												<option value="${gfl.groupCode}">${gfl.groupName}</option>
											</c:forEach>
										</c:if>
                                     </select>
                                 </div>										
                                 <div class="form-group">
                                    <label class="control-label">Group Type</label>
									<select name="groupTypeCode" id="groupTypeCode" class="form-control" >
										<option value="">Select</option>
										<c:if test="${groupTypeList ne null}">
											<c:forEach var="gfl" items="${groupTypeList}">
												<option value="${gfl.groupTypeCode}">${gfl.groupTypeName}</option>
											</c:forEach>
										</c:if>
									</select>
                                 </div> 
                                        
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateGroup();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
           </form:form>
		</div>
		
		
		<%-- <c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success" id="successbox">
					<strong>Successfully Updated</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alret alert-danger" id="errorbox">
					<strong>Updatation Failed</strong>	
				</div>
			</c:if>		
		</c:if>		 --%>
	</div>	
	<div class="row">
		<div class="col-md-12">	
			<section role="main" class="content-body">		
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>
				
						<h2 class="panel-title">Group List</h2>
					</header>
					<div class="panel-body">
					<c:forEach var="group" items="${groupList}" varStatus="j">
					<input type="hidden" id="names" name="names" value="${group.groupName}">
					</c:forEach>
						<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Group Name</th>
									<th>Parent Group Name</th>
									<th>Group Type</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="group" items="${groupList}" varStatus="i">
									<tr class="gradeC">
										<td>
											${group.groupName}
										</td>
										<td>
											${group.parentGroupCode}
										</td>
										<td>
											${group.groupTypeCode}
										</td>
										<td>
                                                <a class="on-default remove-row" href="inactiveLegdgerGroup.html?groupCode=${group.groupName}"><i class="fa fa-trash-o"></i></a> 
                                       	</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
				</section>			
			</section>
		</div>
	</div>			
</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/erp/designation.editable.js"></script>
<script type="text/javascript" src="/icam/js/finance/groupAndLedgerCreate.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</body>
</html>