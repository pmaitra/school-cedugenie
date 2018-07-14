<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Standard-Student-Subject Mapping</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}.mb-md{
		   display: none;
	}
</style>
</head>
<body>
<header class="page-header">
	<h2>Standard-Student-Subject Mapping</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:if test="${insertUpdateStatus eq 'success'}">
		<div class="alert alert-success">
			<strong>${msg}</strong>
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>${msg}</strong>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${courseList eq null || empty courseList}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<c:if test="${courseList eq null || empty courseList}">
						<span id="errormsg">Standard Not Found</span>	
					</c:if>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form name="subjectform" id="subjectform" action="createStudentCourseSubjectMapping.html" method="POST" >
				<input type="hidden" name="type" value="create">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Standard</h2>										
							</header>
							<div style="display: block;" class="panel-body">                                       
							   <div class="form-group">
								   <label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
								   <select class="form-control" id="course" name="course" required>
										<option value="">Select..</option>
										<c:forEach var="course" items="${courseList}" varStatus="i">
											<option value="${course.courseCode}">${course.courseName}</option>
										</c:forEach>
								   </select>
							   </div>
							</div>
						</section>
                    </div>
					
					<div id="studentsDetails" class="col-md-12" style="display: none">	
                        <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<div class="alert alert-danger" id="javascriptmsg" style="display: none">
								  	<span></span>	
								</div>
								<h2 class="panel-title">Students</h2>
							</header>
							
							<div class="panel-body">
								<div class="form-group">
			                        <table class="table table-bordered table-striped mb-none">
										<thead>
											<tr>
												<th>Select</th>
												<th>Roll Number</th>
												<th>Name</th>
											</tr>
										</thead>
										<tbody id="nameAndRoll">
										</tbody>
									</table>
		                        </div>
	                        </div>
                        </section>
					</div>
					<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
				  		<span> </span>	
					</div>
                    <div id="coursesDetails" class="col-md-12" style="display: none">	
		                <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Mapping</h2>
							</header>
							<div class="panel-body" id="panelBody1">
							</div>
			                <footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" onclick=" return validate()">Submit</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
					</div>
				</div>
			</form:form>
		</c:otherwise>
	</c:choose>
	<div class="alert alert-warning" id="warningmsg" style="display: none" align="center">
	  	<span></span>	
	</div>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready( function(){
	$("#course").change(function (){
		$.ajax({
			url: '/cedugenie/getSubjectsForACourse.html',
			dataType: 'json',
			data: "course=" + $("#course").val(),
			success: function(data) {
				data=data.split("*~*");
				var subjectGroupArray = [];
				for(var i=0;i<data.length-1;i++){
					var subjectAndGroup=data[i].split("#@#");
					subjectGroupArray.push(subjectAndGroup[0]);
				}
				var tmp = [];
			    for(var i = 0; i < subjectGroupArray.length; i++){
			        if(tmp.indexOf(subjectGroupArray[i]) == -1){
			        tmp.push(subjectGroupArray[i]);
			        }
			    }
			    
			    var text1='';
			    var counter=0;
			    for(var i=0;i<tmp.length;i++){
			    	if(counter==0){
						text1=text1+'<div class="row">';
					}
			    	text1=text1+'<div class="col-md-3"><div class="well well-sm info no-margin"><div>Subject Name :: '+tmp[i];
			    	text1=text1+'</div></div><table class="table table-bordered table-striped mb-none">';
			    	text1=text1+'<thead><tr><th>Select</th><th>Subject Name</th></tr></thead><tbody id="'+tmp[i]+'">';
			    	
			    	text1=text1+'</tbody></table></div>';
			    	
			    	counter++;
					if(counter==4){
						text1=text1+"</div><hr>";
						counter=0;
					}
			    }
			    if(counter!=0){
			    	text1=text1+"</div><hr>";
				}
			    text1=text1+'</div>';
			    document.getElementById("panelBody1").innerHTML=text1;
			    
			    for(var i=0;i<data.length-1;i++){
			    	var subjectAndGroup=data[i].split("#@#");
			    	text1='<tr><td><input type="checkbox" value="'+subjectAndGroup[1]+'" name="subject" id="subject'+subjectAndGroup[1]+'" onclick="removemsg()"></td><td>'+subjectAndGroup[1]+'</td></tr>';
			    	var row=document.getElementById(subjectAndGroup[0]).innerHTML;
			    	row=row+text1;
			    	
			    	document.getElementById(subjectAndGroup[0]).innerHTML=row;
			    }
			}
		});
		$.ajax({
			url: '/cedugenie/getStudentsNameAndRollForCourse.html',
			dataType: 'json',
			data: "course=" + $("#course").val(),
			success: function(data) {
				$("#nameAndRoll").empty();
				if(data!=""){
					document.getElementById("warningmsg").style.display = 'none';
					document.getElementById("studentsDetails").style.display = 'block';
					document.getElementById("coursesDetails").style.display = 'block';
					data=data.split("~");
					var tableBody=document.getElementById("nameAndRoll");
					
					for(var i=0;i<data.length-1;i++){
						var namrAndRoll=data[i].split("*");
						
		                var rowCount = tableBody.rows.length;
		                var row = tableBody.insertRow(rowCount);
		                
		                var cell, element;
		                cell = row.insertCell(0);	
		                element = document.createElement("input");
		                element.type = "checkbox";
		                element.name="student";
		                element.value=namrAndRoll[2];
		                cell.appendChild(element);
		                
		                cell = row.insertCell(1);	
		                element = document.createElement("label");
		                element.innerHTML=namrAndRoll[0];
		                cell.appendChild(element);
		                
		                cell = row.insertCell(2);	
		                element = document.createElement("label");
		                element.innerHTML=namrAndRoll[1];
		                cell.appendChild(element); 
					}
				}else{
					document.getElementById("warningmsg").style.display = 'block';			
					document.getElementById("warningmsg").innerHTML = "<b>There is no student left for mapping.</b>";
				}
			}
		});
	});
});
		
function validate(){
	
	var table=document.getElementsByName("subject");
	$content = $('#nameAndRoll').html();
	/* var allName = "";
	$("input [name='student']").each(function(){
		allName = allName + $(this).val+";";
	}); */
	var name = document.getElementsByName("student");
	/* for(var i=0;i<name.length;i++){
		if(name[i].checked){
			document.getElementById("javascriptmsg").style.display = 'none';
			document.getElementById("javascriptmsg1").style.display = 'none';
			for(var j=0;j<table.length;j++){
				document.getElementById("javascriptmsg1").style.display = 'none';
				if(table[j].checked){
					document.getElementById("javascriptmsg1").style.display = 'none';
					return true;
				}else{
					document.getElementById("javascriptmsg1").style.display = 'block';			
					document.getElementById("javascriptmsg1").innerHTML = "No Subjects Selected yet";
					return false;
				}
			
			}
		}else{
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "No Student has been Selected yet";
			j=0;
			return false;
		}
	}  */
} 
		
function removemsg(){
	document.getElementById("javascriptmsg1").style.display = 'none';
	var table=document.getElementsByName("subject");
	for(var i=0;i<table.length;i++){
		if(table[i].checked){
			document.getElementById("javascriptmsg").style.display = 'none';
		}
	}
	var name=document.getElementsByName("student");
	for(var j=0;j<name.length;j++){
		if(name[j].checked){
			document.getElementById("javascriptmsg1").style.display = 'none';	
		}
	}
}
</script>
</body>
</html>