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
			<div class="outerTBL">
			<table width="100%" align="center" style="padding-down: 7px;">			
			<tr>
				<th colspan="2" align="center" class="pageHeader">TRAINING RESOURCES</th>
			</tr>
			
					<tr>		
					<td align="center">
					<table align="center" class="borderedPresentation" width="90%">
							
						
						<tr>
						<th width="20px" class="folder">1</th>
						<th>BAREFOOT TECHNICIAN (BFT) CERTIFICATION PROGRAM  </th>							
						</tr>
						
						<tr>	
						<tr>
						<th width="20px">a)</th>
						<th> TRAINING CONTENT OF BFT Module  (Download of individual Chapters) </th>
						</tr>
						
						<tr>
							<th></th>																
							<td> Unit 1.1 Measurements ( <a href="DocumentsForDownload/knowledgeBank/Unit1.1measurementsp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit1.1measurements_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th> </th>
							<td> Unit 1.2 Maps and Sketches ( <a href="DocumentsForDownload/knowledgeBank/Unit1.2maps&Sketches_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit1.2maps&Sketches_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>					      	
							<tr>
							<th></th>
							<td> Unit 1.3 Construction Technology ( <a href="DocumentsForDownload/knowledgeBank/Unit1.3ConstructionTech_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit1.3ConstructionTechp15ver_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 1.4 Basic Surveying and Setting Out( <a href="DocumentsForDownload/knowledgeBank/Unit1.4BasicSurveyingandSettingOutp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit1.4BasicSurveyingandSettingOutp1_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 1.5 Rates and Measurement ( <a href="DocumentsForDownload/knowledgeBank/Unit1.5RatesAndMeasurementp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit1.5RatesAndMeasurementp_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 2.1 Key Features( <a href="DocumentsForDownload/knowledgeBank/Unit2.1KeyFeaturesp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit2.2PermissibleWorksp_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 2.2 Permissible Works ( <a href="DocumentsForDownload/knowledgeBank/2.2PermissibleWorks_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit2.2PermissibleWorksp_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 2.2.1 Roads ( <a href="DocumentsForDownload/knowledgeBank/Unit2.2.1Roads_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit2.2.1Roadsp1_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 2.3 MGNREGS Documents( <a href="DocumentsForDownload/knowledgeBank/Unit2.3MGNREGSDocsFormsAndRegistersp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit2.3MGNREGSDocsFormsAndRegistersp_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>
							<tr>
							<th></th>
							<td> Unit 2.4 ICT and Reports ( <a href="DocumentsForDownload/knowledgeBank/Unit2.4ICTandReports_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/Unit2.4ICTandReports_Hindi.pdf" target="_blank">Hindi</a> )</td>
							</tr>	
							<tr>
							<th width="20px">b)</th>
							<th> HANDBOOK : Ready Reckoner for BFTs
							( <a href="DocumentsForDownload/knowledgeBank/c.Handbookp_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/c.Handbookp1_Hindi.pdf" target="_blank">Hindi</a> )
							 </th>
							</tr>
							<tr>	
							<tr>
							<th width="20px">c)</th>
							<th>SESSION PLAN FOR BFTs TRAINING MODULE 
							( <a href="DocumentsForDownload/knowledgeBank/d.SessionPlanP_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/d.SessionPlan_Hindi.pdf" target="_blank">Hindi</a> ) </th>
							</tr>
							
					</table>
					<table align="center" class="borderedPresentation" width="90%">
							
						
							<tr>
							<th width="20px" class="folder"></th>
							<th>TRAINING MODULE FOR BFT TRAINERS </th>							
							</tr>
							
							<tr>
							<th width="20px">d)</th>
							<th> TRAINERS GUIDE FOR BFT TRAINING (COMPLETE)  
							( <a href="DocumentsForDownload/knowledgeBank/a.BFT_Trainers_Guide_English.pdf" target="_blank">English</a> / 
                            <a href="DocumentsForDownload/knowledgeBank/a.BFT_Trainer_Guide_Hindi.pdf" target="_blank">Hindi</a> )</th>						
							</tr>
							<tr>
							<th width="20px">e)</th>
							<th>SESSION PLAN  FOR TOT MODULE(For Trainers of BFTs) 
							( <a href="DocumentsForDownload/knowledgeBank/e.Session_Plan_ToT.xlsx" target="_blank">English</a></th>
							</tr>						
														
							<tr>
							<th>f)</th>
							<th>PRESENTATIONS 
                            <a href="http://nrega.nic.in/Netnrega/ppt_cso.aspx" target="_blank"> Go to link </a></th>
							</tr>
							
						</table>
						<table align="center" class="borderedPresentation" width="90%">							
						
							<tr>
							<th width="20px" class="folder">2</th>
							<th>DDU-GKY TRAINING TOOL KIT </th>							
							</tr>							
							<tr>
							<th>2.1.1</th>
							<th> ASSESSMENT AND CERTIFICATION PART-1  
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1Assessment_and_Certification-Part1_Final.pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>2.1.1.1</th>
							<td> Alignment of MES modules with NSQF (Handout) 
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.1Alignment_of_MES_modules_with_NSFQ.pdf" target="_blank">(English)</a></td>
							</tr>
							<tr>
							<th>2.1.1.2</th>
							<td> Contact details of various SSCs (Handout)   
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.2_Contact-Details-SSC.pdf" target="_blank"> (English) </a></td>
							</tr>
							<tr>
							<th>2.1.1.3</th>
							<td> Contact details of RDATs (Handout) 
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.3_Contact-Details-RDAT.pdf" target="_blank"> (English) </a></td>
							</tr>
							<tr>
							<th>2.1.1.4</th>
							<td> Summary of QP-NOS of various Trades (Handout)
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.4_Summary_QP-NOS_list_updated.pdf" target="_blank"> (English) </a></td>
							</tr>
							<tr>
							<th>2.1.1.5</th>
							<td> Revised MES Course List (Handout) 
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.5_Revised_MES_Course_List.pdf" target="_blank"> (English) </a></td>
							</tr>
							<tr>
							<th>2.1.1.6</th>
							<td> List of Tools and Equipment for NCVT Courses (Handout)
                            <a href="DocumentsForDownload/knowledgeBank/2.1.1.6Tools_and_Equipment_List_Approved_Courses_NCVT-COMPLETE LIST-578-16Feb2016.xlsx" target="_blank"> (English) </a></td>
							</tr>
							<tr>
							<th>2.1.2</th>
							<th> ASSESSMENT AND CERTIFICATION PART-2  
                            <a href="DocumentsForDownload/knowledgeBank/2.1.2Assessment_and_Certification_Part2_Final.pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>2.2</th>
							<th> DDU-GKY Policy Guidelines 
                            <a href="DocumentsForDownload/knowledgeBank/2.2POLICY_GUIDELINES_DDUGKY-Final.pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>2.3</th>
							<th> Due Diligence and Inspection   
                            <a href="DocumentsForDownload/knowledgeBank/2.3DueDiligence_Inspections_Defaults-Final.pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>2.4</th>
							<th> Process of applying for Permanent Registration Number   
                            <a href="DocumentsForDownload/knowledgeBank/2.4Process_of_applying_for_Permanent_Registration_Number.pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>2.5</th>
							<th> Standard Operating Procedures (SOP Part 1& 2)   
                            <a href="DocumentsForDownload/knowledgeBank/2.5SOP1&2.pdf" target="_blank"> (English) </a></th>
							</tr>							 
						</table>
						<table align="center" class="borderedPresentation" width="90%">
													
							<tr>
							<th width="20px" class="folder">3</th>
							<th>Training in Public Financial Management System (PFMS) &nbsp;&nbsp;<img src="images/new.gif" alt="New" /> </th>							
							</tr>							
							<tr>
							<th>3.1</th>
							<th> Presentation on PFMS Training 
                            <a href="DocumentsForDownload/knowledgeBank/3.1Presentation_for_PFMS_Training_(English).pdf" target="_blank"> (English) </a></th>
							</tr>
							<tr>
							<th>3.2</th>
							<th> PFMS Video Manual Links  
                            <a href="DocumentsForDownload/knowledgeBank/3.2PFMS_video_manual_links.pdf" target="_blank"> (English) </a></th>
							</tr>							
							<tr>
							<th>3.3(a)</th>
							<td> Contact details -PFMS Nodal persons (States) 
                            <a href="DocumentsForDownload/knowledgeBank/3.3(a)-Contact_details-PFMS_Nodal_persons_States).pdf" target="_blank">(English)</a></td>
							</tr>
							<tr>
							<th>3.3(b)</th>
							<td> Contact details -PFMS Nodal persons (CTSAs)    
                            <a href="DocumentsForDownload/knowledgeBank/3.3(b)-Contact_details_of_PFMNodal_persons(CTSA).pdf" target="_blank"> (English) </a></td>
							</tr>							 
						</table>					
						<table align="center" class="borderedPresentation" width="90%">						
							<tr>
							<th width="20px" class="folder">4</th>
							<th>SECC User Guide <a href="DocumentsForDownload/knowledgeBank/4.SECC_User_Guide.pdf" target="_blank"> (English) </a>&nbsp;&nbsp; <img src="images/new.gif" alt="New" /> </th>							
							</tr>							
					</table>
					</td>
					</tr>	
					</table>				
							
		<div align="center"><html:button styleClass="button" property="" onclick="closeReport()">
					<bean:message key="button.close" />
				</html:button>
		</div>
	
</div>	
</html:form>