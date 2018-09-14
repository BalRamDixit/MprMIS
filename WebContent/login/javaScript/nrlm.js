function trim(val) {
	var ret = val.replace(/^\s+/, '');
	ret = ret.replace(/\s+$/, '');
	return ret;
}

function isBlank(inputField,fieldName){
	var blankFlag = false;
	if (trim(inputField.value) == "") {
		alert("Please enter " + fieldName);
		inputField.focus();
		blankFlag =  true;
	} 
	return blankFlag;
}

function toggle(obj) {

	var el = document.getElementById(obj);

	el.style.display = (el.style.display != 'none' ? 'none' : '' );

}
function showDV(obj1,obj2) {

	var el1 = document.getElementById(obj1);
	var el2 = document.getElementById(obj2);


	el1.style.display = (el1.style.display != 'none' ? 'none' : '' );
	el2.style.display = (el2.style.display != 'none' ? 'none' : '' );


}


function showImage(obj,image) {

	var el1 = document.getElementById(obj);
 var el2 = document.getElementById(image);



	el1.style.display = (el1.style.display != 'none' ? 'none' : '' );
	el2.style.display = (el2.style.display != 'none' ? 'none' : '' );



}

function showPopUp(obj)
{
document.getElementById(obj).style.display = "";
document.getElementById("overlay").style.display = "";
}
function hidePopUp(obj)
{
document.getElementById(obj).style.display = "none";
document.getElementById("overlay").style.display = "none";
}

function registerCompanyProducts(targetURL){
	$.ajax({
		  url: targetURL,
		  data:$('#productGroupForm').serialize(),
		  type: 'POST',		  
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){
// alert(data);
			  // var contact = "<tr><td
				// class='row'>"+document.getElementById('contactPerson.personName').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.personDesignation').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.mobileNumber').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.email').value+"</td></tr>";
			  $("#productListTable").html(data); 
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
			  alert("textStatus : " +textStatus);
		  }
		});	
}

function registerCompanyContactPerson(targetURL){
	$.ajax({
		  url: targetURL,
		  data:$('#contactPersonForm').serialize(),
		  type: 'POST',		  
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){
// alert(data);
			  // var contact = "<tr><td
				// class='row'>"+document.getElementById('contactPerson.personName').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.personDesignation').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.mobileNumber').value+"</td><td
				// class='row'>"+document.getElementById('contactPerson.email').value+"</td></tr>";
			  $("#contactPersonTable").html(data); 
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
			  alert("textStatus : " +textStatus);
		  }
		});
}

function validate(form_id,email) {
	 
	   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	   var address = document.forms[form_id].elements[email].value;
	   
	   if(address !=""){
	   if(reg.test(address) == false) {	 
	      alert('Invalid Email Address');
	      return false;
	   }
	   return true;
	   }
	   if(address ==""){		  
		   return true;
		   }
	}

function isNumberKey(event){
	var charCode = (event.which) ? event.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57)){	   
      return false;
	}else{
		return true;
	}
}
function isDoubleKey(event){
	var charCode = (event.which) ? event.which : event.keyCode;	
   if ((charCode > 31 && (charCode < 48 || charCode > 57) && charCode !=46 )){	   
      return false;
	}else{
		return true;
	}
}

/**
 * This method will fetch data from Server - expected result is rows without
 * table tag. Data should be pushed as first row
 * 
 * @param targetUrl
 * @param formId
 * @param targetTable
 */
function searchRetailer(targetUrl, formId, targetTable){
	$.ajax({
		  url: targetUrl,
		  data:$('#'+formId).serialize(),
		  type: 'POST',
		  dataType: 'html',
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){		  			
			  $('#'+targetTable).html(data);
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
		  }
	});
}

$(document).ready(function(){
	// TODO need to put all the value which should be ready on page load
});

/*
 * This function will work only for Drop down boxes.
 */
function fieldPopulator(sourceObjectId, targetUrl, targetField){
	var index = document.getElementById(sourceObjectId).selectedIndex;		
	var paramValue = document.getElementById(sourceObjectId).options[index].value;
	var contextPath = document.getElementById('contextPath').value;
	
	if(document.getElementById('optionList')!= null){
		document.getElementById('optionList').innerHTML='';
		$(document.getElementById('optionList')).remove();		
	}
	
	$('#'+targetField).html("<option>Loading...</option>");
	$.ajax({
	  url: targetUrl,
	  data:'inputFilterData='+paramValue+'&'+buildLinkToken('',true),
	  type: 'POST',
	  cache:false,
	  dataType: 'html',
	  success: function(data, textStatus, jqXHR){
		document.getElementById('tempToknContaner').innerHTML=data;		
		var tempOptionList = '<OPTION value="0" selected="selected">';
		if(document.getElementById('optionList') == null){
			tempOptionList = '<OPTION value="0" selected="selected">Select &nbsp; </OPTION><OPTION value="0">No Data</OPTION>';
		}else{ 
			tempOptionList = tempOptionList+ document.getElementById('optionList').innerHTML;
		}	
		var target = document.getElementById(targetField);
		$(target).html(tempOptionList);		
		document.getElementById('tokenContainer').innerHTML=document.getElementById('tempTokenHolder').innerHTML;
	  },
	  error: function(jqXHR, textStatus, errorThrown){
		  alert("error Occured: " + errorThrown);
	  }
	});
}


