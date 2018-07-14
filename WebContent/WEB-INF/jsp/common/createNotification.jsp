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
<title>Email</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .content-with-menu-container{
			border:1px solid #eee;
		}
		/* #myFileInput {
    		display:none;
		} */
		
		.custom-file-upload {
			    border: 0px solid #ccc; */
			    display: inline-block;
			    /* padding: 6px 12px; */
			    cursor: pointer;
			    color: #abb4be; 
			}
		#file-name{
			color: #fff; 
		}
</style>
</head>
<body>
	<header class="page-header">
			<h2>Email</h2>
		</header>
		
	<c:if test="${status eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${updateStatus}</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>${updateStatus}</strong>	
			</div>
		</c:if>
	<div class="content-padding">
	<div class ="">
		<section class=" content-with-menu-has-toolbar mailbox">
						<div class="content-with-menu-container">						
							
							<div class="row">
                                <div class="col-md-12">
								<div class="mailbox-compose">
									<form class="form-horizontal form-bordered form-bordered" name="createNotification" id="createNotification" method="POST" action="createNotification.html" enctype="multipart/form-data">
							
										<div class="form-group form-group-invisible">
											<label for="to" class="control-label-invisible">To:</label>
											<div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
												<input id="notificationReplyTo" name = "notificationReplyTo" type="text" class="form-control form-control-invisible" data-role="tagsinput" data-tag-class="label label-primary" value="">
											</div>
										</div>
							
										<!-- <div class="form-group form-group-invisible">
											<label for="cc" class="control-label-invisible">CC:</label>
											<div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
												<input id="cc" type="text" class="form-control form-control-invisible" data-role="tagsinput" data-tag-class="label label-primary" value="">
											</div>
										</div> -->
							
										<div class="form-group form-group-invisible">
											<label for="subject" class="control-label-invisible">Subject:</label>
											<div class="col-sm-offset-2 col-sm-9 col-md-offset-1 col-md-10">
												<input name="notificationSubject" id="notificationSubject" type="text" class="form-control form-control-invisible" value="">
											</div>
										</div>
                                        
                                        
                                        <div class="form-group form-group-invisible">
											<div class="col-sm-12 col-md-12">												
                                                <div class="content-with-menu-container" data-mailbox data-mailbox-view="compose">	
													<div class="mailbox-compose">
															
												
															<div class="form-group">
																<div class="compose">
																	<div id="compose-field" class="compose-control">
																	Hello Demo
																	</div>
																</div>
															</div>
														
													</div>
												</div>
											</div>
										</div>
										<div class="inner-toolbar clearfix">
											<ul>
												<li>
													<a href="#" onclick = "submitButtonCall()"><i class="fa fa-send-o mr-sm" ></i> Send</a>
													<input type="hidden" class="form-control" name="emailBody"  id= "emailBody"/>
													<input type="hidden" class="form-control" name="status"  id= "status" value = "attachment"/>
												</li>
												<li>
													<a href="#modalHeaderColorPrimary" class="modal-basic"><i class="fa fa-calendar mr-sm"></i> Meeting Schedule</a>
												</li>
												<!-- <li>
													
													<label for="file-upload" class="custom-file-upload">
												        <i class="fa fa-paperclip mr-sm"></i> Attachment
												    </label>
												    <input id="file-upload" name='upload_cont_img' type="file" style="display:none;">
												    <label id="file-name"></label>
												</li> -->
												<li>
													<label for="myFileInput" class="custom-file-upload">
												        <i class="fa fa-paperclip mr-sm"></i> Attachment
												    </label>
													<!--  <a href="#" onclick="document.getElementById('myFileInput').click()"><i class="fa fa-paperclip mr-sm" aria-hidden="true"></i> Attachment</a>  -->
													<input type="file" id="myFileInput" name="uploadFile.emailRelatedFile" style="display:none;"/>
													<label id="file-name"></label>
													<input type="hidden" id="meetingDate" name="meetingDate" />
													<input type="hidden" id="meetingLocation" name="meetingLocation" />
													<input type="hidden" id="meetingStartTime" name="meetingStartTime" />
													<input type="hidden" id="meetingEndTime" name="meetingEndTime" />
												</li>
											</ul>
										</div>
									</form>
								</div>
								
                                </div>    
							</div>
						</div>
					</section>
					<div id="modalHeaderColorPrimary" class="modal-block modal-header-color modal-block-primary mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Meeting Schedule</h2>
							</header>
							<div class="panel-body">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
                                            <label class="control-label">Date</label>
                                            <div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</span>
												<input type="text" data-plugin-datepicker="" id = "dateValue" name = "dateValue" class="form-control">
											</div>
                                        </div>										
									</div>
									<div class="col-sm-6">
										<div class="form-group">
                                            <label class="control-label">Location</label>
                                            <input type="text" class="form-control" name="location" id = "location" placeholder="">
                                        </div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
                                            <label class="control-label">Start Time</label>
                                            <div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-clock-o"></i>
												</span>
												<input type="text" data-plugin-timepicker="" id = "startTime" name = "startTime" class="form-control">
											</div>
                                        </div>
									</div>
									<div class="col-sm-6">
										<div class="form-group">
                                            <label class="control-label">End Time</label>
                                            <div class="input-group">
												<span class="input-group-addon">
													<i class="fa fa-clock-o"></i>
												</span>
												<input type="text" data-plugin-timepicker="" id = "endTime" name = "endTime" class="form-control">
											</div>
                                        </div>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button class="btn btn-primary modal-dismiss" onclick = "setMeetinginvitationParameter()">Confirm</button>
										<button class="btn btn-default modal-dismiss">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>
				<!-- </section> -->
			</div>
		</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
 <script type="text/javascript">
/*  
 $(function()
		    {
		        $('#myFileInput').on('change',function ()
		        {
		            var filePath =  URL.createObjectURL(event.target.files[0]);//document.getElementById("myFileInput").files[0].name; //$(this).val();
		            alert("filePath=="+filePath);
		            console.log(filePath);
		        });
		    });
 
  */
 function submitButtonCall()
 {
	/// alert("start");
	
	 var emailBody = $('.note-editable').html();
	
	document.getElementById("emailBody").value = emailBody;
	
	document.createNotification.submit();
	//alert("end");
 }
  
  function setMeetinginvitationParameter(){

	  var meetingDate = document.getElementById("dateValue").value;
	  var meetingLocation = document.getElementById("location").value;
	  var meetingStartTime = document.getElementById("startTime").value;
	  var meetingEndTime = document.getElementById("endTime").value;
	
	  
	  document.getElementById("meetingDate").value = meetingDate;
	  document.getElementById("meetingLocation").value = meetingLocation;
	  document.getElementById("meetingStartTime").value = meetingStartTime;
	  document.getElementById("meetingEndTime").value = meetingEndTime;
	  document.getElementById("status").value = "ics";
	  
  }
  
  $("#myFileInput").change(function(){
	  $("#file-name").text(" - "+this.files[0].name);
	});
</script> 


</body>
</html>