<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Academic Term" />
<meta name="keywords" content="Create Academic Term" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Academic Term</title>
<link rel="stylesheet" href="/sms/css/backoffice/createAcademicTerm.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="/sms/Cal/default.css"/>
<script src="/sms/Cal/zebra_datepicker.js"></script>
<script type="text/javascript" src="/sms/js/backoffice/academicTermAddDelete.js"></script>
<script>
 function validationPage(){
	var checkbox = document.getElementsByName('termCode');
	var dateOfCreation = document.getElementsByName('dateOfCreation');
	 for(var i=0;i<checkbox.length;i++)
		{ 
		 var splitedDateOfCreation = dateOfCreation[i].value.split("-");
		  var specificMonth = splitedDateOfCreation[1] - 1;
		  var dateOfCreationFormat = new Date(splitedDateOfCreation[2], specificMonth, splitedDateOfCreation[0]); //Year, Month, Date
		  var date = new Date();
		  var d = date.getDate();
		  var m = date.getMonth();
		  var y = date.getFullYear();
	      var presentDate = new Date(y,m,d); //present date
	      if (presentDate > dateOfCreationFormat) {
	    	  checkbox[i].setAttribute("disabled","disabaled");
		  }
		}
}

function submitActionValidation(){
	var flagToSet = 0;
	var selectedvalue = document.getElementById("academicYear").value;
	var classSelectedValue = document.getElementById("class").value;
	var courseSelectedValue = document.getElementById("course").value;
	var termStart = document.getElementsByName("startDate");
	var termEnd = document.getElementsByName("endDate");
	var academicTermType = document.getElementsByName("academicTermType"); 
			
	var namereg = /^[a-zA-Z]+( [a-zA-Z]+)*$/;	
	if(selectedvalue == ""){
		 document.getElementById("warningbox").style.visibility = 'visible';
		 document.getElementById("warningmsg").innerHTML =  "No Academic Year.";
		 flagToSet = 1;
		 return false;
	}

	if(classSelectedValue == ""){
		 document.getElementById("warningbox").style.visibility = 'visible';
		 document.getElementById("warningmsg").innerHTML =  "Select Class.";
		 flagToSet = 1;
		 return false;
	}

	if(courseSelectedValue == ""){
		 document.getElementById("warningbox").style.visibility = 'visible';
		 document.getElementById("warningmsg").innerHTML =  "Select Course.";
		 flagToSet = 1;
		 return false;
	}
	
	for(var typeNo = 0; typeNo < academicTermType.length; typeNo++){
		if(academicTermType[typeNo].value == ""){
			 document.getElementById("warningbox").style.visibility = 'visible';
			 document.getElementById("warningmsg").innerHTML =  "Select Academic Term.";
			 flagToSet = 1;
			 return false;
		} 

		if(!academicTermType[typeNo].value.match(namereg)){
			 document.getElementById("warningbox").style.visibility = 'visible';
			 document.getElementById("warningmsg").innerHTML = "Enter valid Academic Term.";
			 return false;
		 }

		if(termStart[typeNo].value == ""){
			 document.getElementById("warningbox").style.visibility = 'visible';
			 document.getElementById("warningmsg").innerHTML =  "Select Academic Term Start Date.";
			 flagToSet = 1;
			 return false;
		} 

		if(termEnd[typeNo].value == ""){
			 document.getElementById("warningbox").style.visibility = 'visible';
			 document.getElementById("warningmsg").innerHTML =  "Select Academic Term End Date.";
			 flagToSet = 1;
			 return false;
		} 
	}

	if( flagToSet == 0){
		document.getElementById("warningbox").style.visibility = 'collapse';
		document.getElementById("warningmsg").innerHTML =  "";
		submitAction();
		}
}

function submitAction(){
	 document.academicTerm.method="post";
	 document.academicTerm.action="insertAcademicTerm.html";
	 document.academicTerm.submit();             // back from the page
	 return true;
}		

function deleteAction(){
	 document.academicTerm.method="post";
	 document.academicTerm.action="deleteAcademicTerm.html";
	 document.academicTerm.submit();             // back from the page
	 return true;
}

