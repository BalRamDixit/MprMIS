<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<script type="text/javascript">
 
$(function() { 
	
	 
	var status=$("#statusId").val();	
	
 	if(status=="Closed")
		{
 		document.getElementById("myUpdate").style.display="none";		 
		};
	 
	 var dateOfIssue=$("#dateOfIssueId").val();		  
	 var dateOfReplyFromPia=$("#dateOfReplyFromPiaId").val();
	 var communicationToPiadate=$("#communicationToPiadateId").val();	  
	 

	  if(dateOfIssue!=null && dateOfIssue!=""){
		var date2 = new Date(dateOfIssue);
		 
		$("#dateOfIssueId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	  };
	  
	/*   
	  if(batchStartDate!=null && batchStartDate!=""){
			var date2 = new Date(batchStartDate);
			$("#batchStartDateId").datepicker({
		        dateFormat: 'dd-mm-yy'
		    }).datepicker('setDate', date2)
		  }; */ 
	  
	  
	     if(dateOfReplyFromPia!=null && dateOfReplyFromPia!=""){
		var date2 = new Date(dateOfReplyFromPia);
		$("#dateOfReplyFromPiaId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	     };
	    
	       if(communicationToPiadate!=null && communicationToPiadate!=""){
		var date2 = new Date(communicationToPiadate);
		$("#communicationToPiadateId").datepicker({
	        dateFormat: 'dd-mm-yy'
	    }).datepicker('setDate', date2)
	    	    
	       };
	    
	   
	        $("#dateOfIssueId").datepicker({ dateFormat: 'dd-mm-yy' });
		    $("#dateOfReplyFromPiaId").datepicker({ dateFormat: 'dd-mm-yy' });
		    $("#communicationToPiadateId").datepicker({ dateFormat: 'dd-mm-yy' }); 	    
			$("#dateOfIssueId").datepicker('option', { maxDate:new Date()});
			$("#dateOfReplyFromPiaId").datepicker('option', { maxDate:new Date()});
			$("#communicationToPiadateId").datepicker('option', { maxDate:new Date()}); 
	  
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
							showCompare(sanction,issue);   
							 
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
 
 
 
 
function Update(){ 
	
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		
	  var id=document.getElementById("alerthide").value;
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
		  
			document.forms[0].action="projectAlerts.do?methodName=updateAlert&id="+id+"&"+tokenParameter+"="+tokenValue;
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
<jsp:useBean id="alertDetail" class="com.infotech.sgsy.monitoringalerts.MonitoringAlertVO" scope="request"/>		

	
			
	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">PROJECT ALERTS</td>					
        </tr>				
	</table>
             <input type="hidden" value="${srNo}" id="srno"/>            
			 
			<table width="100%" align="center" class="inputTBL"> 
		        <tr><th> Project-ID<span class="text-error"></span> </th>
						<td><html:text property="projectID" styleId="projectIDId"  styleClass="form-control" readonly="true" value="${projectid}" />
								 </td>
						   </tr> 
					
					<tr>	
					<th>Type Of Alert/SCN<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="typeOfAlert" styleId="typeOfAlertId"  value="${Bean.typeOfAlert}" >
					<html:option value="0">- Select -</html:option>
					<html:option value="Yellow">Yellow </html:option>
					<html:option value="RED">RED</html:option>
					<html:option value="SCN">SCN</html:option>					
 					</html:select> </span></td>
				</tr> 
				<tr>	
					<th>Date Of Issue Of Alert/SCN  <span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="dateOfIssue" styleId="dateOfIssueId"    onchange="javascript:compareDate(this.value);"  value="${Bean.dateOfIssue}"/> </span></td>
				</tr>
				<tr>	
					<th>category Of Reason<span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="reasoncategory" styleId="reasoncategoryId" value="${Bean.reasoncategory}" >
					<html:option value="0">- Select -</html:option>
					<html:option value="PPWS">PPWS </html:option>
					<html:option value="TC Inspection">TC Inspection</html:option>
					<html:option value="Financials">Financials</html:option>	
				    <html:option value="Others">Others</html:option>								
 					</html:select> </span></td>
				</tr>
				
				
				<tr>	
					<th>Reason For Issuing Alert/Show Cause <span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="reasonForIssue" styleId="reasonForIssueId" value="${Bean.reasonForIssue}"    /> </span></td>
				</tr>
				
				
					<tr>	
					<th>Issuing Agency <span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="issuingAgency" styleId="issuingAgencyId" value="${Bean.issuingAgency}" >
					<html:option value="0">- Select -</html:option>
					<html:option value="CTSA">CTSA </html:option>
					<html:option value="SRLM">SRLM</html:option>
  					</html:select> </span></td>
				</tr> 
			  
			  
			  
				<tr>	
					<th>Date Of Reply From PIA<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="dateOfReplyFromPia" styleId="dateOfReplyFromPiaId" value="${Bean.dateOfReplyFromPia}"  /> </span></td>
				</tr>
				 
				<tr>	
					<th>Reply From PIA <span class="text-error"></span></th>
					<td><span class="text-error"> <html:textarea property="replyFromPia" styleId="replyFromPiaId" value="${Bean.replyFromPia}"  /> </span></td>
				</tr>
				<tr>	
					<th>Status <span class="text-error"></span></th>
					<td><span class="text-error"> <html:select property="status" styleId="statusId"  value="${Bean.status}" >  	
					<html:option value="0">- Select -</html:option>
					<html:option value="Query-Raised">Query-Raised</html:option>
					<html:option value="Replied">Replied</html:option>
				    <html:option value="Closed">Closed</html:option>
				    </html:select> </span></td>
					 
				</tr>
				
				
				
				
				<tr>	
					<th>Date of Communication to PIA<span class="text-error"></span></th>
					<td><span class="text-error"> <html:text property="communicationToPiadate" styleId="communicationToPiadateId" value="${Bean.communicationToPiadate}" /> </span></td>
				</tr>
				
				<tr>	
					<th>Remarks<span class="text-error"></span><html:hidden property="id" styleId="alerthide" value="${alertId}"></html:hidden></th>
					<td><span class="text-error"> <html:textarea property="remarks" styleId="remarksId" value="${Bean.remarks}"  /> </span></td>
				</tr>	 
			
			</table> 
			
			<div align="center">
				<input name="Button" type="button" class="button checkPermissionForFormForInsert" id="myUpdate" value="Update" onclick="Update()"/> 		 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.back"/>" onclick="back()"/>   
				
			</div>		
			
	
</html:form>
 