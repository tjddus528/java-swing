import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ProgressButton extends JButton {
	int value = 0; // 0 ~ 100
	Thread t = null;

	ProgressButton(String str) {
		super(str);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = (int) (getWidth() / 100.0f * value); // value => percent
		g.setColor(Color.red);
		g.fillRect(0, 0, w, getHeight());
	}
}

class ThreadWaitNotifyPracticePanel extends JPanel implements ActionListener {
	ProgressButton[] bts = new ProgressButton[4];
	JButton wakeBut;
	ThreadWaitNotifyPracticePanel() {
		this.setLayout(null);
		for (int i = 0; i < 4; i++) {
			bts[i] = new ProgressButton("push");
			bts[i].addActionListener(this);
			add(bts[i]);
			bts[i].setBounds(50, 50 + 50 * i, 400, 30);
		}
		wakeBut = new JButton("wake");
		wakeBut.addActionListener(this);
		wakeBut.setBounds(50,350,400,30);
		add(wakeBut);
	}
	synchronized public void addValue(ProgressButton b) {
		if(b.value == 0)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		b.value++;
	}

	private class MyThread extends Thread {
		ProgressButton but;

		MyThread(ProgressButton b) {
			but = b;
		}
		@Override
		public void run() {
			for(int i=0;i<=100;i++) {
				addValue(but);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				but.repaint();
			}
		}
	}

	@Override
	synchronized public void actionPerformed(ActionEvent e) {
		if(e.getSource() == wakeBut) {
			notifyAll();
		}
		ProgressButton b = (ProgressButton) e.getSource();
		if (b.t == null) {
			b.t = new MyThread(b);
			b.t.start();
		}
	}
}

public class ThreadWaitNotifyPractice extends JFrame {
	ThreadWaitNotifyPractice() {
		setTitle("Wait Notify Practice - Progress Bar");
		setSize(500, 500);

		add(new ThreadWaitNotifyPracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ThreadWaitNotifyPractice();

	}

}
