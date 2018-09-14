 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@page import="com.infotech.sgsy.util.SGSYConstants"%>
<%@page import="com.infotech.sgsy.util.Constants"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
input[type="text"]
{
  	height: 25px;
   	width: 110px;
}
.bordertable {
    empty-cells: show;
    border: 0px solid #000;
    border-collapse: collapse;
}
.bordertable td,
.bordertable th {
    min-width: 1em;
    min-height: 1em;
    border: 1px solid #000;
    
}

</style> 
<script type="text/javascript">

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID")%>' ; 

$(function() {	
		$('.amount').each(function(){
			//alert($(this).attr('onblur'));
			$("#maskedAmount"+$(this).attr('id')).attr({"onblur":$(this).attr('onblur')});
		});
	   	$(".nndate").each(function() {
	    	$(this).find("input:text").each(function() {
		    	var dbDate=$(this).val();
		    	if(dbDate!=null && dbDate!=""){
		    		var date2 = new Date(dbDate);
		    		$(this).datepicker({
		        	    dateFormat: 'dd-mm-yy'
		        	}).datepicker('setDate', date2);
		    	}
		    });
	   	}); 
	   	$("#centralReleasedDateProgramCostId, #centralReleasedDatSuportCostId, #centralReleasedDatCtsaId, #centralReleasedDatAdminCostId" ).datepicker({ dateFormat: 'dd-mm-yy' });
		$("#centralReleasedDatCapacityCostId, #stateReleasedDateProgramCostId, #stateReleasedDateSuportCostId, #stateReleasedDateCtsaId" ).datepicker({ dateFormat: 'dd-mm-yy' });
		$("#stateReleasedDateAdminCostId, #stateReleasedDateCapacityCostId" ).datepicker({ dateFormat: 'dd-mm-yy' });	    
    	$("#centralReleasedDateProgramCostId, #centralReleasedDatSuportCostId, #centralReleasedDatCtsaId, #centralReleasedDatAdminCostId" ).datepicker('option', { maxDate:new Date()}); 
    	$("#centralReleasedDatCapacityCostId, #stateReleasedDateProgramCostId, #stateReleasedDateSuportCostId, #stateReleasedDateCtsaId" ).datepicker('option', { maxDate:new Date()});
    	$("#stateReleasedDateAdminCostId, #stateReleasedDateCapacityCostId" ).datepicker('option', { maxDate:new Date()});  
    	
    	
    	$(".mm").each(function() {
    		//alert(amount);
    		var amount=$(this).html();
    		var maskamount=maskingstatic(amount.trim());
    		//alert(maskamount);
    		$(this).html(maskamount);
    	});
    	
    	
});

	function maskingstatic(x){
        var kk=x+"";
    // alert(kk.length);
       if(kk.length>3){
       var aa=kk.substring(kk.length -3);
       var dd=kk.substring(0,kk.length -3);
     
       var bb=addCommas(dd);
     
       x=bb+","+aa;
	   
}
     return x;  
}
function addCommas(nStr)
{
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{2})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}


