package org.com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testScheduledThreadPool {
       public static void main(String[] args) {
		   //创建一个固定大小的线程池，可安排在给定延迟后执行命令或定期执行
    	   ScheduledExecutorService pool=Executors.newScheduledThreadPool(2);
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
    	   //使用延迟执行的方法
    	   pool.schedule(t5, 2000, TimeUnit.MILLISECONDS);
    	   pool.schedule(t5, 2000, TimeUnit.MILLISECONDS);
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
