<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">
function closePage()
{
	document.getElementById("newRec").style.display="none";
	$('.CompareButton').show();
}
function clearField()
{
	document.getElementById("aadharNoId").value="";
	document.getElementById("bankAccountId").value="";
	document.getElementById("mobileNoId").value="";
	document.getElementById("adharLinkageId").value="";
	document.getElementById("insuranceId").value="";
	document.getElementById("proLifeId").value="";
	document.getElementById("foreignPlacedId").value="";
	document.getElementById("earningpId").value="";
	document.getElementById("retainedForMonthsId").value="";
  }

function hideShow(str){
	if(str=="HIDE"){
	    document.getElementById("newRec").style.display="none";
	    $('.CompareButton').show();
	}else {
		document.getElementById("newRec").style.display="";	
 		$('.CompareButton').hide();
	}
}

$(document).ready(function () {
	
	hideShow('HIDE');
	showRemark("0");

});

function addDetail(){
	hideShow('SHOW');
 	clearField();
}

function editDetail( aadharNo , bankAccount , mobileNo )
{
	document.getElementById("aadharNoId").value=aadharNo;
	document.getElementById("bankAccountId").value=bankAccount;
	document.getElementById("mobileNoId").value=mobileNo;
}
function  test(str)
{
	if(str=="2016-17")
	{
	 document.getElementById("newRec").style.display="";
	}
	 else
	{
	 document.getElementById("newRec").style.display="none";
	}
}
function deleteDetail(id)
{
	var strconfirm = confirm("Are you sure you want to delete the Record ?");
    if (strconfirm == true) {
        return true;
    }
}
  
</script>
<script type="text/javascript">
function showRemark(str){
	if (str=="2016-17")
	{
		document.getElementById("remarkRow").style.display = "";	
		hideShow('HIDE');
	} else{
		document.getElementById("remarkRow").style.display = "none";
		hideShow('HIDE');
	}
}
</script>


<html:form action="login/projectCandidateDataAction">

	<table width="100%" class="pageHeaderTable">
		<tr>
			<td align="center" class="pageHeader">PROJECT CANDIDATE DATA</td>					
        </tr>				
	</table>
			
	<table width="100%" align="center" class="inputTBL">
		 <tr>
			<th>SRLM Name</th>
			<td><b> ANDHRA PRADESH</b></td>
		 </tr>
		 <br/>
		
		 <tr>
         <th align="center">Month<span class="text-error">*</span></th>
         <td ><span class="text-error">					    
         
          <html:select property="month">                                
          <html:option value="0"  >-Select- </html:option>
		  <html:option value="JANUARY">JANUARY</html:option>                
		  <html:option value="FEBRUARY">FEBRUARY</html:option>                
		  <html:option value="MARCH">MARCH</html:option>                
	      <html:option value="APRIL">APRIL</html:option>                
		  <html:option value="MAY">MAY</html:option>                
		  <html:option value="JUNE">JUNE</html:option>                
		  <html:option value="JULY">JULY</html:option>                
		  <html:option value="AUGUST">AUGUST</html:option>                
		  <html:option value="SEPTEMBER">SEPTEMBER</html:option>                
		  <html:option value="OCTOBER">OCTOBER</html:option>                
		  <html:option value="NOVEMBER">NOVEMBER</html:option>                
		  <html:option value="DECEMBER">DECEMBER</html:option>                
 		  </html:select>
 		  </span>	             
		  </td>
		  </tr>	 	
		
		<tr>
        <th align="center">Year<span class="text-error">*</span></th>
           <td ><span class="text-error">					    
               <html:select property="year"  onchange="javascript:showRemark(this.value);">>                                
               <html:option value="0">-Select- </html:option>
			   <html:option value="2016-17">2016-17</html:option>                
			   <html:option value="2017-18">2017-18</html:option>              
			   <html:option value="2018-19">2018-19</html:option>
			   </html:select>
 			   </span>	             
			</td> 
        </tr>
	</table>
	<br/>
	<br/>
          <div id="remarkRow">					
             <table width="100%" class="TFtable" >
						<tr>
							<th align="left">S.No.</th>
							<!-- <th align="left">Total No of Candidate(Under Training + Trained)</th> -->
							<th align="left">No Of Candidate with Aadhar No</th>
							<th align="left">No Of Candidate Bank Accounts</th>
							<th align="left">No Of Candidate Mobile Number</th>
 							<th>&nbsp;</th>
						</tr>
				<logic:present name="ProjectCandidateList">
							<c:set var="indexCount" value="1" />
						<logic:iterate id="list" name="ProjectCandidateList" indexId="index">
							
								<tr>
									<td>${indexCount}</td>
									<%-- <td >${list[1]}</td> --%>
									<td>${list[2]}</td>
									<td>${list[3]}</td>
									<td>${list[4]}</td>
 									<td>
									<html:button styleClass="CompareButton" property=""  onclick="hideShow('SHOW');editDetail( '${list[2]}','${list[3]}','${list[4]}')">Edit</html:button>
									<html:button styleClass="CompareButton" property=""  onclick="hideShow('HIDE');deleteDetail(${list[0] })">Delete</html:button>
									</td>
								</tr>
								
				
								<c:set var="indexCount" value="${indexCount + 1}" />
						</logic:iterate>
				</logic:present>
			</table>
					
					<div align="center"><html:button styleClass="CompareButton" property="" styleId="addB" onclick="hideShow('SHOW');addDetail();">ADD NEW</html:button>
			 	</div>
			 	</div> 
			<br/>
			<div id="newRec">
			<table width="100%" align="center" class="inputTBL">
					
				   <tr>	
					<th>Project ID<span class="text-error">*</span></th>
					<td><span class="text-error"> <input disabled="true"  placeholder="PR-ID1" /> </span></td>
				    </tr>	
					
					<tr>	
					<th>Total No of Candidate(Under Training + Trained)<span class="text-error">*</span></th>
					<td><span class="text-error"><input disabled="true"  placeholder="80" /> </span></td>
				    </tr>
				
				    <tr>	
					<th>No Of Candidate with Aadhar No<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="aadharNo" styleId="aadharNoId" /> </span></td>
				    </tr>
				
				    <tr>	
					<th>No Of Candidate Bank Accounts<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="bankAccount" styleId="bankAccountId"/> </span></td>
			    	</tr>
				
				   <tr>	
					<th>No Of Candidate Mobile Number<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="mobileNo" styleId="mobileNoId" /> </span></td>
				   </tr>
				
				   <tr>	
					<th>No Of Candidate Aadhar Linkage with Bank Account<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="aadharlinkageBank" styleId="adharLinkageId" /> </span></td>
				   </tr>
				 
				   <tr>	
					<th>No Of Candidate with Insurance<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="insurance" styleId="insuranceId"/> </span></td>
				   </tr>
			    	
			      <tr>	
					<th>No Of Pro-Life Trained Candidate <span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="proLife"  styleId="proLifeId"/> </span></td>
				  </tr>
			
			      <tr>	
					<th>No of Candidates Foreign Placed<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="foreignPlaced" styleId="foreignPlacedId" /> </span></td>
				  </tr>
				
				  <tr>	
					<th>No of Candidates placed & earning>=15000 PM<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="earningp" styleId="earningpId"/> </span></td>
				  </tr>	
				 
				  <tr>	
					<th>No of Candidates Retained for >12 Months<span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="retainedForMonths" styleId="retainedForMonthsId"/> </span></td>
				  </tr>	
				
			</table>
			
			
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="checkRecord()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage()"/> 
				
			</div>		
			
			</div>
			
	
</html:form>
</body>
<!--Main form section ends here-->