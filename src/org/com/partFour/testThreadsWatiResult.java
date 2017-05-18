package org.com.partFour;

public class testThreadsWatiResult extends Thread{

	calculator cacu;

	public testThreadsWatiResult(calculator cacu) {
		this.cacu = cacu;
	}

	@Override
	public void run() {
		synchronized (cacu) {
			System.out.println(Thread.currentThread().getName()+"等待当前线程计算结果...");
			try {
				cacu.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"计算结果为："+cacu.total);
		}
	}
	
	public static void main(String[] args) {
		calculator calculator=new calculator();
		//启动三个线程
		new testThreadsWatiResult(calculator).start();
		new testThreadsWatiResult(calculator).start();
		new testThreadsWatiResult(calculator).start();
	   //启动计算结果
		calculator.start();
	}
	
}
