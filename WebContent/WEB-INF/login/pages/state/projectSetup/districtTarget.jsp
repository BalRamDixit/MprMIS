<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>


<!--script for menu-->
<script type="text/javascript">
$(document).ready(function () {
	$('#example').DataTable();
	
  });


function deleteDetail(id){
	 document.forms[0].action="districtTarget.do?methodName=delete&id="+id+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
}


function editDetail(projectId){
	 

	 document.forms[0].action="districtTarget.do?methodName=edit&id="+projectId+'&reqtrack='+tokenValue;
	 document.forms[0].submit();
	  
}

 	 
function addDetail(){
	  
	 document.forms[0].action="districtTarget.do?methodName=addDetail"+"&"+tokenParameter+"="+tokenValue;;
	 document.forms[0].submit(); 

}



function showRemark(str){
	 
	if (str!='0')
	{
		document.getElementById("showTable").style.display = "";	
 			
	} else{
		document.getElementById("showTable").style.display = "none";
 	}
}
$(document).ready(function()
{
	showRemark("0");
});




function save(){
	
	var status=window.confirm('Are You Sure You Want tO Save ?');
		if(status==true){
			document.forms[0].action="districtTarget.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
			document.forms[0].submit();
		}
	}
  
</script>

 <html:form action="login/districtTarget">
			<table width="100%" class="pageHeaderTable">
				<tr>
					<td align="center" class="pageHeader">DISTRICT TARGET</td>					
               </tr>
			</table>			
	<table width="100%" align="center" class="display" id="example">
			 	<thead>
				<tr>
					<th>S.No.</th> 
					<th>Project Id</th>
					<th>Edit</th>
					<th>Delete</th>
					</tr>
				</thead>
				<tfoot>
				 <tr>	 
					<th>S.No.</th> 
					<th>Project Id</th>
					<th>Edit</th>
					<th>Delete</th>
					</tr>
				</tfoot>
				<tbody>
						<logic:present name="ViewList">
							<c:set var="indexCount" value="1" />
							<logic:iterate id="list" name="ViewList" indexId="index">
								<tr>
									<td>${indexCount}</td>
									<td>${list[0]}</td>
									<td><a href="#" onclick="editDetail('${list[0]}')"><img src="images/EditIcon.png" alt="" height="14px" width="17px" /></a></td>
									<td><a href="#" onclick="deleteDetail('${list[0]}')"><img src="images/DeleteIcon.png" alt="" height="14px" width="17px" /></a></td>
								</tr>
								<c:set var="indexCount" value="${indexCount + 1}" />
							</logic:iterate>
						</logic:present>
						</tbody>
				</table>
			  
			  
			  <div align="center"><html:button styleClass="CompareButton" property="" styleId="addB" onclick="addDetail();">ADD NEW</html:button>
			 	</div>
			 
	
</html:form>
 