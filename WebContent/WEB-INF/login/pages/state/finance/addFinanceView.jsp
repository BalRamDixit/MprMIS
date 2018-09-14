<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
>

<script>

function editDetail(str){
	
	document.forms[0].action = "StateTargetFormAction.do?methodName=showRecord&id="+str+"&"
    + tokenParameter + "=" + tokenValue;
   document.forms[0].submit();
 
}


</script>





 <html:form action="login/stateFinancialForm">
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">State Setup-Financial</td>					
               </tr>
				
			</table>			
			
				<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>SRLM Name</th>
					<td><b> ANDHRA PRADESH</b></td>
				</tr>
				</table>
		<br/>
			 	
			<br/>
		 <table>
			   <tr >
               <th >State Name <span class="text-error"></span></th>
               <td><span class="text-error">ANDHRA PRADESH</span></td>
               </tr> 
		</table> 
			  	
		<%-- 	  	<table width="95%" align="center"  class="inputTBL">
					<tr>
 							<th align="left">Scheme <span class="text-error"></span></th>
							<th align="left">Since when Annual Plan <span class="text-error"></span></th>
							<th align="left">Central Technical Support Agency <span class="text-error"></span></th>
							<th align="left">Appraisal Agency <span class="text-error"></span></th>
							<th align="left">Technical Support Agency Name <span class="text-error"></span></th>
							<th align="left">Status of MIS in State <span class="text-error"></span></th>
 							<th align="left">Name of application/Package <span class="text-error"></span></th>
							<th align="left">Whether MIS is SOP compliant <span class="text-error"></span></th>
							<th align="left">Whether MIS is connected to Central MIS <span class="text-error"></span></th>
							<th align="left">Target Period Start Year <span class="text-error"></span></th>
							<th align="left">Target Period End Year <span class="text-error"></span></th>
							<th align="left">Sanctioned/ Allocated Training Target <span class="text-error"></span></th>
							<th align="left">Fund Sanctioned (Rs) <span class="text-error"></span></th>
							<th align="left">Date of Sanction <span class="text-error"></span></th>
 						</tr>
						<logic:present name="detailList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="detailList" indexId="index">
							
								<tr>
									
									<td>${list[0]}</td>
									<td>${list[1]}</td>
									<td>${list[2]}</td>
									<td>${list[3]}</td>
									<td>${list[4]}</td>
									<td>${list[5]}</td>
									<td>${list[6]}</td>
									<td>${list[7]}</td>
									<td>${list[8]}</td>
									<td>${list[9]}</td>
									<td>${list[10]}</td>
									<td>${list[11]}</td>
									<td>${list[12]}</td>
									<td>${list[13]}</td>
									
 									
							<td style="text-align: center;">
									<html:button styleClass="CompareButton" property="" onclick=" editDetail('${list[0]}')">Edit</html:button>
 							</td> 
					</tr>

								
							</logic:iterate>
						</logic:present>
						
				</table> --%>
	<table width="100%" align="center" class="inputTBL" >
       
   <logic:present name="detailList">
	<c:set var="indexCount" value="1" />
	<logic:iterate id="list" name="detailList" indexId="index">
							
       
 <tr  rowspan="2" colspan="9" >
<td   rowspan="2" colspan="2"></td>
<td align="center" colspan="4" ><strong>Project Cost</strong></td>
<td  align="center" colspan="3" ><strong>Other Cost</strong></td>
<td  align="center" rowspan="2" ><strong>Total</strong></td>
</tr> 

<tr>
<td align="center" width="40%"><strong>Program cost</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>Support cost</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>CTSA</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>Total</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>Admin cost</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>Capacity Building</strong><span class="text-error"></span></td>
<td align="center" width="40%"><strong>Total Other</strong><span class="text-error"></span></td>
</tr>

<tr>
<td colspan="12" align="center"><b>Total Sanction amount </b></td>
</tr>

