<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<!--script for menu-->
<script type="text/javascript">
 
 
$(function() {	
    //showRemark("0");
	    $("#dateofIssueId").datepicker({ dateFormat: 'dd-mm-yy' });
	    $("#lastDateOfAppealId").datepicker({ dateFormat: 'dd-mm-yy' });
	    $("#actualDateOfAppealId").datepicker({ dateFormat: 'dd-mm-yy' }); 
	    $("#lastDateOfAppealDisposalId").datepicker({ dateFormat: 'dd-mm-yy' });
	    $("#actualDateOfAppealDisposalId").datepicker({ dateFormat: 'dd-mm-yy' }); 
	    
		$("#dateofIssueId").datepicker('option', { maxDate:new Date()});
		$("#lastDateOfAppealId").datepicker('option', { maxDate:new Date()});
		$("#actualDateOfAppealId").datepicker('option', { maxDate:new Date()}); 
		$("#lastDateOfAppealDisposalId").datepicker('option', { maxDate:new Date()});
		$("#actualDateOfAppealDisposalId").datepicker('option', { maxDate:new Date()});
		 

});
 
 
		function save(){
			var x=checkPermissionForFormForInsert();
			if(x=='true'){	
			  var projectID=document.getElementById("projectIDId").value;
			  var typeOfPenalty=document.getElementById("typeOfPenaltyId").value;
			  var reasonForPenalty=document.getElementById("reasonForPenaltyId").value;
			  var status=document.getElementById("statusId").value;
			  var dateofIssue=document.getElementById("dateofIssueId").value;
			  var lastDateOfAppeal=document.getElementById("lastDateOfAppealId").value;
			  var actualDateOfAppeal=document.getElementById("actualDateOfAppealId").value;
			  var lastDateOfAppealDisposal=document.getElementById("lastDateOfAppealDisposalId").value;
			  var actualDateOfAppealDisposal=document.getElementById("actualDateOfAppealDisposalId").value;
			  var appealDisposalResult=document.getElementById("appealDisposalResultId").value;
			 	
	  
			  if(projectID=="0" || projectID=="")
				  {		 alert("please enter valid projectID");			  
				  }		  
			 else if(typeOfPenalty=="0" || typeOfPenalty=="")
			 {	 alert("please enter valid   Type Of Penalties");		  			  
			 }		  
			 else if(reasonForPenalty=="0" || reasonForPenalty==""|| reasonForPenalty.length<1|| reasonForPenalty==false)
			 {	 alert("please enter valid  Reason For Penalty");		  			  
			 }		  
			 else if(status=="0" || status=="")
			 {	 alert("please enter valid  status");	  		  
			 }	  
			 else if(dateofIssue=="0" || dateofIssue=="")
			 {		 
			  alert("please enter valid Date of Issue Of Penalty Notice ");
			 }			 
				 else if(lastDateOfAppeal=="0" || lastDateOfAppeal=="")
				 {		 
				  alert("please enter valid Permitted Last Date Of Appeal ");
				 } 
				 else   if(actualDateOfAppeal.length>0 &&(lastDateOfAppealDisposal=="0" || lastDateOfAppealDisposal==""))
				  {	 
		 		    alert("PLEASE ENTER VALID  Permitted Last Date Of Appeal Disposal  ");					   				   				  
				 }	
				 else   if(lastDateOfAppealDisposal.length>0 &&(actualDateOfAppeal=="0" || actualDateOfAppeal==""))
				  {	
		 		    alert("PLEASE ENTER VALID  Actual Date Of Appeal ");					   				   				  
				 }	
				 else   if(actualDateOfAppealDisposal.length>0 &&(appealDisposalResult=="0" || appealDisposalResult==""))
				  {		 
		 		    alert("PLEASE ENTER VALID  Appeal Disposal Result  ");					   				   				  
				 }	
				 else   if(appealDisposalResult.length>0 &&(actualDateOfAppealDisposal=="0" || actualDateOfAppealDisposal==""))
				  {		 
		 		    alert("PLEASE ENTER VALID   Actual Date Of Appeal Disposal  ");					   				   				  
				 }			  			
						else
							{
							document.forms[0].action="projectPenalty.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
							document.forms[0].submit();
							}
			  			}
					};
  
function back(){
	
	var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="projectPenalty.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
  
</script>
 <html:form action="login/projectPenalty">

	
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">PROJECT PENALTIES</td>					
               </tr>				
			</table>
			 	 <table width="100%" align="center" class="inputTBL">
				   <tr><th> Project-ID<span class="text-error"></span> </th>
						<td><html:select property="projectID" styleId="projectIDId" styleClass="form-control" >
								<html:option value="0">-select-</html:option>
								<logic:present name="projectlist">
									<logic:iterate id="list" name="projectlist">
										<html:option value="${list.id }">${list.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr> 
					
					<tr>	
					<th>Type Of Penalties<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="typeOfPenalty" styleId="typeOfPenaltyId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Minor">Minor</html:option>
					<html:option value="Major">Major</html:option>
  					</html:select> </span></td>
				</tr> 
				<tr>	
					<th> Reason For Penalty <span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="reasonForPenalty" styleId="reasonForPenaltyId"  /> </span></td>
				</tr>
				
					<tr>	
					<th> Status<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="status" styleId="statusId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Active">Active</html:option>
					<html:option value="Closed">Closed</html:option>
  					</html:select> </span></td>
				</tr> 
				
				<tr>	
					<th> Date of Issue Of Penalty Notice <span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="dateofIssue" styleId="dateofIssueId"  /> </span></td>
				</tr>
				  
				<tr>	
					<th>Permitted Last Date Of Appeal<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="lastDateOfAppeal" styleId="lastDateOfAppealId"  /> </span></td>
				</tr>
				 
				<tr>	
					<th>Actual Date Of Appeal <span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="actualDateOfAppeal" styleId="actualDateOfAppealId"  /> </span></td>
				</tr>
				<tr>	
					<th>Permitted Last Date Of Appeal Disposal<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="lastDateOfAppealDisposal" styleId="lastDateOfAppealDisposalId"  /> </span></td>
				</tr>
				<tr>	
					<th>Actual Date Of Appeal Disposal<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="actualDateOfAppealDisposal" styleId="actualDateOfAppealDisposalId"  /> </span></td>
				</tr>
				
				<tr>	
					<th>Appeal Disposal Result<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="appealDisposalResult" styleId="appealDisposalResultId" >
					<html:option value="">- Select -</html:option> 
					<html:option value="Active">Active</html:option>
					<html:option value="Closed">Closed</html:option>
  					</html:select> </span></td>
				</tr>	 
				
			</table> 
			 
				<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/> 
			</div>	
				
			 
			 
			
	
</html:form>
 