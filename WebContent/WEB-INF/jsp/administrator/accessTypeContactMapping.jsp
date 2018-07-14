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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>
	<header class= "page-header">  <!-- Added by saif 29-03-2018 -->
		<h2>Map Access Type Contact</h2>
	</header>
		<div class = "content-padding">
			<c:if test="${successMessage ne null}">
					<div class="alert alert-success"  >
						<strong>${successMessage}</strong>	
					</div>
				</c:if>
			
				<c:if test="${errorMessage ne null}">
						<div class="alert alert-danger"  >
							<strong>${errorMessage}</strong>	
						</div>
				</c:if>
			<div class="row">
				
						<div class="col-md-4">
						 <form method="POST" action="insertAccessTypeContactMapping.html">
						 	<c:choose>
								<c:when test="${resource.accessTypeList eq null || resource.accessTypeList.size() eq 0}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Contact Access Type Mapping Found</span>	
									</div>						
								</c:when>
								<c:when test="${message ne null}">
									<div class="successbox" id="successbox" style="visibility: visible;">
										<span id="errormsg">${message}</span>	
									</div>						
								</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Access Type - Contact Mapping</h2>										
									</header>
									<c:forEach var="resource"  items="${pagedListHolder}">
                                           <input type="hidden" name="hiddenResourceId" value="${resource.userId}*${resource.accessType.accessTypeCode}"/>
                                     </c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Access Type Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="accessTypeCode" id="accessTypeName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="accessType" items="${resource.accessTypeList}">
													<option value="${accessType.accessTypeCode}">${accessType.accessTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="resourceTypeCode" id="resourceTypeName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resource.resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">User Id <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control"  placeholder="" name="userId" id="userId" required>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Contact Name</label>
                                            <input type="text" class="form-control" name="name" id="name"  placeholder="" readonly>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" onclick = "return validate()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</c:otherwise>
						</c:choose>
                      </form>
					</div>
						<div class="col-md-8">	
                            <form:form >	
                                <section class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-actions">
                                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                        </div>

                                        <h2 class="panel-title">Access Type Contact Mapping List</h2>
                                    </header>
                                    <div class="panel-body">
                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>User Id</th>
                                                    <th>Contact Name</th>
                                                    <th>Access Type</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="resource"  items="${pagedListHolder}">
                                            	<input type="hidden" name="resourceId" value="${resource.userId}*${resource.accessType.accessTypeCode}"/>	
                                               		 <tr>
                                                		 <td>${resource.userId}</td>
														 <td>${resource.name}</td>
														 <td>${resource.accessType.accessTypeName}</td>
                                                   	     <td><a class="on-default remove-row" href="inactiveAccessTypeContactMapping.html?resourceId=${resource.userId}~${resource.accessType.accessTypeCode}" ><i class="fa fa-trash-o"></i></a></td>
                                                    	 
                                               		 <!-- 	Naimisha -->
                                               		 </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div> 
                                </section>
                            </form:form>
						</div>
					</div>
		</div>
					
			
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/javascripts/tables/accessTypeContactMapping.editable.js"></script> -->
<script src="/cedugenie/assets/javascripts/tables/examples.datatables.editable.js"></script>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/administrator/accessTypeContactMapping.js"></script>
<script type="text/javascript">

/* $("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){
		$("#userId").autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())   
	 	});
	}
});  

$(document).ready( function(){      
	$("#name").focus(function (){
		
		$.ajax({
		  url: '/cedugenie/getUserNameForId.html',
		  dataType: 'json',
		  data: "userId=" + $("#userId").val(),
		  success: function(data) {	
			 
			  	if(data != null && data != ""){	
			  		//alert(data)
					var name=document.getElementById("name");
					name.value=data;					
		     	}else{			     		
		     		var name=document.getElementById("name");
					name.value= "";
		     		
		    	//alert("User Name Not Found");
		     	}
        	}			        
		});
	});
});
 */

</script>
</body>
</html>