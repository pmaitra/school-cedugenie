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
									<h2 class="panel-title">Program Details</h2>										
								</header>
								<div style="display: block;" class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Program ID</th>
											<th>Program Name</th> 
											<th>Program Type</th>
											<th>Total Seat</th>
											<th>Form Issuance Date</th>
											<th>Form Submission Last Date</th>
											<th>Candidate Scrutiny Date</th>
											<th>Interview Date</th>
											<th>Marks Submission Date</th>
											<th>Fees Payment Start Date</th>
											<th>Fees Payment End Date</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												${programmeDetails.programId}
											</td>
											<td>
												${programmeDetails.programName}
											</td> 
											<td>
												${programmeDetails.programType}
											</td>
											<td>
												${programmeDetails.totalSeat}
											</td>
											<td>
												${formIssue}
											</td> 
											<td>
												${formSubmissionLast}
											</td>
											<td>
												${candidateScrutiny}
											</td>
											<td>
												${interview}
											</td>
											<td>
												${markSubmissionStart}
											</td>
											<td>
												${feesPaymentStart}
											</td> 
											<td>
												${feesPaymentEnd}
											</td>
										</tr>
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