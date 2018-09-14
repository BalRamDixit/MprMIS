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

//window.onbeforeunload=confirmExit;
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
function clearField()
{
	var status=window.confirm("<bean:message key="msg.clearForm"/>");
	if(status==true){
	document.forms[0].action = "monitorFirstInstallment.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
	document.forms[0].submit();
	}
	
}
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

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
	var status=window.confirm('ARE YOU SURE YOU WANT TO SAVE ?');
	if(status==true){
		document.forms[0].action="monitorFirstInstallment.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	 }
    }
   }


function back(){
	
	var status=window.confirm('ARE YOU SURE YOU WANT to go back ?');
	if(status==true){
		document.forms[0].action="monitorFirstInstallment.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
	}
}

function checkAmount(vv,idForCheck){
	var abc=document.getElementById(idForCheck).value;
	var val1=$(vv).val();
     var tfp=parseInt(abc * .25);
     var ss=val1.replace(/,/g , "");
     
      if(parseInt(ss)>parseInt(tfp)){
	       alert("Value should be Less than 25% of the Project Cost ");
	       $(vv).val("");
       }
     
}


    
  function checkDate(vv){
	 var abc=$(vv).next('input').val();
	 /*   alert(abc+" ---  "+vv.value); */
       //var val1=$(vv).val();
    var  job_start_date = abc.split('-');
    var job_end_date = vv.value.split('-');

    var new_start_date = new Date(job_start_date[0],job_start_date[1]-1,job_start_date[2]);
  
    var new_end_date = new Date(job_end_date[2],job_end_date[1]-1,job_end_date[0]);


    if(new_end_date < new_start_date) {
    	vv.value="";
      alert("not less than sanction date");
     
    }
   } 
 

 
 
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

</script>

<!--Main form section starts here-->
<html:form action="login/monitorFirstInstallment">

	 
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">First Installment</td>					
               </tr>
             </table>
             <table width="100%">
				<tr>
	               	<td>
	               		<div class="tab">
		 				 <a onclick="first()" class="tablinks active">First Installment</a>
		 				 <a onclick="second()"class="tablinks">Second Installment</a>
		 				 <a onclick="third()" class="tablinks">Third Installment</a>
		 				 <a onclick="fourth()"class="tablinks" >Fourth Installment</a>
						</div>
	               	</td>
               </tr>
               </table>
		    <div style="overflow-x:scroll; overflow-y:hidden;">	
			<table  width="100%" align="center" class="inputTBL"  id="tableId" >			
			 <tr>
			     <th align="center" >Sr. No.</th>
                 <th align="center" >Project ID</th> 
                 <th align="center">Amount Released</th>
                 <th align="center" >Date of Release</th>
                 <th align="center">Utilization in %<span class="text-error">*</span></th>
                 <th align="center">Remarks</th>
             </tr> 
              
              
              <logic:iterate id="mm" name="mm" scope="request" indexId="index">
                   <tr>                
                    <td>${index+1 }</td> 
                    <td><input type="hidden" name="projectId" value="${mm.projectId.id}"/><input type="text" readonly="readonly"    value="${mm.projectId.projectID}"/><html:hidden property="id" value='${mm.id}'></html:hidden></td>      
					<td><html:text property="relAmount" styleClass="amount input" onblur="checkAmount(this,'hiddenAmount${mm.projectId.id}')" styleId="Project${mm.projectId.id}" value='${mm.relAmount}'/><input type="hidden" id="hiddenAmount${mm.projectId.id}" value="${mm.relAmountHidden}"/></td>
                    <td> <html:text styleClass="datepicker input" readonly="true" property="relDate" onchange="checkDate(this)" value='${mm.relDate}' /><input type='hidden' value="${mm.reldateHidden}"></td>
                    <td><html:text property="utilizationPercent" onkeypress="return isNumberKey(event)" onkeyup="checkUtilizationPercent(this);" styleClass="input" value='${mm.utilizationPercent}'/></td> 
                    <td><html:textarea rows="1" cols="2" property="remarks" value='${mm.remarks}' /> </td> 
                    
                    </tr>                
              </logic:iterate>
   
              </table>
              
             </div >
             
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
 			</div>	
</html:form>
</body>

<!--Main form section ends here-->