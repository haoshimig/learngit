package org.com.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class testBlockingQueue {
      public static void main(String[] args) throws InterruptedException {
		   BlockingQueue<Integer> bQueue=new ArrayBlockingQueue<Integer>(20);
		   for(int i=0;i<30;i++){
			   //将指定元素添加到此队列，如果没有可用空间，将一直等待
			   bQueue.put(i);
			   System.out.println("向阻塞队列中添加了元素："+i);
		   }
		   System.out.println("程序到此运行结束，即将退出----");
	  }
}

//另外，阻塞队列还有更多实现类：ArrayBlockingQueue,DelayQueue,LinkedBlockingQueue,PriorityBlockingQueue,SynchronousQueue