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
<title>Activity Log List</title>
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
			<h2>Activity Log List</h2>
		</header>				
	<div class="row">
		<c:choose>
			<c:when test="${logList eq null || empty logList}">			
				<div class="alert alert-danger">
				  <strong>No Activity Log List</strong> 
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-12">
					<form name="activityLogListForm" id="activityLogListForm">
	                    <section class="panel">
	                        <header class="panel-heading">
	                            <div class="panel-actions">
	                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                            </div>
	
	                            <h2 class="panel-title">Activity Log List</h2>
	                        </header>
	                        <div class="panel-body">
	                           <table  id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
	                                <thead>
	                                    <tr>
	                                        <th>Updated By</th>
	                                        <th>Updated On</th>
	                                        <th>Updated For</th>
	                                        <th>Action</th>
	                                        <th>Changes Made On</th>
	                                        <th>Description</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                	<c:forEach var="activityLog" items="${logList}" >
		                                <tr>
											<td>
												${activityLog.updatedByName}
											</td>
											<td>
												${activityLog.updatedOn}
											</td>	
											<td>
												${activityLog.updatedFor}
											</td>
											<td>
												${activityLog.insertUpdate}
											</td>
											<td>
												${activityLog.functionality}
											</td>
											<td>
												${activityLog.description}
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
</body>
</html> 