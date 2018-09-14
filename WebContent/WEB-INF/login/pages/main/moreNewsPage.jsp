<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<script type="text/javascript">
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ;
function closeReport(){
	 window.location = "login.do?methodName=showMainPage"+"&"+tokenParameter+"="+tokenValue;
	}
</script>
<!-- HEADER MENU DIV START -->
  <%-- <div id="nav" class="box">
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Reports</a></li>
            <li><a href="#">Directory</a></li>
            <li><a href="#">Documents</a></li> 
            <li><a href="#">About Us</a></li>
            <li><a href="#">Help</a></li>
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
            <div  align="center">
            <table width="90%" align="center"  >
			<tr><td align="left" class="pageHeader" >More News</td></tr>
			<tr><td>
			<table width="100%" align="center" class="inputTBL">
			<tr>
				<th></th>
				<th>Date</th>
				<th colspan="3"></th>				
			</tr>
			
			<tr class="odd">
				<td rowspan="3">1</td>
				<td rowspan="3">15 May'2013</td>
				<td colspan="3"><strong>Letter from JS for Implementation of MIS</strong>
			</tr>
			<tr class="odd">
				<td>
						<strong>States</strong><br/><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Arunachal Pradesh.PDF" target="_blank">1. Arunachal Pradesh</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/CEOAssam.PDF" target="_blank">2. Assam</a><br/>							
							<a href="DocumentsForDownload/ImplementationOfMIS/CEOBihar.PDF" target="_blank">3. Bihar</a><br/> 
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Chhatisgarh.PDF" target="_blank">4. Chhattisgarh</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Gujarat.PDF" target="_blank">5. Gujarat</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD haryana.PDF" target="_blank">6. Haryana</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/CEOShimla.PDF" target="_blank">7. Himachal Pradesh</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Srinagar.PDF" target="_blank">8. Jammu &amp; Kashmir</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/CEORanchi.PDF" target="_blank">9. Jharkhand</a><br/> 
				</td>
				<td>
					<strong>States</strong><br/><br/>
						<a href="DocumentsForDownload/ImplementationOfMIS/MD Karnataka.PDF" target="_blank">10. Karnataka</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/PC Bhopal.PDF" target="_blank">11. Madhya Pradesh</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/CEOMaharashtra.PDF" target="_blank">12. Maharashtra</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Manipur.PDF" target="_blank">13. Manipur</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/COOMeghalaya.PDF" target="_blank">14. Meghalaya</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Mizoram.PDF" target="_blank">15. Mizoram</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Nagaland.PDF" target="_blank">16. Nagaland</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Odhisa.PDF" target="_blank">17. Odisha</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/CEOPunjab.PDF" target="_blank">18. Punjab</a><br/>		
				</td>
				<td>	
						<strong>States</strong><br/><br/>
						<a href="DocumentsForDownload/ImplementationOfMIS/MD Rajasthan.PDF" target="_blank">19. Rajasthan</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Sikkim.PDF" target="_blank">20. Sikkim</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD tamilnadu.PDF" target="_blank">21. Tamil Nadu</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/COOTripura.PDF" target="_blank">22. Tripura</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/MD Lucknow.PDF" target="_blank">23. Uttar Pradesh</a><br/>
							<a href="DocumentsForDownload/ImplementationOfMIS/OSD uttrakhand.PDF" target="_blank">24. Uttarakhand</a><br/>							
							<a href="DocumentsForDownload/ImplementationOfMIS/MD West Bengal.PDF" target="_blank">25. West Bengal</a>
				</td>		
			</tr>	
				
		</table>
		</td></tr>
		</table>
			
			<div align="center"><html:button styleClass="button" property="" onclick="closeReport()">
						<bean:message key="button.close" />
					</html:button>
			</div>
			</div>
</div>
	