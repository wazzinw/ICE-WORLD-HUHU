package ICEPort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

import Weather.Snow;

public class TopPane extends JPanel implements Runnable{
	Thread t;
	
	
	public TopPane(){
		setSize(800,500);
		t= new Thread(this);
		t.start();
	}
	public void paintComponent(Graphics g){
		Snow.draw(g);
	
	
}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				repaint();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
}
