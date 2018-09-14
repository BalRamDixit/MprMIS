
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<%
	ServletContext context = getServletContext();
%>

<script language="javascript" type="text/javascript"
	src="javaScript/dhtmlgoodies_calendar.js"></script>
<script language="javascript" type="text/javascript"
	src="javaScript/datetimepicker.js"></script>
<script language="javascript" src="javaScript/choosedate.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>
<link type="text/css" rel="stylesheet"
	href="css/dhtmlgoodies_calendar.css"></link>
<script language="javascript">
  
  var tokenParameter='reqtrack';
  var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

	function closePage() {
		var status = window.confirm('<bean:message key="alert.close.form" />');
		if (status == true) {
			document.forms[0].action = "login.do?methodName=closePage" + "&"
					+ tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}
	
	function saveUser() {
		var errorList = "";
		var arg = "";
		var error = false;
		var Value = "";

		if (document.forms[0].circularScheme.value == "") {
			arg = 'Circular Scheme ';
			errorList = '<bean:message key="errors.required"  arg0="'+arg+ '" />'
					+ '\n';
			document.getElementById("circode").focus();
			alert(errorList);
			document.getElementById("circode").style.backgroundColor = "yellow";
			return;
		}
		if (document.forms[0].uploadCircularName.value == "") {

			arg = 'Circular Description ';
			errorList = '<bean:message key="errors.required"  arg0="'+arg+ '" />'
					+ '\n';
			document.getElementById("upCirNameId").focus();
			alert(errorList);

			document.getElementById("upCirNameId").style.backgroundColor = "yellow";
			return;
		}

		if (document.forms[0].uploadCircularDate.value == "") {
			arg = 'Circular Date ';
			errorList = '<bean:message key="errors.required"  arg0="'+arg+ '" />'
					+ '\n';
			document.getElementById("upCirDateId").focus();
			alert(errorList);

			document.getElementById("upCirDateId").style.backgroundColor = "yellow";
			return;
		}

		if (document.forms[0].meetingAgendaFileEnglish.value == "") {
			arg = 'Upload Circular File ';
			errorList = '<bean:message key="errors.required"  arg0="'+arg+ '" /> \n';
			document.getElementById("upCirFileEnglishId").focus();
			alert(errorList);
			document.getElementById("upCirFileEnglishId").style.backgroundColor = "yellow";
			return;

		}
		
		/*if(document.forms[0].meetingAgendaFileHindi.value == "") {
			arg = 'Upload Circular File';
			errorList = '<bean:message key="errors.required" arg0="'+arg+'"/> \n';
			alert(errorList);
			document.getElementById("upCirFileHindiId").style.backgroundColor = "yellow";
			return;
		}*/
		document.forms[0].action = "uploadCircular.do?method=save" + "&" + tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
	
	function clearPage() {
		var status = window.confirm('<bean:message key="msg.clear" />');
		if (status == true) {
			document.forms[0].circularScheme.value = "";
			document.forms[0].uploadCircularName.value = "";
			document.forms[0].uploadCircularDate.value = "";
			document.forms[0].meetingAgendaFile.value = null;
			document.forms[0].action = "uploadCircular.do?method=showAdd" + "&"
					+ tokenParameter + "=" + tokenValue;

			document.forms[0].submit();

		}
	}

	function checkScheme(val) {
		var scheme = val.value;
		var count1 = 0;
		if (document.getElementsByName("circularScheme").length > 0) {
			for ( var i = 0; i < document.getElementsByName("circularScheme").length; i++) {
				if (document.getElementsByName("circularScheme")[i].value == scheme) {
					count1 = count1 + 1;
				}
			}
		}

		var arg1 = "Scheme Name";
		var errorList = "<bean:message key="error.selected" arg0='"+arg1+"'  />"
				+ '\n';

		if (count1 > 1) {
			alert("2");
			alert(errorList);
			val.selectedIndex = 0;
			return false;
		}

	}
	function addScheme() {
		if (document.forms[0].otherCircular.value != "") {
			var val = document.forms[0].otherCircular.value;
			var scheme = document.forms[0].otherCircular.value;
			var count1 = 0;
			if (document.getElementsByName("circularScheme").length > 0) {
				//alert('circularScheme LEN=='+document.forms[0].circularScheme.length);
				for ( var i = 0; i < document.forms[0].circularScheme.length; i++) {
					//alert('circularScheme==='+document.forms[0].circularScheme[i].value);
					if (document.forms[0].circularScheme[i].value == scheme) {
						count1 = count1 + 1;
					}
				}
			}
			// alert('count1==='+count1)
			var arg1 = "Scheme Name";
			var errorList = "<bean:message key="error.selected" arg0='"+arg1+"'  />"
					+ '\n';

			if (count1 > 1) {
				alert(errorList);
				val.selectedIndex = 0;
				return false;
			} else {

				document.forms[0].action = "uploadCircular.do?method=addScheme"
						+ "&" + tokenParameter + "=" + tokenValue;

				document.forms[0].submit();

			}
		}

		else {
			alert("Please specify the Circular Scheme !!! ");
			document.getElementById('othercir').style.backgroundColor = "yellow";
		}
	}

	function showOther() {
		if (document.forms[0].circularScheme.value == "other") {
			document.forms[0].otherCircular.value = "";
			document.getElementById('other').className = "";
		} else {
			document.getElementById('other').className = "hide";
		}
	}
</script>

<body>
<html:form action="login/uploadCircular" method="post" enctype="multipart/form-data">
<div class="mainpane">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="pageHeader" width="100%" align="center"><bean:message
							key="label.add"></bean:message> <bean:message
							key="label.sgsyuploadcircular"></bean:message></td>
		</tr>
		<tr><td>	
			<table width="90%" align="center" class="inputTBL" >
				<tr id="shemeRow">
					<th>
						<bean:message key="label.SelectSchemeCircular" /><span class="mandatory">*</span>
					</th>
					<td>
						<html:select property="circularScheme" styleId="circode" onchange="showOther()">
							<html:option value="">Select</html:option>
							<logic:present name="schemeList">
								<html:options collection="schemeList" property="circularScheme" labelProperty="circularScheme" />
								<html:option value="other">Others</html:option>
							</logic:present>
						</html:select>
					</td>
				</tr>
				<tr id="other" class="hide">
					<th width="30%"><bean:message
							key="label.ifOtherCircular" /></th>
					<td>
						<html:text property="otherCircular" styleId="othercir" onblur="this.value=this.value.toUpperCase();" />
						<html:button styleClass="button" property="next" value="Add" onclick="addScheme()"></html:button></td>
				</tr>

				<tr>
					<th width="30%">
						<bean:message key="label.uploadCirDes" /> 
						<span class="mandatory">*</span></th>
					<td>
						<html:textarea property="uploadCircularName" styleId="upCirNameId" styleClass="textArea" />
					</td>
				</tr>
				<tr>
					<th width="30%"><bean:message
							key="label.CirDate" /> <span class="mandatory">*</span></th>
					<td><html:text property="uploadCircularDate"
							size="10" readonly="true" styleId="upCirDateId"
							onclick="javascript:displayCalendar(toDate,'dd-mm-yyyy',this)" />
						<img align="middle" title="Click here to select date"
						src="images/calendar.gif"
						onclick="javascript:displayCalendar(uploadCircularDate,'dd-mm-yyyy',this)">
						 <font color="red" size="1"><html:errors
								property="uploadCircularDate" />
					</font></td>

				</tr>


				<tr>
					<th rowspan="2" width="30%">
						<bean:message key="label.uploadfile"/> 
						<span class="mandatory">*</span></th>
					<td>
						<span class="formsubheading"><bean:message key="label.english"/></span>
						<html:file property="meetingAgendaFileEnglish" styleId="upCirFileEnglishId" maxlength="40"></html:file>
					</td>
				</tr>
			<tr>
			<td>
				<span class="formsubheading"><bean:message key="label.hindi"/></span>
						<html:file property="meetingAgendaFileHindi" style="upCirFileHindiId" maxlength="40"></html:file>
			</td>
			</tr>
				



			</table></td></tr>
			<tr>
				<td colspan="2" align="center"><html:button
							styleClass="button" property="next" value="Save"
							onclick="saveUser()"></html:button> <input name="Button"
						type="button" class="button" value="Clear" onclick="clearPage()" />
						<input name="Button" type="button" class="button" value="Close"
						onclick="closePage()" />
					</td>
				</tr>
			</table>
		</div>
	</html:form>
</body>
