package org.com.partFour;

public class testThreadsWatiResult extends Thread{

	calculator cacu;

	public testThreadsWatiResult(calculator cacu) {
		this.cacu = cacu;
	}

	@Override
	public void run() {
		synchronized (cacu) {
			System.out.println(Thread.currentThread().getName()+"�ȴ���ǰ�̼߳�����...");
			try {
				cacu.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"������Ϊ��"+cacu.total);
		}
	}
	
	public static void main(String[] args) {
		calculator calculator=new calculator();
		//���������߳�
		new testThreadsWatiResult(calculator).start();
		new testThreadsWatiResult(calculator).start();
		new testThreadsWatiResult(calculator).start();
	   //����������
		calculator.start();
	}
	
}
