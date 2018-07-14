$("#standard").change(function(){
	var table = document.getElementById("sectionbody");
	var sectionObject=document.getElementById("section");
	var options=" <select class='form-control' name='className' id = 'className0' onclick='valuedetails()'><option value=''>Select...</option>";
	$.ajax({
        url: '/cedugenie/getSectionsAgainstCourse.html',
        dataType: 'json',
        data: "standard=" + ($(this).val()),
        success: function(dataDB) {
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split(";");
				for (var i=0;i<arr.length;i++){
					var section = arr[i].split("*");
					options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
				}
				options=options+"</select>";
				document.getElementById("section").innerHTML=options;
			}else{
				document.getElementById("javascriptmsg2").style.display='block';
        		document.getElementById("javascriptmsg2").innerHTML="Section not found for this Course";
			}
       }
	});	
});

$("#section").change(function(){
	var standard= $("#standard").val();
	var section= $("#section").val();
	var options=" <select class='form-control' name='sectionName' id = 'sectionName0' onclick='valuedetails()'><option value=''>Select...</option>";
	$.ajax({
        url: '/cedugenie/getStudentRollAgainstStandardAndSection.html',
        dataType: 'json',
        data: "standard=" + standard+ "&section=" + section,
        success: function(dataDB) {
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split(";");
				for (var i=0;i<arr.length;i++){
					var student = arr[i].split("*");
					options=options+"<option value='"+student[0]+"'>"+student[0]+'-'+student[1]+"</option>";
				}
				options=options+"</select>";
				document.getElementById("rollNumber").innerHTML=options;
        		}
        	else
        		{
        			alert("No student assigned to standard" + standard + " section" + section);
        		}
        }
	});
	
});

$("#disciplinaryCode").change(function(){
	
	$.ajax({
        url: '/cedugenie/getDescriptionAgainstDisciplinaryCode.html',
        dataType: 'json',
        data: "disciplinaryCode=" +$("#disciplinaryCode").val(),
        success: function(dataDB) {
        	
        	$("#description").html(dataDB);
        }
        	
      });
});

