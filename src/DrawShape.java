import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class Shape2D {
	int x1;
	int y1;
	int x2;
	int y2;
	Color color;

	Shape2D() {
		int r1 = (int) (Math.random() * 255);
		int g1 = (int) (Math.random() * 255);
		int b1 = (int) (Math.random() * 255);
		color = new Color(r1, g1, b1);

	}

	Shape2D(int x, int y) {
		this();
		setPosition1(x,y);
	}

	public void setPosition1(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public void setPosition2(int x, int y) {
		x2 = x;
		y2 = y;
	}

	public void draw(Graphics g) {
		g.setColor(color);
	}
}

class Line2D extends Shape2D{
	Line2D(int x, int y) {
		super(x,y);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x1,y1,x2,y2);
	}
}

class Rect2D extends Shape2D{
	Rect2D(int x, int y) {
		super(x,y);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1,y1,x2-x1,y2-y1);
	}
}

class Circle2D extends Shape2D{
	Circle2D(int x, int y) {
		super(x,y);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		int cx = x1;
		int cy = y1;
		int r = (int)Math.sqrt((double)((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
		g.fillOval(cx-r,cy-r,2*r,2*r);
	}
}

class DrawingLinePracticePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

	LinkedList<Shape2D> shapes;

	DrawingLinePracticePanel() {
		shapes = new LinkedList<Shape2D>();

		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		// Keyboard -> Focus Component
		setFocusable(true); // Panel은 default false
		requestFocus();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

//		g.drawLine(x1, y1, x2, y2);
		for (var s : shapes) {
			s.draw(g);
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			shapes.add(new Line2D(e.getX(), e.getY()));
		else if(e.getButton() == MouseEvent.BUTTON3)
			shapes.add(new Rect2D(e.getX(), e.getY()));
		else if(e.getButton() == MouseEvent.BUTTON2)
			shapes.add(new Circle2D(e.getX(), e.getY()));
			
	}

	public void mouseReleased(MouseEvent e) {
		shapes.get(shapes.size() - 1).setPosition2(e.getX(), e.getY());
		repaint();
	}
	public void mouseDragged(MouseEvent e) {
		shapes.get(shapes.size() - 1).setPosition2(e.getX(), e.getY());
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class DrawShape extends JFrame {
	DrawShape() {
		setSize(500, 500);
		setTitle("Drawing Practice");

		add(new DrawingLinePracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DrawShape();

	}

}
