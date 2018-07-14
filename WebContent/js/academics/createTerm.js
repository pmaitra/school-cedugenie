/*$(document).ready(function(){

    $("#startMonth").datepicker({ 
        dateFormat: 'mm-yy',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,

        onClose: function(dateText, inst) {  
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
            $(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
        }
    });

    $("#startMonth").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });    
    });
    
});*/


function saveData(rowId, programName){
	var term = document.getElementById("termName").value;
	if(term != null && term != ""){
		document.getElementById("saveId").value=rowId;
		document.getElementById("getProgram").value=programName;
		document.getElementById("getTerm").value=term;
		
		document.getElementById("warningmsg").style.display = 'none';	
		document.editTerm.submit();
	}else{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Term must not be NULL.";
	}
	
	
};

function showTermDetails(programName, termName, index)
{
	$('#programTermDetails > tbody').empty();
 	if((programName != null && programName!="") && (termName != null && termName!="")){
 		var row = "<tbody>";
 		row += "<tr><td>"+"<input type='hidden' id='index' name='index' value='"+index+"'><input type='text'  name='course.courseName"+index+"' class='form-control' value='"+programName+"' readonly ></td>"
 				+"<td>"+"<input type='text' id='termName' name='termName' class='form-control' value='"+termName+"' pattern='[a-zA-Z0-9\s]+'></td>";    				
 		$("#programTermDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateTerms");
 	btn.setAttribute("onclick","saveData("+index+",'"+programName+"');");
 	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};


function termValidation()
{
	
	var re = /^[A-Za-z]+$/
	
	var query = document.getElementById("termName").value;
		 var isNumeric=query.match(/^\d+$/);
		  if(isNumeric)
		  {
			  	document.getElementById("javascriptmsg1").style.display = 'block';			
				document.getElementById("javascriptmsg1").innerHTML = "Term Name should contain atleast one character."; 
				return false;
		  }
		  else
		  {
			
			  return true;
		  }
	
	}

function removeMsg()
{
	document.getElementById("javascriptmsg1").style.display = 'none';	
	}