package org.com.shcedule;

public class schedulePriority {
        
static class myThread1 extends Thread{
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"第"+i+"次执行！");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
         	
}

static class myRunnable implements Runnable{
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+"第"+i+"次执行！");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public static void main(String[] args) {
	Thread t1=new myThread1();
	Thread t2=new Thread(new myRunnable());
	t1.setPriority(10);
	t2.setPriority(1);
	t2.start();
	t1.start();
}

}
