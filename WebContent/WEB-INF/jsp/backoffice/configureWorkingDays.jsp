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
<title>Read QR Code</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Working Days Configuration</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			  <form name="congspec" action="listConfigureWorkingDays.html" method="POST">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Configure Working Days</h2>										
						</header>
						<div style="display: block;" class="panel-body">
							<div class="form-group">
								<label class="col-sm-5 control-label">Academic Year</label>
								<div class="col-sm-7">
									<c:choose>
										<c:when test="${currentYear eq null && currentYear eq ''}">			
											<div class="errorbox" id="errorbox" style="visibility: visible;">
												<span id="errormsg">Current Academic Year Not Found</span>	
											</div>		
										</c:when>
										<c:otherwise>
											<input class="form-control" id="academicYear" name="academicYear" value="${currentYear.academicYearCode}" readonly="readonly">
	                                    </c:otherwise>
									</c:choose>
								</div>
							</div><div class="form-group">
								<label class="col-sm-5 control-label">Year</label>
								<div class="col-sm-7">
									<select class="form-control" id="year" name="year">
                                        <option value="">Select...</option>
                                        <c:if test="${year ne null}">
											<c:forEach var="year" items="${year}">
												<option value="${year.academicYearName}">${year.academicYearName}</option>
											</c:forEach>
										</c:if>
                                    </select>
								</div>
							</div> 
                            <div class="form-group">
								<label class="col-sm-5 control-label">Month</label>
								<div class="col-sm-7">
									<select class="form-control" id="month" name="month">
                                        <option value="00">Select...</option>
                                    </select>
								</div>
							</div> 
						</div>
                        <hr>
                        <div id="showdiv" style="visibility: collapse;" class="panel-body">
							<div class="form-group">
								<label class="col-sm-5 control-label">Total Working Days</label>
								<div class="col-sm-7">
									<input id="tworking" name="tworking" class="form-control" type="text">
								</div>
							</div>
                            <div class="form-group">
								<label class="col-sm-5 control-label">Holidays For</label>
								<div class="col-sm-7">
                                             <div class="row">
                                                 <div class="col-md-6">
                                             		<div>
                                                         <input class="checkbox-custom" type="checkbox" id="mon" name="Monday" value="1">
                                                         <label for="mon">Monday</label>
                                                     </div>
                                      				<div>
                                                         <input class="checkbox-custom" type="checkbox" id="tue" name="Tuesday" value="2">
                                                         <label for="tue">Tuesday</label>
                                                     </div>
                                     				<div>
                                                         <input class="checkbox-custom" type="checkbox" id="wed" name="Wednesday" value="3">
                                                         <label for="wed">Wednessday</label>
                                                     </div>
                                      				<div>
                                                         <input class="checkbox-custom" type="checkbox" id="thurs" name="Thursday" value="4">
                                                         <label for="thurs">Thursday</label>
                                                     </div>
                                                 </div>
                                                 <div class="col-md-6">
                                             		<div>
                                                         <input class="checkbox-custom" type="checkbox" id="fri" name="Friday" value="5" >
                                                         <label for="fri">Friday</label>
                                                     </div>
                                     				<div>
                                                         <input class="checkbox-custom" type="checkbox" id="sat" name="Saturday" value="6">
                                                         <label for="sat">Saturday</label>
                                                     </div>
                                      				<div>
                                                         <input class="checkbox-custom" type="checkbox" id="sun" name="Sunday"  value="0">
                                                         <label for="sun">Sunday</label>
                                                     </div>
                                                 </div>
                                             </div>
								</div>
							</div>
                                     <div class="form-group">
								<label class="col-sm-4 control-label">Public Holidays</label>
								<div class="col-sm-8">
									<table class="table table-bordered table-striped mb-none" id="tabdata">
									    <tbody id="specialHolidaysList">
									        <tr>
									            <td>
									                <div class="input-group">
									                    <span class="input-group-addon">
									                        <i class="fa fa-calendar"></i>
									                    </span>
									                    <input type="text" class="form-control" data-plugin-datepicker="" id="inputDate1" name="inputDateA" readonly="readonly">
														<input type="hidden" name="mode" id="mode" value="Public"/>
									    			</div>
												</td>
												<td>
												    <a class="on-default" href="#">
												        <i class="fa fa-minus-square" onclick="deleteTableRow(this);"></i>
													</a>
												</td>
												          
											</tr>
											
	                                    </tbody>
	                                    <tfoot>
	                                    	<tr>
	                                    		<td colspan="2">
	                                    			<input id="moreDates" type="button" class="btn btn-primary pull-right" value = "Add" onclick="addRowNew();" />
	                                    		</td>
	                                    	</tr>
	                                    </tfoot>
	                                </table>
								</div>
							</div>
                            <div class="form-group">
								<label class="col-sm-5 control-label"></label> 
								<div class="col-sm-7">
									<button class="btn btn-danger pull-right" id="bt" name="bt" value="calculate" name="Calculate" onclick="showdivone();" type="button">Calculate </button>
								</div>
							</div>
                                         
						</div>
                        <hr>
                        <div style="display: none;" class="panel-body" id="showdivone">
                        	<div class="form-group">
								<label class="col-sm-5 control-label">Total Holidays</label>
								<div class="col-sm-7">
									<input id="tholi" name="tholi" readonly="readonly" class="form-control" type="text">
								</div>
							</div>
						</div>
						<footer id="submitFooter" style="display: none;" class="panel-footer">
							<button class="btn btn-primary" type="submit" value="submit" id="submitbutton">Submit </button>
						</footer>
						<input type="hidden" id="checkHoliday" name="checkHoliday" value=""/>
						<input type="hidden" id="countHoliPerTerm" name="countHoliPerTerm" value=""/>
					</section>
                 </form>
			</div>
		</div>	
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/configureWorkingDays.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/workingDays.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/adddelete.js"></script>
<script type="text/javascript">
var y=1;
function addRowNew(){
		var inputDateA="inputDateA";
        var inpdtA= inputDateA + y; 
        var serialNo1=document.getElementsByName("inputDateA");
        var id ;
        for(var i=0;i<serialNo1.length;i++){ 
        	id="inputDate"+(i+1);
        	serialNo1[i].setAttribute("id",id);
    	}
        var calId = "#"+id;
        var innerHeight2=document.body.scrollHeight;
		  var frame=window.parent.document.getElementById("frame");	    	
		  frame.style.height = innerHeight2+25+ 'px';
        y++;
        
       
		var row = "<tr>" +
				"<td><div class='input-group'><span class='input-group-addon'><i class='fa fa-calendar'></i></span><input type='text' id='"+id+"' name='"+inputDateA+"' readonly='readonly' class='form-control hasDate'></div></td>" +
				"<td><a class='on-default' href='#'><i class='fa fa-minus-square' onclick='deleteTableRow(this);'></i></a></td>" +
				"</tr>";
		$("#specialHolidaysList").append(row);
		
		$(".hasDate").datepicker({	 		 
	 		 dateFormat: 'dd/mm/yy',
	    });
}

function deleteTableRow(obj){
	var table = document.getElementById("tabdata");
	var rowCount = table.rows.length;
	if(rowCount>2){
		var p = obj.parentNode.parentNode.rowIndex;
		table.deleteRow(p);
	}else{
		alert("Atleast One Row Required");
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}
} 

</script>
</body>
</html>