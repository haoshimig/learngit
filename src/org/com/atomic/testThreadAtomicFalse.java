package org.com.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/*
 * 原子量虽然可以保证单个变量在某一操作过程的安全，但无法保证整个代码块（或者说整个程序）的安全性
 */
public class testThreadAtomicFalse {
     public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		Runnable r1=new myRunnable("张三", 50000);
		Runnable r2=new myRunnable("李四", 10000);
		Runnable r3=new myRunnable("王五", 30000);
		Runnable r4=new myRunnable("赵六", 20000);
		Runnable r5=new myRunnable("老七", 70000);
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
	
	public myRunnable(String name, int cash) {
		this.name = name;
		this.cash = cash;
	}

	@Override
	public void run() {
		System.out.println(name+"执行了"+cash+"，当前余额："+aLong.addAndGet(cash));
	}
}
}
