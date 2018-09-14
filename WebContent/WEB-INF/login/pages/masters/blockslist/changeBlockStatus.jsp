<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<head>
	<script type="text/javascript">
		var tokenParameter='reqtrack';
		var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';
		
		function getBlocks() {
			document.forms[0].action = "blockIdentificationAction.do?methodName=view&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
		
		function updateBlockStatus() {
			document.forms[0].action = "blockIdentificationAction.do?methodName=updateBlockStatus&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
		
		function closePage(){
			var status = window.confirm('<bean:message key="alert.close.form" />');
			if(status==true){
			 	document.forms[0].action="login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;
			 	document.forms[0].submit();
			 }
		}
		
		function enableType(objID, index){
			if(document.getElementById(objID).value !="" ){
				document.getElementById("resourceType"+index).disabled = false;
				document.getElementById("mksp"+index).disabled = false;
				document.getElementById("livelihoodPilotsInnovations"+index).disabled = false;
				document.getElementById("ngoForImpNrlm"+index).disabled = false;
			}		
			else{
				document.getElementById("resourceType"+index).disabled = true;
				document.getElementById("mksp"+index).disabled = true;
				document.getElementById("livelihoodPilotsInnovations"+index).disabled = true;
				document.getElementById("ngoForImpNrlm"+index).disabled = true;
			}
		}
	</script>
</head>

<body>
<html:form action="login/blockIdentificationAction">

<table width="100%" align="center">
	<tr>
		<td colspan="2" class="pageHeader" align="center">
			<h3><bean:message key="label.updateBlockStatus"/></h3>
		</td>
	</tr>
	<tr style="padding-top: 20px; padding-bottom: 20px;">
		<td colspan="2" align="center" class="pageSubHeader">
			<b><i>Select <bean:message key="label.district"/></i></b> ---				
			<logic:present name="districtList" scope="request">
			<html:select property="districtCode" onchange="getBlocks()">
				<html:option value="">--Select--</html:option>
				<html:options collection="districtList" labelProperty="districtName" property="districtCode"></html:options>
			</html:select>
			</logic:present>
		</td>
	</tr>
	<tr>
		<td align="center">
			<logic:present name="status">
				<font color="green" size="2"><b>Message:</b> <bean:write name="status"/> </font>
			</logic:present>
		</td>
	</tr>
				
	<logic:present name="blockForm">
	<tr>
		<td align="center">
		<table width="90%" class="inputTBL" align="center">
		<tr>
			<th>S.No.</th>
			<th width="25%"><bean:message key="label.block"/></th>
			<th width="25%" align="center">Type</th>
			<th width="10%" align="center">Resource Block</th>
			<th width="10%" align="center">MKSP</th>
			<th width="10%" align="center">Other Livelihood<br/> Pilots Or Innovations </th>
			<th width="10%" align="center">Partnership <br/>With NGOs For Implementing<br/> NRLM Approach Adopted</th>
		</tr>
		
		<logic:iterate id="block"  name="blockForm" property="blockList" indexId="index">
		<c:set var="flag" value="false"/>
		<logic:equal  name="block" property="blockType" value="">
			<c:set var="flag" value="true"/>
		</logic:equal>
		
		<%-- <c:set var="flag" value="true"/>
		<c:if test="${block.blockType != '' }" >
			
		</c:if> --%>
		<tr>
			<td>${index+1}</td>
			<td>
				<bean:write name="block" property="blockName" />
				<html:hidden name="block" property="id" indexed="true"></html:hidden>
				<html:hidden name="block" property="entityCode" indexed="true"></html:hidden>
			</td>
			<td align="center">
				<html:select name="block" styleId="blockType${index}" property="blockType" onfocus="enableType(this.id, ${index});" onchange="enableType(this.id, ${index});" indexed="true">
					<html:option value="">-- SELECT --</html:option>
					<html:option value="NonI">NON-INTENSIVE</html:option>
					<html:option value="null" disabled="true" style="color:#fff; background-color:#5bb75b;">-- INTENSIVE --</html:option>
					<html:option value="NrlmI">NRLM INTENSIVE</html:option>
					<html:option value="NrlpI">NRLP INTENSIVE</html:option>
					<html:option value="OSrlpI">SRLP</html:option>
					<html:option value="OEarlpI">OTHER EARLP</html:option>
				</html:select>
			</td>
			<td><html:checkbox name="block" styleId="resourceType${index}" disabled="${flag}" property="resourceType" indexed="true"/></td>
			<td><html:checkbox name="block" styleId="mksp${index}" disabled="${flag}" property="mksp" indexed="true"/></td>
			<td><html:checkbox name="block" styleId="livelihoodPilotsInnovations${index}" disabled="${flag}" property="livelihoodPilotsInnovations" indexed="true"/></td>
			<td><html:checkbox name="block" styleId="ngoForImpNrlm${index}" disabled="${flag}" property="ngoForImpNrlm" indexed="true"/></td>
		</tr>
		</logic:iterate>
		</table>
	</td>
</tr>
<tr>	
	<td>
		<span class="normaltext"><b><u>NOTES</u></b></span><br/>
		<span class="normaltext">1. SRLM = State Rural Livelihoods Projects</span><br/>
		<span class="normaltext">2. EARLP = Externally Aided Rural Livelihoods Projects</span>
	</td>
</tr>		
<tr>
	<td colspan="2" align="center" class="formlabel">
		<html:button property="" onclick="updateBlockStatus()" styleClass="button" value="Submit"></html:button>
		<input name="Button" type="button" class="button" value="<bean:message  key="button.close"/>" onclick="closePage();"/></td>
</tr>	
</logic:present>

</table>
</html:form>
</body>

