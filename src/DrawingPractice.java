import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawingPracticePanel extends JPanel implements KeyListener{
	
	int tx = 0;
	int ty = 30;
	
	DrawingPracticePanel() {
//		add(new JButton("button"));
		
		addKeyListener(this);
		
		// Keyboard -> Focus Component
		setFocusable(true); // Panel은 default false
		requestFocus();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		g.setColor(Color.red);
		g.drawLine(100, 100, 300, 300);

		g.setColor(Color.blue);
		for (int i = 0; i < 10; i++)
			g.drawRect(100 + 10 * i, 100 + 10 * i, 200, 100);

		g.setColor(Color.green);
		g.fillOval(100, 100, 200, 100);
		g.fillRoundRect(50, 50, 200, 100, 20, 10);

//		g.setColor(Color.red);
//		g.fillOval(100, 100, 300, 300);

		int x[] = { 0, 400, 400, 500, 300 };
		int y[] = { 0, 0, 100, 200,150 };
//		g.fillPolygon(x, y, 5);

		int r1 = (int)(Math.random()*255);
		int g1 = (int)(Math.random()*255);
		int b1 = (int)(Math.random()*255);
		Color c = new Color(r1, g1, b1);
		g.setColor(c);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 30);
		g.setFont(font);
		g.drawString("Sejong University", tx, ty);
	}

	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed! = " + e.getKeyCode());
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT: tx -= 10; break;
		case KeyEvent.VK_RIGHT: tx += 10; break;
		case KeyEvent.VK_UP: ty -= 10; break;
		case KeyEvent.VK_DOWN: ty += 10; break;
		default: break;
		}
//		paintComponent(g);
		repaint();  // 바로 다시 그리는 건 아님, 그림을 그려야 한다고 알고 있할 일 업을 때 그림
	}
	public void keyReleased(KeyEvent e) {
	}
}

public class DrawingPractice extends JFrame {
	DrawingPractice() {
		setSize(500, 500);
		setTitle("Drawing Practice");

		add(new DrawingPracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DrawingPractice();
	}

}
