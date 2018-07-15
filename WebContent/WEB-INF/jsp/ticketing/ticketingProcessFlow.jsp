<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/magnifier/jquery.simpleLens.css">
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/magnifier/jquery.simpleGallery.css">
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>
<body>
<article style="margin-left:12%;margin-top:5%;">
<span id="proceeFlowHeader">Ticketing Process Flow</span>
    <div class="simpleLens-gallery-container" id="demo-1">
        <div class="simpleLens-container">
            <div class="simpleLens-big-image-container">
                <a class="simpleLens-lens-image" data-lens-image="/cedugenie/images/TicketingFlowDiagram.png">
                    <img src="/cedugenie/images/TicketingFlowDiagram.png" class="simpleLens-big-image">
                </a>
            </div>
        </div>        
    </div>
</article>
<script src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/magnifier/jquery.simpleGallery.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/magnifier/jquery.simpleLens.js"></script>
<script>
    $(document).ready(function(){
        $('#demo-1 .simpleLens-thumbnails-container img').simpleGallery({
            loading_image: '/cedugenie/images/loading.gif'
        });

        $('#demo-1 .simpleLens-big-image').simpleLens({
            loading_image: '/cedugenie/images/loading.gif'
        });
    });
</script>
</body>
</html>