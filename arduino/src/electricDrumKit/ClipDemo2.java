package electricDrumKit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Handles play, pause, and looping of sounds for the game.
 * @author Tyler Thomas
 *
 */
public class ClipDemo2 {
    private Clip myClip;
    public ClipDemo2(String fileName) {
            try {
                File file = new File(fileName);
                if (file.exists()) {
                    myClip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
                    myClip.open(ais);
                }
                else {
                    throw new RuntimeException("Sound: file not found: " + fileName);
                }
            }
            catch (MalformedURLException e) {
                throw new RuntimeException("Sound: Malformed URL: " + e);
            }
            catch (UnsupportedAudioFileException e) {
                throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            }
            catch (IOException e) {
                throw new RuntimeException("Sound: Input/Output Error: " + e);
            }
            catch (LineUnavailableException e) {
                throw new RuntimeException("Sound: Line Unavailable: " + e);
            }
    }
    public void play(){
        myClip.setFramePosition(0);  // Must always rewind!
        myClip.loop(0);
        myClip.start();
    }
    public void loop(){
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        myClip.stop();
    }
    
    public static void main(String[] s){
    	ClipDemo2 clip = new ClipDemo2("E:/L/others/test.wav");
    	clip.play();
    }
}