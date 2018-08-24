<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="fixed dark">
<head>

		<!-- Basic -->
		
		<meta charset="utf-8"/>

		<title>Dashboard | cEduGenie Admin - BETA</title>
		<meta name="keywords" content="" />
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
		<!-- Vendor CSS -->
		<link rel="stylesheet" href="/cedugenie/assets/stylesheets/main.css">
		
		<link rel="stylesheet" href="/cedugenie/assets/vendor/bootstrap/css/bootstrap.css" />

		<link rel="stylesheet" href="/cedugenie/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="/cedugenie/assets/vendor/magnific-popup/magnific-popup.css" />

		<!-- Theme CSS -->
		<!--<link rel="stylesheet" href="/cedugenie/assets/stylesheets/theme.css" />-->
        <link rel="stylesheet" href="/cedugenie/assets/stylesheets/BootSideMenu.css" />
        <link rel="stylesheet" href="/cedugenie/assets/vendor/clock/clock.css" />
        <link rel='icon' href='/cedugenie/assets/images/favicon.png' type='image/png' />
        <style type="text/css">
            .scrollable .scrollable-content{
                padding: 0 17px 0 0;
            }            
        </style>
		
       <script src="/cedugenie/assets/vendor/modernizr/modernizr.js"></script>

	</head>
	<body>
<!--INDRA HEADER -->
<!--==========Header===========-->
<div id="preloader"></div>

<div class="main-holder">
<header class='main-wrapper header'>
	<div class="container apex">
		<div class="row">

			<nav class="navbar header-navbar" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<div class="logo navbar-brand">
						<a href="#" title="cEduGenie"></a>
					</div>
		      <button class='toggle-slide-left visible-xs collapsed navbar-toggle' type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"><i class="fa fa-bars"></i></button>
				</div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<div class="navbar-right">
						
						<div class="wr-soc">
							<div class="header-social">
								<ul class='social-transform unstyled'>
								<li>
									<a href='#' target='blank' class='front'><div class="fa fa-facebook"></div></a>
								</li>
								<li>
									<a href='#' target='blank' class='front'><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href='#' target='blank' class='front'><i class="fa fa-google-plus"></i></a>
								</li>
								<li>
									<a href='#' target='blank' class='front'><i class='fa fa-vimeo-square'></i></a>
								</li>
								</ul>
							</div>
						</div>
					</div>
		    </div><!-- /.navbar-collapse -->
			</nav>

		</div>
	</div>
</header>

<!-- INDRA HEADER ENDS -->

<!--INDRA CONTENT -->
<!--===========================-->
<!--==========Content==========-->
<div class='main-wrapper content'>
	<section class="relative software_slider">
		<div class="forma-slider">
			<div class="container">
				<div class="row">
					<div id="form_slider" data-anchor="form_slider">

						<ul class="form-bxslider">
							<li>
								<div class="list-forstart fin_1">
									<h2 class='h-Bold'>Notice Board</h2>
									<ul class='ul-list-slider Open-sansR' >
										<c:choose>
											<c:when test="${noticelist ne null && not empty noticelist}">	
                                           		<c:forEach var="notice" items="${noticelist}"><br/>
													<c:if test="${notice.noOfDay lt 5}">
													<blink>
                                                		<li>${notice.noticeName}<span>${notice.time}</span> <a href="#modalInfo" id="${notice.noticeCode}~${notice.noticeDesc}" class="modal-basic">more</a></li>
                                                	</blink>
													</c:if>	
													<c:if test="${notice.noOfDay ge 5}">
                                                		<li>${notice.noticeName}<span>${notice.time}</span> <a href="#modalInfo" id="${notice.noticeCode}~${notice.noticeDesc}" class="modal-basic">more</a></li>
                                                	</c:if>
                                                </c:forEach>
                                               </c:when>
                                              <c:otherwise>
                                               	<li>No New Notices To Display</li>
                                              </c:otherwise>
											</c:choose>
									</ul>
								</div>
								<div class="img-slider hidden-xs slide-man1 fin_2"></div>
							</li>
							
						</ul>
						<div class="bx-controls bx-has-pager bx-has-controls-direction" id='dafault_pager'></div>
				  </div>

					<div class="clearfix visible-xs visible-md"></div>

					<div class="container relative fin_3" id='elem-portable'>
						<div class="reg-now">
							<h2 class='medium-h text-center'>Take part in the training</h2>
							<br>
							<br>
							<form action="home.html" method="post">
							<div class="form-group mb-lg">
								<div class="input-group input-group-icon">
									<input name="userId" type="text" placeholder="Username: " class="form-control input-lg" required />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-user"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="form-group mb-lg">
								<div class="clearfix">
									
									<!-- <a href="pages-recover-password.html" class="pull-right">Lost Password?</a> -->
								</div>
								<div class="input-group input-group-icon">
									<input name="password" type="password" placeholder="Password: "class="form-control input-lg" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-8">
									<!-- <div class="checkbox-custom checkbox-default">
										<input id="RememberMe" name="rememberme" type="checkbox"/>
										<label for="RememberMe">Remember Me</label>
									</div> -->
								</div>
								<div class="col-sm-4 text-right">
									<button type="submit" class="btn btn-primary hidden-xs">Sign In</button>
									<button type="submit" class="btn btn-primary btn-block btn-lg visible-xs mt-lg">Sign In</button>
									<!-- <button type="submit" class='btn submit sub-form'>Sign In</button> -->
								</div>
							</div>

							

						</form>
						
							<%-- <form action="home.html" method="post" class='reg-now-visible' id='formIndex' accept-charset="utf-8" role="form">
								<div class='control-group'>
								  <input name="userId" type="text" placeholder='User Name' required>
								</div>
								<div class='control-group'>
								  <input type="password" name="password" class='insert-attr' required>
								</div>
								 <button type="submit" value="Sign In" class='btn submit sub-form'>Sign In</button>
							</form> --%>

						</div>
					</div>
				</div>
			</div><!-- end container -->
		</div>
	</section>
