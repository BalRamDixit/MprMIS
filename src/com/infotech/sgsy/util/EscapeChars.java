package com.infotech.sgsy.util;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EscapeChars {

	 public static String forHTML(String aText){
	     final StringBuilder result = new StringBuilder();
	     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	     char character =  iterator.current();
	     while (character != CharacterIterator.DONE ){
	       if (character == '<') {
	         result.append("&lt;");
	       }
	       else if (character == '>') {
	         result.append("&gt;");
	       }
	       else if (character == '&') {
	         result.append("&amp;");
	      }
	       else if (character == '\"') {
	         result.append("&quot;");
	       }
	       else if (character == '\t') {
	         addCharEntity(9, result);
	       }
	       else if (character == '!') {
	         addCharEntity(33, result);
	       }
	       else if (character == '#') {
	         addCharEntity(35, result);
	       }
	       else if (character == '$') {
	         addCharEntity(36, result);
	       }
	       else if (character == '%') {
	         addCharEntity(37, result);
	       }
	       else if (character == '\'') {
	         addCharEntity(39, result);
	       }
	      /* else if (character == '(') {
	         addCharEntity(40, result);
	       }
	       else if (character == ')') {
	         addCharEntity(41, result);
	       }*/
	       else if (character == '*') {
	         addCharEntity(42, result);
	       }
	       else if (character == '+') {
	         addCharEntity(43, result);
	       }
	       else if (character == ',') {
	         addCharEntity(44, result);
	       }
	       else if (character == '-') {
	         addCharEntity(45, result);
	       }
	       else if (character == '.') {
	         addCharEntity(46, result);
	       }
	       else if (character == '/') {
	         addCharEntity(47, result);
	       }
	       else if (character == ':') {
	         addCharEntity(58, result);
	       }
	       else if (character == ';') {
	         addCharEntity(59, result);
	       }
	       else if (character == '=') {
	         addCharEntity(61, result);
	       }
	       else if (character == '?') {
	         addCharEntity(63, result);
	       }
	       else if (character == '@') {
	         addCharEntity(64, result);
	       }
	       else if (character == '[') {
	         addCharEntity(91, result);
	       }
	       else if (character == '\\') {
	         addCharEntity(92, result);
	       }
	       else if (character == ']') {
	         addCharEntity(93, result);
	       }
	       else if (character == '^') {
	         addCharEntity(94, result);
	       }
	       else if (character == '_') {
	         addCharEntity(95, result);
	       }
	       else if (character == '`') {
	         addCharEntity(96, result);
	       }
	       else if (character == '{') {
	         addCharEntity(123, result);
	       }
	       else if (character == '|') {
	         addCharEntity(124, result);
	       }
	       else if (character == '}') {
	         addCharEntity(125, result);
	       }
	       else if (character == '~') {
	         addCharEntity(126, result);
	       }
	       else {
	         //the char is not a special one
	         //add it to the result as is
	         result.append(character);
	       }
	       character = iterator.next();
	     }
	     return result.toString();
	  }
	 
 public static String forHrefAmpersand(String aURL){
		    return aURL.replace("&", "&amp;");
		  }
	 
 public static String forURL(String aURLFragment){
	     String result = null;
	     try {
	       result = URLEncoder.encode(aURLFragment, "UTF-8");
	     }
	     catch (UnsupportedEncodingException ex){
	       throw new RuntimeException("UTF-8 not supported", ex);
	     }
	     return result;
	   }
	 
 public static String toDisableTags(String aText){
		    final StringBuilder result = new StringBuilder();
		    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		    char character =  iterator.current();
		    while (character != CharacterIterator.DONE ){
		      if (character == '<') {
		        result.append("&lt;");
		      }
		      else if (character == '>') {
		        result.append("&gt;");
		      }
		      else {
		        //the char is not a special one
		        //add it to the result as is
		        result.append(character);
		      }
		      character = iterator.next();
		    }
		    return result.toString();
		  }
	  
 public static String forScriptTagsOnly(String aText){
	    String result = null;
	    Matcher matcher = SCRIPT.matcher(aText);
	    result = matcher.replaceAll("&lt;SCRIPT>");
	    matcher = SCRIPT_END.matcher(result);
	    result = matcher.replaceAll("&lt;/SCRIPT>");
	    return result;
	  }
	  
 private static final Pattern SCRIPT = Pattern.compile(
		    "<SCRIPT>", Pattern.CASE_INSENSITIVE
		   );
 private static final Pattern SCRIPT_END = Pattern.compile(
		    "</SCRIPT>", Pattern.CASE_INSENSITIVE
		  );
 
 private static void addCharEntity(Integer aIdx, StringBuilder aBuilder){
	    String padding = "";
	    if( aIdx <= 9 ){
	       padding = "00";
	    }
	    else if( aIdx <= 99 ){
	      padding = "0";
	    }
	    else {
	      //no prefix
	    }
	    String number = padding + aIdx.toString();
	    aBuilder.append("&#" + number + ";");
	  }
}
