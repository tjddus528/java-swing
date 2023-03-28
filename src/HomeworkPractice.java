import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 조상 object
abstract class MyObject {
	float x;
	float y;
	Color color;

	MyObject(float _x, float _y, Color c) {
		x = _x;
		y = _y;
		color = c;
	}

	// abstract -> 반드시 구현
	abstract void draw(Graphics g);

	// optional로 만드는 방법
	void update(float dt) {
	}

	abstract void collisionResolution(MyObject o);
}

class MyWall extends MyObject {
	float width;
	float height;

	MyWall(float _x, float _y, float _w, float _h, Color c) {
		super(_x, _y, c);
		width = _w;
		height = _h;
	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	void collisionResolution(MyObject o) {

	}
}

class MyBall extends MyObject {
	float radius;
	float vx;
	float vy;

	// 0.016초 전 위치
	float prev_x;
	float prev_y;

	MyBall(float _x, float _y, float r, Color c) {
		super(_x, _y, c);
		radius = r;

		// 시작할 때에는 이전 위치는 현재 위치와 동일 -> update에서 변화됨
		prev_x = _x;
		prev_y = _y;

		// 속도 100
		float speed = 100.0f; // 초당 100pixel 움직임

		// 방향은 각도 (360 = 2ㅠ) 랜덤으로
		float angle = (float) (Math.random() * 2.0f * 3.141592); // 0~2ㅠ
		angle = (float) (0.5f * 3.141592);
		vx = speed * (float) (Math.cos(angle));
		vy = speed * (float) (Math.sin(angle));

	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	@Override
	void update(float dt) {
		// x, y 변화하기 전에 위치 저장
		prev_x = x;
		prev_y = y;

		x += vx * dt;
		y += vy * dt;
	}

	boolean isCollide(MyObject o) {
		if (o instanceof MyWall) {
			MyWall w = (MyWall) o;
			if (x > (w.x - radius) && x < (w.x + w.width + radius)) {
				if (y > (w.y - radius) && y < (w.y + w.height + radius)) {
					color = Color.blue;

					return true;
				}
			}
		}
		return false;
	}

	@Override
	void collisionResolution(MyObject o) {
		if (o instanceof MyWall) {
			MyWall w = (MyWall) o;

			// 벽의 왼쪽에 있었다면
			if (prev_x < w.x - radius) {
				// 뚫고 들어가지 않게 벽에 붙여놓고
				x = w.x - radius;
				vx = -vx;
			}
			// 벽의 오른쪽에 있었다면
			if (prev_x > w.x + w.width + radius) {
				// 뚫고 들어가지 않게 벽에 붙여놓고
				x = w.x + w.width + radius;
				vx = -vx;
			}
			// 벽의 위쪽에 있었다면
			if (prev_y < w.y - radius) {
				// 뚫고 들어가지 않게 벽에 붙여놓고
				y = w.y - radius;
				vy = -vy;
			}
			// 벽의 아래쪽에 있었다면
			if (prev_y > w.y + w.height + radius) {
				// 뚫고 들어가지 않게 벽에 붙여놓고
				y = w.y + w.height + radius;
				vy = -vy;
			}

		}
	}
}

class MyBlock extends MyObject {
	float width;
	float height;

	MyBlock(float _x, float _y, float _w, float _h, Color c) {
		super(_x, _y, c);
		width = _w;
		height = _h;
	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	void collisionResolution(MyObject o) {
		// TODO Auto-generated method stub

	}
}

class HomeworkPanel extends JPanel implements Runnable {
	LinkedList<MyObject> objs;
	boolean keyPressed = false;

	HomeworkPanel() {
		objs = new LinkedList<>();
		objs.add(new MyWall(0, 0, 560, 20, Color.black));
		objs.add(new MyWall(0, 350, 560, 20, Color.black));
		objs.add(new MyWall(0, 0, 20, 370, Color.black));
		objs.add(new MyWall(560, 0, 20, 370, Color.black));

		objs.add(new MyWall(100, 100, 200, 50, Color.black));
		objs.add(new MyWall(300, 250, 150, 70, Color.black));

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					keyPressed = true;
				}
			}
		});
		setFocusable(true);
		requestFocus();

		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (var o : objs) {
			o.draw(g);
		}
	}

	@Override
	public void run() {
		try {
			while (true) { // GAME LOOP
				// key input
				if (keyPressed) {
					objs.add(new MyBall(50, 50, 5, Color.red));
					keyPressed = false;
				}

				// update
				for (var o : objs)
					o.update(0.016f);

				// collision resolution
				for (var o : objs) {
					if (!(o instanceof MyBall))
						continue;
					for (var w : objs) {
						if (!(w instanceof MyWall))
							continue;
						// w -> wall
						// o -> ball
						MyWall wall = (MyWall) w;
						MyBall ball = (MyBall) o;

						if (ball.isCollide(wall)) {
							ball.collisionResolution(wall);
						}

					}
				}

				// repaint
				repaint();

				Thread.sleep(16);
			}
		} catch (Exception E) {
			return;
		}
	}
}

public class HomeworkPractice extends JFrame {
	HomeworkPractice() {
		setTitle("Homework Practice");
		setSize(600, 400);

		add(new HomeworkPanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HomeworkPractice();
	}

}
