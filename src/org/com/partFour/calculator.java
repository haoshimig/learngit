package org.com.partFour;

public class calculator extends Thread{

	int total;

	public void run() {
          synchronized (this) {
			 for(int i=0;i<=100;i++){
				 total+=i;
			 }
			//通知所有在此对象上等待的线程
			//notifyAll();
		   try {
                 Thread.sleep(1000*5);//为了演示效果明显
           } catch (InterruptedException e) {
                 e.printStackTrace();
           }
           //完成计算 唤醒在此对象监视器上等待的单个线程
           notify();
          }		
	}
	
}
