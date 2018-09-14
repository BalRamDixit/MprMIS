package com.infotech.sgsy.util;


class superClass{
	
	int i = 10;
	int show(){
		
		System.out.println("i" + i);
		return i;
		
	}
	
}

class subClass extends superClass{
	
	int i = 20;
	int show(){
		
		return i;
		
	}
	
	
	
}


public class A extends superClass{
	
	int i = 20;
	int show(){
		
		return i;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		superClass class1 = new subClass();
		
		System.out.println("Variable" + class1.i);
		
		System.out.println("Variable" + class1.show());

	}

}
