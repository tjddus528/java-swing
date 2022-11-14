import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MyActionListener implements ActionListener {

	EventHandlingPracticePanel panel = null;

	MyActionListener(EventHandlingPracticePanel p) {
		panel = p;
	}

	boolean toggle = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action Performed!!!");
		JButton but = (JButton) e.getSource();
		if (toggle != true)
			but.setText("haha!");
		else
			but.setText("hoho!");
		toggle = !toggle;

		Color c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
		panel.setBackground(c);
		
		String str = panel.text.getText();
		panel.but2.setText(str);

	}

}

// C++ 스타일
class MyButton extends JButton implements ActionListener {
	MyButton(String str) {
		super(str);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("button2 Performed!!!");

	}

}

class EventHandlingPracticePanel extends JPanel implements ActionListener, MouseListener {
	
	
	JButton but1, but2, but3, but4;
	JTextField text;
	EventHandlingPracticePanel() {
		setBackground(Color.orange);

		but1 = new JButton("button");
		MyActionListener a = new MyActionListener(this);
		but1.addActionListener(a);

		but2 = new MyButton("but2");
		but2.addActionListener(a);
		
		but3 = new JButton("but3");
		but3.addActionListener(this);
		
		final int aaa =10;
		but4 = new JButton("but4");
		but4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				but4.setText(text.getText()+aaa);
			}
		});
//		aaa = 20;
		
		JButton but5 = new JButton("but5");
		but5.addActionListener((e)->{
			but5.setText(text.getText())
;		});

		text = new JTextField("..........");
		text.addActionListener((e)->{
			but1.setText(text.getText());
			but2.setText(text.getText());
			but3.setText(text.getText());
			but4.setText(text.getText());
			but5.setText(text.getText());
		});
		
		add(but1);
		add(but2);
		add(but3);
		add(but4);
		add(but5);
		add(text);
		
		this.addMouseListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == but3) {
			but3.setText(text.getText());
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse Pressed!");
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

public class EventHandlingPractice extends JFrame {

	EventHandlingPractice() {
		setTitle("Event Handling Practice!");
		setSize(400, 300);

		add(new EventHandlingPracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new EventHandlingPractice();

	}

}
