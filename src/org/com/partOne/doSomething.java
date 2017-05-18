package org.com.partOne;

public class doSomething implements Runnable{

		private String name;
		
		public doSomething(String name) {
			this.name = name;
		}

		public void run() {
			for(int i=0;i<5;i++){
				for(long k=0;k<100;k++){
					System.out.println(name+":"+i);
				}
			}
		}

}
