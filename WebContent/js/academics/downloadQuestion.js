function getQuestionFolderNames(element, dirName){	
	document.getElementById("downloadDiv").setAttribute("style", "visibility: collapse;");
	if($(element.id).val() != ''){
		var paperDirName = '';
		if(dirName == "standardDir"){
			paperDirName = $(element).val();
		}
		if(dirName == "examDir"){
			paperDirName = $("#year").val()+"/"+$(element).val();
		}
		if(dirName == "subjectDir"){
			paperDirName = $("#year").val()+"/"+$("#standard").val()+"/"+$(element).val();
		}
		if(dirName == "paperFiles"){
			paperDirName = $("#year").val()+"/"+$("#standard").val()+"/"+$("#exam").val()+"/"+$(element).val();
		}
		$.ajax({
	        url: '/icam/getQuestionFolderNames.html',
	        data: "paperDirName=" + paperDirName,
	        dataType: 'json',
	        success: function(data) {
        		var options = "<option value=''>Select</option>";
        		var rowText = "<tr><th>Download Question Papers</th></tr>";
	        	if(data != null && data != ""){
	        		data = data.split("~");
	        		for (var i=0;i<data.length-1;i++){
	        			if(dirName == "paperFiles"){
	        				rowText = rowText + "<tr><td><a href='viewDownloadQuestionPapers.html?folderParam="+paperDirName+"&fileParam="+data[i]+"'>" + data[i] + "</a></td></tr>";
	        			}else{
	        				options = options+"<option value='"+data[i]+"'>"+data[i]+"</option>";
	        			}
					}
	        		if(dirName == "standardDir"){
	        			var standardObject = document.getElementById("standard");
	        			standardObject.innerHTML = options;
	        		}
	        		if(dirName == "examDir"){
	        			var examObject = document.getElementById("exam");
	        			examObject.innerHTML = options;
	        		}
	        		if(dirName == "subjectDir"){
	        			var subjectObject = document.getElementById("subject");
	        			subjectObject.innerHTML = options;
	        		}
	        		if(dirName == "paperFiles"){
	        			document.getElementById("downloadDoc").innerHTML = rowText;
	        			document.getElementById("downloadDiv").setAttribute("style", "visibility: visible;");
	        		}
	        	}
	        }
		});
	}	
}