</div>

<!--===========================-->
<!--=========Footer============-->
<footer class='main-wrapper footer'>
  <div class="container bottom">
  <div class="clearifx"></div>
		<span class="copyright">
	&#169;Copyright 2018. CloudHead Technologies Pvt Ltd. All Rights Reserved.</span></div>
</footer>


<!-- Top -->
<div id="back-top-wrapper" class="visible-lg">
	<p id="back-top" class='bounceOut'>
		<a href="#top">
			<span></span>
		</a>
	</p>
</div>

<!-- end: page -->

<!-- INDRA CONTENT ENDS -->


		<!-- Vendor -->
		<script src="/cedugenie/assets/vendor/jquery/jquery.js"></script>		
        <script src="/cedugenie/assets/vendor/bootstrap/js/bootstrap.js"></script>		
        <script src="/cedugenie/assets/vendor/nanoscroller/nanoscroller.js"></script>
		
		<!-- Theme Base, Components and Settings -->
        <script src="/cedugenie/assets/javascripts/BootSideMenu.js"></script>
         <script type="text/javascript">
          var clock;
          $(document).ready(function(){              
              $('#about').BootSideMenu({side:"right"});
              
           // Create two variable with the names of the months and days in an array
              var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ]; 
              var dayNames= ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]
			  
              $.ajax({
      	        url: '/cedugenie/getTimeFromDB.html',
      	        dataType: 'json',
      	        success: function(dataDB) {
      	        	if(dataDB!= null ){
    	        		dataDB=dataDB.split(":");
    	        		var h=parseInt(dataDB[0]);
    	        		var m=parseInt(dataDB[1]);
    	        		var s=parseInt(dataDB[2]);
    	        		var d=parseInt(dataDB[3]);
    	        		var mn=parseInt(dataDB[4]);
    	        		mn=mn-1;
    	        		var y=parseInt(dataDB[5]);
    	        		var newDate = new Date(y,mn,d,h,m,s,00);
    	        		
    	        		//var newDate = new Date(2015,01,25,16,59,55,00);
    	                newDate.setDate(newDate.getDate());
    	                $('#Date').html(dayNames[newDate.getDay()] + " " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());

    	                var seconds=newDate.getSeconds();
    	                var minutes=newDate.getMinutes();
    	                var hours=newDate.getHours();

    	                $("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
    	                $("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    	                $("#hours").html(( hours < 10 ? "0" : "" ) + hours);

    	                setInterval( function() {
    	                	if(seconds==60){
    	                		seconds=0;
    	                		minutes++;
    	                		if(minutes==60){
    	                			minutes=0;
    	                			hours++;
    	                			if(hours==24)
    	                				hours=0;
    	                		}
    	                		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    	                		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    	                	}
    	                	$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
    	                	seconds++;
    	                	//console.log("Test"+seconds);
    	                }, 1000);
    	        		
    	        		
    	        	}else{
    	        		alert("Date Not Configured On DB Server");
    	        	}
      	       }
      		  });
              
              

            $(".modal-basic").each(function(){
           	   	$(this).click(function(){                        		
	           	   	var noticeCodeDesc=this.id;
	    			var noticeDesc = noticeCodeDesc.split('~');
	    			noticeDesc[1]=noticeDesc[1].replace(/\n/g, "<br/>");
	    			document.getElementById("modal_content").innerHTML = noticeDesc[1];
	    			
	    			
	    			
	    			
           		});
            });

              
          });
          </script>
        <script src="/cedugenie/assets/vendor/magnific-popup/jquery.magnific-popup.js"></script>
        <script src="/cedugenie/assets/javascripts/theme.js"></script>
        <script src="/cedugenie/assets/javascripts/theme.init.js"></script>
        <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
        <script src="/cedugenie/assets/javascripts/modernizr.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.bxslider.min.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.customSelect.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.validate.min.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.colorbox-min.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.waypoints.min.js"></script>
	<script src="/cedugenie/assets/javascripts/jquery.parallax-1.1.3.js"></script>
	<script src="/cedugenie/assets/javascripts/custom.js"></script>
	<!-- file loader -->
	<script src="/cedugenie/assets/javascripts/loader.js"></script>
	</body>


</html>