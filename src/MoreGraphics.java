import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyStroke {
	ArrayList<Point> pts = new ArrayList<>();
	Color color = Color.black;

	MyStroke(int x, int y, Color c) {
		pts.add(new Point(x, y));
		color = c;
	}

	void addPoint(int x, int y) {
		pts.add(new Point(x, y));
	}

	void draw(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < pts.size() - 1; i++) {
			Point p1 = pts.get(i);
			Point p2 = pts.get(i + 1);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
}

class MyColorButton extends JButton {
	Color color = Color.black;

	MyColorButton(String str, Color c) {
		super(str);
		color = c;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		// getSize();
		g.fillRect(4, 4, getWidth() - 8, getHeight() - 8);

	}
}

class MoreGraphicsPanel extends JPanel {

	LinkedList<MyStroke> strokes = new LinkedList<>();
	Color curColor = Color.black;

	ArrayList<Shape> shapes = new ArrayList<>();

	MoreGraphicsPanel() {

		shapes.add(new Rectangle2D.Float(100, 100, 300, 300));
		shapes.add(new Ellipse2D.Float(100, 100, 300, 300));

		MyColorButton but1 = new MyColorButton("R", Color.red);
		but1.addActionListener((e) -> curColor = but1.color);
		MyColorButton but2 = new MyColorButton("G", Color.green);
		but2.addActionListener((e) -> curColor = but2.color);
		MyColorButton but3 = new MyColorButton("B", Color.blue);
		but3.addActionListener((e) -> curColor = but3.color);

		add(but1);
		add(but2);
		add(but3);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				strokes.add(new MyStroke(e.getX(), e.getY(), curColor));
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				strokes.get(strokes.size() - 1).addPoint(e.getX(), e.getY());
				repaint();
			}
		});

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(3));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		GradientPaint gp = new GradientPaint(0,0,Color.white, 500,500, Color.blue);
		g2.setColor(Color.orange);
		g2.setPaint(gp);
		for (var s : shapes) {
			g2.fill(s);
		}

		g.drawString("Number of Strokes: " + strokes.size(), 0, 30);

		for (var s : strokes) {
			s.draw(g);
		}
	}
}

public class MoreGraphics extends JFrame {
	MoreGraphics() {
		setTitle("More Graphics!");
		setSize(500, 500);

		add(new MoreGraphicsPanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MoreGraphics();
	}

}
