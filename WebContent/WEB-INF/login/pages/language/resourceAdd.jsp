 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page import="java.util.*"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%ArrayList vvv=(ArrayList)request.getAttribute("allResourceList") ; %>
<!DOCTYPE htm PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javaScript/md5.js"></script>
  
<!--script for menu-->
<script type="text/javascript">
  
 function selectResource()
 {
 		if(document.forms[0].languageShortName.value!=""){
	 		document.languageForm.action ="language.do?methodName=getResources&paging=page"+"&"+tokenParameter+"="+tokenValue;
			document.languageForm.submit();
 		}
 }  
function save(){

  if(document.forms[0].languageShortName.value==""){
 		 var arg="<bean:message key="label.languageName"/>";
		 var check='<bean:message key="errors.required"  arg0="'+arg+ '" />';
			alert(check);
		return false;
  }
  	    var status=window.confirm("<bean:message key="alert.submit.confirm"/>");
		if(status==true){	
	 		document.languageForm.action ="language.do?methodName=showAllLanguage&paging=page"+"&"+tokenParameter+"="+tokenValue;
			document.languageForm.submit();
		}
     
 }

 
function closePage()
{
	//var status=window.confirm("Are you sure you want to Close ");
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.languageForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
	document.languageForm.submit();
	}
}

function clearField()
{
	//var status=window.confirm("Are you sure you want to Clear the Fields ");
	 var status=window.confirm("<bean:message key="msg.clearForm"/>");
	if(status==true){
	
	document.languageForm.language.value="";
	document.languageForm.languageShortName.value=""; 
	}
	
}
 

</script>
 
<!--Main form section starts here-->
<body >
<html:form action="/login/language.do">

	<table width="100%" >
		 
	<logic:notEmpty name="LanguageList">						
		<tr>
			<td>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr align="center">
					<td align="center" class="actionstxt">
					<span class="actionstxt"><bean:message  key="label.modify"/></span>
					<bean:message  key="label.languagePack" /></td>
					<html:hidden property="informationDialog"></html:hidden>
					<html:hidden property="informationDialogText"></html:hidden>
					<html:hidden property="informationDialogHeader"></html:hidden>
				</tr>
              	<tr>
                      <td height="20" class="formlabel"><span class="mandatory">*</span>
                      <span class="normaltext"><bean:message  key="lable.field.manadatory" /></span></td>
                </tr>
             </table>
           
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    
                    <tr>
                      <td width="50%" class="formlabel"><bean:message  key="label.languageName" /><span class="mandatory">*</span></td>
                      <td class="formlabel"><html:select property="languageShortName"  onchange="selectResource()"> 
                      	<html:option value=""><bean:message key="label.select"/></html:option>
                     	<logic:present name="LanguageList">
						 	<html:options collection="LanguageList" property="languageshortname" labelProperty="language"/>
						</logic:present>
						</html:select>
					 
                      </td>
                    </tr>
               </table>
         
            
           <logic:notEmpty name="allResourceList">
            <logic:present name="allResourceList">
            <%int cnt=0; %>
            
                <table width="100%" border="0" cellpadding="0" cellspacing="0" >
	                	<display:table class="formlabel" id="List" name="allResourceList" requestURI="/language.do" pagesize="25" cellpadding="0" cellspacing="0" >
				 			<%cnt++; %>
				 			<display:column style="width:33%;text-align:center"  class="formlabel"   sortable="true" title="Srno"   ><%=cnt%></display:column>
				 			 <display:setProperty  name="resourceid" value="resourceid"/>
				 			<display:column style="width:33%;text-align:center" class="formlabel" property="resourcenameenglish" title="Resource Name" sortable="true"   />
				 			<display:column style="width:33%;text-align:center" class="formlabel"  property="resourceName1" title="Local Resource Name" sortable="true"   />
				       		
				        </display:table>
               </table>
             </logic:present>
             </logic:notEmpty>
              <logic:notEmpty name="LanguageList"></logic:notEmpty>
             <table width="100%" border="0" cellpadding="0" cellspacing="0">
                   
                    <tr>
                      <td height="40" colspan="2" align="center" >
                      <html:button styleClass="button" property="saveButton" onclick="save()"><bean:message key="button.save" /></html:button>
                      <html:button styleClass="button" property="clearButton" onclick="clearField()"><bean:message  key="button.clear" /></html:button>
                      <html:button styleClass="button" property="closeButton" onclick="closePage()" ><bean:message  key="button.close" /></html:button>
                      </td>
                    </tr>
                    
			</table>
			
			</td>
		</tr>
		</logic:notEmpty>
	</table>
	
</html:form>
</body>
<!--Main form section ends here-->