$('#edit').click(function()
{
	$('#edit').css('display','none');
	$('#submit').css('display','block');
	$('#reset').css('display','block');
	$('.class1').removeAttr('disabled');
	$('#paidUnpaid input[type="radio"]' ).removeAttr( "disabled" );
	$('input[name=ispaid]').click(function(){
		if(this.id=="paid")
		{
			$('#chargeTable').css('display','block');	
		}
		else
		{
			$('#chargeTable').css('display','none');
		}
	});
});
