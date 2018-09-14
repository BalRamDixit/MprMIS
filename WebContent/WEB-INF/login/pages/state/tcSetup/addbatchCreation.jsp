<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<script type="text/javascript">

$(function() {	
	// showRemark("0");
    $("#batchStartDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    $("#batchFreezeDateId").datepicker({ dateFormat: 'dd-mm-yy' });
    //$("#batchEndDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
    $("#ojtStartDateId").datepicker({ dateFormat: 'dd-mm-yy' }); 
    
	$("#batchStartDateId").datepicker('option', { maxDate:new Date()});
	//$("#batchFreezeDateId").datepicker('option', { maxDate:new Date()});
	//$("#ojtStartDateId").datepicker('option', { maxDate:new Date()});

    
    
});

function showRemark(str){
	if (str=="0")
	{
		document.getElementById("showProject").style.display = "";	
		document.getElementById("ShowTClist").style.display = "none";
		document.getElementById("showTable").style.display = "none";		
		
 			
	} else{
		document.getElementById("showProject").style.display = "none";
		document.getElementById("ShowTClist").style.display = "none";
		document.getElementById("showTable").style.display = "none";
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
							//alert("1-->  "+projectid);
							//showstartDate(projectid);
						} 
						else {
							 alert("PROJECT DOESN'T HAVE ACTIVE TC");
 							document.getElementById("tcIDId").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					} 
				}
			}
		
	xmlHttpRequest.open("POST","batchCreation.do?methodName=showTCList", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);
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
		 var total= Number(document.getElementById("commenced_TotalId").value);
         var batch=Number(batchSize);
		//  var batchEndDate=document.getElementById("batchEndDateId").value;
		  
		
		  if(batchSize=="0" || batchSize=="")
			  {
			  alert("PLEASE ENTER VALID BATCH SIZE");			  
			  }
		  else if(projectID=="0" || projectID=="")
			  {		 alert("PLEASE ENTER VALID PROJECT");			  
			  }		  
		  else if(tcID=="0" || tcID=="")
		  {	 alert("PLEASE ENTER VALID TC");		  			  
		  }		  
		  else if(sector=="0" || sector=="")
		  {	 alert("PLEASE ENTER VALID SECTOR");		  			  
		  }		  
		  else if(trade=="0" || trade=="")
		  {	 alert("PLEASE ENTER VALID  TRADE");	  		  
		  }	  
		  else if(batchStartDate=="0" || batchStartDate=="")
	 		 {		 
			  alert("PLEASE ENTER VALID BATCH START DATE ");
	 		 }	  
	 			 else if(batchFreezeDate=="0" || batchFreezeDate=="")
	  		{	  
				  alert("PLEASE ENTER VALID  BATCH FREEZE DATE");
			  }	  
			  else if(batchSize=="0" || batchSize=="")
			  {		 alert("PLEASE ENTER VALID  BATCH SIZE ");  		  
			  }	  
			  else if(ojtStartDate=="0" || ojtStartDate=="")
			  {		 alert("PLEASE ENTER VALID OJT START DATE ");  		  
			  }	 
		  
			  /* else if(ojtStartDate<batchStartDate)
		  		{		
					  alert("OJT DATE SHOULD AFTER START DATE");			  
		 		 } */
		  
			 /*  else if(batchStartDate<batchFreezeDate)
		  		{		
					  alert("START DATE SHOULD AFTER FREEZE DATE");			  
		 		 } */
		  
			  else if(total!=batch)
		  		{		
					  alert("BATCH SIZE SHOULD BE EQUAL TO TOTAL");			  
		 		 }
		  
			  /* else if(batchEndDate=="0" || batchEndDate=="")
	  		{		
				  alert("PLEASE ENTER VALID BATCH END DATE ");			  
	 		 } 
		  
	 		  else if(batchEndDate<batchFreezeDate)
		  		{		
					  alert("PLEASE ENTER VALID BATCH END DATE ");			  
		 		 }  */
				  else
			  {		
					  
					  var status=window.confirm('Are You Sure You Want to Save ?');
						if(status==true){	  
					  
			   document.forms[0].action="batchCreation.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
						}
				}
			}
	};
  function back(){ 
				document.forms[0].action="batchCreation.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
		};
   
  	function showSector(tcid){ 
 			var xmlHttpRequest = getXMLHttpRequest();
			  xmlHttpRequest.onreadystatechange = function(){ 
					if (xmlHttpRequest.readyState == 4) {
						if (xmlHttpRequest.status == 200) {
							if (xmlHttpRequest.responseText != "") { 
								document.getElementById("sectorId").innerHTML = xmlHttpRequest.responseText ;		
								showstartDate(tcid);
							} 
						} else {
							document.getElementById("sectorId").innerHTML = '<option value=""> --SELECT-- </option>';
				    	} 
					}
				}
			
		xmlHttpRequest.open("POST","batchCreation.do?methodName=showSectorList", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("tcid="+tcid);  
		
  };
  
  
  function showTrade(sector){ 
	  var tcid=$("#tcIDId").val();
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
			
		xmlHttpRequest.open("POST","batchCreation.do?methodName=showTradeList", true);
		xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
		xmlHttpRequest.send("sector="+sector+"&tcid="+tcid);  
		
};
  
  
  function showbatchID(){ 
	  
	 /*  var srno=document.getElementById("srno").value;
 	  if(srno==NaN||srno==""||srno==null||srno==false)
			  {
		  var sr=1;
			  } 
	  else {
		  var sr=1+parseInt(srno);
	  } */
	  
	  var freezedate =$("#batchFreezeDateId").val();
	  var trade=$("#tradeId").val();
	  if(freezedate==null||freezedate==""){
		  $("#tradeId").val('0');
		 alert("please select Freeze date..!");
		  return;
	  }
	  if(trade==null||trade==""){
		  alert("Please select trade..!!"); 
		  return;
	  }
	  var fr=freezedate.replace(/-/g, '');
	  var tcid=document.getElementById("tcIDId").value;
	  var sector=document.getElementById("sectorId").value;
	 // alert(fr);
  	  /* var tcid=document.getElementById("tcIDId").value;
	  var sector=document.getElementById("sectorId").value;
	  var trade=document.getElementById("tradeId").value; */
	  //var id=tcid+sector+trade+sr;		
		//document.getElementById("batchIDId").value=id;
 		showbatch(trade,fr,sector,tcid);
		
		
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
			
		function showbatch(trade,freezedate,sector,tcid){ 
					
					var trade=trade;
					var freezedate=freezedate;
				 
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
					
				xmlHttpRequest.open("POST","batchCreation.do?methodName=showbatchId", true);
				xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
				xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
				xmlHttpRequest.send("sector="+sector+"&"+"trade="+trade+"&"+"tcid="+tcid+"&"+"freezedate="+freezedate);  
				
				};
  
			  function isNumberKey(evt)
				{
				   var charCode = (evt.which) ? evt.which : event.keyCode
				   if (charCode > 31 && (charCode < 48 || charCode > 57))
				      return false;
				   
			
				   return true;
				} ; 
				
				   
					 function showstartDate(tcid){	
						 //alert(projectid);
				  		      var projectid=tcid;  	  			 
						    
						   var xmlHttpRequest = getXMLHttpRequest();
						 //  alert(1);
							xmlHttpRequest.onreadystatechange = function(){ 
					 			if (xmlHttpRequest.readyState == 4) {
									if (xmlHttpRequest.status == 200) {
										if (xmlHttpRequest.responseText != "") { 
											dd_date=xmlHttpRequest.responseText;
											
											if(dd_date!="no"){
											var dd_date1=dd_date.split("-");
											//alert(dd_date);
											var new_dd_date = new Date(dd_date1[2],dd_date1[1]-1,dd_date1[0]);
			 					 		   
		                                       new_dd_date.setDate(new_dd_date.getDate()+10); 
			 					 		  $("#batchFreezeDateId").datepicker('option', { 'minDate':new_dd_date});
			 					 		  
			 					 		    
										} else{
			 					 		 //   alert("aa");	
			 					 		 alert("Date of receipt of request from Q team for Due diligence in empty");
											
			 					 		    }
					  						
										} 
										
									} else {	
										//alert("error---> "+xmlHttpRequest.status);
										//document.getElementById("batchIDId").innerHTML = '<option value=""> --SELECT-- </option>';
							    	} 
								}
							}
						
					xmlHttpRequest.open("POST","batchCreation.do?methodName=showStartDate", true);
					xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
					xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
					xmlHttpRequest.send("projectid="+projectid);
					};   
				
					     function compareDate(){ 	
			 		 			
			 					var date=document.getElementById("batchFreezeDateId").value;
			 					//alert(date);
			 					  var CurrentDate = new Date(date.split("-")[2],date.split("-")[1]-1,date.split("-")[0]);
			 					 CurrentDate.setDate(CurrentDate.getDate()-10); 
			 					// alert(CurrentDate);
			 					$("#batchStartDateId").datepicker({
		                                    dateFormat: 'dd-mm-yy'
		                             }).datepicker('setDate', CurrentDate)
			 					$("#batchStartDateId").datepicker('option', 'minDate', CurrentDate);
			 					$("#batchStartDateId").datepicker('option', 'maxDate', CurrentDate);
			 					$("#ojtStartDateId").datepicker('option', 'minDate', CurrentDate);
			 					
							};      
		     
	/* 	function showCompare(ddDate,startDate){ 	
			   	var ddDate=ddDate;
				var startDate=startDate; 
 			    var day1 = parseInt(startDate.substring(0, 2));
				var month1=parseInt(startDate.substring(3, 5));
				var year1=parseInt(startDate.substring(6, 10)); 			
				var day2 = parseInt(ddDate.substring(8,10));
				var month2=parseInt(ddDate.substring(5,7));
				var year2=parseInt(ddDate.substring(0,4));    
				
			 	if(year2<=year1)
					{			
			 		 
					if(month2<=month1)
					{		 			 
						if(day2<=day1)
							{			
							 
							 var startDate=document.getElementById("batchStartDateId").value;		
							 }
						else
							{
							alert("please enter valid Batch Start Date");
							document.getElementById("batchStartDateId").value="";
							}
						}
					else
					{
					alert("please enter valid Batch Start Date ");
					document.getElementById("batchStartDateId").value="";
					}
					}
			 	else
				{
				alert("please enter valid Batch Start Date");
				document.getElementById("batchStartDateId").value="";
				}			 		
					};	 
					*/
					 /* function validFreezeDate(date){ 	
			 			 var startDate=document.getElementById("batchStartDateId").value;
	 		 			 var FreezeDate=document.getElementById("batchFreezeDateId").value;	
	 		 			var day1 = parseInt(startDate.substring(0, 2));
	 					var month1=parseInt(startDate.substring(3, 5));
	 					var year1=parseInt(startDate.substring(6, 10));	 					
	 					var day2 = parseInt(FreezeDate.substring(0, 2));
	 					var month2=parseInt(FreezeDate.substring(3, 5));
	 					var year2=parseInt(FreezeDate.substring(6, 10));	 		 			 
	 					//alert("in validate freeze- start date-->"+day1+month1+year1+" freeze date-->"+day2+month2+year2);
	 		 			
	 					if(year2>=year1)
						{			
				 		 
						if(month2>=month1)
						{		 			 
							if(day2>=day1)
								{			
								 
								 var FreezeDate=document.getElementById("batchFreezeDateId").value;		
								 }
							else
								{
								alert("please enter valid Freeze Date");
								document.getElementById("batchFreezeDateId").value="";
								}
							}
						else
						{
						alert("please enter valid Freeze Date ");
						document.getElementById("batchFreezeDateId").value="";
						 }
						}	 					
	 					else
	 					{
	 					alert("please enter valid Freeze Date");
	 					document.getElementById("batchFreezeDateId").value="";
	 					}	 					 					  
					};  */	  
					
</script>
<!--Main form section starts here-->
<html:form action="login/batchCreation">	
			
			
		<%-- 	<input type="hidden" value="${srNo}" id="srno"/> --%>
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">BATCH CREATION</td>
               </tr>				
			</table> 
	 
			 
			 <table width="100%" align="center" class="inputTBL">
			 <tr>	
					<th>PROJECT ID<span class="text-error"></span></th>
					<td><html:select property="projectID" styleId="projectIDId"   styleClass="form-control" onchange="javascript:getTCList(this.value);">								 
								<logic:present name="projectlist">
								 <html:option value="">--SELECT--</html:option>
									<logic:iterate id="list" name="projectlist">									
										<html:option value="${list.id }">${list.projectID}</html:option>
									</logic:iterate>
								</logic:present> 
							</html:select></td>						 
				</tr>	  
 
				 	  <tr>	
				       	<th>Training Center ID<span class="text-error"></span></th>
					<td><html:select property="tcID" styleId="tcIDId"   styleClass="form-control" onchange="javascript:showSector(this.value); ">	
					 <html:option value="">--SELECT--</html:option> 
							 
							</html:select></td>					 
				</tr>	
					 
		 
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
				</tr> 
				
				<tr>	
					<th>BATCH ID<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchID" styleId="batchIDId" readonly="true" /> </span></td>
				</tr> 				 
				 
				 	<tr>	
					<th>BATCH DURATION<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchDuration" styleId="batchDurationId" onkeypress="return isNumberKey(event)" /> </span></td>
				<!-- </tr>  
					 
				 <tr> -->	
					<th>BATCH FREEZE DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchFreezeDate"   onchange="compareDate();" styleId="batchFreezeDateId" /> </span></td>					 
				</tr> 
				<tr>
				 	<th>BATCH SIZE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchSize"  styleId="batchSizeId" onchange="javascript:checkData(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>
				 
				 
				<tr> -->	
					<th>BATCH START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchStartDate"    styleId="batchStartDateId" /> </span></td>	<!-- 	onchange="compareDate(this.value);"		 -->	 
				</tr> 
				 <tr>	
					<th>OJT START DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="ojtStartDate" styleId="ojtStartDateId" /> </span></td>
				</tr> 
				 
				 
				<%-- <tr>	
					<th>BATCH END DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchEndDate" styleId="batchEndDateId" /> </span></td>					 
				</tr>  
				
				<tr>	
					<th>BATCH COMMENCED DATE<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="batchCommencedDate" styleId="batchCommencedDateId" /> </span></td>					 
				</tr> --%>
				
				
				<tr>	
					<th>BATCH TYPE<span class="text-error"></span></th>				 	
				    <td><html:select property="batchType" styleId="batchTypeId"   onchange="javascript:checkResi(this.value);" styleClass="form-control">					
 					 <html:option value="0">--SELECT--</html:option>		
 			         <html:option value="RESIDENTIAL">RESIDENTIAL</html:option>					 
 			         <html:option value="NON RESIDENTIAL">NON RESIDENTIAL</html:option>					 
 				     <html:option value="PARTIAL">PARTIAL</html:option>		
					 </html:select></td>				 
				</tr> 
				
				<tr>	
					<th id="res">RESIDENTIAL CAPACITY <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="resiSize"  styleId="resiSizeId"   onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr>
				<!--  <tr> </tr> 	
				  <tr align="right"> <th><td align="right">CATEGORY WISE CANDIDATEDS COMMENCED</td></th></tr> 		
				   <tr> </tr> 	 -->
				   
				<tr> 	
					<th>SC CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Sc"   styleId="commenced_ScId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>				
				<tr> -->	
					<th>ST CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_St"   styleId="commenced_StId" onchange="javascript:checktotal(this.value);"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>	
				
				<tr>  	
					<th>OTHERS CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Others"   onchange="javascript:checktotal(this.value);"   styleId="commenced_OthersId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>			
					<tr> -->	
					<th>TOTAL CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Total"   readonly="true" styleId="commenced_TotalId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
			 	</tr>
				
				<tr>	 
					<th>WOMEN CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Women"   styleId="commenced_WomenId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				<!-- </tr>							
				
				<tr> -->	
					<th>MINORITY CANDIDATEDS COMMENCED <span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Minority"  styleId="commenced_MinorityId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				  </tr>				
				
				<tr>  
					<th>PWD CANDIDATEDS COMMENCED<span class="text-error"></span></th>
				 <td><span class="text-error"> <html:text property="commenced_Pwd"    styleId="commenced_PwdId"  onkeypress="return isNumberKey(event)"  /> </span></td>					 
				</tr> 
					 </table>
			 	
			 <div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert"  class="checkPermissionForFormForInsert"  value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>
	 
</html:form>
