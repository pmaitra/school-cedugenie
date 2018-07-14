function showColumnsDiv(){
		document.getElementById("columnsDiv").style.visibility="visible";
	}
	function hideColumnsDiv(){
		document.getElementById("columnsDiv").style.visibility="collapse";
	}

function ShowHideField(header, gridID, checkboxElement) {
        var theGrid = document.getElementById(gridID);
        var displayValue;
     
        if (checkboxElement.checked) {
            displayValue = "";
        } else { 
            displayValue = "none";
        }
     
     
        if (theGrid != null) {
            var theHeaders = theGrid.getElementsByTagName("th");
            var theRows = theGrid.getElementsByTagName("tr");
            var numHeaders = theHeaders.length;
            var numRows = theRows.length;
            var i;
            var foundHeader = false;
            //searching through the headers in the grid for one that matches the header value provided
            for (i = 0; i < numHeaders && foundHeader==false; i++) {
                var headerText = theHeaders[i].innerHTML;
                if (headerText == header) {
                //once found the header, set the header's display value to display or not depending on what was provided
                    foundHeader = true;
                    theHeaders[i].style.display = displayValue;
                    var j;
                    for (j = 0; j < numRows; j++) {
                    //looping through all of the rows in the grid and setting each cell in the column to display or not.
                        var cells = theRows[j].getElementsByTagName("td");
                        if (cells.length > i) {
                            cells[i].style.display = displayValue;
                        }                  
                    }
     
                }
            }        
        }
     
    }