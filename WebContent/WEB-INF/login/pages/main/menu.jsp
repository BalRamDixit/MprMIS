<!DOCTYPE>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/WEB-INF/tld/packtag.tld" prefix="pack"%>

<script type="text/javascript">
jQuery(function($) {
	$('.amount').each(function(){
		
		var maskedElement=document.createElement('input');
		$(maskedElement).attr({
			"type":"text",
			"data-d-group":"2s",
			"data-v-max":"99999999999999",
			"data-v-min":"0",
			"data-a-pad":"false",
			"class":"maskedAmount",
			"onChange":"changeTextToOriginal(this,'"+$(this).attr('id')+"')",
			"id":"maskedAmount"+$(this).attr('id')
			
		});
		$(maskedElement).insertAfter($(this));
		$(this).addClass("hideText");
	});
	$(".maskedAmount").autoNumeric("init");
	$(".maskedAmount").autoNumeric('reSet');
	
	$('.amount').each(function(){
		$("#maskedAmount"+$(this).attr('id')).autoNumeric('set', $(this).val());
		$("#maskedAmount"+$(this).attr('id')).attr({"onblur":$(this).attr('onblur')});
	});
	
});
function changeTextToOriginal(element,x){
	var getInput = $(element).autoNumeric('get');
	document.getElementById(x).value=getInput;
}
function checkPermissionForHide(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
		$('.checkPermissionForFormForInsert').show();
	}
	else{
		
		$('.checkPermissionForFormForInsert').hide();
	}
}
$(document).ready(function(){
	checkPermissionForHide();
	//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
	$("#firstpane p.menu_head").click(function(){
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
function createPdf(divIdToExport){
	var url = "formModule.do?methodName=downloadPdf";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var dataToPdf=$('#'+divIdToExport).html();
	req.open("POST", url, true);
	req.setRequestHeader(tokenParameter, tokenValue);
	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");	
	req.onreadystatechange = handleHttpResponse;
	req.send("data="+escape(dataToPdf));
}
function handleHttpResponse(){
	if (req.readyState == 4 ){	
    	var response = req.responseText; 
    	$('#fileToDownload').attr("href",response);
    	SaveToDisk(response,"Report")
  	}
}
function SaveToDisk(fileURL, fileName) {
    // for non-IE
    if (!window.ActiveXObject) {
        var save = document.createElement('a');
        save.href = fileURL;
        save.target = '_blank';
        save.download = fileName || 'unknown';

        var evt = new MouseEvent('click', {
            'view': window,
            'bubbles': true,
            'cancelable': false
        });
        save.dispatchEvent(evt);

        (window.URL || window.webkitURL).revokeObjectURL(save.href);
    }

    // for IE < 11
    else if ( !! window.ActiveXObject && document.execCommand)     {
        var _window = window.open(fileURL, '_blank');
        _window.document.close();
        _window.document.execCommand('SaveAs', true, fileName || fileURL)
        _window.close();
    }
}
function fnExcelReport(TableIdToExport) {
	var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
	var textRange;
	var j = 0;
	tab = document.getElementById(TableIdToExport); // id of table

	for (j = 0; j < tab.rows.length; j++) {
		tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
		//tab_text=tab_text+"</tr>";
	}

	tab_text = tab_text + "</table>";
	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");

	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
		txtArea1.document.open("txt/html", "replace");
		txtArea1.document.write(tab_text);
		txtArea1.document.close();
		txtArea1.focus();
		sa = txtArea1.document.execCommand("SaveAs", true, "FileName.xls");
	} else
		sa = window.open('data:application/vnd.ms-excel,'
				+ encodeURIComponent(tab_text));

	return (sa);
}
</script>


<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><tiles:getAsString name="title" ignore="true" />
</title>
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<div id="checkPermissionForFormForInsertResponse" style="display: none"></div>

	<div id="Accordion_menu">
		<div id="firstpane" class="menu_list">
			<logic:present name="userBeanData">
			<!--Code for menu starts here-->
			<c:forEach items="${userBeanData.moduleList}" var="moduleList">
				<p class="menu_head">
					<c:forEach items="${moduleList.value}" var="menuHeader">
						<c:if test="${menuHeader.key eq moduleList.key}">
							${menuHeader.value.formName}
							
						</c:if> 
					</c:forEach>
				</p>
				<div class="menu_body" 
					<c:if test="${masterFormId eq moduleList.key}">
						style="display: block;"
					</c:if>
					<c:if test="${masterFormId ne moduleList.key}">
						style="display: none;"
					</c:if>
					>
					
					<div class="top"></div>
					<div class="contents">
						<div id="navigation">
							<ul class="top-level">
								<c:forEach items="${moduleList.value}" var="formModuleList">
								
<%-- 									<c:if test="${formModuleList.value.permission ne '000'}"> --%>
									<c:if test="${fn:startsWith(formModuleList.value.permission, '11') || fn:startsWith(formModuleList.value.permission, '01') || fn:startsWith(formModuleList.value.permission, '10')}">
										<c:if test="${formModuleList.key ne moduleList.key}">
											<li
											<c:if test="${currentFormId eq formModuleList.key}">
												style="background-color: #bca4f9;"
											</c:if>
											>
												<a href="${formModuleList.value.url}" id="${formModuleList.key}" onclick="checkMenuItem(this);return false;">
													${formModuleList.value.formName}
												</a>
											</li>
										</c:if> 
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="bottom"></div>
				</div>
			</c:forEach>
			</logic:present>
		</div>
	</div>



<!--Left navigation menu ends here-->
<script language="javascript">
function showStateName(e){
	var div = e.options[e.selectedIndex].title;
	$('#stateNameForShow').html(div);

}
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
function checkMenuItem(element){
	
	//Ajax Call To set Menu Id in session
	var IdForSet=$(element).attr('id');
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else  {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        		var response = xmlhttp.responseText;
        		if(response=="true"){
               		var location=$(element).attr('href');
               		window.location.href =location;
        		}
        		else if(response=="logout"){
        			alert("Session IS Destroyed Please Login Again")
               		getTracker();
        		}
        		else{
        			alert(response);
        		}
        }
           
    }
	xmlhttp.open("GET", "formModule.do?methodName=setMenuItem&formId="+IdForSet, true);
    xmlhttp.setRequestHeader(tokenParameter, tokenValue);
    xmlhttp.send();
}
function getMenuItem(){
	
	//Ajax Call To set Menu Id in session
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else  {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        }
    }
	xmlhttp.open("GET", "formModule.do?methodName=getMenuItem", true);
    xmlhttp.setRequestHeader(tokenParameter, tokenValue);
    xmlhttp.send();
}

function checkPermissionForFormForInsert(){
	checkPermissionForFormForInsertFunction();
	var x=$('#checkPermissionForFormForInsertResponse').html();
	return x;
}

function checkPermissionForFormForInsertFunction(){
	$('#checkPermissionForFormForInsertResponse').html('');
	//Ajax Call To set Menu Id in session
	$.blockUI({ message: '<img src="busy.gif" /><br>Just a moment...' });
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else  {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        		var response = xmlhttp.responseText;
        		$.unblockUI();
        		if(response=="true"){
        			$('#checkPermissionForFormForInsertResponse').html('true');
        		}
        		else if(response=="logout"){
        			alert("Session IS Destroyed Please Login Again");
               		getTracker();
               		return 'false';
        		}
        		else{
//         			alert(xmlhttp.responseText);
        			$('#checkPermissionForFormForInsertResponse').html('false');
        		}
        }
           
    }
	xmlhttp.open("GET", "formModule.do?methodName=checkPermissionForFormForInsert", false);
    xmlhttp.setRequestHeader(tokenParameter, tokenValue);
    xmlhttp.send();
}



function onloadfunction(){
		
	//Ajax Call To set Menu Id in session
	
	
	
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
