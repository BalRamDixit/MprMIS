
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/packtag.tld" prefix="pack"%>

<script type="text/javascript">
$(document).ready(function()
		{
			//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
			$("#firstpane p.menu_head").click(function()
		    {
				$(this).next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		       	$(this).siblings();
			});
			
		});
 
function showHideMenu(img){

	if(img=='plus'){
		document.getElementById('nrlmID').className="";
		document.getElementById('plus').className="hide";
		document.getElementById('remove').className="";
	}
	else{
		document.getElementById('nrlmID').className="hide";
		document.getElementById('plus').className="";
		document.getElementById('remove').className="hide";
}
 //document.getElementById('test').style.display='';
 
}
function showHideMenu2(img){

	if(img=='plus2'){
		document.getElementById('splPrjID').className="";
		document.getElementById('plus2').className="hide";
		document.getElementById('remove2').className="";
	}
	else{
		document.getElementById('splPrjID').className="hide";
		document.getElementById('plus2').className="";
		document.getElementById('remove2').className="hide";
}
 //document.getElementById('test').style.display='';
 
}
</script>


<%String loggedFlag="";

if(request.getAttribute("logFlag")!=null){
	
	loggedFlag=(String)request.getAttribute("logFlag");
}
%>
<%if(session.getAttribute("form_mod_code")!=null){%>
<input type="hidden" id="form_mod_codeID"
	value="<%=session.getAttribute("form_mod_code") %>" />
<%}
if(session.getAttribute("form_Name")!=null){%>
<input type="hidden" id="form_NameID"
	value="<%=session.getAttribute("form_Name") %>" />
<%}
if(session.getAttribute("form_code")!=null){%>
<input type="hidden" id="form_codeID"
	value="<%=session.getAttribute("form_code") %>" />
<%}
if(session.getAttribute("mode_code")!=null){%>
<input type="hidden" id="mode_codeID"
	value="<%=session.getAttribute("mode_code") %>" />
<%}if(session.getAttribute("mode_name123")!=null){

 


%>
<input type="hidden" id="mode_nameID"
	value="<%=session.getAttribute("mode_name123") %>" />
<%}%>



<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" />
</title>
<link href="css/menu.css" rel="stylesheet" type="text/css" />




<logic:present name="moduleList">
	<div id="Accordion_menu">
		<div id="firstpane" class="menu_list">
			<!--Code for menu starts here-->
			<c:forEach items="${moduleList}" var="moduleList">
				<p class="menu_head">
					<bean:write name="moduleList" property="modName" />
				</p>
				<bean:define id="modcd">
					<bean:write name="moduleList" property="modcd" />
				</bean:define>
				<div class="menu_body" style="display: none;">
					<div class="top"></div>
					<div class="contents">
						<div id="navigation">
							<ul class="top-level">
								<logic:present name="formModuleList">
									<c:forEach items="${formModuleList}" var="formModuleList">
										<logic:equal name="formModuleList" property="modcd"
											value="<%=modcd%>">
											<li><a
												href="<bean:write name="formModuleList" property="formURL" />"
												onclick="onloadfunction()"><bean:write
														name="formModuleList" property="formName" />
											</a>
											</li>
										</logic:equal>
									</c:forEach>
								</logic:present>
							</ul>
						</div>
					</div>
					<div class="bottom"></div>
				</div>
			</c:forEach>
			<!-- Added By Balram Dixit For New Modules 10-03-2017-->
				<p class="menu_head">
					New Modules Masters
				</p>
				<div class="menu_body" style="display: none;">
					<div class="top"></div>
					<div class="contents">
						<div id="navigation">
							<ul class="top-level">	
								<li>
									<a href="asignRole.do?methodName=addRole">Role Master</a>
								</li>
								<li>
									<a href="formModule.do?methodName=addModule">Form Module Master</a>
								</li>
								<li>
									<a href="userMaster.do?methodName=addUserMaster">User Master</a>
								</li>
								<li>
									<a href="assignRoleMaster.do?methodName=addAssignRoleMaster">Assign Module</a>
								</li>
								<li>
									<a href="projectMapping.do?methodName=addProjectMapping">Assign Project Mapping</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="bottom"></div>
				</div>
				<p class="menu_head">
					Reports
				</p>
				<div class="menu_body" style="display: none;">
					<div class="top"></div>
					<div class="contents">
						<div id="navigation">
							<ul class="top-level">	
								<li>
									<a href="report.do?methodName=overallIndicators">Overall Indicator</a>
								</li>
								<li>
									<a href="report.do?methodName=appraisals">Appraisals</a>
								</li>
								<li>
									<a href="report.do?methodName=financialAchievement">Financial Achievement</a>
								</li>
								<li>
									<a href="report.do?methodName=physicalAchievement">Physical Achievement</a>
								</li>
								<li>
									<a href="report.do?methodName=installmentStatus">Installment Status</a>
								</li>
								<li>
									<a href="report.do?methodName=exceptionsState">Inspection & Defaults</a>
								</li>
								<li>
									<a href="report.do?methodName=exceptionsNational">Inspection & Defaults(Cont..)</a>
								</li>
								<li>
									<a href="report.do?methodName=championEmployer">Champion Employers</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="bottom"></div>
				</div>
		</div>
		</div>
		<!--Code for menu ends here-->
		<br></br>
	<!-- Form Description -->
	
	
	
	<% if( (String)request.getAttribute("formName")!=null){%>

	
<%-- 	<font color="Blue"><b><i>Description of <%=(String)request.getAttribute("formName") %> Form 
 	will come here...</i></b></font>--%>
	
<%}%>
	</div>
	<!--End of Accordian Area-->
