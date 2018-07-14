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

<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.2.min.js"></script> -->


</head>
<body>					
	
	
	
	
			<header class="page-header">
				<h2>Dashboard</h2>
			</header>
			<div class="content-padding">
	
						<!-- start: page -->
                    <div class="row">
						<div class="col-md-6">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
					
									<h2 class="panel-title" id="chartLabel1"></h2>
									<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
										<button id="chart1" class="mb-xs mt-xs mr-xs modal-basic btn  btn-danger"> Download PDF </button>
									</c:if>
								</header>
								
								<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
									<div class="panel-body">
					
											<div id="chartContainer1">Chart will load here!</div>
									
					
										</div>			
								</c:if>
							</section>
						</div>
						<div class="col-md-6">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
									<h2 class="panel-title" id="chartLabel2"></h2>	
									<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
										<button id="chart2" class="mb-xs mt-xs mr-xs modal-basic btn  btn-danger"> Download PDF </button>
									</c:if>								
								</header>
								<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
									<div class="panel-body">
						
										<div id="chartContainer2">Chart will load here!</div>
						
									</div>
								</c:if>
							</section>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xl-3 col-md-6">
							<section class="panel panel-transparent">								
								<div class="panel-body">
									<section class="panel panel-group">
										<header class="panel-heading bg-primary">
					
											<div class="widget-profile-info">
												<div class="profile-picture">
													<img src="assets/images/logo-blackbg.png">
												</div>
												<div class="profile-info">
													<h4 class="name text-weight-semibold">${resourceDetails.userId}</h4>
													<h5 class="role">${sessionScope.sessionObject.currentRoleOrAccess}</h5>
													<div class="profile-footer">
														<a href="viewStaffProfileDetails.html?userId=${sessionScope.sessionObject.userId}" target="frame">(view profile)</a>
													</div>
												</div>
											</div>
					
										</header>
                                        <div class="panel-body">
										<div class="row">
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Full Name</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.name}</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Designation</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.resourceTypeName}</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Contact Number</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.mobile}</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Mail Id</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.emailId}</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Gender</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.gender}</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-6 control-label"><b>Blood Group</b></label>
                                                <label class="col-md-6 control-label">${resourceDetails.bloodGroup}</label>
                                            </div>
                                        </div>
                                        </div>
									</section>
					
								</div>
							</section>
						</div>
						<div class="col-xl-3 col-md-6">
							<section class="panel">
							<header class="panel-heading">	
									<h2 class="panel-title">Major Events</h2>									
								</header>								
								<div class="panel-body">
									<div class="scrollable" data-plugin-scrollable style="height:275px;">
									<div class="timeline timeline-simple mb-md scrollable-content">
										<div class="tm-body">
											<ol class="tm-items">
											
											
											
												<c:forEach var="majorEvent" items="${majorEventList}" varStatus="i">
												<li>
													<div class="tm-box">
														<p class="text-muted mb-none">${majorEvent.date}</p>
														<p>
															${majorEvent.event}
														</p>
													</div>
												</li>
												</c:forEach>
											</ol>
										</div>
									</div>
									</div>
								</div>
							</section>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-6 col-md-12">
							<section class="panel">
								<div class="panel-body">
									<div><h2 class="panel-title">Calendar</h2></div>
									<div id="scheduler">${body}</div>
								</div>
							</section>
						</div>
					</div>
					<!-- end: page -->
	</div>

	
	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/fusionchart/fusioncharts.js"></script>
<!-- <script type="text/javascript" src="/icam/js/fusionchart/fusioncharts.theme.fint.js"></script> -->

<script src="/icam/js/login/saveSvgAsPng.js"></script>
<script src="/icam/js/login/jspdf.debug.js"></script>
<script src="/icam/js/login/svg_to_pdf.js"></script>

<script type="text/javascript">

	var arraydata1 = null;
	var arraydata2 = null;
		
	FusionCharts.ready(function(){
	
	var chart1 = new FusionCharts({
        "type": "pie3d",
        "renderAt": "chartContainer1",
        "width": "500",
        "height": "300",
        "dataFormat" : "xml",
        "dataSource" : ""
  });
	
	
	
	var chart2 = new FusionCharts({
        "type": "column2d",
        "renderAt": "chartContainer2",
        "width": "500",
        "height": "300",
        "dataFormat" : "xml",
        "dataSource" : ""
  });
    
	
    $.ajax({
        url:"http://localhost:8080/icam/chart1Data.html",
        dataType:"text",
        success:function(xmldata1){
        	arraydata1 = xmldata1.split("||"); 
        	chart1.chartType(arraydata1[0]);
        	document.getElementById("chartLabel1").innerHTML = arraydata1[1];
        	chart1.setXMLData(arraydata1[2]);
        	chart1.render();
        },
        error:function(xmldata1){
        	//alert("error : " + xmldata1);
        }
    });
    
    
    $.ajax({
        url:"http://localhost:8080/icam/chart2Data.html",
        dataType:"text",
        success:function(xmldata2){
        	arraydata2 = xmldata2.split("||"); 
        	chart2.chartType(arraydata2[0]);
        	document.getElementById("chartLabel2").innerHTML = arraydata2[1];
        	chart2.setXMLData(arraydata2[2]);
        	chart2.render();
        },
        error:function(xmldata2){
        	//alert("error : " + xmldata2);
        }
    }); 

});
	
	//var svgdata = $('#chartobject-1').html();
	//console.log(svgdata);
	
	document.querySelector("#chart1").addEventListener("click", function () {
		svg_to_pdf(document.querySelector("svg"), function (pdf) {
            download_pdf(arraydata1[1] + '.pdf', pdf.output('dataurlstring'));
        });
    });
	
	document.querySelector("#chart2").addEventListener("click", function () {
        svg_to_pdf(document.querySelectorAll("svg")[1], function (pdf) {
            download_pdf(arraydata2[1] + '.pdf', pdf.output('dataurlstring'));
        });
    });
</script>
</body>
</html>