/*
 * DATE: 7 December 2012
 * AUTHOR: HIMANSHU MEHRA
 * DESCRPTION:
 */
function showPopSmall(url) {
	window
			.open(
					url,
					"",
					"menubar=0,resizable=1,status=1,width=200,height=200,left=0,top=0 ,scrollbars=yes");
}

function showPopBig(url) {
	window
			.open(
					url,
					"",
					"menubar=0,resizable=1,status=1,width=800,height=600,left=0,top=0 ,scrollbars=yes");
}

function selectAll(obj) {
	for ( var i = 0; i < obj.length; i++) {
		obj[i].selected = true;
	}
}

function addSelected(selectbox1, selectbox2) {
	var i;
	for (i = selectbox1.options.length - 1; i >= 0; i--) {
		if (selectbox1.options[i].selected) {
			var optn = document.createElement("OPTION");
			optn.text = selectbox1.options[i].text;
			optn.value = selectbox1.options[i].value;
			selectbox2.options.add(optn);
			selectbox1.remove(i);
		}
	}
}

function show(sel1, sel2) {
	var j = sel2.length;
	for ( var i = 0; i < sel1.length; i++) {
		if (sel1.options[i].selected && sel1.options[i].value != '') {
			sel2.options[j++] = new Option(sel1.options[i].text,
					sel1.options[i].value, false, true);
			sel1.options[i--] = null;
		}
	}
}

function closePage(tokenParameter, tokenValue) {
	var status = window.confirm("Are You Sure You want to close the form ?");
	if (status == true) {
		document.forms[0].action = "login.do?methodName=closePage&"
				+ tokenParameter + "=" + tokenValue;
		document.forms[0].submit();
	}
}

function isAlpha(txtObj, msg) {
	var val = txtObj.value;
	var caps = /[A-Z]/;
	var small = /[a-z]/;
	var space = /\s/;
	var digit = /[0-9]/;
	var special = /[+-=|!@#$%&*^~()_\/<>:;`,.?{}"]/;
	if (val == "") {
		return true;
	} else if (!(caps.test(txtObj.value) || small.test(txtObj.value))
			|| special.test(txtObj.value)) {
		alert("Please use only alphabetic characters for " + msg + ".");
		txtObj.value = "";
		txtObj.focus();
		txtObj.style.backgroundColor = "yellow";
		txtObj.select();
		return false;
	} else if (digit.test(val)
			&& (caps.test(txtObj.value) || small.test(txtObj.value))) {
		alert("Please use only alphabetic characters for " + msg + ".");
		txtObj.value = "";
		txtObj.focus();
		txtObj.style.backgroundColor = "yellow";
		txtObj.select();
		return false;
	}
	return true;
}

function submitForm(URL) {
	document.forms[0].action = URL;
	document.forms[0].submit();
}

function trim(str) {
	s = str.replace(/^(\s)*/, '');
	s = s.replace(/(\s)*$/, '');
	return s;
}

function isNumberKey(evt) {
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function isNumber(evt) {
	alert("check for a number");
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode == 45) {
		return true;
	}
	if (charCode == 47) {
		return true;
	} else if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		alert("Please Enter only numbers.");
		return false;
	}
	return true;
}

function echeck(str) {
	var at = "@";
	var dot = ".";
	var lat = str.indexOf(at);
	var lstr = str.length;
	var ldot = str.indexOf(dot);
	if (str.indexOf(at) == -1) {
		alert("Invalid E-mail ID");
		return false;
	}
	if (str.indexOf(at) == -1 || str.indexOf(at) == 0
			|| str.indexOf(at) == lstr) {
		alert("Invalid E-mail ID");
		return false;
	}
	if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
			|| str.indexOf(dot) == lstr) {
		alert("Invalid E-mail ID");
		return false;
	}
	if (str.indexOf(at, (lat + 1)) != -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.substring(lat - 1, lat) == dot
			|| str.substring(lat + 1, lat + 2) == dot) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(dot, (lat + 2)) == -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	if (str.indexOf(" ") != -1) {
		alert("Invalid E-mail ID");
		return false;
	}

	return true;
}

