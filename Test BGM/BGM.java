import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.event.*;

public class BGM extends JComponent {
	static File bgm;
    int prog;
    static JFrame jframe;
    int al;
    JLabel time;
    Timer tr;
    JSlider volume = new JSlider(JSlider.VERTICAL,-60,0,0);
    Clip clip;
    AudioInputStream audio;
    
    public static void main(String args[]) {
    	bgm = new File("Track1.wav");
    	jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		BGM kp = new BGM(bgm);
        
		jframe.getContentPane().add(kp, "Center");
        jframe.setSize(250,100);
        jframe.setVisible(true);
        } 
    
    public BGM(File bgm) {
        try {
            audio=AudioSystem.getAudioInputStream(bgm);
            AudioFormat af=audio.getFormat();
            DataLine.Info di=new DataLine.Info(Clip.class,af);
            clip=(Clip)AudioSystem.getLine(di);
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
        al=(int)(clip.getMicrosecondLength()/1000);
        tr = new javax.swing.Timer(100, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {}
        	});
        Button button=new Button("Apply Volume Change");
        time = new JLabel();
        Box row = Box.createHorizontalBox();
        row.add(volume);
        volume.addChangeListener(new ChangeListener(){
        	public void stateChanged(ChangeEvent event2) {
        		volume = (JSlider) event2.getSource();
        		if (!volume.getValueIsAdjusting())
        			prog=(int)volume.getValue();
        		prog = volume.getValue();
        		time.setText(prog+ "." + (prog % 1000)/100);
        		}
        	});
        row.add(button);
        row.add(time);
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event1) {
        		play();}
        	});
        setLayout(new GridLayout(0, 1, 0, 0));
        this.add(row);
        }
    public void play() {
        try {
        	FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        	volume.setValue(prog);
        	} catch(Exception e) {}
        clip.start();
        tr.start();
        }
    }