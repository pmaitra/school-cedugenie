$(document).ready(function (){
	$("#tenderSPOC").autocomplete({
		source: '/cedugenie/getAllUserId.html' ,
		/*select: function (event, ui){
			var userId0 = ui.item.value;
		}*/
});
});

$("#tenderCategory").change(function() {
	var tenderSubCategory = document.getElementById("tenderSubCategory");
	removeOption(tenderSubCategory);
	//removeOption(commodityName);
	
	$.ajax({
		url: '/cedugenie/getAllSubCategory.html',
		dataType: 'json',
		data: "tenderCategory="+($("#tenderCategory").val()),
		success: function(data){ 
			var dataArr = data.split("#");
			
			for(var i=0;i<dataArr.length-1;i++){
				var dataDB = dataArr[i].split("*");
				$('#tenderSubCategory')
   	         	.append($("<option></option>")
   	                    .attr("value",dataDB[0])
   	                    .text(dataDB[1])); 
   		
			}
		}
	});
});

function removeOption(x){
	for(var i=x.length;i>=0;i--){
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select..</option>";
}

//$("#tenderSubCategory").change(function() {
function getAllCommodityList(index){
	var tenderCategory = document.getElementById("tenderCategory");
	var tenderSubCategory = document.getElementById("tenderSubCategory");
	//removeOption(commodityName);
	
	
	$.ajax({
	url: '/cedugenie/getAllCommodityName.html',
	dataType: 'json',
	data: "tenderCategory="+($("#tenderCategory").val())+"&tenderSubCategory="+($("#tenderSubCategory").val()),
	success: function(data){ 

		/*if successful create a row with a select box of all the Commodity name*/
		var dataArr = data.split("#");
		
		for(var i=0;i<dataArr.length-1;i++){
			var dataDB = dataArr[i].split("*");
			$('#commodityName_'+index)
         	.append($("<option></option>")
                    .attr("value",dataDB[0])
                    .text(dataDB[1])); 
	
		}
	}
	});
	var tenderCategoryValue = tenderCategory.value;
	
	if(tenderCategoryValue == 'TC-1'){
		document.getElementById("tenderTable").style.display = "none";
		document.getElementById("msgDiv").style.display = "block";
		document.getElementById("msg").innerHTML ="No Data Available";
	}else{
		document.getElementById("tenderTable").style.display = 'block';
		document.getElementById("msgDiv").style.display = "none";
	}
}	
//});

/*$("#commodityName").change(function() {*/
function getCommodityUnit(thisValue){
	
	//var commodityName = document.getElementById("commodityName"+indexNo).value;
	
	var split= (thisValue.id).split("_");
	var indexNo = split[1];
	
	$.ajax({
	url: '/cedugenie/getCommodityUnit.html',
	dataType: 'text',
	data: "commodityName="+thisValue.value,
	success: function(data){ 
		
		/*if successful the unit for the commodity name will come from the DB*/
		$('#unit'+indexNo).val(data); 
	}
	});
}

var index=1;
function addrows(){
	
	var table = document.getElementById("tenderTableBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);    
	
	getAllCommodityList(index);
	
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "commodityName_" + index;
	element.name = "commodityName";
	element.setAttribute( "required","required");
	element.setAttribute("onchange","getCommodityUnit(this);");	
	element.add(new Option("Select", ""));
	cell0.appendChild(element);
	
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="unit";
	element1.id="unit"+index;
	element1.className="form-control";	
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	            
	            
	var cell2 = row.insertCell(2);		
	var element2 = document.createElement("input");
	element2.type = "text";
	element2.name="quantity";
	element2.className="form-control";
	element2.id="quantity"+index;
	element2.size=25;
	element2.setAttribute("required","required");
	cell2.appendChild(element2);
	
	var cell3 = row.insertCell(3);
	var element3 = document.createElement('a');
	element3.setAttribute("class","fa fa-minus-square");
	element3.setAttribute("onclick", "deleteRow(this);");
	element3.setAttribute("href","#");
	cell3.appendChild(element3);
	
	index++;
}

function deleteRow(obj){
	var table = document.getElementById("tenderTableBody");
	var rowCount = table.rows.length;	
	if(rowCount>1){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
}

	
$(".addFileClassName").each(function(){
	$(this).click(function(){                      		
		var tableNode = $(this).parent().parent().parent().parent();
		 var row = $('<tr>'); 
         row.append($('<td><input type="file" name="uploadFile.tenderRelatedFile" /></td><td><img  src="/cedugenie/images/minus_icon.png" onclick="deleteThisRow(this);"></td>'));
    $(tableNode).append(row); 
	});
});

function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}