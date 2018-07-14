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
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Designation Level Mapping</h2>
	</header>
	<div class= "content-padding">
		<section role="main" class="content-body">
<!-- start: page -->
<div class="row">
	<div class="col-md-6 col-md-offset-3">
	
	<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
				  <strong>Mapping done successfully.</strong> 
				</div>
			
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">				
				<div class="alert alert-danger">
				  <strong>Mapping creation failed.</strong> 
				</div>
			</c:if>			
		</c:if>
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
		<form method="POST" action="submitDesignationLevelMapping.html" >
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>	
					<h2 class="panel-title">Designation Level Mapping </h2>										
				</header>
				
				<div style="display: block;" class="panel-body">                                  
					<div class="form-group">
						<label class="col-sm-4 control-label">Designation Name</label>
						<div class="col-sm-8">
							<select class="form-control" id="designationCode" name="designationCode">
		                        <option value="">Select...</option>
		                        <c:forEach var="designation" items="${designationListForUnmapped}" >
									<option value="${designation.designationCode}">${designation.designationName}</option>						
								</c:forEach>
	                        </select>                                          
						</div>
					</div>            
					<div class="form-group">
						<label class="col-sm-4 control-label">Designation Level Name</label>
						<div class="col-sm-8">												
		                    <ul class="ulList">
		                    	<c:forEach var="level" items="${designationLevelList}" >
		                    		<li>
		                    			<input type="checkbox" value="${level.designationLevelCode}" name= "designationLevel"> ${level.designationLevelName}
		                    		</li>
		                    	</c:forEach>                            
		                    </ul>
						</div>
					</div>            
				</div>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" onclick="return validate();">Submit </button>
					<button type="reset" class="btn btn-default">Reset</button>
				</footer>
			</section>
		</form>
		<div class="alert alert-warning" id="javascriptmsg" style="display: none">
	  <span></span>	
	</div>
	</div>
	<!-- /*modified by ranita.sur on 20092017 for getting unmapped designation*/ -->
	<div class="content-padding">
		<div class="row">
		<div class="col-md-12">						  
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
										<!--  modified by sourav.bhadra on 22-11-2017  -->									
										<input type="hidden" class="form-control" id="designationName${ind.index}" value="${designation.designationName}">
			    						<input type="hidden" name="designation${ind.index}"  value="${designation.designationCode}">
			    						<!--  added by sourav.bhadra on 22-11-2017  -->
			    						${designation.designationName}
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
							
</div>	
<!-- end: page -->
</section>
	</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/designationLevelMapping.js"></script>
<script src="/cedugenie/js/erp/designationLevelMapping.editable.js"></script>
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