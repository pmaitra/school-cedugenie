function closeTask(rowId){
	var ticketCode  = document.getElementById("ticketCode"+rowId).value;
	document.getElementById("ticketCode").value = ticketCode;
	var taskId  = document.getElementById("taskId"+rowId).value;
	document.getElementById("taskId").value = taskId;
	var taskName = document.getElementById("taskName"+rowId).value;
	document.getElementById("taskName").value = taskId;
	var taskComment = document.getElementById("taskcomment").value;
	document.getElementById("taskComment").value = taskComment;
	if(null != taskComment && taskComment != ""){
		window.location="closedTask.html?saveId="+rowId+"&ticketCode="+ticketCode+"&taskId="+taskId+"&taskName="+taskName+"&taskComment="+taskComment;
	}else{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Comment is mandatory.";
		return false;
	}
};

function approveTask(rowId){
	var taskComment = document.getElementById("taskcomment").value ;
	document.getElementById("taskComment").value = taskComment;
	var ticketCode  = document.getElementById("ticketCode"+rowId).value;
	document.getElementById("ticketCode").value = ticketCode;
	var taskId  = document.getElementById("taskId"+rowId).value;
	document.getElementById("taskId").value = taskId;
	var taskName = document.getElementById("taskName"+rowId).value;
	document.getElementById("taskName").value = taskName;
	if(null != taskComment && taskComment != ""){
		window.location="approveTask.html?saveId="+rowId+"&ticketCode="+ticketCode+"&taskId="+taskId+"&taskName="+taskName+"&taskComment="+taskComment;
	}else{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Comment is mandatory.";
		return false;
	}
	
};

function rejectTask(rowId){
	var taskComment = document.getElementById("taskcomment").value ;
	document.getElementById("taskComment").value = taskComment;
	var ticketCode  = document.getElementById("ticketCode"+rowId).value;
	document.getElementById("ticketCode").value = ticketCode;
	var taskId  = document.getElementById("taskId"+rowId).value;
	document.getElementById("taskId").value = taskId;
	var taskName = document.getElementById("taskName"+rowId).value;
	document.getElementById("taskName").value = taskName;
	if(null != taskComment && taskComment != ""){
		window.location="rejectTask.html?saveId="+rowId+"&ticketCode="+ticketCode+"&taskId="+taskId+"&taskName="+taskName+"&taskComment="+taskComment;
	}else{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Comment is mandatory.";
		return false;
	}
	
};

function addComment(rowId,taskStatus){
	var taskId  = document.getElementById("taskId"+rowId).value;
	$.ajax({
		url: '/icam/getPreviousCommentForTask.html',
		dataType: 'json',
		data: "taskId=" + taskId,
	    success: function(data) {
	    	$('#commentTable > tbody').empty();
	    	var row = "<tbody>";
	  
	    	if(data != null && data!=""){
	     		var dataArr = data.split("#");
	     		for(var i=1; i<dataArr.length; i++){
    				var arr = dataArr[i].split("*");
    				row += "<tr><td>"+arr[0]+"</td><td>"+arr[1]+"</td><td><textarea id='taskcomment' name='taskcomment' class='form-control'></textarea></td></tr>";    				
    			}
	     		$("#commentTable").append(row);
	     	}else{
				row += "<tr><td>"+'Not Available'+"</td><td>"+'Not Available'+"</td><td><textarea id='taskcomment' name='taskcomment' class='form-control'></textarea></td></tr>";
				$("#commentTable").append(row);
			}
	    	$('#modalInfo').fadeIn("fast");
	    }
	});
	if(taskStatus == 'ACCEPT'){
		var btn = document.getElementById("submit");
		btn.setAttribute("onclick","approveTask('"+rowId+"');");
	}
	if(taskStatus == 'CLOSE'){
		var btn = document.getElementById("submit");
		btn.setAttribute("onclick","closeTask('"+rowId+"');");
	}
	if(taskStatus == 'REJECT'){
		var btn = document.getElementById("submit");
		btn.setAttribute("onclick","rejectTask('"+rowId+"');");
	}
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};