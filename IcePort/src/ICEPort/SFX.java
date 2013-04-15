package ICEPort;

import java.io.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class SFX extends JComponent {
	static File sfx;
    int prog;
    static JFrame jframe;
    int al;
    JLabel time;
    Timer tr;
    JSlider volume = new JSlider(JSlider.HORIZONTAL,-60,0,0);
    Clip clip;
    AudioInputStream audio;
    
    public static void main(String args[]) {
    	sfx = new File("MSN.wav");
		SFX kp = new SFX(sfx);
        } 
    
    public SFX(File sfx) {
        try {
            audio=AudioSystem.getAudioInputStream(sfx);
            AudioFormat af=audio.getFormat();
            DataLine.Info di=new DataLine.Info(Clip.class,af);
            clip=(Clip)AudioSystem.getLine(di);
            clip.open(audio);
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            }
        catch(Exception e) {
        	System.out.println("Exception Caught");
        	}finally{
            try {
            	audio.close();
            	}
            catch(Exception e) {
                System.out.println("Exception Caught");
                }
            }
        }
    }