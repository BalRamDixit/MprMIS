<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<!--script for menu-->
<script type="text/javascript">
function closePage()
{
	
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.accessForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;;
	document.accessForm.submit();
	}
}
function clearField()
{
	var status=window.confirm("<bean:message key="msg.clearForm"/>");
	if(status==true){
	document.accessForm.action = "access.do?methodName=showAdd"+"&"+tokenParameter+"="+tokenValue;
	document.accessForm.submit();
	}
	
}
  
</script>
<!--Main form section starts here-->
<html:form action="login/projectTarget">
	<div>
	<table width="100%" >
		<tr>
			<td>
			
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">Project Setup - Project Target</td>
					
               </tr>
				<tr>
					<td style="padding-left: 7%;" ><span class="text-error">*</span>
					<span class="normaltext"><bean:message  key="lable.field.manadatory" /></span>
					</td>
				</tr>
			</table>
			<table width="100%" align="center" class="inputTBL">
				<tr >
                <th >State Name <span class="text-error">*</span></th>
                 <td span class="text-error"> <html:text size="25" maxlength="100" property="stateName" disabled="true" styleId="stateNameId" /></td>
               </tr> 
            
            <tr>
					<th>Project ID<span class="text-error">*</span></th>
					<td><span class="text-error">  
					<html:select property="projectID">
					
					<html:option value="1">Project ID-1</html:option>
					<html:option value="2">Project ID-2</html:option>
					<html:option value="3">Project ID-3</html:option>
					</html:select>	
					</span></td>
			</tr>
            
            
            				<tr>	
					<th>Total training target<span class="text-error">*</span></th>
					<td><span class="text-error"><html:text disabled="true" value="19" property="totalTrainingTarget" />  </span></td>
				</tr>
				<tr>	
					<th>SC<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="sc" />	</span></td>
				</tr>
			<tr>	
					<th>ST<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="st" />	</span></td>
				</tr>
				<tr>	
					<th>General<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="general" />	</span></td>
				</tr>
				<tr>	
					<th>Minority<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="minority" />	</span></td>
				</tr>
				<tr>	
					<th>Woman<span class="text-error">*</span></th>
					<td><span class="text-error">  <html:text property="woman" />	</span></td>
				</tr>
			</table>
			
			 
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="checkRecord()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage()" />
			</div>			
			</td>
		</tr>
	</table>
	</div>
</html:form>

 <!--Main form section ends here-->