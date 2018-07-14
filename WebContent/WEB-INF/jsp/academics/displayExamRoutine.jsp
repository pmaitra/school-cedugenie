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
<title>Display Exam Seating</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: center;
}
.editableTable {
    border:solid 1px;
    width:100%
}
 
.editableTable td {
    border:solid 1px;
}
 
.editableTable .cellEditing {
    padding: 0;
}
 
.editableTable .cellEditing input[type=text]{
    width:100%;
    border:0;
    background-color:rgb(255,253,210);  
}

.tdconflictBackground{
  background-color:red;
}

.tdnoconflictBackground{
  background-color:white;
}
</style>
</head>
<body>
	<header class="page-header">
			<h2>Display Exam Routine</h2>
		</header>
	 <div class="row">
	 <%-- 	<form name="submitExamRoutine" id="submitExamRoutine" action="submitExamRoutine.html" method="post"> --%>
	 	<div class="col-md-8 col-md-offset-2">
	 	<section class="panel">
              <header class="panel-heading">
                  <div class="panel-actions">
                      <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                  </div>

                  <h2 class="panel-title">Display Exam Routine</h2>										
              </header>
              <div style="display: block;" class="panel-body">
				 	<div class = "form-group">
					 	
					 		<div class="col-sm-6">	
								<label class="control-label"><b>Exam Name: <span class="required" aria-required="true">*</span></b></label>
								<select class="form-control" name="exam" id="exam" required>
				                      <option value="">Select...</option>
				                      <c:forEach var="exam" items="${examList}" varStatus="i">
											<option value="${exam.examCode}">${exam.examName}</option>
									</c:forEach>
				                 </select>
							
							</div>
						
						</div>
				 	</div>
				
			</section>
			</div>
			<div class="col-md-8 col-md-offset-2">
				            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                                    </div>

                                    <h2 class="panel-title">Routine</h2>										
                                </header>
                                <div style="display: block;" class="panel-body">
                                	<div class="table-responsive">
	                                	<table class="table table-bordered table-striped mb-none" id = "routineArrangements">
	                                		<tbody id = "routineArrangementsBody">
	                                		</tbody>
	                                	</table>
                                    </div>
                                    
                                </div>
                            </section>
						</div>
						
						<%-- <footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button id="submit" class="btn btn-success" style = "display:none">Submit</button>
									
								</div>
							</div>
						</footer>
				</form> --%>
	 </div>
	<!--  <div class="row"> -->
						
						
					<!-- </div>	 -->
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %> 
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/academics/displayExamRoutine.js"></script>
</body>
</html>