<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE>

<script>
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
<html:form action="login/monitoringProgressReport">
<body>
 
<table width="100%" class="pageHeaderTable">
	<tr>
		<td align="center" class="pageHeader">MONITORING PROGRESS REPORT</td>
   </tr>
</table>
 <div style="overflow-x:scroll; overflow-y:scroll;" id="Export">	
			<table  width="100%" align="center"  id="tableId" class="display" border="1">			
				 
				 <tr>
				  	<th align="center" >S.No.</th>
				     <th align="center" >State Name</th>
				     <th align="center" >PIA PRN</th>
				     <th align="center" >PIA Name</th>
	                 <th align="center" >No of  Projects Detail</th> 
	                 <th align="center">No of Project sanction Details</th>
	                 <th align="center" >No of Project Sanction Modified</th>
	                 <th align="center">No of Project With Trade Target</th>
	                 <th align="center">No of Project with District Target</th>
	                 <th align="center" >No of Project with PPWS</th>
	                 <th align="center">No Of Project With 1st Installment</th>
	                 <th align="center" >No Of Project With 2nd Installment</th>
	                 <th align="center">No Of Project With 3rd Installment </th>
	                 <th align="center">No Of Project With 4th Installment</th>
	                  <th align="center" >No of Training Center</th> 
	                 <th align="center">No of Training Center those Due diligence entered</th>
	                 <th align="center" >No of Training Center with Trade Capacity</th>
	                 <th align="center">No of Batch data those are completed before 31st March 2017 </th>
	                 <th align="center">No of Batch Creation After 31st March 2017</th>
	                 <th align="center" >No of Batches Batch Completion After 31st March 2017</th>
	                 <th align="center" >No of Inspection</th> 
	                 <th align="center" >No of Batches whose Placement is marked</th>
	                 <th align="center" >No of Candidates Trained </th>
	                 <th align="center" > No of Candidate Placed </th>
	             </tr> 
	             <logic:present name="formBean">
	             	<c:set var="indexCount" value="1" />
	            		 <logic:iterate id="formBean" name="formBean" indexId="index">
	            		 <tr>
			            	 	<td>${indexCount}</td> 
  								<td>${formBean.field}</td>
  								<td>${formBean.field22}</td>
  								<td>${formBean.field23}</td>
  								<td>${formBean.field2}</td>
  								<td>${formBean.field3}</td>
  								<td>${formBean.field4}</td>
  								<td>${formBean.field5}</td>
  								<td>${formBean.field6}</td>
  								<td>${formBean.field7}</td>
  								<td>${formBean.field9}</td>
  								<td>${formBean.field10}</td>
  								<td>${formBean.field11}</td>
  								<td>${formBean.field12}</td>
  								<td>${formBean.field8}</td>
  								<td>${formBean.field13}</td>
  								<td>${formBean.field14}</td>
  								<td>${formBean.field15}</td>
  								<td>${formBean.field16}</td>
  								<td>${formBean.field17}</td>
  								<td>${formBean.field18}</td>
  								<td>${formBean.field19}</td>
  								<td>${formBean.field20}</td>
  								<td>${formBean.field21}</td>
  							<c:set var="indexCount" value="${indexCount + 1}" />
  							</tr>
						</logic:iterate>
					</logic:present>
          </table>
</div >
<div align="center">
<!-- 		<input name="Button" type="button" class="button" value="Retrieve" onclick="Retrieve()" /> -->
			<button id="btnExport" class="button" type="button" onclick="fnExcelReport('tableId');"> EXPORT To Excel</button>
			<button id="btnExport" class="button" type="button" onclick="createPdf('Export');"> EXPORT To PDF</button>
</div>
<a href="" id="fileToDownload" >open</a>
</body>
</html:form>
