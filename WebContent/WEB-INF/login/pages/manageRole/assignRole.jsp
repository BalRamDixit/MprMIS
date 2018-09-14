<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<jsp:include page="/WEB-INF/login/pages/common/messages.jsp" />
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<script language=javascript>
	function save() {

		if (document.getElementById('levelCode').value == "") {
			alert("Please Select Level of User");
			return;
		}

		if (document.getElementById('loginId').value == "") {
			alert("Please Select loginId");
			return;
		}
		if (document.getElementById('levelCodeA').value == "") {
			alert("Please Select Level of  Role to be assigned");
			return;
		}
		if (document.getElementById('roleCode').value == "") {
			alert("Please Select Role Name");
			return;
		}
		if ((document.getElementById('stateRowRole').className != "hide")
				&& (document.getElementById('stateCodeRole').value == "")) {
			alert("Please Select State");
			return;
		}
		if ((document.getElementById('districtRowRole').className != "hide")
				&& (document.getElementById('districtCodeRole').value == "")) {
			alert("Please Select District");
			return;
		}
		if ((document.getElementById('blockRowRole').className != "hide")
				&& (document.getElementById('blockCodeRole').value == "")) {
			alert("Please Select block");
			return;
		}
		selectAll();
		if ((document.getElementById('stateRowMultiple').className != "hide")
				&& (document.getElementById('selectedStateM').value == "")) {
			alert("No Selected State, Please Select State");
			return;
		}
		if ((document.getElementById('districtRowMultiple').className != "hide")
				&& (document.getElementById('selectedDistrictM').value == "")) {
			alert("No Selected District, Please Select District");
			return;
		}

		if ((document.getElementById('blockRowMultiple').className != "hide")
				&& (document.getElementById('selectedBlockM').value == "")) {
			alert("No Selected Block, Please Select Block");
			return;
		}

		var roleCode = document.getElementById('roleCode').value;

		var status = window.confirm("Are you sure you want to submit ");
		if (status == true) {
			document.manageRoleForm.action = "manageRoleAction.do?methodName=save&"
					+ tokenParameter + "=" + tokenValue;

			document.manageRoleForm.submit();
		}
	}

	function getRole() {

		var levelCodeA = document.getElementById('levelCodeA').value;

		if (levelCodeA != '3' || levelCodeA != '4') {

			//alert('levelCodeA== '+levelCodeA)

			/*
			
			var selectBox = document.getElementById("roleCode");
			var len = selectBox.length;
			for(var k=len-1;k>0;k--){
				selectBox.remove(k);
			}	
			 
			 */

			if (levelCodeA != '4') {

				if (document.getElementById('blockRowMultiple') != null)
					document.getElementById('blockRowMultiple').className = "hide";

			}

			if (document.getElementById('stateCodeRole') != null)
				document.getElementById('stateCodeRole').value = "";

			if (document.getElementById('districtCodeRole') != null)
				document.getElementById('districtCodeRole').value = "";

			var selectBoxRole = document.getElementById("districtCodes");

			if (selectBoxRole != null) {
				var lenRole = selectBoxRole.length;
				for ( var kRole = lenRole - 1; kRole >= 0; kRole--) {
					selectBoxRole.remove(kRole);
				}

			}

		}

		if (levelCodeA != '2') {

			var selectedStateM = document.getElementById("selectedStateM");

			if (selectedStateM != null) {
				document.getElementById('selectedStateM').value = "";
			}

		}

		var selectBoxRoleD = document.getElementById("selectedDistrictM");

		if (selectBoxRoleD != null) {

			var lenRoleD = selectBoxRoleD.length;
			for ( var kRoleD = lenRoleD - 1; kRoleD >= 0; kRoleD--) {
				selectBoxRoleD.remove(kRoleD);
			}
		}

		var blockCodes = document.getElementById("blockCodes");

		if (blockCodes != null) {
			var len = blockCodes.length;

			for ( var k = len - 1; k >= 0; k--) {
				blockCodes.remove(k);
			}

		}
		var selectedBlockM = document.getElementById("selectedBlockM");
		if (selectedBlockM != null) {

			var lenRoleB = selectedBlockM.length;

			for ( var kRoleB = lenRoleB - 1; kRoleB >= 0; kRoleB--) {
				selectedBlockM.remove(kRoleB);
			}
		}

		if (levelCodeA != "") {

			var url = "manageRoleAction.do?methodName=getRole&levelA="
					+ levelCodeA;
			//alert('url=='+url);
			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponseRoless;
			req.send(null);
		}

	}

	function handleHttpResponseRoless() {

		if (req.readyState == 4) {

			var xmlDoc = req.responseXML;

			//alert('xml doc=='+xmlDoc);

			var validate = xmlDoc.getElementsByTagName("RolesList");

			try {

				if (validate[0] != null) {

					if (validate[0].childNodes[0].childNodes[0].nodeValue == "true") {
						var selectBox = document.getElementById("roleCode");
						var len = selectBox.options.length;
						for ( var k = len - 1; k > 0; k--) {
							selectBox.remove(k);
						}
						for ( var i = 1; i < validate[0].childNodes.length; i++) {
							var oOption = document.createElement("OPTION");
							oOption.text = validate[0].childNodes[i].childNodes[1].childNodes[0].nodeValue;
							oOption.value = validate[0].childNodes[i].childNodes[0].childNodes[0].nodeValue;
							selectBox.options.add(oOption);
						}

					} else {
						alert("There seems to be no role available for This level");
					}

				}

				var levelA = document.getElementById('levelCodeA').value;

				hideShowFieldRole();

				/*
				if (levelA == '3' || levelA == '4')
					getDistrictByStateCode();*/

			} catch (err) {
				alert("There seems to be an error fetching role for This Level");
			}
		}

	}

	function getDistrictByStateCode() {

		var state = document.getElementById('stateCode');

		var stateCodeRole = document.getElementById('stateCodeRole');

		//  alert('state code=='+state.value+' stateCodeRole '+stateCodeRole.value)

		var url = "", stateCode = "", districtCodeId = "";

		var lavel = document.getElementById('levelCode').value;

		if (lavel != '0') {
			stateCode = state.value;
		} else {
			stateCode = stateCodeRole.value;
		}

		var lavelA = document.getElementById('levelCodeA').value;

		if (lavelA == '3')
			districtCodeId = document.getElementById('districtCodes').id;

		if (lavelA == '4')
			districtCodeId = document.getElementById('districtCodeRole').id;

		if (stateCode != '') {

			url = "manageRoleAction.do?methodName=getDistrictByStateCode&state="
					+ stateCode + "&districtCodeId=" + districtCodeId;

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponse;
			req.send(null);
		} else {

			var selectBox = document.getElementById("districtCode");
			var selectBoxRole = document.getElementById("districtCodes");
			var selectBoxRoleCode = document.getElementById("districtCodeRole");

			if (selectBox != null) {
				var len = selectBox.length;

				for ( var k = len - 1; k > 0; k--) {
					selectBox.remove(k);
				}

			}

			if (selectBoxRole != null) {

				var lenRole = selectBoxRole.length;
				for ( var kRole = lenRole - 1; kRole >= 0; kRole--) {
					selectBoxRole.remove(kRole);
				}

			}

			if (selectBoxRoleCode != null) {
				var lenRoleCode = selectBoxRoleCode.length;
				for ( var kRoleCode = lenRoleCode - 1; kRoleCode > 0; kRoleCode--) {
					selectBoxRoleCode.remove(kRoleCode);
				}
			}
			var selectBoxRoleD = document.getElementById("selectedDistrictM");

			if (selectBoxRoleD != null) {
				var lenRoleD = selectBoxRoleD.length;
				for ( var kRoleD = lenRoleD - 1; kRoleD >= 0; kRoleD--) {
					selectBoxRoleD.remove(kRoleD);
				}
			}
		}
	}

	function handleHttpResponse() {

		if (req.readyState == 4) {

			try {

				if (req.responseText != '') {

					if (document.getElementById("districtCodes") != null) {

						var selectBoxRole = document
								.getElementById("districtCodes");
						var lenRole = selectBoxRole.length;
						for ( var kRole = lenRole - 1; kRole >= 0; kRole--) {
							selectBoxRole.remove(kRole);
						}

					}
					if (document.getElementById("selectedDistrictM") != null) {

						var selectBoxRoleD = document
								.getElementById("selectedDistrictM");
						var lenRoleD = selectBoxRoleD.length;
						for ( var kRoleD = lenRoleD - 1; kRoleD >= 0; kRoleD--) {
							selectBoxRoleD.remove(kRoleD);
						}

					}
					if (document.getElementById('districtCodeRole') != null) {

						var districtCodeRole = document
								.getElementById("districtCodeRole");

						if (districtCodeRole != null) {
							var lenRole2 = districtCodeRole.length;
							for ( var kRole = lenRole2 - 1; kRole > 0; kRole--) {
								districtCodeRole.remove(kRole);
							}

						}
					}

					var selectBlockRole = document.getElementById("blockCodes");

					if (selectBlockRole != null) {
						var lenRole = selectBlockRole.length;
						for ( var kRole = lenRole - 1; kRole >= 0; kRole--) {
							selectBlockRole.remove(kRole);
						}

					}

					var selectedBlockM = document
							.getElementById("selectedBlockM");

					if (selectedBlockM != null) {
						var lenRoleB = selectedBlockM.length;
						for ( var kRoleB = lenRoleB - 1; kRoleB >= 0; kRoleB--) {
							selectedBlockM.remove(kRoleB);
						}

					}

					evalScript(req.responseText);
				} else {

					alert("<bean:message key="msg.noDistrictsAvailable" />");
					var selectBoxRole = document
							.getElementById("districtCodes");
					var lenRole = selectBoxRole.length;
					for ( var kRole = lenRole - 1; kRole >= 0; kRole--) {
						selectBoxRole.remove(kRole);
					}
				}

			} catch (err) {
				alert("There seems to be an error fetching District for This State");

			}
		}
	}

	function getBlockByDistrictCode() {

		var district = document.getElementById('districtCode');
		var districtCodes = document.getElementById('districtCodeRole');

		var districtCode = "";

		var lavel = document.getElementById('levelCode').value;

		//alert("districtCode=="+district+" districtCodes "+districtCodes);

		if (lavel != '0') {

			if (district != null)
				districtCode = district.value;
			else
				districtCode = districtCodes.value;
		} else {

			districtCode = districtCodes.value;
		}

		//

		/*
		if(district != ""){
		
			 	districtCode=district;
		}
		 if(districtCodes != ""){
		
				districtCode=districtCodes;
		}
		
		 */
		var url = "manageRoleAction.do?methodName=getBlockByDistrictCode&district="
				+ districtCode
				+ "&blockCodeId="
				+ document.getElementById("blockCodes").id;

		if (districtCode != "") {

			if (window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.open("GET", url, true);
			req.setRequestHeader(tokenParameter, tokenValue);
			req.onreadystatechange = handleHttpResponseBlock;
			req.send(null);

		} else {

		}
	}

	function handleHttpResponseBlock() {

		if (req.readyState == 4) {

			try {

				if (req.responseText != '') {

					var selectBoxRole = document.getElementById("blockCodes");
					var len = selectBoxRole.length;

					for ( var k = len - 1; k >= 0; k--) {
						selectBoxRole.remove(k);
					}
					var selectBoxRoleB = document
							.getElementById("selectedBlockM");
					var lenRoleB = selectBoxRoleB.length;

					for ( var kRoleB = lenRoleB - 1; kRoleB >= 0; kRoleB--) {
						selectBoxRoleB.remove(kRoleB);
					}

					evalScript(req.responseText);

				} else {

					alert("There seems to be no block available for This District");

					var selectBoxRole = document.getElementById("blockCodes");
					var len = selectBoxRole.length;

					for ( var k = len - 1; k >= 0; k--) {
						selectBoxRole.remove(k);
					}
					var selectBoxRoleB = document
							.getElementById("selectedBlockM");
					var lenRoleB = selectBoxRoleB.length;
					for ( var kRoleB = lenRoleB - 1; kRoleB >= 0; kRoleB--) {
						selectBoxRoleB.remove(kRoleB);
					}

				}
			} catch (err) {

				alert("There seems to be an error fetching block for This District");

			}
		}
	}

	function hideShowFieldRole() {

		var lavel = document.getElementById('levelCode').value;
		var lavelA = document.getElementById('levelCodeA').value;

		if (lavelA == "") {

			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}

		if (lavelA == "" || lavelA == '0' || lavel == '1' || lavelA == '2') {

			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}

		if (lavel == '0' && lavelA == '5') {

			document.getElementById('stateRowRole').className = "";
			document.getElementById('districtRowRole').className = "";
			document.getElementById('blockRowRole').className = "";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}
		if (lavel == '2' && lavelA == '5') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "";
			document.getElementById('blockRowRole').className = "";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}
		if (lavel == '3' && lavelA == '5') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}

		if (lavel == '4' && lavelA == '5') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}
		if (lavel == '0' && lavelA == '4') {
			document.getElementById('stateRowRole').className = "";
			document.getElementById('districtRowRole').className = "";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "";

		}
		if (lavel == '2' && lavelA == '4') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "";

		}
		if (lavel == '3' && lavelA == '4') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "";

		}
		if (lavel == '0' && lavelA == '3') {
			document.getElementById('stateRowRole').className = "";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "";
			document.getElementById('blockRowMultiple').className = "hide";

		}
		if (lavel == '2' && lavelA == '3') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "hide";
			document.getElementById('districtRowMultiple').className = "";
			document.getElementById('blockRowMultiple').className = "hide";

		}
		if (lavel == '0' && lavelA == '2') {
			document.getElementById('stateRowRole').className = "hide";
			document.getElementById('districtRowRole').className = "hide";
			document.getElementById('blockRowRole').className = "hide";

			document.getElementById('stateRowMultiple').className = "";
			document.getElementById('districtRowMultiple').className = "hide";
			document.getElementById('blockRowMultiple').className = "hide";

		}

		if (lavelA == '4')
			getBlockByDistrictCode();

	}

	function closePage() {
		var status = window.confirm("Are you sure you want to Close ");
		if (status == true) {
			document.manageRoleForm.action = "login.do?methodName=closePage&"
					+ tokenParameter + "=" + tokenValue;
			document.manageRoleForm.submit();
		}
	}

	function addSelected(selectbox1, selectbox2) {
		var i;
		for (i = selectbox1.options.length - 1; i >= 0; i--) {
			if (selectbox1.options[i].selected) {
				var optn = document.createElement("OPTION");
				optn.text = selectbox1.options[i].text;
				optn.value = selectbox1.options[i].value;

				selectbox2.options.add(optn);
				selectbox1.remove(i);

			}

		}

	}

	function selectAll() {
		var lavelA = document.getElementById('levelCodeA').value;

		if (lavelA == '2') {
			for ( var i = 0; i < document.forms[0].selectedStateM.length; i++) {
				document.forms[0].selectedStateM[i].selected = true;
			}
		}
		if (lavelA == '3') {
			for ( var i = 0; i < document.forms[0].selectedDistrictM.length; i++) {
				document.forms[0].selectedDistrictM[i].selected = true;
			}
		}

		if (lavelA == '4') {
			for ( var i = 0; i < document.forms[0].selectedBlockM.length; i++) {
				document.forms[0].selectedBlockM[i].selected = true;
			}
		}

		if (lavelA == '5') {
			for ( var i = 0; i < document.forms[0].selectedVillageM.length; i++) {
				document.forms[0].selectedVillageM[i].selected = true;
			}
		}

	}
	function clearForm() {

		if (confirm("Do you want to clear the form ? ")) {
			document.forms[0].action = "manageRoleAction.do?methodName=assignRole"
					+ "&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
	}
	
	
	function getDataList(type, param, flag) {

		alert(type);
		
		if (document.getElementById("levelCodeA") != null)
			document.getElementById("levelCodeA").value = "";

		if (flag == '0') {

			if (document.forms[0].stateCode != null)
				document.forms[0].stateCode.value = "";
			if (document.forms[0].districtCode != null)
				document.forms[0].districtCode.value = "";

			if (document.forms[0].blockCode != null)
				document.forms[0].blockCode.value = "";

		}

		if (type == 'ZP') {
			if (document.forms[0].districtCode != null)
				document.forms[0].districtCode.value = "";

		}
		if (type == 'ZP' || type == 'BP') {
			if (document.forms[0].blockCode != null)
				document.forms[0].blockCode.value = "";

		}

		if (document.forms[0].loginId != null) {

			document.forms[0].loginId.value = "";
		}
		document.manageRoleForm.action = "manageRoleAction.do?methodName=getDataList&form=1&type="
				+ type + "&" + tokenParameter + "=" + tokenValue + "&param="+ param;
		document.manageRoleForm.submit();
	}

	
	function getUsers(level, primaryObject) {
		var levelCode = document.getElementById('levelCode').value;
		levelName = document.getElementById('levelCode')[document.getElementById('levelCode').selectedIndex].firstChild.nodeValue;
		if (levelCode == level) {
			getDataList(level, '1', '');
		} else {
		}
		return;
	}

	
	function clearRole() {

		if (document.getElementById('districtCodeRole') != null)
			document.getElementById('districtCodeRole').value = "";

		/*
		var selectBoxRole = document.getElementById("districtCodes");
		
		if(selectBoxRole!=null){
		var lenRole = selectBoxRole.length;
		for(var kRole=lenRole-1;kRole>=0;kRole--){
				selectBoxRole.remove(kRole);
		}
		
		}
		 */

		var selectBoxRoleD = document.getElementById("selectedDistrictM");

		if (selectBoxRoleD != null) {

			var lenRoleD = selectBoxRoleD.length;
			for ( var kRoleD = lenRoleD - 1; kRoleD >= 0; kRoleD--) {
				selectBoxRoleD.remove(kRoleD);
			}
		}

		var blockCodes = document.getElementById("blockCodes");

		if (blockCodes != null) {
			var len = blockCodes.length;

			for ( var k = len - 1; k >= 0; k--) {
				blockCodes.remove(k);
			}

		}
		var selectedBlockM = document.getElementById("selectedBlockM");
		if (selectedBlockM != null) {

			var lenRoleB = selectedBlockM.length;

			for ( var kRoleB = lenRoleB - 1; kRoleB >= 0; kRoleB--) {
				selectedBlockM.remove(kRoleB);
			}
		}

		hideShowFieldRole();
	}
</script>
<body>
	<font color="red" size="2"> <html:errors /></font>
	<html:form action="login/manageRoleAction">
		<table width="100%">
			<tr>
				<td>
					<table width="90%" align="center" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" class="pageHeader"><span> <bean:message key="label.manageRole.assign"></bean:message>
							</span> <bean:message key="label.manageRole.role"></bean:message></td>

						</tr>
						<tr>
							<td><font color="red" size="2">*</font>&nbsp;<span><bean:message key="mandatory.label"></bean:message> </span></td>
						</tr>
					</table>

					<table width="90%" align="center" class="inputTBL">
						<tr>
							<th width="30%"><bean:message key="label.manageRole.levelofuser"></bean:message><font color="red" size="2">*</font></th>
							<td><html:select property="levelCode" styleId="levelCode" onchange="getUsers('1',this);getDataList(this.value,'0','0')">
									<html:option value="">--Select Level--</html:option>
									<html:options collection="levelList" property="propertyCode" labelProperty="propertyValue" />
								</html:select></td>
						</tr>

						<logic:present name="myas">
							<html:hidden property="entityCode"></html:hidden>
						</logic:present>
						
						<logic:present name="state">

					<tr>
						<th width="30%"><bean:message key="lable.user.selectState" /><span
							class="mandatory">*</span>
						</th>
						<td><html:select property="stateCode" styleId="stateCode"
								onchange="getUsers('2',this)">
								<html:option value="">
									<bean:message key="label.selectState" />
								</html:option>

								<logic:present name="stateList">
									<html:options collection="stateList" property="propertyCode"
										labelProperty="propertyValue" />
								</logic:present>
							</html:select>
						</td>
					</tr>
				</logic:present>
						
						<tr>
							<th width="30%"><bean:message key="lable.user.loginID" /><font color="red" size="2">*</font></th>
							<td><html:select property="loginId" styleId="loginId">
									<html:option value="">
										<bean:message key="select.label" />
									</html:option>

									<logic:present name="userList">
										<html:options collection="userList" property="propertyCode" labelProperty="propertyValue" />
									</logic:present>
								</html:select></td>
						</tr>

						<tr>
							<th width="30%">Level Of Role To Be Assigned<font color="red" size="2">*</font>
							</th>
							<td><html:select property="levelCodeA" styleId="levelCodeA" onchange="getRole()">
									<html:option value="">--Select Level of Role--</html:option>
									<logic:present name="roleList">
										<html:options collection="roleList" property="propertyCode" labelProperty="propertyValue" />
									</logic:present>
								</html:select></td>
						</tr>

						<tr>
							<th width="30%"><bean:message key="access.addRole.roleName.label" /><font color="red" size="2">*</font></th>
							<td><html:select property="roleCode" styleId="roleCode" onchange="clearRole()">
									<html:option value="">--Select Role--</html:option>
								</html:select></td>
						</tr>

						<tr id="stateRowRole" class="hide">
							<th width="30%"><bean:message key="label.manageRole.selectState" /><font color="red" size="2">*</font></th>
							<td><html:select property="stateCodeRole" styleId="stateCodeRole" onchange="getDistrictByStateCode()">
									<html:option value="">--Select State--</html:option>
									<logic:present name="stateList">
										<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue" />
									</logic:present>
								</html:select></td>
						</tr>


						<tr id="districtRowRole" class="hide">
							<th width="30%"><bean:message key="label.manageRole.selectDistrict" /><font color="red" size="2">*</font></th>
							<td><html:select property="districtCodeRole" styleId="districtCodeRole" onchange="getBlockByDistrictCode()">
									<html:option value="">--Select District--</html:option>
								</html:select></td>
						</tr>

						<tr id="blockRowRole" class="hide">
							<th width="30%"><bean:message key="label.manageRole.selectBlockPanchayat" /><font color="red" size="2">*</font></th>
							<td><html:select property="blockCodeRole" styleId="blockCodeRole">
									<html:option value="">--Select Block Panchayat--</html:option>
								</html:select></td>
						</tr>
						<tr id="stateRowMultiple" class="hide">
							<td colspan="2">
								<div align="center">
									<table border="0" width="49%">

										<tr>
											<td><bean:message key="label.manageRole.selectState" /><font color="red" size="2">*</font> <html:select property="stateCodes" styleId="stateCodes" multiple="true" style="width:195px">
													<logic:present name="stateList">
														<html:options collection="stateList" property="propertyCode" labelProperty="propertyValue" />
													</logic:present>
												</html:select></td>
											<td>
												<%
											String state = "<<";
										%> <html:button property="next" value=">>" styleClass="button" onclick="addSelected(document.forms[0].stateCodes,document.forms[0].selectedStateM)"></html:button><br /> <html:button
													property="next" styleClass="button" value="<%=state %>" onclick="addSelected(document.forms[0].selectedStateM,document.forms[0].stateCodes)"></html:button>
											</td>

											<td><bean:message key="label.manageRole.selectState" /> <html:select property="selectedStateM" styleId="selectedStateM" multiple="true" style="width:195px">
												</html:select></td>
										</tr>
									</table>

								</div>
							</td>
						</tr>


						<tr id="districtRowMultiple" class="hide">
							<td colspan="2">
								<div align="center">
									<table border="0" width="49%">

										<tr>
											<td><bean:message key="label.manageRole.selectDistrict" /><font color="red" size="2">*</font> <html:select property="districtCodes" styleId="districtCodes" multiple="true"
													style="width:195px">

												</html:select></td>
											<td>
												<%
											String district = "<<";
										%> <html:button property="next" value=">>" styleClass="button" onclick="addSelected(document.forms[0].districtCodes,document.forms[0].selectedDistrictM)"></html:button><br> <html:button
													property="next" styleClass="button" value="<%=district %>" onclick="addSelected(document.forms[0].selectedDistrictM,document.forms[0].districtCodes)"></html:button>
											</td>

											<td><bean:message key="label.manageRole.selectedDistrict" /> <html:select property="selectedDistrictM" styleId="selectedDistrictM" multiple="true" style="width:195px">
												</html:select></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>

						<tr id="blockRowMultiple" class="hide">
							<td colspan="2">
								<div align="center">
									<table border="0" width="49%">
										<tr>
											<td><bean:message key="label.user.selectBlocks" /><font color="red" size="2">*</font> <html:select property="blockCodes" styleId="blockCodes" multiple="true" style="width:195px">
												</html:select></td>
											<td>
												<%
											String block = "<<";
										%> <html:button property="next" value=">>" styleClass="button" onclick="addSelected(document.forms[0].blockCodes,document.forms[0].selectedBlockM)"></html:button><br> <html:button
													property="next" styleClass="button" value="<%=block %>" onclick="addSelected(document.forms[0].selectedBlockM,document.forms[0].blockCodes)"></html:button>
											</td>

											<td><bean:message key="label.user.selectedBlocks" /> <html:select property="selectedBlockM" styleId="selectedBlockM" multiple="true" style="width:195px">
												</html:select></td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<div align="center">
						<html:button styleClass="button" property="next" value="Assign Role" onclick="save()"></html:button>
						<input name="Button" type="button" class="button" value="Close" onclick="closePage()" />
						<html:button styleClass="button" property="" onclick="clearForm()"> <bean:message key="button.clear" /> </html:button>
					</div>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>