function editAction(){
	 document.academicTerm.method="post";
	 document.academicTerm.action="editAcademicTerm.html";
	 document.academicTerm.submit();             // back from the page
	 return true;
}
function showenable(){
	
	var textboxname = document.getElementsByName('termType1');
	var textBoxStartDatename = document.getElementsByName('termStartDate');
	var textBoxEndDatename = document.getElementsByName('termEndDate');
	var checkbox = document.getElementsByName('termCode');
	var chk=0;
	  for(var i=0;i<textboxname.length;i++)
  		{ 
		  if(checkbox[i].type=="checkbox" && checkbox[i].checked){
			  document.getElementById("warningbox1").style.visibility = 'collapse';
			  document.getElementById("warningmsg1").innerHTML="";
			  chk=1;
			 
			  textboxname[i].removeAttribute("readonly");	
			  textBoxStartDatename[i].removeAttribute("readonly");			 
			  textBoxEndDatename[i].removeAttribute("readonly");			 
			  document.getElementById('submitPre').removeAttribute("disabled");
			  document.getElementById('deletePre').removeAttribute("disabled");
			  
			  var startId = textBoxStartDatename[i].id;
			  var endId = textBoxEndDatename[i].id;	
			  var startIdForCal = "#"+startId;
			  var endIdForCal = "#"+endId;
			  $(startIdForCal).Zebra_DatePicker();
			  $(endIdForCal).Zebra_DatePicker();
			  $(startIdForCal).Zebra_DatePicker({
			 	  format: 'd-m-Y'
			  });
			  $(endIdForCal).Zebra_DatePicker({
				 format: 'd-m-Y'
			  });
			 
			 	
			  
		  }
		  if (chk==0){
			    document.getElementById("warningbox1").style.visibility = 'visible';
				document.getElementById("warningmsg1").innerHTML="Must check some option!";
		  }
  		}
}
</script>
<script>
$(document).ready(function() {
	 $('#startDate').Zebra_DatePicker();
	 $('#endDate').Zebra_DatePicker();
	 
	 $('#startDate').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
	 $('#endDate').Zebra_DatePicker({
	  format: 'd/m/Y'
	});
	/*submit action*/
	var codeandtype = "";
	var code="";
	var termName="";
	var termStartDateVal = "";
	var termEndDateVal = "";
	var value = "";
	$('#submitPre').click(function(){
		$('input:checkbox').each(function()
				{	
			<c:forEach var="year" items="${AcademicYear}">
			value = '<c:out value="${year.academicYearCode}"></c:out>';
			$("#hiddenAcademicYear").val(value);
			</c:forEach>
					if ($(this).is(':checked'))
					{		
		    			code = $(this).val();
		    			termName = $(this).parent().next().next().next().find('input:text').val();
		    			termStartDateVal = $(this).parent().next().next().next().next().find('input:text').val();
		    			termEndDateVal = $(this).parent().next().next().next().next().next().find('input:text').val();
					}
					if(code != ""){
						codeandtype = code + "," + termName + "," + termStartDateVal + "," + termEndDateVal  +  "," + value + "/" + codeandtype;
					}
					
	    	
				});
		$('#hiddenTermType').val(codeandtype);
		editAction();
				});
				
				
				//sayani modify
		
			$.ajax({
		    url: '/sms/getClassForTermCreation.html',
		    dataType: 'json',
		    success: function(data) {
		    	if(data == ""){
		        	$("#infomsgbox").css('visibility','visible');
		        	$("#infomsg").text("class is not assigrned.");
	        	}
	        	if(data != ""){
		        	$("#infomsgbox").css('visibility','collapse');
			        $("#infomsg").text("");
		    		var arrClass = data.split(",");
		    		var classid=document.getElementById("class");
		    		
		    		for(var classlist=0;classlist<=arrClass.length-1;classlist++)
					{
		    			classid.add(new Option(arrClass[classlist], arrClass[classlist],null));
		    			
					}
		    		classid.remove(classlist); 
		    	}
		    } 
		}); 
				
		$("#class").change(function(){
			$.ajax({
		    url: '/sms/getCourseForTermCreation.html',
		    dataType: 'json',
		    data:"class=" + ($(this).val()),
		    success: function(data) {
		    	if(data == ""){
		        	$("#infomsgbox").css('visibility','visible');
		        	$("#infomsg").text("Course is not assigrned for particular Class.");
	        	}
	        	if(data != ""){
		        	$("#infomsgbox").css('visibility','collapse');
			        $("#infomsg").text("");
		    		  var arrCourse = data.split(",");
		    		var courseid=document.getElementById("course");
		    		
		    		for(var courselist=0;courselist<=arrCourse.length-1;courselist++)
					{
		    			courseid.add(new Option(arrCourse[courselist], arrCourse[courselist],null));
		    			
					}
		    		courseid.remove(courselist); 
		    	}
		    } 
		}); 
	});
});
</script>
</head>
<body onload="validationPage()">
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Academic Term
		</h1>
