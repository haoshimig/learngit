package org.com.blockingQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class testBlockingDeque {
      public static void main(String[] args) throws InterruptedException {
		   BlockingDeque<Integer> blockingDeque=new LinkedBlockingDeque<Integer>(20);
		   for(int i=0;i<30;i++){
			   //��ָ��Ԫ����ӵ�������ջ�����û�п��ÿռ䣬��һֱ�ȴ�
			   blockingDeque.putFirst(i);
			   System.out.println("�����������������Ԫ�أ�"+i);
		   }
		   System.out.println("���򵽴����н����������˳�----");
	  }
}