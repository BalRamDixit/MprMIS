<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>




<%
	String loggedFlag = "";
	if (request.getAttribute("logFlag") != null) {
		loggedFlag = (String) request.getAttribute("logFlag");
	}
%>


<script type="text/javascript">
$('document').ready(function(){
	$('#flip-container').quickFlip();
	
	$('#flip-navigation li a').each(function(){
		$(this).click(function(){
			$('#flip-navigation li').each(function(){
				$(this).removeClass('selected');
			});
			$(this).parent().addClass('selected');
			var flipid=$(this).attr('id').substr(4);
			$('#flip-container').quickFlipper({ }, flipid, 1);
			
			return false;
		});
	});
});


 function showPopup() {
	 
	 window.open('javaScript/NotificationNo_31.html', '_blank', 'toolbar=0,location=10,menubar=0,width=600,height=400,resizable=0');
 }


</script>

<html:form action="login/login">

<!-- Columns -->
<div id="cols">
	
<%--    
	<div id="abc" class="box" ><jsp:include page="scroller.html" flush="true"  /></div> --%>
	<div id="cols-in" class="box" style="border:thin inset; border-color:#CCC; border-right:none;">
   		<!-- Content -->
            <div id="content">
               <!--  <div class="in" style="background:#CCC;">
                <ul id="ticker">
				<li><p id="breadcrumbs">States have to send the details of MIS-NRLM Nodal Officer through <strong><span style="color: blue" >mis-nrlm@nic.in</span></strong></p>
				</li> 
				</ul> 
              <marquee width="550px" align="absmiddle" behavior="scroll" direction="left" scrollamount="5" onMouseOver="this.setAttribute('scrollamount',0,0);" onMouseOut="this.setAttribute('scrollamount',5,0);">
                   <table id="DataList112" cellspacing="0" border="0" style="width:100%;border-collapse:collapse;">
	               <tbody>
	                 <tr> 
	                  <td style="white-space:nowrap;">                                           
				 <span id="DataList112_ctl00_lblNews11111112" style="color:black;font-size:12px;font-weight: bold">&nbsp; &nbsp;::&nbsp; &nbsp;</span>                                                                    
				<a style="color: black;font-size:12px; font-weight: bold"  target="_blank">
				 <span id="DataList112_ctl00_lblNews12" style="font-color: black; font-weight: bold"> For latest information, please visit our new website www.ddugky.gov.in </span></a>
				 <img src="images/new.gif" alt="New" /> </td>
	              
                </tr>
                </tbody></table> </marquee>  	
                
                			
                </div> -->


				<h2 class="title-01">Welcome to MPR System</h2> 
                <div class="in">  
                     <%--          
                    <!-- Topstory -->
                     <ol style="padding: 5px 20px 5px 20px;" >
					 <logic:present name="outerReportCount">
                     <logic:iterate id="list" name="outerReportCount">
                     <table class="zebra" width="100%">
                     <tr>
                     	<td width="40px;">1</td>
                     	<td>Total No. applications received for Permanent Registration Number</td>
                     	<td width="60px;"><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=PSV">${list[0]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">2</td>
                     	<td>Permanent Registration Number alloted to the number of the Applicants </td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=V">${list[1]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">3</td>	
                     	<td>Total Number of rejeceted Applications </td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=R">${list[2]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">4</td>
                     	<td>Number of applications submitted for the Permanent Registration Number</td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=S">${list[3]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">5</td>
                     	<td>Number of alloted Permanent Registration Numbers withdrawn after Review</td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=PW">${list[4]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">6</td>
                     	<td>Number of Applicant Organisations Debarred</td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=PD">${list[5]}</a></span></td>
                     </tr>
                     <tr>
                     	<td width="40px;">7</td>
                     	<td>Number of Applicant Organisations Blacklisted</td>
                     	<td><span class="text-error"><a href="outerAction.do?methodName=showpiaRegStatusPage&reportType=PB">${list[6]}</a></span></td>
                     </tr>
                     </table>
					</logic:iterate>				
					</logic:present>					
					</ol> 
                     --%>
                </div> <!-- /in --> 
                
				           
            </div> <!-- /content -->
            <hr class="noscreen" />

            <!-- Aside -->
           <div id="aside">
           		<a href="login/login.do?methodName=showMainPage&reqtrack=<%=request.getSession().getAttribute("TRACKERID")%>">Login</a>
           		<%-- 
                <h4 class="title-03">Events</h4>                
                <div class="in" style="min-height: 150px;">              
                    <ul id="subnav"  >                   		 
						<li><a
							href="DocumentsForDownload/Notifications/2015/Notification_No_69-2015.pdf"
							target="_blank">Notification No. 69/2015 Activation of online
								application form for DDU-GKY Projects </a> </br> <a
							href="DocumentsForDownload/Miscellaneous/Project_App_User_Manual.pdf"
							target="_blank">User Manual for Online Project Application
								&nbsp;
						</a></li>
						<li><a
							href="DocumentsForDownload/Notifications/2016/Notification_No_29-2016.pdf"
							target="_blank">Notification No. 29 /2016 Project application
								module, reopening of website and extension of last date revision
								of cost norms-reg. &nbsp;
						</a></li>
						<li><a
							href="DocumentsForDownload/Notifications/2016/Notification_No_31-2016.pdf"
							target="_blank">Notification No. 31/2016 Inviting
								applications for skill development projects for wage employment
								under Himayat - reg. &nbsp;
						</a></li>
						
						<li> <a href="DocumentsForDownload/Notifications/2016/Notification_No_35-2016.pdf" target="_blank">Notification No. 35/2016 Project appraisal toolkit for conducting qualitative appraisal in Stage II of the appraisal process of project applications in DDU-GKY &nbsp; </a></li>
                	  <li> <a href="DocumentsForDownload/Notifications/2016/Notification_No_35_DD-GKY_Qualitative_Appraisal_Tool.xlsx" target="_blank">DDU-GKY Qualitative Appraisal Tool Kit &nbsp; </a></li>        	           	            	 
					</ul>
                </div>              
                 --%>               
            </div> <!-- /aside -->              
        </div> <!-- /cols-in -->
    </div> <!-- /cols -->
       
    <hr class="noscreen" />      


</html:form>