function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function calculateSum(){
	  var sc= document.getElementById("centralProgramCostId").value;
	  var st= document.getElementById("centralSuportCostId").value;
	  var gen= document.getElementById("centralCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	 // alert(sum);
	  var aa=maskingstatic(sum);
	 // alert(aa);
	  document.getElementById("sumCatId").innerHTML=aa;
	  
	  var sc1= document.getElementById("centralAdminCostId").value;
	  var st1= document.getElementById("centralCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc="0";
	  }
	  if(st1==null || st1==''){
		  st="0";
	  }
	 	  
	  var sum1=parseInt(sc1) + parseInt(st1) ;
	  var masksum1=maskingstatic(sum1);
	  document.getElementById("sumCatId1").innerHTML=masksum1;
	  
	  
	  var sum12=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	  var masksum12=maskingstatic(sum12);
	  document.getElementById("sumCatId11").innerHTML=masksum12;
	  }


function calculateSumA(){
	  var sc= document.getElementById("stateProgramCostId").value;
	  var st= document.getElementById("stateSuportCostId").value;
	  var gen= document.getElementById("stateCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	  var masksum=maskingstatic(sum);
	  
	  document.getElementById("sumCatIdA").innerHTML=masksum;
	  
	  var sc1= document.getElementById("stateAdminCostId").value;
	  var st1= document.getElementById("stateCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum1=parseInt(sc1) + parseInt(st1) ;
	  var masksum1=maskingstatic(sum1);
	  document.getElementById("sumCatId1A").innerHTML=masksum1;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	 var masksum22= maskingstatic(sum22);
	  document.getElementById("sumCatId2A").innerHTML=masksum22;
}

function calculateSumB(){
	  var sc= document.getElementById("centralReleasedProgramCostId").value;
	  var st= document.getElementById("centralReleasedSuportCostId").value;
	  var gen= document.getElementById("centralReleasedCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	  var masksum=maskingstatic(sum);
	  
	  document.getElementById("sumCatIdB").innerHTML=masksum;
	  
	  var sc1= document.getElementById("centralReleasedAdminCostId").value;
	  var st1= document.getElementById("centralReleasedCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum1=parseInt(sc1) + parseInt(st1) ;
	 var  masksum1=maskingstatic(sum1);
	  document.getElementById("sumCatId1B").innerHTML=masksum1;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	  var masksum22=maskingstatic(sum22);
	  document.getElementById("sumCatId2B").innerHTML=masksum22;
}


function calculateSumC(){
	  var sc= document.getElementById("centralOnlyProgramCostId").value;
	  var st= document.getElementById("centralOnlySuportCostId").value;
	  var gen= document.getElementById("centralOnlyCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	 var masksum= maskingstatic(sum);
	  document.getElementById("sumCatIdC").innerHTML=masksum;
	  
	  var sc1= document.getElementById("centralOnlyAdminCostId").value;
	  var st1= document.getElementById("centralOnlyCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum1=parseInt(sc1) + parseInt(st1) ;
	  var masksum1=maskingstatic(sum1);
	  document.getElementById("sumCatId1C").innerHTML=masksum1;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	  var masksum22=maskingstatic(sum22);
	  document.getElementById("sumCatId2C").innerHTML=masksum22;
}


function calculateSumD(){
	  var sc= document.getElementById("centralReleasedDateProgramCostId").value;
	  var st= document.getElementById("centralReleasedDatSuportCostId").value;
	  var gen= document.getElementById("centralReleasedDatCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	 var masksum= maskingstatic();
	  document.getElementById("sumCatIdD").innerHTML=masksum;
	  
	  var sc1= document.getElementById("centralReleasedDatAdminCostId").value;
	  var st1= document.getElementById("centralReleasedDatCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum=parseInt(sc1) + parseInt(st1) ;
	  var masksum1=maskingstatic(sum);
	  document.getElementById("sumCatId1D").innerHTML=masksum1;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	  var masksum22=maskingstatic(sum22);
	  document.getElementById("sumCatId2D").innerHTML=masksum22;
}

function calculateSumE(){
	  var sc= document.getElementById("stateOnlyProgramCostId").value;
	  var st= document.getElementById("stateOnlySuportCostId").value;
	  var gen= document.getElementById("stateOnlyCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	  var masksum=maskingstatic(sum);
	  document.getElementById("sumCatIdE").innerHTML=masksum;
	  
	  var sc1= document.getElementById("stateOnlyAdminCostId").value;
	  var st1= document.getElementById("stateOnlyCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum=parseInt(sc1) + parseInt(st1) ;
	  var masksum=maskingstatic(sum);
	  document.getElementById("sumCatId1E").innerHTML=masksum;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	 var masksum22= maskingstatic(sum22);
	  document.getElementById("sumCatId2E").innerHTML=masksum22;
}

function calculateSumF(){
	  var sc= document.getElementById("stateReleasedDateProgramCostId").value;
	  var st= document.getElementById("stateReleasedDateSuportCostId").value;
	  var gen= document.getElementById("stateReleasedDateCtsaId").value;
	  
	  if(sc==null || sc==''){
		  sc="0";
	  }
	  if(st==null || st==''){
		  st="0";
	  }
	  if(gen==null || gen==''){
		  gen="0";
	  }
	  
	  var sum=parseInt(sc) + parseInt(st) + parseInt(gen);
	 var masksum= maskingstatic(sum);
	  document.getElementById("sumCatIdF").innerHTML=masksum;
	  
	  var sc1= document.getElementById("stateReleasedDateAdminCostId").value;
	  var st1= document.getElementById("stateReleasedDateCapacityCostId").value;
	  	  
	  if(sc1==null || sc1==''){
		  sc1="0";
	  }
	  if(st1==null || st1==''){
		  st1="0";
	  }
	 	  
	  var sum=parseInt(sc1) + parseInt(st1) ;
	  var masksum=maskingstatic(sum);
	  document.getElementById("sumCatId1F").innerHTML=masksum;
	  
	  var sum22=parseInt(sc) + parseInt(st) + parseInt(gen)+parseInt(sc1) + parseInt(st1);
	  var masksum22=maskingstatic(sum22);
	  document.getElementById("sumCatId2F").innerHTML=masksum22; 
	  
	  
}

function checkWithOtherColumn(value,id){
	
	var centralOnlyProgramCostId=document.getElementById("centralOnlyProgramCostId").id;
	var centralReleasedProgramCostId=document.getElementById("centralReleasedProgramCostId").id;
	
	if(id==centralOnlyProgramCostId){
		var centralReleasedProgramCostValue=document.getElementById("centralReleasedProgramCostId").value;
		if(parseInt(value)<=parseInt(centralReleasedProgramCostValue)){
			document.getElementById('spancentralOnlyProgramCost').innerHTML="";
		} else{
			document.getElementById('spancentralOnlyProgramCost').innerHTML="Central Share Amount (Program Cost) must be <= Central share Released to State(Program Cost)";  
			}
	}
	
	if(id==centralReleasedProgramCostId){
		var centralProgramCostValue=document.getElementById("centralProgramCostId").value;
		if(parseInt(value)<=parseInt(centralProgramCostValue)){
			document.getElementById('spancentralReleasedProgramCost').innerHTML="";
		} else{
			document.getElementById('spancentralReleasedProgramCost').innerHTML="Central share Released to State (Program Cost) must be <= Central(Program Cost)";  
			}
	}
	
	var centralOnlySuportCostId=document.getElementById("centralOnlySuportCostId").id;
	var centralReleasedSuportCostId=document.getElementById("centralReleasedSuportCostId").id;
	
	if(id==centralOnlySuportCostId){
		var centralReleasedSuportCostValue=document.getElementById("centralReleasedSuportCostId").value;
		if(parseInt(value)<=parseInt(centralReleasedSuportCostValue)){
			document.getElementById('spancentralOnlySuportCost').innerHTML="";
		} else{
			document.getElementById('spancentralOnlySuportCost').innerHTML="Central share Released to State (Support Cost) must be <= Central(Support Cost)";
		}
	}
	
	if(id==centralReleasedSuportCostId){
		var centralSuportCostValue=document.getElementById("centralSuportCostId").value;
		if(parseInt(value)<=parseInt(centralSuportCostValue)){
			document.getElementById('spancentralReleasedSuportCost').innerHTML="";
		} else{
			document.getElementById('spancentralReleasedSuportCost').innerHTML="Central share Released to State (Support Cost) must be <= Central(Support Cost)";
		}
	}
	
	var centralOnlyCtsaId=document.getElementById("centralOnlyCtsaId").id;
	var centralReleasedCtsaId=document.getElementById("centralReleasedCtsaId").id;
	
	if(id==centralOnlyCtsaId){
		var centralReleasedCtsaValue=document.getElementById("centralReleasedCtsaId").value;
		if(parseInt(value)<=parseInt(centralReleasedCtsaValue)){
			document.getElementById('spancentralOnlyCtsa').innerHTML="";
		} else{
			document.getElementById('spancentralOnlyCtsa').innerHTML="Central Share Amount (CTSA) must be <= Central share Released to State(CTSA)";
		}
	}
	
	if(id==centralReleasedCtsaId){
		var centralCtsaValue=document.getElementById("centralCtsaId").value;
		if(parseInt(value)<=parseInt(centralCtsaValue)){
			document.getElementById('spancentralReleasedCtsa').innerHTML="";
		} else{
			document.getElementById('spancentralReleasedCtsa').innerHTML="Central share Released to State (CTSA) must be <= Central(CTSA)";
		}
	}
	
	var centralOnlyAdminCostId=document.getElementById("centralOnlyAdminCostId").id;
	var centralReleasedAdminCostId=document.getElementById("centralReleasedAdminCostId").id;
	
	if(id==centralOnlyAdminCostId){
		var centralReleasedAdminCostValue=document.getElementById("centralReleasedAdminCostId").value;
		if(parseInt(value)<=parseInt(centralReleasedAdminCostValue)){
			document.getElementById('spancentralOnlyAdminCost').innerHTML="";
		} else{
			document.getElementById('spancentralOnlyAdminCost').innerHTML="Central Share Amount (Admin Cost) must be <= Central share Released to State(Admin Cost)";
		}
	}
	
	if(id==centralReleasedAdminCostId){
		var centralAdminCostValue=document.getElementById("centralAdminCostId").value;
		if(parseInt(value)<=parseInt(centralAdminCostValue)){
			document.getElementById('spancentralReleasedAdminCost').innerHTML="";
		} else{
			document.getElementById('spancentralReleasedAdminCost').innerHTML="Central share Released to State (Admin Cost) must be <= Central(Admin Cost)";
		}
	}
	
	var centralOnlyCapacityCostId=document.getElementById("centralOnlyCapacityCostId").id;
	var centralReleasedCapacityCostId=document.getElementById("centralReleasedCapacityCostId").id;
	
	if(id==centralOnlyCapacityCostId){
		var centralReleasedCapacityCostValue=document.getElementById("centralReleasedCapacityCostId").value;
		if(parseInt(value)<=parseInt(centralReleasedCapacityCostValue)){
			document.getElementById('spancentralOnlyCapacityCost').innerHTML="";
		} else{
			document.getElementById('spancentralOnlyCapacityCost').innerHTML="Central Share Amount (Capacity Building) must be <= Central share Released to State(Capacity Building)";
		}
	}
	/* if(parseInt(value)<=parseInt(centralCapacityCostValue)){ */
	if(id==centralReleasedCapacityCostId){
		var centralCapacityCostValue=document.getElementById("centralCapacityCostId").value;
		if(parseInt(value)<=parseInt(centralCapacityCostValue)){
			document.getElementById('spancentralReleasedCapacityCost').innerHTML="";
		} else{
			document.getElementById('spancentralReleasedCapacityCost').innerHTML="Central share Released to State (Capacity Building) must be <= Central(Capacity Building)";
		}
	}
	<!---->
	
	var stateOnlyProgramCostId=document.getElementById("stateOnlyProgramCostId").id;
	
	if(id==stateOnlyProgramCostId){
		var stateProgramCostValue=document.getElementById("stateProgramCostId").value;
		if(parseInt(value)<=parseInt(stateProgramCostValue)){
			document.getElementById('spanstateOnlyProgramCost').innerHTML="";
		} else{
			document.getElementById('spanstateOnlyProgramCost').innerHTML="State Share Amount (Program Cost) must be <= State share Released to State(Program Cost)";
		}
	}
	
var stateOnlySuportCostId=document.getElementById("stateOnlySuportCostId").id;
	
	if(id==stateOnlySuportCostId){
		var stateSuportCostValue=document.getElementById("stateSuportCostId").value;
		if(parseInt(value)<=parseInt(stateSuportCostValue)){
			//document.getElementById('spanstateOnlySuportCost').innerHTML="";
			document.getElementById('spanstateSuportCost').innerHTML="";
		} else{
			document.getElementById('spanstateSuportCost').innerHTML="State Share Amount (Support Cost) must be <= State share Released to State(Support Cost)";
		}
	}
	
var stateOnlyCtsaId=document.getElementById("stateOnlyCtsaId").id;
	
	if(id==stateOnlyCtsaId){
		var stateCtsaValue=document.getElementById("stateCtsaId").value;
		if(parseInt(value)<=parseInt(stateCtsaValue)){
			document.getElementById('spanstateOnlyCtsa').innerHTML="";
		} else{
			document.getElementById('spanstateOnlyCtsa').innerHTML="State Share Amount (CTSA) must be <= State share Released to State(CTSA)";
		}
	}
	
var stateOnlyAdminCostId=document.getElementById("stateOnlyAdminCostId").id;
	
	if(id==stateOnlyAdminCostId){
		var stateAdminCostValue=document.getElementById("stateAdminCostId").value;
		if(parseInt(value)<=parseInt(stateAdminCostValue)){
			document.getElementById('spanstateOnlyAdminCost').innerHTML="";
		} else{
			document.getElementById('spanstateOnlyAdminCost').innerHTML="State Share Amount (Admin Cost) must be <= State share Released to State(Admin Cost)";
		}
	}
	
var stateOnlyCapacityCostId=document.getElementById("stateOnlyCapacityCostId").id;
	
	if(id==stateOnlyCapacityCostId){
		var stateCapacityCostValue=document.getElementById("stateCapacityCostId").value;
		if(parseInt(value)<=parseInt(stateCapacityCostValue)){
			document.getElementById('spanstateOnlyCapacityCost').innerHTML="";
		} else{
			document.getElementById('spanstateOnlyCapacityCost').innerHTML="State Share Amount (Capacity Building) must be <= State share Released to State(Capacity Building)";
		}
	}
	
			
}

function save(){
	var x=checkPermissionForFormForInsert();
	if(x=='true'){
 
	var centralOnlyProgramCostvalue=document.getElementById("centralOnlyProgramCostId").value;
	var centralProgramCostValue=document.getElementById("centralProgramCostId").value;
	var centralReleasedProgramCostValue=document.getElementById("centralReleasedProgramCostId").value;
	
	checkWithOtherColumn(document.getElementById("centralOnlyProgramCostId").value,document.getElementById("centralOnlyProgramCostId").id);
	checkWithOtherColumn(document.getElementById("centralProgramCostId").value,document.getElementById("centralProgramCostId").id);
	checkWithOtherColumn(document.getElementById("centralReleasedProgramCostId").value,document.getElementById("centralReleasedProgramCostId").id);
	
	var centralOnlySuportCostvalue=document.getElementById("centralOnlySuportCostId").value;
	var centralSuportCostValue=document.getElementById("centralSuportCostId").value;
	var centralReleasedSuportCostValue=document.getElementById("centralReleasedSuportCostId").value;
	
	checkWithOtherColumn(document.getElementById("centralOnlySuportCostId").value,document.getElementById("centralOnlySuportCostId").id);
	checkWithOtherColumn(document.getElementById("centralSuportCostId").value,document.getElementById("centralSuportCostId").id);
	checkWithOtherColumn(document.getElementById("centralReleasedSuportCostId").value,document.getElementById("centralReleasedSuportCostId").id);
	
	var centralOnlyCtsavalue=document.getElementById("centralOnlyCtsaId").value;
	var centralCtsaValue=document.getElementById("centralCtsaId").value;
	var centralReleasedCtsaCostValue=document.getElementById("centralReleasedCtsaId").value;
	
	checkWithOtherColumn(document.getElementById("centralOnlyCtsaId").value,document.getElementById("centralOnlyCtsaId").id);
	checkWithOtherColumn(document.getElementById("centralCtsaId").value,document.getElementById("centralCtsaId").id);
	checkWithOtherColumn(document.getElementById("centralReleasedCtsaId").value,document.getElementById("centralReleasedCtsaId").id);

	var centralOnlyAdminCostvalue=document.getElementById("centralOnlyAdminCostId").value;
	var centralAdminCostValue=document.getElementById("centralAdminCostId").value;
	var centralReleasedAdminCostValue=document.getElementById("centralReleasedAdminCostId").value;
	
	checkWithOtherColumn(document.getElementById("centralOnlyAdminCostId").value,document.getElementById("centralOnlyAdminCostId").id);
	checkWithOtherColumn(document.getElementById("centralAdminCostId").value,document.getElementById("centralAdminCostId").id);
	checkWithOtherColumn(document.getElementById("centralReleasedAdminCostId").value,document.getElementById("centralReleasedAdminCostId").id);
	
	var centralOnlyCapacityCostvalue=document.getElementById("centralOnlyCapacityCostId").value;
	var centralCapacityCostValue=document.getElementById("centralCapacityCostId").value;
	var centralReleasedCapacityCostValue=document.getElementById("centralReleasedCapacityCostId").value;
	
	checkWithOtherColumn(document.getElementById("centralOnlyCapacityCostId").value,document.getElementById("centralOnlyCapacityCostId").id);
	checkWithOtherColumn(document.getElementById("centralCapacityCostId").value,document.getElementById("centralCapacityCostId").id);
	checkWithOtherColumn(document.getElementById("centralReleasedCapacityCostId").value,document.getElementById("centralReleasedCapacityCostId").id);
	
	var stateOnlyProgramCostvalue=document.getElementById("stateOnlyProgramCostId").value;
	var stateProgramCostValue=document.getElementById("stateProgramCostId").value;
	
	checkWithOtherColumn(document.getElementById("stateOnlyProgramCostId").value,document.getElementById("stateOnlyProgramCostId").id);
	checkWithOtherColumn(document.getElementById("stateProgramCostId").value,document.getElementById("stateProgramCostId").id);
	
	var stateOnlySuportCostvalue=document.getElementById("stateOnlySuportCostId").value;
	var stateSuportCostValue=document.getElementById("stateSuportCostId").value;
	
	checkWithOtherColumn(document.getElementById("stateOnlySuportCostId").value,document.getElementById("stateOnlySuportCostId").id);
	checkWithOtherColumn(document.getElementById("stateSuportCostId").value,document.getElementById("stateSuportCostId").id);
	
	var stateOnlyCtsavalue=document.getElementById("stateOnlyCtsaId").value;
	var stateCtsaValue=document.getElementById("stateCtsaId").value;

	checkWithOtherColumn(document.getElementById("stateOnlyCtsaId").value,document.getElementById("stateOnlyCtsaId").id);
	checkWithOtherColumn(document.getElementById("stateCtsaId").value,document.getElementById("stateCtsaId").id);
	
	var stateOnlyAdminCostvalue=document.getElementById("stateOnlyAdminCostId").value;
	var stateAdminCostValue=document.getElementById("stateAdminCostId").value;
	
	checkWithOtherColumn(document.getElementById("stateOnlyAdminCostId").value,document.getElementById("stateOnlyAdminCostId").id);
	checkWithOtherColumn(document.getElementById("stateAdminCostId").value,document.getElementById("stateAdminCostId").id);

	var stateOnlyCapacityCostvalue=document.getElementById("stateOnlyCapacityCostId").value;
	var stateCapacityCostValue=document.getElementById("stateCapacityCostId").value;

	checkWithOtherColumn(document.getElementById("stateOnlyCapacityCostId").value,document.getElementById("stateOnlyCapacityCostId").id);
	checkWithOtherColumn(document.getElementById("stateCapacityCostId").value,document.getElementById("stateCapacityCostId").id);
	
	
	
	/* alert(centralReleasedAdminCostValue+"  <=  "+centralAdminCostValue);
	alert(stateOnlyAdminCostvalue+"  <=  "+stateAdminCostValue);  */
	
	
	
	   if((parseInt(centralOnlyProgramCostvalue)<=parseInt(centralReleasedProgramCostValue)) && (parseInt(centralReleasedProgramCostValue)<=parseInt(centralProgramCostValue))
	&& (parseInt(centralOnlySuportCostvalue)<=parseInt(centralReleasedSuportCostValue)) && (parseInt(centralReleasedSuportCostValue)<=parseInt(centralSuportCostValue))
	&& (parseInt(centralOnlyCtsavalue)<=parseInt(centralReleasedCtsaCostValue)) && (parseInt(centralReleasedCtsaCostValue)<=parseInt(centralCtsaValue))
	&& (parseInt(centralReleasedAdminCostValue) <= parseInt(centralOnlyAdminCostvalue)) && ( parseInt(centralReleasedAdminCostValue) <= parseInt(centralAdminCostValue))
	&& (parseInt(centralOnlyCapacityCostvalue)<=parseInt(centralReleasedCapacityCostValue)) && (parseInt(centralReleasedCapacityCostValue)<=parseInt(centralCapacityCostValue))
	&& (parseInt(stateOnlyProgramCostvalue)<=parseInt(stateProgramCostValue)) 
	&& (parseInt(stateOnlySuportCostvalue)<=parseInt(stateSuportCostValue))
	&& (parseInt(stateOnlyCtsavalue)<=parseInt(stateCtsaValue))
	&& (parseInt(stateOnlyAdminCostvalue)  <= parseInt(stateAdminCostValue))
	&& (parseInt(stateOnlyCapacityCostvalue)<=parseInt(stateCapacityCostValue))) {  
 	
		
			 
 	var id=$("#sfId").val();
	if(id>0)
	{  
		var status=window.confirm('ARE YOU SURE YOU WANT TO UPDATE ?');
		if(status==true){ 				
			  
			
			    document.forms[0].action="stateFinancialForm.do?methodName=update"+"&"+tokenParameter+"="+tokenValue;
 				document.forms[0].submit();
		}  
	}else {
	   
		var status=window.confirm('ARE YOU SURE YOU WANT TO SAVE ?');
		if(status==true){
				document.forms[0].action="stateFinancialForm.do?methodName=save"+"&"+tokenParameter+"="+tokenValue;
				document.forms[0].submit();
		}  
	 }  
	  }	
 	else 	{ /* alert("man update correct id-->"); */
		 alert("Correct the errors First..!!");
			
	  }  
	}
}
	
//window.onbeforeunload=confirmExit;
function confirmExit(){		
	var URL="login.do?methodName=logOut&"+tokenParameter+"="+tokenValue;
	if(window.event.clientY < 0 ){
		var xmlhttp;
		if (window.ActiveXObject) { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET",URL,false);
		xmlhttp.send();
	}
  }
} 


$(document).ready(function () {
   
var id=$("#sfId").val();
 if(id>0){
	$("#saveorupdate").val("Update");
	
}else{
	$("#saveorupdate").val("Save");
}

/*   $(".nndate").each(function() {
	$(this).find("input:text").each(function() {
	var dbDate=$(this).val();
	if(dbDate!=null && dbDate!=""){
	var date2 = new Date(dbDate);
	$(this).datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker('setDate', date2)
}
});
}); */  

}); 


</script>


<html:form action="login/stateFinancialForm">
  
    <jsp:useBean id="financialVO"   class="com.infotech.sgsy.stateFinance.StateFinancialVO"  scope="request"/>
    
<table  width="100%" class="pageHeaderTable">	
  <tr>
	<td class="pageHeader" align="center" >STATE FINANCIAL</td> 		 
  </tr>	
</table>
 
  <br>
  <br>
 
<div style="overflow-x:scroll;overflow-y:hidden;width:100%;" >

<!-- new code for modification -->

<table width="100%" align="center" class="bordertable inputTBL">

  <tr>
  <th rowspan="2" width="20%"></th>
  <th colspan="4" width="40%"  style="text-align: center">Project Cost</th>
  <th colspan="3" width="30%"  style="text-align: center">Other Cost</th>
  <th width="10%"  style="text-align: center">Total</th>
 </tr>
 
  <tr>
  <th width="10%" style="text-align: center">Program cost</th>
  <th width="10%"  style="text-align: center">Support cost</th>
  <th width="10%"  style="text-align: center">CTSA</th>
  <th width="10%"  style="text-align: center">Total</th>
 
  <th width="10%"  style="text-align: center">Admin cost</th>
  <th width="10%"  style="text-align: center">Capacity Building</th>
  <th width="10%"  style="text-align: center">Total</th>
  <th width="10%"  style="text-align: center"></th>
  </tr>
 
 <tr>
 <th colspan="9" align="left">Total Sanction amount (In Rs.)</th>
 </tr>



 <tr>
 <th>Central</th>
 <td><span class="text-error"><html:text   styleClass="amount" style="direction:RTL;" property="centralProgramCost"  onblur="calculateSum();" styleId="centralProgramCostId" value="${financialVO.centralProgramCost}" /></span></td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="centralSuportCost"   onblur="calculateSum();" styleId="centralSuportCostId" value="${financialVO.centralSuportCost}" /></span></td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="centralCtsa"  onblur="calculateSum();" styleId="centralCtsaId" value="${financialVO.centralCtsa}" /></span></td>
 <td id="sumCatId" class="mm" style="text-align: right;"><%-- <html:hidden  property="central_totalprojectcost" /> --%>${financialVO.central_totalprojectcost}</td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="centralAdminCost"    onblur="calculateSum();" styleId="centralAdminCostId" value="${financialVO.centralAdminCost}"/></span></td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="centralCapacityCost"    onblur="calculateSum();"  styleId="centralCapacityCostId" value="${financialVO.centralCapacityCost}" /></span></td>
 <td id="sumCatId1" class="mm" style="text-align: right;"><%-- <html:hidden property="central_otherprojectcost" />  --%>${financialVO.central_otherprojectcost}</td>
 <td id="sumCatId11" class="mm"  style="text-align: right;"><%-- <html:hidden property="central_totalcost" /> --%>  ${financialVO.central_totalcost}</td>
 </tr>




 <tr>
 <th>State</th>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="stateProgramCost"  onblur="calculateSumA();" styleId="stateProgramCostId" value="${financialVO.stateProgramCost}"/></span></td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;"  property="stateSuportCost"   onblur="calculateSumA();" styleId="stateSuportCostId" value="${financialVO.stateSuportCost}"/></span> </td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="stateCtsa"   onblur="calculateSumA();" styleId="stateCtsaId" value="${financialVO.stateCtsa}" /></span> </td>
 <td id="sumCatIdA"  class="mm"  style="text-align: right;"><%-- <html:hidden property="state_totalprojectcost" /> --%>${financialVO.state_totalprojectcost}</td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="stateAdminCost"  onblur="calculateSumA();" styleId="stateAdminCostId" value="${financialVO.stateAdminCost}" /></span> </td>
 <td><span class="text-error"><html:text  styleClass="amount" style="direction:RTL;" property="stateCapacityCost"  onblur="calculateSumA();" styleId="stateCapacityCostId" value="${financialVO.stateCapacityCost}" /></span></td>
 <td id="sumCatId1A"  class="mm"  style="text-align: right;"><%-- <html:hidden property="state_otherprojectcost" /> --%>  ${financialVO.state_otherprojectcost}</td>
 <td id="sumCatId2A"  class="mm"  style="text-align: right;"><%-- <html:hidden property="state_totalcost" /> --%>  ${financialVO.state_totalcost}</td>
 </tr>
 
 
 <tr>
 <th>Central share Released to State</th>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralReleasedProgramCost"   onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedProgramCostId" value="${financialVO.centralReleasedProgramCost}" /> <div id="spancentralReleasedProgramCost"  style="color:red;"></div></td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralReleasedSuportCost"  onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);"  styleId="centralReleasedSuportCostId" value="${financialVO.centralReleasedSuportCost}"/><div id="spancentralReleasedSuportCost"  style="color:red;"></div> </td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralReleasedCtsa"   onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedCtsaId" value="${financialVO.centralReleasedCtsa}" /><div id="spancentralReleasedCtsa"  style="color:red;"></div></td>
 <td id="sumCatIdB"  class="mm"  style="text-align: right;" ><%-- <html:hidden property="centralreleased_totalprojectcost" /> --%>  ${financialVO.centralreleased_totalprojectcost}</td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralReleasedAdminCost"    onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);"  styleId="centralReleasedAdminCostId" value="${financialVO.centralReleasedAdminCost}" /> <div id="spancentralReleasedAdminCost" style="color:red;"></div> </td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralReleasedCapacityCost"   onblur="calculateSumB(),checkWithOtherColumn(this.value,this.id);" styleId="centralReleasedCapacityCostId" value="${financialVO.centralReleasedCapacityCost}" /> <div id="spancentralReleasedCapacityCost" style="color:red;"></div></td>
 <td id="sumCatId1B"  class="mm"  style="text-align: right;"><%-- <html:hidden property="centralreleased_otherprojectcost" /> --%> ${financialVO.centralreleased_otherprojectcost}</td>
 <td id="sumCatId2B"  class="mm"  style="text-align: right;"><%-- <html:hidden property="centralreleased_totalcost" /> --%>   ${financialVO.centralreleased_totalcost}</td>
 </tr>
 
 <tr>
 <th colspan="9" align="left">Total received by SRLM (In Rs.)</th>
 </tr>
 
 <tr>
 <th>Central Share Amount</th>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralOnlyProgramCost"  onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyProgramCostId" value="${financialVO.centralOnlyProgramCost}" /> <div id="spancentralOnlyProgramCost" style="color:red;"></div></td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralOnlySuportCost"    onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlySuportCostId" value="${financialVO.centralOnlySuportCost}" />  <div id="spancentralOnlySuportCost" style="color:red;"></div></td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralOnlyCtsa"   onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyCtsaId" value="${financialVO.centralOnlyCtsa}" /><div id="spancentralOnlyCtsa" style="color:red;"></div></td>
 <td id="sumCatIdC" class="mm"  style="text-align: right;"><%-- <html:hidden property="centralonly_totalprojectcost" /> --%>${financialVO.centralonly_totalprojectcost}</td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralOnlyAdminCost"   onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);" styleId="centralOnlyAdminCostId" value="${financialVO.centralOnlyAdminCost}" /> <div id="spancentralOnlyAdminCost" style="color:red;"></div> </td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="centralOnlyCapacityCost"   onblur="calculateSumC(),checkWithOtherColumn(this.value,this.id);"  styleId="centralOnlyCapacityCostId" value="${financialVO.centralOnlyCapacityCost}" /><div id="spancentralOnlyCapacityCost" style="color:red;"></div>  </td>
 <td id="sumCatId1C" class="mm"  style="text-align: right;"><%-- <html:hidden property="centralonly_otherprojectcost" /> --%>   ${financialVO.centralonly_otherprojectcost}</td>
 <td id="sumCatId2C"  class="mm" style="text-align: right;"><%-- <html:hidden property="centralonly_totalcost" />   --%> ${financialVO.centralonly_totalcost}</td>
 </tr>
 
  
 <tr>
 <th>Central Share Released Date</th>
 <td><span class="text-error nndate"><html:text  property="centralReleasedDateProgramCost" readonly="true" styleId="centralReleasedDateProgramCostId" value="${financialVO.centralReleasedDateProgramCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="centralReleasedDatSuportCost" readonly="true"  styleId="centralReleasedDatSuportCostId" value="${financialVO.centralReleasedDatSuportCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="centralReleasedDatCtsa" readonly="true"  styleId="centralReleasedDatCtsaId" value="${financialVO.centralReleasedDatCtsa}"/></span></td>
 <td></td>
 <td><span class="text-error nndate"><html:text  property="centralReleasedDatAdminCost"  readonly="true" styleId="centralReleasedDatAdminCostId" value="${financialVO.centralReleasedDatAdminCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="centralReleasedDatCapacityCost"  readonly="true" styleId="centralReleasedDatCapacityCostId" value="${financialVO.centralReleasedDatCapacityCost}" /></span></td>
 <td></td>
 <td></td>
 </tr>
 
 
  <tr>
 <th>State Share Amount</th>
 <td><html:text style="direction:RTL;" styleClass="amount" property="stateOnlyProgramCost"  onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyProgramCostId" value="${financialVO.stateOnlyProgramCost}"/> <div id="spanstateOnlyProgramCost" style="color:red;"></div> </td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="stateOnlySuportCost"  onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlySuportCostId" value="${financialVO.stateOnlySuportCost}" /><div id="spanstateSuportCost" style="color:red;"></div></td>
 <td><html:text  style="direction:RTL;" styleClass="amount"  property="stateOnlyCtsa"   onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyCtsaId" value="${financialVO.stateOnlyCtsa}" /> <div id="spanstateOnlyCtsa" style="color:red;"></div> </td>
 <td id="sumCatIdE" class="mm"  style="text-align: right;"><%-- <html:hidden property="stateonly_totalprojectcost" /> --%>  ${financialVO.stateonly_totalprojectcost}</td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="stateOnlyAdminCost"    onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyAdminCostId" value="${financialVO.stateOnlyAdminCost}" /> <div id="spanstateOnlyAdminCost" style="color:red;"></div> </td>
 <td><html:text  style="direction:RTL;" styleClass="amount" property="stateOnlyCapacityCost"    onblur="calculateSumE(),checkWithOtherColumn(this.value,this.id);" styleId="stateOnlyCapacityCostId" value="${financialVO.stateOnlyCapacityCost}" /> <div id="spanstateOnlyCapacityCost" style="color:red;"></div> </td>
 <td id="sumCatId1E" class="mm"  style="text-align: right;"><%-- <html:hidden property="stateonly_otherprojectcost" /> --%>  ${financialVO.stateonly_otherprojectcost}</td>
 <td id="sumCatId2E"  class="mm" style="text-align: right;"><%-- <html:hidden property="stateonly_totalcost" /> --%>  ${financialVO.stateonly_totalcost}</td>
 </tr>
 
 
 <tr>
 <th>State Share Released Date</th>
 <td><span class="text-error nndate"><html:text  property="stateReleasedDateProgramCost" readonly="true"  styleId="stateReleasedDateProgramCostId" value="${financialVO.stateReleasedDateProgramCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="stateReleasedDateSuportCost" readonly="true"  styleId="stateReleasedDateSuportCostId" value="${financialVO.stateReleasedDateSuportCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="stateReleasedDateCtsa" readonly="true"  styleId="stateReleasedDateCtsaId" value="${financialVO.stateReleasedDateCtsa}" /></span></td>
 <td></td>
 <td><span class="text-error nndate"><html:text  property="stateReleasedDateAdminCost" readonly="true"  styleId="stateReleasedDateAdminCostId" value="${financialVO.stateReleasedDateAdminCost}" /></span></td>
 <td><span class="text-error nndate"><html:text  property="stateReleasedDateCapacityCost" readonly="true"  styleId="stateReleasedDateCapacityCostId" value="${financialVO.stateReleasedDateCapacityCost}" /></span></td>
 <td></td>
 <td></td>
 </tr>
<tr> <td colspan="9" style="text-align:center;border-style: none;">
  <table width="100%" style="text-align:center;border-style: none;">
    <tr>
      <td  style="text-align:center;border-style: none;"><input type="hidden" id="sfId" name="id" value="${financialVO.id}"/><input name="Button" type="button" id="saveorupdate" class="button checkPermissionForFormForInsert" value="save" onclick="save()"/> 
   	  <html:button styleClass="button" property="" onclick="closePage(tokenParameter,tokenValue)"><bean:message  key="button.close" /></html:button></td>
    </tr>
  </table>
</td></tr>

</table>
</div>


</html:form>



