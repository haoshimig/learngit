package org.com.partOne;

public class testRunnable {
		public static void main(String[] args) {
		     doSomething ds1=new doSomething("����");	
		     doSomething ds2=new doSomething("����");
		     Thread td1=new Thread(ds1);
		     Thread td2=new Thread(ds2);
		     td1.start();
		     td2.start();
		}

}
