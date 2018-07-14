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
<title>Admission Form</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Admission Form</h2>
</header>
<div class="content-padding">
	<div class="row">
		<div class="row">
	        <div class="col-md-8 col-md-offset-2">
	          <section class="panel">
	              <header class="panel-heading">
	                <div class="panel-actions">
	                  <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                  <a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
	                </div>
	                <h2 class="panel-title">Admission Form</h2>
	              </header>
	              <div class="panel-body">
	                <div class="owl-carousel owl-theme" data-plugin-carousel data-plugin-options='{ "dots": true, "autoplay": true, "autoplayTimeout": 3000, "loop": true, "margin": 10, "nav": false, "items": 1 }'>
	                  <div class="item"><img class="img-thumbnail" src="assets/images/AdmissionForm1.jpg" alt=""></div>
	                  <div class="item"><img class="img-thumbnail" src="assets/images/AdmissionForm2.jpg" alt=""></div>
	                 </div>
	              </div>
	              <div class="panel-footer">
	                  <a href="assets/images/Form.pdf" download="Form.pdf" class="btn btn-primary">Download Form</a>
	              </div>
	            </section>
	        </div>
      	</div>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>