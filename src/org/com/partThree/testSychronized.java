package org.com.partThree;

public class testSychronized {
      public static void main(String[] args) {
    	  final testNameList nl=new testNameList();
    	  nl.add("aaa");
    	  class nameDropped extends Thread{

			@Override
			public void run() {
				String name=nl.removeFirst();
				System.out.println(name);
			}
    		  
    	  }
    	  Thread ta=new nameDropped();
    	  Thread tb=new nameDropped();
    	  ta.start();
    	  tb.start();
      }
}
