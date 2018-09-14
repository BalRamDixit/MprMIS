<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<script>
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>';

function startDownloadDetector() {
	 var nIntervId = setInterval(detectDownload, 250);
	}
	
function detectDownload() {
	
	   var allcookies = document.cookie;
	   cookiearray  = allcookies.split(';');
	   for(var i=0; i<cookiearray.length; i++){
	      name = cookiearray[i].split('=')[0];
	      value = cookiearray[i].split('=')[1];
		if(name == "fileDownload"){
			//document.getElementById("downloadButton").style.display = "";
			document.getElementById("downloadMsg").style.display = "none";
			}
	   }
}

function downloadExcel(status) {
	
		document.getElementById("downloadMsg").style.display = "";
		document.forms[0].action = "piaReportAction.do?methodName=downloadPIAsExcel&dType="+status+"&" + tokenParameter + "=" + tokenValue;
	 	document.forms[0].submit(); 
}
 
</script>
<body onload="startDownloadDetector();">
<html:form action="/login/piaReportAction"> 
<!--  -->
<table  width="100%" align="center"> 
		<tr><td align="center" class="pageHeader">Download Applicant Organisation Excel</td></tr>
		<tr><td align="center"> 
		<table  width="100%" align="center">
	<tr>
		<td>
			<div style="border: solid #ccc 1px; -moz-border-radius: 6px;
				 -webkit-border-radius: 6px; border-radius: 6px;
    			 -webkit-box-shadow: 0 1px 1px #ccc; -moz-box-shadow: 0 1px 1px #ccc; 
    			 box-shadow: 0 1px 1px #ccc;margin: 10px 17px;
				 padding:15px 10px 15px 10px;background-repeat: no-repeat;
				 background-position: 10px center;color: #51398D;
				 font-weight:bold;">
				NOTE: In the first option save the zip file to a location, unzip it, which will
				show Applicant Organisation Detail and Applicant Organisation Members Detail in excel file and related documents.
			</div>
		</td>
	</tr>
</table>
			<table>
					
					<tr>
						<td align="center">
							<table width="95%" class="bordered" align="center">
		  						<tr>
		  							<th style="text-align: center;">Sr. No.</th>
									<th style="text-align: center;">Download Type</th>
									<th style="text-align: center;">Description</th>
									<th style="text-align: center;">Download</th>		
		  						<!-- Link for zip -->
		  						<tr>
		  							<td style="text-align: center;"><b>1.</b></td>
		  							<td><b>All documents with Excel File</b></td>
		  							<td style="font-size: 12px;">This file contains both excel files (i.e. PIAs and Members) with their related documents bundled in a zip file.  </td>
		  							<td style="text-align: center;"><a href="javascript:downloadExcel('A');"><img src="../images/download.png" width="24px" height="24px" alt="Doanload"/></a></td>
		  						</tr>
		  						
		  						<!-- Link for PIA excel download -->
		  						<tr>
		  							<td style="text-align: center;"><b>2.</b></td>
		  							<td><b>Applicant Organisation Detail Excel File</b></td>
		  							<td style="font-size: 12px;">This file contains excel sheet of Applicant Organisation detail.</td>
		  							<td style="text-align: center;"><a href="javascript:downloadExcel('PE');"><img src="../images/download.png" width="24px" height="24px" alt="Doanload"/></a></td>
		  						</tr>
		  						
		  							<!-- Link for member Excel download -->
		  						<tr>
		  							<td style="text-align: center;"><b>3.</b></td>
		  							<td><b>Member Detail Excel File</b></td>
		  							<td style="font-size: 12px;">This file contains excel sheet of PIAs members .</td>
		  							<td style="text-align: center;"><a href="javascript:downloadExcel('ME');"><img src="../images/download.png" width="24px" height="24px" alt="Doanload"/></a></td>
		  						</tr>
							
							</table>
						</td>
					</tr>
			</table>
		</td></tr>
		
	</table>
	
	<table style="display: none;margin-top: -200px;" id="downloadMsg"  width="100%" align="center">
	<!-- <table id="downloadMsg" style="margin-top: -200px;"  width="100%" align="center"> -->
		<tr>
					<td align="center">
					<div style="border: solid #ccc 1px; -moz-border-radius: 6px;
						-webkit-border-radius: 6px; border-radius: 6px;
    					-webkit-box-shadow: 0 5px 5px #ccc; -moz-box-shadow: 0 2px 2px #ccc; 
    					box-shadow: 0 2px 2px #ccc;margin: -10px 180px;
						padding:15px 10px 15px 10px;background-color: #18364C;
						background-position: 10px center;color: #51398D;
						font-weight:bold;">
						Downloading...... Please Wait ....!<br/><br/><br/>
						<img src="../images/loading.gif" alt="Loading ..!! Please wait..!!"/>
						<br/><br/><br/>
						NOTE: It will take some time, Please don't refresh this page.
					</div>
					</td>
				</tr>
	</table>
</html:form>
</body>