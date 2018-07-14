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
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>
	<header class="page-header">
			<h2>Create/Update User Password</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
	</header>
		<div class = "content-padding">
			<c:if test="${message ne null}">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>${message}</span>	
				</div>
			</c:if>
			
			
	 <div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" name="createAndUpdateUserPassword" id="createAndUpdateUserPassword" commandName="createAndUpdateUserPassword" action="createAndUpdateUserPassword.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Manage User Password</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="form-group">
                                            <label class="control-label">Resource Type</label>
                                            <select class="form-control" name='resourceType' id="resourceType" >
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">User Id</label>
                                            <input type="text" class="form-control" name="userId" id="userId" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Name</label>
                                            <input type="text" class="form-control" id="name" name="name" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">New Password</label>
                                            <input type="text" class="form-control" id="newPassword" name="newPassword" placeholder="">
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="changePassword" name="change" disabled>Update Password</button>
                                        <button class="btn btn-success" id="submitNewPassword" name="submit" disabled>Submit New Password</button>
										<button type="reset" class="btn btn-default">Reset</button>
										<button type="submit" id="delete" name="delete"  class="btn btn-danger" disabled>Delete</button>
									</footer>
								</section>
                           </form:form>
						</div>						
					</div>
		</div>
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript"> 
 
 $("#resourceType").change(function (){
		if(($("#resourceType").val()!='')){			
			$("#userId").autocomplete({
				source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceType").val()) ,
				select: function (event, ui){
					var userId = ui.item.value;
				    $.ajax({
						url: '/cedugenie/getUserNameForId.html',
						dataType: 'json',
						data: "userId=" + userId,
						success: function(data) {
							if(data != null && data!=""){
								($("#name").val(data));
								//document.getElementById("warningbox").style.visibility='collapse';
								 $.ajax({			
									    url: '/cedugenie/getUserNameAndStatus.html',
									    	dataType: 'json',
									    	data: "resourceId=" + userId,		    	
									    	 success: function(data) {
									    	if(data != ""){
									    		var name = document.getElementById("name");
									    		 data=data.split("*");
									    		 document.getElementById("name").value=data[0];
									    		 if(data[1]=='exist'){
									    			 document.getElementById("submitNewPassword").setAttribute("disabled","disabled");
									    			 document.getElementById("changePassword").removeAttribute("disabled");
									    			 document.getElementById("delete").removeAttribute("disabled");
									    			// document.getElementById("infomsgbox1").style.visibility='visible';
													 //document.getElementById("infomsg1").innerHTML="Password already exist. Update password";
													 alert("Password already exist. Update password");
									    		 }
									    		 if(data[1]=='notExist'){
									    			 document.getElementById("submitNewPassword").removeAttribute("disabled");
									    			 document.getElementById("changePassword").setAttribute("disabled","disabled");
									    			 document.getElementById("delete").setAttribute("disabled","disabled");
									    		 }
									    		 
									    	}	
									    }	
								});
							}
							else{	   
								//document.getElementById("warningbox").style.visibility='visible';
								//document.getElementById("warningmsg").innerHTML="User Name Not Found";
							}
						}			        
					});
				}
			});
		}
	});   
 
   </script>
</body>
</html>