function fieldPopulatorForWarehouse(sourceObjectId,companyId, targetUrl, targetField){	
	var index = document.getElementById(sourceObjectId).selectedIndex;		
	var paramValue = document.getElementById(sourceObjectId).options[index].value;
	//var indexCompany = document.getElementById(companyId).selectedIndex;		
	var paramCompanyValue = document.getElementById(companyId).value;
	var contextPath = document.getElementById('contextPath').value;
	
	if(document.getElementById('optionList')!= null){
		document.getElementById('optionList').innerHTML='';
		$(document.getElementById('optionList')).remove();		
	}
	
	$('#'+targetField).html("<option>Loading...</option>");
	$.ajax({
	  url: targetUrl,
	  data:'inputFilterData='+paramValue+'&'+'companyId='+paramCompanyValue+'&'+buildLinkToken('',true),	 
	  type: 'POST',
	  cache:false,
	  dataType: 'html',
	  success: function(data, textStatus, jqXHR){		  
		document.getElementById('tempToknContaner').innerHTML=data;		
		var tempOptionList = '<OPTION value="0" selected="selected"></OPTION>';
		if(document.getElementById('optionList') == null){
			tempOptionList = '<OPTION value="0" selected="selected">Select &nbsp; </OPTION><OPTION value="0">No Data</OPTION>';
		}else{ 
			tempOptionList = tempOptionList+ document.getElementById('optionList').innerHTML;
		}	
		var target = document.getElementById(targetField);
		$(target).html(tempOptionList);		
		document.getElementById('tokenContainer').innerHTML=document.getElementById('tempTokenHolder').innerHTML;
	  },
	  error: function(jqXHR, textStatus, errorThrown){
		  alert("error Occured: " + errorThrown);
	  }
	});
}

	function listPopulator(sourceObjectId, targetUrl, targetField){
	var index = document.getElementById(sourceObjectId).selectedIndex;		
	var paramValue = document.getElementById(sourceObjectId).options[index].value;
	var contextPath = document.getElementById('contextPath').value;
	
	if(document.getElementById('optionList')!= null){
		document.getElementById('optionList').innerHTML='';
		$(document.getElementById('optionList')).remove();		
	}
	
	$('#'+targetField).html("<option>Loading...</option>");
	$.ajax({
	  url: targetUrl,
	  async:false,
	  data:'inputFilterData='+paramValue+'&'+buildLinkToken('',true),
	  type: 'POST',
	  cache:false,
	  dataType: 'html',
	  success: function(data, textStatus, jqXHR){		  
		document.getElementById('tempToknContaner').innerHTML=data;		
		var tempOptionList = '<OPTION value="0" selected="selected">';
		if(document.getElementById('optionList') == null){
			tempOptionList = '<OPTION value="0" selected="selected">Select &nbsp; </OPTION><OPTION value="0">No Data</OPTION>';
		}else{ 
			tempOptionList = tempOptionList+ document.getElementById('optionList').innerHTML;
		}	
		var target = document.getElementById(targetField);
		$(target).html(tempOptionList);		
		document.getElementById('tokenContainer').innerHTML=document.getElementById('tempTokenHolder').innerHTML;
	  },
	  error: function(jqXHR, textStatus, errorThrown){
		  alert("error Occured: " + errorThrown);
	  }
	});
}


$(document).ready(function(){
	// TODO need to put all the value which should be ready on page load
});

function compareDate(fieldToDate,fieldFromDate){
    var dateFieldTo =eval(fieldToDate);
    var dateFieldFrom =eval(fieldFromDate);
   if(dateFieldTo.value.length!=0&&dateFieldFrom.value.length!=0){
	 var toDate = dateFieldTo.value.substring(3,6)+dateFieldTo.value.substring(0,3)+ dateFieldTo.value.substring(6,10);
    var fromDate  = dateFieldFrom.value.substring(3,6)+dateFieldFrom.value.substring(0,3)+ dateFieldFrom.value.substring(6,10);
    var dateTo = Date.parse(toDate);
    var dateFrom = Date.parse(fromDate);
       if(dateTo>dateFrom){
        alert("Approve date cannot be greater than Valid Upto date");
        return false;
       }
  }
       return true;
}


 function validateEmail(emailAddress){ 
	 if(emailAddress==""){
		 return true;
	 }
	 var emailPattern =/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	 var isValid = emailPattern.test(emailAddress);
	 if(!isValid){
		 alert("Email Address is not valid."); 
		 return false; 
	 }else{
		 return true; 
		 } 
	 }


