<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Read QR Code</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form id="form1">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Read QR Code</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group" id="imageDiv" >
											<div>
												<%-- <img alt="No image" src="${pageContext.request.contextPath}/getQRCode/imageFileName"> --%>
												<%-- <img src="${path}"> --%>
												<img src="file:///D:/icamRepository/QRCode/Book/MICRO-12.png">
											</div>
										</div>
									</div>
                                    <hr>
                                    <input type="hidden" value="${imageData}" id="imageData">
                                    <div style="display: block;" class="panel-body" >
                                        
                                        <table class="table table-bordered table-striped mb-none" id="imageDataTable"> 
                                            
                                        </table>   
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type = "button" onclick="printing();">Print </button>
									</footer>
								</section>
                            </form>
						</div>
					</div>

<script>
window.onload=function(){
	var imageData=document.getElementById("imageData").value;
	
	//alert(imageData);
	imageData=imageData.split(";");
	var rows="";
	for(var i=0;i<imageData.length;i=i+2){
		var thtd=imageData[i].split(":");
		var thtd1=imageData[i+1].split(":");
		rows=rows+"<tr><th>"+thtd[0]+"</th><td>"+thtd[1]+"</td><th>"+thtd1[0]+"</th><td>"+thtd1[1]+"</td></tr>";
	}
	document.getElementById("imageDataTable").innerHTML=rows;
}
function printing(){
	var mywindow = window.open('', 'my div', 'height=220,width=220');
    mywindow.document.write('<html><head><title>my div</title>');
    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(document.getElementById("imageDiv").innerHTML);
    mywindow.document.write('</body></html>');
    //window.close(); 
    mywindow.print();
    mywindow.close();
};

</script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
</body>
</html>