
	 $("#exam").change(function() {
			$.ajax({
		        url: '/cedugenie/getProgramAndExamDetailsAgainstExam.html',
		        dataType:"text",
		        data: "exam=" + ($(this).val()),
		        success: function(dataDB) {
		        	
		        	//alert(dataDB);
		        	
		        	var data = dataDB.split("*");
		        	//alert("data=="+data);
		        	var dateArr = data[1].split("#");
		        	//alert("dateArr=="+dateArr);
		        	$('#routineArrangementsBody').empty();
		        	var table = document.getElementById("routineArrangementsBody");
		        	
		        	
		        	var rowCount = table.rows.length;
		        	//alert("rowCount=="+rowCount);
		            var row = table.insertRow(rowCount);
		            cell = row.insertCell(0);
		            
		        	for (var i=0;i<dateArr.length-1;i++){
		        		
			            cell = row.insertCell(i+1);
			        
			            cell.id = "date_"+i;
			            cell.innerHTML = dateArr[i];
				     }
		        	var programArr = data[2].split("@");
		        	//alert("programArr=="+programArr);
		        	for(var k=0;k<programArr.length;k++){
		        		var rowCount = table.rows.length;
			        	//alert("rowCount=="+rowCount);
			            var row = table.insertRow(rowCount);
			        	for (var j=0;j<dateArr.length;j++){
			        		 var cell1 = row.insertCell(j);
					         cell1.id = k+"_"+j;
			        	}
			           
		        	}
		        	//var dataArr = data[0].split("|");
		            
		            
		        	var dataArr = data[0].split("|");
		        	for(var counter = 0;counter<dataArr.length ;counter++){
		        		var gridData = dataArr[counter];
		        		var gridDataDetails = gridData.split("-");
		        		var cellId = gridDataDetails[0];
		        	    var cellData = gridDataDetails[1];
		        	    $('#' + cellId).text(cellData);
		        	}
		        	callEvent();
		        },
		        error:function(data){
		        	//alert("error : " + data);
		        }
		        	
		        	
			})
	 })

function callEvent(){
		 var rowcount = $("#routineArrangements > tbody > tr").length;
		var columncount = $("#routineArrangements > tbody").find("> tr:first > td").length;	 
		$("#routineArrangementsBody td").dblclick(function () {
			

			//alert("hii");
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
	                	//alert(newContent);
	                	//alert(rowcolID);
	                	
	                	$.ajax({
	                        url:'/cedugenie/insertRoutineGridData.html',
	                        dataType:"text",
	                        data:"cellid=" + rowcolID + "&celldata=" + newContent,
	                        success:function(data){
	                        	//alert(data);
	                        },
	                        error:function(xmldata){
	                        	//alert("error : " + data);
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
	        
	        
	         
	        /*$(this).children().first().blur(function(){
	        	$(this).parent().text(OriginalContent);
	        	$(this).parent().removeClass("cellEditing");
	        });*/
	        
	       
	    
			
		});
}
	 
$(document).ready(function() {
	 
	 var rowcount = $("#routineArrangements > tbody > tr").length;
		var columncount = $("#routineArrangements > tbody").find("> tr:first > td").length
		/*alert(rowcount);
		alert(columncount);
		*/
		// checking for conflict in all the rows of all the columns and marking them as red
		/*for(var k=0;k<columncount;k++){
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
		}*/
		
		$("td").dblclick(function () {
			
		});
})