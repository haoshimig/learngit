package org.com.blockingQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class testBlockingDeque {
      public static void main(String[] args) throws InterruptedException {
		   BlockingDeque<Integer> blockingDeque=new LinkedBlockingDeque<Integer>(20);
		   for(int i=0;i<30;i++){
			   //将指定元素添加到此阻塞栈，如果没有可用空间，将一直等待
			   blockingDeque.putFirst(i);
			   System.out.println("向阻塞队列中添加了元素："+i);
		   }
		   System.out.println("程序到此运行结束，即将退出----");
	  }
}