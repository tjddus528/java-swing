import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyMouseListener implements MouseListener {
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clicked!");
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed!");
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("Released!");
	}

	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered!");
	}

	public void mouseExited(MouseEvent e) {
		System.out.println("Exited!");
	}
}

class MyMouseMotionListener implements MouseMotionListener {

	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	
}

class MyMouseAdapter extends MouseAdapter{
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
}

class MyKetListener implements KeyListener {

	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	
}

class MouseEventPracticePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	JLabel text = null;
	MouseEventPracticePanel() {
		setLayout(null);
		
		setBackground(Color.orange);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		addMouseListener(this); // 스스로 등록해야 함
		addMouseMotionListener(this);
		addKeyListener(this);
		
		text = new JLabel("Mouse Event: ");
		add(text);
		text.setLocation(100,100);	// location position
		text.setSize(200,20); // size
		
		setFocusable(true);  // panel 의 경우 default false
		requestFocus();  // 지금 이 프로그램에서 포커스는 내가 갖는다
		
	}

	public void mouseClicked(MouseEvent e) {
	if(e.getClickCount() == 2) {
		System.out.println("Double Click");
	}
	}

	public void mousePressed(MouseEvent e) {
//		Point p = e.getPoint();
//		System.out.println(p);
//		int x = e.getX();  // p.x
//		int y = e.getY();  // p.y
//		
//		e.getButton();  // left, middle, right
//		switch(e.getButton()) {
//		case(MouseEvent.BUTTON1):
//			System.out.println("Left!"); break;
//		case(MouseEvent.BUTTON2):
//			System.out.println("Middle!"); break;
//		case(MouseEvent.BUTTON3):
//			System.out.println("Right!"); break;
//		default:
//			break;
//			
//		}
		
		int x = e.getX();
		int y = e.getY();
	
		
		Point textP = text.getLocation(); // 자신을 가진 애를 기준으로
		Dimension textD = text.getSize();
		if(x>textP.x && x<textP.x + textD.width &&
				y>textP.y && y<textP.y + textD.height) {
			System.out.println("text Click!");
			Dimension pd = getSize();
			int newX = (int)(Math.random()*pd.width);
			int newY = (int)(Math.random()*pd.height);
			text.setLocation(newX, newY);
		} else {
			JLabel l = new JLabel("^_^");
			l.setLocation(x,y);
			l.setSize(30,20);
			add(l);
			repaint();
			
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		setBackground(Color.LIGHT_GRAY);
	}

	public void mouseExited(MouseEvent e) {
		setBackground(Color.DARK_GRAY);
	}

	public void mouseDragged(MouseEvent e) {
		text.setText("Dragged: "+e.getPoint());
		
	}
	public void mouseMoved(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		text.setText("Moved: "+e.getPoint());
//		JLabel l = new JLabel("^_^");
//		l.setLocation(x,y);
//		l.setSize(30,20);
//		add(l);
//		repaint();
	}
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed! :"+e.getKeyChar());
		text.setText(text.getText() + e.getKeyChar());
	}
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed! : "+e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			Point p = text.getLocation();
			p.x-= 10;
			text.setLocation(p);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Point p = text.getLocation();
			p.x+= 10;
			text.setLocation(p);
		}
	}
	public void keyReleased(KeyEvent e) {
		System.out.println("Key Released! : "+e.getKeyCode());
	}
}

public class MouseEventPractice extends JFrame {

	MouseEventPractice() {
		setTitle("Mouse Event Practice");
		setSize(500, 500);

		add(new MouseEventPracticePanel());

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MouseEventPractice();
	}

}
