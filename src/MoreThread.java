import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyFlickeringLabel extends JLabel {
	boolean bb = true;

	MyFlickeringLabel(String str, int interval, Color c) {
		super(str);
		Thread t = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(interval);
					bb = !bb;
					if (bb)
						setForeground(c);
					else
						setForeground(Color.gray);
				}
			} catch (InterruptedException E) {
				return;
			}
		});
		t.start();
	}
}

class MoreThreadPanel extends JPanel implements Runnable {
	JLabel label;
	int time = 0;
	boolean bRun = false;
	Thread t;

	MoreThreadPanel() {
		label = new JLabel("Time: " + time);
		add(label);

		JButton but = new JButton("start");
		but.addActionListener((e) -> {
			bRun = !bRun;
			if (bRun == true) {
				t = new Thread(this);
				t.start();
				but.setText("stop");
			} else {
				t.interrupt();
				but.setText("start");
			}
		});
		add(but);

		MyFlickeringLabel a = new MyFlickeringLabel("Merry", 200, Color.red);
		MyFlickeringLabel b = new MyFlickeringLabel("Christmas", 300, Color.green);
		add(a);
		add(b);
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(100);
				time++;
				label.setText("Time: " + time / 10.0f);
			}
		} catch (InterruptedException e) {
			return;
		}
	}
}

class MyCalcThread extends Thread {
	int st;
	int ed;
	long sum = 0;

	MyCalcThread(String name, int s, int e) {
		super(name);

		st = s;
		ed = e;
	}

	@Override
	public void run() {
		for (int i = st; i < ed; i++)
			sum += i;
		System.out.println("T:" + getName() + " sum = " + sum);
	}
}

public class MoreThread extends JFrame {
	MoreThread() {
		setTitle("More Thread");
		setSize(300, 300);

		add(new MoreThreadPanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MoreThread();
		
		MyCalcThread t1 = new MyCalcThread("t1", 0,2500);
		MyCalcThread t2 = new MyCalcThread("t2", 2500,5000);
		MyCalcThread t3 = new MyCalcThread("t3", 5000,7500);
		MyCalcThread t4 = new MyCalcThread("t4", 7500,10001);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
//			t1.join(100);	// 0.1초간 기다림
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			return;
		}
		
		long sum = t1.sum + t2.sum + t3.sum + t4.sum;
		System.out.println("Total sum = "+sum);
	}

}