<tr>  
<td colspan="2"><b>Central</b></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="centralProgramCost" onblur="calculateSum();" styleId="centralProgramCostId" /></span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="centralSuportCost" onblur="calculateSum();" styleId="centralSuportCostId" />  </span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="centralCtsa" onblur="calculateSum();" styleId="centralCtsaId" />  </span></td>
<td id="sumCatId"><html:hidden property="central_totalprojectcost" /><span class="text-error"></span></td>

<td><span class="text-error"><html:text size="10" maxlength="10" property="centralAdminCost" onblur="calculateSum();" styleId="centralAdminCostId" />  </span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="centralCapacityCost" onblur="calculateSum();"  styleId="centralCapacityCostId"  />  </span></td>
<td id="sumCatId1"><html:hidden property="central_otherprojectcost" /><span class="text-error"></span></td>
<td id="sumCatId11"><html:hidden property="central_totalcost" /><span class="text-error"></span></td>
</tr>


<tr>
<td colspan="2"><b>State</b></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="stateProgramCost" onblur="calculateSumA();" styleId="stateProgramCostId" />  </span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="stateSuportCost"  onblur="calculateSumA();" styleId="stateSuportCostId"/>  </span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="stateCtsa" onblur="calculateSumA();" styleId="stateCtsaId" />  </span></td>
<td id="sumCatIdA"><html:hidden property="state_totalprojectcost" /><span class="text-error"></span></td>

<td><span class="text-error"><html:text size="10" maxlength="10" property="stateAdminCost" onblur="calculateSumA();" styleId="stateAdminCostId" />  </span></td>
<td><span class="text-error"><html:text size="10" maxlength="10" property="stateCapacityCost" onblur="calculateSumA();" styleId="stateCapacityCostId"  />  </span></td>
<td id="sumCatId1A"><html:hidden property="state_otherprojectcost" /><span class="text-error"></span></td>
<td id="sumCatId2A"><html:hidden property="state_totalcost" /><span class="text-error"></span></td>
</tr>

<tr>
<td colspan="2"><b>Central share Released to State</b></td>
<td><html:text size="10" maxlength="10"  property="centralReleasedProgramCost" onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedProgramCostId" /> <div id="spancentralReleasedProgramCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10" property="centralReleasedSuportCost" onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);"  styleId="centralReleasedSuportCostId" /><div id="spancentralReleasedSuportCost" style="color:red;"></div>   </td>
<td><html:text size="10" maxlength="10" property="centralReleasedCtsa" onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedCtsaId"  /><div id="spancentralReleasedCtsa" style="color:red;"></div>   </td>
<td id="sumCatIdB"><html:hidden property="centralreleased_totalprojectcost" /><span class="text-error"></span></td>

<td><html:text size="10" maxlength="10"  property="centralReleasedAdminCost" onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);"  styleId="centralReleasedAdminCostId" /> <div id="spancentralReleasedAdminCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10"  property="centralReleasedCapacityCost"  onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedCapacityCostId" /> <div id="spancentralReleasedCapacityCost" style="color:red;"></div>  </td>
<td id="sumCatId1B"><html:hidden property="centralreleased_otherprojectcost" /><span class="text-error"></span></td>
<td id="sumCatId2B"><html:hidden property="centralreleased_totalcost" /><span class="text-error"></span></td>
</tr>

<!-- new changes... -->
<tr>
<td colspan="12" align="center"><b>Total received by SRLM</b></td>
</tr>

<tr>
<td colspan="2"><b>Central Share Amount</b></td>
<td><html:text size="10" maxlength="10"  property="centralOnlyProgramCost" onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyProgramCostId" /> <div id="spancentralOnlyProgramCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10" property="centralOnlySuportCost" onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlySuportCostId" />  <div id="spancentralOnlySuportCost" style="color:red;"></div> </td>
<td><html:text size="10" maxlength="10" property="centralOnlyCtsa" onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyCtsaId"  /><div id="spancentralOnlyCtsa" style="color:red;"></div>   </td>
<td id="sumCatIdC"><html:hidden property="centralonly_totalprojectcost" /><span class="text-error"></span></td>

