
 
 
 
<html> 
<body>
<script type="text/javascript">    
 
var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 
	
// disable the backward-forward button of browser
	function disableKeys(){
		window.history.forward(); 
	}
   setTimeout("disableKeys()", 0);
   window.onbeforeunload=function(){null};
  
   
// disable the mouse right button
   	var message="Function Disabled!";

	function clickIE4(){
		if (event.button==2){
			//alert(message);
			return false;
		}
	}

	function clickNS4(e){
		if (document.layers||document.getElementById&&!document.all){
			if (e.which==2||e.which==3){
				//alert(message);
				return false;
			}
		}
	}

	if (document.layers){
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=clickNS4;
	}
	else if (document.all&&!document.getElementById){
		document.onmousedown=clickIE4;
	}
	document.oncontextmenu=new Function("return false")
	
// Added on 
 
function startsWith(st, wi){
	  	if (st == '') {return wi == ''}
	  	return st.substring(wi.length,0)==wi ;
	}
 
function replaceQueryString(a,k,v) {
	   var re = new RegExp("([?|&|//])" + k + "=.*?(&|$)","i");     
	   //alert(a.match(re));  
	   if (a.match(re)){
	       return a.replace(re,'$1' + k + "=" + v + '$2');
	   }else{
	    //alert(a);
	       	if(a.indexOf('?')==-1){
		 		return a+'?'+k+'='+v;
		 	}else{
		 		return a+'&'+k+'='+v;
		 	}
	     //   	return a + '&' + k + "=" + v;
	   }
	}


 
 	var anchors = document.getElementsByTagName('a'); 
    //var forms = document.getElementsByTagName('form'); 
    
	for(var i = 0; i < anchors.length; i++) {	
		var hrefStr = anchors[i].href; 	 
 	    // alert(anchors[i].href);
 	 	var str = hrefStr ;
		if(str.split(':')[0]!='javascript'){
		str = str.substring(str.length-1, str.length );
        if(str != '#')
      	//  alert("tokenParameter" + tokenParameter + "tokenValue"  + tokenValue);	
	 	anchors[i].href=replaceQueryString(hrefStr,tokenParameter,tokenValue);
	 	//alert(anchors[i].href);	 
	 	 }        
	}
 </script>
<!-- HEADER DIV START -->
     <div style="background-color: #51398D; height: 80px;">
        <img src="images/ddukgylogo.jpg" height="80px" alt="" />
    </div>
     <hr class="noscreen" />
<!-- HEADER DIV END -->
</body>
</html>