</logic:present>



<!--Left navigation menu ends here-->
<script language="javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 


var persistmenu="yes"; //"yes" or "no". Make sure each SPAN content contains an incrementing ID starting at 1 (id="sub1", id="sub2", etc)
var persisttype="sitewide" ;//enter "sitewide" for menu to persist across site, "local" for this page only

if (document.getElementById){ //DynamicDrive.com change
document.write('<style type="text/css">\n');
document.write('.submenu{display: none;}\n');
document.write('</style>\n');
}
 
function SwitchMenu(obj){
	if(document.getElementById){
			var el = document.getElementById(obj); 
			var ar = document.getElementById("masterdiv").getElementsByTagName("span"); //DynamicDrive.com change	
			var form_mod_codeID;
			var form_NameID;
			var mod_codeID;
			var mode_nameID;	
			if(document.getElementById("mod_codeID")!=null){
				mod_codeID = document.getElementById("mod_codeID").value;
			}
			if(document.getElementById("form_mod_codeID")!=null){
				form_mod_codeID = document.getElementById("form_mod_codeID").value;
			}
			if(document.getElementById("mode_nameID")!=null){
				 mode_nameID = document.getElementById("mode_nameID").value;
			}
			if(document.getElementById("form_NameID")!=null){
				form_NameID = document.getElementById("form_NameID").value;
			}
			  
			if(el.style.display != "block"){ //DynamicDrive.com change
				for (var i=0; i<ar.length; i++){	
					if (ar[i].className=="submenu") //DynamicDrive.com change
						ar[i].style.display = "none";
				}	
				el.style.display = "block";
			}else{
				el.style.display = "none";
			}		
	}
}

function get_cookie(Name) {
	var search = Name + "=";
	var returnvalue = "";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) { 
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1) end = document.cookie.length;
			returnvalue=unescape(document.cookie.substring(offset, end));
		}
	}
	return returnvalue;
}

function onloadfunction(){
	var form_mod_codeID;
	var form_NameID;
	var mod_codeID;
	var mode_nameID;
	if(document.getElementById("form_mod_codeID")!=null){
		form_mod_codeID = document.getElementById("form_mod_codeID").value;
	}	
	if(document.getElementById("form_NameID")!=null){
		form_NameID = document.getElementById("form_NameID").value;
	}	
	if(document.getElementById("mod_codeID")!=null){
		mod_codeID = document.getElementById("mod_codeID").value;
	}	
	if(document.getElementById("mode_nameID")!=null){
		mode_nameID = document.getElementById("mode_nameID").value;
	}
	<%if(session.getAttribute("mode_name123")!=null){
		
	 }%>
	//alert("form_mod_codeID 111-->>>"+form_mod_codeID);
	//alert("mod_codeID   111- ->>"+mod_codeID);
	//alert("form_NameID   111111-->>"+form_NameID);
	//alert("mode_nameID   111111-->>"+mode_nameID);
	
	if (persistmenu=="yes"){
		var cookiename=(persisttype=="sitewide")? "switchmenu" : window.location.pathname;
		var cookievalue=get_cookie(cookiename);
		if (cookievalue!="")
			document.getElementById(cookievalue).style.display="block";
	}	 
}

function savemenustate(){
	var inc=1, blockid="";
	while (document.getElementById("sub"+inc)){
	
		if (document.getElementById("sub"+inc).style.display=="block"){
			blockid="sub"+inc;
			break;
		}
		inc++;
	}
		var cookiename=(persisttype=="sitewide")? "switchmenu" : window.location.pathname;
		var cookievalue=(persisttype=="sitewide")? blockid+";path=/" : blockid;
		document.cookie=cookiename+"="+cookievalue;
}

	if (window.addEventListener)
		window.addEventListener("load", onloadfunction, false);
	else if (window.attachEvent)
		window.attachEvent("onload", onloadfunction);
	else if (document.getElementById)
		window.onload=onloadfunction;
	
	if (persistmenu=="yes" && document.getElementById)
		window.onunload=savemenustate;



// Added on 
 
function startsWith(st, wi){
	  	if (st == '') {return wi == '';}
	  	return st.substring(wi.length,0)==wi ;
	}
 
 function replaceQueryString(a,k,v) {
 	
	   var re = new RegExp("([?|&|//])" + k + "=.*?(&|$)","i");   
	  
	   //alert(a.match(re));
	  
	   if (a.match(re)){
	       return a.replace(re,'$1' + k + "=" + v + '$2');
	   }else{
	    //alert(a);
	       	if(a.indexOf('?')==-1){
		 		return a+'?'+k+'='+v;
		 	}else{
		 		return a+'&'+k+'='+v;
		 	}
	     //   	return a + '&' + k + "=" + v;
	   }
	}

/////////////////

 
 	var anchors = document.getElementsByTagName('a'); 
 
    //var forms = document.getElementsByTagName('form'); 
   
  
   
	for(var i = 0; i < anchors.length; i++) {
		
		var hrefStr = anchors[i].href;
 	 	 
 	 	 
 	    // alert(anchors[i].href);
 	 	var str = hrefStr ;
		
		if(str.split(':')[0]!='javascript'){
			
			str = str.substring(str.length-1, str.length );
	    
	        if(str != '#')
		       anchors[i].href=replaceQueryString(hrefStr,tokenParameter,tokenValue);
		 }
	          
	} 

</script>
