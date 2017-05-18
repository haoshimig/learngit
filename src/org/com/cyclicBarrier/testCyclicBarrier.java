package org.com.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/*
 * CountDownLatchu�������ӳ���ִ������Ժ���ִ�������򣻹�ע����������
 * CyclicBarrier�������ӳ���ִ�е�ĳһ��󣬣�����ִ��������������ִ�У��������ټ���ִ�У���ע�����ӳ���
 */
public class testCyclicBarrier {
      
	public static void main(String[] args) {
		//�����ϰ�����������MainTask---Ϊ����ָ���������̶߳��ﵽ�ϰ����ʱ����Ҫִ�е� ������Runnable��
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
	
//������
static class mainTask implements Runnable{
	@Override
	public void run() {
		System.out.println(">>>>>>>>������ʼִ����<<<<<<<");
	}
}

//������
static class subTask extends Thread{
	private String name;  //����������
	private CyclicBarrier cb;  //�ϰ���
	
	public subTask(String name, CyclicBarrier cb) {
		this.name = name;
		this.cb = cb;
	}

	@Override
	public void run() {
	      System.out.println("��������"+name+"����ʼִ���ˣ�");
	      //ģ��һ����ʱ����
	      for(int i=0;i<999999;i++);
	      System.out.println("��������"+name+"����ʼִ���ˣ���֪ͨ�ϰ����Ѿ���ɣ�");
	      
	     try {
	    	//֪ͨ�ϰ����Ѿ����
			cb.await();
			//System.out.println(">>>>>>>>������ʼִ����<<<<<<<");
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
