package org.com.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testThreadLock {
      public static void main(String[] args) {
		 //���������˻�
    	  myCount myCount=new myCount("95599200901215522", 100000);
    	  //����һ��������
    	  Lock myLock=new ReentrantLock();
    	  //����һ���ɱ��С���̳߳�
    	  ExecutorService pool=Executors.newCachedThreadPool();
    	  //����һ�������˻���һ�����ÿ�����Ĵ棬ȡ��ȡ
    	  user u1=new user("����", myCount, -60000, myLock);
    	  user u2=new user("���������", myCount, -30000, myLock);
    	  user u3=new user("�������ϱ�", myCount, 80000, myLock);
    	  user u4=new user("��������", myCount, -70000, myLock);
    	  user u5=new user("��������", myCount, -30000, myLock);
    	  user u6=new user("��������", myCount, 110000, myLock);
    	  //���̳߳���ִ�и����û�����
    	  pool.execute(u1);
    	  pool.execute(u2);
    	  pool.execute(u3);
    	  pool.execute(u4);
    	  pool.execute(u5);
    	  pool.execute(u6);
    	  //�ر��̳߳�
    	  pool.shutdown();
	  }
      
static class user implements Runnable{
	private String name; //�û���
	private myCount myCount; //��Ҫ�������˻�
	private int operateCash; //�����Ľ�������֮��
	private Lock myLock;  	//ִ�в��������������
	
	
	public user(String name, myCount myCount, int operateCash, Lock myLock) {
		this.name = name;
		this.myCount = myCount;
		this.operateCash = operateCash;
		this.myLock = myLock;
	}


	public void run() {
		//��ȡ��
		myLock.lock();
		//ִ���ֽ�ҵ��
		System.out.println(name+"���ڲ���"+myCount+"�˻������Ϊ"+operateCash+"����ǰ���Ϊ"+myCount.getCash());
		myCount.setCash(myCount.getCash()+operateCash);
		System.out.println(name+"����"+myCount+"�˻��ɹ������Ϊ"+operateCash+"����ǰ���Ϊ"+myCount.getCash());
		//�ͷ���,�������߳�û�л���ִ����
		myLock.unlock();
	}
	
}

static class myCount{
    private String oId; //�˺�
    private int cash;   //�˻����
	public myCount(String oId, int cash) {
		this.oId = oId;
		this.cash = cash;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	@Override
	public String toString() {
		return "myCount [oId=" + oId + ", cash=" + cash + "]";
	}
    
}

}
