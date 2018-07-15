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
		<%-- <input type = "hidden" name = "module" id = "module" value = "${module}"> --%>
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
						<div class="col-md-6">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
									<h2 class="panel-title" id="chartLabel3"></h2>	
									<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
										<button id="chart3" class="mb-xs mt-xs mr-xs modal-basic btn  btn-danger"> Download PDF </button>
									</c:if>								
								</header>
								<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
									<div class="panel-body">
						
										<div id="chartContainer3">Chart will load here!</div>
						
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
									<h2 class="panel-title" id="chartLabel4"></h2>	
									<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
										<button id="chart4" class="mb-xs mt-xs mr-xs modal-basic btn  btn-danger"> Download PDF </button>
									</c:if>								
								</header>
								<c:if test="${resourceDetails.resourceTypeName ne 'STUDENT'}">
									<div class="panel-body">
						
										<div id="chartContainer4">Chart will load here!</div>
						
									</div>
								</c:if>
							</section>
						</div>
					</div>
					
					<!-- end: page -->
	</div>

	
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script> -->
<script type="text/javascript" src="/cedugenie/js/fusionchart/fusioncharts.js"></script>
<!-- <script type="text/javascript" src="/cedugenie/js/fusionchart/fusioncharts.theme.fint.js"></script> -->

<script src="/cedugenie/js/login/saveSvgAsPng.js"></script>
<script src="/cedugenie/js/login/jspdf.debug.js"></script>
<script src="/cedugenie/js/login/svg_to_pdf.js"></script>

<script type="text/javascript">

	var arraydata1 = null;
	var arraydata2 = null;
	var arraydata3 = null;
	var arraydata4 = null;
	
	
	(function (  ) { 

	    // put all that "wl_alert" code here   
		FusionCharts.ready(function(){
			
			//var module = document.getElementById("module").value;
//			alert("module====="+module);
			var chart1 = new FusionCharts({
		        "type": "bar3d",
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
			
			var chart3 = new FusionCharts({
		        "type": "column2d",
		        "renderAt": "chartContainer3",
		        "width": "500",
		        "height": "300",
		        "dataFormat" : "xml",
		        "dataSource" : ""
		  });
		    
			var chart4 = new FusionCharts({
		        "type": "column2d",
		        "renderAt": "chartContainer4",
		        "width": "500",
		        "height": "300",
		        "dataFormat" : "xml",
		        "dataSource" : ""
		  });
		    $.ajax({
		        url:"http://localhost:8080/cedugenie/loadChart1DataForLibrary.html",
		        dataType:"text",
		        //data: "module=" + ($(module).val()),
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
		        url:"http://localhost:8080/cedugenie/loadChart2DataForLibrary.html",
		        dataType:"text",
		        //data: "module=" +  ($(module).val()),
		        success:function(xmldata1){
		        	
		        	arraydata2 = xmldata1.split("||"); 
		        	chart2.chartType(arraydata2[0]);
		        	document.getElementById("chartLabel2").innerHTML = arraydata2[1];
		        	chart2.setXMLData(arraydata2[2]);
		        	chart2.render();
		        },
		        error:function(xmldata1){
		        	//alert("error : " + xmldata1);
		        }
		    });
		    
		    
		    $.ajax({
		        url:"http://localhost:8080/cedugenie/loadChart3DataForLibrary.html",
		        dataType:"text",
		        //data: "module=" +  ($(module).val()),
		        success:function(xmldata2){
		        	
		        	arraydata3 = xmldata2.split("||"); 
		        	chart3.chartType(arraydata3[0]);
		        	document.getElementById("chartLabel3").innerHTML = arraydata3[1];
		        	chart3.setXMLData(arraydata3[2]);
		        	chart3.render();
		        },
		        error:function(xmldata2){
		        	//alert("error : " + xmldata2);
		        }
		    }); 
		    
		    $.ajax({
		        url:"http://localhost:8080/cedugenie/loadChart4DataForLibrary.html",
		        dataType:"text",
		        //data: "module=" +  ($(module).val()),
		        success:function(xmldata2){
		        	//alert("xmldata2=="+xmldata2);
		        	arraydata4 = xmldata2.split("||"); 
		        	chart4.chartType(arraydata4[0]);
		        	document.getElementById("chartLabel4").innerHTML = arraydata4[1];
		        	chart4.setXMLData(arraydata4[2]);
		        	chart4.render();
		        },
		        error:function(xmldata2){
		        	//alert("error : " + xmldata2);
		        }
		    }); 

		});

	}( jQuery ));


	
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
	document.querySelector("#chart3").addEventListener("click", function () {
		svg_to_pdf(document.querySelectorAll("svg")[2], function (pdf) {
            download_pdf(arraydata3[1] + '.pdf', pdf.output('dataurlstring'));
        });
    });
	
	document.querySelector("#chart4").addEventListener("click", function () {
        svg_to_pdf(document.querySelectorAll("svg")[3], function (pdf) {
            download_pdf(arraydata4[1] + '.pdf', pdf.output('dataurlstring'));
        });
    });
</script>
</body>
</html>