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
<title>Interview Details</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2></h2>
		</header>
		<div class="content-padding">
		
		
					<div class="row">
						<div class="col-md-12">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Interview Details</h2>										
								</header>
								<div style="display: block;" class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Interview Date</th>
											<th>Interview Address</th> 
											<th>Interview Panel</th>
											<th>INterview City</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="location" items="${locationDetailsList}">
											<tr>
												<td>
													${location.interviewDate}
												</td>
												<td>
													${location.venueName}, ${location.area}, ${location.zoneName}, ${location.pin}, ${location.city}, ${location.stateName}
												</td> 
												<td>
													Anup Narayan Roy
												</td>
												<td>
													${location.city}
												</td>
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
</body>
</html>