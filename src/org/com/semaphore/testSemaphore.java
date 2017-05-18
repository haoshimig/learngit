package org.com.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class testSemaphore {
     public static void main(String[] args) {
		myPool myPool=new myPool(20);
		//�����̳߳�
		ExecutorService threadPool=Executors.newFixedThreadPool(2);
		myThread t1=new myThread("����A", myPool, 5);
		myThread t2=new myThread("����B", myPool, 8);
		myThread t3=new myThread("����C", myPool, 12);
		//���̳߳���ִ������
		threadPool.execute(t1);
		threadPool.execute(t2);
		threadPool.execute(t3);
		//�ر��̳߳�
		threadPool.shutdown();
	}

static class myPool{
	private Semaphore sp;  //����ص��ź���

	//�صĴ�С�ᴫ�ݸ��ź���
	public myPool(int size) {
		this.sp = new Semaphore(size);
	}

	public Semaphore getSp() {
		return sp;
	}

	public void setSp(Semaphore sp) {
		this.sp = sp;
	}
}

static class myThread extends Thread{
	private String ThreadName;  //�߳�����
	private myPool pool;   //�Զ����
	private int x;  //�����ź����Ĵ�С
	public myThread(String threadName, myPool pool, int x) {
		ThreadName = threadName;
		this.pool = pool;
		this.x = x;
	}
	@Override
	public void run() {
		try {
			//�Ӵ��ź�����ȡ������Ŀ�����
			pool.getSp().acquire(x);
			System.out.println(ThreadName+"�ɹ���ȡ��"+x+"����ɣ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷŸ�����Ŀ����ɣ����䷵�ص��ź���
			pool.getSp().release(x);
			System.out.println(ThreadName+"�ͷ���"+x+"����ɣ�");
		}
	}
}
     
}
