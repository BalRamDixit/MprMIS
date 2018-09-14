<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<script type="text/javascript">
/*  
function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.accessForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;;
	document.accessForm.submit();
	}
}



  */
  function backform(){
	  var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}  
  }
 
function updateform(){
	alert("save");
	 var status=window.confirm('Are You Sure You Want to update ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		} 
	}


  function isNumberKey(evt)
  {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

     return true;
  }

</script>
<!--Main form section starts here-->
<html:form action="login/ppwsSetup" styleId="ppwsForm">
<%-- <jsp:useBean id="ppwsdetail" scope="request" class="com.infotech.sgsy.ppws.PpwsSetupActionVO"/> --%>
	 
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">PPWS Setup</td>					
               </tr>
				<tr>
					<td style="padding-left: 7%;" ><span class="text-error">*</span>
					<span class="normaltext"><bean:message  key="lable.field.manadatory" /></span>
					</td>
				</tr>
			</table> 
			<tr>	</tr>			
			
		<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>${name}</th>
					<td><b>${nameValue}</b></td>
				</tr>
				</table>
		<br/>
			 	
			<br/>
		 
				
				<table width="100%" align="center" class="inputTBL">
			 
			 	 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td>	<html:text property="projectId" styleId="projectIdId" readonly="true"  styleClass="form-control" value="${projectid}" />
								</td>
						   </tr>
			 </table>
				
				<br/>
				<br/>
				
				<div id="showtable">
				<table width='100%' class='inputTBL'> <tr ><th>Entry-Month <span class='text-error'></span></th><th>Entry-Year<span class='text-error'></span></th>
				    <th>Expected Training Commencement<span class='text-error'></span></th><th>Expected Training Completion<span class='text-error'></span></th>
				    		<th>Expected Placement<span class='text-error'></span></th> </tr>
				
				
				   <logic:iterate name="ppwsdetail" id="ppwsdetailid">
                   <tr>   
	                    <td><span class='text-error'><input type='text' name='entryMonth' value='${ppwsdetailid.entryMonth}' onkeypress="return isNumberKey(event)" readonly/><input type='hidden' name='id' value='${ppwsdetailid.id}'/></span></td> 
	                    <td><span class='text-error'><input type='text' name='entryYear' value='${ppwsdetailid.entryYear}'  onkeypress="return isNumberKey(event)" readonly/></span></td>
	                    <th><span class='text-error'><input type='text' name='expTrainComn' value='${ppwsdetailid.expTrainComn}' onkeypress="return isNumberKey(event)"/> </span></th>
	                    <th><span class='text-error'><input type='text' name='expTrainComp'  value='${ppwsdetailid.expTrainComp}'  onkeypress="return isNumberKey(event)"/> </span></th>
	                    <th><span class='text-error'><input type='text' name='placeExp'   value='${ppwsdetailid.placeExp}' onkeypress="return isNumberKey(event)"/> </span></th>
	                    
	                  
                  </tr>
                              </logic:iterate>
				
				</table>
				<div align='center'><input name='Button' type='button' class='button' id='update' value='update' onclick='javascript:updateform()'/></div>
				<div align='center'><input name='Button' type='button' class='button' id='back' value='back' onclick='javascript:backform()'/></div>
				</div>
				
				
 	
</html:form>
 