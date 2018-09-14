<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>

<!--script for menu-->
<script type="text/javascript">
/*  
function closePage()
{
	var status=window.confirm("<bean:message key="alert.close.form" />");
	if(status==true){
	document.accessForm.action = "login.do?methodName=closePage"+"&"+tokenParameter+"="+tokenValue;;
	document.accessForm.submit();
	}
}



  */
  function backform(){
	  var status=window.confirm('Are You Sure You Want to go back ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=show"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}  
  }
  
 
function saveform(){
	
	$("input[data-test]").each(function(){
		var target=$(this).data('test');
		alert(target);
		var abc=$("#expTrainComn"+target).val();
		var efg=$("#expTrainComp"+target).val();
		var jkl=$("#placeExp"+target).val();
		//alert(abc+"  "+efg+"  "+jkl);
		if(abc==null||abc==""){
			abc=0;
		}if(efg==null||efg==""){
			efg=0;
		}if(jkl==null||jkl==""){
			jkl=0;
		}
		var result=0;
		var rr=false;
		if(parseInt(abc)<parseInt(efg)+parseInt(jkl)){
			$("#expTrainComn"+target).focus();
			rr=true;
			return false;
		}
	});
	
	if(rr){
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	alert("save");
	 var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="ppwsSetup.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
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
	
	function showTableFn(projectid){
		
		var xmlHttpRequest = getXMLHttpRequest();
		 xmlHttpRequest.onreadystatechange = function(){
			  
				if (xmlHttpRequest.readyState == 4) {
					
					if (xmlHttpRequest.status == 200) {
						
						if (xmlHttpRequest.responseText != "") {
							//alert(xmlHttpRequest.responseText);
							
							document.getElementById("showtable").innerHTML = xmlHttpRequest.responseText;
						} else{
							
							
					}
					} else {
						alert("error");
						
				}
				}
			}
		
	xmlHttpRequest.open("POST","ppwsSetup.do?methodName=durationMonths", true);
	xmlHttpRequest.setRequestHeader(tokenParameter, tokenValue);
	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");		
	xmlHttpRequest.send("projectId="+projectid);	

	}

	 function isNumberKey(evt)
	  {
	     var charCode = (evt.which) ? evt.which : event.keyCode
	     if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;

	     return true;
	  }
	

</script>
<!--Main form section starts here-->
<html:form action="login/ppwsSetup" styleId="ppwsForm">

	 
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">PPWS Setup</td>					
               </tr>
				<tr>
					<td style="padding-left: 7%;" ><span class="text-error">*</span>
					<span class="normaltext"><bean:message  key="lable.field.manadatory" /></span>
					</td>
				</tr>
			</table> 
			<tr>	</tr>			
			
			<table width="100%" align="center" class="inputTBL">
				<tr>
					<th>${name}</th>
					<td><b>${nameValue}</b></td>
				</tr>
				</table>
		<br/>
			 	
			<br/>		 
				
				<table width="100%" align="center" class="inputTBL">
			 
			 	 <tr><th><span class="input-group-addon" id="basic-addon1">ProjectID</span></th>
						<td>	<html:select property="projectId" styleId="projectIdId"  styleClass="form-control"  onchange="javascript:showTableFn(this.value);" >
								<html:option value="0">-Select-</html:option>
								<logic:present name="ProjectList">
									<logic:iterate id="project" name="ProjectList">
										<html:option value="${project}">${project}</html:option>
									</logic:iterate>
								</logic:present>
							</html:select></td>
						   </tr>
			 </table>
				
				<br/>
				<br/>
				
				<div id="showtable"></div>
				
				
 	
</html:form>
 