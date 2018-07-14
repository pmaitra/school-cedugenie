$("#venueName").change(function (){
	$.ajax({
		url: '/icam/getBlocksOfVenue.html',
		dataType: 'json',
		data: "venueName=" + $("#venueName").val(),
		success: function(data) {
			var options ='<option value="">Select</option>';
			if(data!=""){
				var array = data.split("#");
				for(var i=0;i<array.length;i++){
					var splitedArray =  array[i].split("*");
					options = options+'<option value="'+splitedArray[0]+'">'+splitedArray[1]+'</option>';
				}
			}
			document.getElementById("block").innerHTML=options;
		}
	});
});

$("#block").change(function (){
	$.ajax({
		url: '/icam/getAvailableRoomOfBlock.html',
		dataType: 'json',
		data: "block=" + ($(this).val()),
		success: function(data) {
			var options ='<option value="">Select</option>';
			if(data!=""){
				var array = data.split("#");
				for(var i=0;i<array.length;i++){
					var splitedArray =  array[i].split("*");
					options = options+'<option value="'+splitedArray[0]+'">'+splitedArray[1]+'</option>';
				}
			}
			document.getElementById("roomNo").innerHTML=options;
		}
	});
});

$("#roomNo").change(function (){
	$.ajax({
		url: '/icam/getAvailableBedsInRoom.html',
		dataType: 'json',
		data: "roomNo=" + $("#roomNo").val(),
		success: function(data) {
			if(data!=""){
				$('#bedsTableDiv').css('display','block');
				var array = data.split("*");
				$('#bedVaccent').val(array[0]);
				$('#bedTotal').val(array[1]);
			}
		}
	});
});

$("#userId").change(function() {
	$.ajax({
		url:' /icam/getResourceDetails.html',
		data:'userId='+($(this).val()),
		dataType: 'json',
		success: function(data){
			$('#resourceName').val(data);
			/*var student=data.split("~");
			document.getElementById("name").value="";
			document.getElementById("klass").value="";
			document.getElementById("sectionName").value="";
			
			document.getElementById("name").value=student[0];
			document.getElementById("klass").value=student[1];
			document.getElementById("sectionName").value=student[2];*/
		}
	}); 
});

function validateAssign(){
	
	var venueName = document.getElementById("venueName").value;
	var block = document.getElementById("block").value;
	var roomNo = document.getElementById("roomNo").value;
	var userId = document.getElementById("userId").value;
	var bedVaccant = document.getElementById("bedVaccent").value;
	var bedTotal = document.getElementById("bedTotal").value;
	if(venueName == ""){
		alert("Please select a venue.");
		return false;
	}
	if(block == ""){
		alert("Please select a block for this venue.");
		return false;
	}
	if(roomNo == ""){
		alert("Please select a room no for this block and venue.");
		return false;
	}
	if(userId == ""){
		alert("Please select a user for assign venue.");
		return false;
	}
	if(bedVaccant == 0){
		alert("Insufficient venue to assign");
		return false;
	}
	return true;
}