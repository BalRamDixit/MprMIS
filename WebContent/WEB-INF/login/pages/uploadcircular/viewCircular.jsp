<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<% 

ServletContext context = getServletContext(); 


%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<META http-equiv=Content-Language content=en-us>
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script language="Javascript1.1" src="javaScript/staticJs.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/datetimepicker.js"></script>
<script language="JavaScript" src="sushil/datetimepicker.js" type="text/javascript" ></script>
<script language="javascript" src="javaScript/choosedate.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<script language="Javascript1.1" src="javaScript/common.js"></script>






<script language="javascript" type="text/javascript" src="javaScript/dhtmlgoodies_calendar.js"></script>
<script language="javascript" type="text/javascript" src="javaScript/datetimepicker.js"></script>
<script language="javascript" src="javaScript/choosedate.js"></script>
<script language="javascript" src="javaScript/dateValidation.js"></script>
<script language="javascript" src="javaScript/AjaxScript.js"></script>
<link href="css/PYKKA.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/calendar.css"></link>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css"></link>
<script language="javascript">
  
  var tokenParameter='reqtrack';
  var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;
  function showDetail()
  {
 
  	document.forms[0].action="uploadCircular.do?method=showView"+"&"+tokenParameter+"="+tokenValue;
	
	document.forms[0].submit();
  }
  
  
  function closeReport(){
	  var status=window.confirm("Are You Sure You want to close the form ?");
	  if(status==true){
		document.forms[0].action = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
		document.forms[0].submit();
		}
}
var child1=false;
    window.onfocus = function (){
 	checkChildWindow();
  }
     
function  checkChildWindow(){
 if( typeof(test)=='undefined'  || test.closed == true){
  // Do nothing as the window is closed...
 }  
 else{
     test.focus();
  }
}
function popitup(fileName) {
     var schemeName=document.forms[0].circularScheme.value;
     var path="FileFolder/UpLoadCircular/"+schemeName+"/"+fileName;
   //  alert(path);
	newwindow=window.open(path,'name','');
	if (window.focus) {newwindow.focus()}
	
}

function fileUpload(fileName){
 

    var schemeName=document.forms[0].circularScheme.value;
    
   // alert("schemeName=="+schemeName+"-filename-"+fileName);

	document.forms[0].action="uploadCircular.do?method=downloadCircular&schemeName="+schemeName+"&path="+fileName;
	
	document.forms[0].submit();
	
}
</script>
</head>
<body onclick="checkChildWindow()">
<html:form action="login/uploadCircular"  method="post" enctype="multipart/form-data">
		<div class="mainpane">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="actionstxt" width="100%" align="center">
						<bean:message key="label.ViewSchemeCircular"></bean:message>
					</td>
				</tr>
			</table>

			<table border="0" cellpadding="0" cellspacing="0" class="formlabel">
				<tr id="shemeRow">
					<td width="30%" class="formlabel"><bean:message
							key="label.SelectSchemeCircular" /></td>
					<td class="formlabel"><html:select property="circularScheme"
							styleId="circode"  onchange="showDetail()">
							<html:option value="">Select</html:option>
							<html:options collection="schemeList" property="circularScheme"
								labelProperty="circularScheme" />
						</html:select></td>
				</tr>
			</table>

			<table width="95%" border="0" cellpadding="0" cellspacing="0" align="center">
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
			</table>

			<logic:present name="circularMessage">
				<script>
 		 			alert('<bean:write name="circularMessage"/>');
 		 		</script>
			</logic:present>

			<logic:present name="CircularDetaill">
				<%int cnt = 0; %>

				<table border="1" width="750" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<th align="center" class="formlabel">
							Sl.No
						</th>
						<th align="center" class="formlabel">
							<bean:message key="label.uploadCirDes" />
						</th>
						<th align="center" class="formlabel">
							<bean:message key="label.Cirfile"/>
							(<bean:message key="label.english"/>)
						</th>
						<th align="center" class="formlabel">
							<bean:message key="label.Cirfile"/>
							(<bean:message key="label.hindi"/>)
						</th>
					</tr>
					
					<logic:iterate id="List" name="CircularDetaill">
						<tr>
							<td width="12%" align="left" class="formlabel"><%=++cnt %></td>
							<td width="60%" align="left" class="formlabel">
								<bean:write name="List" property="uploadCircularName" />
							</td>
							<td width="15%" align="left" class="formlabel">
								<a href="javascript:fileUpload('<bean:write name="List" property="uploadCircularCode"/>')">
									${List.uploadCircularFileName}
								</a>
							</td>
							<td width="15%" align="left" class="formlabel">
								<a href="javascript:fileUpload('<bean:write name="List" property="uploadCircularCodeHindi"/>')">
									${List.uploadCircularFileNameHindi}
								</a>
							</td>
						</tr>
					</logic:iterate>

					<tr>
						<td colspan="4" align="center">
							<!-- <html:button property="next" styleClass="button" onclick="window.print()">
								<bean:message key="button.print" />
							</html:button> -->
							<html:button property="next" styleClass="button" onclick="closeReport()">
								<bean:message key="button.close" />
							</html:button>
						</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>
					</tr>
				</table>
			</logic:present>
		</div>
	</html:form>
</body>
</html:html>