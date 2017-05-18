package org.com.partFour;

public class testWaitThread {
      public static void main(String[] args) {
		  testThreadB b=new testThreadB();
		  b.start();
		  //线程A拥有b对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者 
		  synchronized (b) {
			  try {
				  System.out.println("等待对象B完成计算...");
				  //当前线程A等待
				  b.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    System.out.println("b对象计算的总和为："+b.total);
		  }
      }
}
