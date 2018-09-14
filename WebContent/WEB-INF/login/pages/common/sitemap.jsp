<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%String loggedFlag="";
if(request.getAttribute("logFlag")!=null){
	loggedFlag=(String)request.getAttribute("logFlag");
}
%>


<!--Main form section starts here-->

<body>
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;
function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
	
$(document).ready(function(){
	$("#black, #gray").treeview({
		control: "#treecontrol",
		collapsed: true,
		animated: "slow",
		persist: "cookie",
		cookieId: "treeview-black"
	});

});	
</script>
<!-- HEADER MENU DIV START -->
   <%-- <div id="nav" class="box">
        <ul>
            <li><a href="login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID")%>">Home</a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="shgReport.do?methodName=showDetails">Directory</a></li>
            <li><a href="uploadCircular.do?method=showView">Documents</a></li> 
            <li><a href="newReport.do?methodName=showAboutUs">About Us</a></li>
            <li><a href="SGSYHelp.jsp">Help</a></li>
        </ul>
        <p id="feeds">
            <a href="http://aajeevika.gov.in" target="_blank" class="ico-aajeevika">Aajeevika Site</a> &nbsp;&nbsp;&nbsp;
            <a href="login/login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID")%>" class="ico-login">Login </a>    
        </p> 
    </div> --%>
    
 	<!-- HEADER MENU DIV END -->
 	
 	<!-- <div style="border-top: solid 2px gray; border-bottom: solid 2px gray;"> -->
	<div  style="padding-top: 10px; padding-bottom: 10px;">
   	<!-- Content -->   
  	<div class="pageHeader" align="center"> SITEMAP OF (NRLM) </div> 
	<!-- NRLM TREE VIEW -->   
	<div>    
	<ul id="black" class="filetree treeview">
		<li class="collapsable">
	</ul>
	
	<ul id="black" class="filetree">		
		<li><span class="folder" style="background: #F0F0F0; width:75%;"> <img src="images/About-me-icon.png" alt="" />About Us</span>
				<ul>
					<li><span class="file">About Us </span></li>																							 
				</ul>
   		 </li>
	</ul>            
         
      <ul id="black" class="filetree">		
		<li><span class="folder" style="background: #F0F0F0; width:75%;"> <img src="images/report.png" alt="" /> Report</span>
				<ul>
					<li><span class="file">State Report </span></li>											
					<li><span class="file">District Report</span></li>											 
				</ul>
   		 </li>
	</ul>   
         
    <ul id="black" class="filetree">		
		<li><span class="folder" style="background: #F0F0F0; width:75%;"><img src="images/directory.png" alt="" />Directory</span>
				<ul>
					<li><span class="file">Directory 1</span></li>
					<li><span class="file">Directory 2</span></li>
					<li><span class="file">Directory 3</span></li>											 
				</ul>
   		 </li>
	</ul>     
        
      <ul id="black" class="filetree">		
		<li><span class="folder" style="background: #F0F0F0; width:75%;"><img src="images/document.png" alt="" />Documents</span>
				<ul>
					<li><span class="file">Document 1</span></li>
					<li><span class="file">Document 2</span></li>
					<li><span class="file">Document 3</span></li>											 
				</ul>
   		 </li>
   		 
		</ul>  
         
      <ul id="black" class="filetree">		
			<li><span class="folder" style="background: #F0F0F0; width:75%;"><img src="images/help.png" alt="" />Help</span>
					<ul>
						<li><span class="file">Help</span></li>												 
					</ul>
	   		 </li>
	  	</ul> 
     	<ul id="black" class="filetree">		
			<li><span class="folder" style="background: #F0F0F0; width:75%;"> <img src="images/phone-icon2.png" alt="" />Contact Us</span>
					<ul>
						<li><span class="file">Contact Details</span></li>												 
					</ul>
	   		 </li>
		</ul>  
	
		<ul id="black" class="filetree">		
			<li><span class="folder" style="background: #F0F0F0; width:75%;"><img src="images/related-site.png" alt="" />NRLM Related Site</span>
					<ul>
			<li><span class="file">Aajeevika</span></li>												 
			</ul>
	   </li>
	</ul>         
</div>
<div align="center">
	<html:button styleClass="button" property="" onclick="closeReport()"><bean:message key="button.close" /></html:button>
</div>	
</div>
</body>


