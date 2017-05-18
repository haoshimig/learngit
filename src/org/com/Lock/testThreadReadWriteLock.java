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
		 //创建并发账户
    	  myCount myCount=new myCount("95599200901215522", 100000);
    	  //创建一个锁对象
    	  ReadWriteLock myLock=new ReentrantReadWriteLock(false);
    	  //创建一个固定大小的线程池
    	  ExecutorService pool=Executors.newFixedThreadPool(2);
    	  //创建一个并发账户，一个信用卡，存的存，取的取
    	  user u1=new user("张三", myCount, -60000, myLock, false);
    	  user u2=new user("张三他表弟", myCount, -30000, myLock, false);
    	  user u3=new user("张三他老表", myCount, 80000, myLock, false);
    	  user u4=new user("张三他爹", myCount, -70000, myLock, false);
    	  user u5=new user("张三他娘", myCount, -30000, myLock, false);
    	  user u6=new user("张三他妹", myCount, 110000, myLock, false);
    	  user u7=new user("张三他二大爷", myCount, 0, myLock, true);
    	  //在线程池中执行各个用户操作
    	  pool.execute(u1);
    	  pool.execute(u2);
    	  pool.execute(u3);
    	  pool.execute(u4);
    	  pool.execute(u5);
    	  pool.execute(u6);
    	  pool.execute(u7);
    	  //关闭线程池
    	  pool.shutdown();
	  }
      
static class user implements Runnable{
	private String name; //用户名
	private myCount myCount; //所要操作的账户
	private int operateCash; //操作的金额，有正负之分
	private ReadWriteLock myLock;  	//执行操作所需的所对象
	private boolean isCheck;      //是否查询
	
	
	public user(String name, myCount myCount, int operateCash, ReadWriteLock myLock, boolean isCheck) {
		this.name = name;
		this.myCount = myCount;
		this.operateCash = operateCash;
		this.myLock = myLock;
		this.isCheck = isCheck;
	}


	public void run() {
		if(isCheck){
			//获取读锁
			myLock.readLock().lock();
			//执行现金业务
			System.out.println("读："+name+"正在查询"+myCount+"账户，当前余额为"+myCount.getCash());
			//释放读锁,否则别的线程没有机会执行了
			myLock.readLock().unlock();
		}else{
			//获取写锁
			myLock.writeLock().lock();
			//执行现金业务
			System.out.println("写："+name+"正在操作"+myCount+"账户，金额为"+operateCash+"，当前金额为"+myCount.getCash());
			myCount.setCash(myCount.getCash()+operateCash);
			System.out.println("写："+name+"操作"+myCount+"账户成功，金额为"+operateCash+"，当前金额为"+myCount.getCash());
			//释放写锁,否则别的线程没有机会执行了
			myLock.writeLock().unlock();	
		}
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
