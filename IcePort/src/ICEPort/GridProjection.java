package ICEPort;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import Login.test1;


public class GridProjection extends JPanel{
	final int row = 100, col = 100;
	private int diaX, diaY, gridX, gridY;
	private int mouseX,mouseY;
	private int [] xCo,yCo;
	private int width = 800,height = 500;
	private int posX = 385,posY = 375,imageW = 24,imageH = 30;
	int a,b;
	public GridProjection(){
		this.setSize(width, height);
		zoom(1);
		gridX = -1; 
		gridY = -1;
		
		
		
		addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	        	mouseX = e.getX();
	        	mouseY = e.getY();
	        	gridX = (50*mouseX+100*mouseY-50*diaY)/diaY;
	        	gridY = (100*mouseY-50*mouseX+50*diaY)/diaY;
	        
	        
	        	System.out.println("Grid: "+gridX+" , "+gridY);
	        	
	        	if(gridX>-1&&gridY>-1 && gridX<101&&gridY<101)
	        	{   walk(gridX,gridY);
	        		
	        		System.out.println("Position: "+posX+" , "+posY);
	        	}
	        	repaint();
	        }
	       
	      });
	      
	     
	}
	
	public void zoom(double i){
		width = (int) (width*i);
		height = (int) (height*i);
		this.setPreferredSize(new Dimension(width,height));
		diaX = (int)(800*i);
		diaY = diaX/2;
		xCo = new int []{0,diaX/2,diaX,diaX/2};
		yCo = new int []{diaY/2,0,diaY/2,diaY};
		posX = (int) (posX*i);
		posY = (int) (posY*i);
		imageW*= i;
		imageH*=i;
		repaint();
	}
	
	public void paintComponent(Graphics g){
		int startX=0, startY=0;
		g.setColor(new Color(192,192,192));
		g.fillRect(0, 0, width, height);
		
		g.setColor(new Color(0,204,102));
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
		
		Image a = img.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(a,posX-diaX/45,posY-diaY/15,null);
		
		
	}
	public void walk(int x,int y){
		test1.immigration.walk(x, y);
    	
    	
	}
	public int getGridX(){
		return gridX;
	}
	
	public int getGridY(){
		return gridY;
	}

}
