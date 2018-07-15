
			$("#venueCode").change(function (){
			//	alert("hii");
				$.ajax({
				url: '/cedugenie/getFacilityListAgainstVenue.html',
				dataType: 'json',
				data: "venueCode=" + $("#venueCode").val(),
				success: function(data) {
					//var options='<option value="">Select</option>';
					//alert("data==="+data);
					$("tbody").children().remove()
				
					if(data!=""){
						document.getElementById("editFacility").style.visibility = 'visible';
						data=data.split(",");	
						//alert("data length==="+data.length);
						var trHTML = '';
						for(var i=1;i<data.length;i++){
							//alert("i==="+i);
							var venue = data[i].split(":");
						
							
							trHTML += '<tr><td>' + '<input type="checkbox" id = "facilityCode" name="facilityCode" class = "facility" value="' + venue[0] + '" checked disabled></input>' + '</td><td>' + venue[1] + '</td></tr>';
							 
						}
					}
					 $('#editFacility').append(trHTML);
				}
				});
			})
function makeEditable(){
	$(".facility").removeAttr('disabled','disabled');
	//$("#submit").removeAttr('disabled','disabled');
	document.getElementById("submit").style.visibility="visible";
	//document.getElementById("reset").style.visibility="visible";
	//$("#edit").addAttr('disabled','disabled');
	document.getElementById("edit").style.visibility="hidden";
	}