function viewTimeTableData(){
	//alert("hii");
	//alert($("#semsterName").val());
	$.ajax({
		url:"http://localhost:8080/cedugenie/getAllTimeTableGridData.html", 
        dataType:"text",
        data:"semester=" + ($("#semsterName").val()),
        success:function(data){
        	//alert(data);
        	var dataArray = data.split("*#*");
        	
        	var teacherArr = dataArray[1].split("*");
        	var columnValue = dataArray[2];
        	//alert("columnValue=="+columnValue);
        	$('#routineTableBody').empty();
        	var table = document.getElementById("routineTableBody");
        	for(var  i=0; i<teacherArr.length ; i++){
        		var rowCount = table.rows.length;
        		//alert("rowCount=="+rowCount)
	            var row = table.insertRow(rowCount)
        		for(var j=0;j<=columnValue ; j++){
        			 var cell1 = row.insertCell(j);
			         cell1.id = i+"_"+j;
        		}
        	}
        	
        	var dataArr = dataArray[0].split("|");
        	for(var counter = 0;counter<dataArr.length ;counter++){
        		var gridData = dataArr[counter];
        		var gridDataDetails = gridData.split("-");
        		var cellId = gridDataDetails[0];
        	    var cellData = gridDataDetails[1];
        	    $('#' + cellId).text(cellData);
        	}
        	document.getElementById("routine").style.display = 'block';
        },
        error:function(data){
        	//alert("error : " + data);
        	var confirmed = confirm('Time Table Not Configured Yet.Do You Want To Configure?');
        	var semesterName = $("#semsterName").val();
        	if(confirmed) {
        		window.location = "insertIntoTimeTable.html?semester="+semesterName;
        	    //Do dangerous action
        	}
        }
    });
}

//$(document).ready(function() {
	
	
	
	
	
	
	var rowcount = $("#timetable > tbody > tr").length;
	var columncount = $("#timetable > tbody").find("> tr:first > td").length
	//alert(rowcount);
	//alert(columncount);
	
	// checking for conflict in all the rows of all the columns and marking them as red
	for(var k=0;k<columncount;k++){
		for(var i=0;i<rowcount;i++){
	     	var firstcounter = i + "_" + k;
	     	for(var j=i+1;j<rowcount;j++){
	     		var secondcounter = j + "_" + k;
	     		//alert(firstcounter + " : " + secondcounter);
	     		// Here we are checking for conflicts and marking them as red.
	     		if($('#' + firstcounter).text() == $('#' + secondcounter).text() ){
	     			if($('#' + firstcounter).text()){
	     				//alert( "same");
		         		$('#' + firstcounter).removeClass('tdnoconflictBackground');
		         		$('#' + firstcounter).addClass('tdconflictBackground');
		         		$('#' + secondcounter).removeClass('tdnoconflictBackground');
		         		$('#' + secondcounter).addClass('tdconflictBackground');
	     			}
	     		}
	     	}
	     }
	}
	
	$('td.dblclickable').on('click',function () {
		alert("hiii");
        var OriginalContent = $(this).text();
        var rowcolID = $(this).attr("id"); // rowcolID has the id of the cell that has been dbl clicked
        //alert(rowcolID);
        var arrId = rowcolID.split("_"); // arrId array contains rowId as the first element and columnId as the second element
        //alert(arrId[1]);
        
        $(this).addClass("cellEditing");
        $(this).html("<input type='text' value='" + OriginalContent + "' />");
        $(this).children().first().focus();
 
        $(this).children().first().keypress(function (e) {
            if (e.which == 13) {
                var newContent = $(this).val();
                $(this).parent().text(newContent);
                $(this).parent().removeClass("cellEditing");
                
                var conflict = 0;
                
                for(var i=0;i<rowcount;i++){ 
                	// checking for conflict in all the rows of a column (i.e, column of the cell that has been edited))
                	var counter = i + "_" + arrId[1];
                	//alert(counter + " : " + rowcolID);
                	if($('#' + counter).text() == newContent && counter != rowcolID){
                		//alert("same");
                		$('#' + rowcolID).removeClass('tdnoconflictBackground');
                		$('#' + rowcolID).addClass('tdconflictBackground');
                		$('#' + counter).removeClass('tdnoconflictBackground');
                		$('#' + counter).addClass('tdconflictBackground');
                		conflict++;
                	}
                }
                
                //alert("conflict : " + conflict);
                if(conflict === 0 ){
                	$('#' + rowcolID).addClass('tdconflictBackground');
                	$('#' + rowcolID).addClass('tdnoconflictBackground');
                	alert(newContent);
                	alert(rowcolID);
                	
                	$.ajax({
                        url:"http://localhost:8080/cedugenie/insertTimeTableGridData.html",
                        dataType:"text",
                        data:"cellid=" + rowcolID + "&celldata=" + newContent,
                        success:function(data){
                        	alert(data);
                        },
                        error:function(xmldata){
                        	alert("error : " + data);
                        }
                    });
                	
                }
                
                var totalconflict = 0;
            	
        		for(var i=0;i<rowcount;i++){
        	     	var firstcounter = i + "_" + arrId[1];
        	     	for(var j=i+1;j<rowcount;j++){
        	     		var secondcounter = j + "_" + arrId[1];
        	     		//alert(firstcounter + " : " + secondcounter);
        	     		if($('#' + firstcounter).text() == $('#' + secondcounter).text()){
        	     			if($('#' + firstcounter).text()){
        	     				totalconflict++;
        	     			}
        	         	}
        	     	}
        	     }
                
        		//alert("totalconflict : " + totalconflict);
        		if(totalconflict == 0){
        			for(var i=0;i<rowcount;i++){
        				var firstcounter = i + "_" + arrId[1];
        				$('#' + firstcounter).removeClass('tdconflictBackground');
        				$('#' + firstcounter).addClass('tdnoconflictBackground');
        			}
        		}
                
            }
            
        });
        
        
         
        $(this).children().first().blur(function(){
        	$(this).parent().text(OriginalContent);
        	$(this).parent().removeClass("cellEditing");
        });
        
       
    });
	
	
	
//});