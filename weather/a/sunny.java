package a;
/*Ontiretse Keipidile 
 * create an applet showing the sun travelling across 
 * it is animated 
 */
import java.awt.*;
import java.applet.Applet;
public class sunny extends Applet implements Runnable {
	
	protected Thread mainThread;
	protected int delay;
	static int i =0 ;
	//static int y = 500;
	Dimension d;
	public  void init()
	{
	mainThread = null; 
	delay = 10;
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

	// create the offscreen buffer and associated Graphics
	offscreen = createImage(d.width, d.height);
	offgc = offscreen.getGraphics();
	// clear the exposed area
	offgc.setColor(getBackground());
	offgc.fillRect(0, 0, d.width, d.height);
	offgc.setColor(getForeground());
	// do normal redraw
	paint(offgc);
	// transfer offscreen to window
	g.drawImage(offscreen, 0, 0, this);
    }
    
	public void paint (Graphics g){
		setSize (600,600);
		 d = this.getSize();
		draw(g,d);
		
		
		
	}
public static void draw(Graphics g,Dimension d){
		
	Image  sunImage = Toolkit.getDefaultToolkit().getImage("sunglasses2smaller.gif");
	Image  cloudImage = Toolkit.getDefaultToolkit().getImage("two_glossy_cloud_S.png");
	
	int cloudwidth = cloudImage.getHeight(null);
	int cloudheight = cloudImage.getHeight(null);
	
	if (i<d.width){
		g.drawImage(sunImage,i,0,null);
		g.drawImage(cloudImage, i+sunImage.getHeight(null),40 , null);
		g.drawImage(cloudImage, i+ cloudwidth*2,d.height-cloudheight , null);
		g.drawImage(cloudImage, i ,d.height-cloudheight*2 ,null);
		i+=5;
	}else {
		i=0;
		
	}
	
	

}
}
