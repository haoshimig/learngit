package org.com.partThree;

public class myRunnable implements Runnable{

	private Foo foo=new Foo();
	
	public static void main(String[] args) {
		myRunnable myRun=new myRunnable();
		Thread ta=new Thread(myRun, "Thread-A");
		Thread tb=new Thread(myRun, "Thread-B");
		ta.start();
		tb.start();
	}
	
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			this.fix(30);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+":当前foo对象的x值="+foo.getX());
		}
	}
	
	public int fix(int y){
		return foo.fix(y);
	}

}
