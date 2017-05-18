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
    public static final int maxSize=100; //最大库存量
    public int curNum;  //当前库存量
	public Godown(int curNum) {
		this.curNum = curNum;
	}
	//生产指定数量的产品
	public synchronized void produce(int needNum){
		//测试是否需要生产
		while((needNum+curNum) > maxSize){
			System.out.println("要生产的产品数量"+needNum+",超过剩余库存量"+(maxSize-curNum)+",暂时不能执行生产任务！");
			try {
				//当前生产线程等待
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//满足生产条件，则进行生产，简单更改库存量
		curNum+=needNum;
		System.out.println("已经生产了"+needNum+"个产品，现仓储量为"+curNum);
		//唤醒此对象监视器上等待的所有线程
		notifyAll();
	}
	
	//消费指定数量的产品
    public synchronized void consume(int needNum){
    	//测试是否可消费
    			while(curNum<needNum){
    				System.out.println("要消费的产品数量"+needNum+",超过剩余库存量"+curNum+",暂时不能执行消费任务！");
    				try {
    					//当前消费线程等待
    					wait();
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    			//满足消费条件，则进行消费，简单更改库存量
    			curNum-=needNum;
    			System.out.println("已经消费了"+needNum+"个产品，现仓储量为"+curNum);
    			//唤醒此对象监视器上等待的所有线程
    			notifyAll();
    }
}
static class producer extends Thread{
	private int needNum; //生产产品数量
	private Godown godown; //仓库
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
	private int needNum; //生产产品数量
	private Godown godown; //仓库
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
