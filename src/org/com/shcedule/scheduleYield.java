package org.com.shcedule;

public class scheduleYield {
        
static class myThread1 extends Thread{
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"第"+i+"次执行！");
		}
	}
         	
}

static class myRunnable implements Runnable{
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"第"+i+"次执行！");
			Thread.yield();
		}
	}
	
}

public static void main(String[] args) {
	Thread t1=new myThread1();
	Thread t2=new Thread(new myRunnable());
	t2.start();
	t1.start();
}

}
