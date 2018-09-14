package com.infotech.sgsy.util;


import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author 34966
 *
 * ListMap is to keep the order in which the data is stored in collection.
 * It has the quality of a List ( iteration ) and a Hashtable ( key ,value )
 * pair data storing.
 */


public class ListMap extends Hashtable{
	ArrayList keys = new ArrayList();

/**
 * Retruns the object at a perticular position.
 */
    public Object getValue(int index) {
    	try {
    		Object obj = keys.get(index);
            return this.get(obj);
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
 	/**
	 * Put , overides the method of Hashtable. 
	 * It also update the sequence in Arraylist by which its order is 
	 * maintained. 
	 */
    
	public synchronized Object put(Object arg0, Object arg1) {
		keys.add(arg0);
		return super.put(arg0, arg1);
	}
	
	/**
	 * Add method will put the object in the corresponding possition specified
	 *  @param index : Insert at this index 
	 *  		arg0 : Object at this position.
	 */
	
	public Object add(int index, Object arg0,Object arg1){
		keys.add(index,arg0);
		return super.put(arg0,arg1);
	}
	
	public void add(Object arg0){
		keys.add(arg0);
	}
	
	public synchronized void putAll(ListMap arg0) {
	
		if(arg0 != null && arg0.size()!=0) {
			//for(Enumeration enumt = (Enumeration) arg0.keys() ; enumt.hasMoreElements();){
			for(int i = 0 ; i < arg0.size() ; i++){
				Object obj1 = arg0.getKey(i);
				Object obj2 = arg0.getValue(i);
				this.put(obj1,obj2);
				
			}
			
		}
		 
		
	}
	
/**
 * * @param index
 *  @return key at the perticular index
 */
	public Object getKey(int index){
		
		return keys.get(index);
	}
	
	/**
	 * Total size of this object
	 */
	
	public int size(){
		return keys.size();
	}
	
	public boolean contains(Object obj){
		if(keys.contains(obj)){
			return true;
		}else{
			return false;
		}
	}
	
	public ArrayList getArrayListofKeys(){
		return keys;
	}
	
	public void sort(){
		Collections.sort(keys);
	}
	
	
	/**
	 * Remove method composed by maria
	 *  
	 */
	
	
	public synchronized Object remove(Object arg0) {
		keys.remove(arg0);
		return super.remove(arg0);
	}
	
	public String  toStringAl(){
		if(keys != null){
		return keys.toString();
		}else{
			return "";
		
		}
	}
}
