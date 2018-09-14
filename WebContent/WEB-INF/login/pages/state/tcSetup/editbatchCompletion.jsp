<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<script type="text/javascript">

$(function() {	
	/*  showRemark("0"); */
 
	 
	 var batchStartDate=$("#batchStartDateId").val();	 
	 var batchFreezeDate=$("#batchFreezeDateId").val();
	 var batchEndDate=$("#batchEndDateId").val();	 
	 //var CommencedDate=$("#batchCommencedDateId").val();	 
	 var ojtStartDate=$("#ojtStartDateId").val();	 

	 
	
	 
	 
	 
	  if(batchStartDate!=null && batchStartDate!=""){
		var date2 = new Date(batchStartDate);
		$("#batchStartDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	  };
	  
	     if(batchFreezeDate!=null && batchFreezeDate!=""){
		var date2 = new Date(batchFreezeDate);
		$("#batchFreezeDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	     };
	    
	        if(batchEndDate!=null && batchEndDate!=""){
		var date2 = new Date(batchEndDate);
		$("#batchEndDateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	    	    
	       }; 
	       if(batchEndDate!=null && batchEndDate!=""){
	       var date2 = new Date(batchEndDate);
	       $("#batchEndDateId").datepicker('option', 'minDate', date2);
			  $("#batchEndDateId").datepicker('option', 'maxDate', date2);
	       };
	     /*   if(CommencedDate!=null && CommencedDate!=""){
				var date2 = new Date(CommencedDate);
				$("#batchCommencedDateId").datepicker({
			        dateFormat: 'dd-mm-yy'
			    }).datepicker('setDate', date2)
			  };*/
			  if(ojtStartDate!=null && ojtStartDate!=""){
					var date2 = new Date(ojtStartDate);
					$("#ojtStartDateId").datepicker({
				        dateFormat: 'dd-mm-yy'
				    }).datepicker('setDate', date2)
				  }; 
	
			  
    $("#batchStartDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    $("#batchFreezeDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    $("#batchEndDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
  //  $("#batchCommencedDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
    $("#ojtStartDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 

    
	$("#batchStartDateId").datepicker('option', { maxDate:new Date()});
	$("#batchFreezeDateId").datepicker('option', { maxDate:new Date()});
	//$("#batchCommencedDateId").datepicker('option', { maxDate:new Date()});
	$("#ojtStartDateId").datepicker('option', { maxDate:new Date()});

    
	  
});

 

function getXMLHttpRequest() {
	var xmlHttpReq = false;
	// to create XMLHttpRequest object in non-Microsoft browsers
	
	if (window.XMLHttpRequest) {
		xmlHttpReq = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
	} else if (window.ActiveXObject) {
		try {
			// to create XMLHttpRequest object in later versions
			// of Internet Explorer
			xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (exp1) {
			try {
				// to create XMLHttpRequest object in older versions
				// of Internet Explorer
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (exp2) {
				xmlHttpReq = false;
			}
		}
	}
	return xmlHttpReq;
}; 

	function getTCList(projectid){
	  
		var xmlHttpRequest = getXMLHttpRequest();
		
		document.getElementById("sectorId").innerHTML = '<option value=""> --SELECT-- </option>';
		document.getElementById("tradeId").innerHTML = '<option value=""> --SELECT-- </option>';
 
		  xmlHttpRequest.onreadystatechange = function(){ 
				if (xmlHttpRequest.readyState == 4) {
					if (xmlHttpRequest.status == 200) {
						if (xmlHttpRequest.responseText != "") {
						 
 							 
							document.getElementById("tcIDId").innerHTML = xmlHttpRequest.responseText ;
						} 
						
						else {
							 alert("PROJECT DOESN'T HAVE ACTIVE TC");
							document.getElementById("tcIDId").innerHTML = '<option value=""> --SELECT-- </option>';
							document.getElementById("batchIDId").value = '';

				    	} 
					} 	 
			    	  
				}
			}
		
	xmlHttpRequest.open("POST","batchCompletion.do?methodName=showTCList", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);
	};
	
	function save() {		
		var x=checkPermissionForFormForInsert();
		if(x=='true'){
			var id=$('#bactchide').val();
			var batchEndDate=document.getElementById("batchEndDateId").value;
			document.forms[0].action="batchCompletion.do?methodName=updateBatchDetails&id="+id+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	};
  function back(){ 
				document.forms[0].action="batchCompletion.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
		};
   
  function showSector(tcid){ 
	  
			var xmlHttpRequest = getXMLHttpRequest();
			  xmlHttpRequest.onreadystatechange = function(){ 
					if (xmlHttpRequest.readyState == 4) {
						if (xmlHttpRequest.status == 200) {
							if (xmlHttpRequest.responseText != "") {	  							 
								document.getElementById("sectorId").innerHTML = xmlHttpRequest.responseText ;
							} 
						} else {
							 
							document.getElementById("sectorId").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					}
				}
			
		xmlHttpRequest.open("POST","batchCompletion.do?methodName=showSectorList", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("tcid="+tcid);  
		
  };
  
  
  function showTrade(sector){ 
			var xmlHttpRequest = getXMLHttpRequest();
			  xmlHttpRequest.onreadystatechange = function(){ 
					if (xmlHttpRequest.readyState == 4) {
						if (xmlHttpRequest.status == 200) {
							if (xmlHttpRequest.responseText != "") {	 						 
								document.getElementById("tradeId").innerHTML = xmlHttpRequest.responseText ;
							} 
						} else {							 
							document.getElementById("tradeId").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					}
				}
			
		xmlHttpRequest.open("POST","batchCompletion.do?methodName=showTradeList", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("sector="+sector);  
		
};
  
  
function showbatchID(){ 
	  
	  var srno=document.getElementById("srno").value;
	  if(srno==NaN||srno==""||srno==null||srno==false)
			  {
		  var sr=1;
			  } 
	  else {
		  var sr=1+parseInt(srno);
	  }
	  var tcid=document.getElementById("tcIDId").value;
	  var sector=document.getElementById("sectorId").value;
	  var trade=document.getElementById("tradeId").value;
	  var id=tcid+sector+trade+sr;		
		//document.getElementById("batchIDId").value=id;
		showbatch(sector,trade,tcid);
		
		
};



function checkResi(resi){ 
 	  
	  if(resi=="RESIDENTIAL")
		  {
		  document.getElementById("res").style.display=""
		  document.getElementById("resiSizeId").style.display=""
 		  document.getElementById("resiSizeId").value=document.getElementById("batchSizeId").value;
		  document.getElementById("resiSizeId").readOnly=true;
		     //document.getElementById.("resiSizeId").setAttribute("readonly", "true");
		  }
	  else if (resi=="NON RESIDENTIAL")
		  {
 		  document.getElementById("res").style.display="none"
		  document.getElementById("resiSizeId").style.display="none"
		  }
	  else  
	  { 
		  document.getElementById("res").style.display="";
		 		  document.getElementById("resiSizeId").style.display="";
				  document.getElementById("resiSizeId").value="";
				  document.getElementById("resiSizeId").readOnly=false;			 
			  } 
		 		
		};

		function checkData(data){ 
			  var x = Number(data)
		 	  if(x>35)
				  {
				  alert("batch size can't be more than 35");
				  }		
		};

		function checktotal(data){ 
			   
			var sc=  document.getElementById("commenced_ScId").value;
			var st=  document.getElementById("commenced_StId").value;
			var others=  document.getElementById("commenced_OthersId").value;
			var myTotal=Number(sc)+Number(st)+Number(others); 	
		 
			var batch= Number(document.getElementById("batchSizeId").value);
 			if(myTotal>batch)
			{
				alert(" total can,t be more than batch size");
				document.getElementById("commenced_TotalId").value="";
			}		 
			else  
			{
				document.getElementById("commenced_TotalId").value=myTotal;
			} 		 	   	
		};
			





function showbatch(sector,trade,tcid){ 
	var sector=sector;
	var trade=trade;
	var tcid=tcid;

	var xmlHttpRequest = getXMLHttpRequest();
	  xmlHttpRequest.onreadystatechange = function(){ 
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					if (xmlHttpRequest.responseText != "") { 	
						
						document.getElementById("batchIDId").value = xmlHttpRequest.responseText ;
					} 
				} else {							 
					document.getElementById("batchIDId").innerHTML = '<option value=""> --SELECT-- </option>';
		    	} 
			}
		}
	
xmlHttpRequest.open("POST","batchCompletion.do?methodName=showbatchId", true);
xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
xmlHttpRequest.send("sector="+sector+"&"+"trade="+trade+"&"+"tcid="+tcid);  

}; 
  
  function isNumberKey(evt)
	{
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	      return false;
	   

	   return true;
	};
  
	   
	 function showstartDate(projectid,startDate){	
				 
		  		      var projectid=projectid;  	  			 
				    
				   var xmlHttpRequest = getXMLHttpRequest();
			       xmlHttpRequest.onreadystatechange = function(){  			  
						if (xmlHttpRequest.readyState == 4) {
							if (xmlHttpRequest.status == 200) {
								if (xmlHttpRequest.responseText != "") { 		
									
									var ddDate=xmlHttpRequest.responseText;	
	 					 			 var startDate=document.getElementById("batchStartDateId").value;	
	 					 			var  dd_date = ddDate.split('-');
	 					 		    var start_date = startDate.split('-');

	 					 		    var new_start_date = new Date(start_date[2],start_date[1]-1,start_date[0]);
	 					 		    var new_dd_date = new Date(dd_date[2],dd_date[1]-1,dd_date[0]);
	 					 		    
	 					 		  if(new_dd_date < new_start_date) {
	 					 	    	alert("dd date cannot be earlier than Date of Sanction");
	 					 			document.getElementById("batchStartDateId").style.border="1px solid red";
	 					 			document.getElementById("batchStartDateId").focus(); 
	 					 			return;
							  	} 
								else 
								{ 
									alert("project not yet sanction");
 
						    	} 
							} 
						}
					}
				
			xmlHttpRequest.open("POST","batchCompletion.do?methodName=showStartDate", true);
			xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
			xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
			xmlHttpRequest.send("projectid="+projectid);
			}; 
	 }; 
			 
			     function compareDate(date){ 	
	 		 			 var projectid=document.getElementById("projectIDId").value;	 			  
			 			 var startDate=document.getElementById("batchStartDateId").value;
	 					 showstartDate(projectid,startDate);					  
					};      
		 
						
	
	
  
</script>
<!--Main form section starts here-->
<html:form action="login/batchCompletion">	

			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">BATCH COMPLETION</td>
               </tr>				
			</table>
 
			 <table width="100%" align="center" class="inputTBL">
			 <tr>
			 <th>PROJECT ID<span class="text-error"></span></th>
				 <td><span class="text-error"><html:text property="projectID" styleId="projectIDId"  value="${prid}" readonly="true" /> </span></td>
				</tr> 
				 <tr>	
				<th>TRAINING CENTER ID<span class="text-error"></span></th>
				 <td><span class="text-error">  <html:text property="tcID" styleId="tcIDId"  value="${tcID}" readonly="true" /> </span></td>
				</tr> 
				 <tr>	
				<th>SECTOR<span class="text-error"></span></th>
				 <td><span class="text-error">  <html:text property="sector" styleId="sectorId"  value="${sector}" readonly="true" /> </span></td>
				</tr> 
				 <tr>	
				<th>TRADE<span class="text-error"></span></th>
				 <td><span class="text-error"><html:text property="trade" styleId="tradeId" value="${trade}"  readonly="true" /> </span></td>
				</tr> 	
			 
			 
			 
		<%-- 	 <tr>	
					<th>PROJECT ID<span class="text-error"></span></th>
					<td><html:select property="projectID" styleId="projectIDId" value="${prid}"   styleClass="form-control"  onchange="javascript:getTCList(this.value);"    >								 
								<logic:present name="projectlist">
								 <html:option value="0">--SELECT--</html:option>
									<logic:iterate id="list" name="projectlist">									
										<html:option value="${list.id }">${list.projectID}</html:option>
									</logic:iterate>
								</logic:present> 
							</html:select></td>	 
 							 				 
				</tr>	  
					  
				 	  <tr>	
				       	<th>TC ID<span class="text-error"></span></th>
					<td><html:select property="tcID" styleId="tcIDId"   styleClass="form-control"    value="${myTcId}" onchange="javascript:showSector(this.value); " >	
					  	 		<logic:present name="tcList">
									<logic:iterate id="list" name="tcList">
										<html:option value="${list.id}">${list.trainningCenter.tcID}(${list.trainningCenter.district.districtName})</html:option>
									</logic:iterate>
								</logic:present>			  
					</html:select></td>					 
				</tr>	
	 
				<tr>	
				
					<th>SECTOR<span class="text-error"></span></th>
				<td><html:select property="sector" styleId="sectorId"   styleClass="form-control"   value="${mySector}"  onchange="javascript:showTrade(this.value); "   >					 
					 		 <html:option value="0">--SELECT--</html:option>
					 			<logic:present name="sectorlist">
									<logic:iterate id="list" name="sectorlist">
										<html:option value="${list.sector.sectorId}">${list.sector.sectorName}</html:option>
									</logic:iterate>
								</logic:present>
					
					 </html:select></td>
				</tr>
				<tr>	
					<th>TRADE<span class="text-error"></span></th>
				<td><html:select property="trade" styleId="tradeId"   styleClass="form-control"    value="${myTrade}" onchange=  "javascript:showbatchID();">	
				 				 <html:option value="0">--SELECT--</html:option>
				 				<logic:present name="tradelist">
									<logic:iterate id="list" name="tradelist"> 
										 <html:option value="${list.trade.tradeId}">${list.trade.tradeName}(${list.trade.tradeCode})</html:option>
									</logic:iterate>
								</logic:present>
					 </html:select></td>
				</tr> --%>
				
			<tr>
			<th>BATCH ID<span class="text-error"></span></th>
				 <td><span class="text-error"><html:hidden property="id" styleId="bactchide" value="${batchCompleteDetail.id}"></html:hidden> <html:text property="batchID" styleId="batchIDId"   readonly="true" /> </span></td>
				</tr> 			 
				
				
					<tr>	
					<th>BATCH DURATION<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchDuration" readonly="true"  value="${batchDetail.batchDuration}" styleId="batchDurationId"  /> </span></td>
				<!-- </tr>  
				<tr> -->	
					<th>BATCH FREEZE DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchFreezeDate" readonly="true" onchange="javascript:validFreezeDate(this.value);" styleId="batchFreezeDateId" value="${batchDetail.batchFreezeDate}" /> </span></td>					 
				 </tr> 
				 
				<tr>  	
					<th>BATCH SIZE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchSize"  readonly="true" onchange="javascript:checkData(this.value);" styleId="batchSizeId" value="${batchDetail.batchSize}" onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>	
					 
				<tr>	
					<th>BATCH START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchStartDate" readonly="true" onchange="javascript:compareDate(this.value);"  styleId="batchStartDateId" value="${batchDetail.batchStartDate}" /> </span></td>					 
				</tr> 
				
				<tr>
				  <th>OJT START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="ojtStartDate" readonly="true" value="${batchDetail.ojtStartDate}" styleId="ojtStartDateId" /> </span></td>
				</tr> 
				 
				<%-- <tr>	
					<th>BATCH END DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchEndDate"  styleId="batchEndDateId"  value="${batchDetail.batchEndDate}" /> </span></td>					 
				</tr>  --%>
				
<!--   new entry -->

			


<%-- 
				<tr>	
					<th>BATCH COMMENCED DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchCommencedDate"  value="${batchDetail.batchCommencedDate}" styleId="batchCommencedDateId" /> </span></td>					 
				</tr>  --%>
				<tr>	
					<th>BATCH TYPE<span class="text-error"></span></th>
				 	
				 <td><html:select property="batchType" styleId="batchTypeId"   value="${batchDetail.batchType}" onchange="javascript:checkResi(this.value);"   styleClass="form-control">					
 					 <html:option value="0">--SELECT--</html:option>		
 			         <html:option value="RESIDENTIAL">RESIDENTIAL</html:option>					 
 			         <html:option value="NON RESIDENTIAL">NON RESIDENTIAL</html:option>					 
 				     <html:option value="PARTIAL">PARTIAL</html:option>		
					 </html:select></td>				 
			<!-- 	</tr> 
				
				
				<tr> -->	
					<th id="res">RESIDENTIAL CAPACITY <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="resiSize"  styleId="resiSizeId" readonly="true" value="${batchDetail.resiSize}"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr> 
				<tr>	
				<th>SC CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Sc" readonly="true"  value="${batchDetail.commenced_Sc}" styleId="commenced_ScId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>				
				<tr> -->	
					<th>ST CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_St" readonly="true" value="${batchDetail.commenced_St}" styleId="commenced_StId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>			
				
				<tr>	
					<th>OTHERS CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Others" readonly="true" value="${batchDetail.commenced_Others}" styleId="commenced_OthersId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>		
				
					<tr> -->	
					
				<%-- 	<th>TOTAL CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Total"  styleId="commenced_TotalId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				 --%>
				</tr>	
				
				<tr>	
					<th>WOMEN CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Women"  readonly="true" value="${batchDetail.commenced_Women}" styleId="commenced_WomenId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>							
				
				<tr> -->	
					<th>MINORITY CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Minority" readonly="true" value="${batchDetail.commenced_Minority}" styleId="commenced_MinorityId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>				
				
				<tr>	
					<th>PWD CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Pwd" readonly="true" value="${batchDetail.commenced_Pwd}" styleId="commenced_PwdId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>			
				 			
			   <tr>	
				<th>BATCH END DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchEndDate"  styleId="batchEndDateId"  value="${batchCompleteDetail.batchEndDate}" /> </span></td>					 
				</tr>			
				
				 <tr>	
				<th>SC CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Sc"  value="${batchCompleteDetail.complet_Sc}" styleId="complet_ScId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				   </tr>				
				<tr>  
					<th>ST CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_St"  value="${batchCompleteDetail.complet_St}" styleId="complet_StId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>			
				
				<tr>	
					<th>OTHERS CANDIDATEDS COMPLETED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Others"  value="${batchCompleteDetail.complet_Others}" styleId="complet_OthersId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>		
				
					<tr> 
					
				 	<th>TOTAL CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Total"  styleId="complet_TotalId"  onkeypress="return isNumberKey(event)"  value="${batchCompleteDetail.complet_Total}" /> </span></td>					 
				  
				</tr>	
				
				<tr>	
					<th>WOMEN CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Women"   value="${batchCompleteDetail.complet_Women}" styleId="complet_WomenId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>							
				
				<tr> -->	
					<th>MINORITY CANDIDATEDS COMPLETED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Minority"  value="${batchCompleteDetail.complet_Minority}" styleId="complet_MinorityId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>				
				
				<tr>	
					<th>PWD CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Pwd"  value="${batchCompleteDetail.complet_Pwd}" styleId="complet_PwdId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>	
				
				
				
				
					 </table>
			 	
			 		<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert"  value="Update" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>
	 
</html:form>
