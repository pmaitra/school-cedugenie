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
<title>Email Event For Template</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2> Event For Template</h2>
		</header>
		<div class="content-padding">
			<c:if test="${status eq 'fail'}">
				<div class="alert alert-danger" id="errormsgbox">
					<strong>Problem in Event Creation!!</strong>	
				</div>
			</c:if>
			<c:if test="${status eq 'success'}">
				<div class="alert alert-success" id="successboxmsgbox">
					<strong>Event is created Successfully!!</strong>	
				</div>
			</c:if>
					<div class="row">
						<div class="col-md-7">
							<form action="insertEventForTemplate.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Event For Template</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											 <label class="control-label">Event Name<span aria-required="true" class="required">*</span></label>
									         <input type="text" class="form-control" name="eventName"  id= "eventName"  placeholder="Enter Event Name" required>
										</div>
									</div>
									 <div class="alert alert-warning" id="message1" style="display: none">
							  							<span></span>	
									</div>	
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" class="btn btn-primary" onclick="return validating()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						 <c:choose>
							<c:when test="${emailEventTemplateList eq null || empty emailEventTemplateList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Event is created Yet!!</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>
						<div class="col-md-8">
							<form name="editEmailEventTemplate" id="editEmailEventTemplate" action="editEmailEventTemplate.html" method="post">
							<input type="hidden" name="emailCode" id="emailCode">
							<input type="hidden" name="serialId" id="serialId">
							<input type="hidden" name="getEventName" id="getEventName">
							
							
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Existing Events</h2>
							</header>
							<div class="panel-body" id="datatable-editable">
								 <div id="oldEventName">
										<c:forEach var="event" items="${emailEventTemplateList}" varStatus="i">
											<input type="hidden" name="oldEventName" id= "oldEventName" value="${event.eventName}">
										</c:forEach>
								</div>
								<table class="table table-bordered table-striped mb-none"  id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Event Name</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="emailEvent" items="${emailEventTemplateList}" varStatus="i" >
										<tr>
											<td>
												<input type="hidden" id="eventCode${i.index}" name="eventCode${i.index}" value="${emailEvent.eventCode}">
												<input type="hidden" id="eventName${i.index}" name="eventName${i.index}" class="form-control" value="${emailEvent.eventName}"/>
												${emailEvent.eventName}
											</td>
											 <td class="actions">
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showEventDetails('${emailEvent.eventName}','${i.index}')"><i class="fa fa-pencil"></i></a>
													<%-- <a href="inactiveStandard.html?serialId=${emailEvent.serialId}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a> --%>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="hidden on-editing save-row" id="save${i.index}" ><i class="fa fa-save"></i></a>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
							<!-- popup Window code -->
											   <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                                            <section class="panel">
                                                <header class="panel-heading">
                                                    <h2 class="panel-title">Event Details</h2>
                                                </header>
                                                <div class="panel-body">
                                                    <table class="table table-bordered table-striped mb-none" id = "emailEventDetails">
                                                        <thead>
                                                            <tr>
                                                                <th>Event Name</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            
                                                        </tbody>
                                                    </table>
                                                   <div class="alert alert-warning" id="warningmsg1" style="display: none">
							  							<span></span>	
													</div>	
                                                </div>
                                                <footer class="panel-footer">
													<div class="row">
														<div class="col-md-12 text-right">
															<button id="updateEmailEvent" class="btn btn-success">Update</button>
															<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
														</div>
													</div>
												</footer>
                                            </section>
                                        </div>   
						</form>
					</div>
					</c:otherwise>
					</c:choose>	
					</div>
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createStandard.editable.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
 <script type="text/javascript">
 function showEventDetails(eventName,index)
 {
 	$('#emailEventDetails> tbody').empty();
  	if(eventName != null && eventName!=""){
  		var row = "<tbody>";
  		row += "<tr><td><input type='text' name='eventName' id='eventName' class = 'form-control'  value='"+eventName+"'> </td></tr>";
  		$("#emailEventDetails").append(row);
  	}
  	
  	$('#modalInfo').fadeIn("fast");
  	var btn = document.getElementById("updateEmailEvent");
  	btn.setAttribute("onclick","saveData('"+index+"','"+eventName+"');");
 }
 function saveData(rowId,eventName){
		var eventName=document.getElementById("eventName").value;
		document.getElementById("serialId").value=rowId;
		document.getElementById("getEventName").value = eventName;
		 var status=validatingField(rowId,eventName);
		if(status==true){ 
			document.editEmailEventTemplate.submit();
		 } 
	};
	
	function validatingField(rowId,eventName)
	{
		var regex = /^[a-zA-Z\s]+$/;
		var oldEventName=document.getElementsByName("oldEventName");
		
		var newEventName=document.getElementById("getEventName").value;
		newEventName=newEventName.trim();
		newEventName=newEventName.toUpperCase();
		
		for(var i=0;i<document.getElementsByName("oldEventName").length;i++)
			{
				if(oldEventName[i].value==newEventName)
					{
						document.getElementById("warningmsg1").style.display = 'block';			
						document.getElementById("warningmsg1").innerHTML = "Event Name Alerady Exists.";
						return false;
					}
			}
		
		if (newEventName ==""||newEventName==null)
		{
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Event Name must not be empty.";
			return false;
		}
		
		
		else if (regex.test(newEventName)==false)
		    {
		    	document.getElementById("warningmsg1").style.display = 'block';			
				document.getElementById("warningmsg1").innerHTML = "Event name can contain alphabets and spaces between words !!";
				return false;
		    }
		else
			{
			return true;
			}
	};
	
	function validating()
	{
		var newEventName=document.getElementById("eventName").value;
		newEventName=newEventName.trim();
		newEventName=newEventName.toUpperCase();

		var oldEventName=document.getElementsByName("oldEventName");
		for(var i=0;i<oldEventName.length;i++)
			{
			if(oldEventName[i].value==newEventName)
				{
					document.getElementById("message1").style.display = 'block';			
					document.getElementById("message1").innerHTML = "Event Name already exists.";
					return false;
				}
			}
		return true;
		}
 </script>
 
</body>
</html>