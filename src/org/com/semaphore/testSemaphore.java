package org.com.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class testSemaphore {
     public static void main(String[] args) {
		myPool myPool=new myPool(20);
		//创建线程池
		ExecutorService threadPool=Executors.newFixedThreadPool(2);
		myThread t1=new myThread("任务A", myPool, 5);
		myThread t2=new myThread("任务B", myPool, 8);
		myThread t3=new myThread("任务C", myPool, 12);
		//在线程池中执行任务
		threadPool.execute(t1);
		threadPool.execute(t2);
		threadPool.execute(t3);
		//关闭线程池
		threadPool.shutdown();
	}

static class myPool{
	private Semaphore sp;  //池相关的信号量

	//池的大小会传递给信号量
	public myPool(int size) {
		this.sp = new Semaphore(size);
	}

	public Semaphore getSp() {
		return sp;
	}

	public void setSp(Semaphore sp) {
		this.sp = sp;
	}
}

static class myThread extends Thread{
	private String ThreadName;  //线程名称
	private myPool pool;   //自定义池
	private int x;  //申请信号量的大小
	public myThread(String threadName, myPool pool, int x) {
		ThreadName = threadName;
		this.pool = pool;
		this.x = x;
	}
	@Override
	public void run() {
		try {
			//从此信号量获取给定数目的许可
			pool.getSp().acquire(x);
			System.out.println(ThreadName+"成功获取了"+x+"个许可！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放给定数目的许可，将其返回到信号量
			pool.getSp().release(x);
			System.out.println(ThreadName+"释放了"+x+"个许可！");
		}
	}
}
     
}
