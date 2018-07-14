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

<c:if test="${insertUpdateStatus ne null}">
	<div class="alert alert-success">
						<strong>${insertUpdateStatus}</strong>
	</div>
</c:if>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form method="POST" action="createNotice.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">
										<c:if test="${noticeBoard eq null}">
											Create Notice
										</c:if>
										<c:if test="${noticeBoard ne null}">
											Update Notice
										</c:if>
										</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-md-12">
	                                        <div class="form-group">
	                                            <label class="control-label">Notice Name</label>
	                                            <input type="hidden" class="form-control" name="noticeCode" value="${noticeBoard.noticeCode}" />
												<input id ="noticeName" class="form-control" name="noticeName" value="${noticeBoard.noticeName}" required/>
	                                        </div>
										<div class="form-group">
                                           	<label class="control-label">Notice Description</label>
                                           	<textarea rows="7" cols="98"  class="form-control" id ="noticeDesc" name="noticeDesc"  required>${noticeBoard.noticeDesc}</textarea>
                                       	</div>
                                        </div>
                                        
                                        
									</div>
									<footer style="display: block;" class="panel-footer">
										<c:if test="${noticeBoard eq null}">
											<button class="btn btn-primary" type="submit" id="submit" name="submit">Submit</button>
										</c:if>
										<c:if test="${noticeBoard ne null}">
											<button class="btn btn-primary" type="submit" id="update" name="update">Update</button>
										</c:if>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<c:choose>
							<c:when test="${noticeList eq null}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Notice Found</span>	
								</div>				
							</c:when>
						<c:otherwise>
						<div class="col-md-12">
							<form name="updateForm" id="updateForm" action="viewAndDeleteNotice.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="function" id="function">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Notice List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
											<th>Date</th>
											<th>Notice Subject</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="notice" items="${noticeList}" varStatus="i">
										<tr>											
											<td>
												${notice.time}
											</td>			
											<td>
												${notice.noticeName}
											</td>
											<td>
												<button class="btn btn-primary update" type="button" name="${notice.noticeCode}">Update</button>
												<button class="btn btn-default delete" type="button" name="${notice.noticeCode}">DELETE</button>
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
<script type="text/javascript">
$(document).ready(function(){	

	$(".delete").each(function(){
   	$(this).click(function(){                        		
   		var noticeCode = $(this).attr("name");
   		
   		document.getElementById("saveId").value=noticeCode;
   		document.getElementById("function").value="DELETE";
   		document.updateForm.submit();
   		
   		
		});
	});
	
	$(".update").each(function(){
	   	$(this).click(function(){                        		
	   		var noticeCode = $(this).attr("name");
	   		
	   		document.getElementById("saveId").value=noticeCode;
	   		document.getElementById("function").value="UPDATE";
	   		document.updateForm.submit();
	   		
	   		
			});
		});
});
</script>
</body>
</html>