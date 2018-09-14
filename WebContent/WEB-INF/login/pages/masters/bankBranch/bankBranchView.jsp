<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="javascript" src="javaScript/common.js"></script>


<script language="javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>'; 
window.onbeforeunload=confirmExit;

function confirmExit(){		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 

function getBankBranchList(){
		document.forms[0].action="bankBranch.do?methodName=showView&"+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
}
</script>


	 
<html:form action="login/bankBranch">
<table width="100%">
<tr>
	<td align="center" class="pageHeader" colspan="3"><bean:message key="title.viewbankbranch"></bean:message></td>
</tr>
<tr>
	<td align="center">
	<div align="left" class="note" style="width:90%;">
	<div style="width: 40%; float: left; padding-left: 10px;">
	<strong>Select Bank Level</strong>:&nbsp;
	<html:radio property="bankLevel" styleId="bankLevel" value="0" onchange="getBankBranchList()" >&nbsp;<strong>All</strong></html:radio>&nbsp;&nbsp;
	<html:radio property="bankLevel" styleId="bankLevel" value="1" onchange="getBankBranchList()" >&nbsp;<strong>National</strong></html:radio>&nbsp;&nbsp;
	<html:radio property="bankLevel" styleId="bankLevel" value="2" onchange="getBankBranchList()" >&nbsp;<strong>State</strong></html:radio>&nbsp;&nbsp;
	<html:radio property="bankLevel" styleId="bankLevel" value="3" onchange="getBankBranchList()" >&nbsp;<strong>District</strong></html:radio>&nbsp;&nbsp;
	</div>
	<div style="width:55%; float: right;"><strong>Bank Name</strong>:&nbsp;
	<html:select property="bankCode" styleId="bankCode" onchange="getBankBranchList()" >		
		<html:option value="">ALL BANK</html:option>
		<logic:present name="bankscoll">
		<logic:iterate id="bank" name="bankscoll">
		<html:option value="${bank.propertyCode}"><span style="font-size: 10px;">${bank.propertyValue}</span></html:option>
		</logic:iterate>
		<%-- <html:options  collection="bankscoll" property="propertyCode" labelProperty="propertyValue" /> --%>
		</logic:present>
	</html:select>
	</div>
	
	</div>
	</td>
</tr>

<tr><td colspan="3" align="center">
<table width="90%" align="center" class="reportTBL1">
	<tr>
		<th>Sl.No.</th>
		<th>Branch Level</th>
		<logic:equal value="M" name="LOGIN_LEVEL">
			<th>State Name</th>
			<th>District Name</th>
		</logic:equal>
		<logic:equal value="S" name="LOGIN_LEVEL">
			<th>District Name</th>
		</logic:equal>
		<th>Bank Name</th>
		<th>Branch Name</th>
		<th>IFSC Code</th>
		<th>Address</th>
	</tr>
	<logic:present name="VIEW_BRANCH_LIST">
	<logic:iterate id="branch" name="VIEW_BRANCH_LIST" indexId="index">
	<tr>
		<td>${index + 1}</td>
		<td>
			<logic:equal value="S" name="branch" property="branchLevel">STATE</logic:equal>
			<logic:equal value="D" name="branch" property="branchLevel">DISTRICT</logic:equal>
		</td>
		<logic:equal value="M" name="LOGIN_LEVEL">
			<td>${branch.stateName}</td>
			<td>${branch.districtName}</td>
		</logic:equal>
		<logic:equal value="S" name="LOGIN_LEVEL">
			<td>${branch.districtName}</td>
		</logic:equal>
		<td>${branch.bankName}</td>
		<td>${branch.bankBranchName}</td>
		<td>${branch.ifscCode}</td>
		<td>${branch.address}</td>
	</tr>
	</logic:iterate>
	</logic:present>
	<logic:empty name="VIEW_BRANCH_LIST">
	<tr>
		<td colspan="8">
			No Bank Branch Found In Our System. Contact To State Administrator.
		</td>
	</tr>
	</logic:empty>
	
</table>	
</td></tr>
<tr>
	<td colspan="3" align="center" >
	<html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button>  
	</td>
</tr> 
</table>
</html:form>