function rakePointAttributes()
{
	if(document.forms["rakePointInfo"]["rakePointId"].value=="")
	{	alert('Please enter Rake Point Id');
		return false;
	}
	else if(document.forms["rakePointInfo"]["rakePointName"].value=="")
		{
		alert('Please enter Rake Point Name');
		return false;
		}
	return true;
}
function portAttributes(){
	if(document.forms["portInfo"]["portId"].value==""){
		alert('Please enter Port Id');
		return false;
	}else if(document.forms["portInfo"]["portName"].value=="" ){
		alert('Plese enter Port Name');
		return false;
	}
	return true;
}

function stateDistrictValidation()
{
	if(document.getElementById("stateList").selectedIndex ==0)
		{
		alert('Please Choose State!');
		return false;
		}
	else if(document.getElementById("districtList").selectedIndex ==0)
	{
		alert('Please Choose District!');
		return false;
	}
	return true;	
}



function validateRakepoint()
{
	if(stateDistrictValidation() && rakePointAttributes())	
		return true;
	else
		return false;
}

function validatePort(){
	if(stateDistrictValidation() && portAttributes())
		return true;
	else 
		return false;
}

function validateZone(){
	if (isBlank(document.forms["zoneInfo"]["zoneId"],"Zone ID") == true){
		return false;
	}else if(isBlank(document.forms["zoneInfo"]["zoneName"],"Zone Name") == true){
		return false;
	}
	return true;
}

function validateState(){
	if(document.getElementById("zoneList").selectedIndex ==0){
		alert('Please Select Zone Name');
		return false;
	}if(isBlank(document.forms["stateInfo"]["stateId"],"State Id") == true){
		return false;
	}else if(isBlank(document.forms["stateInfo"]["stateName"],"State Name") == true){
		return false;
	}
	return true;
}

function validateDistrict(){
	if(document.getElementById("stateList").selectedIndex ==0){
		alert('Please Select State Name');
		return false;
	}if(isBlank(document.forms["districtInfo"]["districtId"],"District Id") == true){
		return false;
	}else if(isBlank(document.forms["districtInfo"]["districtName"],"District Name") == true){
		return false;
	}
	return true;
}

function validateSubDistrict(){
	if(isBlank(document.forms["subDistrictInfo"]["subDistrictId"],"Sub-District Id") == true){
		return false;
	}else if(isBlank(document.forms["subDistrictInfo"]["subDistrictName"],"Sub-District Name") == true){
		return false;
	}else if(document.getElementById("stateList").selectedIndex ==0){
		alert('Please Select State Name');
		return false;
	}else if(document.getElementById("districtList").selectedIndex ==0){
		alert('Please Select District Name');
		return false;
	}
	return true;
}

function existingSalesInfo(targetURL, targetTableId){
	var addstatus = $('#addNewSalesStatus').val();
	if(addstatus == 'true'){
		alert('One sale information is already pending to submit.');
		return;
	}
	var isValid = validateWholeSalersalesForm();
	//alert('buildLinkToken(targetURL,false) : ' + buildLinkToken(targetURL,false));
	if(isValid){
		$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'POST',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){
			  document.getElementById('tempToknContaner').innerHTML=data;
			 	$('#'+targetTableId).html(data);
			 	$('#addNewSalesStatus').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  $('#addNewSalesStatus').val('false');
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
		});
	}
}

function validateCompanySalesForm(){
	var compId = document.getElementById('companySalesInfo.companyId').value;
	if(compId == null || compId ==''){
		alert('Company Id is missing.');
		return false;
	}	
	var invNum = document.getElementById('invoiceNumber').value;
	if(invNum == null || invNum ==''){
		alert('Invoice Number is missing.');
		return false;
	}
	
	var invDate = document.getElementById('invoiceDate').value;
	if(invDate == null || invDate ==''){
		alert('Invoice Date is missing.');
		return false;
	}
	var state = document.getElementById('stateList').selectedIndex;
	if(state == null || state <= 0){
		alert('State is missing. Select a state.');
		return false;
	}
	var district = document.getElementById('districtList').selectedIndex;
	if(district == null || district <=0){
		alert('District is missing. Select a district.');
		return false;
	}
	var warehous = document.getElementById('warehouseList').selectedIndex;
	if(warehous == null || warehous ==''){
		alert('Warehouse is missing. Select a warehouse.');
		return false;
	}
	var dealer = document.getElementById('dealerId').value;
	if(dealer == null || dealer ==''){
		alert('Dealer id is missing.');
		return false;
	}
	return true;
}




