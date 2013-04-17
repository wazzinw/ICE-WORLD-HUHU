package a;
/*ontiretse keipidile 
 * try to make a sunny day , which is animated . 
 * 
 * */
import java.awt.*;
import java.applet.Applet;


public class Cloudy extends Applet implements Runnable{
	
	protected Thread mainThread;
	protected int delay;
	
	static int i =0 ;
	static int y = 500;
	static Dimension d ;
	public  void init()
	{
	mainThread = null; 
	delay = 1000;
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
	public void update(Graphics g) {
		Graphics offgc;
		Image offscreen = null;
		Dimension d = this.getSize();
		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());
		paint(offgc);
		g.drawImage(offscreen, 0, 0, this);
	    }
	public void paint (Graphics g){
			setSize(800,600);
			d = this.getSize();
			draw(g,d);
		 
			
			
	}
	public static void draw(Graphics g,Dimension d){
		
		Image  cloudImage = Toolkit.getDefaultToolkit().getImage("two_glossy_cloud_S.png");
		
			int width = cloudImage.getHeight(null);
			int height = cloudImage.getHeight(null);
			int ycoord = 0;
			
			if(i<d.width ){
				
			g.drawImage(cloudImage, i ,ycoord , null);
			g.drawImage(cloudImage, i+width ,ycoord+height*2 , null);
			g.drawImage(cloudImage, i+width+width, ycoord+height, null);
			g.drawImage(cloudImage, i+width*3 ,ycoord+height*3, null);
			g.drawImage(cloudImage, i+width*4, ycoord, null);
			g.drawImage(cloudImage, i,ycoord+height*3 , null);
			g.drawImage(cloudImage, i+ width*2,d.height-height , null);
			g.drawImage(cloudImage, i ,d.height-height*2 ,null);
			
			i+=5;
			}else{
				i=0;
			}
			
			
			if(y<d.width){
			g.drawImage(cloudImage, y+width ,ycoord+height*2 , null);
			g.drawImage(cloudImage, y+width+width, ycoord+height, null);
			g.drawImage(cloudImage, y+width*3 ,ycoord+height*3, null);
			
			g.drawImage(cloudImage, y+700 ,d.height-height*2 ,null);
			g.drawImage(cloudImage, y+width*2,d.height-height , null);
			y+=5;
			}else{
				y=0;
			}
	}

}
