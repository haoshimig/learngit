package org.com.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class testThreadReadWriteLock {
      public static void main(String[] args) {
		 //���������˻�
    	  myCount myCount=new myCount("95599200901215522", 100000);
    	  //����һ��������
    	  ReadWriteLock myLock=new ReentrantReadWriteLock(false);
    	  //����һ���̶���С���̳߳�
    	  ExecutorService pool=Executors.newFixedThreadPool(2);
    	  //����һ�������˻���һ�����ÿ�����Ĵ棬ȡ��ȡ
    	  user u1=new user("����", myCount, -60000, myLock, false);
    	  user u2=new user("���������", myCount, -30000, myLock, false);
    	  user u3=new user("�������ϱ�", myCount, 80000, myLock, false);
    	  user u4=new user("��������", myCount, -70000, myLock, false);
    	  user u5=new user("��������", myCount, -30000, myLock, false);
    	  user u6=new user("��������", myCount, 110000, myLock, false);
    	  user u7=new user("����������ү", myCount, 0, myLock, true);
    	  //���̳߳���ִ�и����û�����
    	  pool.execute(u1);
    	  pool.execute(u2);
    	  pool.execute(u3);
    	  pool.execute(u4);
    	  pool.execute(u5);
    	  pool.execute(u6);
    	  pool.execute(u7);
    	  //�ر��̳߳�
    	  pool.shutdown();
	  }
      
static class user implements Runnable{
	private String name; //�û���
	private myCount myCount; //��Ҫ�������˻�
	private int operateCash; //�����Ľ�������֮��
	private ReadWriteLock myLock;  	//ִ�в��������������
	private boolean isCheck;      //�Ƿ��ѯ
	
	
	public user(String name, myCount myCount, int operateCash, ReadWriteLock myLock, boolean isCheck) {
		this.name = name;
		this.myCount = myCount;
		this.operateCash = operateCash;
		this.myLock = myLock;
		this.isCheck = isCheck;
	}


	public void run() {
		if(isCheck){
			//��ȡ����
			myLock.readLock().lock();
			//ִ���ֽ�ҵ��
			System.out.println("����"+name+"���ڲ�ѯ"+myCount+"�˻�����ǰ���Ϊ"+myCount.getCash());
			//�ͷŶ���,�������߳�û�л���ִ����
			myLock.readLock().unlock();
		}else{
			//��ȡд��
			myLock.writeLock().lock();
			//ִ���ֽ�ҵ��
			System.out.println("д��"+name+"���ڲ���"+myCount+"�˻������Ϊ"+operateCash+"����ǰ���Ϊ"+myCount.getCash());
			myCount.setCash(myCount.getCash()+operateCash);
			System.out.println("д��"+name+"����"+myCount+"�˻��ɹ������Ϊ"+operateCash+"����ǰ���Ϊ"+myCount.getCash());
			//�ͷ�д��,�������߳�û�л���ִ����
			myLock.writeLock().unlock();	
		}
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