function validateSupplyPlanStateForm(){
	
	var state = document.getElementById('stateList').selectedIndex;
	if(state == null || state <= 0){
		alert('State is missing. Select a state.');
		return false;
	}
	var year = document.getElementById('supplyYear').value;
	if(year == null || year ==''){
		alert('Please Select Year.');
		return false;
	}
	var month = document.getElementById('supplyMonth').value;
	if(month == null || month ==''){
		alert('Please Select Month.');
		return false;
	}
	
	return true;
}




function existingCompanySalesInfo(targetURL, targetTableId, tokenForm){
	var addstatus = $('#addNewSalesStatus').val();
	if(addstatus == 'true'){
		alert('One sale information is already pending to submit.');
		return;
	}
	var isValid = validateCompanySalesForm();
	if(isValid){
		$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'GET',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){	
				document.getElementById('tempToknContaner').innerHTML=data;
				$('#'+targetTableId).html(data);
			 	$('#addNewSalesStatus').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
		});
	}
}




function existingSupplyPlanStateInfo(targetURL, targetTableId, tokenForm){
	var addstatus = $('#addNewStateSupply').val();
	if(addstatus == 'true'){
		alert('One sale information is already pending to submit.');
		return;
	}
	var isValid = validateSupplyPlanStateForm();
	if(isValid){
		$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'GET',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){	
				document.getElementById('tempToknContaner').innerHTML=data;
				$('#'+targetTableId).html(data);
			 	$('#addNewStateSupply').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
		});
	}
}




function saveExistingCompanySalesInfo(targetURL, formId, targetTableId){
	
	if(validateUnitStockSales()){
		var formData = "";
		if(navigator.appName == 'Netscape' || navigator.appCodeName == 'Mozilla'){
			var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
			var plantId = document.getElementById('plantId').options[document.getElementById('plantId').selectedIndex].value;
			var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
			var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
			formData = 'companyId='+compId+'&plantId='+plantId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
			
		}else{
			formData = $('#'+formId).serialize();
		}
		
		$.ajax({
			  url: targetURL,		  
			  type: 'POST',
			  timeout:15000,
			  data:formData+buildLinkToken('',true),
			  dataType: 'html',
			  context: document.body,	 
			  success: function(data, textStatus, jqXHR){		  
				document.getElementById('tempTokenHolder').innerHTML=data;
				$('#'+targetTableId).html(data); 
			 	$('#addNewSalesStatus').val('false');
			 	var saveButton = document.getElementById('saveSalesTxnBtn');			 
			 	if(saveButton.getAttribute('disabled')){
			 		saveButton.removeAttribute('disabled');
			 	}
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;			 	
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
			  }
			});
	}
}


function saveExistingStateSupplyPlanInfo(targetURL, formId, targetTableId){
	
	if(validateStateSupplyPlanInfo()){
		var formData = "";
		if(navigator.appName == 'Netscape' || navigator.appCodeName == 'Mozilla'){
			var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
			var productGroupId = document.getElementById('productGroupId').options[document.getElementById('productGroupId').selectedIndex].value;
			var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
			var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
			var isImported = document.getElementById('isImported').options[document.getElementById('isImported').selectedIndex].value;
			formData = 'companyId='+compId+'&productGroupId='+productGroupId+'&isImported='+isImported+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
		}else{
			formData = $('#'+formId).serialize();
		}
		
		$.ajax({
			  url: targetURL,		  
			  type: 'POST',
			  timeout:15000,
			  data:formData+buildLinkToken('',true),
			  dataType: 'html',
			  context: document.body,	 
			  success: function(data, textStatus, jqXHR){		  
				document.getElementById('tempTokenHolder').innerHTML=data;
				$('#'+targetTableId).html(data); 
			 	$('#addNewStateSupply').val('false');
			 	var saveButton = document.getElementById('saveStateSupplyBtn');			 
			 	if(saveButton.getAttribute('disabled')){
			 		saveButton.removeAttribute('disabled');
			 	}
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;			 	
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
			  }
			});
	}
}




function validateWholeSalersalesForm(){
	var wholesalerId = document.getElementById('wholesalerId').value;
	var retailerId = document.getElementById('retailerId').value;
	var invoiceNo = document.getElementById('invoiceNumber').value;
	var invoiceDate = document.getElementById('invoiceDate').value;
	if(wholesalerId == null || wholesalerId==''){
		alert('Wholesaler Id is missing.')
		return false;
	}else
	if(retailerId == null || retailerId==''){
		alert('Retailer Id is missing.')
		return false;
	}else
	if(invoiceNo == null || invoiceNo==''){
		alert('Invoice Number missing.')
		return false;
	}else
	if(invoiceDate == null || invoiceDate==''){
		alert('Invoice date is missing.')
		return false;
	}else{
		return true;
	}
	
}

