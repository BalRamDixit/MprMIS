<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
div.tab {overflow: hidden; border: 1px solid #ccc; background-color: #f1f1f1;}
div.tab a {float:left;border:none;outline:none;cursor:pointer;padding:6px 1%;transition:0.3s;font-size:13px;width:22%;margin-left:1%;color:#51398D;}
div.tab a:hover {background-color: lightblue;}
div.tab a.active {background-color: #ccc;}


.input ,.maskedAmount{
width: 88px!important; }
</style>

<script type="text/javascript">
$(function() {	
    $(".datepicker").datepicker({ dateFormat: 'dd-mm-yy' });
});
$(document).ready(function () {
$(".datepicker").each(function(){
	var dd=$(this).val();
	if(dd!=null && dd!=""){
		var date2 = new Date(dd);
		$(this).datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
		}
});
});
function confirmExit(){		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 
function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('ARE YOU SURE YOU WANT TO SAVE ?');
	if(status==true){
		document.forms[0].action="monitorFirstInstallment.do?methodName=save4"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 }
    }
   }

/* function clearField()
{
	var status=window.confirm("<bean:message key="msg.clearForm"/>");
	if(status==true){
	document.forms[0].action = "monitorFirstInstallment.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
	}
	
} */
  
    function first()
    {
      
    document.forms[0].action = "monitorFirstInstallment.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
    document.forms[0].submit();
    }
    function second()
        {
    	  
    	document.forms[0].action = "monitorFirstInstallment.do?methodName=show2"+"&"+tokenParameter+"="+tokenValue;
    	document.forms[0].submit();
    	}
    	
    function third()
    {
      
    document.forms[0].action = "monitorFirstInstallment.do?methodName=show3"+"&"+tokenParameter+"="+tokenValue;
    document.forms[0].submit();
    }

    function fourth()
    {
      
    document.forms[0].action = "monitorFirstInstallment.do?methodName=show4"+"&"+tokenParameter+"="+tokenValue;
    document.forms[0].submit();
    }

function checkAllDates(idx) { 
	 
	var dateOfIssuance= document.getElementById("d1"+idx).value;
	//alert(dI1);
 	//alert(dateOfIssuance);  
	var  dateOfIssuance1 = dateOfIssuance.split('-');
	/* alert(dateOfIssuance1); */
    var newdateOfIssuance1 = new Date(dateOfIssuance1[2],dateOfIssuance1[1]-1,dateOfIssuance1[0]);
    /*  alert(newdateOfIssuance1);  */
    
	var dateOfReccomend = document.getElementById("d2"+idx).value;
	//alert(dR2);
	/* alert(dateOfReccomend); */
	var  dateOfReccomend1 = dateOfReccomend.split('-');
	/* alert(dateOfReccomend1); */
	var newdateOfReccomend1 = new Date(dateOfReccomend1[2],dateOfReccomend1[1]-1,dateOfReccomend1[0]);
	/* alert(newdateOfReccomend1); */
	
	var dateOfPIA= document.getElementById("d3"+idx).value;	  
	var  dateOfPIA1= dateOfPIA.split('-');
	var newdateOfPIA1 = new Date(dateOfPIA1[2],dateOfPIA1[1]-1,dateOfPIA1[0]);
	
	var dateOfRelease= document.getElementById("d4"+idx).value; 
	var  dateOfRelease1= dateOfRelease.split('-');
	var newdateOfRelease1 = new Date(dateOfRelease1[2],dateOfRelease1[1]-1,dateOfRelease1[0]);
	
	
	if(newdateOfIssuance1!="" && newdateOfReccomend1!="" && newdateOfIssuance1 >= newdateOfReccomend1){
		alert("Date of Recommendation must be greater than Date of Issuance");
  		document.getElementById("d2"+idx).value="";  
  	}
	else if(newdateOfReccomend1!="" && newdateOfPIA1!="" && newdateOfReccomend1 >= newdateOfPIA1){
		alert("Date of PIA must be greater than Date of Recommend"); 
		document.getElementById("d3"+idx).value="";	
  	}
	else if(newdateOfPIA1!="" && newdateOfRelease1!="" && newdateOfPIA1 >= newdateOfRelease1) {
		alert("Date of DateOfRelease must be greater than DateOfPIA");
 		document.getElementById("d4"+idx).value="";	
	}

};
    /* ######################   */
    
     function checkAmount(vv,idForCheck){
	 var abc=document.getElementById(idForCheck).value;
     var val1=$("#"+vv).val();
     var tfp=parseInt(abc) * .10;
     var ss=val1.replace(/,/g , "");
    // alert("----"+ss+"-----------"+tfp); 
      if(parseInt(ss)>parseInt(tfp)){
	       alert("Value should be Less than 10% of the Project Cost");
	       $("#"+vv).val("0");
	       $("#maskedAmount"+vv).autoNumeric('set',"0");
       }
      else
      { 	}
    };

    function checkAmountReleased(vv,id){
	var abc=document.getElementById(id).value;
	var amtClaimed=parseInt(abc);
	var val1=$("#"+vv).val();
    
    var amtReleased=parseInt(val1);
    var ss=val1.replace(/,/g , "");
    /* alert(ss+" >  "+amtClaimed); */
      if(parseInt(ss)>parseInt(amtClaimed)){
       alert("Cannot be more than Claim ");
       $("#"+vv).val("0"); 
       $("#maskedAmount"+vv).autoNumeric('set',"0");
       }
      else
      { 	}
    }; 
    
 	    
    function isNumberKey(evt)
	{
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	      return false;
	   

	   return true;
	};
	
	function checkUtilizationPercent(vv){
		var abc=$(vv).val();
		  if(abc<0 || abc>100){
		  alert("Value should be between 0 - 100");
		   vv.value="";
		  }
		}
	
	/* 
	function Validate(){
		var dateOfIssuance= document.getElementById("d1"+idx).value;	
		var dateOfReccomend = document.getElementById("d2"+idx).value;
		var dateOfPIA= document.getElementById("d3"+idx).value;	
		var dateOfRelease= document.getElementById("d4"+idx).value;
		if(dateOfIssuance=="" && dateOfReccomend=="" && dateOfPIA=="" && dateOfRelease=="")
		{
			alert("Please fill all the details");
		}
	} */

</script>
<!--Main form section starts here-->
<html:form action="login/monitorFirstInstallment">

	 
			<table width="100%" class="pageHeaderTable">
			<tr>
					<td align="center" class="pageHeader">Fourth Installment</td>					
                </tr>
                </table>
                <table width="100%" >
			     <tr>
	               	<td>
	               		<div class="tab">
		 				 <a onclick="first()"class="tablinks">First Installment</a>
		 				 <a onclick="second()" class="tablinks">Second Installment</a>
		 				 <a onclick="third()"class="tablinks">Third Installment</a>
		 				 <a onclick="fourth()"class="tablinks active" >Fourth Installment</a>
						</div>
	               	</td>
               </tr>
			</table> 
			
			<div id="show">			
			<div style="overflow-x:scroll;overflow-y:hidden;">
			
			 <table width="100%"  class="inputTBL"  id="tableId" >			
			 <tr>
			     <th align="center" >SNo.<span class="text-error"></span></th>
                 <th align="center">Project ID<span class="text-error"></span></th>
                 <th align="center">Date Issuance of E7<span class="text-error">*</span></th>
                 <th align="center">Date recommendation of Installment<span class="text-error">*</span></th>
                 <th align="center">Date of Receipt of PIA Claim<span class="text-error">*</span></th>
                 <th align="center">Amount Claim<span class="text-error">*</span></th>
                 <th align="center">Amount Released<span class="text-error">*</span></th>
                 <th align="center">Date of Release<span class="text-error">*</span></th>
                 <th align="center">Status of Claim<span class="text-error">*</span></th>
                 <th align="center">Utilization in %<span class="text-error">*</span></th>
                 <th align="center">Remarks&nbsp;<span class="text-error">(if any)</span></th>
             </tr> 
			<logic:iterate id="mm" name="mm" indexId="index" scope="request">
			
				<tr>                               
					<td align="center">${index+1}<span class="text-error"></span></td> 
					<td><input type="hidden" name="projectId" value="${mm.projectId.id}"/><input type="text" name=""  readonly="true" value="${mm.projectId.projectID}"/><html:hidden property="id" value='${mm.id}'></html:hidden></td>      
					<td align="center"><span class="text-error"><html:text  readonly="true" property="dateofIssuance"  onchange="checkAllDates('${index}')" styleId="d1${index}" styleClass="datepicker input" value='${mm.dateofIssuance}' />  </span></td> 
					<td align="center"><span class="text-error"><html:text  readonly="true" property="dateofRecommend" onchange="checkAllDates('${index}')" styleId="d2${index}" styleClass="datepicker input" value='${mm.dateofRecommend}' />  </span></td> 
					<td align="center"><span class="text-error"><html:text  readonly="true" property="dateofReceipt"   onchange="checkAllDates('${index}')" styleId="d3${index}" styleClass="datepicker input" value='${mm.dateofReceipt}' />  </span></td> 
					
					<td align="center"><span class="text-error"><html:text styleClass="amount" property="amountClaimed" onblur="checkAmount('Project${mm.projectId.id}','hiddenAmount${mm.projectId.id}')" styleId="Project${mm.projectId.id}" value="${mm.amountClaimed}" /> <input type="hidden" id="hiddenAmount${mm.projectId.id}" value="${mm.projectId.totalProjectCost}"/> </span></td> 
					<td align="center"><span class="text-error"><html:text styleClass="amount" property="relAmount"  onblur="checkAmountReleased('ProjectRelAmount${mm.projectId.id}','Project${mm.projectId.id}')" styleId="ProjectRelAmount${mm.projectId.id}" value="${mm.relAmount}"/><input type="hidden" value="${mm.relAmountHidden}"/></span></td>
					
					
					<td align="center"><span class="text-error"><html:text styleClass="datepicker input" readonly="true" onchange="checkAllDates('${index}')" styleId="d4${index}" property="relDate"  value='${mm.relDate }' /><input type='hidden' value="${mm.reldateHidden}"></span></td>
					<td align="center"><span class="text-error"><html:select styleClass="input" property="statusofClaim" value='${mm.statusofClaim}' > 
					<html:option value="">Select</html:option>
					<html:option value="PIA not eligible">PIA not eligible</html:option>
					<html:option value="Not Applied">Not Applied</html:option>
					<html:option value="Under Going Verification">Under Going Verification</html:option>
					<html:option value="Pending at FD">Pending at FD</html:option>
					<html:option value="Fund Released">Fund Released</html:option>
					</html:select> </span></td>
					<td align="center"><span class="text-error"><html:text styleClass="input" onkeypress="return isNumberKey(event)" onkeyup="checkUtilizationPercent(this);" property="utilizationPercent" value='${mm.utilizationPercent}' />  </span></td> 
					<td align="center"><span class="text-error"><html:textarea rows="1" cols="2" property="remarks" value='${mm.remarks}' />  </span></td> 
				</tr>                
			</logic:iterate>
				 </table>
			</div>
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
				<!-- <input name="Button" type="button" class="button" value="Back" onclick="back()"/>  -->
			</div>	
			
			</div>
		
</html:form>
</body>
<!--Main form section ends here-->