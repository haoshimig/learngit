package org.com.model;

public class producerAndConsumer {
        public static void main(String[] args) {
			Godown godown=new Godown(50);
			consumer c1=new consumer(30, godown);
			consumer c2=new consumer(60, godown);
			consumer c3=new consumer(50, godown);
			producer p1=new producer(60, godown);
			producer p2=new producer(20, godown);
			producer p3=new producer(40, godown);
			producer p4=new producer(80, godown);
			consumer c4=new consumer(20, godown);
			c1.start();
			c2.start();
			c3.start();
			p1.start();
			p2.start();
			p3.start();
			p4.start();
			c4.start();
		}
static class Godown{
    public static final int maxSize=100; //�������
    public int curNum;  //��ǰ�����
	public Godown(int curNum) {
		this.curNum = curNum;
	}
	//����ָ�������Ĳ�Ʒ
	public synchronized void produce(int needNum){
		//�����Ƿ���Ҫ����
		while((needNum+curNum) > maxSize){
			System.out.println("Ҫ�����Ĳ�Ʒ����"+needNum+",����ʣ������"+(maxSize-curNum)+",��ʱ����ִ����������");
			try {
				//��ǰ�����̵߳ȴ�
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��������������������������򵥸��Ŀ����
		curNum+=needNum;
		System.out.println("�Ѿ�������"+needNum+"����Ʒ���ֲִ���Ϊ"+curNum);
		//���Ѵ˶���������ϵȴ��������߳�
		notifyAll();
	}
	
	//����ָ�������Ĳ�Ʒ
    public synchronized void consume(int needNum){
    	//�����Ƿ������
    			while(curNum<needNum){
    				System.out.println("Ҫ���ѵĲ�Ʒ����"+needNum+",����ʣ������"+curNum+",��ʱ����ִ����������");
    				try {
    					//��ǰ�����̵߳ȴ�
    					wait();
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			//����������������������ѣ��򵥸��Ŀ����
    			curNum-=needNum;
    			System.out.println("�Ѿ�������"+needNum+"����Ʒ���ֲִ���Ϊ"+curNum);
    			//���Ѵ˶���������ϵȴ��������߳�
    			notifyAll();
    }
}
static class producer extends Thread{
	private int needNum; //������Ʒ����
	private Godown godown; //�ֿ�
	public producer(int needNum, Godown godown) {
		this.needNum = needNum;
		this.godown = godown;
	}
	@Override
	public void run() {
	     godown.produce(needNum);	
	}
}
static class consumer extends Thread{
	private int needNum; //������Ʒ����
	private Godown godown; //�ֿ�
	public consumer(int needNum, Godown godown) {
		this.needNum = needNum;
		this.godown = godown;
	}
	@Override
	public void run() {
	     godown.consume(needNum);	
	}
}

}
