<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<script type="text/javascript">

$(function() {	
	 showRemark("0");
    $("#batchStartDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    $("#batchFreezeDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    $("#batchEndDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
    $("#ojtStartDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
    
	$("#batchStartDateId").datepicker('option', { maxDate:new Date()});
	$("#batchFreezeDateId").datepicker('option', { maxDate:new Date()});
	$("#ojtStartDateId").datepicker('option', { maxDate:new Date()});

    
    
});

function showRemark(str){
	if (str=="0")
	{
		//document.getElementById("batchcreation").innerHTML = '<option value=""> --SELECT-- </option>';
		//document.getElementById("showProject").style.display = "";	
		//document.getElementById("ShowTClist").style.display = "none";
		document.getElementById("showtable").style.display = "none";		
		
 			
	} else{
		/* document.getElementById("showProject").style.display = "none";
		document.getElementById("ShowTClist").style.display = "none";
		document.getElementById("showTable").style.display = "none"; */
	}
}

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
		  xmlHttpRequest.onreadystatechange = function(){ 
				if (xmlHttpRequest.readyState == 4) {
					if (xmlHttpRequest.status == 200) {
						if (xmlHttpRequest.responseText != "") {  
							document.getElementById("tcIDId").innerHTML = xmlHttpRequest.responseText ;
						} 
						else {
							 alert("PROJECT DOESN'T HAVE ACTIVE TC");
 							document.getElementById("tcIDId").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					} 
				}
			}
		
	xmlHttpRequest.open("POST","batchCompletion.do?methodName=showTCList", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);
	};
	
	function getBatchList(batchId){
 		var xmlHttpRequest = getXMLHttpRequest();
		  xmlHttpRequest.onreadystatechange = function(){ 
				if (xmlHttpRequest.readyState == 4) {
					if (xmlHttpRequest.status == 200) {
						if (xmlHttpRequest.responseText != "") {  
							document.getElementById("showtable").style.display = "";
// 							alert("response"+xmlHttpRequest.responseText);
							var response=JSON.parse(xmlHttpRequest.responseText);
							
 							//alert(response[0].batchID);
							//$("#").val(response[0].id);
							//$("#id").val(response[0].id);
							//alert(response[0].sector+"--"+ response[0].trade+"=="+response[0].projectid);
						/* 	
							$("#tcIDId").val(response[0].tcid);
							$("#tcIDId").val(response[0].tcid);
							$("#tcIDId").val(response[0].tcid); */
							
							$("#projectIDId").val(response[0].projectid);
							$("#sectorId").val(response[0].sector);
							$("#tradeId").val(response[0].trade);
							
							$("#tcIDId").val(response[0].tcid);
							
							
							
							$("#batchSizeId").val(response[0].batchSize);
							$("#batchDurationId").val(response[0].batchDuration);
							$("#resiSizeId").val(response[0].resiSize);
							$("#batchTypeId").val(response[0].batchType);
							$("#commenced_ScId").val(response[0].commenced_Sc);
							$("#commenced_StId").val(response[0].commenced_St);
							$("#commenced_OthersId").val(response[0].commenced_Others);
							$("#commenced_WomenId").val(response[0].commenced_Women);
							$("#commenced_MinorityId").val(response[0].commenced_Minority);
							$("#commenced_PwdId").val(response[0].commenced_Pwd);
							$("#commenced_TotalId").val(response[0].commenced_Total);
							$("#batchIDNew").val(response[0].batchID);
						//	$("#batchIDId").val(response[0].batchId);
							var startdate=new Date(response[0].batchStartDate.split("-")[0]+"-"+response[0].batchStartDate.split("-")[1]+"-"+response[0].batchStartDate.split("-")[2]);
							startdate.setMonth(startdate.getMonth() +parseInt(response[0].batchDuration));
							  $("#batchEndDateId").datepicker({
							        dateFormat: 'dd-mm-yy'
							    }).datepicker('setDate', startdate)
							    $("#batchEndDateId").datepicker('option', 'minDate', startdate);
							  $("#batchEndDateId").datepicker('option', 'maxDate', startdate);
							 
							  var ojt=response[0].ojtStartDate;
							  var batchstartdate=response[0].batchStartDate;
							  var batchfreezdate=response[0].batchFreezeDate;
							  
							  
							  if(ojt!=null && ojt!=""){
							    	var date2 = new Date(ojt);
							    	$("#ojtStartDateId").datepicker({
								        dateFormat: 'dd-mm-yy'
								    }).datepicker('setDate', date2)
							    };
							  if(batchstartdate!=null && batchstartdate!=""){
							    	var date2 = new Date(batchstartdate);
							    	$("#batchStartDateId").datepicker({
								        dateFormat: 'dd-mm-yy'
								    }).datepicker('setDate', date2)
							    };
							  if(batchfreezdate!=null && batchfreezdate!=""){
							    	var date2 = new Date(batchfreezdate);
							    	$("#batchFreezeDateId").datepicker({
								        dateFormat: 'dd-mm-yy'
								    }).datepicker('setDate', date2)
							    };
							 
							 /*  $("#ojtStartDateId").val(response[0].ojtStartDate);
							  $("#batchStartDateId").val(response[0].batchStartDate);
							  $("#batchFreezeDateId").val(response[0].batchFreezeDate); */
							
							 
						} 
						else {
							 
 							document.getElementById("batchcreation").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					} 
				}
			}
		
	xmlHttpRequest.open("POST","batchCompletion.do?methodName=getBatchList", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("batchId="+batchId);
	};
	
	
	
	
	 
	
	
		function save() {
			
			var x=checkPermissionForFormForInsert();
			if(x=='true'){
 		  var batchSize=document.getElementById("batchSizeId").value;
		  var projectID=document.getElementById("projectIDId").value;
		  var tcID=document.getElementById("tcIDId").value;
		  var sector=document.getElementById("sectorId").value;
		  var trade=document.getElementById("tradeId").value;
		  var batchStartDate=document.getElementById("batchStartDateId").value;
		  var batchFreezeDate=document.getElementById("batchFreezeDateId").value;
		  var batchSize=document.getElementById("batchSizeId").value;
		  var ojtStartDate=document.getElementById("ojtStartDateId").value;
		// var total= Number(document.getElementById("commenced_TotalId").value);
         var batch=Number(batchSize);
		 var batchEndDate=document.getElementById("batchEndDateId").value;
		/* 	  if(total!=batch)
		  		{		
					  alert("BATCH SIZE SHOULD BE EQUAL TO TOTAL");			  
		 		 } */
		  
			/*    if(batchEndDate=="0" || batchEndDate=="")
	  		{		
				  alert("PLEASE ENTER VALID BATCH END DATE ");			  
	 		 } 
		  
	 		  else if(batchEndDate<batchFreezeDate)
		  		{		
					  alert("PLEASE ENTER VALID BATCH END DATE ");			  
		 		 }  
				  else
			  {	 */	  
					  
					  
					  var status=window.confirm('Are You Sure You Want to save ?');
				if(status==true){	  
			   document.forms[0].action="batchCompletion.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
				}
			//	}
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
			//document.getElementById("resiSizeId").readonly=false;
			//$("#maskedAmount"+$(this).attr('id')).attr({"onblur":$(this).attr('onblur')});
		  /* 	  var att = document.createAttribute("teadonly");       
			  att.value = "false";                           
					  h.setAttributeNode(att); */
			  }
			/* var batch = document.getElementById("batchTypeId").value
			document.getElementById("resiSizeId").value */
		 		
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
				} ; 
				
				   
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
			 		 			 var projectid=document.getElementById("tcIDId").value;	 			  
					 			 var startDate=document.getElementById("batchStartDateId").value;
			 					 showstartDate(projectid,startDate);					  
							};      
		     
	   
					
</script>
<!--Main form section starts here-->
<html:form action="login/batchCompletion">	
			
			
		<%-- \\<input type="hidden" value="${srNo}" id="srno"/> --%>
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">BATCH COMPLETION</td>
               </tr>				
			</table> 
	 
	 
	  <table width="100%" align="center" class="inputTBL">
			 <tr>	
					<th>COMMENCED BATCH<span class="text-error"></span></th>
					<td><html:select property="batchID" styleId="batchIDId"   styleClass="form-control" onchange="javascript:getBatchList(this.value);">								 
								<logic:present name="batchlist">
								 <html:option value="">--SELECT--</html:option>
									<logic:iterate id="list" name="batchlist">									
										<html:option value="${list.id }">${list.batchID}</html:option>
									</logic:iterate>
								</logic:present> 
							</html:select> </td>		
							
		  	
											 
				</tr>
				
				</table>
				 				
 
		   <div id="showtable">
			 <table width="100%" align="center" class="inputTBL">
			 
			 <tr>	
					<th>PROJECT ID<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="projectID" styleId="projectIDId" readonly="true" /> </span></td>
				</tr> 
			  <tr>	
					<th>TC ID<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="tcID" styleId="tcIDId" readonly="true" /> </span></td>
				</tr> 
			 
			 
			 
			<%--  <tr>	
					<th>PROJECT ID<span class="text-error"></span></th>
					<td><html:select property="projectID" styleId="projectIDId"   styleClass="form-control" onchange="javascript:getTCList(this.value);">								 
								<logic:present name="projectlist">
								 <html:option value="">--SELECT--</html:option>
									<logic:iterate id="list" name="projectlist">									
										<html:option value="${list.id }">${list.projectID}</html:option>
									</logic:iterate>
								</logic:present> 
							</html:select></td>						 
				</tr>	 --%>  
 <%-- 
				 	  <tr>	
				       	<th>TC ID<span class="text-error"></span></th>
					<td><html:select property="tcID" styleId="tcIDId"   styleClass="form-control" onchange="javascript:showSector(this.value); ">	
					 <html:option value="">--SELECT--</html:option> 
							 
							</html:select></td>					 
				</tr>	 --%>
					 
		 
				 <tr>	
					<th>SECTOR<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="sector" styleId="sectorId" readonly="true" /> </span></td>
				</tr> 
				<tr>	
					<th>TRADE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="trade" styleId="tradeId" readonly="true" /> </span></td>
				</tr> 
				
			<%-- 	
				<tr>		
					<th>SECTOR<span class="text-error"></span></th>
				<td><html:select property="sector" styleId="sectorId"   styleClass="form-control" onchange="javascript:showTrade(this.value); "   >					 
					 <html:option value="">--SELECT--</html:option>
					 </html:select></td>
				<!-- </tr>
				<tr> -->	
					<th>TRADE<span class="text-error"></span></th>
				<td><html:select property="trade" styleId="tradeId"   styleClass="form-control"   onchange=  "javascript:showbatchID();">					
 					 <html:option value="">--SELECT--</html:option>					 
					 </html:select></td>
				</tr>  --%>
				
				
				
				
				
				
				
				
				
				<tr>	
					<th>BATCH ID<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchID" readonly="true" styleId="batchIDNew"  /> </span></td>
				</tr> 				 
				 
				 	<tr>	
					<th>BATCH DURATION<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchDuration" readonly="true" styleId="batchDurationId"  /> </span></td>
				<!-- </tr>  
					 
				 <tr> -->	
					<th>BATCH FREEZE DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchFreezeDate" readonly="true"  onchange="javascript:validFreezeDate(this.value);" styleId="batchFreezeDateId" /> </span></td>					 
				</tr> 
				<tr>
				 	<th>BATCH SIZE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchSize" readonly="true" styleId="batchSizeId" onchange="javascript:checkData(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>
				 
				 
				<tr> -->	
					<th>BATCH START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchStartDate" readonly="true"  onchange="javascript:compareDate(this.value);" styleId="batchStartDateId" /> </span></td>					 
				</tr> 
				 <tr>	
					<th>OJT START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="ojtStartDate" readonly="true" styleId="ojtStartDateId" /> </span></td>
				</tr> 
				 
				 
				<%--  
				
				<tr>	
					<th>BATCH COMMENCED DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchCommencedDate" readonly="true" styleId="batchCommencedDateId" /> </span></td>					 
				</tr> --%>
				
				<tr>	
					<th>BATCH TYPE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchType" readonly="true" styleId="batchTypeId" /> </span></td>					 
				</tr>
				
				<%-- <tr>	
					<th>BATCH TYPE<span class="text-error"></span></th>				 	
				    <td><html:select property="batchType" styleId="batchTypeId"     onchange="javascript:checkResi(this.value);" styleClass="form-control">					
 					 <html:option value="0">--SELECT--</html:option>		
 			         <html:option value="RESIDENTIAL">RESIDENTIAL</html:option>					 
 			         <html:option value="NON RESIDENTIAL">NON RESIDENTIAL</html:option>					 
 				     <html:option value="PARTIAL">PARTIAL</html:option>		
					 </html:select></td>				 
				</tr>  --%>
				
				<tr>	
					<th id="res">RESIDENTIAL CAPACITY <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="resiSize"  styleId="resiSizeId" readonly="true"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>
				<!--  <tr> </tr> 	
				  <tr align="right"> <th><td align="right">CATEGORY WISE CANDIDATEDS COMMENCED</td></th></tr> 		
				   <tr> </tr> 	 -->
				   
				<tr> 	
					<th>SC CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Sc"   styleId="commenced_ScId" readonly="true" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>				
				<tr> -->	
					<th>ST CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_St"   styleId="commenced_StId" readonly="true" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>	
				
				<tr>  	
					<th>OTHERS CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Others" readonly="true"  onchange="javascript:checktotal(this.value);"   styleId="commenced_OthersId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
			 	</tr>
				
				<tr>	 
					<th>WOMEN CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Women" readonly="true"  styleId="commenced_WomenId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>							
				
				<tr> -->	
					<th>MINORITY CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Minority" readonly="true"  styleId="commenced_MinorityId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>	
				<tr>  
					<th>PWD CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Pwd"  readonly="true"  styleId="commenced_PwdId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr> 
				
				<tr>	
					<th>BATCH END DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchEndDate" styleId="batchEndDateId" /> </span></td>					 
				</tr> 
				
				<tr> 
					
				 	<th>TOTAL CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Total"  styleId="complet_TotalId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  
				</tr>	
				
				
				<tr>
				<th>SC CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Sc"    styleId="complet_ScId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				
				<th>ST CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_St"    styleId="complet_StId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>			
				
				<tr>  
					<th>OTHERS CANDIDATEDS COMPLETED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Others"    styleId="complet_OthersId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				
					<th>WOMEN CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Women"     styleId="complet_WomenId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<tr>
					<th>MINORITY CANDIDATEDS COMPLETED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Minority"    styleId="complet_MinorityId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				 		
				 	
					<th>PWD CANDIDATEDS COMPLETED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="complet_Pwd"   styleId="complet_PwdId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>	
				
					 </table>
					 </div>  
			 	
			 <div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert"  class="checkPermissionForFormForInsert"  value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>
	 
</html:form>