// Alpha-numeric and spaces Validation Added by CVAS2273 (24th-NOV-2009)

function checkAlNum(o) {
	if (/[^a-z 0-9]/i.test(o.value)) {
		alert("Alpha-numeric characters and spaces allowed");
		o.style.background = 'yellow';
		o.value = '';
		return false;
	}

	o.style.background = 'white';

}

/**
 * Added by CVAS2273 for phone number validation (international and national)
 */

// Declaring required variables
var digits = "0123456789";
// non-digit characters which are allowed in phone numbers
var phoneNumberDelimiters = "()- ";
// characters which are allowed in international phone numbers
// (a leading + is OK)
var validWorldPhoneChars = phoneNumberDelimiters + "+";
// Minimum no of digits in an international phone no.
var minDigitsInIPhoneNumber = 10;

function isInteger(s) {
	var i;
	for (i = 0; i < s.length; i++) {
		// Check that current character is number.
		var c = s.charAt(i);
		if (((c < "0") || (c > "9")))
			return false;
	}
	// All characters are numbers.
	return true;
}
function trim(s) {
	var i;
	var returnString = "";
	// Search through string's characters one by one.
	// If character is not a whitespace, append to returnString.
	for (i = 0; i < s.length; i++) {
		// Check that current character isn't whitespace.
		var c = s.charAt(i);
		if (c != " ")
			returnString += c;
	}
	return returnString;
}
function stripCharsInBag(s, bag) {
	var i;
	var returnString = "";
	// Search through string's characters one by one.
	// If character is not in bag, append to returnString.
	for (i = 0; i < s.length; i++) {
		// Check that current character isn't whitespace.
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1)
			returnString += c;
	}
	return returnString;
}

function checkInternationalPhone(strPhone) {
	var bracket = 3;
	strPhone = trim(strPhone);
	if (strPhone.indexOf("+") > 1)
		return false;
	if (strPhone.indexOf("-") != -1)
		bracket = bracket + 1;
	if (strPhone.indexOf("(") != -1 && strPhone.indexOf("(") > bracket)
		return false;
	var brchr = strPhone.indexOf("(");
	if (strPhone.indexOf("(") != -1 && strPhone.charAt(brchr + 2) != ")")
		return false;
	if (strPhone.indexOf("(") == -1 && strPhone.indexOf(")") != -1)
		return false;
	s = stripCharsInBag(strPhone, validWorldPhoneChars);
	return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}

/*
 Added By CVAS2273 
 Change textbox color on keypress event
 */
function changeColor(object) {
	object.style.backgroundColor = "white";
}

/*
 function ValidateForm(){
 var Phone=document.facilitatorForm.contactNumber   // facilitatorForm.contactNumber (form name & field name) this will be change only

 if ((Phone.value==null)||(Phone.value=="")){
 alert("Please Enter your Phone Number")
 Phone.focus()
 return false
 }
 if (checkInternationalPhone(Phone.value)==false){
 alert("Please Enter a Valid Phone Number")
 Phone.value=""
 Phone.focus()
 return false
 }
 return true
 }
 */

//------------------------------ End of phone number validation  ------------------------------
/*
 * __________________ NRLM Scripts 25-10-2012 _____________________ 
 */

//Function used to collect all or select element from the list (NOTE: A - ALL & S - SELECT)
function multipleSelection(list1, list2, type) {
	var i;
	if (type == "A") {
		for (i = list1.options.length - 1; i >= 0; i--) {
			var optn = document.createElement("OPTION");
			optn.text = list1.options[i].text;
			optn.value = list1.options[i].value;
			list2.options.add(optn);
			list1.remove(i);
		}
	}
	if (type == "S") {
		for (i = list1.options.length - 1; i >= 0; i--) {
			if (list1.options[i].selected) {
				var optn = document.createElement("OPTION");
				optn.text = list1.options[i].text;
				optn.value = list1.options[i].value;
				list2.options.add(optn);
				list1.remove(i);
			}
		}
	}
}

//Function used to select all element from multiple list.
function selectAllList(list) {
	for ( var i = 0; i < list.length; i++) {
		list.options[i] = new Option(list.options[i].text,
				list.options[i].value, false, true);
	}
}
