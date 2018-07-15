$("#resourceTypeName").change(function (){
	
	if(($("#resourceTypeName").val()!=null)){			
		$("#userId0").autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
			select: function (event, ui){
				var userId0 = ui.item.value;
				$.ajax({
					url: '/cedugenie/getUserNameForId.html',
					dataType: 'json',
					data: "userId=" + userId0,
					success: function(data) {
						if(data != null && data!=""){
							($("#name0").val(data));
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
function auto(userId,name,index){
	$(userId).autocomplete({	 
		source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()),
		select: function (event, ui){
			var userId = ui.item.value;
			$.ajax({
				url: '/cedugenie/getUserNameForId.html',
				dataType: 'json',
				data: "userId=" + userId,
				success: function(data) {
					if(data != null && data!=""){
						//($(("#name")+index).val(data));
							//($("#name").val(data));
						document.getElementById("name"+index).value = data;
					}
					else{	   
						alert("User Name Not Found");
					}
				}			        
			});
		}
	});
	
}




var index=1;
var newIndex = 0;
function addrows(){
	var userIds=document.getElementsByName("userId");
	var names=document.getElementsByName("name");
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
	}
	
	
	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="userId";
	element0.id="userId"+index;
	element0.className="form-control";
	element0.setAttribute("required", "");
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="name";
	element1.className="form-control";
	element1.id="name"+index;
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	/*var element2 = document.createElement("img");
	element2.setAttribute("src","/cedugenie/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");*/
	var element2= document.createElement('a');	
	 element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell2.appendChild(element2);		
		
	var userId="#userId"+index;
	var name="#name"+index;
	index++;
	 newIndex = index - 1;
	auto(userId,name,newIndex);
	
}
 
function deleteRow(obj){	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		alert("Atleast One Row Required");
	}
} 