package org.com.partFour;

public class calculator extends Thread{

	int total;

	public void run() {
          synchronized (this) {
			 for(int i=0;i<=100;i++){
				 total+=i;
			 }
			//֪ͨ�����ڴ˶����ϵȴ����߳�
			//notifyAll();
		   try {
                 Thread.sleep(1000*5);//Ϊ����ʾЧ������
           } catch (InterruptedException e) {
                 e.printStackTrace();
           }
           //��ɼ��� �����ڴ˶���������ϵȴ��ĵ����߳�
           notify();
          }		
	}
	
}
