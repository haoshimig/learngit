package org.com.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class testBlockingQueue {
      public static void main(String[] args) throws InterruptedException {
		   BlockingQueue<Integer> bQueue=new ArrayBlockingQueue<Integer>(20);
		   for(int i=0;i<30;i++){
			   //��ָ��Ԫ����ӵ��˶��У����û�п��ÿռ䣬��һֱ�ȴ�
			   bQueue.put(i);
			   System.out.println("�����������������Ԫ�أ�"+i);
		   }
		   System.out.println("���򵽴����н����������˳�----");
	  }
}

//���⣬�������л��и���ʵ���ࣺArrayBlockingQueue,DelayQueue,LinkedBlockingQueue,PriorityBlockingQueue,SynchronousQueue