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
<title>Create Transactions</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script>
 
var date=1;
function addRow(){
	var table = document.getElementById("passbookTable"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    var cell;
    var element;
    var id1="date"+date;
    var id2="instrumentDate"+date;    
    cell = row.insertCell(0);
    element = document.getElementById("bank").cloneNode(true);
    element.name="bank";
    element.required = true;
    element.setAttribute("data-plugin-datepicker", "bac");
    cell.appendChild(element);
    
    cell = row.insertCell(1);
    element = document.createElement("input");
    element.type = "text";
    element.name="date";
    element.required = true;
    element.id=id1;
    element.setAttribute("class","form-control");    
	cell.appendChild(element);
	
    cell = row.insertCell(2);
    element = document.createElement("textarea");
    element.name="particulars";
    element.required = true;
    element.setAttribute("class","form-control");
    cell.appendChild(element);
    
    cell = row.insertCell(3);
    element = document.createElement("select");
    element.innerHTML="<option value=''>Select..</option><option value='true'>Withdraw</option><option value='false'>Deposit</option>";
    element.name="withdraw";
    element.required = true;
    element.setAttribute("class","form-control");
    cell.appendChild(element);
    
    cell = row.insertCell(4);
    element = document.createElement("input");
    element.type="text";
    element.name="instrumentNumber";
    element.required = true;
    element.setAttribute("class","form-control");
    cell.appendChild(element);
    
    cell = row.insertCell(5);
    element = document.createElement("input");
    element.type="text";
    element.name="instrumentDate";
    element.required = true;
    element.id=id2;
    element.setAttribute("class","form-control");
    element.setAttribute("data-plugin-datepicker", "");
    cell.appendChild(element);
    
    cell = row.insertCell(6);
    element = document.createElement("select");
    element.innerHTML="<option value=''>Select..</option><option value='true'>Debit</option><option value='false'>Credit</option>";
    element.name="debit";
    element.required = true;
    element.setAttribute("class","form-control");
    cell.appendChild(element);
    
    cell = row.insertCell(7);
    element = document.createElement("input");
    element.type="text";
    element.name="balance";
    element.required = true;
    element.setAttribute("class","form-control");
    cell.appendChild(element);
    
    cell = row.insertCell(8);
    element = document.createElement('a');
    element.setAttribute("onclick","return deleteRow(this);");
    element.setAttribute("class","fa fa-minus-square");
    element.setAttribute("href","#");
    /* element = "<img src='/icam/images/minus_icon.png' onclick='deleteRow(this);'>"; */
    cell.appendChild(element);
    
    id1="#"+id1;
    id2="#"+id2;
    date++;
}

function deleteRow(obj){	
	var table = document.getElementById("passbookTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
	}else{
// 		document.getElementById("warningbox").style.visibility='visible';
// 		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		alert("Atleast One Row Required");
	}
}
 </script>
</head>
<body>
	<c:choose>
		<c:when test="${message ne null}">		
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">${message}</span>	
				</div>		
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form name="" action="savePassbook.html" method="POST" >		
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<h2 class="panel-title">PassBook</h2>										
							</header>
							<div style="display: block;" class="panel-body">                   
								<div class="col-md-12">
										<table class="table table-bordered table-striped mb-none" id="passbookTable">
											<thead>
												<tr>
		                                            <th>Bank</th>
													<th>Date</th>
													<th>Particulars</th>
													<th>Withdraw/Deposit</th>
													<th>Instrument No.</th>
													<th>Instrument Dt.</th>
													<th>Debit/Credit</th>
													<th>Balance</th>
													<th><a class="on-default remove-row" href="#"><i class="fa fa-plus-square" onclick="addRow();"></i></a></th>
												</tr>
											</thead>
											<tbody>
												<tr>
		                                       		<td>
					                                     <select name="bank" class="form-control" required>
					                                        <option value="">Select..</option>
															<c:if test="${allBanks ne null}">
																<c:forEach var="banks" items="${allBanks}">
																	<option value="${banks}">${banks}</option>
																</c:forEach>
															</c:if>
					                                     </select>
													</td>
													<td>
														<input type="text" class="form-control" name="date" id="transactionDate" data-plugin-datepicker="" required/>
				                                    </td>
													<td>
														<textarea name="particulars" rows="2" data-plugin-maxlength="" maxlength="140" class="form-control" required></textarea>
													</td>
													<td>
														<select name="withdraw" class="form-control" required>
															<option value="">Select..</option>
															<option value="true">Withdraw</option>
															<option value="false">Deposit</option>	
							                            </select>
													</td>
													<td>
														<input type="text" class="form-control" name="instrumentNumber" required>
													</td>
													<td>													
														<input type="text" class="form-control" name="instrumentDate" id="instrumentDate0" data-plugin-datepicker="" required/>
				                                    </td>
													<td>
														<select name="debit" class="defaultdropdown form-control" required>
															<option value="">Select..</option>				                                        
															<option value="true">Debit</option>
															<option value="false">Credit</option>
						                                </select>
						                            </td>				
													<td>
														<input type="text" class="form-control" name="balance" required>
													</td>				
													<td>
														<a class="on-default remove-row" href="#"><i class="fa fa-minus-square" onclick="deleteRow(this);"></i></a>
													</td>
												</tr>
											</tbody>
										</table>
										<div style="display: none;">
											<select id="bank" class="defaultdropdown form-control" >
												<option value="">Select..</option>
												<c:if test="${allBanks ne null}">
													<c:forEach var="banks" items="${allBanks}">
														<option value="${banks}">${banks}</option>
													</c:forEach>
												</c:if>
											</select>
										</div>
									</div> 
								</div>						
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" type="submit" value="SUBMIT">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>			
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>