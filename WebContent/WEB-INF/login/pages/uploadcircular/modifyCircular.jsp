<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" type="text/javascript"
	src="javaScript/dhtmlgoodies_calendar.js"></script>
<script language="javascript" type="text/javascript"
	src="javaScript/datetimepicker.js"></script>
<script language="javascript" src="javaScript/choosedate.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>
<link type="text/css" rel="stylesheet"
	href="css/dhtmlgoodies_calendar.css"></link>
	
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';
	function submitForm() {
		document.forms[0].action = "uploadCircular.do?method=modify&desc="
				+ document.getElementById("uploadCircularDescription").value
				+ "&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}

	function getCircularDescription() {
		document.forms[0].action = "uploadCircular.do?method=showModify&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}

	function showData() {
		document.forms[0].action = "uploadCircular.do?method=showModify&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
</script>
</head>
<body>
<html:form action="login/uploadCircular" method="post" enctype="multipart/form-data">
<table width="100%">
<tr>
	<td colspan="2" class="pageHeader" align="center">
	<bean:message key="label.modify" />
	<bean:message key="label.sgsyuploadcircular" />
	</td>
</tr>
<tr><td align="center">
<table class="inputTBL" align="center" width="90%">
<tr>
	<th width="30%"><bean:message key="label.SelectSchemeCircular" /></th>
	<td><html:select property="circularScheme" onchange="getCircularDescription()">
		<html:option value="">--Select--</html:option>
		<html:options collection="schemeList"
				labelProperty="circularScheme" property="circularScheme" />
		</html:select>
	</td>
</tr>
<logic:present name="circularDescription" scope="request">
<tr>
	<th><bean:message key="label.uploadCirDes" /></th>
	<td><html:select property="uploadCircularName" onchange="showData()">
			<html:option value="">--Select--</html:option>
			<html:options collection="circularDescription"
				labelProperty="uploadCircularName" property="uploadCircularName" />
		</html:select></td>
</tr>
</logic:present>
</table></td></tr>
<tr><td>
<table>
<logic:equal name="show" value="true" scope="request">
<tr>
	<td><bean:message key="label.uploadCirDes" /> <span class="mandatory">*</span> <html:hidden
							name="uploadCircularForm" property="id" /> <html:hidden
							name="uploadCircularForm" property="show" /></td>
	<td><html:textarea styleId="uploadCircularDescription" property="uploadCircularName"
				styleClass="textArea" /></td>
</tr>
<tr>
	<td><bean:message key="label.CirDate" /><span class="mandatory">*</span></td>
	<td><html:text name="uploadCircularForm" property="uploadCircularDate" size="10"
			readonly="true" styleId="upCirDateId" onclick="javascript:displayCalendar(toDate,'dd-mm-yyyy',this)" />
		<img align="middle" title="Click here to select date" src="images/calendar.gif"
						onclick="javascript:displayCalendar(document.forms[0].uploadCircularDate,'dd-mm-yyyy',this)">
					</td>
				</tr>

	<tr>
					<td><bean:message
							key="label.uploadfile" /></td>
					<td>
						<div>
							<span class="formsubheading"><bean:message
									key="label.english" />
							</span>
							<html:file property="meetingAgendaFileEnglish" />
							<span class="formsubheading"> <bean:write
									name="uploadCircularForm" property="uploadCircularFileName" />
								<html:hidden name="uploadCircularForm"
									property="uploadCircularFileName" /> </span> <br /> <span
								class="formsubheading"><bean:message key="label.hindi" />
							</span>
							<html:file property="meetingAgendaFileHindi" />
							<logic:notEmpty name="uploadCircularForm"
								property="uploadCircularFileNameHindi">
								<span class="formsubheading"> <bean:write
										name="uploadCircularForm"
										property="uploadCircularFileNameHindi" /> <html:hidden
										name="uploadCircularForm"
										property="uploadCircularFileNameHindi" /> </span>
							</logic:notEmpty>
							<logic:empty name="uploadCircularForm"
								property="uploadCircularFileNameHindi">
								<span class="formsubheading">No File Exists</span>
							</logic:empty>
						</div></td>
	</tr>
	</logic:equal>
</table>
</td></tr>
<tr>
	<td align="center">
	<html:button property="" onclick="submitForm()" styleClass="button">
		<bean:message key="button.modify" /></html:button> 
	<html:button property="" onclick="submitForm()" styleClass="button">
		<bean:message key="button.close" /></html:button>
	</td>
</tr>

</table>
</html:form>
</body>
</html>