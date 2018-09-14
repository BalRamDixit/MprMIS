<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
 	
<!--  	<div style="border-top: solid 2px gray; border-bottom: solid 2px gray;"> -->
	<div  style="padding-top: 10px; padding-bottom: 10px;">
   		<!-- Content -->
            <div  align="center">
			<table width="100%"  >
			<tr><td align="center" class="pageHeader" >CONTACT DETAILS</td></tr>
			<tr><td align="center">
			<table width="95%" align="center" class="inputTBL">
			<tr>
				<th>Sl.No</th>
				<th>Officer Name</th>
				<th>Office Phone No</th>
				<th>Office Fax No</th>
				<th>Residence Ph No/Mobile</th>
				<th>EmailId</th>
			</tr>
			<logic:iterate id="bean" name="contactDetails">
				<tr class="odd">
					<td><bean:write name="bean" property="contact_id" />
					</td>
					<td><bean:write name="bean" property="officer_name" />
					</td>
					<td><bean:write name="bean" property="ph_office" />
					</td>
					<td><bean:write name="bean" property="fax_office" />
					</td>
					<td><bean:write name="bean" property="ph_residence" />
					</td>
					<td><bean:write name="bean" property="email" />
					</td>
				</tr>
			</logic:iterate>
		
		</table>
		</td></tr>
		</table>
		<div align="center"><html:button styleClass="button" property="" onclick="closeReport()">
					<bean:message key="button.close" />
				</html:button>
		</div>
	</div>
</div>	
