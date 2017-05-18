package org.com.lockCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testLockCondition2 {
       public static void main(String[] args) {
    	   //�������������˻�
		   myCount myCount=new myCount("95599200901215522", 100000);
		   //����һ���̳߳�
		   ExecutorService pool=Executors.newFixedThreadPool(2);
		   Thread t1=new drawThread("����", myCount, 60000);
		   Thread t2=new drawThread("����", myCount, 60000);
		   Thread t3=new saveThread("����", myCount, 20000);
		   Thread t4=new saveThread("����", myCount, 120000);
		   Thread t5=new drawThread("С��", myCount, 80000);
		   Thread t6=new saveThread("�ϰ�", myCount, 110000);
		   //ִ�и����߳�
		   pool.execute(t1);
		   pool.execute(t2);
		   pool.execute(t3);
		   pool.execute(t4);
		   pool.execute(t5);
		   pool.execute(t6);
		   //�ر��̳߳�
		   pool.shutdown();
		   
       }
//����߳���
static class saveThread extends Thread{
	private String name;  //������
	private myCount myCount;  //�˻�
	private int cash;  //�����
	public saveThread(String name,myCount myCount, int cash) {
		this.name = name;
		this.myCount = myCount;
		this.cash = cash;
	}
	//������
	@Override
	public void run() {
	      myCount.saveCash(cash, name);
	}
}

//ȡ���߳���
static class drawThread extends Thread{
	private String name;  //������
	private myCount myCount;  //�˻�
	private int cash;  //ȡ����
	public drawThread(String name,myCount myCount, int cash) {
		this.name = name;
		this.myCount = myCount;
		this.cash = cash;
	}
	//ȡ�����
	@Override
	public void run() {
	      myCount.drawCash(cash, name);
	}
}

//��ͨ�����˻��࣬����͸֧
static class myCount{
	private String oId;   //�˺�
	private int curCash;  //���
	public myCount(String oId, int curCash) {
		this.oId = oId;
		this.curCash = curCash;
	}
	
	//���
	public synchronized void saveCash(int operateCash,String name){
		if(operateCash>0){
			curCash+=operateCash; //������
			System.out.println(name+"���"+operateCash+"����ǰ���Ϊ"+curCash);
		}
		notifyAll();  //�������еȴ��߳�
	}
	//ȡ��
	public synchronized void drawCash(int operateCash,String name){
			while(curCash-operateCash<0){
				System.out.println("���㣬����ȡ��...");
				try {
					wait();  //����ȡ�����
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			curCash-=operateCash; //ȡ�����
			System.out.println(name+"ȡ��"+operateCash+"����ǰ���Ϊ"+curCash);
			notifyAll();  //�������д���߳�
	}
}

}
