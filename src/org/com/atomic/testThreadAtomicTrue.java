package org.com.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ԭ������Ȼ���Ա�֤����������ĳһ�������̵İ�ȫ�����޷���֤��������飨����˵�������򣩵İ�ȫ��
 * ���ԣ�Ӧ��������ͬ���������飩��ͬ��������������������İ�ȫ��
 */
public class testThreadAtomicTrue {
     public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		Lock lock=new ReentrantLock(false);
		Runnable r1=new myRunnable("����", 50000,lock);
		Runnable r2=new myRunnable("����", 10000,lock);
		Runnable r3=new myRunnable("����", 30000,lock);
		Runnable r4=new myRunnable("����", 20000,lock);
		Runnable r5=new myRunnable("����", 70000,lock);
		//�����̳߳أ�ִ�и����߳�
		pool.execute(r1);
		pool.execute(r2);
		pool.execute(r3);
		pool.execute(r4);
		pool.execute(r5);
		//�ر��̳߳�
		pool.shutdown();
	}
     
static class myRunnable implements Runnable{
	private static AtomicLong aLong=new AtomicLong(100000);   //ԭ������ÿ���̶߳��������ɲ���
	private String name;  //������
	private int cash;  //�������
	private Lock lock; //����������
	
	public myRunnable(String name, int cash, Lock lock) {
		this.name = name;
		this.cash = cash;
		this.lock=lock;
	}

	@Override
	public void run() {
		lock.lock();   //��ȡ��
		System.out.println(name+"ִ����"+cash+"����ǰ��"+aLong.addAndGet(cash));
		lock.unlock();  //�ͷ���
	}
}
}
