package org.com.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 原子量虽然可以保证单个变量在某一操作过程的安全，但无法保证整个代码块（或者说整个程序）的安全性
 * 所以，应该用锁、同步方法（块）等同步机制来控制整个程序的安全性
 */
public class testThreadAtomicTrue {
     public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		Lock lock=new ReentrantLock(false);
		Runnable r1=new myRunnable("张三", 50000,lock);
		Runnable r2=new myRunnable("李四", 10000,lock);
		Runnable r3=new myRunnable("王五", 30000,lock);
		Runnable r4=new myRunnable("赵六", 20000,lock);
		Runnable r5=new myRunnable("老七", 70000,lock);
		//加入线程池，执行各个线程
		pool.execute(r1);
		pool.execute(r2);
		pool.execute(r3);
		pool.execute(r4);
		pool.execute(r5);
		//关闭线程池
		pool.shutdown();
	}
     
static class myRunnable implements Runnable{
	private static AtomicLong aLong=new AtomicLong(100000);   //原子量，每个线程都可以自由操作
	private String name;  //操作人
	private int cash;  //操作金额
	private Lock lock; //操作对象锁
	
	public myRunnable(String name, int cash, Lock lock) {
		this.name = name;
		this.cash = cash;
		this.lock=lock;
	}

	@Override
	public void run() {
		lock.lock();   //获取锁
		System.out.println(name+"执行了"+cash+"，当前余额："+aLong.addAndGet(cash));
		lock.unlock();  //释放锁
	}
}
}
