package org.com.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class testThreadPoolExecutor {
       public static void main(String[] args) {
    	   //�����ȴ�����
    	   BlockingQueue<Runnable> blockingQueue=new ArrayBlockingQueue<Runnable>(20);
		   //����һ���Զ����̳߳أ����԰����ڸ����ӳٺ�����������߶���ִ��
    	   ThreadPoolExecutor pool=new ThreadPoolExecutor(3, 5, 20, TimeUnit.MILLISECONDS, blockingQueue);
    	   //����ʵ����Runnable�ӿڵĶ��󣬵�ȻThreadҲʵ���˸ýӿ�
    	   myThread t1=new myThread();
    	   myThread t2=new myThread();
    	   myThread t3=new myThread();
    	   myThread t4=new myThread();
    	   myThread t5=new myThread();
    	   myThread t6=new myThread();
    	   //���̷߳����̳߳ؽ���ִ��
    	   pool.execute(t1);
    	   pool.execute(t2);
    	   pool.execute(t3);
    	   pool.execute(t4);
    	   pool.execute(t5);
    	   pool.execute(t6);
    	   //�ر��̳߳�
    	   pool.shutdown();
       }

static class myThread extends Thread{

	@Override
	public void run() {
		 System.out.println(Thread.currentThread().getName()+"����ִ��...");
		 try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}
