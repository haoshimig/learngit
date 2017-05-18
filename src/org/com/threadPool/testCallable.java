package org.com.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testCallable {
     public static void main(String[] args) {
		   //创建一个固定大小的线程池
           ExecutorService pool=Executors.newFixedThreadPool(3);
           //创建2个又返回值的任务
           Callable c1=new myCallable("A");
           Callable c2=new myCallable("B");
           Callable c3=new myCallable("C");
           //执行任务并获取Future对象
           Future f1=pool.submit(c1);
           Future f2=pool.submit(c2);
           Future f3=pool.submit(c3);
           //从Future对象上获取任务的返回值Object
           try {
			   System.out.println(">>>>"+f1.get().toString());
			   System.out.println("<<<<"+f2.get().toString());
			   System.out.println("<<<<"+f3.get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           //关闭线程池
           pool.shutdown();
	 }
     
static class myCallable implements Callable{
	private String oId;
	public myCallable(String oId){
		this.oId=oId;
	}
	@Override
	public Object call() throws Exception {
		return oId+"任务返回的内容";
	}
	
}
     
}
