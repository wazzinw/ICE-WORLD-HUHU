package ICEPort;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Weather.Snow;
import a.*;


public class TopPane extends JPanel implements Runnable{
	Thread t;
	String weather;
	Dimension d;
	public TopPane(String w){
		this.weather = w;
		setSize(800,500);
		setOpaque(false);
		t= new Thread(this);
		t.start();
	}
	public void paintComponent(Graphics g){
		d = this.getSize();
		if(weather.equals("Snowing")) {
			//g.setColor(c)
			
			Snow.draw(g);
		}
			
		else if(weather.equals("Raining")){
			rain.draw(g,d);
		}else if(weather.equals("Cloudy")){
			
			Cloudy.draw(g,d);
		}else if(weather.equals("Sunny")){
			
			sunny.draw(g,d);
		}
		
	
	
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
