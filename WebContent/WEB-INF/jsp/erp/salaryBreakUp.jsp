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
<title>Salary Break Up</title>
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

	<header class="page-header">
		<h2>Create Custom Salary Template</h2>
	</header>
<div class="content-padding">
	<c:if test="${salaryBreakUpListForCreateUpdate[0]!= null}">
	 		<c:if test="${salaryBreakUpListForCreateUpdate[0].status!= null}">
			<c:choose>
			<c:when test="${salaryBreakUpListForCreateUpdate[0].status eq 'success'}">
				<div class="alert alert-success"  >
					<strong>New Structure Name Inserted Successfully</strong>	
				</div>			
			</c:when>
			<c:otherwise>
					
				<div class="alert alert-danger" >
				<strong>Problem occurred during insertion</strong>	<!-- //Added By Naimisha 28072017 -->
			</div>		
			</c:otherwise>
			</c:choose>
			</c:if>
		</c:if>	
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="create" id="create" action="createSalaryBreakUp.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Create New Salary Head</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                                   
                       <div class="form-group">
                           <label class="control-label">Salary Structure Name</label>
                           <input type="text" class="form-control" name="salaryBreakUpName" id="newSalaryBreakUpName" placeholder="First Name" required="" pattern="^[a-zA-Z0-9 ]+$">
                       </div>										
                       <div class="form-group">
                            <label class="control-label">Salary Structure Type</label>
                            <select class="form-control" name="salaryBreakUpType" id="newSalaryBreakUpType" required>
							    <option value="">Select Type</option>									
								<option value="EARNING">EARNING</option>
								<option value="DEDUCTION">DEDUCTION</option>
								<option value="CALCULATION_BASE">CALCULATION_BASE</option>
								<option value="MANUAL">MANUAL</option>
							</select>                                            
                       </div>  
                       <div class="form-group">
                           <label class="control-label">Slab</label>
                           <input type="radio" name="slab" value="false" checked="checked"/>No
							<input type="radio" name="slab" value="true"/>Yes                                                                                      
                       </div>                                              
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>											
					</footer>
				</section>
	     	 </form:form>
		</div>							
		 										
		<%-- <c:choose>
		<c:when test="${salaryBreakUpListForCreateUpdate eq null || empty salaryBreakUpListForCreateUpdate}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Salary Head Found</span>	
				</div>
		</c:when>	
		<c:otherwise>	 --%>					
		<div class="col-md-7">	
			<input type="hidden" name="saveId" id="saveId">
                <section class="panel">
                     <header class="panel-heading">
                         <div class="panel-actions">
                             <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                         </div>

                         <h2 class="panel-title">View Salary Heads</h2>
                     </header>
                   <div class="panel-body">

                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                             <thead>
                                 <tr>
                                     <th>Salary Structure Name</th>
                                     <th>Salary Structure Type</th>
                                     <th>Slab Required Or Not</th> 	 	
                                 </tr>
                             </thead>
                             <tbody>
                             	<c:forEach var="salaryBreakUp" items="${salaryBreakUpListForCreateUpdate}">
	                                 <tr class="gradeX">
										<td>
											<input type="text" class="form-control" id="${salaryBreakUp.salaryBreakUpCode}" name="${salaryBreakUp.salaryBreakUpCode}"  value="${salaryBreakUp.salaryBreakUpName}" readonly>
										</td>	
										<td>
											<input type="text" class="form-control" id="${salaryBreakUp.salaryBreakUpType}" name="${salaryBreakUp.salaryBreakUpType}"  value="${salaryBreakUp.salaryBreakUpType}" readonly>
								        </td>
	                                     <td>
												${salaryBreakUp.slab}
	                                     </td>													
									</tr>
                              	</c:forEach>
                             </tbody>
                         </table>
                     </div>
               </section>
		</div>
		<%-- </c:otherwise>
		</c:choose> --%>
	</div>	
</div>				



<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/erp/salaryBreakUp.js"></script>
</body>
</html>