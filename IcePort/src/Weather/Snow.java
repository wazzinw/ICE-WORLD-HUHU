package Weather;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Snow extends Applet implements Runnable{
	Thread t;
	
	public Snow(){
		t = new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while(true){
			repaint();
			try {
				repaint();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	

}
	public void paint(Graphics g){
		//this.isOpaque();
		setSize(800,500);
		draw(g);
	}
	public static void draw(Graphics g){
		
		int xCo,yCo;
		g.setColor(Color.white);
		for(int i =0;i<200;i++){
		 xCo = (int) (Math.random()*800);
		 yCo = (int) (Math.random()*600);
		
		g.fillOval(xCo, yCo, 8, 8);
		
		}
	}
	
	
}