//Himanshu Buyer part Retailer

function existingRetailerSalesInfo(targetURL, targetTableId){
	var addstatus = $('#addNewSalesStatus').val();
	if(addstatus == 'true'){
		alert('One sale information is already pending to submit.');
		return;
	}
	var isValid = validateRetailersalesForm();
	//alert('buildLinkToken(targetURL,false) : ' + buildLinkToken(targetURL,false));
	if(isValid){
		$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'POST',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){
			  document.getElementById('tempToknContaner').innerHTML=data;
			 	$('#'+targetTableId).html(data);
			 	$('#addNewSalesStatus').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  $('#addNewSalesStatus').val('false');
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
		});
	}
}
function validateRetailersalesForm(){
	var retailerId = document.getElementById('retailerId').value;
	var buyerId = document.getElementById('buyerId').value;
	var invoiceNo = document.getElementById('invoiceNumber').value;
	var invoiceDate = document.getElementById('invoiceDate').value;
	if(retailerId == null || retailerId==''){
		alert('Retailer Id is missing.');
		return false;
	}else
	if(buyerId == null || buyerId==''){
		alert('Buyer Id is missing.');
		return false;
	}else
	if(invoiceNo == null || invoiceNo==''){
		alert('Invoice Number missing.');
		return false;
	}else
	if(invoiceDate == null || invoiceDate==''){
		alert('Invoice date is missing.');
		return false;
	}else{
		return true;
	}
	
}

function saveExistingSalesInfo(targetURL, formId, targetTableId){
	var isValid = validateWholeSalersalesForm();
	if(isValid && validateUnitStockSales()){
		
		var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
		var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
		var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
		var formData = 'companyId='+compId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;

		$.ajax({
			  url:targetURL,		  
			  type: 'POST',
			  data:formData+buildLinkToken('',true),
			  dataType: 'html',
			  context: document.body,	 
			  success: function(data, textStatus, jqXHR){		  
				document.getElementById('tempToknContaner').innerHTML=data;				
				$('#'+targetTableId).html(data);
			 	$('#addNewSalesStatus').val('false');
			 	var saveButton = document.getElementById('saveSalesTxnBtn');			 
			 	if(saveButton.getAttribute('disabled')){
			 		saveButton.removeAttribute('disabled');
			 	}
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
			  }
			});
	}
}
function existingRetailerUnitStockInfo(targetURL, targetTableId){
	var addstatus = $('#addNewSalesStatus').val();
	if(addstatus == 'true'){
		alert('One stock information is already pending to submit.');
		return;
	}	
	var retailerId = document.getElementById('retailerStockDto.retailerId').value;
	var stockDate = document.getElementById('stockDate').value;
	if(retailerId == null || retailerId==""){
		alert('Please enter valid retailer id');
		return;
	}
	
	if(stockDate == null || stockDate ==""){
		alert('Please enter Stock Date.');
		return;
	}

	
	$.ajax({
		  url: buildLinkToken(targetURL,false),		  
		  type: 'GET',
		  dataType: 'html',
		  success: function(data, textStatus, jqXHR){	
			document.getElementById('tempToknContaner').innerHTML=data;
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('true');
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
			  //alert('textStatus : ' +textStatus);
		  }
	});	
}

function existingUnitStockInfo(targetURL, targetTableId){
	var addstatus = $('#addNewSalesStatus').val();
	if(addstatus == 'true'){
		alert('One stock information is already pending to submit.');
		return;
	}
	var wholesalerId = document.getElementById('wholesalerStockDto.wholesalerId').value;
	var stockDate = document.getElementById('stockDate').value;
	if(wholesalerId == null || wholesalerId=="" || stockDate == null || stockDate ==""){
		alert('Either Wholesaler Id or Stock Date or both are missing.');
		return;
	}
		
	$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'POST',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){
				 document.getElementById('tempToknContaner').innerHTML=data;
			 	$('#'+targetTableId).html(data);
			 	$('#addNewSalesStatus').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
	});	
}

