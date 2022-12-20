
class MyThread extends Thread {
	int st;
	int ed;
	int sum = 0;
	MyThread(String str, int s, int e) {
		super(str);
		st = s;
		ed = e;
	}

	@Override
	public void run() {
		for (int i = st; i < ed; i++) {
			sum += i;
		}
		System.out.println("End : " + getName() +" " +st+"~"+(ed-1) +" sum ="+ sum);
	}
}

class MyRunnable implements Runnable{
	String name;
	MyRunnable(String str) {
		name = str;
	}
	@Override
	public void run() {
		int sum = 0;
		for (int i = 1; i <= 1000; i++) {
			sum += i;
		}
		System.out.println("End : " + name + " sum = " + sum);
	}
}

public class HelloThread implements Runnable {

	public static void main(String[] args) {
		System.out.println("Start : " + Thread.activeCount());
		MyThread t1 = new MyThread("t1",1,1000);
		MyThread t2 = new MyThread("t2",1000,2000);
		MyThread t3 = new MyThread("t3",2000,3000);
		MyThread t4 = new MyThread("t4",3000,4000);
		
//		// 정의
//		MyThread t1 = new MyThread("t1");
//		Thread t2 = new Thread(new MyRunnable("t2")); 
//		Thread t3 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				int sum = 0;
//				for (int i = 1; i <= 1000; i++) {
//					sum += i;
//				}
//				System.out.println("End : t3 sum = " + sum);
//			}
//		});
//		Thread t4 = new Thread(() -> {
//			int sum = 0;
//			for (int i = 1; i <= 1000; i++) {
//				sum += i;
//			}
//			System.out.println("End : t4 sum = " + sum);
//		});
//		Thread t5 = new Thread(new HelloThread());
//		
		// 동작
		t1.start();
		t2.start();
		t3.start();
		t4.start();
//		t5.start();
		
		int sum = t1.sum + t2.sum + t3.sum + t4.sum;
		System.out.println("total sum = " +sum);
		
		System.out.println("Finish : " + Thread.activeCount());

	}

	@Override
	public void run() {
		int sum = 0;
		for (int i = 1; i <= 1000; i++) {
			sum += i;
		}
		System.out.println("End : t5 sum = " + sum);
		
	}

}
