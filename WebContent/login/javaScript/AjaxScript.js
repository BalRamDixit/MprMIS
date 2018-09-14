var http_request;

function createAjaxRequest() {

	if (window.ActiveXObject) { // IE 6.0+ , 7.0
		http_request = new ActiveXObject("Microsoft.XMLHTTP");
		//alert("Through Active-x object");
	} else if (window.XMLHttpRequest != undefined) { // Mozilla, Safari, ...
		http_request = new XMLHttpRequest();
		//alert("Through built-in object");
		//http_request.overrideMimeType('text/xml');
	}
	return http_request;
}

function callAjax() {
	prompt(window.XMLHttpRequest);
	if (window.XMLHttpRequest != undefined) { // Mozilla, Safari, ...
		http_request = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE
		http_request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	alert("1");
	//http_request = new XMLHttpRequest();
	alert("2");
	http_request.overrideMimeType('text/xml'); //setting mime-time
	alert("calling Ajax");
	return;
	http_request.onreadystatechange = nameOfTheFunction; //on ready state, this function will be called
	/*
	// or using anonymous function
	http_request.onreadystatechange = function(){
		// do the thing
	};
	 */

	//Actually making the request
	http_request.open('GET', 'http://www.example.org/some.file', true);
	http_request.send(null);
	/*
	//	OR
	data = "name=value&anothername=othervalue&so=on";
	http_request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	http_request.send(data);
	 */
}

function nameOfTheFunction() {
	/*	   
	 * 0 (uninitialized)
	 * 1 (loading)
	 * 2 (loaded)
	 * 3 (interactive)
	 * 4 (complete) 
	 */
	if (http_request.readyState == 4) {
		// everything is good, the response is received
		alert("hurrrrree");
	} else {
		// still not ready
	}
}

function otherFunction() {
	/*	   
	 * 0 (uninitialized)
	 * 1 (loading)
	 * 2 (loaded)
	 * 3 (interactive)
	 * 4 (complete) 
	 */
	if (http_request.readyState == 4) {
		// everything is good, the response is received
		alert("hurrrrree again.");
	} else {
		// still not ready
	}
}

function clearMultiSelect(DropDownName) {
	dropdown = document.getElementsByName(DropDownName)[0];
	while (dropdown.firstChild) {
		//The list is LIVE so it will re-index each call
		dropdown.removeChild(dropdown.firstChild);
	}

}

/*This script creates a function named evalScript() which evaluates anything 
 that is given as string argument to it. */

(evalScript = function(e) {
	var h = evalScript.node, s = document.createElement("script");
	s.type = "text/javascript";
	s.text = e;
	h.appendChild(s);
	h.removeChild(s);
}).node = document.getElementsByTagName("head")[0]
		|| document.getElementsByTagName("*")[0]
		|| document.getElementsByTagName("body")[0];

/*End of function evalScript() */

/*Code to check whether browser is IE or not.*/
isIE = true;
if (document.all) {
	isIE = true;
} else {
	isIE = false;
}
/*END OF - Code to check whether browser is IE or not.*/

function replaceSingleQuote(obj) {
	//alert("BEFORE"+obj);
	obj = obj.replace(/'/g, "\'");
	//obj = obj.replace(/"/g, "\"");
	//alert("AFTER"+obj);
	return obj;
	//obj.value = obj.value.replace(/'/g, "''");

}

function addOption(selectbox, text, value) {

	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	selectbox.options.add(optn);
}
