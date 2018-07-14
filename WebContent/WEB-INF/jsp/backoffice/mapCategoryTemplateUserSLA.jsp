<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Map Category-Template-User For SLA</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Map Category-Template-User For SLA</h2>
</header>
<div class="content-padding">
	<c:if test="${insertStatus eq 'success'}">
		<div class="alert alert-success" id="successboxmsgbox" >
			<span>Mapping Successful.</span>	
		</div>
	</c:if>
	<c:if test="${insertStatus eq 'fail'}">
		<div class="alert alert-danger" id="errormsgbox" >
			<span>Mapping UnSuccessful.</span>	
		</div>
	</c:if> 
	<div class="row">
		<div class="col-md-4">
		  	<form action="submitMapCategoryTemplateUser.html" method="POST" >
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Category-Template-User</h2>
					</header>
					<div style="display: block;" class="panel-body">
						<div class="form-group">
	                        <label class="control-label">Category Name<span class="required" aria-required="true">*</span></label>
	                        <select class="form-control" name="category" id="category" required>
	                            <option value="">Select...</option>
	                            <c:forEach var="category" items="${categoryList}">
								<option value="${category.jobTypeCode}">${category.category}</option>
								</c:forEach>
	                        </select>
	                   	</div>
	                   	 	<div class="form-group">
	                        <label class="control-label">Ticket Status<span class="required" aria-required="true">*</span></label>
	                        <select class="form-control" name="status" id="status" required>
	                            <option value="">Select...</option>
	                            <c:forEach var="ticketStatus" items="${ticketStatusList}">
								<option value="${ticketStatus.ticketStatusCode}">${ticketStatus.ticketStatusName}</option>
								</c:forEach>
	                        </select>
	                   	</div>    
	                   	<div class="form-group">
	                        <label class="control-label">Template Type<span class="required" aria-required="true">*</span></label>
	                        <select class="form-control" name="templateType" id="templateType" required>
	                            <option value= "">Select...</option>
	         					<option value= "Email">Email</option>
	         					<option value= "Alert">Alert</option>
	         					<option value= "Notification">Notification</option>
	         					<option value= "SMS">SMS</option>
	                        </select>
	                   	</div>
	                   	<div class="form-group">
	                        <label class="control-label">Template Name<span class="required" aria-required="true">*</span></label>
	                        <select class="form-control" name="template" id="template" required>
	                            <option value="">Select...</option>
	                            <%-- <c:forEach var="template" items="${templateList}">
								<option value="${template.templateCode}">${template.emailSubject}</option>
								</c:forEach> --%>
	                        </select>
	                   	</div>
	                     
					</div>
					<footer style="display: block;" class="panel-footer">
						<button  type = "submit" class="btn btn-primary">Submit</button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
           	</form>
		</div>
	 	<div class="col-md-8">	
	        <section class="panel">
	            <header class="panel-heading">
	                <div class="panel-actions">
	                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                </div>
	                <h2 class="panel-title">Existing Category-Template-User Mapping List</h2>
	            </header>
	            <div class="panel-body">
	                <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
	                    <thead>
	                        <tr>
	                            <th>Category Name</th>
	                            <th>Template Name</th>
	                            <!-- <th>User Id</th> -->
	                        </tr>
	                    </thead>
	                    <tbody>
                    		<c:forEach var="mappedData" items="${mappedCategoryTemplateUserList}">
                         		<tr>
		                            <td>${mappedData.category}</td>
		                       		<td>${mappedData.template}</td>
		                       		<%-- <td>${mappedData.userId}</td> --%>
		                         </tr>
                   			</c:forEach>
                   		</tbody>
               		</table>
            	</div>
        	</section>
		</div> 
	</div>	
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script>
	$("#templateType").change(function(){
		//alert("hii");
		var template = document.getElementById("template");
		removeOption(template);
		
		$.ajax({
	        url: '/cedugenie/getTemplateForATemplateType.html',
	        dataType: 'json',
	        data: "templateType=" + ($(this).val()),
	        success: function(dataDB) {
	        	//alert("dataDB=="+dataDB);
	        	var options="<option value=''>Select..</option>";
	        	if(dataDB != "null" && dataDB !=""){
	        		var arr = dataDB.split("#");
					for (var i=0;i<arr.length-1;i++){   
						var temp = arr[i].split("*");
						options=options+"<option value='"+temp[0]+"'>"+temp[1]+"</option>";
					}
				}
	        	template.innerHTML=options;
	       }
		});

	})
	
function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select..</option>";
}
</script>
</body>
</html>