<td><html:text size="10" maxlength="10" property="centralOnlyAdminCost"  onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyAdminCostId" /> <div id="spancentralOnlyAdminCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10" property="centralOnlyCapacityCost" onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);"  styleId="centralOnlyCapacityCostId" /><div id="spancentralOnlyCapacityCost" style="color:red;"></div>   </td>
<td id="sumCatId1C"><html:hidden property="centralonly_otherprojectcost" /><span class="text-error"></span></td>
<td id="sumCatId2C"><html:hidden property="centralonly_totalcost" /><span class="text-error"></span></td>
</tr>

<tr>
<td colspan="2"><b>Central Share Released Date</b></td>
<td><span class="text-error"><html:text  property="centralReleasedDateProgramCost" readonly="true" styleId="centralReleasedDateProgramCostId" /></span></td>
<td><span class="text-error"><html:text  property="centralReleasedDatSuportCost" readonly="true"  styleId="centralReleasedDatSuportCostId" /></span></td>
<td><span class="text-error"><html:text  property="centralReleasedDatCtsa" readonly="true"  styleId="centralReleasedDatCtsaId" /></span></td>
<td></td>
<td><span class="text-error"><html:text  property="centralReleasedDatAdminCost"  readonly="true" styleId="centralReleasedDatAdminCostId" /></span></td>
<td><span class="text-error"><html:text  property="centralReleasedDatCapacityCost"  readonly="true" styleId="centralReleasedDatCapacityCostId" /></span></td>
<td></td>
<td></td>
</tr>

<tr>
<td colspan="2"><b>State Share Amount</b></td>
<td><html:text size="10" maxlength="10"  property="stateOnlyProgramCost" onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyProgramCostId" /> <div id="spanstateOnlyProgramCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10" property="stateOnlySuportCost" onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlySuportCostId" /><div id="spanstateSuportCost" style="color:red;"></div>   </td>
<td><html:text size="10" maxlength="10" property="stateOnlyCtsa" onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyCtsaId" /> <div id="spanstateOnlyCtsa" style="color:red;"></div>  </td>
<td id="sumCatIdE"><html:hidden property="stateonly_totalprojectcost" /><span class="text-error"></span></td>

<td><html:text size="10" maxlength="10" property="stateOnlyAdminCost"  onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyAdminCostId"  /> <div id="spanstateOnlyAdminCost" style="color:red;"></div>  </td>
<td><html:text size="10" maxlength="10" property="stateOnlyCapacityCost" onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyCapacityCostId" /> <div id="spanstateOnlyCapacityCost" style="color:red;"></div>  </td>
<td id="sumCatId1E"><html:hidden property="stateonly_otherprojectcost" /><span class="text-error"></span></td>
<td id="sumCatId2E"><html:hidden property="stateonly_totalcost" /><span class="text-error"></span></td>
</tr>

<tr>
<td colspan="2"><b>State Share Released Date</b></td>
<td><span class="text-error"><html:text  property="stateReleasedDateProgramCost" readonly="true"  styleId="stateReleasedDateProgramCostId" /></span></td>
<td><span class="text-error"><html:text  property="stateReleasedDateSuportCost" readonly="true"  styleId="stateReleasedDateSuportCostId" /></span></td>
<td><span class="text-error"><html:text  property="stateReleasedDateCtsa" readonly="true"  styleId="stateReleasedDateCtsaId" /></span></td>
<td></td>
<td><span class="text-error"><html:text  property="stateReleasedDateAdminCost" readonly="true"  styleId="stateReleasedDateAdminCostId" /></span></td>
<td><span class="text-error"><html:text  property="stateReleasedDateCapacityCost" readonly="true"  styleId="stateReleasedDateCapacityCostId" /></span></td>
<td></td>
<td></td>
</tr>

	
							</logic:iterate>
						</logic:present>
						
</table>
</html:form>