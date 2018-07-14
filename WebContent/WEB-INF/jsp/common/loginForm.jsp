<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="fixed dark">

    
<head>

		<!-- Basic -->
		<meta charset="UTF-8">

		<title>Dashboard | cEduGenie Admin - BETA</title>
		<meta name="keywords" content="" />
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		
		<!-- Vendor CSS -->
		<link rel="stylesheet" href="/cedugenie/assets/vendor/bootstrap/css/bootstrap.css" />

		<link rel="stylesheet" href="/cedugenie/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="/cedugenie/assets/vendor/magnific-popup/magnific-popup.css" />

		<!-- Theme CSS -->
		<link rel="stylesheet" href="/cedugenie/assets/stylesheets/theme.css" />
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
		<c:choose>
			<c:when test="${SchoolDetails ne null}">
		        <div id="about">
		            <div class="scrollable" data-plugin-scrollable style="height: 320px;">
		            <div style="padding:15px" class="scrollable-content">
		                <h2>Welcome to ${SchoolDetails.schoolDetailsName}</h2>
		                <p>${SchoolDetails.schoolDetailsDesc}</p><br/>
			            <p>Principal Name <span style="color:green;font-weight: bolder;font-style: italic;">${SchoolDetails.principal}</span></p><br/>
			            <p>Affiliated To ${SchoolDetails.boardUniversity}</p><br/>
			            <p>Address ${SchoolDetails.schoolAddress}<br/><br/>Contact Number - ${SchoolDetails.phone} <br/> Email - ${SchoolDetails.email}</p>  
		            </div>
		            </div>
		        </div>
		    </c:when>
		    <c:otherwise>
				<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">No School Details Found</span>	
				</div>	
			</c:otherwise>
		</c:choose>
		<!-- start: page -->
		<section class="body-sign">
            <div class="row center-sign">
                <div class="col-md-12">                    
                    <div id="Date"></div>
                    <div class="clock">					
						
						<ul>
							<li id="hours"> </li>
						    <li id="point">:</li>
						    <li id="min"> </li>
						    <li id="point">:</li>
						    <li id="sec"> </li>
						</ul>					
					</div>
                </div>
                <div class="col-md-6">
                     <div class="center-sign"> 
                        <a href="#" class="logo pull-left">
                            <img src="/cedugenie/assets/images/logo-blackbg.png" height="72" alt="" />
                        </a>
                        <div class="panel panel-sign">
                            <div class="panel-title-sign mt-xl text-right">
                                <h2 class="title text-uppercase text-weight-bold m-none"><i class="fa fa-list-alt mr-xs"></i> Notice &nbsp; Board</h2>
                            </div>
                            <div class="panel-body">                                                                            
                                <div class="input-group input-group-icon"></div>                                   
                               <div class="scrollable" data-plugin-scrollable style="height: 220px;">
                                    <div class="row scrollable-content">
                                        <div class="col-sm-12">
                                            <ul class="noticelist">
                                            
                                            
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
                                    </div>
                                </div>
                                
                            </div>
                        </div>                    
                    </div>
                </div>
                <div class="col-md-6">                    
                    <div class="center-sign">
                        <a href="#" class="logo pull-left">
                            <img src="/cedugenie/assets/images/logo.png" height="72" alt="" />
                        </a>

                        <div class="panel panel-sign">
                            <div class="panel-title-sign mt-xl text-right">
                                <h2 class="title text-uppercase text-weight-bold m-none"><i class="fa fa-user mr-xs"></i> Sign &nbsp; In</h2>
                            </div>
                            <div class="panel-body">
                            <form action="home.html" method="post">
							<div class="form-group mb-lg">
								<label>Username</label>
								<div class="input-group input-group-icon">
									<input name="userId" type="text" class="form-control input-lg" required />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-user"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="form-group mb-lg">
								<div class="clearfix">
									<label class="pull-left">Password</label>
									<!-- <a href="pages-recover-password.html" class="pull-right">Lost Password?</a> -->
								</div>
								<div class="input-group input-group-icon">
									<input name="password" type="password" class="form-control input-lg" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-8">
									<div class="checkbox-custom checkbox-default">
										<input id="RememberMe" name="rememberme" type="checkbox"/>
										<label for="RememberMe">Remember Me</label>
									</div>
								</div>
								<div class="col-sm-4 text-right">
									<button type="submit" class="btn btn-primary hidden-xs">Sign In</button>
									<button type="submit" class="btn btn-primary btn-block btn-lg visible-xs mt-lg">Sign In</button>
								</div>
							</div>

							

						</form>
					</div>
                        </div>                    
                    </div>
                </div>
                <div class="col-md-12">
                    <p class="text-center text-muted mt-md mb-md">&copy; Copyright 2018. CloudHead Technologies Pvt Ltd. All Rights Reserved.</p>
                </div>
            </div>
           
		</section>
         <div id="modalInfo" class="modal-block modal-header-color modal-block-primary mfp-hide">
            <section class="panel">
                <header class="panel-heading">
                    <h2 class="panel-title">Notice Board</h2>
                </header>
                <div class="panel-body">
                    <div class="modal-wrapper">
                        <div class="modal-icon">
                            <i class="fa fa-list-alt"></i>
                        </div>
                        <div class="modal-text">
                            <p id="modal_content"></p>
                        </div>
                    </div>
                </div>
                <footer class="panel-footer">
                    <div class="row">
                        <div class="col-md-12 text-right">
                            <button class="btn btn-primary modal-dismiss">OK</button>
                        </div>
                    </div>
                </footer>
            </section>
        </div>
		<!-- end: page -->

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
	</body>


</html>