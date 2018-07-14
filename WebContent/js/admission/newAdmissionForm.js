/*
	function validate(){
		var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;

		var dateVal = document.getElementById("admissionFormSubmissionLastDate").value;
		var admissionDriveStartDate = document.getElementById("admissionDriveStartDate").value;
		var admissionDriveExpectedEndDate = document.getElementById("admissionDriveExpectedEndDate").value;
		alert(admissionDriveStartDate);
		alert(dateVal);
		alert(admissionDriveExpectedEndDate);
		var countCustomizedAdmissionProcess=0;
			$(".customizedAdmissionProcessCode").each(function(){	
				if ($(this).is(':checked')){			
					countCustomizedAdmissionProcess=1;
				}
				&& )
		});
		alert(admissionFormSubmissionLastDate<admissionDriveExpectedEndDate);
		if(admissionFormSubmissionLastDate<admissionDriveStartDate)
			{
		
			alert("Last Date For Form Submission Must Be After Admission Start Date ");
			return false;
		}
		if(admissionFormSubmissionLastDate>admissionDriveExpectedEndDate)
		{
	
		alert("Last Date For Form Submission Must Be Before Admission End Date ");
		return false;
	}
		if(admissionFormSubmissionLastDate>admissionDriveExpectedEndDate && admissionFormSubmissionLastDate<admissionDriveStartDate)
		{
	
			alert("Last Date For Form Submission Must Be between Admission End Date And Start Date ");
			return false;
		}else{
			alert("Correct");
		}
		return true;	
		
	}
	
	

<script>
$(document).ready(function() {
	//var admissionDriveStartDate = document.getElementById("admissionDriveStartDate").value;
	//var admissionDriveExpectedEndDate = document.getElementById("admissionDriveExpectedEndDate").value;
    $('#newAdmissionForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            date: {
                validators: {
                    date: {
                        message: 'The date is not valid',
                        format: 'DD/MM/YYYY'
                    },
                    callback: {
                        message: 'The date is not in the range',
                        callback: function(value, validator) {
                            var m = new moment(value, 'DD/MM/YYYY', true);
                            if (!m.isValid()) {
                                return false;
                            }
                            // Check if the date in our range
                            return m.isAfter( $('#admissionDriveStartDate').val()) && m.isBefore($('#admissionDriveExpectedEndDate').val());
                        }
                    }
                }
            }
        }
    });
});
</script>
	*/
	