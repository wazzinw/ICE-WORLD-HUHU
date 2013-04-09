package ICEPort;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GridProjection extends JComponent{
	final int row = 100, col = 100;
	private int diaX, diaY, gridX, gridY;
	private int mouseX,mouseY;
	private int [] xCo,yCo;
	private int width = 800,height = 500;
	int posX = 401,posY = 3;
	
	public GridProjection(){
		this.setSize(width, height);
		zoom(1);
		gridX=-1; gridY=-1;
		
		
		
		addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	        	mouseX = e.getX();
	        	mouseY = e.getY();
	        	gridX = (50*mouseX+100*mouseY-50*diaY)/diaY;
	        	gridY = (100*mouseY-50*mouseX+50*diaY)/diaY;
	        	posX = e.getX();
	        	posY = e.getY();
	        	repaint();
	        }
	      });
	      addMouseMotionListener(new MouseMotionAdapter() {
	        public void mouseDragged(MouseEvent e) {
	        	
	      }
	      });
	      
	     
	}
	
	public void zoom(double i){
		int newW = (int) (width*i);
		int newH = (int) (height*i);
		this.setPreferredSize(new Dimension(newW,newH));
		diaX = (int)(800*i);
		diaY = diaX/2;
		xCo = new int []{0,diaX/2,diaX,diaX/2};
		yCo = new int []{diaY/2,0,diaY/2,diaY};
		posX = (int) (posX*i);
		posY = (int) (posY*i);
		repaint();
	}
	
	public void paintComponent(Graphics g){
		int startX=0, startY=0;
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.green.darker());
		g.fillPolygon(xCo,yCo,4);
		if(gridX>-1&&gridY>-1 && gridX<101&&gridY<101){
			 startX = diaY*(gridX-gridY+100)/100;
			 startY = diaY*(gridX+gridY)/200;
			int [] gridXCo = new int []{startX,startX+diaX/200,startX,startX-diaX/200};
			int [] gridYCO = new int []{startY,startY+diaY/200,startY+diaY/100,startY+diaY/200};
			g.setColor(Color.RED);
			g.fillPolygon(gridXCo, gridYCO, 4);
		}
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new URL("http://iceworld.sls-atl.com/graphics//body//blue.png"));
			
		} catch (IOException e) {
			System.err.println("die");
		}
		Image resize = getScaledImage(img,30,30);
		//JLabel pic = new JLabel(new ImageIcon(resize));
		g.drawImage(resize,posX,posY,null);
		
		
	}
	
	public int getGridX(){
		return gridX;
	}
	
	public int getGridY(){
		return gridY;
	}
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}