</div>
<form name="academicTerm" id="academicTerm" >	
<c:choose>
	<c:when test="${AcademicYear eq null}">	
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg">No Academic Year Found</span>
		</div>					
	</c:when>
<c:otherwise>
	<table id="categoryDetail" cellspacing="0" cellpadding="0" class="midsec">	
		<tr>
			<th>Academic Year :: </th>
			<td>
			<c:forEach var="year" items="${AcademicYear}">
				<input type="text" id="academicYear" name="academicYear" class="textfield1" value='<c:out value="${year.academicYearCode}"></c:out>' readonly="readonly"/>
			</c:forEach>
			</td>
			<th>Class :: </th>		
			<td>
				<select id="class" name="classObj" class="defaultselect">
					<option value="">select</option>
				</select>
			</td>
			<th>Course ::</th>
			<td>
				<select id="course" name="course.courseName" class="defaultMultipleSelect" multiple="multiple">
					<option value="">select...</option>
				</select>
			</td>
		</tr>		
		<tr>
			<th>Academic Term :: </th>	
			<td>
				<input type="text" id="academicTermType" name="academicTermType" class="textfield1" />
			</td>
			 <th>Start Date	:: </th>
			<td>
				<input type="text" id="startDate" class="startDateClass"  name="startDate" value=""  class="textfield1"/>
			</td>
			<th>End Date :: </th>		
			<td>
				<input type="text" id="endDate"  class="endDateClass" name="endDate" value="" class="textfield1" />
			</td> 
		</tr>
		<tr>
			<td colspan="6">
				<input type="button" id="add" name="add" class="addbtn"  onclick="addRow();"/>						
			</td>
		</tr>
	</table>
	<div class="btnsarea01">									
		<input type="submit" id="submit" name="submit" class="submitbtn" value="Submit" onclick="return submitActionValidation();"/>
	</div>
	</c:otherwise>
	</c:choose>	
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<br>
	<div class="infomsgbox" id="infomsgbox" >
	<span id="infomsg"></span>	
	</div>
	<c:choose>
		<c:when test="${TermName eq null}">			
			 <div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">No Academic Term Found</span>
			</div>	 	
		</c:when>
	<c:otherwise>
	<br/>	
	<table id="previousCategoryDetail" cellspacing="0" cellpadding="0" class="midsec1">		
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Academic Term
			</th>
		</tr>		
		<c:forEach var="term" items="${TermName}" varStatus="status" >		
		<tr>
			<td>
				<input type="checkbox" name="termCode" value="<c:out value="${term.termDetailsId}"></c:out>" id="termCode"></input>
			</td>
			<td>
				<input class="textfield1" type="text" name="termClass" id="termClass" readonly="readonly" value="<c:out value="${term.classObj}"></c:out>"/>
			</td>
			<td>
				<input class="textfield1" type="text" name="termCourse" id="termCourse" readonly="readonly" value="<c:out value="${term.course.courseName}"></c:out>"/>
			</td>
			<td>
				<input class="textfield1" type="text" name="termType1" id="termType1" readonly="readonly" value="<c:out value="${term.termName}"></c:out>"/>
			</td>
			<td>
				<input class="textfield1" type="text" name="termStartDate" id="termStartDate${status.index}" readonly="readonly"   value="<c:out value="${term.termStartDate}"></c:out>"/>
			</td>
			<td>
				<input class="textfield1" type="text" name="termEndDate" id="termEndDate${status.index}" readonly="readonly" value="<c:out value="${term.termEndDate}"></c:out>"/>
				<input class="textfield1" type="hidden" name="dateOfCreation" id="dateOfCreation${status.index}" readonly="readonly" value="<c:out value="${term.dateOfCreation}"></c:out>"/>
			</td>
		</tr>
		</c:forEach>		
	</table>
	</c:otherwise>
	</c:choose>
	<input type="hidden" id="hiddenTermType" name="hiddenTermType" value="" size="20"/>
	<input type="hidden" id="hiddenAcademicYear" name="hiddenAcademicYear" value=""/>
	<div class="btnsarea01">
		<input type="button" id="editPre" name="editPre" value="Edit" class="editbtn" onclick="showenable();"/>
		<input type="submit" id="deletePre" name="deletePre" value="Delete" class="clearbtn" disabled="disabled" onclick="deleteAction(); deleteRows();"/>
		<input type="submit" id="submitPre" name="submitPre" value="Submit" class="submitbtn" disabled="disabled"/>
	</div>	
	<br>
	<br>
	<div class="warningbox" id="warningbox1" >
		<span id="warningmsg1"></span>	
	</div>
</form>
</body>
</html>