package com.infotech.sgsy.filter;

 

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Cookie;
import java.util.Enumeration;
import java.io.IOException;
import javax.servlet.ServletException;


import com.infotech.sgsy.util.EscapeChars;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RequestFilter implements Filter{

  private FilterConfig filterConfig;

  static class FilteredRequest extends HttpServletRequestWrapper {

	  public FilteredRequest(ServletRequest request) {
		super((HttpServletRequest)request);
	  }

	/** Method used to remove the unsafe characters(<,>,(,),%) from parameter values */
	  public String getParameter(String paramName) {
		String value = removeUntrustChars(super.getParameter(paramName));
		return value;
	  }

/** Method used to remove the unsafe characters(<,>,(,),%) from parameter values */
	  public String[] getParameterValues(String paramName) {
		  String cleanData ="";
	  	String values[] = super.getParameterValues(paramName);
		if(values != null && values.length >0){
		   for (int index = 0; index < values.length; index++) {
  		      if(values[index] != null && values[index].length()>0){
  		    	  
  		    	 /*Pattern evilChars = Pattern.compile("[^a-zA-Z0-9-._\\s]");
  		    	   cleanData = evilChars.matcher(values[index]).replaceAll("");
  		    	   values[index] =cleanData; */
  		    	  values[index] = removeUntrustChars(values[index]);
  		    	}
	  	    }
	  	}
	  	return values;
	  }
	  
/** Method used to remove the unsafe characters(<,>,(,),%) from cookie values */
	  public Cookie[] getCookies(){
		  String cleanData ="";
	  	Cookie[] cookies = super.getCookies();
	    	for (int i = 0; i < cookies.length; i++) {
	    		
	    		/*Pattern evilChars = Pattern.compile("[^a-zA-Z0-9-._]");
  		    	  cleanData = evilChars.matcher(cookies[i].getValue()).replaceAll("");
  		          cookies[i].setValue(removeUntrustChars(cleanData)); */
	    	      cookies[i].setValue(removeUntrustChars(cookies[i].getValue()));
	    	}
	    	return cookies;
	  }
	  

/** Method used to remove the unsafe characters(<,>,(,),%) from given String. */
	  private String removeUntrustChars(String value){
	  try{
		 
	  	if(value != null && value.length()>0){
		   value = value.replaceAll(">"," ");
		   value = value.replaceAll("="," ");
		   value = value.replaceAll("'"," ");
		   value = value.replaceAll("`"," ");		  		
		   value = value.replaceAll("<"," ");
	  	   value=EscapeChars.forScriptTagsOnly(value);
		   value = value.replaceAll("\\("," ");
		   value = value.replaceAll("\\)"," ");
		   value = value.replaceAll(">"," ");
		   value = value.replaceAll("<"," ");
	  	   value=EscapeChars.forScriptTagsOnly(value);
		   value = value.replaceAll("\\("," ");
		   value = value.replaceAll("\\)"," ");
		   value = value.replaceAll("%"," ");
		   
		   //value = value.replaceAll("and"," ");		   
		   //value = value.replaceAll("And"," ");
		   //value = value.replaceAll("AND"," ");
		   
		   value = value.replaceAll("SELECT"," ");		   
		   value = value.replaceAll("Select"," ");
		   value = value.replaceAll("select"," ");
		   
		   
		   value = value.replaceAll("Insert"," ");
		   value = value.replaceAll("INSERT"," ");
		   value = value.replaceAll("insert"," ");
		   
           value = value.replaceAll("Delete"," ");		   
		   value = value.replaceAll("DELETE"," ");
		   value = value.replaceAll("delete"," ");
		   
		  /* value = value.replaceAll("UPDATE"," ");
		   value = value.replaceAll("Update"," ");
		   value = value.replaceAll("update"," ");*/
           value = value.replaceAll("Insert"," ");
		   
		   value = value.replaceAll("INSERT"," ");
		   value = value.replaceAll("insert"," ");
		   
           value = value.replaceAll("Where"," ");
		   value = value.replaceAll("WHERE"," ");
		   value = value.replaceAll("where"," ");
		   value = value.replaceAll("Where"," ");
		   		   
		}
	  	
	  	
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  
	  }
		return value;
	  }
  }

  public void init(FilterConfig filterConfig){
	  this.filterConfig=filterConfig;
  }

  public void doFilter (ServletRequest request,ServletResponse response,FilterChain chain) {

    try{

       // Wrapping the request object using custom wrapper
        FilteredRequest filteredRequest= new FilteredRequest(request);
        chain.doFilter(filteredRequest, response);
    }
    catch (IOException io){      
    }
    catch (ServletException se)
    {
     
    }
  }

  public FilterConfig getFilterConfig(){
    return this.filterConfig;
  }

  public void setFilterConfig (FilterConfig filterConfig){
    this.filterConfig = filterConfig;
  }
  
  
  public void destroy(){
  }
}