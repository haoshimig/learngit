package org.com.lockCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testLockCondition3 {
       public static void main(String[] args) {
    	   //创建并发访问账户
		   myCount myCount=new myCount("95599200901215522", 100000);
		   //创建一个线程池
		   ExecutorService pool=Executors.newFixedThreadPool(2);
		   Thread t1=new drawThread("张三", myCount, 60000);
		   Thread t2=new drawThread("李四", myCount, 60000);
		   Thread t3=new saveThread("王五", myCount, 20000);
		   Thread t4=new saveThread("赵六", myCount, 120000);
		   Thread t5=new drawThread("小七", myCount, 80000);
		   Thread t6=new saveThread("老八", myCount, 110000);
		   //执行各个线程
		   pool.execute(t1);
		   pool.execute(t2);
		   pool.execute(t3);
		   pool.execute(t4);
		   pool.execute(t5);
		   pool.execute(t6);
		   //关闭线程池
		   pool.shutdown();
		   
       }
//存款线程类
static class saveThread extends Thread{
	private String name;  //操作人
	private myCount myCount;  //账户
	private int cash;  //存款金额
	public saveThread(String name,myCount myCount, int cash) {
		this.name = name;
		this.myCount = myCount;
		this.cash = cash;
	}
	//存款操作
	@Override
	public void run() {
	      myCount.saveCash(cash, name);
	}
}

//取款线程类
static class drawThread extends Thread{
	private String name;  //操作人
	private myCount myCount;  //账户
	private int cash;  //取款金额
	public drawThread(String name,myCount myCount, int cash) {
		this.name = name;
		this.myCount = myCount;
		this.cash = cash;
	}
	//取款操作
	@Override
	public void run() {
	      myCount.drawCash(cash, name);
	}
}

//普通银行账户类，不可透支
static class myCount{
	private String oId;   //账号
	private int curCash;  //余额
	public myCount(String oId, int curCash) {
		this.oId = oId;
		this.curCash = curCash;
	}
	
	//存款
	public void saveCash(int operateCash,String name){
		synchronized (this) {
			if(operateCash>0){
				curCash+=operateCash; //存款操作
				System.out.println(name+"存款"+operateCash+"，当前余额为"+curCash);
			}
			notifyAll();  //唤醒所有等待线程
		}
	}
	//取款
	public void drawCash(int operateCash,String name){
		synchronized (this) {
			while(curCash-operateCash<0){
				System.out.println("余额不足，阻塞取款...");
				try {
					wait();  //阻塞取款操作
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			curCash-=operateCash; //取款操作
			System.out.println(name+"取款"+operateCash+"，当前余额为"+curCash);
			notifyAll();  //唤醒所有存款线程
		}
	}
}

}
