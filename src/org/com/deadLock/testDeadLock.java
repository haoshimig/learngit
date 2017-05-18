package org.com.deadLock;

public class testDeadLock {
        public static void main(String[] args) {
			DeadLock deadLock=new DeadLock();
			myThread t1=new myThread(deadLock, 1, 2);
			myThread t2=new myThread(deadLock, 3, 4);
			myThread t3=new myThread(deadLock, 5, 6);
			myThread t4=new myThread(deadLock, 7, 8);
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();
		}
        
static class myThread extends Thread{
	private DeadLock deadLock;
	private int a,b;
    
	public myThread(DeadLock deadLock, int a, int b) {
		this.deadLock = deadLock;
		this.a = a;
		this.b = b;
	}

	public void run() {
		deadLock.read();
		deadLock.write(a, b);
	}
	
}

static class DeadLock{
	private Resource resourceA=new Resource();
	private Resource resourceB=new Resource();
	
	private int read(){
		synchronized (resourceA) {
			   System.out.println("read:"+Thread.currentThread().getName()+"获取了resourceA的锁！");
			   try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (resourceB) {
				System.out.println("read:"+Thread.currentThread().getName()+"获取了resourceB的锁！");
				return resourceB.value+resourceA.value;
			}
		}
	}
	
	private void write(int a,int b){
		synchronized (resourceB) {
			System.out.println("write:"+Thread.currentThread().getName()+"获取了resourceB的锁！");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     synchronized (resourceA) {
		    	 System.out.println("write:"+Thread.currentThread().getName()+"获取了resourceA的锁！");
		    	 resourceA.value=a;
		    	 resourceB.value=b;
			}	
		}
	}
}

static class Resource{
	public int value;
}

}
