package org.com.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testCachedThreadPool {
       public static void main(String[] args) {
		   //����һ���������̳߳أ����޽���з�ʽ����
    	   ExecutorService pool=Executors.newSingleThreadExecutor();
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
	}
}

}
