<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Asset" />
<meta name="keywords" content="Add Asset" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Read QR Code</title>
<link rel="stylesheet" href="/sms/css/backoffice/readQRCode.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

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
</head>
<body>
	<div id="imageDiv" style="margin-left: auto; margin-right: auto; width: 220px; ">
		<div>
			<img src="${path}" alt="QR Code Not Found." >
		</div>
	</div>
	
	<input type="hidden" value="${imageData}" id="imageData">
	
	<table id="imageDataTable" cellspacing="0" cellpadding="0" class="midsec"></table>
	
	<button type="button" class="submitbtn" onclick="printing();">PRINT</button>
</body>
</html>