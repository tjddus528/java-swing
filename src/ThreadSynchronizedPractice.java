
class BankClient extends Thread {
	BankAccount ac = null;

	BankClient(String name, BankAccount in) {
		super(name);
		ac = in;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			ac.deposit(10, this); // 10원씩 100번 넣음, 클라이언트가 누군지 알려줌(내가 냈어)
		}
	}
}

class BankAccount {
	int money = 0;

	// Vector / ArrayList != LinkedList
	// Vector : 예약되어 있는 모든 함수에 synchronized 걸려 있음(동시 작업X) -> 모든 함수에 sync걸려 있으면 혼자서
	// 하는 것과 동일 -> 느림(multi thread에 안전)
	// ArrayList : sync 안 걸려 있음 -> 빠름(multi thread 에 안전X)

	// 하나의 resource를 놓고 여러 작업자를 들기에 되면 문제점 발생 -> 1명만 들어올 수 있게 private하게 만들어야 함
	// critical section(임계 영역) : 1번에 하나씩만 작업하게끔 만들어야 하는 영역
	// == Mutex(Mutual Exclusive) C언어 용어
	void deposit(int amount, BankClient c) {

		synchronized (this) {
			int cur = money;
			cur = cur + amount;

//		Thread.yield();  // 지금 돌고 있는 thread한테 중요도를 낮춤(다른 thread에게 양보), 내 작업을 잠깐 멈추고 작업을 넘김
			int sum = 0;
			for (int i = 0; i < 100000; i++) {
				sum += i;
			}

			money = cur;
		}
		System.out.println("Cur Money = " + money + " by " + c.getName());
	}
}

public class ThreadSynchronizedPractice {

	public static void main(String[] args) {
		BankAccount ba = new BankAccount();
		BankClient c1 = new BankClient("c1", ba);
		BankClient c2 = new BankClient("c2", ba);
		BankClient c3 = new BankClient("c3", ba);

		c1.start();
		c2.start();
		c3.start();
	}

}
