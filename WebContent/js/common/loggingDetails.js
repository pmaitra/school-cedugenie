
$(document).ready(function()
{
var slider_width = $('.pollSlider').width();//get width automaticly
  $('#pollSlider-button').click(function() {
    if($(this).css("margin-right") == slider_width+"px" && !$(this).is(':animated'))
    {
        $('.pollSlider,#pollSlider-button').animate({"margin-right": '-='+slider_width});
    }
    else
    {
        if(!$(this).is(':animated'))//perevent double click to double margin
        {
            $('.pollSlider,#pollSlider-button').animate({"margin-right": '+='+slider_width});
        }
    }

    getLoggedData();
  });
 }); 



function deleteLoggingTableRow(table)
{
	var rowCount = table.rows.length;	
	for(var i=rowCount-1;i>0;i--){
		table.deleteRow(i);
	}
}

	function createTable(data){
		var table = document.getElementById('loggingDetails');
		deleteLoggingTableRow(table);
		if(data=="Details Not Available"){
			var rowCount = table.rows.length;
	        var row = table.insertRow(rowCount);
	        var cell,element;
	        
			cell = row.insertCell(0);
			cell.setAttribute("colspan","3");
		    element = document.createTextNode(data);
	        cell.appendChild(element);
		}else{
		data=data.split("~*~");
			for(var i=0;i<data.length-1;i++){
		    	var rowCount = table.rows.length;
		        var row = table.insertRow(rowCount);
		        var cell,element;
		        
		        var det=data[i].split("*~*");
		        
		        cell = row.insertCell(0);
		        element = document.createTextNode(det[0]);
		        cell.appendChild(element);
		        
		        cell = row.insertCell(1);
		        element = document.createTextNode(det[1]);
		        cell.appendChild(element);
		        
		        cell = row.insertCell(2);
		        element = document.createTextNode(det[2]);
		        cell.appendChild(element);
			}
		}
	}
