import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ImagePracticePanel extends JPanel {
	BufferedImage img;

	ImagePracticePanel() {
//		ImageIcon imgIcon = new ImageIcon("images/flower.jpg"); // 상대경로 (기준경로 : 현재 프로젝트 폴더)
//		img = imgIcon.getImage();

//		JLabel imgLabel = new JLabel(imgIcon);
//		add(imgLabel);	// 크기 지정 X, 위치 지정 O

		try {
			URL url = getClass().getResource("flower.jpg");
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int iw = img.getWidth();
		int ih = img.getHeight();

		int cx = getWidth() / 2;
		int cy = getHeight() / 2;

//		g.drawImage(img, cx-iw/2,cy-ih/2, this);
//		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		float aspect = (float) iw / ih;
		int nh = getHeight();
		int nw = (int) (nh * aspect);

		if (nw > getWidth()) {
			nw = getWidth();
			nh = (int) (nw / aspect);
		}
//		g.drawImage(img, cx-nw/2, cy-nh/2, nw, nh, this);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0, iw / 2, ih / 2, this);

	}
}

public class ImagePractice extends JFrame {

	ImagePractice() {

		setTitle("ImagePractice");
		setSize(500, 500);

		add(new ImagePracticePanel());

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ImagePractice();
	}

}
