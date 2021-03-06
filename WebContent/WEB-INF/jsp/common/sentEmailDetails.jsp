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

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .content-with-menu-container{
				border:1px solid #eee;
		}
		 .attached{
            	background: #f6f6f6;
    			border-bottom: 1px solid #DADADA;
    			margin: 0px !important;
    			padding: 6px 0;
            }
            .attached a:hover{
            	text-decoration: underline;
            	color: #0088cc;
            }
            .meeting{
            	background: #f6f6f6;
    			border-bottom: 1px solid #DADADA;
    			margin: 0px !important;
    			padding: 6px 0;
            }
</style>
</head>
<body>
	<header class="page-header">
			<h2>SENT</h2>
		</header>
	<div class="content-padding">
		<div class ="">
			<div class="row">
						<div class="col-md-12 col-lg-12 col-xl-6">
							<div class="panel">
								<div class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="fa fa-mail-reply"></a>
										<!-- <a href="#" class="fa fa-mail-reply-all"></a> -->
									</div>
				
									<p class="panel-title">Subject <i class="fa fa-angle-right fa-fw"></i> ${emailDetailsObj.emailDetailsSubject}</p>
								</div>
								<c:if test="${type ne 'ics'}">
									<div class="row attached">
										<div class="col-sm-5">
											<small> <b>From</b>  <i class="fa fa-angle-right fa-fw"></i>  ${emailDetailsObj.emailDetailsSender} - ${emailDetailsObj.time}</small>
										</div>
										<div class="col-sm-7">
											<small> <b>Attachment</b>  <i class="fa fa-angle-right fa-fw"></i> <!-- <a href="">Plan_document.doc</a> --> 
												<c:forEach var="file" items="${allFiles}" varStatus="i">
													<a href = "viewDownloadEmailAttachment.html?folderParam=${filePath}&fileParam=${file}">${file}</a>
												</c:forEach>
											</small>
										</div>
									</div>
								</c:if>
								<c:if test="${type eq 'ics'}">
									<div class="row attached">
									<div class="col-sm-5">
										<small> <b>From</b>  <i class="fa fa-angle-right fa-fw"></i>  ${emailDetailsObj.emailDetailsSender} - ${emailDetailsObj.time}</small>
									</div>
									</div>
									<div class="row meeting">
											<!-- <div class="col-sm-3">
												<small> <b>Meeting Schedule :- </b> <b>Date </b>  <i class="fa fa-angle-right fa-fw"></i> July 07, 2014 </small>
											</div> -->
											<div class="col-sm-4">
												<small> <b>Start Time </b>  <i class="fa fa-angle-right fa-fw"></i> ${startTime} </small>
											</div>
											<div class="col-sm-4">
												<small> <b>End Time </b>  <i class="fa fa-angle-right fa-fw"></i> ${endTime} </small>
											</div>	
											<div class="col-sm-3">
												<small> <b>Location</b>  <i class="fa fa-angle-right fa-fw"></i> ${location}</small>
											</div>
											
											<div class="col-sm-2">
												<small> <a href="updateIntoMyEvents.html?startTime=${startTime}&endTime=${endTime}&subject=${emailDetailsObj.emailDetailsSubject}&userId=${userId}&emailBody=${emailDetailsObj.emailDetailsDesc}" class="btn btn-primary btn-xs"><i class="fa fa-check"></i> Accept</a> </small>
											</div>
										</div>
								</c:if>
								<div class="panel-body">
									${emailDetailsObj.emailDetailsDesc}
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>