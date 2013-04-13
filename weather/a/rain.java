package a;
/* ontiretse keipidile 
 * create a animation of rain on an applet 
 *  
 */
import java.awt.*;
import java.applet.Applet;

public class rain extends Applet implements Runnable{
	
	
	protected Thread mainThread;
	protected int delay;
	
	public  void init()
	{
	mainThread = null;
	// higher delay value results in slower "rain" 
	delay = 200;
	}
	public void start(){
		if(mainThread== null){
			mainThread = new Thread(this);
			mainThread.start();
			}
		}
	public void run (){
		while(Thread.currentThread()== mainThread){
			repaint();
			try{
				Thread.currentThread().sleep(delay);
			}
			catch (InterruptedException e){}
		}
	}
	public void paint(Graphics g){
		Dimension d = this.getSize();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, d.width, d.height);
		draw(g,d);
		
	}
	public static void draw(Graphics g, Dimension d){
		g.setColor(Color.white);
		// higher limit for the loop will result in more "rain" 
		for (int i =0 ; i < 500 ; i++){
			int xcoord = (int)(Math.random()*d.width)+5;
			int ycoord = (int)(Math.random()*d.height)+5;
			g.drawLine(xcoord, ycoord, xcoord-5, ycoord+10);
			//g.fillOval(xcoord, ycoord, 10, 10);
		}
	}
}
