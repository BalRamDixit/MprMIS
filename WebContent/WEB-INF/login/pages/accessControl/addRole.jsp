<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Add: Role</title>

<!--script for menu-->
<script type="text/javascript">
 
function checkRecord(){

var roleName = document.accessForm.roleName.value;
var roleDesc = document.accessForm.roleDescription.value;

var url = "access.do?methodName=checkRecord&roleName="+roleName+"&roleDesc="+roleDesc;
	if (window.XMLHttpRequest) {
	req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
	//req = new ActiveXObject("Msxml2.XMLHTTP");
	req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.open("GET", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.onreadystatechange = handleHttpResponse;
	req.send(null);
}
function handleHttpResponse(){
 if (req.readyState == 4) 
  {	
    var response = req.responseText; 
  
    if(response == 'true'){
	     alert("<bean:message key="error.roleExist" />");

	     return;
    }else{
         
         document.accessForm.roleName.focus();

	     save();
    }
  }
}

function save()
{
	
	
 if(document.accessForm.roleName.value == "" )
  	{
	  	var arg='<bean:message key="label.roleName" />';  
		var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
		alert(mess);
	  	document.accessForm.roleName.focus();
	  	return false;
  	} 
  	var roleName=document.accessForm.roleName.value;
	
	alphnum = /^[A-Za-z][A-Za-z0-9 ]*$/;
	if( !(alphnum.test(roleName)) ) {
		var arg='<bean:message key="label.roleName" />';  
		var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
		alert(mess);
		document.accessForm.roleName.value ="";
		document.accessForm.roleName.focus();
		return false;
	}
  	if( document.accessForm.roleDescription.value == "" )
  	{
	  	var arg='<bean:message key="access.addRole.roleDesc.label" />';  
		var mess='<bean:message key="msg.please.enter"  arg0="'+arg+ '" />';  
		alert(mess);
	  	document.accessForm.roleDescription.focus();
	  	return false;
  	} 
  	var roleDescription=document.accessForm.roleDescription.value;
	if( !(alphnum.test(roleDescription)) ) {
		var arg='<bean:message key="access.addRole.roleDesc.label" />';  
		var mess='<bean:message key="error.correctvalue"  arg0="'+arg+ '" />';  
		alert(mess);
		document.accessForm.roleDescription.value ="";
		document.accessForm.roleDescription.focus();
		return false;
	}
	 if(document.accessForm.levelSchemeName.value == "" )
	  	{
	  		var arg='<bean:message key="label.schemeName" />';  
			var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
			alert(mess);
		  	document.accessForm.levelRoleName.focus();
		  	return false;
	  	}
  	if( document.accessForm.levelRoleName.value == "" )
  	{
  		var arg='<bean:message key="access.addRole.roleLevel.label" />';  
		var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
		alert(mess);
	  	document.accessForm.levelRoleName.focus();
	  	return false;
  	}
  		
  	if((document.accessForm.roleName.value != "") && (document.accessForm.roleDescription.value != "") && (document.accessForm.levelRoleName.value != ""))
  	  {
  	  	
  	  	var ctr=0;
  		for (i=0; i<document.accessForm.elements.length; i++)
  		{
			if (document.accessForm.elements[i].type == 'checkbox'  )
			{
		   		if(document.accessForm.elements[i].checked)
	  			ctr=ctr+1;
			}		
	
		}
		if(ctr==0) {
		alert("<bean:message key="msg.selectOne" />");
		return false;
		}
		
		
  	  	
	}
	var status=window.confirm("<bean:message key="alert.submit.confirm" />");
		if(status==true){
  		document.accessForm.action = "access.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
		document.accessForm.submit();
  	  }
}

function getFormDetails(var1){

	 if( document.accessForm.levelSchemeName.value == "" )
	  	{
	  		var arg='<bean:message key="label.schemeName" />';  
			var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
			alert(mess);
		  	document.accessForm.levelRoleName.focus();
		  	return false;
	  	}
	  else {	
			document.accessForm.action = "access.do?methodName=getFormDetails&levelRoleName="+var1+"&"+tokenParameter+"="+tokenValue;
			document.accessForm.submit();
		}
}

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
function getLevelRole(){
	
  document.getElementById('levelRoleName').value="";
  
  getFormDetails("");
  
}    
</script>
<!--Main form section starts here-->
<html:form action="login/access">

	<table width="100%" >
		<tr>
			<td>
			
			<table width="100%" >
				<tr>
					<td align="center" class="pageHeader"><bean:message  key="lable.add" /><bean:message  key="login.selectRole.label" /></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
               </tr>
				<tr>
					<td style="padding-left: 7%;" ><span class="text-error">*</span>
					<span class="normaltext"><bean:message  key="lable.field.manadatory" /></span>
					</td>
				</tr>
			</table>
			<table width="100%" align="center" class="inputTBL">
				<tr>
					<th><bean:message  key="access.addRole.roleName.label" /><span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="roleName" styleId="roleName" size="31" maxlength="30" onblur="this.value=this.value.toUpperCase();"/> </span></td>
				</tr>
				<tr>	
					<th><bean:message  key="access.addRole.roleDesc.label" /><span class="text-error">*</span></th>
					<td><span class="text-error"> <html:text property="roleDescription" styleId="roleDescription" size="60" maxlength="60" onblur="this.value=this.value.toUpperCase();" /> </span></td>
				</tr>
				<tr>	
					<th><bean:message  key="label.schemeName" /><span class="text-error">*</span></th>
					<td><span class="text-error">  
						<html:select property="levelSchemeName" styleId="levelSchemeName" onchange="getLevelRole()">
							<html:option value=""><bean:message key="label.select"/></html:option>
							<logic:present name="schemeList">
								<html:options collection="schemeList" property="key" labelProperty="value"/>
							</logic:present>
						</html:select>
					</span></td>
				</tr>
				<tr>
					<th><bean:message  key="access.addRole.roleLevel.label" /><span class="text-error">*</span></th>
					<td ><span class="text-error"> 
						<html:select property="levelRoleName" styleId="levelRoleName" onchange="getFormDetails(this.value)">
							<html:option value=""><bean:message key="label.select"/></html:option>
							<html:options collection="levelList" property="key" labelProperty="value"/>
						</html:select>
					</span></td>
				</tr>
				<tr>
				<td><font color="red" size="1">&nbsp;<html:errors property="roleName"/></font></td>
				<td><font color="red" size="1">&nbsp;<html:errors property="roleDescription"/></font></td>
				<td><font color="red" size="1">&nbsp;<html:errors property="levelRoleName"/></font></td>
				<td></td>
				</tr>
			</table>
			
			<table width="90%" align="center" class="inputTBL">
				<logic:present name="subModList">
				<tr>					
					<th><bean:message  key="access.addRole.moduleForm.label" /></th>
					<th><bean:message  key="access.addRole.permission.label" /></th>					
				</tr>
				
				<logic:iterate name="accessForm" property="accessList" id="index">
					<logic:equal name="index" property="moduleStatus" value="change">
						<tr>
							<th colspan="2"><b><bean:write name="index" property="modName" /></b></th>
						</tr>
					</logic:equal>

					<tr>
						<td>&nbsp;<bean:write name="index" property="formName" />&nbsp;</td>						
							<td><html:checkbox name="index" property="permission" styleId="permission" indexed="true" />
						</td>						
					</tr>
				</logic:iterate>
				</logic:present>
				<%-- <tr>
					<td height="40" colspan="5" align="center">
					<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="checkRecord()"/> 
					<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
					<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage()" />
					</td>
				</tr> --%>
			</table>
			<div align="center">
				<input name="Button" type="button" class="button" value="<bean:message  key="button.save"/>" onclick="checkRecord()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.clear"/>" onclick="clearField()"/> 
				<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage()" />
			</div>			
			</td>
		</tr>
	</table>
	
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<!--Main form section ends here-->