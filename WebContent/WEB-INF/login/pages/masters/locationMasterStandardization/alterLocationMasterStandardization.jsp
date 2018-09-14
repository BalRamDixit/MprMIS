<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="html" uri="/WEB-INF/struts-html.tld" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>

<head>
	<%
		String path="";
		if(request.getAttribute("pdf_file_path")!=null)
		{ path =request.getAttribute("pdf_file_path")+"";} %>

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
	<script src="../javaScript/apprise-1.5.full.js"></script>
	<link rel="stylesheet" href="../css/apprise.css" type="text/css">
	<script type="text/javascript">
		
	var tokenParameter='reqtrack';
	var tokenValue = '<%=request.getSession().getAttribute("TRACKERID")%>';
	
		/*function openAllPriPage(){
			document.forms[0].action="locationMasterStandardization.do?methodName=showAllPrisInDistrict&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();	
		}*/
		
		function openAllPriPage(){			
			document.forms[0].action="locationMasterStandardization.do?methodName=createPDF&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();	
		}
		
		function openAllPriPageCreateExcel(){			
			document.forms[0].action="locationMasterStandardization.do?methodName=createExcel&"+tokenParameter+"="+tokenValue;			
			document.forms[0].submit();	
		} 
		/*
		 Date: 30 July 2013		 
		function openAllPriPage(){
		    var districtCode = document.getElementById("cmbDistrict").value;
		    var districtName = document.getElementById('cmbDistrict')[document.getElementById('cmbDistrict').selectedIndex].innerText;
             alert(districtName + ":" + districtCode); 
		    if(districtCode != ""){
		    	 document.forms[0].action="locationMasterStandardization.do?methodName=createPDF&dstcd="+districtCode+"&dstnm=" +districtName+ "&" + tokenParameter + "=" + tokenValue;
		    	 document.forms[0].submit();
		    }else{
		    	document.forms[0].action="locationMasterStandardization.do?methodName=createPDF&"+tokenParameter+"="+tokenValue;
			    document.forms[0].submit();
		    }
		   }
		
		function openAllPriPageCreateExcel(){	
			  var districtCode = document.getElementById("cmbDistrict").value;
			  var districtName = document.getElementById("cmbDistrict").value;	            
			    if(districtCode != ""){
			    	 document.forms[0].action="locationMasterStandardization.do?methodName=createExcel&dstcd=" + districtCode + "&dstnm=" + districtName + "&" + tokenParameter + "=" + tokenValue;
					 document.forms[0].submit();
			    }
			document.forms[0].action="locationMasterStandardization.do?methodName=createExcel&"+tokenParameter+"="+tokenValue;			
			document.forms[0].submit();	
		}
	
		/* End 30 July 2013 */
		
		function districtChange() {
			var districtCode = document.forms[0].districtCode.value;
			document.forms[0].action = "locationMasterStandardization.do?methodName=districtChange&"+ "districtCode=" + districtCode + "&" + tokenParameter + "=" + tokenValue;
			document.forms[0].submit();
		}
		
		function blockChange() {
			var blockCode = document.getElementById("cmbBlock").value;
			if(document.forms[0].districtCode) {
				var districtCode = document.forms[0].districtCode.value;
				document.forms[0].action = "locationMasterStandardization.do?methodName=blockChange&"+ "districtCode=" + districtCode + "&blockCode=" + blockCode + "&" + tokenParameter + "=" + tokenValue;
			} else {
				document.forms[0].action = "locationMasterStandardization.do?methodName=blockChange&"+ "blockCode=" + blockCode + "&" + tokenParameter + "=" + tokenValue;
			}
			document.forms[0].submit();
		}
		
		function grampanchayatChange() {
			var actionUrl = "locationMasterStandardization.do?methodName=grampanchayatChange&";
			if(document.forms[0].districtCode) {
				var districtCode = document.forms[0].districtCode.value;
				actionUrl += "districtCode=" +  districtCode + "&";
			}
			if(document.forms[0].blockCode) {
				var blockCode = document.getElementById("cmbBlock").value;
				actionUrl += "blockCode=" +  blockCode + "&";
			}
			var grampanchayatCode = document.getElementById("cmbGrampanchayat").value;
			actionUrl += "grampanchayatCode="+grampanchayatCode+"&"+tokenParameter + "=" + tokenValue;
			document.forms[0].action = actionUrl;
			document.forms[0].submit();
		}
		
		function deleteRow(deleteLink) {
			var status = window.confirm("Are you sure you want to delete.");
			if(status) {
				var rowNumber = deleteLink.parentNode.parentNode.rowIndex;
				var currentRow = document.getElementById("list").rows(rowNumber).cells;
				var code = document.getElementById(rowNumber).value;
				var xmlHTTP;
				if(window.XMLHttpRequest) {
					xmlHTTP = new XMLHttpRequest();
				}
				else if (window.ActiveXObject){
					xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlHTTP.onreadystatechange = function () {
					if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200){
						var flag = xmlHTTP.responseText;
						if(flag == "true") {
							alert("Deleted Successfully");	
							var text = currentRow[1].innerHTML;
							var hashIndex = text.indexOf("#");
							var slashIndex = text.indexOf("/");
							var newText = text.substring(0, hashIndex) + "#B0171F" + text.substring(hashIndex+7, slashIndex-1) + "<bean:message key='label.deleted'/>" + text.substring(slashIndex-1);
							currentRow[1].innerHTML = newText;
							currentRow[2].innerHTML = "";
						}
						else if(flag == "false") {
							alert("Unable to delete. Please try again '"+flag+"'");
						}
						else {
							alert(flag);
						}
					}
				};
				xmlHTTP.open("POST", "locationMasterStandardization.do?methodName=delete", true);
				xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
				xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xmlHTTP.send("code="+code+"");
			}
		}
		
		
		function editRow(editLink) {
			var rowNumber = editLink.parentNode.parentNode.rowIndex;
			var currentRow = document.getElementById("list").rows[rowNumber].cells;
			var code = document.getElementById(rowNumber).value;
			var text = currentRow[1].innerHTML;
			var nameChange = text.substring(text.indexOf(">")+1, text.indexOf("</")-1); 
			var name = window.prompt("Please enter a new Name", $.trim(nameChange));
			if(name && trim(name)!="") {
				var xmlHTTP;
				if(window.XMLHttpRequest) {
					xmlHTTP = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlHTTP.onreadystatechange = function() {
					var flag = xmlHTTP.responseText;
					if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200) {
						if(flag == "true") {
							alert("Edited Successfully");
							var hashIndex = text.indexOf("#");
							var slashIndex = text.indexOf("/");
							var newText = text.substring(0, hashIndex) + "#EE7600" + text.substring(hashIndex+7, text.indexOf(">")+1) + name.toUpperCase() + "<bean:message key='label.edited'/>" + text.substring(slashIndex-1);
							currentRow[1].innerHTML = newText;
							currentRow[2].innerHTML = "";
						}
						else if(flag == "false") {
							alert("Unable to edit. Please try again.");
						}
						else {
							alert(flag);
						}
					}
				};
				xmlHTTP.open("POST", "locationMasterStandardization.do?methodName=edit", true);
				xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
				xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xmlHTTP.send("code=" + code + "&name=" + name);
			}
		}
		
		function addRow() {
			var districtCode;
			var blockCode;
			var grampanchayatCode;
			if(document.getElementById("cmbDistrict")) {
				if(document.getElementById("cmbDistrict").value != "") {
					districtCode = document.getElementById("cmbDistrict").value;
				}
			}
			if(document.getElementById("cmbBlock")) {
				if(document.getElementById("cmbBlock").value != "") {
					blockCode = document.getElementById("cmbBlock").value;
				};
			}
			if(document.getElementById("cmbGrampanchayat")) {
				if(document.getElementById("cmbGrampanchayat").value != "") {
					grampanchayatCode = document.getElementById("cmbGrampanchayat").value;
				};
			}
			var name = window.prompt("Please enter a new Name.", "");
			if(name && trim(name)!="") {
				var xmlHTTP;
				if(window.XMLHttpRequest) {
					xmlHTTP = new XMLHttpRequest();
				}
				else if(window.ActiveXObject) {
					xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlHTTP.onreadystatechange = function() {
					if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200) {
						var flag = xmlHTTP.responseText;
						if(flag == "true") {
							alert("Added Successfully");
							var table = document.getElementById("list");
							var newRow = table.insertRow(table.rows.length-1);
							var cellZero = newRow.insertCell(0);
							cellZero.innerHTML = "";
							var cellOne = newRow.insertCell(1);
							cellOne.className = "formlabel";
							cellOne.innerHTML = "<span style= 'color: #2E8B57; font-weight: bold'>"+ name.toUpperCase() +"<bean:message key='label.new'/></span>";
						}
						else if(flag == "false") {
							alert("Unable to Add. Please try again.");
						}
						else {
							alert(flag);
						}
					}
				};
				xmlHTTP.open("POST", "locationMasterStandardization.do?methodName=add", true);
				xmlHTTP.setRequestHeader(tokenParameter, tokenValue);
				xmlHTTP.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xmlHTTP.send("districtCode="+districtCode +"&blockCode="+blockCode+"&grampanchayatCode="+grampanchayatCode+"&name="+name);
			}
		}
		
		function trim(stringToTrim) {
			return stringToTrim.replace(/^\s+|\s+$/g,"");
		}
		function ltrim(stringToTrim) {
			return stringToTrim.replace(/^\s+/,"");
		}
		function rtrim(stringToTrim) {
			return stringToTrim.replace(/\s+$/,"");
		}
		
		function goBack() {
			if(document.getElementById("cmbGrampanchayat") && document.getElementById("cmbGrampanchayat").value != "") {
				//Case for Village(Go back to grampanchayat)
				var url = "locationMasterStandardization.do?methodName=blockChange";
				if(document.getElementById("cmbDistrict")) {
					var districtCode = document.getElementById("cmbDistrict").value;
					url += "&districtCode=" + districtCode;
				}
				if(document.getElementById("cmbBlock")) {
					var blockCode = document.getElementById("cmbBlock").value;
					url += "&blockCode=" + blockCode;
				}
				document.getElementById("cmbGrampanchayat").selectedIndex = 0;
				url += "&" + tokenParameter + "=" + tokenValue;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			else if(document.getElementById("cmbBlock") && document.getElementById("cmbBlock").value != "") {
				//Case for Grampanchayat(Go back to block)
				var url = "locationMasterStandardization.do?methodName=districtChange"; 
				if(document.getElementById("cmbDistrict")) {
					url += "&districtCode=" + document.getElementById("cmbDistrict").value;
				}
				url += "&" + tokenParameter + "=" + tokenValue;
				document.getElementById("cmbBlock").selectedIndex = 0;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
			/* var backUrl = "${header.Referer}";
			backUrl = backUrl.substring(0, backUrl.indexOf("reqtrack"));
			document.forms[0].action = backUrl + tokenParameter + "=" + tokenValue;
			document.forms[0].submit(); */
		}
	</script>
</head>
<body>
	<html:form action="login/locationMasterStandardization">
		<table width="100%">
				<tr>
					<td class="pageHeader" align="center"> <bean:message key="label.locationMasterStandardizaton"/></td>
				</tr>
				<tr>
					<td align="left" style="padding-left: 50px;">Download your District's Block, Gram Panchayat and Village Directory as &emsp; 
					<a href="javascript:openAllPriPage()" title="Download as PDF"><img alt="PDF" src="images/pdf-icon.png" width="20px;" height="20px;"></a> 
					&emsp;<a href="javascript:openAllPriPageCreateExcel()" title="Download as Excel"><img alt="EXCEL" src="images/Excel-icon.png" width="20px;" height="20px;">
					</a>
					</td>
				</tr>
				<tr>
					<td><table width="90%" class="inputTBL" align="center">
					<tr>
						<th width="200px;"><bean:message key="label.district" />
						</th>
						<td><logic:present name="districtName" scope="request">
								<bean:write name="districtName" />
							</logic:present> <logic:present name="districtList" scope="request">
								<html:select styleId="cmbDistrict" property="districtCode"
									onchange="districtChange()">
									<html:option value="">--Select--</html:option>
									<html:options collection="districtList"
										labelProperty="districtName" property="districtCode" />
								</html:select>
							</logic:present></td>
					</tr>
					<tr>
						<th><bean:message key="label.blockName" />
						</th>
						<td><logic:present name="blockName" scope="request">
								<span class="textbold"><bean:write name="blockName" />
								</span>
							</logic:present> <logic:present name="blockList" scope="request">
								<html:select styleId="cmbBlock" property="blockCode"
									onchange="blockChange()">
									<html:option value="">--Select--</html:option>
									<html:options collection="blockList" labelProperty="blockName"
										property="blockCode" />
								</html:select>
							</logic:present> <logic:notPresent name="blockName" scope="request">
								<logic:notPresent name="blockList" scope="request">
									<html:select property="blockCode">
										<html:option value="">--Select--</html:option>
									</html:select>
								</logic:notPresent>
							</logic:notPresent></td>
					</tr>
					<tr>
						<th><bean:message key="label.gramPanchayat" />
						</th>
						<td><logic:present name="grampanchayatList" scope="request">
								<html:select styleId="cmbGrampanchayat"
									property="gramPanchayatCode" onchange="grampanchayatChange()">
									<html:option value="">--Select--</html:option>
									<html:options collection="grampanchayatList"
										labelProperty="gramPanchayatName" property="gramPanchayatCode" />
								</html:select>
							</logic:present> <logic:notPresent name="grampanchayatList" scope="request">
								<html:select property="gramPanchayatCode">
									<html:option value="">--Select--</html:option>
								</html:select>
							</logic:notPresent></td>
					</tr>
				</table>
				</td>
				</tr>
		</table>
		
		<table id="list"  width="90%" class="inputTBL" align="center">
			<logic:equal value="true" name="blockShow">
				<tr>
					<th align="center" colspan="3">
						<span class="textbold"><bean:message key="label.block"/></span>
					</th>
				</tr>
				<logic:present name="blockList" scope="request">
					
					<logic:iterate id="block" name="blockList" indexId="index">
						<tr>
							<td width="5%"><html:hidden styleId="${index+1}" name="block" property="blockCode"/>${index+1}</td>
							<logic:equal value="D" name="block" property="lastModifedBy">
								<td>
									<span style="color: #B0171F; font-weight: bold;">
										<bean:write name="block" property="blockName"/>
										<bean:message key="label.deleted"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="E" name="block" property="lastModifedBy">
								<td >
									<span style="color: #EE7600; font-weight: bold;">
										<bean:write name="block" property="blockName"/>
										<bean:message key="label.edited"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="A" name="block" property="lastModifedBy">
								<td >
									<span style="color: #2E8B57; font-weight: bold;">
										<bean:write name="block" property="blockName"/>
										<bean:message key="label.new"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:empty name="block" property="lastModifedBy">
								<td width="70%" >
									<span style="color: #4169E1; font-weight: bold;">
										<bean:write name="block" property="blockName"/>
									</span>
								</td>
								<td width="30%" align="left" >
									<a id="editLink" href="#" onclick="editRow(this)">Edit</a>&emsp;
									<a id="deleteLink" href="#" onclick="deleteRow(this)">Delete</a>
								</td>
							</logic:empty>
						</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3" align="center">
						<html:button property="" onclick="addRow()" styleClass="button"><bean:message key="label.add.block"/></html:button>
					</td>
				</tr>
			</logic:equal>
			
			<logic:equal value="true" name="grampanchayatShow">
				<tr>
					<th align="center" colspan="3">
						<span class="textbold"><bean:message key="label.gramPanchayat"/></span>
					</th>
				</tr>
				<logic:present name="grampanchayatList" scope="request">
					<logic:iterate id="grampanchayat" name="grampanchayatList" indexId="index">
						<tr>
							<td width="5%"><html:hidden styleId="${index+1}" name="grampanchayat" property="gramPanchayatCode"/>${index+1}</td>
							<logic:equal value="D" name="grampanchayat" property="lastModifedBy">
								<td >
									<span style="color: #B0171F; font-weight: bold;">
										<bean:write name="grampanchayat" property="gramPanchayatName"/>
										<bean:message key="label.deleted"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="E" name="grampanchayat" property="lastModifedBy">
								<td >
									<span style="color: #EE7600; font-weight: bold;">
										<bean:write name="grampanchayat" property="gramPanchayatName"/>
										<bean:message key="label.edited"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="A" name="grampanchayat" property="lastModifedBy">
								<td >
									<span style="color: #2E8B57; font-weight: bold;">
										<bean:write name="grampanchayat" property="gramPanchayatName"/>
										<bean:message key="label.new"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:empty name="grampanchayat" property="lastModifedBy">
								<td width="70%" >
									<span style="color: #4169E1; font-weight: bold;">
										<bean:write name="grampanchayat" property="gramPanchayatName"/>
									</span>
								</td>
								<td width="30%" align="left" >
									<a id="editLink" href="#" onclick="editRow(this)">Edit</a>&emsp;
									<a id="deleteLink" href="#" onclick="deleteRow(this)">Delete</a>
								</td>
							</logic:empty>
						</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3" align="center">
						<html:button property="" onclick="addRow()" styleClass="button"><bean:message key="label.add.gramPanchayat"/></html:button>
						<logic:notEqual value="4" name="loginVO" scope="session" property="levelCode">
							<html:button property="" onclick="goBack()" styleClass="button"><bean:message key="button.back"/></html:button>
						</logic:notEqual>
					</td>
				</tr>
			</logic:equal>
			
			<logic:equal value="true" name="villageShow">
				<tr>
					<th align="center" colspan="3">
						<span class="textbold"><bean:message key="label.village"/></span>
					</th>
				</tr>
				<logic:present name="villageList" scope="request">
					<%-- <tr>
						<th>Sl. No.</th>
						<th><span class="textbold"><bean:message key="label.village" /></span></th>
						<th></th>
					</tr> --%>
					<logic:iterate id="village" name="villageList" indexId="index">
						<tr>
							<td width="5%"><html:hidden styleId="${index+1}" name="village" property="villageCode"/>${index+1}</td>
							<logic:equal value="D" name="village" property="lastModifedBy">
								<td >
									<span style="color: #B0171F; font-weight: bold;">
										<bean:write name="village" property="villageName"/>
										<bean:message key="label.deleted"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="E" name="village" property="lastModifedBy">
								<td >
									<span style="color: #EE7600; font-weight: bold;">
										<bean:write name="village" property="villageName"/>
										<bean:message key="label.edited"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:equal value="A" name="village" property="lastModifedBy">
								<td >
									<span style="color: #2E8B57; font-weight: bold;">
										<bean:write name="village" property="villageName"/>
										<bean:message key="label.new"/>
									</span>
								</td>
								<td></td>
							</logic:equal>
							<logic:empty name="village" property="lastModifedBy">
								<td width="70%" >
									<span style="color: #4169E1; font-weight: bold;">
										<bean:write name="village" property="villageName"/>
									</span>
								</td>
								<td width="30%" align="left" >
									<a id="editLink" href="#" onclick="editRow(this)">Edit</a>&emsp;
									<a id="deleteLink" href="#" onclick="deleteRow(this)">Delete</a>
								</td>
							</logic:empty>
						</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3" align="center">
						<html:button property="" onclick="addRow()" styleClass="button"><bean:message key="label.add.village"/></html:button>
						<html:button property="" onclick="goBack()" styleClass="button"><bean:message key="button.back"/></html:button>
					</td>
				</tr>
			</logic:equal>	
		</table>
	</html:form>
</body>

