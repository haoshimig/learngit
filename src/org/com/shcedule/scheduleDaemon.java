package org.com.shcedule;

public class scheduleDaemon {
        
static class myCommon extends Thread{
	public void run() {
		for(int i=0;i<5;i++){
			System.out.println("�߳�1��"+i+"��ִ�У�");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
         	
}

static class myDaemon implements Runnable{
	public void run() {
		for(int i=0;i<100000000;i++){
			System.out.println("��̨�̵߳�"+i+"��ִ�У�");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public static void main(String[] args) {
	Thread t1=new myCommon();
	Thread t2=new Thread(new myDaemon());
	t2.setDaemon(true); //����Ϊ�ػ��߳�
	t2.start();
	t1.start();
}

}
