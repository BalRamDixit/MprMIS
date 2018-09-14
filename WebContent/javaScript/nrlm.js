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

function showPopUp(obj){
document.getElementById(obj).style.display = "";
document.getElementById("overlay").style.display = "";
}


function hidePopUp(obj){
document.getElementById(obj).style.display = "none";
document.getElementById("overlay").style.display = "none";
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



function v_CNAME(ob) {
	  var cname = gbi("cname");
	  var error_cname = gbi("error_cname");
	  var status_cname = gbi("status_cname");
	  var params = new Array();
	  var err = false;
	  cname.value = cname.value.trim();
	  if (cname.value.length == 0 ) {
	    err = true;
	    hideErrorCSS('cname','error_cname','status_cname','lbl_cname');
	  } else if ( cname.value.length > 0  &&  ! isValidName(cname.value) ) {
	    params = { "ErrDivObj": error_cname, "ErrorMsg": "Special Characters Other Than (Space Dot SingleQuote) are Not Allowed.", "EleToFocus": cname, "StatusObj": status_cname, "ob": ob, "HName": "Name", "Id": "cname", "label":"lbl_cname" };
	    showErrMsg(params);
	    err = true;
	  }
	  if ( ob && !err ) {
	    hideErrorCSS('cname','error_cname','status_cname','lbl_cname');
	    status_cname.style.display = '';
	    status_cname.src = Images_Path_Resman+"/correct.gif";
	    opacity("status_cname", 100, 0, fd_tout);
	  }
	}	

/**
 *  validate form
 */

function isValidName(Name) {
	  var pattern = /[^a-zA-Z'\s.]+/;
	  if ( pattern.test ( Name ) )
	    return false;
	  else
	    return true;
	}


			


