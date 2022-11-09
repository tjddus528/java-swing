import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class HelloSwingPanel extends JPanel {
	HelloSwingPanel() {
		setBackground(Color.orange); // panel의 색상 변경

		this.setLayout(new GridLayout(3, 4)); // 세로 3칸, 가로 4칸

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {

				JButton but = new JButton("button " + i + " "+j);
				add(but, j, i); // panel에 button을 위치시킴
			}
		}

		JTextField tf = new JTextField("Sejong University!");
		add(tf, 4, 4);
	}
}

public class HelloSwing extends JFrame {

	HelloSwing() {
		// JFrame 생성자 호출 -> 작업자 호출(Thread), Action 또는 이벤트가 일어날 때까지 대기
		setTitle("My First Swing Program");
		setSize(400, 300);

		// 컴포넌트 배치
		HelloSwingPanel p = new HelloSwingPanel();
		add(p); // frame에 panel을 추가 -> frame 에 추가시키면 default는 전체를 채움

		JButton but = new JButton("bottom");
		add(but, BorderLayout.SOUTH);
		
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloSwing f = new HelloSwing();
		System.out.println("Frame is ready!");
		return;

	}

}
