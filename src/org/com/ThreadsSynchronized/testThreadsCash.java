package org.com.ThreadsSynchronized;

public class testThreadsCash {
        public static void main(String[] args) {
			user user=new user("����", 100);
			Thread t1=new myThread("�߳�A", user, 20);
			Thread t2=new myThread("�߳�B", user, -20);
			Thread t3=new myThread("�߳�C", user, -60);
			Thread t4=new myThread("�߳�D", user, 40);
			Thread t5=new myThread("�߳�E", user, -30);
			Thread t6=new myThread("�߳�F", user, 50);
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();
		}
}
class myThread extends Thread{
	private user user;
	private int y=0;
	public myThread(String ThreadName,user user, int y) {
		super(ThreadName);
		this.user = user;
		this.y = y;
	}
	@Override
	public void run() {
		user.oper(y);
	}
	
	
}
class user{
	   private String name;
	   private int cash;
	public user(String name, int cash) {
		this.name = name;
		this.cash = cash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	
	//��ȡ�����
	public synchronized void oper(int operatCash){
		try {
			Thread.sleep(10L);
			this.cash+=operatCash;
			System.out.println(Thread.currentThread().getName()+"���н���������"+operatCash+",��ǰ�˻����Ϊ��"+cash);
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		return "user [name=" + name + ", cash=" + cash + "]";
	}   
}
