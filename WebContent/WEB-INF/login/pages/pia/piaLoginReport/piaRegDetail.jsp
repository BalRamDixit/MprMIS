<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

 function closePage(){
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.forms[0].action = "piaReportAction.do?methodName=showPage&" + tokenParameter + "=" + tokenValue;;
	document.forms[0].submit();
	}
} 

function showData(){
	document.forms[0].action = "piaReportAction.do?methodName=piaRegStatusInner&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}

function getDetail(pid){
	document.forms[0].action = "piaReportAction.do?methodName=getPiaDetail&pid="+pid+"&" + tokenParameter + "=" + tokenValue;
	document.forms[0].submit();
}
</script>
<html:form action="/login/piaReportAction"> 

<table width="100%" align="center" style="padding-down: 7px;">
	<tr>
		<th colspan="2" align="center" class="pageHeader">Applicant Organisation Verification</th>
	</tr>
	<tr>
	<th width="50%"><label class="label-info">Select Applicant Organisation Status </label></th>
	<td>
		<html:select property="piaReportStatus" styleId="piaReportStatus" onchange="javascript:showData();">
			<html:option value="">Select</html:option>
			<html:option value="A">All</html:option>
			<html:option value="S">Submitted</html:option>
			<html:option value="C">Checked</html:option>
			<html:option value="V">Verified</html:option>
			<html:option value="R">Rejected</html:option>
			<html:option value="P">Incomplete</html:option>
			<html:option value="PW">Withdrawn</html:option>
			<html:option value="PD">Debarred</html:option>
			<html:option value="PB">Blacklisted</html:option>
		</html:select>
	</td>
	</tr>
<tr><td colspan="2">
<table width="99%" class="bordered" style="overflow: scroll;">
<!-- VERIFIED -->
 <tr>
       <th width="20px;">Sr. No.</th>
       <th width="55%;">Applicant Organisation</th>
       <th width="40%">Authorized Person</th>       
 </tr>

<logic:present name="piaList">
 <logic:iterate id="list" name="piaList" indexId="index">
  <tr>
	 <td>${index+1 }</td>
	 <td><table>
	 		<tr><th><span class="text-primary" >Applicant Organisation Name</span></th><td> ${list[1]}
	 		<br/>(<a href="javascript:getDetail('${list[0]}');" >View Complete Details</a>)</td></tr>
	     	<tr><th><span class="text-primary" >Applicant Organisation Address</span></th><td>${list[2] }</td></tr>
	     	<tr><th><span class="text-primary" >Applicant Organisation Contact</span></th><td><span class="text-primary" >Contact</span>: ${list[3] }<br/>
	                <span class="text-primary" >Email-Id</span>: ${list[4] }</td></tr>
	     </table>
	 </td>
	
	 <td>
	  <c:if test="${list[5] !='' }" ><span class="text-primary" >Name</span>: ${list[5] }<br/>
	     <span class="text-primary" >Contact</span>: ${list[6] } <br/>
	     <span class="text-primary" >Email-Id</span>:  ${list[7]}
	  </c:if>
	  <c:if test="${list[5]=='' }"><span class="text-primary" >No Authorized Person Found</span></c:if>
	 </td>
  </tr>
 </logic:iterate>
 </logic:present>
 <logic:notPresent name="piaList">
  <tr>
	 <td colspan="8">No record found</td>	 
  </tr>
 </logic:notPresent>
 </table>
 </td>
 </tr></table>
<div align="center" style="padding-top: 15px; padding-bottom: 10px;"> 
	<html:button  styleClass="defaultBtn" property="" onclick="closePage()">Close</html:button>
</div>

</html:form>