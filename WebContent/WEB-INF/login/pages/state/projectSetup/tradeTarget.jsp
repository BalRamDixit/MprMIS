<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>


<!--script for menu-->
<script type="text/javascript">

function deleteDetail(id){
	 document.forms[0].action="tradeTarget.do?methodName=delete&id="+id+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
}


function editDetail(projectId){
	 

	 document.forms[0].action="tradeTarget.do?methodName=edit&id="+projectId+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
	  
}

 	 
function addDetail(){
	  
	 document.forms[0].action="tradeTarget.do?methodName=addDetail"+"&"+tokenParameter+"="+tokenValue;;
	 document.forms[0].submit(); 

}



function showRemark(str){
	 
	if (str!='0')
	{
		document.getElementById("showTable").style.display = "";	
 			
	} else{
		document.getElementById("showTable").style.display = "none";
 	}
}
$(document).ready(function()
{
	showRemark("0");
});




function save(){
	
	var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="tradeTarget.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
  
</script>

 <html:form action="login/tradeTarget">
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">TRADE TARGET</td>					
               </tr>
			</table>			
			<%-- 
				<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>${name}</th>
					<td><b>${nameValue}</b></td>
				</tr>
				</table> --%>
			  	<table width="100%"  class="TFtable" >
					<tr>
 							<th align="left">S.No.<span class="text-error"></span></th>
							<th align="left">Project Id<span class="text-error"></span></th>
							<th align="left">Total Training Target <span class="text-error"></span></th>
 							<th>&nbsp;</th>
							
 						</tr>
						<logic:present name="ViewList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="ViewList" indexId="index">
							
								<tr>
									<td>${indexCount}</td>
									<td>${list[0]}</td>
									<td>${list[1]}</td>
 									<td><a href="#" onclick=" editDetail('${list[0]}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#" onclick=" deleteDetail('${list[0]}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
								</tr>

								<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
						
				</table>
			  
			  
			  <div align="center"><html:button styleClass="CompareButton" property="" styleId="addB" onclick="addDetail();">ADD NEW</html:button>
			 	</div>
			 
			  
			  
			  
			  
			  
			<%-- <table width="100%" align="center" class="inputTBL">
			 
			 	 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td>	<html:select property="projectID" styleId="projectIDId"  styleClass="form-control"  onchange="javascript:showRemark(this.value);" >
								<html:option value="0">-select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project[1]}">${project[1]}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
			 </table>
			   --%>
			 <br/>
			 <br/>
			 <br/>
			 
			 <%-- <div id="showTable">
			<table width="100%" align="center" class="inputTBL">
			
			 <tr><th><span class="input-group-addon" id="basic-addon1">Sector</span></th>
						<td>	<html:select property="sector" styleId="sectorId"   styleClass="form-control" >
								<html:option value="">-select-</html:option>
								<logic:present name="SectorList">
									<logic:iterate id="sector" name="SectorList">
										<html:option value="${sector[0]}">${sector[1]}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
			
			 
		
			 
			 
			 
			 
			 
				<tr><th><span class="input-group-addon" id="basic-addon1">Trade</span></th>
						<td>	<html:select property="trade" styleId="tradeId"   styleClass="form-control" >
								<html:option value="">-select-</html:option>
								<logic:present name="TradeList">
									<logic:iterate id="trade" name="TradeList">
										<html:option value="${trade[0]}">${trade[1]}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
			<tr>	
					<th>Is Other Trade Included <span class="text-error">*</span></th>
					<td><span class="text-error"><html:select property="otherTrade" styleId="otherTradeId" >
					<html:option value="0">- Select -</html:option>
					<html:option value="YES">YES</html:option>
					<html:option value="NO ">NO</html:option>
					
					</html:select>   </span></td>
				</tr>
				<tr>	
					<th>Total Trade Duration(in hours) <span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="totalTradeDuration" styleId="totalTradeDurationId"  /> </span></td>
				</tr>
				
				
				
				<tr>	
					<th>OJT(in hours) <span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="ojt" styleId="ojtId"  /> </span></td>
				</tr>
				<tr>	
					<th>Training Target <span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="trainingTarget" styleId="trainingTargetId"  /> </span></td>
				</tr>
				<tr>	
					<th>Non Residential Full Time<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="nonResiFull" styleId="nonResiFullId"  /> </span></td>
				</tr>
				
				
				<tr>	
					<th>Non Residential Part Time<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="nonResiPart" styleId="nonResiPartId"  /> </span></td>
				</tr>				
				<tr>	
					<th>Non Residential Weekend<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="nonResiWeekend" styleId="nonResiWeekendId"  /> </span></td>
				</tr>				
				<tr>	
					<th>Residential Full Time<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="resiFull" styleId="resiFullId"  /> </span></td>
				</tr>
				
			</table>
			
			</div>
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="save()"/> 
				 
				
			</div>		
			 --%>
			 
			
	
</html:form>
 