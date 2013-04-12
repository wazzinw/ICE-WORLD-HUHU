package ICEPort;

import sun.audio.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class Sound {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(200,200);
		JButton button = new JButton("Start");
		frame.add(button);
		button.addActionListener(new AL());
		frame.show(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO Auto-generated method stub

	}
	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e){
			music();
		}
	}

	public static void music(){
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;

	try{
	BGM = new AudioStream(new FileInputStream("Track1.wav"));
	MD = BGM.getData();
	loop = new ContinuousAudioDataStream(MD);
	}catch(IOException error){}

	MGP.start(loop);
	}

}