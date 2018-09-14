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

$(function() {	
	    //showRemark("0");
	    $("#dateOfIssueId").datepicker({ dateFormat: 'dd-mm-yy' });
	    $("#dateOfReplyFromPiaId").datepicker({ dateFormat: 'dd-mm-yy' });
	    $("#communicationToPiadateId").datepicker({ dateFormat: 'dd-mm-yy' }); 	    
		$("#dateOfIssueId").datepicker('option', { maxDate:new Date()});
		$("#dateOfReplyFromPiaId").datepicker('option', { maxDate:new Date()});
		$("#communicationToPiadateId").datepicker('option', { maxDate:new Date()}); 
  
});

function showRemark(str){
	if (str=="0")
	{  
		document.getElementById("showProjects").style.display = "";	
 		document.getElementById("showTable").style.display = "none";
	} 
	else
		{
		document.getElementById("showTable").style.display = "";	
		}
	};

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
 
  
  	    function showSanctionDate(projectid,dateOFIssue){	
		 
  		      var projectid=projectid;  
  			  var dateOFIssue=dateOFIssue;   
		    
		   var xmlHttpRequest = getXMLHttpRequest();
		  xmlHttpRequest.onreadystatechange = function(){  			  
				if (xmlHttpRequest.readyState == 4) {
					if (xmlHttpRequest.status == 200) {
						if (xmlHttpRequest.responseText != "") { 		
							
							var sanction=xmlHttpRequest.responseText;							 
							var issue=dateOFIssue;
 							 
							 var startDate=document.getElementById("dateOfIssueId").value;	
					 			var  issue_date = issue.split('-');
					 		    var start_date = startDate.split('-');

					 		    var new_start_date = new Date(start_date[2],start_date[1]-1,start_date[0]);
					 		    var new_issue_date = new Date(issue_date[2],issue_date[1]-1,issue_date[0]);
					 		    
					 		  if(new_issue_date < new_start_date) {
					 	    	alert("issue date cannot be earlier than Date of Sanction");
					 			document.getElementById("dateOfIssueId").style.border="1px solid red";
					 			document.getElementById("dateOfIssueId").focus(); 
					 			return;
								
						  	} 
					  	} 
						else 
						{ 
							 
				    	} 
					} 
				}
			}
		
	xmlHttpRequest.open("POST","projectAlerts.do?methodName=showSanctionDate", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectid="+projectid);
	};   
	 
	     function compareDate(date){ 	    	 
	 			 var projectid=document.getElementById("projectIDId").value;	 			  
	 			 var dateOfIssue=document.getElementById("dateOfIssueId").value;	 			 
				 showSanctionDate(projectid,dateOfIssue);					  
			};      
	     
	function showCompare(sanction,issue){ 
		    var day1 = parseInt(issue.substring(0, 2));
			var month1=parseInt(issue.substring(3, 5));
			var year1=parseInt(issue.substring(6, 10)); 			
			var day2 = parseInt(sanction.substring(8,10));
			var month2=parseInt(sanction.substring(5,7));
			var year2=parseInt(sanction.substring(0,4));    
			
		 	if(year2<=year1)
				{				 			
				if(month2<=month1)
				{					 
					if(day2<=day1)
						{			
						document.getElementById("dateOfIssueId").value=issue;						 
						}
					else
						{
						alert("please enter valid date of issue");
						document.getElementById("dateOfIssueId").value="";
						}
					}
				else
				{
				alert("please enter valid date of issue");
				document.getElementById("dateOfIssueId").value="";
				}
				}
		 	else
			{
			alert("please enter valid date of issue");
			document.getElementById("dateOfIssueId").value="";
			}			 		
				};	 
	
	function save() {			  		 
		var x=checkPermissionForFormForInsert();
		if(x=='true'){			
   		  var projectID=document.getElementById("projectIDId").value;
 		  var typeOfAlert=document.getElementById("typeOfAlertId").value;
 		  var dateOfIssue=document.getElementById("dateOfIssueId").value;
 		  var reasoncategory=document.getElementById("reasoncategoryId").value;
 		  var reasonForIssue=document.getElementById("reasonForIssueId").value;
 		  var issuingAgency=document.getElementById("issuingAgencyId").value;
 		  var dateOfReplyFromPia=document.getElementById("dateOfReplyFromPiaId").value;
 		  var replyFromPia=document.getElementById("replyFromPiaId").value;
 		  var status=document.getElementById("statusId").value;
 		  var communicationToPiadate=document.getElementById("communicationToPiadateId").value;
 		  var remarks=document.getElementById("remarksId").value; 	
 		 
  		 
		   if(projectID=="0" || projectID=="")
			  {		 alert("PLEASE ENTER VALID PROJECT");			  
			  }		  
		  else if(typeOfAlert=="0" || typeOfAlert=="")
		  {	 alert("PLEASE ENTER VALID TYPE OF ALERT");		  			  
		  }		  
		  else if(dateOfIssue=="0" || dateOfIssue=="")
		  {	 alert("PLEASE ENTER VALID DATE OF ISSUE");		  			  
		  }		  
		  else if(reasoncategory=="0" || reasoncategory=="")
		  {	 alert("PLEASE ENTER VALID  CATEGORY OF REASON");	  		  
		  }	  
		  else if(reasonForIssue=="0" || reasonForIssue==""|| reasonForIssue.length<1)
	 		 {		 
			  alert("PLEASE ENTER VALID REASON FOR ISSUE ");
	 		 }	  
	 	  else if(issuingAgency=="0" || issuingAgency=="")
	  		{	  
				  alert("PLEASE ENTER VALID ISSUING AGENCY");
			  }	 
 		     
	 	   else   if(dateOfReplyFromPia.length>0 &&(replyFromPia=="0" || replyFromPia==""))
			  {		
	 		    alert("PLEASE ENTER VALID  REPLY FROM PIA ");					   				   				  
			 }	
		   
		 	  else   if(replyFromPia.length>0 &&(dateOfReplyFromPia=="0" || dateOfReplyFromPia=="") )
			  {		
		 	   alert("PLEASE ENTER VALID DATE OF REPLY FROM PIA ");				    
			  }
			  else   if(communicationToPiadate.length>0 && (remarks=="0" || remarks==""))
			  {	 
				  alert("PLEASE ENTER VALID REMARKS")				   	  
			  }			   
			  else  if(remarks.length>0&&(communicationToPiadate=="0" || communicationToPiadate==""))
			  {	 
				  alert("PLEASE ENTER VALID COMMUNICATION TO PIA DATE");
				     
			 	 }    		
		 	  
		 else
		  {	  
 		   document.forms[0].action="projectAlerts.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
		   document.forms[0].submit();			   
		  }	
		 }
		}; 
		  function back(){ 
				document.forms[0].action="projectAlerts.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
		  };
	 	
	 	
</script>

 <html:form action="login/projectAlerts"> 
 		
  		
	<table width="100%" class="pageHeaderTable" >
		<tr>
			<td align="center" class="pageHeader">PROJECT ALERTS</td>					
        </tr>				
	</table>
			<table width="100%" align="center" class="inputTBL">
				<tr><th> Project-ID<span class="text-error"></span> </th>
						<td><html:select property="projectID" styleId="projectIDId" onchange="javascript:showRemark(this.value);" styleClass="form-control" >
								<html:option value="0">-select-</html:option>
								<logic:present name="projectlist">
									<logic:iterate id="list" name="projectlist">
										<html:option value="${list.id }">${list.projectID}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr> 
					 
					
					<tr>	
					<th>Type Of Alert/SCN<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="typeOfAlert" styleId="typeOfAlertId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Yellow">Yellow </html:option>
					<html:option value="RED">RED</html:option>
					<html:option value="SCN">SCN</html:option>					
 					</html:select> </span></td>
				</tr> 
				<tr>	
					<th>Date Of Issue Of Alert/SCN  <span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="dateOfIssue" onchange="javascript:compareDate(this.value);" styleId="dateOfIssueId" /> </span></td>
				</tr>
					<tr>	
					<th>category Of Reason<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="reasoncategory" styleId="reasoncategoryId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="PPWS">PPWS </html:option>
					<html:option value="TC Inspection">TC Inspection</html:option>
					<html:option value="Financials">Financials</html:option>	
				    <html:option value="Others">Others</html:option>								
 					</html:select> </span></td>
				</tr>  
				
				<tr>	
					<th>Reason For Issuing Alert/Show Cause <span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="reasonForIssue" styleId="reasonForIssueId"/> </span></td>
				</tr>
				
				
					<tr>	
					<th>Issuing Agency <span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="issuingAgency" styleId="issuingAgencyId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="CTSA">CTSA </html:option>
					<html:option value="SRLM">SRLM</html:option>
  					</html:select> </span></td>
				</tr> 
			  
			  
			  
				<tr>	
					<th>Date Of Reply From PIA<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="dateOfReplyFromPia" styleId="dateOfReplyFromPiaId"  /> </span></td>
				</tr>
				
				 <tr>	
					<th>Date of Communication to PIA<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="communicationToPiadate" styleId="communicationToPiadateId"  /> </span></td>
				</tr>
				
				<tr>	
					<th>Reply From PIA <span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="replyFromPia" styleId="replyFromPiaId"  /> </span></td>
				</tr> 
				
				
				<tr>	
					<th>Status <span class="text-error"></span></th>
 			    	<td><span class="text-error"> <html:select property="status" styleId="statusId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Query-Raised">Query-Raised</html:option>
					<html:option value="Replied">Replied</html:option>
				    <html:option value="Closed">Closed</html:option>
					
  					</html:select> </span></td></tr>
				 
				<tr>	
					<th>Remarks<span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="remarks"   styleId="remarksId"  /> </span></td>
				</tr>	
			
			</table>
				<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" value="<bean:message  key="button.save"/>" onclick="save()"/> 
			    <input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/>   
			</div>
			 	
			
	
</html:form>
 