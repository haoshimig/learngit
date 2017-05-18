package org.com.partOne;

public class testThread extends Thread{
      public testThread(String name){
    	  super(name);
      }
      
      public void run(){
    	  for(int i=0;i<5;i++){
    		  for(long k=0;k<100;k++){
    			  try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    			  System.out.println(this.getName()+":"+i);
    		  }
    	  }
      }
      
      public static void main(String[] args) {
		Thread td1=new testThread("张三");
		Thread td2=new testThread("李四");
		//td1.setPriority(MIN_PRIORITY);
		//td2.setPriority(MAX_PRIORITY);
		//td2.yield();
		td1.start();
		try {
			td1.join();
			//td1.join(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//td1.run();
//		try {
//			td2.sleep(5000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		td2.start();
		
		System.out.println(Thread.currentThread().getName());
	}
}
