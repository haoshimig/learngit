package org.com.partFour;

public class testWaitThread {
      public static void main(String[] args) {
		  testThreadB b=new testThreadB();
		  b.start();
		  //�߳�Aӵ��b�����ϵ������߳�Ϊ�˵���wait()��notify()���������̱߳������Ǹ���������ӵ���� 
		  synchronized (b) {
			  try {
				  System.out.println("�ȴ�����B��ɼ���...");
				  //��ǰ�߳�A�ȴ�
				  b.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    System.out.println("b���������ܺ�Ϊ��"+b.total);
		  }
      }
}
