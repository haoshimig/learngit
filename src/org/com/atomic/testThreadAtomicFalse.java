package org.com.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/*
 * ԭ������Ȼ���Ա�֤����������ĳһ�������̵İ�ȫ�����޷���֤��������飨����˵�������򣩵İ�ȫ��
 */
public class testThreadAtomicFalse {
     public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		Runnable r1=new myRunnable("����", 50000);
		Runnable r2=new myRunnable("����", 10000);
		Runnable r3=new myRunnable("����", 30000);
		Runnable r4=new myRunnable("����", 20000);
		Runnable r5=new myRunnable("����", 70000);
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
	
	public myRunnable(String name, int cash) {
		this.name = name;
		this.cash = cash;
	}

	@Override
	public void run() {
		System.out.println(name+"ִ����"+cash+"����ǰ��"+aLong.addAndGet(cash));
	}
}
}
