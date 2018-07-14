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
       #datatable-editable_filter{
			display: none;
		}
</style>
</head>
	<body>
	<header class="page-header">
			<h2>Designation Level Mapping</h2>
		</header>
		<c:if test="${status eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${message}</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>${message}</strong>	
			</div>
		</c:if>
		 <c:if test="${status eq 'mapped'}">
			<div class="alert alert-danger" >
				<strong>${message}</strong>	
			</div>
		</c:if> 
	<div class="content-padding">
		<div class="row">
		<div class="col-md-8 col-md-offset-2">						  
			<form name="updateDesignationLevelMapping" id="updateDesignationLevelMapping" action="updateDesignationLevelMapping.html" method="post">
				<input type="hidden" name="saveId" id="saveId">				
                <section class="panel">
				<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
				</div>
			
				<h2 class="panel-title">Designation Level List</h2>
				</header>
					<div class="alert alert-warning" id="javascriptmsg" style="display: none">
					  <span></span>	
					</div>
					<div class="panel-body">								
						<table class="table table-bordered table-striped mb-none"  id="datatable-editable">
							<thead>
								<tr>	                                         
									<th>Designation</th>
									<th>Level</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="designation" items="${designationList}" varStatus="ind">
								<tr>											
									<td>
										<input type="hidden" name="designationCodeList"  value="${ind.index}">										
										<input type="text" class="form-control" readonly id="designationName${ind.index}" value="${designation.designationName}">
			    						<input type="hidden" name="designation${ind.index}"  value="${designation.designationCode}">
									</td>
									<td>
									<ul class="ulList" readonly id="ulList${ind.index}">
								    		<c:forEach var="level" items="${designation.designationLevelList}" >	
								    		
												<c:choose>
													<c:when test="${level.status eq 'checked'}">
														<li>	
														<input type="checkbox" value="${level.designationLevelCode}" name="level${ind.index}" checked="checked"> ${level.designationLevelName}	
														<input type="hidden" name="oldDesignationLevel${ind.index}"  value="${level.designationLevelCode}"/>
														</li>					
													</c:when>					
													<c:otherwise>
														<li>
														<input type="checkbox" value="${level.designationLevelCode}" name="level${ind.index}"> ${level.designationLevelName}
														</li>
													</c:otherwise>
												</c:choose>			
											</c:forEach>						
										</select>
										</ul>
									</td>
									<td class="actions">
										<a href="#" class="hidden on-editing save-row" id="save${ind.index}"><i class="fa fa-save"></i></a>
										<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
										<a href="#" class="on-default edit-row" id="edit${ind.index}"><i class="fa fa-pencil"></i></a>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</section>
			</form>
		</div>
	</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/editDesignationLevelMapping.editable.js"></script>
<script type="text/javascript">
function makeEditable(rowId){	
	rowId=rowId.replace("edit","");	
	document.getElementById("ulList"+rowId).removeAttribute("readonly");
};
function saveData(rowId){	
	rowId=rowId.replace("save","");	
	document.getElementById("saveId").value=rowId;	
	var level = document.getElementsByName('level'+rowId);	
	var p=0;
	for(var i=0; i<level.length;i++){
		if(level[i].checked)
		p=p+1;
	}
	if(p==0){
		document.getElementById("javascriptmsg").style.display = 'block';
		document.getElementById("javascriptmsg").innerHTML="Please select atleast one Designation Level";		
		return false;
	}	
	document.updateDesignationLevelMapping.submit();
};

</script>
</body>
	
</html>
									
									