function saveRetailerUnitStockInfo(targetURL, targetTableId){	
		
	var retailerId = document.getElementById('retailerStockDto.retailerId').value;
	var stockDate = document.getElementById('stockDate').value;
	
	if(retailerId == null || retailerId=="" || stockDate == null || stockDate ==""){
		alert('Either retailer Id or Stock Date or both are missing.');
		return;
	}
	if(!validateUnitStockSales()){
		return;
	}
	var formData ='';
	var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
	var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
	var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
	formData = 'companyId='+compId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
	
	$.ajax({
		  url: buildLinkToken(targetURL,false),		  
		  type: 'POST',
		  data:formData,
		  dataType: 'html',
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){		  
			document.getElementById('tempToknContaner').innerHTML=data;
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('false');
		 	var saveButton = document.getElementById('saveStockTxnBtn');		 	
		 	if(saveButton.getAttribute('disabled')){
		 		saveButton.removeAttribute('disabled');
		 	}
			
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
		  }
		});
}

function saveUnitStockInfo(targetURL, targetTableId){
	var wholesalerId = document.getElementById('wholesalerStockDto.wholesalerId').value;
	var stockDate = document.getElementById('stockDate').value;
	if(wholesalerId == null || wholesalerId=="" || stockDate == null || stockDate ==""){
		alert('Either Wholesaler Id or Stock Date or both are missing.');
		return;
	}
	if(!validateUnitStockSales()){
		return;
	}
	var formData = "";
	
		var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
		var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
		var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
		formData = 'companyId='+compId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
	
//	alert('Form Data: ' + formData);
	$.ajax({
		  url: buildLinkToken(targetURL,false),		  
		  type: 'POST',
		  data:formData,
		  dataType: 'html',
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){		  
			document.getElementById('tempToknContaner').innerHTML=data;			
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('false');
		 	var saveButton = document.getElementById('saveStockTxnBtn');		 	
		 	if(saveButton.getAttribute('disabled')){
		 		saveButton.removeAttribute('disabled');
		 	}
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
		  }
		});
}

function validateUnitStockSales(){
	var companyId = document.getElementById('companyId').selectedIndex;
	if(companyId == null || companyId ==0){
		alert("Please select a company.");
		return false;
	}
	/*var plantId = document.getElementById('plantId').selectedIndex;
		if(plantId == null || plantId ==0){
			alert("Please select a plant.");
			return false;
		}	
	*/
	// changes on 6 Oct 2012
	if(document.getElementById('plantId'))
	{
		var plantId = document.getElementById('plantId').selectedIndex;
		if(plantId == null || plantId ==0){
			alert("Please select a plant.");
			return false;
		}	
	}
	var productId = document.getElementById('productId').selectedIndex;
	if(productId == null || productId ==0){
		alert("Please select a product.");
		return false;
	}	
	var unitId = document.getElementById('unitId').selectedIndex;
	if(unitId == null || unitId ==0){
		alert("Please select a unit.");
		return false;
	}
	var quantity = document.getElementById('quantity').value;
	if(quantity == null || quantity.length ==0){		
		alert('Please enter a valid quantity');
		return false;
	}else if(isNaN(quantity)){
		alert('Please enter a valid number.');
		return false;
	}
	return true;
}

function validateStateSupplyPlanInfo(){
	var companyId = document.getElementById('companyId').selectedIndex;
	if(companyId == null || companyId ==0){
		alert("Please select a company.");
		return false;
	}
	
	var productGroupId = document.getElementById('productGroupId').selectedIndex;
	if(productGroupId == null || productGroupId ==0){
		alert("Please select a Product Group.");
		return false;
	}
	
	var productId = document.getElementById('productId').selectedIndex;
	if(productId == null || productId ==0){
		alert("Please select a product.");
		return false;
	}	
	var unitId = document.getElementById('unitId').selectedIndex;
	if(unitId == null || unitId ==0){
		alert("Please select a unit.");
		return false;
	}
	var quantity = document.getElementById('quantity').value;
	if(quantity == null || quantity.length ==0){		
		alert('Please enter a valid quantity');
		return false;
	}else if(isNaN(quantity)){
		alert('Please enter a valid number.');
		return false;
	}
	return true;
}
function getTockenQueryString(formElement){
	var tokenQueryString = "";
	$('#'+formElement).find('input').each(function(index) {
		if($(this).attr('name') == 'struts.token.name'){
			if(tokenQueryString == ''){
				tokenQueryString += 'struts.token.name=' +$(this).val()+'&';							
			}else{
				tokenQueryString +='&struts.token.name=' +$(this).val();
			}			
		}else if($(this).attr('name') == 'struts.token'){
			if(tokenQueryString == ''){
				tokenQueryString += 'struts.token='+$(this).val()+'&';							
			}else{
				tokenQueryString += 'struts.token='+$(this).val();
			}			
		}							
	  });
	return tokenQueryString;
		// alert(tokenQueryString);
}
/*
 * This method should be invoked on clicking on any link on the page.
 */
function requestInterceptor(targetAction, isQueryStringPassed){
	location.href=buildLinkToken(targetAction, isQueryStringPassed);
}

