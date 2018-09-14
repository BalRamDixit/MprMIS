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


<html:form action="/outerAction">
	<div class="outerTBL">
		<table width="100%" align="center" style="padding-down: 7px;">
			<tr>
				<th colspan="2" align="center" class="pageHeader">Aajeevika Skills Presentations</th>
			</tr>
			<tr>
				<td>
					<table align="center" class="borderedPresentation" width="100%">
						<tr>
							<th width="20px">1</th>
							<td width="45%;"><a
								href="DocumentsForDownload/Agriculture.pptx" target="_blank">Sector
									Skill Council on Agriculture</a></td>
							<th width="20px">2</th>
							<td width="45%;"><a
								href="DocumentsForDownload/Automotive.ppt" target="_blank">Automotive
									Skills Development Council </a></td>
						</tr>
						<tr>
							<th>3</th>
							<td><a href="DocumentsForDownload/BFSI .ppt" target="_blank">The
									BFSI Sector Skill Council of India (BFSI SSC)</a></td>
							<th>4</th>
							<td><a href="DocumentsForDownload/Capital Goods .pptx"
								target="_blank">Capital Goods Skills Council</a></td>
						</tr>
						<tr>
							<th>5</th>
							<td><a href="DocumentsForDownload/Construction.ppt"
								target="_blank">Integration Model with MoRD</a></td>
							<th>6</th>
							<td><a href="DocumentsForDownload/Electronics.pptx"
								target="_blank">Electronics Sector Skills Council of India</a></td>
						</tr>
						<tr>
							<th>7</th>
							<td><a href="DocumentsForDownload/Gems & Jewellery.ppt.pptx"
								target="_blank">Gem and Jewellery Skill Council of India</a></td>
							<th>8</th>
							<td><a href="DocumentsForDownload/Healthcare.ppt"
								target="_blank">Health Care Sector Skill Council</a></td>
						</tr>
						<tr>
							<th>9</th>
							<td><a href="DocumentsForDownload/Indian Plumbing.pptx"
								target="_blank">Indian Plumbing Skills Council (IPSC)</a></td>
							<th>10</th>
							<td><a href="DocumentsForDownload/Leather.ppt"
								target="_blank">Leather Sector Skill Council (SSC)</a></td>
						</tr>
						<tr>
							<th>11</th>
							<td><a href="DocumentsForDownload/Logistics SSC.ppt"
								target="_blank">Logistics Sector Skill Council (SSC)</a></td>
							<th>12</th>
							<td><a href="DocumentsForDownload/Media.pptx"
								target="_blank">Media and Entertainment Skills Council
									(MESC) </a></td>
						</tr>
						<tr>
							<th>13</th>
							<td><a href="DocumentsForDownload/Retail.ppt"
								target="_blank">RASCI: Sector Skill Council for Retail</a></td>
							<th>14</th>
							<td><a href="DocumentsForDownload/Rubber.pptx"
								target="_blank">RSDC: Rubber Skill Development Council</a></td>
						</tr>
						<tr>
							<th>15</th>
							<td><a href="DocumentsForDownload/Security.pptx"
								target="_blank">Security Knowledge And Skill Development
									Council</a></td>
							<th>16</th>
							<td><a href="DocumentsForDownload/Telecom.pptx"
								target="_blank"> Telecom Sector Skill Council</a></td>
						</tr>
					</table>
					</td>
			</tr>
		</table>
	</div>
</html:form>

