$("#standardCode").change(function(){
	
	var sectionObject=document.getElementById("section");
	removeOption(sectionObject);
	
	$.ajax({
        url: '/cedugenie/getSectionAgainstStandard.html',
        dataType: 'json',
        data: "standard=" + ($(this).val()),
        success: function(dataDB) {
        	//alert("dataDB=="+dataDB);
        	var options="<option value=''>Select..</option>";
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split(";");
				for (var i=0;i<arr.length;i++){   
					var section = arr[i].split("*");
					options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
				}
			}
        	sectionObject.innerHTML=options;
       }
	});
	
});

function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select..</option>";
}

$(document).ready(function() {	
	 $("#desc").autocomplete({
	 	source: '/cedugenie/getStaffUserIdList.html',
	 	select: function (event, ui){
			var userId = ui.item.value;
			
			$.ajax({
				url: '/cedugenie/getUserNameForId.html',
				dataType: 'json',
				data: "userId=" + userId,
				success: function(data) {
					if(data != null && data!=""){
						($("#userName").val(data));
					}
				}			        
			});
		}
	 });
 });