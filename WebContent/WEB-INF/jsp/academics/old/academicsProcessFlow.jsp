<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/icam/css/common/magnifier/jquery.simpleLens.css">
<link rel="stylesheet" type="text/css" href="/icam/css/common/magnifier/jquery.simpleGallery.css">
</head>
<body>
<article style="margin-left:12%;margin-top:2%;">
<span id="proceeFlowHeader" style="margin-left: 10%;">Academics Process Flow</span>
    <div class="simpleLens-gallery-container" id="demo-1">
        <div class="simpleLens-container">
            <div class="simpleLens-big-image-container">
                <a class="simpleLens-lens-image" data-lens-image="/icam/images/Academics.png">
                    <img src="/icam/images/Academics.png" class="simpleLens-big-image">
                </a>
            </div>
        </div>        
    </div>
</article>
<script src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/magnifier/jquery.simpleGallery.js"></script>
<script type="text/javascript" src="/icam/js/common/magnifier/jquery.simpleLens.js"></script>
<script>
    $(document).ready(function(){
        $('#demo-1 .simpleLens-thumbnails-container img').simpleGallery({
            loading_image: '/icam/images/loading.gif'
        });

        $('#demo-1 .simpleLens-big-image').simpleLens({
            loading_image: '/icam/images/loading.gif'
        });
    });
</script>
</body>
</html>