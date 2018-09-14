var oPanel;
if(oPanel != undefined )
{
if(document.getElementById('informationDialog')!=null && document.getElementById('informationDialog').value=='true')
{
oPanel.render(document.body);
oPanel.show();
}
}

var dialog1;
if(dialog1 != undefined)
{
dialog1.render(document.body);
if(document.getElementById('confirm').value == "true")
confirmPayment();
}
