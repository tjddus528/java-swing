import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MoreComponentPanel extends JPanel {
	JLabel label;

	MoreComponentPanel() {
		label = new JLabel("Sejong");
		add(label);

	}
}

class MyDialog extends JDialog {
	MyDialog(MoreComponentPractice frame) {
		super(frame, "My Dialog", true); // Modal Dialog

		setSize(300, 100);
//		setTitle("My Dialog");

		this.setLayout(new FlowLayout());

		JTextField tf = new JTextField(10);
		add(tf);
		tf.setText(frame.panel.label.getText());

		JButton okay = new JButton("okay");
		JButton cancle = new JButton("cancle");

		okay.addActionListener((e) -> {
			frame.panel.label.setText(tf.getText());
			setVisible(false);
		});
		cancle.addActionListener((e) -> {
			setVisible(false);
		});

		add(okay);
		add(cancle);

		setVisible(true);
	}
}

public class MoreComponentPractice extends JFrame implements ActionListener {

	MoreComponentPanel panel = null;

	MoreComponentPractice() {
		setSize(300, 300);
		setTitle("More Component");

		setMenu();
		panel = new MoreComponentPanel();
		add(panel);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	void setMenu() {
		JMenuBar mb = new JMenuBar();

		String[][] strs = { {"Open", "Save", "", "Close"}, 
							{"copy", "paste", "cut", "", "Input1", "Input2", "", "Color"} };
		String[] str = { "Open", "Save", "", "Close" };
		JMenu fileMenu = new JMenu("File");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("")) {
				fileMenu.addSeparator();
			} else {
				JMenuItem it = new JMenuItem(str[i]);
				it.addActionListener(this);
				fileMenu.add(it);
			}
		}

		JMenu editMenu = new JMenu("Edit");
		String[] str2 = { "copy", "paste", "cut", "", "Input1", "Input2", "", "Color" };
		for (int i = 0; i < str2.length; i++) {
			if (str2[i].equals("")) {
				editMenu.addSeparator();
			} else {
				JMenuItem it = new JMenuItem(str2[i]);
				it.addActionListener(this);
				editMenu.add(it);
			}
		}
//		editMenu.add(new JMenuItem("copy"));
//		editMenu.add(new JMenuItem("paste"));
//		editMenu.add(new JMenuItem("cut"));

		mb.add(fileMenu);
		mb.add(editMenu);

		setJMenuBar(mb);
	}

	public static void main(String[] args) {
		new MoreComponentPractice();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) { // 그 메뉴에 써 있는 스트링
		case "Open":
			JFileChooser fileChooser1 = new JFileChooser();
			int result1 = fileChooser1.showOpenDialog(this);
			if (result1 == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser1.getSelectedFile();
				panel.label.setText(file.getAbsolutePath());
			}
			System.out.println("Open!");
			break;
		case "Save":
			JFileChooser fileChooser2 = new JFileChooser();
			int result2 = fileChooser2.showSaveDialog(this);
			if (result2 == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser2.getSelectedFile();
				panel.label.setText(file.getAbsolutePath());
			}
			System.out.println("Save!");
			break;
		case "Input1":
			MyDialog dlg = new MyDialog(this);
			break;
		case "Input2":
			String str = JOptionPane.showInputDialog("Input Text");
			if (str != null)
				panel.label.setText(str);
			break;
		case "Color":
			Color color = JColorChooser.showDialog(this, "Select your Color", panel.getBackground());
			if (color != null) {
				panel.setBackground(color);
			}
			break;
		case "Close":
			int result = JOptionPane.showConfirmDialog(this, "Close?", "Close Confirm", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
			break;
		default:
			break;
		}
	}

}