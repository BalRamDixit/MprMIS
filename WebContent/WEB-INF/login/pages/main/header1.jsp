<%@page import="com.infotech.sgsy.userAccessControlManagement.UserMaster"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!--Masthead starts here-->  
 
 <%@page import="com.infotech.sgsy.login.LoginVO"%>
 <%@page import="com.infotech.sgsy.util.Constants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<style>

</style>
<script type="text/javascript">    

var tokenParameter='reqtrack';
var tokenValue= '<%=request.getSession().getAttribute("TRACKERID") %>' ; 

   
   	var message="Function Disabled!";

	function clickIE4()
	{
		if (event.button==2)
		{
			//alert(message);
			return false;
		}
	}

	function clickNS4(e)
	{
		if (document.layers||document.getElementById&&!document.all)
		{
			if (e.which==2||e.which==3)
			{
				//alert(message);
				return false;
			}
		}
	}

	if (document.layers)
	{
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=clickNS4;
	}
	else if (document.all&&!document.getElementById)
	{
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

/////////////////

 
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
 <style>
 	
 </style>
 <html>
<body  >
<!-- HEADER DIV START -->
   <div class="mainHeader">
   		<div class="headerBoxlink">
        <a href="login.do?methodName=login"><img src="../images/ddukgylogo.jpg" height="80px" alt="" width="100%"/></a>
        <!-- Header Menu Start  -->
		</div>
		<div class="headerBox">
	        <ul>   
		      	<logic:present name="userVO" >	      
		      		<bean:define id="user" name="userVO" type="com.infotech.sgsy.userAccessControlManagement.UserMaster"  ></bean:define>	      
		      	</logic:present>
		      	<% UserMaster loginvo= (UserMaster)request.getSession().getAttribute("userVO");      
			      if(loginvo!=null){      
			      %>
				      <li><b>WELCOME</b> &nbsp;&nbsp;<b><bean:write name="user" property="userName" /></b></li>
				      <li><b>Logged In as</b> &nbsp;&nbsp;<b>${userType}</b>
				      <c:if test="${RoleId eq '45'}">
				      		for <b>${stateName}</b>
				      </c:if>
				      </li>
			      <%
			      } 
			      %>
		   	</ul>
	 		<p id="feeds">
	 		  	<a href="javascript:openFile('2')" >Change Password</a> &nbsp;&nbsp;&nbsp;
	            <a href="javascript:getTracker()" >LogOut</a>       
	        </p>
		    <p id="helpDiv">
		    	<a href="files/DDUGKY_UserManual.pdf" target='_BLANK' title='Click Here To Download User Manual For System'>User Manual</a>    
		    </p>
	 	</div>
	<!--  Header Menu End -->
    </div>
    
     <hr class="noscreen" />
<!-- HEADER DIV END -->
  </body>
  </html>
  

