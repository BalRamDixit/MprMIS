<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
</head>
<script language=javascript>

 var tokenParameter='reqtrack';
 var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
 
var levelName;

function getDataList(type,param,flag)
{ 
   
	if(type=='0' || type=='2' || type=='3' || type=='4'){
	
			/*	if(document.forms[0].stateCode!=null)
					document.forms[0].stateCode.value="";
				if(document.forms[0].districtCode!=null)
					document.forms[0].districtCode.value="";
				
				if(document.forms[0].blockCode!=null)
					document.forms[0].blockCode.value="";
		 */
	 
	} 
	if(flag=='0'){
	
		 		if(document.forms[0].stateCode!=null)
					document.forms[0].stateCode.value="";
				if(document.forms[0].districtCode!=null)
					document.forms[0].districtCode.value="";
				
				if(document.forms[0].blockCode!=null)
					document.forms[0].blockCode.value="";
	
	}
	
	if(type=='ZP'){
	 	if(document.forms[0].districtCode!=null)
	 	 document.forms[0].districtCode.value="";
	
	}
	if(type=='ZP' || type=='BP'){
	 	if(document.forms[0].blockCode!=null)
	 	 document.forms[0].blockCode.value="";
	
	}
	
	
	 if(document.forms[0].loginId!=null){
	
		document.forms[0].loginId.value="";
	 }	 
	 document.userForm.action = "userAction.do?methodName=getDataList&form=2&type="+type+"&"+tokenParameter+"="+tokenValue+"&param="+param;
    
     document.userForm.submit();
} 

function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("<bean:message key="error.invalidEmailId" />");
		   return false;
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		  alert("<bean:message key="error.invalidEmailId" />");
		   return false;
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("<bean:message key="error.invalidEmailId" />");
		    return false;
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		  alert("<bean:message key="error.invalidEmailId" />");
		    return false;
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		   alert("<bean:message key="error.invalidEmailId" />");
		    return false;
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		   alert("<bean:message key="error.invalidEmailId" />");
		    return false;
		 }
		
		 if (str.indexOf(" ")!=-1){
		   alert("<bean:message key="error.invalidEmailId" />");
		    return false;
		 }

 		 return true;					
	}

