<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
body{
font-family:Arial, Helvetica, sans-serif; 
font-size:13px;
}
.success, .warning, .error, .validation {
border: 1px solid;
margin: 10px 0px;
padding:15px 10px 15px 50px;
background-repeat: no-repeat;
background-position: 10px center;
}
.info {
color: #00529B;
background-color: #BDE5F8;
font-weight:bolder;
font-size:larger;
background-image: url('info.png');
}
.success {
color: #4F8A10;
background-color: #DFF2BF;
font-weight:bolder;
font-size:larger;
font-family:sans-serif;
background-image:url('success.png');
}
.warning {
color: #9F6000;
background-color: #FEEFB3;
background-image: url('warning.png');
}
.error {
color: #D8000C;
background-color: #FFBABA;
background-image: url('error.png');
}

</style>

<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function showData() {
	document.forms[0].action = "verifyPiaAction.do?methodName=getPiaDetail&page=categoryChange&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.forms[0].action = "verifyPiaAction.do?methodName=showCategoryChangePage" + "&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
}

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
}

function showName(id) {
	//alert('${piaDetail.piaCode}');
	var xmlHttpRequest = getXMLHttpRequest();
	
	xmlHttpRequest.onreadystatechange = function()
	 {
		if (xmlHttpRequest.readyState == 4) 
			{
			if (xmlHttpRequest.status == 200) 
				{
				if (xmlHttpRequest.responseText != "") 
				{
					document.getElementById("showPRN").style.display = "";
					document.getElementById("FinalButton").style.display = "";	
					document.getElementById("NEWPRNID").innerHTML = xmlHttpRequest.responseText;
				}
			}
			 else
			{
				document.getElementById("NEWPRNID").innerHTML = '';
			}
		}
	};

	xmlHttpRequest.open("POST","verifyPiaAction.do?methodName=getModifiedPRN",true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	xmlHttpRequest.send("categoryCode=" +id+"&trn="+'${piaDetail.piaCode}');
	//alert("hello");

}

function modifyPage(){
	//alert("inside");
	    var piaCategorycode = document.forms[0];
	    var txt = "";
	    var i;
	    for (i = 0; i < piaCategorycode.length; i++) {
	        if (piaCategorycode[i].checked) {
	            txt =piaCategorycode[i].value;
	        }
	    }
	 // alert(  document.getElementById("categoryCode").value = "You ordered a coffee with: " + txt);	
	//alert(document.forms[0].categoryCode.value); 
	///alert(document.getElementById("categoryCode").value);
	var categoryCode=txt;
	//alert(categoryCode);
	/* var validationCheck = validate();
	if(validationCheck){ */
	 var status=window.confirm("<bean:message key="alert.submit.confirm" />");
	 	if(status==true){ 
			document.forms[0].action = "verifyPiaAction.do?methodName=modifyPiaCategoryAndPRN&categoryCode="+categoryCode+"&trn="+'${piaDetail.piaCode}'+"&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		 }  
	 	/* }  */
} 

document.getElementById('realtxt').onkeyup = searchSel;
function searchSel() 
    {
      var input = document.getElementById('realtxt').value.toLowerCase();
       
          len = input.length;
          output = document.getElementById('piaCode').options;
      for(var i=0; i<output.length; i++)
          if (output[i].text.toLowerCase().indexOf(input) != -1 ){
          output[i].selected = true;
              break;
          }
      if (input == '')
        output[0].selected = true;
    }

function hideDetail() {
	 document.getElementById("detail").style.display="none";
}

</script>

<html:form action="/login/verifyPiaAction"> 
<div   class="outerTBL">

<table width="100%" align="center" style="padding-down: 7px;" class="piaLogTbl">
	<tr>
		<th colspan="2" align="center" class="pageHeader">Verified Applicant Organisation Category Change</th>
	</tr>
<logic:present name="Notification"> 
 <div style="width: 70%; padding-left: 75px;border: solid #ccc 1px; -moz-border-radius: 6px; -webkit-border-radius: 6px; border-radius: 6px; -webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; box-shadow: 0 1px 1px #ccc; margin: 10px 50px 5px 50px; padding: 15px 10px 15px 50px; background-repeat: no-repeat;  background-position: 10px center; color: #B22222; background-color: #9ACD32; text-transform: uppercase; background-image: url(../images/tick.png);size: 40px;">${Notification}
</div>
 </logic:present>
	<tr>
	<logic:empty name="piaList">
		<td><label class="label-info">NO NEW VERIFIED APPLICANT ORGANISATION FOR CATEGORY CHANGE IS FOUND IN OUR DATA BASE</label></td>
	</logic:empty>
	<logic:notEmpty name="piaList">
	<th width="50%"><label class="label-info">Applicant Organisation List for Category Change</label></th>
	<td align="center">
	<b><label class="label-info"><font color="#fff">SEARCH</font></label> </b> <input type="text" id="realtxt" onkeyup="javascript:searchSel();" onkeydown="hideDetail();"/><br/>
		<html:select property="piaCode" name="verifyPiaForm" styleId="piaCode" onchange="hideDetail();">
			<html:option value="">Select</html:option>
			<logic:present name="piaList">
				<logic:iterate id="pia" name="piaList">
					<html:option value="${pia.piaCode}" >${pia.piaCode} - ${pia.piaName}</html:option>
				</logic:iterate>
			</logic:present>
		</html:select>
	</td>
	</logic:notEmpty>
	</tr>
	<tr>
		<td colspan="2" align="center">
 	<html:button  styleClass="mybtn" property="" onclick="showData();">Show</html:button>
 	</td>     
	</tr>
</table>

<div id="detail">
<logic:present name="piaDetail" >
<html:hidden property="email" name="piaDetail"></html:hidden>
<html:hidden property="piaName" name="piaDetail"></html:hidden>
<html:hidden property="registrationNumber" name="piaDetail"></html:hidden>
<html:hidden property="address" name="piaDetail"></html:hidden>
<html:hidden property="panNo" name="piaDetail"></html:hidden>
<html:hidden property="tanNo" name="piaDetail"></html:hidden>
<html:hidden property="categoryName" name="piaDetail"></html:hidden>
<table width="100%" class="inputTBL">

<tr style="padding-top: 7px;">
	<td width="40%" ><label class="label-important">Applicant Organisation Temporary Reference Number</label></td>
 	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="piaCode"/></span></h3>
 	  </td>
</tr>
<tr style="padding-top: 7px;">
	<td width="40%" ><label class="label-important">Applicant Organisation Permanent Registration Number</label></td>
 	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="piaRegistrationNo"/></span></h3></td>
</tr>
<tr>
	<td style="padding-top: 7px;"><label class="label-important">Present Category of Applicant</label></td>
	<td><h3><span class="text-primary"><bean:write name="piaDetail" property="categoryName"/></span></h3></td>
</tr>
</table>


		


<table width="100%" align="center" class="inputTBL" style="padding-top: 7px;">
<tr><td width="40%" ><label class="label-important">Available Category of Applicant</label></td>
	<td><logic:present name="piaCategory">						
							<logic:iterate id="category" name="piaCategory"><br/>
									<html:radio property="categoryCode" name="piaDetail" styleId="categoryCode" value="${category.categoryId}" onclick="javascript:showName(${category.categoryId});">
											&nbsp;<bean:write name="category" property="categoryName" />
									</html:radio>								
							</logic:iterate>
		</logic:present>
		</td>
</tr>
<tr id="showPRN" style="display: none">
<td width="40%" ><div class="info">Modified Permanent Registration Number</div></td>
<td><div class="success" id="NEWPRNID"></div></td>
</table>

<div align="center" style="padding-top: 15px; padding-bottom: 10px;display: none" id="FinalButton" >
 	<html:button  styleClass="defaultBtn" property="" onclick="modifyPage()">Modify</html:button> 
    <html:button  styleClass="defaultBtn" property="" onclick="closePage()">Back</html:button> 
</div>

</logic:present>
</div>
</div>
</html:form>