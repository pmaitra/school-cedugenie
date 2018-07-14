
$("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){			
		$("#userId").autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
			select: function (event, ui){
				var userId = ui.item.value;
				$.ajax({
					url: '/icam/getUserNameForId.html',
					dataType: 'json',
					data: "userId=" + userId,
					success: function(data) {
						if(data != null && data!=""){
							($("#name").val(data));
						}
						else{	   
							alert("User Name Not Found");
						}
					}			        
				});
			}
		});
	}
});


function validate(){
	var userIdAndAccessTypeList = document.getElementsByName("hiddenResourceId");
	var userId = document.getElementById("userId").value;
	var accessType = document.getElementById("accessTypeName").value;
	for(var i=0;i<userIdAndAccessTypeList.length;i++){
		var arr = (userIdAndAccessTypeList[i].value).split("*");
		if(userId==arr[0]){
			if(accessType==arr[1]){
				alert("Mapping Of This User With THis Role Already Exists");
				return false;
			}
		}
	}
	return true;
}