function userForm_mask () { 

	 var arg1='<bean:message key="userForm.userName" />';  
	 var arg2='<bean:message key="userForm.userDesignation" />';  
	 var arg3='<bean:message key="lable.user.loginID" />';  
	
     this.a0 = new Array("userName", '<bean:message key="error.correctvalue"  arg0="'+arg1+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a1 = new Array("userDesignation", '<bean:message key="error.correctvalue"  arg0="'+arg2+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[ a-zA-Z]*$/;  return this[varName];"));
     this.a3 = new Array("loginId", '<bean:message key="error.correctvalue"  arg0="'+arg3+'" />', new Function ("varName", "this.mask=/^[a-zA-Z]{1}[a-zA-Z0-9]*$/;  return this[varName];"));
     
}

function searchUser(form){
	if(! ( validateMask(form))) 
   		 return;
   	 
	if(document.getElementById('levelCode').value==""){
		alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	}
	if(lavel=='3'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	}
	if(lavel=='4'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}

	if(lavel=='5'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	 
 	}
 	if(document.getElementById('loginId').value==""){
		var arg='<bean:message key="lable.user.loginID" />';  
		var mess='<bean:message key="error.select"  arg0="'+arg+ '" />';  
		alert(mess);			
		return;
	} 	
	
	
	
    var status= true;
	if(status==true){
	document.userForm.action = "userAction.do?methodName=getDataList&form=2&"+tokenParameter+"="+tokenValue;
 
	document.userForm.submit();
	}
}
function updateUser(form){
    if(!( validateMask(form))) 
   		 return;
	

	if(document.getElementById('levelCode').value==""){
	alert("<bean:message key="msg.selectLevelOfUser" />");
	return;
	}
		
	var lavel=document.getElementById('levelCode').value;	
	if(lavel=='2'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	}
	if(lavel=='3'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	}
	if(lavel=='4'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	}

	if(lavel=='5'){
	if(document.getElementById('stateCode').value==""){
	alert("<bean:message key="msg.selectState" />");
	return;
	}
	if(document.getElementById('districtCode').value==""){
	alert("<bean:message key="msg.selectDistrict" />");
	return;
	}
	if(document.getElementById('blockCode').value==""){
	alert("<bean:message key="msg.selectBlock" />");
	return;
	}
	 
 	}
 	if(document.getElementById('loginId').value==""){
	alert("Please select Login Id.");
	return;
	} 	
	if(document.getElementById('userName').value==""){
	alert("<bean:message key="msg.enterUserName" />");
	return;
	}
	if(document.getElementById('userDesignation').value==""){
	alert("<bean:message key="msg.enterUserDesignation" />");
	return;
	}
	
	var emailID=document.getElementById('emailId');
	if (emailID.value != null){	
		if (echeck(emailID.value)==false){
		emailID.focus();
		return false;
	  }
	}
	
    var status=window.confirm("<bean:message key="msg.update" />");
	if(status==true){
	document.userForm.action = "userAction.do?methodName=modify&"+tokenParameter+"="+tokenValue;
    document.userForm.submit();
	} 
}
 
function clearUser(){ 

    var status=window.confirm("<bean:message key="msg.clear" />");
	if(status==true){
	document.userForm.action = "userAction.do?methodName=showModify&"+tokenParameter+"="+tokenValue;
  	document.userForm.submit();
	} 
}
function closePage()
{
	var status=window.confirm('<bean:message key="alert.close.form" />');
	if(status==true){
	document.userForm.action = "login.do?methodName=closePage&"+tokenParameter+"="+tokenValue;
 	document.userForm.submit();
	}
}
 
  

function resetList(){
	if(document.getElementById('roleListRow') != null){	
	document.getElementById('roleListRow').className="hide";
	}
}

 function getUsers(level,primaryObject){ 

  var levelCode=document.getElementById('levelCode').value;
   
  levelName = document.getElementById('levelCode')[document.getElementById('levelCode').selectedIndex].firstChild.nodeValue;
   
  if(levelCode==level){
  
        var tableBody=document.getElementById("infraTable");
		
		for(var i = tableBody.rows.length ; i>0;i-- ){
		
			tableBody.deleteRow(i-1);
		
		}
	   //	clearMultiSelect('notAvailableUserCodeID'); 

    var notAvailable='';
 
    if(document.forms[0].notAvailableUserCode!=null && typeof(document.forms[0].notAvailableUserCode!='undefined')){

			notAvailable=document.forms[0].notAvailableUserCode.name;
        }
	  
       
       //getDetailsByAjax(primaryObject,notAvailable,'GETUSERS','',level);
        
       
        getDataList(level,'0','');
       
       
   }else{
   
        document.getElementById("labelTable").className="hide";
        
		document.getElementById("infraTable").className="hide";
   
   }
  
  return;
  
}

  
</script>


<body>
<html:form action="login/userAction">
<div class="mainpane">
<table width="100%">
<tr>
	<td>
		<table width="90%" align="center">
				<tr>
					<td align="center" class="pageHeader"><bean:message key="lable.update"/>
					<bean:message key="lable.user.user"></bean:message></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
				</tr>
				<logic:present name="userModified">
				<tr>
					<td>
						<div id="errorDiv" style="border: solid #ccc 1px;-moz-border-radius: 6px;
				 		-webkit-border-radius: 6px; border-radius: 6px;
    			 		-webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; 
    			 		box-shadow: 0 1px 1px #ccc;margin: 10px 120px;
						padding:15px 10px 15px 50px;background-repeat: no-repeat;
						background-position: 10px center;color: #1642EA;
						background-color: #ACCC8B;
						background-image: url('../images/info.png');">
							User Modifed Successfully</b>.
						</div>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td><span class="mandatory">*</span>
					<span class="normaltext"><bean:message key="lable.field.manadatory"></bean:message> </span></td>
				</tr>
			 	
			 	<logic:present name="userNotFound">
					<tr>
						<td height="20" > 
						 	<script>
						 		alert('<bean:write name="userNotFound"/>');
						   	</script>
						 
				 		</td>
					</tr>
		 		</logic:present>
		 		
			</table>
			
			<table width="90%" align="center" class="inputTBL">
				<tr >
					<th width="30%"><bean:message key="lable.user.levelofuser"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="levelCode" styleId="levelCode" onchange="getUsers('0',this);">
					<html:option value=""><bean:message key="lable.manageRole.level" /></html:option>
					<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue"/>
					</html:select></td>
				</tr>	
				 
		        <logic:present name="myas">
		             <html:hidden property="entityCode"></html:hidden>
		        </logic:present>
		        
		         <logic:present name="state">
		        
				<tr>
					<th><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="stateCode" styleId="stateCode" onchange="getUsers('2',this)">
					<html:option value=""><bean:message key="label.selectState" /></html:option>
					
					<logic:present name="stateList">
						<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
					</logic:present>
					</html:select></td>
				</tr>				
				 </logic:present>
				 
			  <logic:present name="district">
				  
				 <tr>
					<th><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="stateCode" styleId="stateCode" onchange="getDataList('DT','0','');">
					<html:option value=""><bean:message key="label.selectState" /></html:option>
					<logic:present name="stateList">
						<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
					</logic:present>
					</html:select></td>
				</tr>		
				
				 
				<tr>
					<th><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="districtCode" styleId="districtCode" onchange="getUsers('3',this)">
					<html:option value=""><bean:message key="label.selectDistrict" /></html:option>
					<logic:present name="districtList">
					 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
					 </logic:present>
					</html:select></td>
				</tr>
			  </logic:present>
		 		 
			 <logic:present name="block">
		        
		        
		        <tr>
					<th><bean:message key="lable.user.selectState"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="stateCode" styleId="stateCode" onchange="getDataList('ZP','0','')">
					<html:option value=""><bean:message key="label.selectState" /></html:option>
					<logic:present name="stateList">
						<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue"/>
					</logic:present>
					</html:select></td>
				</tr>		
				
				 
				<tr>				
					<th><bean:message key="lable.user.selectDistrict"/><span class="mandatory">*</span></th>
					<td >
					<html:select property="districtCode" styleId="districtCode" onchange="getDataList('BP','0','')"  >
					<html:option value=""><bean:message key="label.selectDistrict" /></html:option>
					<logic:present name="districtList">
						 <html:options collection="districtList" property="propertyCode" labelProperty="propertyValue"/>
					 </logic:present>
					</html:select></td>
				</tr>
				  
				<tr>
					<th><bean:message key="lable.user.selectBlockPanchayat"/><span class="mandatory">*</span></th>
					<td>						
						<html:select property="blockCode" styleId="blockCode" onchange="getUsers('4',this)">
						<html:option value=""><bean:message key="label.selectBlock" /></html:option>						
						<logic:present name="blockList">
					    	<html:options collection="blockList" property="propertyCode" labelProperty="propertyValue"/>
					    </logic:present>
						</html:select>					
					</td>
				</tr> 
				</logic:present>
				 
				<tr>
					<th><bean:message key="lable.user.loginID"/><span class="mandatory">*</span></th>
					<td ><html:select property="loginId" styleId="loginId" onchange="resetList();searchUser(this.form);">
					<html:option value=""><bean:message key="select.label" /></html:option>						
					<logic:present name="userList">
						 <html:options collection="userList" property="propertyCode" labelProperty="propertyValue"/>
					 </logic:present>				
					</html:select></td>
				</tr>
				<tr  class="hide">				
					<th> <span class="mandatory">*</span></th>
					<td >
						<html:select property="notAvailableUserCode" styleId="notAvailableUserCodeID" >
						</html:select></td>
				</tr>
				<%-- <tr>
					<td height="40" colspan="2" align="center">					
					<html:button styleClass="button" property="Search" value="Submit" onclick="searchUser(this.form)"/>
					<logic:notEqual name="searchSuccess" value="searchSuccess">
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					</logic:notEqual></td>
				</tr> --%>
			</table>
			<br/>
			<div align="center">
					<%-- <html:button styleClass="button" property="Search" value="Submit" onclick="searchUser(this.form)"/>--%>
					<logic:notEqual name="searchSuccess" value="searchSuccess"> 
					<input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
					</logic:notEqual>
			</div>
			
			
			<logic:equal name="searchSuccess" value="searchSuccess">
				<table id="roleListRow" class="inputTBL" width="90%" align="center">
					
					<tr>
					<th><bean:message key="lable.user.userName"/><span class="mandatory">*</span></th>
					<td ><html:text property="userName" styleId="userName" onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
						<th><bean:message key="lable.user.designation"/><span class="mandatory">*</span></th>
						<td ><html:text property="userDesignation" styleId="userDesignation" onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<th><bean:message key="lable.user.emailID"/><span class="mandatory">*</span>
					<font size="1" color="blue">(NIC mail id is preferable.Don't use yahoo mail id)</font>
					</th>
					<td ><html:text property="emailId" styleId="emailId" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase(),echeck(this.value);"/></td>
					</tr>
					<tr>
						<th>Mobile <font color="blue" size="1">(10 digit
										Only)</font></th>
						<td>+91 - <html:text property="mobile" styleId="mobile"
										size="10" maxlength="10" /> <br />
								<font color="red" size="1"><html:errors property="mobile" />
								</font></td>				
					</tr>
					<tr>
						<th><bean:message key="lable.user.accountStatus"/></th>
						<td ><html:select property="activeFlag" size="1">
							<html:option value="Y"><bean:message key="lable.open" /></html:option>
							<html:option value="B"><bean:message key="lable.blocked" /></html:option>
							<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
						</html:select></td>
					</tr>				
					
					<%-- <tr>
						<td height="40" colspan="2" align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>										
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
						</td>
					</tr> --%>
				</table>
				<div align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>										
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
				</div>
			</logic:equal>
			
			<logic:equal name="searchSuccess" value="<%=Constants.SEARCH_SUCCESS_OPEN%>">
				<table id="roleListRow" class="inputTBL" width="90%" align="center">	
					<tr>
					<th><bean:message key="lable.user.userName"/><span class="mandatory">*</span></th>
					<td><html:text property="userName" styleId="userName" readonly="true" /></td>
					</tr>
					
					<tr>
					<th><bean:message key="lable.user.designation"/><span class="mandatory">*</span></th>
					<td ><html:text property="userDesignation" styleId="userDesignation" onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<th><bean:message key="lable.user.emailID"/><span class="mandatory">*</span>
					<font size="1" color="blue">(NIC mail id is preferable.Don't use yahoo mail id)</font>
					</th>
					<td ><html:text property="emailId"  styleId="emailId" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					<tr>
						<th>Mobile <font color="blue" size="1">(10 digit Only)</font></th>
						<td>+91 - <html:text property="mobile" styleId="mobile" size="10" maxlength="10" /> <br />
								<font color="red" size="1"><html:errors property="mobile"/>
								</font></td>				
					</tr>
					<tr>
						<th><bean:message key="lable.user.accountStatus" /></th>
						<td ><html:select property="activeFlag" styleId="activeFlag" size="1">
							<html:option value="Y"><bean:message key="lable.open" /></html:option>
							<html:option value="B"><bean:message key="lable.blocked" /></html:option>
							<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
						</html:select></td>
					</tr>				
				</table>
				<div align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>											
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
				</div>
			</logic:equal>
			
			<logic:equal name="searchSuccess" value="<%=Constants.SEARCH_SUCCESS_NOT_AUTHORISED%>">
				<table id="roleListRow" class="inputTBL" width="90%" align="center">
					
					<tr>
					<th><bean:message key="lable.user.userName"/><span class="mandatory">*</span></th>
					<td ><html:text property="userName" styleId="userName"  onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<th><bean:message key="lable.user.designation"/><span class="mandatory">*</span></th>
					<td ><html:text property="userDesignation" styleId="userDesignation"  onblur="this.value=this.value.toUpperCase();"/></td>
					</tr>
					
					<tr>
					<th><bean:message key="lable.user.emailID"/><span class="mandatory">*</span>
					<font size="1" color="blue">(NIC mail id is preferable.Don't use yahoo mail id)</font>
					</th>
					<td ><html:text property="emailId" styleId="emailId" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase(),echeck(this.value);"/></td>
					</tr>
					<tr>
						<th>Mobile <font color="blue" size="1">(10 digit Only)</font></th>
						<td>+91 - <html:text property="mobile" styleId="mobile"
										size="10" maxlength="10" /> <br />
								<font color="red" size="1"><html:errors property="mobile" />
								</font></td>				
					</tr>
					<tr>
						<th><bean:message key="lable.user.accountStatus"/></th>
						<td ><html:select property="activeFlag" size="1">
							<html:option value="Y"><bean:message key="lable.open" /></html:option>
							<html:option value="N"><bean:message key="lable.notauthorised" /></html:option>
						</html:select></td>
					</tr>									
					<%-- <tr>
						<td height="40" colspan="2" align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>											
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/></td>
					</tr> --%>
				</table>
				<div align="center">
						<html:button styleClass="button" property="next" value="Update" onclick="updateUser(this.form)"/>											
					    <input name="Button" type="button" class="button" value="Close" onclick="closePage()"/>
				</div>
			</logic:equal>
			
			 <%-- <logic:equal name="searchSuccess" value="searchFail">
				<table width="90%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td  align="center"><bean:message key="search.fail" /></td>
					</tr>
				</table>
			</logic:equal> --%></td>
		</tr>
		<html:hidden property="hiddenStateCode"></html:hidden>
		<html:hidden property="hiddenDistrictCode"></html:hidden>
		<html:hidden property="hiddenBlockCode"></html:hidden>
 
		<html:hidden property="hiddenLoginId"></html:hidden>
			
	</table>
	
	
	<table  id="labelTable"  class="hide" align="center" border="0">
	<tr>
	<td width="33%" class="formtitle"><bean:message key="lable.user.levelofuser"/></td>
	<td width="44%" class="formtitle"><bean:message key="lable.user.loginID"/></td>
	<td width="44%" class="formtitle"><bean:message key="lable.user.accountStatus"/></td>
	</tr>
	
	</table>
	<table id="infraTable" border="0" class="hide" align="center">
	</table>
	
	</div>
</html:form>
<script type="text/javascript" src="javaScript/informationDialog.js"></script>
</body>
<script>
//hideShowField();
if((document.getElementById('hiddenStateCode') != null) && (document.getElementById('hiddenStateCode').value!="")){
 getStateOnReloadOfPage();
}
</script>
</html:html>
