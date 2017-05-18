package org.com.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class testThreadPoolExecutor {
       public static void main(String[] args) {
    	   //创建等待队列
    	   BlockingQueue<Runnable> blockingQueue=new ArrayBlockingQueue<Runnable>(20);
		   //创建一个自定义线程池，可以安排在给定延迟后运行命令或者定期执行
    	   ThreadPoolExecutor pool=new ThreadPoolExecutor(3, 5, 20, TimeUnit.MILLISECONDS, blockingQueue);
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
		 try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}
