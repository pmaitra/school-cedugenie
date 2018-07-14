function viewMyTimeTableData(){
	//alert("hii");
	//alert($("#semsterName").val());
	$.ajax({
		url:"http://localhost:8080/icam/getTimeTableGridDataForStudent.html", 
        dataType:"text",
        data:"semester=" + ($("#semsterName").val())+ "&program="+($("#program").val()), 
        success:function(data){
        	//alert(data);
        	var dataArray = data.split("*#*");
        	
        	//var teacherArr = dataArray[1].split("*");
        	var rowValue = dataArray[2];
        	var columnValue = dataArray[1];
        	//alert("columnValue=="+columnValue);
        	$('#timetableBody').empty();
        	var table = document.getElementById("timetableBody");
        	for(var  i=0; i<rowValue ; i++){
        		var rowCount = table.rows.length;
        		//alert("rowCount=="+rowCount)
	            var row = table.insertRow(rowCount)
        		for(var j=0;j<columnValue ; j++){
        			 var cell1 = row.insertCell(j);
			         cell1.id = i+"_"+(j+1);
        		}
        	}
        	
        	var dataArr = dataArray[0].split("|");
        	for(var counter = 0;counter<dataArr.length ;counter++){
        		var gridData = dataArr[counter];
        		var gridDataDetails = gridData.split("-");
        		
        	    var tempDataArr = gridDataDetails[0].split("_");
        	    var tempData = tempDataArr[1];
        	    var quotint = Math.floor(tempData/7);
        	    var remainder = Math.floor(tempData % 7);
        	    var cellId = quotint+"_"+remainder;
        	    //alert("cellId=="+cellId);
        	    var cellData = gridDataDetails[1];
        	    $('#' + cellId).text(cellData);
        	}
        	document.getElementById("routine").style.display = 'block';
        },
        error:function(data){
        	//alert("error : " + data);
        	
        }
    });
}
