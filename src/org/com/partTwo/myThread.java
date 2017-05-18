package org.com.partTwo;

public class myThread extends Thread{
	public void run() {
		for(int i=0;i<100;i++){
			if(i%10==0){
				System.out.println("------"+i);
			}
			System.out.print(i);
			try {
				Thread.sleep(1);
				System.out.println(" Ïß³ÌË¯Ãß1ºÁÃë£¡\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new myThread().start();
	}
 
}