function buildLinkToken(targetAction, isQueryStringPassed){
	var newTargetAction = '';
	//var tokenString = document.getElementById('tokenHolder').
	var tokenQueryString='';	
	$('#tokenContainer').find('input').each(function(index) {
		if($(this).attr('name') == 'struts.token.name'){
			if(tokenQueryString == ''){
				tokenQueryString += 'struts.token.name=' +$(this).val()+'&';							
			}else{
				tokenQueryString +='&struts.token.name=' +$(this).val();
			}			
		}else if($(this).attr('name') == 'struts.token'){
			if(tokenQueryString == ''){
				tokenQueryString += 'struts.token='+$(this).val()+'&';							
			}else{
				tokenQueryString += 'struts.token='+$(this).val();
			}			
		}							
	  });
	if(isQueryStringPassed){
		newTargetAction = targetAction + '&'+tokenQueryString;
	}else{
		newTargetAction = targetAction + '?'+tokenQueryString;
	}
	return newTargetAction;
}

function existingCompanyUnitStockInfo(targetURL, targetTableId){
	var addstatus = $('#addNewSalesStatus').val();	
	if(addstatus == 'true'){
		alert('One stock information is already pending to submit.');
		return;
	}	
	var companyId = document.getElementById('companyStockDto.companyId').value;
	var stockDate = document.getElementById('stockDate').value;
	var warehouseId = document.getElementById('warehouseList').value;
	var stateId = document.getElementById('stateList').value;
	var districtId = document.getElementById('districtList').value;
	
	if(companyId == null || companyId==""){
		alert('Please enter valid company id');
		return;
	}
	
	if(stockDate == null || stockDate ==""){
		alert('Please enter Stock Date.');
		return;
	}
	if(stateId == null || stateId =="" || stateId==0){
		alert('Please select state');
		return;
	}
	if(districtId == null || districtId =="" || districtId==0){
		alert('Please select district');
		return;
	}
	
	
	if(warehouseId ==null || warehouseId=="" || warehouseId==0){
		alert('Please select warehouse');
		return;
	}
	
	

	$.ajax({
		  url: buildLinkToken(targetURL,false),		  
		  type: 'GET',
		  dataType: 'html',
		  success: function(data, textStatus, jqXHR){	
			document.getElementById('tempToknContaner').innerHTML=data;
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('true');
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
			  //alert('textStatus : ' +textStatus);
		  }
	});	
}

function saveCompanyUnitStockInfo(targetURL, targetTableId){
	var companyId = document.getElementById('companyStockDto.companyId').value;
	var stockDate = document.getElementById('stockDate').value;
	var warehouseId = document.getElementById('warehouseList').value;
	if(companyId == null || companyId=="" || stockDate == null || stockDate =="" || warehouseId == null || warehouseId ==""){
		alert('Either Wholesaler Id or Stock Date or Warehouse Id is missing.');
		return;
	}
	if(!validateUnitStockSales()){
		return;
	}
	var formData = "";
	
		var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
		var plantId = document.getElementById('plantId').options[document.getElementById('plantId').selectedIndex].value;
		var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
		var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
		formData = 'companyId='+compId+'&plantId='+plantId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
		
	
//	alert('Form Data: ' + formData);
	$.ajax({
		  url: buildLinkToken(targetURL,false),
		  type: 'POST',
		  data:formData,
		  dataType: 'html',
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){		  
			document.getElementById('tempToknContaner').innerHTML=data;			
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('false');
		 	var saveButton = document.getElementById('saveStockTxnBtn');		 	
		 	if(saveButton.getAttribute('disabled')){
		 		saveButton.removeAttribute('disabled');
		 	}
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
		  }
		});
}

function validateStorageCapacity(storageCapacityUnit,storageCapacityValue){
	if(storageCapacityUnit=="1"){
		 var pattern =/^((?!^0*$)(?!^0*\.0*$)^\d{0,5}(\.\d{1,3})?$)$/;
		 var isValid = pattern.test(storageCapacityValue);
		 if(!isValid){
			 alert("Invalid Storage Capacity Quantity");
			 return false;
			 }
		 return true;
	}else{
		var pattern =/^\d{1,5}$/;
		 var isValid = pattern.test(storageCapacityValue);
		 if(!isValid){
			 alert("Invalid Storage Capacity Quantity");
			 return false;
			 }
		 return true;
	}
	
}


