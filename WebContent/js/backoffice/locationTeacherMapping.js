//$(document).ready( function(){
var resourceIndex = 0;

$("#venueTypeCode").change(function (){
		var venueType = $("#venueTypeCode").val();
			$.ajax({
				url: '/icam/getVenueAgainstVenueType.html',
				dataType: 'json',
				data: "venueTypeCode=" + $("#venueTypeCode").val(),
				success: function(data) {
					var options='<option value="">Select</option>';
					if(data!=""){
						data=data.split(",");						
						for(var i=1;i<data.length;i++){
							var venue=data[i].split(":");
							options=options+'<option value="'+venue[0]+'">'+venue[1]+'</option>';
						}
					}
					document.getElementById("venueCode").innerHTML=options;
				}
			});
				
				
	})
	
	$("#resourceTypeName").change(function (){
//		alert("hii");
		var type  = $("#resourceTypeName").val();
		//alert("type=="+type);
		if(($("#resourceTypeName").val()!=null)){
			
			$("#userId0").autocomplete({
				source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
				select: function (event, ui){
					var userId = ui.item.value;
					//alert("userId0==="+userId);
					$.ajax({
						url: '/icam/getUserNameForId.html',
						dataType: 'json',
						data: "userId=" + userId,
						success: function(data) {
							//alert("data====="+data);
							if(data != null && data!=""){
								($("#name0").val(data));
							}
						}			        
					});
				}
		 	});
		}
	});
	function getUserId(thisClassNode){
		
		var split= (thisClassNode.id).split("resourceType");
		var index=split[1];
		//alert("INDEX====="+index);
		if(($("#resourceType"+index).val()!=null)){
			
			$("#userId"+index).autocomplete({
				source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) ,
				select: function (event, ui){
					var userId = ui.item.value;
					
					$.ajax({
						url: '/icam/getUserNameForId.html',
						dataType: 'json',
						data: "userId=" + userId,
						success: function(data) {
							//alert("data======="+data);
							if(data != null && data!=""){
								($("#name"+index).val(data));
							}
						}			        
					});
				}
		 	});
		}
	}

	function auto(userId,name){
		//alert("Phase2");
		$(userId).autocomplete({	 
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()),
			select: function (event, ui){
			var userId = ui.item.value;
			$.ajax({
				url: '/icam/getUserNameForId.html',
				dataType: 'json',
				data: "userId=" + userId,
			    success: function(data) {
			     	if(data != null && data!=""){
			  			($(name).val(data));
			  			
			     	}else{	  
			     		$(name).val("");
			    		    	
			     		}
			  		}
					});
				}
			
			});
		
		}




	var index=1;
	var newIndex = 0;
	function addrows(){		
		//alert("hiiiiii");
		var userIds = document.getElementsByName("userName");
		var names = document.getElementsByName("name");
		var resourceTypeCodes = document.getElementsByName("resourceTypeCode");
		var resourceTypeNames = document.getElementsByName("resourceTypeName");
		
		for(var i=0;i<userIds.length;i++){
			var userId=userIds[i].value;
			var name=names[i].value;
			
		}
		var resourceTypeCode = null;
		var resourceTypeName = null;
		
		for(var i=0;i<resourceTypeCodes.length;i++){
			resourceTypeCode = resourceTypeCode + "*" +resourceTypeCodes[i].value;
			resourceTypeName = resourceTypeName + "*" + resourceTypeNames[i].value;
		}
		
		var resourceCode = resourceTypeCode.split("*");
		var resourceName = resourceTypeName.split("*");
//		alert("resourceCode==="+resourceCode);
		//alert("resourceName==="+resourceName);
		var table = document.getElementById("userTable");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);           
			
		var cell0 = row.insertCell(0);
		element = document.createElement("select");
		element.className = "form-control";
		element.id = "resourceType" + index;
		element.name = "resourceType";
		element.setAttribute( "required","required");
		element.setAttribute("onchange","getUserId(this);");	
		element.add(new Option("Select", ""));
		for(var i = 1; i<resourceCode.length;i++){
			element.add(new Option(resourceName[i], resourceCode[i]));
		}
		cell0.appendChild(element);
		
		var cell1 = row.insertCell(1);		
		var element1 = document.createElement("input");
		element1.type = "text";
		element1.name="userName"+index;
		element1.id="userId"+index;
		element1.className="form-control";	
		element1.setAttribute( "required","required");
		cell1.appendChild(element1);	            
		            
		var cell2 = row.insertCell(2);		
		var element2 = document.createElement("input");
		element2.type = "text";
		element2.name="name"+index;
		element2.className="form-control";
		element2.id="name"+index;
		element2.size=25;
		element2.setAttribute("readonly","readonly");
		cell2.appendChild(element2);	        
		
		/*var cell3 = row.insertCell(3);		
		var element3 = document.createElement("textarea");
		///element3.type = "text";
		element3.name="desc"+index;
		element3.className="form-control";
		element3.id="desc"+index;
		element3.size=25;
		element3.setAttribute( "required","required");
		cell3.appendChild(element3);*/	
		
		var cell3= row.insertCell(3);
		/*var element2 = document.createElement("img");
		element2.setAttribute("src","/icam/images/minus_icon.png");		
		element2.setAttribute("onclick", "deleteRow(this);");	*/	
		var element4 = document.createElement('a');
		element4.setAttribute("class","fa fa-minus-square");
		element4.setAttribute("onclick", "deleteRow(this);");
		element4.setAttribute("href","#");
		cell3.appendChild(element4);		
			
		var userId="#userId"+index;
		var name="#name"+index;
		auto(userId,name);
		resourceIndex = index;
		index++;
		
		//alert("rresourceIndex==="+resourceIndex)
	}
	 
	function deleteRow(obj){	
//		document.getElementById("warningbox").style.visibility='collapse';
		
		var table = document.getElementById("userTable");
		var rowCount = table.rows.length;	
		if(rowCount>1){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}
	}
	
	function setIndex(){
		//alert("within");
		//alert("index====="+resourceIndex);
		document.getElementById("resourceIndex").value=resourceIndex;
	}
//})