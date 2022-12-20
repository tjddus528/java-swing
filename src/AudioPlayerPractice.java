import java.awt.Color;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class AudioPlayerPanel extends JPanel implements LineListener {
	Clip clip; // thread

	AudioPlayerPanel() {

		try {
			clip = AudioSystem.getClip();
//			File audioFile = new File("audios/soundtest.wav");
			
			URL url = getClass().getResource("soundtest.wav");	// bytecode 위치 찾음
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip.open(audioInputStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton play = new JButton("play");
		JButton stop = new JButton("stop");
		JButton loop = new JButton("loop");

		add(play);
		add(stop);
		add(loop);

		play.addActionListener((e) -> {
			clip.start();
		});
		stop.addActionListener((e) -> {
			clip.stop();
			// 전체를 여러개의 Frame으로 나누고 그 중에서 어디를 가리키는지
			clip.setFramePosition(0); // -> 처음으로 되돌리기
//			clip.getFrameLength();	// 전체 Frame 개수
		});
		loop.addActionListener((e) -> {
			clip.loop(Clip.LOOP_CONTINUOUSLY); // 계속 반복
		});

		clip.addLineListener(this);

	}

	@Override
	public void update(LineEvent event) {
		if (event.getType() == LineEvent.Type.START) {
			this.setBackground(Color.orange);
		}
		if (event.getType() == LineEvent.Type.STOP) {
			this.setBackground(Color.darkGray);
			clip.setFramePosition(0);		// 리와인드
		}
	}
}

public class AudioPlayerPractice extends JFrame {
	AudioPlayerPractice() {
		setSize(300, 100);
		setTitle("Audio Player");

		add(new AudioPlayerPanel());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AudioPlayerPractice();
	}

}
