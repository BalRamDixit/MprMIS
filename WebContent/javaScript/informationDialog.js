//var myDialog = new YAHOO.widget.Dialog("myDialog");

var handleOK = function() {
	document.body.className = "";
	this.cancel();
}
var myButtons = [{ text:"OK", handler:handleOK, isDefault:true }];
myDialog.cfg.queueProperty("buttons", myButtons);


//myDialog.render();
//We get the texts to be displayed here
alert("GETTING VALUE");
var informationDialogText = document.getElementById('informationDialogText').value;
alert(document.getElementById('informationDialogText').value);
var buttonOK = "OK";
var informationDialogHeader = document.getElementById('informationDialogHeader').value;


//var informationDialogText = "text here";
//var informationDialogHeader = "info here";

var oPanel = new YAHOO.widget.SimpleDialog("panel", {
	
	modal: true,
	icon: YAHOO.widget.SimpleDialog.ICON_INFO,
	visible: false,
	fixedcenter: true,
	constraintoviewport: true,
	width: "300px",
	role: "alertdialog",
	buttons: [ { text:buttonOK, handler:handleOK, isDefault:true } ],
	text: informationDialogText

});	

oPanel.setHeader(informationDialogHeader);

document.body.className = "yui-skin-sam";