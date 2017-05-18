package org.com.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class testCallable {
     public static void main(String[] args) {
		   //����һ���̶���С���̳߳�
           ExecutorService pool=Executors.newFixedThreadPool(3);
           //����2���ַ���ֵ������
           Callable c1=new myCallable("A");
           Callable c2=new myCallable("B");
           Callable c3=new myCallable("C");
           //ִ�����񲢻�ȡFuture����
           Future f1=pool.submit(c1);
           Future f2=pool.submit(c2);
           Future f3=pool.submit(c3);
           //��Future�����ϻ�ȡ����ķ���ֵObject
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
           //�ر��̳߳�
           pool.shutdown();
	 }
     
static class myCallable implements Callable{
	private String oId;
	public myCallable(String oId){
		this.oId=oId;
	}
	@Override
	public Object call() throws Exception {
		return oId+"���񷵻ص�����";
	}
	
}
     
}
