package electricDrumKit;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class ClipDemo {

	public static void main(String[] args) throws Exception {

		// specify the sound to play
		// (assuming the sound can be played by the audio system)
		File soundFile = new File("E:/code/arduino-1.6.5-r2/workspace/soundbank/sd2.wav");
		AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

		// load the sound into memory (a Clip)
		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.open(sound);

		clip.addLineListener(new LineListener() {
			public void update(LineEvent event) {
				if (event.getType() == LineEvent.Type.STOP) {
					event.getLine().close();
					System.exit(0);
				}
			}
		});
		
		// play the sound clip
		clip.start();
		Thread.sleep(1000000);
	}
}
