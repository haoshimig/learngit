package org.com.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/*
 * CountDownLatchu是所有子程序执行完成以后，再执行主程序；关注的是主程序。
 * CyclicBarrier是所有子程序都执行到某一点后，（或者执行完主程序后继续执行），或者再继续执行；关注的是子程序。
 */
public class testCyclicBarrier {
      
	public static void main(String[] args) {
		//创建障碍器，并设置MainTask---为所有指定数量的线程都达到障碍点的时候，所要执行的 主任务（Runnable）
		CyclicBarrier cb=new CyclicBarrier(6, new mainTask());
		new subTask("A", cb).start();
		new subTask("B", cb).start();
		new subTask("C", cb).start();
		new subTask("D", cb).start();
		new subTask("E", cb).start();
		new subTask("F", cb).start();
		//new subTask("G", cb).start();
		//new subTask("H", cb).start();
	}
	
//主任务
static class mainTask implements Runnable{
	@Override
	public void run() {
		System.out.println(">>>>>>>>主任务开始执行了<<<<<<<");
	}
}

//子任务
static class subTask extends Thread{
	private String name;  //子任务名称
	private CyclicBarrier cb;  //障碍点
	
	public subTask(String name, CyclicBarrier cb) {
		this.name = name;
		this.cb = cb;
	}

	@Override
	public void run() {
	      System.out.println("【子任务"+name+"】开始执行了！");
	      //模拟一个耗时操作
	      for(int i=0;i<999999;i++);
	      System.out.println("【子任务"+name+"】开始执行了，并通知障碍器已经完成！");
	      
	     try {
	    	//通知障碍器已经完成
			cb.await();
			//System.out.println(">>>>>>>>主任务开始执行了<<<<<<<");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}
