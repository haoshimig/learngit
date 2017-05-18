package org.com.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testThreadLock {
      public static void main(String[] args) {
		 //创建并发账户
    	  myCount myCount=new myCount("95599200901215522", 100000);
    	  //创建一个锁对象
    	  Lock myLock=new ReentrantLock();
    	  //创建一个可变大小的线程池
    	  ExecutorService pool=Executors.newCachedThreadPool();
    	  //创建一个并发账户，一个信用卡，存的存，取的取
    	  user u1=new user("张三", myCount, -60000, myLock);
    	  user u2=new user("张三他表弟", myCount, -30000, myLock);
    	  user u3=new user("张三他老表", myCount, 80000, myLock);
    	  user u4=new user("张三他爹", myCount, -70000, myLock);
    	  user u5=new user("张三他娘", myCount, -30000, myLock);
    	  user u6=new user("张三他妹", myCount, 110000, myLock);
    	  //在线程池中执行各个用户操作
    	  pool.execute(u1);
    	  pool.execute(u2);
    	  pool.execute(u3);
    	  pool.execute(u4);
    	  pool.execute(u5);
    	  pool.execute(u6);
    	  //关闭线程池
    	  pool.shutdown();
	  }
      
static class user implements Runnable{
	private String name; //用户名
	private myCount myCount; //所要操作的账户
	private int operateCash; //操作的金额，有正负之分
	private Lock myLock;  	//执行操作所需的锁对象
	
	
	public user(String name, myCount myCount, int operateCash, Lock myLock) {
		this.name = name;
		this.myCount = myCount;
		this.operateCash = operateCash;
		this.myLock = myLock;
	}


	public void run() {
		//获取锁
		myLock.lock();
		//执行现金业务
		System.out.println(name+"正在操作"+myCount+"账户，金额为"+operateCash+"，当前金额为"+myCount.getCash());
		myCount.setCash(myCount.getCash()+operateCash);
		System.out.println(name+"操作"+myCount+"账户成功，金额为"+operateCash+"，当前金额为"+myCount.getCash());
		//释放锁,否则别的线程没有机会执行了
		myLock.unlock();
	}
	
}

static class myCount{
    private String oId; //账号
    private int cash;   //账户余额
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