function saveUnitStockTransferInfo(targetURL, targetTableId){
	var wholesalerId = document.getElementById('wholesalerStockTransferDto.senderDealerId').value;
	var stockDate = document.getElementById('stockDate').value;
	if(wholesalerId == null || wholesalerId=="" || stockDate == null || stockDate ==""){
		alert('Either Wholesaler Id or Stock Date or both are missing.');
		return;
	}
	if(!validateUnitStockSales()){
		return;
	}
	var formData = "";
	
		var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
		var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
		var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
		formData = 'companyId='+compId+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
	
//	alert('Form Data: ' + formData);
	$.ajax({
		  url: buildLinkToken(targetURL,false),		  
		  type: 'POST',
		  data:formData,
		  dataType: 'html',
		  context: document.body,	 
		  success: function(data, textStatus, jqXHR){		  
			document.getElementById('tempToknContaner').innerHTML=data;			
			$('#'+targetTableId).html(data);
		 	$('#addNewSalesStatus').val('false');
		 	var saveButton = document.getElementById('saveStockTxnBtn');		 	
		 	if(saveButton.getAttribute('disabled')){
		 		saveButton.removeAttribute('disabled');
		 	}
		 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
		  },
		  error: function(jqXHR, textStatus, errorThrown){
			  alert("error Occured: " + errorThrown);
		  }
		});
}

function existingSupplyPlanDistrictInfo(targetURL, targetTableId, tokenForm){
	
	var addstatus = $('#addNewDistrictSupply').val();
	if(addstatus == 'true'){
		alert('One sale information is already pending to submit.');
		return;
	}
	var isValid = validateSupplyPlanDistrictForm();
	if(isValid){
		$.ajax({
			  url: buildLinkToken(targetURL,false),		  
			  type: 'GET',
			  dataType: 'html',		  
			  success: function(data, textStatus, jqXHR){	
				document.getElementById('tempToknContaner').innerHTML=data;
				$('#'+targetTableId).html(data);
			 	$('#addNewDistrictSupply').val('true');
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
				  //alert('textStatus : ' +textStatus);
			  }
		});
	}
}

function validateSupplyPlanDistrictForm(){
	
	var state = document.getElementById('stateList').selectedIndex;
	if(state == null || state <= 0){
		alert('State is missing. Select a state.');
		return false;
	}
	
	var district = document.getElementById('districtList').selectedIndex;
	if(district == null || district <= 0){
		alert('District is missing. Select a district.');
		return false;
	}
	
	var year = document.getElementById('supplyYear').value;
	if(year == null || year ==''){
		alert('Please Select Year.');
		return false;
	}
	var month = document.getElementById('supplyMonth').value;
	if(month == null || month ==''){
		alert('Please Select Month.');
		return false;
	}
	
	return true;
}

function saveExistingDistrictSupplyPlanInfo(targetURL, formId, targetTableId){
	
	if(validateStateSupplyPlanInfo()){
		var formData = "";
		if(navigator.appName == 'Netscape' || navigator.appCodeName == 'Mozilla'){
			var compId = document.getElementById('companyId').options[document.getElementById('companyId').selectedIndex].value;
			var plantId = document.getElementById('plantId').options[document.getElementById('plantId').selectedIndex].value;
			var productGroupId = document.getElementById('productGroupId').options[document.getElementById('productGroupId').selectedIndex].value;
			var prodId = document.getElementById('productId').options[document.getElementById('productId').selectedIndex].value;
			var unitId = document.getElementById('unitId').options[document.getElementById('unitId').selectedIndex].value;
			var isImported = document.getElementById('isImported').options[document.getElementById('isImported').selectedIndex].value;
			var isFirstFortnight = document.getElementById('isFirstFortnight').options[document.getElementById('isFirstFortnight').selectedIndex].value;
			formData = 'companyId='+compId+'&plantId='+plantId+'&productGroupId='+productGroupId+'&isFirstFortnight='+isFirstFortnight+'&isImported='+isImported+'&productId='+prodId+'&unitId='+unitId+'&quantity='+document.getElementById('quantity').value;
		}else{
			formData = $('#'+formId).serialize();
		}
		
		$.ajax({
			  url: targetURL,		  
			  type: 'POST',
			  timeout:15000,
			  data:formData+buildLinkToken('',true),
			  dataType: 'html',
			  context: document.body,	 
			  success: function(data, textStatus, jqXHR){		  
				document.getElementById('tempTokenHolder').innerHTML=data;
				$('#'+targetTableId).html(data); 
			 	$('#addNewDistrictSupply').val('false');
			 	var saveButton = document.getElementById('saveDistrictSupplyBtn');			 
			 	if(saveButton.getAttribute('disabled')){
			 		saveButton.removeAttribute('disabled');
			 	}
			 	document.getElementById('tokenContainer').innerHTML=document.getElementById('tokenHolder').innerHTML;			 	
			  },
			  error: function(jqXHR, textStatus, errorThrown){
				  alert("error Occured: " + errorThrown);
			  }
			});
	}
}


