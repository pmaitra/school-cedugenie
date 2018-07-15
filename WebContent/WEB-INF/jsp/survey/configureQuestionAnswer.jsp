<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

</head>
<body>
<c:if test="${insertUpdateStatus eq 'success'}">
		<div class="alert alert-success">
			<strong>${msg}</strong>
		</div>
	</c:if>
	
	<c:if test="${insertUpdateStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>${msg}</strong>
		</div>
	</c:if>
	
	<div class="row">
						
						<div class="col-md-8 col-md-offset-2">
						<form enctype="multipart/form-data" action = "configureQuestionAnswer.html" method="POST">
				            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                                    </div>

                                    <h2 class="panel-title">Survey</h2>										
                                </header>
                                <div style="display: block;" class="panel-body">
                                    <table class="table table-bordered table-condensed mb-none" id="tbl_posts">
                                    	<thead style="background: #eee;">
                                    		<tr>
												<th><b>Question</b></th>
												<th><b>Answer Type</b></th>
												<th><b>Answer</b></th>
												<th><b>Weightage</b></th>
												<th><b>Actions</b></th>										
											</tr>
                                    	</thead>
                                    	<tbody id="tbl_posts_body">
                                    		<tr id="rec-0">
                                    			<td><input type="text" name="question" id = "question0" class="form-control"></td>
                                    			<td>
	                                    			<select name="answer_type" id = "answer_type0" class="form-control" onchange = "hideAddButton()">
	                                    				<option value="">Select</option>
									                   <option value="Text">Text</option>
									                   <option value="Checkbox">Checkbox</option>
									                   <option value="Radio">Radio</option>
									                   <option value="TextArea">Textarea</option>
									                 </select>
                                    			</td>
                                    			
                                    			<td id = "tdOne_0">
                                    				<!-- <div  id = "answer_0" > -->
                                    					<input type="text" name="answer0" id = "answer0" class="form-control">
                                    				<!-- </div> -->
                                    			</td>
                                    			<td id = "tdTwo_0">
                                    				<div class="row"  style="width: 270px;">
                                    					<div class="col-sm-10"><input type="text" name="weightage0" id = "weightage0" class="form-control"></div>
                                    					<div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer" id = "add0" href="javascript:addrows(this)"><i class="glyphicon glyphicon-plus-sign"></i></a></div>
                                    				</div>
                                    			</td>
                                    			<td>
                                   					<div id = "delete0">
                                   						<a class="btn btn-xs btn-danger delete-record" data-id="1"><i class="glyphicon glyphicon-trash"></i></a>
                                   					</div>
                                    			</td>
                                    		</tr>                                    		
                                    	</tbody>										
										
									</table>
                                    
                                </div>
                                <footer style="display: block;" class="panel-footer">
                                	<a class="btn btn-primary pull-right add-record" data-added="0"  href="javascript:cloneRow()"><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;Add Row</a>
									<button class="btn btn-primary">Submit </button>
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
                            </section>
                            </form>
						</div>
					</div>
			<!-- <div style="display:none;">
					    <table id="sample_table">
					      <tr id="">
					       <td><input type="text" name="question" id = "question" class="form-control"></td>
                			<td>
                    			<select name="answer_type" id = "answer_type" class="form-control">
                    				<option value="">Select</option>
				                   	<option value="text">Text</option>
				                   	<option value="checkbox">Checkbox</option>
				                  	<option value="radio">Radio</option>
				                   	<option value="textarea">Textarea</option>
				                  </select>
                			</td>
                			<td>
                				<div class="row" style="width: 270px;">
                					<div class="col-sm-10"><input type="text" name="" class="form-control"></div>
                					<div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer"><i class="glyphicon glyphicon-plus-sign"></i></a></div>
                				</div>               				
                			</td>
					       <td><a class="btn btn-xs btn-danger delete-record" data-id="0"><i class="glyphicon glyphicon-trash"></i></a></td>
					     </tr>
					   </table>
					 </div>	 -->
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>	
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/survey/configureQuestionAnswer.js"></script>
<script type="text/javascript">
/*  $(document).ready(function(){
		    
		    jQuery(document).delegate('a.add-record', 'click', function(e) {
		     e.preventDefault();    
		     var content = jQuery('#sample_table tr'),
		     size = jQuery('#tbl_posts >tbody >tr').length + 1,
		     element = null,    
		     element = content.clone();
		     element.attr('id', 'rec-'+size);
		     element.find('.delete-record').attr('data-id', size);
		     element.appendTo('#tbl_posts_body');
		     element.find('.sn').html(size);
		   	});
		    jQuery(document).delegate('a.delete-record', 'click', function(e) {
			     e.preventDefault();    
			     var didConfirm = confirm("Are you sure You want to delete");
			     if (didConfirm == true) {
			      var id = jQuery(this).attr('data-id');
			      var targetDiv = jQuery(this).attr('targetDiv');
			      jQuery('#rec-' + id).remove();
			      
			    //regnerate index number on table
			    $('#tbl_posts_body tr').each(function(index){
					$(this).find('span.sn').html(index+1);
			    });
			    return true;
			  } else {
			    return false;
			  }
			});

	var i = 0;
 		    jQuery(document).delegate('a.add-answer', 'click', function(e) {
		     e.preventDefault();    
		     //alert('add-answer');
		     var appendTxt = "<td><input type='text' name='' class='form-control'></td><td><div id='ans-div'><div class='col-sm-10'><input type='text' name='' class='form-control'></div><div class='col-sm-2'><a class='btn btn-xs btn-danger del'><i class='glyphicon glyphicon-minus-sign'></i></a></div></div></td> ";
				$(this).parent().after(appendTxt);	
		   	});

		   	jQuery(document).delegate('a.del', 'click', function(e) {
		     e.preventDefault();    
		     //alert('delete ans');
		     //var targetDiv = jQuery(this).attr('targetDiv');
		     $(this).parent().parent().remove();
		   	});			

		  }); */
		  
	function hideAddButton(){
		var selectedValue = document.getElementById('answer_type0').value;
	//	alert("selectedValue===="+selectedValue);
		if(selectedValue != null){
			if(selectedValue=='text' || selectedValue =='textarea'){
				document.getElementById('add0').style.display = 'none';
				document.getElementById('delete0').style.display = 'none';
				//document.getElementById('answer0').style.display = 'none';
			}else{
				document.getElementById('add0').style.display = 'block';
				document.getElementById('delete0').style.display = 'block';
				//document.getElementById('answer0').style.display = 'block';
			}
		}
		
	}
	
	
</script>
</body>
</html>