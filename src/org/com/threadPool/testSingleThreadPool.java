package org.com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testSingleThreadPool {
       public static void main(String[] args) {
		   //创建一个可变大小的程池，以前构建的线程可用时将重用它们
    	   ExecutorService pool=Executors.newCachedThreadPool();
    	   //创建实现了Runnable接口的对象，当然Thread也实现了该接口
    	   myThread t1=new myThread();
    	   myThread t2=new myThread();
    	   myThread t3=new myThread();
    	   myThread t4=new myThread();
    	   myThread t5=new myThread();
    	   myThread t6=new myThread();
    	   //将线程放入线程池进行执行
    	   pool.execute(t1);
    	   pool.execute(t2);
    	   pool.execute(t3);
    	   pool.execute(t4);
    	   pool.execute(t5);
    	   pool.execute(t6);
    	   //关闭线程池
    	   pool.shutdown();
       }

static class myThread extends Thread{

	@Override
	public void run() {
		 System.out.println(Thread.currentThread().getName()+"正在执行...");
	}
}

}
