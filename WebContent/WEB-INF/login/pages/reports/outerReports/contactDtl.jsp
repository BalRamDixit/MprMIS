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
<html:form action="/outerAction">
	<div  style="padding-top: 10px; padding-bottom: 10px;">
   		<!-- Content -->
            <div  align="center">
			<table width="100%"  >
			<tr><td align="center" class="pageHeader" >ADDRESS</td></tr>
			<tr><td align="center"><a>DDU-GKY, Division, Ministry of Rural Development, <br/>1st Floor, Western Wing  
						Thapar House, 124, Janpath, New Delhi - 1100 01</a></td></tr>
			<tr><td align="center" class="pageHeader" >CONTACT DETAILS</td></tr>
			
			
			<table class="borderedPresentation" width="98%"> 
				<th colspan="6">DDU-GKY MoRD Team</th>
			<table  class="borderedPresentation" border="1" cellpadding="5" cellspacing="5" width="98%" >
						
			 	<thead>
				<tr>
				<th>Officer Name</th>
				<th>Designation</th>
				<th>Phone No</th>
				<th>Email-Id</th>
				</tr>
				</thead>				
				<tbody>
				<tr>
				<td>Shri Narendra Singh Tomar  </td>
				<td>Minister of Rural Development </td>
				<td>011-23782373,23782327 </td>
				<td>minister.rd@gov.in  </td>
				</tr>
				<tr>
				<td>Dr.Navneet Mohan Kothari</td>
				<td>PS to Minister</td>
				<td>011-23383548,23782373</td>
				<td> kotharin@nic.in</td>
				</tr>					
				<tr>
				<td>Shri Ram Kripal Yadav</td>
				<td>Minister of State (Rural Development)</td>
				<td>011-23388859, 23388879</td>
				<td>officemos-rd@gov.in </td>
				</tr>
				<tr>
				<td>Shri B C Behera</td>
				<td>Private Secretary to MOS (RD)</td>
				<td>011-23388859, 23388879</td>
				<td>bc.behera@nic.in</td>
				</tr>
				<tr>
				<td>Shri Amarjeet Sinha </td>
				<td>Secretary (RD)</td>
				<td>011-23384467, 23382230</td>
				<td>secyrd@nic.in,sinhaa5@nic.in</td>
				</tr>
				<tr>
				<td>Shri H Rama Krishan </td>
				<td>PPS to (SRD)</td>
				<td>011-23384467, 23382230</td>
				<td>secyrd@nic.in</td>
				</tr>							
				<tr>
				<td>Shri Atal Dulloo</td>
				<td>Joint Secretary (Skills-Additional Charge) </td>
				<td>011-23348753, 54</td>
				<td>atal.dulloo@nic.in</td>
				</tr>
				<tr>
				<td>Shri Shyam Sharma</td>
				<td>PA to JS(S)</td>
				<td>011-23348753, 54</td>
				<td>shyam.s14@ddugky.gov.in</td>
				</tr>
				<tr>
				<td>Shri Anil Subramaniam</td>
				<td>Director</td>
				<td>011-23743625, 26, 28, 29</td>
				<td>anil.sub@ddugky.gov.in</td>
				</tr>
				<tr>
				<td>Shri SL Sharma</td>
				<td>PA to DS(S)</td>
				<td>011-23743625, 26, 28, 29 </td>
				<td>shadi.l@ddugky.gov.in </td>
				</tr>
				<tr>
				<td>Shri Sanjay Kumar</td>
				<td>Under Secretary</td>
				<td>011-23743625, 26, 28, 29 </td>
				<td>sanjay.kmr70@nic.in</td>
				</tr>
				<tr>
				<td>Shri Shashi Bhusan Tiwari </td>
				<td>Under Secretary</td>
				<td>011-23743625, 26, 28, 29 </td>
				<td>shashi.bhusant@ddugky.gov.in</td>
				</tr>
				<tr>
				<td>Shri Ajit Kumar Senapati</td>
				<td>Section Officer</td>
				<td>011-23743625, 26, 28, 29 </td>
				<td>ajit.ksenapati@ddugky.gov.in</td>
				</tr>
				<tr>
				<td>Shri KK Raman</td>
				<td>PPS CAPART </td>
				<td>011-23348753, 54</td>
				<td>kk.raman@ddugky.gov.in </td>
				</tr>
				<tr>
				<td>Shri Shailendra Kumar</td>
				<td>Section Officer</td>
				<td></td>
				<td>kmr.shailendra71@gmail.com</td>
				</tr>
		   <!-- <tr>
				<td>Ms. Puloma Basu</td>
				<td>Research Officer CAPART</td>
				<td>011-23743625, 26, 28, 29</td>
				<td>puloma.b@ddugky.gov.in</td>
				</tr> -->
				<tr>
				<td>Shri Shyama Rao Chongli</td>
				<td>Research Officer CAPART</td>
				<td>011-23743625, 26, 28, 29</td>
				<td>syamala.raoc@ddugky.gov.in</td>
				</tr>			
				<tr>
				<td>Shri Praveen Kumar Kashyap</td>
				<td>Junior Statistical Officer</td>
				<td></td>
				<td>praveen.kashyap@gov.in</td>
				</tr>
				<tr>
				<td>Shri Sher Shingh</td>
				<td>UDC</td>
				<td>011-23743625, 26, 28, 29</td>
				<td>sher.singh14@ddugky.gov.in</td>
				</tr>
				</tbody>
		</table>
		</table>
		<br/>		  			
			<tr><td align="center">
			<table align="center" class="borderedPresentation" width="98%">
			<th colspan="6">DDU-GKY National Mission Team</th>					
			<table align="center" class="borderedPresentation" border="1" cellpadding="5" cellspacing="5" width="98%" >
			<thead>
			<tr>
				<th>Officer Name</th>
				<th>Designation</th>
				<th>Phone No</th>
				<th>Email-Id</th>
			</tr>
			</thead>
			<tbody>
			<tr >
					<td>Smt. Gayathri B Kalia</td>
					<td>Chief Operation Officer</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>gayathri.kalia@ddugky.gov.in</td>					
				</tr>
				<!-- <tr >
					<td>Shri Blesson Mathew</td>
					<td>Coordinator COO Office</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>cooskillsoffice@gmail.com</td>					
				</tr> -->
				<tr >
					<td>Shri Mojeeb Khan</td>
					<td>Chief Information Officer</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>mojeebudin.khan@ddugky.gov.in</td>					
				</tr>
				<!-- <tr >
					<td>Shri Rohit Chandra</td>
					<td>Head Standard, QA and HR</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>rohit.chandra22@ddugky.gov.in</td>					
				</tr> -->
				<tr >
					<td>Smt. Sheeja Nair </td>
					<td>Head, Partnerships</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>sheeja.nair@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Shri Abu Osama Saifi</td>
					<td>SME</td>
					<td>011-23743625, 26, 28, 32</td>
					<td>abu.osamasaifi@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Shri Anand Kumar Patney</td>
					<td>Head SGSY Projects</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>a.kpatney@ddugky.gov.in</td>					
				</tr>
				<!-- <tr >
					<td>Shri Rohtas Singh</td>
					<td>Head FMPA</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>rohtash.s@ddugky.gov.in</td>					
				</tr> -->
				<!-- <tr >
					<td>Shri Rahul Rana </td>
					<td>Manager Finance</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>rahul.rana@ddugky.gov.in</td>					
				</tr> -->
				<!-- <tr >
					<td>Shri Madhav Raghvan </td>
					<td>Manager MIS</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>madhav.raghavan@ddugky.gov.in</td>					
				</tr> -->
				<!-- <tr >
					<td>Shri Rajesh Natarajan</td>
					<td>Chief Branding Officer</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>rajesh.natarajan@ddugky.gov.in </td>					
				</tr> -->
				<tr >
					<td>Shri Senthil Rajan</td>
					<td>Mission Manager MIS</td>
					<td>011-23743625, 26, 28, 34</td>
					<td>senthil.rajan@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Smt. Vandana Tallur</td>
					<td>Manager QA&TC</td>
					<td>011-23743625, 26, 28, 35</td>
					<td>vandana.tallur@ddugky.gov.in</td>					
				</tr>
				<!-- <tr >
					<td>Shri Devesh Sharma</td>
					<td>Manager, Program Coordination</td>
					<td>011-23743625, 26, 28, 35</td>
					<td>in.devesh@gmail.com</td>					
				</tr> -->
				 <tr >
					<td>Shri Reeni Kurian</td>
					<td>Regional representative (N.E and West Bengal)</td>
					<td></td>
					<td>reeni.kurian@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Shri Satyavijay</td>
					<td>Senior Executive, FMPA</td>
					<td></td>
					<td>satyavijay.yss@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Ms. Tanya Kynadi</td>
					<td>Data Analytics</td>
					<td></td>
					<td>tanya.kynadi@gov.in</td>					
				</tr>
				<tr >
					<td>Ms. Simerneet Bajwa</td>
					<td>Manager, Communications</td>
					<td>011-23743625, 26, 28, 34</td>
					<td>simerneet.bajwa@ddugky.gov.in</td>					
				</tr>
				<!-- <tr >
					<td>Ms. Kim Fernandes</td>
					<td>Monitoring and Evaluation Analyst</td>
					<td>011-23743625, 26, 28, 35</td>
					<td>kimberlyfernandes@gmail.com</td>					
				</tr> -->
				<tr >
					<td>Shri Gaurav Singh Chhabra</td>
					<td>Senior Executive FMPA</td>
					<td>011-23743625, 26, 28, 34</td>
					<td>gaurav.chhabra@ddugky.gov.in </td>					
				</tr>
				<!-- <tr >
					<td>Shri Kumar Manish</td>
					<td>Senior Program Executive,Skills</td>
					<td>011-23743625, 26, 28, 35</td>
					<td>manish.kr31@ddugky.gov.in</td>					
				</tr> -->
				<!-- <tr >
					<td>Ms. Tanya Grover</td>
					<td>Executive Skills</td>
					<td>011-23743625, 26, 28, 35</td>
					<td>tanya.grover@ddugky.gov.in</td>					
				</tr> -->
				<tr >
					<td>Smt. Jyoti Chawla</td>
					<td>Coordinator Projects</td>
					<td>011-23743625, 26, 28, 35</td>
					<td></td>					
				</tr>
				<tr >
					<td>Shri Umesh Kumar</td>
					<td>Office Assistant</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>umesh.k14@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Shri Chetan Kumar</td>
					<td>Office Assistant</td>
					<td>011-23743625, 26, 28, 29</td>
					<td>chetan.knandan@ddugky.gov.in</td>					
				</tr>
				<tr >
					<td>Shri Mohan Singh </td>
					<td>DEO</td>
					<td>011-23743625, 26, 28, 29</td>
					<td></td>					
				</tr>
			</tbody>
		</table>
		</table>
		<br/>			
		
		<table class="borderedPresentation" width="98%">
		<th colspan="6"> DDU-GKY Nodal Person in State Government</th>				
		<table  class="borderedPresentation" border="1" cellpadding="5" cellspacing="5" width="98%" >
			<thead>	
			<tr>
				<th>State</th>
				<th>Officer Name</th>
				<th>Designation</th>
				<th>Phone No</th>
				<th>Email-Id</th>
				
			</tr>
			</thead>	
			<tbody>
			<tr >
					<td>Arunachal Pradesh</td>					
					<td>Mrs. N Breeze Dove</td>
					<td>DD</td>
					<td>9436041977</td>
					<td>ningyomi_doye@yahoo.com</td>
				</tr>
				<tr >
					<td>Andhra Pradesh</td>					
					<td>Shri Vijay Mannala</td>
					<td>Mission Manager,EGMM</td>
					<td>9866900079</td>
					<td>vijay.mannala@gmail.com</td>																		
				</tr>					
				<tr >
					<td>Assam</td>					
					<td>Shri  Amrit Borah</td>
					<td>PM, Skills &amp; Placement</td>
					<td>9085440957</td>
					<td>amritborah@gmail.com</td>													
				</tr>					
				<tr >
					<td>Bihar</td>					
					<td>Shri  Jitendra Kumar</td>
					<td>SPM-Jobs</td>
					<td>9771478313</td>
					<td>jitendra@brlp.in</td>								
				</tr>			
				<tr >
					<td>Chhattisgarh</td>					
					<td>Shri Pankaj Ekka</td>
					<td>SPM Skills &amp; Livelihoods</td>
					<td>9406809100</td>
					<td>pankaj_ekka@rediffmail.com</td>									
				</tr>
				<tr >
					<td>Gujarat</td>					
					<td>Heital Jani</td>
					<td>SPM</td>
					<td>7567897468</td>
					<td>apmstp@glpc.co.in</td>							
				</tr>			
				<tr >
					<td>Haryana</td>					
					<td>Dr. S C Anand</td>
					<td>SPM, Skills</td>
					<td>9888194707</td>
					<td> spmhsrlm.mf-hsrlm@nic.in</td>								
				</tr>					
				<tr >
					<td>Himachal Pradesh</td>					
					<td>Shri B K Sharma</td>
					<td>Nodal Person</td>
					<td>9418493388</td>
					<td>nrlmhp@gmail.com</td>		
				</tr>
				<tr >
					<td>Jharkhand</td>					
					<td>Shri Vinay Kumar Pandey</td>
					<td>SPM</td>
					<td>9431103066</td>
					<td>vinayppandey@yahoo.com</td>																			
				</tr>							
				<tr >
					<td>Karnataka</td>					
					<td>Shri Shaik Jalaluddin Basha</td>
					<td>SPAM-Skills</td>
					<td>9066564054</td>
					<td>amskillsksrlm@gmail.com </td>																			
				</tr>		
				<tr >
					<td>Kerala</td>					
					<td>Shri Bibind Vasu</td>
					<td>SPM</td>
					<td>9037089590</td>
					<td>bibindvasu@gmail.com</td>								
				</tr>
				<tr >
					<td>Madhya Pradesh</td>					
					<td>Shri Dhirendra Singh</td>
					<td>SPM</td>
					<td>8349901014</td>
					<td>dhirendra1982@gmail.com</td>								
				</tr>					
				<tr >
					<td>Maharashtra</td>					
					<td>Shri Ramchandra Kowligi </td>
					<td>SPM</td>
					<td>9004391619</td>
					<td>msrlmskills@gmail.com</td>								
				</tr>					
				<tr >
					<td>Manipur</td>					
					<td>Shri Naorem</td>
					<td>Consultant</td>
					<td>8729911992</td>
					<td>naorem.kk1780@gmail.com</td>																			
				</tr>
				<tr >
					<td>Meghalaya</td>					
					<td>Smt. G.S.Lyngdem</td>
					<td>COO, Skills</td>
					<td>9856028958</td>
					<td>megsird@gmail.com</td>																				
				</tr>
				<tr >
					<td>Mizoram</td>					
					<td>Mrs. Zosangi</td>
					<td>Addl.CEO</td>
					<td>9436372559</td>
					<td>srlm.mizoram@gmail.com</td>
				</tr>
				<tr >
					<td>Nagaland</td>					
					<td>Shri Menuoneituo Chadi</td>
					<td>COO, Skills</td>
					<td>9862286884</td>
					<td>chadinsrlm@gmail.com</td>
				</tr>
				<tr >
					<td>Odisha</td>					
					<td>Shri OM Prakash</td>
					<td>SPM</td>
					<td>9437190119</td>
					<td>ormashq@gmail.com</td>
				</tr>
				<tr >
					<td>Punjab</td>					
					<td>Shri Parashar</td>
					<td>SPM</td>
					<td>9814554833</td>
					<td>psdmission@gmail.com</td>	
				</tr>
				<tr >
					<td>Rajasthan</td>					
					<td>Shri V Pareek</td>
					<td>GM</td>
					<td>9829022566</td>
					<td>vishwaspareek@rediffmail.com </td>
				</tr>
				<tr >
					<td>Sikkim</td>					
					<td>Ms. Durga Upreti</td>
					<td>Nodal Person</td>
					<td>9434241430</td>
					<td>durga.upreti94@gmail.com</td>
				</tr>
				<tr >
					<td>Tamil Nadu</td>					
					<td>Shri Veerannan</td>
					<td>JD</td>
					<td>9445034359</td>
					<td>adrdcb@ymail.com</td>
				</tr>
				<tr >
					<td>Telangana</td>					
					<td>Shri SB Rao</td>
					<td>SPM EGMM</td>
					<td>9849906066</td>
					<td>egmmit@gmail.com</td>
				</tr>			
				<tr >
					<td>Tripura</td>					
					<td>Shri Avijit Chowdhury</td>
					<td>PM S&amp;P</td>
					<td>8415986941</td>
					<td>pm.sp.trlm@gmail.com</td>
				</tr>
				<tr >
					<td>Uttar Pradesh</td>					
					<td>Shri Md. Arshi</td>
					<td>SPM Skills</td>
					<td>9415010989</td>
					<td>arshi1967@rediffmail.com</td>
				</tr>
				<tr >
					<td>Uttarakhand</td>					
					<td>Dr. Bebni Prabhakar</td>
					<td>COO</td>
					<td>9412382091</td>
					<td>pcbebni@gmail.com</td>
				</tr>
				<tr >
					<td>West Bengal</td>					
					<td>Shri NC Manna</td>
					<td>SPM Skills</td>
					<td>9432897004</td>
					<td>ncmanna100@gmail.com</td>
				</tr>
			</tbody>											
		</table>
		</table>
		</tr>
		</table>
		<div align="center"><html:button styleClass="button" property="" onclick="closeReport()">
					<bean:message key="button.close" />
				</html:button>
		</div>
	</div>
</div>	
</html:form>
