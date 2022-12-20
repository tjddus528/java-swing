import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MovingBoxPracticePanel extends JPanel implements KeyListener, Runnable {

	int x = 100;
	int y = 100;
	int vx = 0; // 속도
	int vy = 0; // 속도

	MovingBoxPracticePanel() {
		addKeyListener(this);
		setFocusable(true);
		requestFocus();

		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(x, y, 50, 50);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		// 1/60 sec 마다 그림을 바꿈
//		vx = 0;
//		vy = 0;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			vy = -3;
			break;
		case KeyEvent.VK_DOWN:
			vy = 3;
			break;
		case KeyEvent.VK_LEFT:
			vx = -3;
			break;
		case KeyEvent.VK_RIGHT:
			vx = 3;
			break;
		default:
			break;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void run() {
		try {
			while (true) {								// game loop
				
				Thread.sleep(16);						// 1/60 sec
				
				x += vx;								// update states
				y += vy;								
				
				if(x > getWidth() -50 || x < 0)			// collision resolving
					vx = -vx;
				if(y > getHeight() -50 || y < 0)
					vy = -vy;
				
				repaint();								// redraw
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

public class MovingBoxPractice extends JFrame {
	MovingBoxPractice() {
		setTitle("Moving Box");
		setSize(500, 500);

		add(new MovingBoxPracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MovingBoxPractice();
	}

}
