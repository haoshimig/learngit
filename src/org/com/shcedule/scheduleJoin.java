package org.com.shcedule;

public class scheduleJoin {
	
	public static void main(String[] args) { 
        Thread t1 = new MyThread1(); 
        t1.start(); 

        for (int i = 0; i < 20; i++) { 
                System.out.println("���̵߳�" + i + "��ִ�У�"); 
                if (i > 2){ 
	                try { 
	                     //t1�̺߳ϲ������߳��У����߳�ִֹͣ�й��̣�ת��ִ��t1�̣߳�ֱ��t1ִ����Ϻ������ 
	                     t1.join(); 
	                } catch (InterruptedException e) { 
	                        e.printStackTrace(); 
	                } 
                }
        } 
	}
}

class MyThread1 extends Thread { 
	public void run() { 
	        for (int i = 0; i < 10; i++) { 
	                System.out.println("�߳�1��" + i + "��ִ�У�"); 
	        } 
	} 
}

