package org.com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testSingleThreadScheduledExcutor {
       public static void main(String[] args) {
		   //����һ���������ӳ����ӳأ��ɰ����ڸ����ӳٺ�ִ���������ִ��
    	   ScheduledExecutorService pool=Executors.newSingleThreadScheduledExecutor();
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
    	   //ʹ���ӳ�ִ�еķ���
    	   pool.schedule(t5, 2000, TimeUnit.MILLISECONDS);
    	   pool.schedule(t5, 2000, TimeUnit.MILLISECONDS);
    	   //�ر��̳߳�
    	   pool.shutdown();
       }

static class myThread extends Thread{

	@Override
	public void run() {
		 System.out.println(Thread.currentThread().getName()+"����ִ��...");
	}
}

}
