package ICEPort;

import iceworld.given.IcetizenLook;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;

import Fetch.Fetching;
import Fetch.HuhuIcetizen;
import Fetch.actionn;
import Login.Customize;
import Login.test1;


public class GridProjection extends JPanel{
	final int row = 100, col = 100;
	private int diaX, diaY, gridX, gridY;
	private int mouseX,mouseY;
	private int [] xCo,yCo;
	private int width = 800,height = 500;
	private int posX=0 ,posY=0 ,imageW = 28,imageH = 32;
	private SFX sf;
	//int X,Y;
	Point d = new Point();
	Point userLocation;
	int count =0;
	public  static boolean isTalking = false;
	public static boolean isYelling = false;
	int TALK_DURATION = ICEFrame.TALK_DURATION;
	Image hImage,wImage,bImage,sImage;
	public static Image uhImage,uwImage,ubImage,usImage;
	String msg = "NULL";
	Timer timer;
	public static IcetizenLook userLook = new IcetizenLook();
	public Graphics graphics;
	 LinkedList<HuhuIcetizen> tizeno = ICEFrame.s.getCitizen();
	 LinkedList<actionn> walkingo = ICEFrame.s.getWalk();
	 LinkedList<actionn> talkingo = ICEFrame.s.getTalk();
	 LinkedList<actionn> yellingo = ICEFrame.s.getYell();
	

	public GridProjection(){
		this.setSize(width, height);
		zoom(1.5);
		gridX = -1; 
		gridY = -1;
		



		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				
				gridX = (50*mouseX+100*mouseY-50*diaY)/diaY;
				gridY = (100*mouseY-50*mouseX+50*diaY)/diaY;


				//System.out.println("Grid: "+gridX+" , "+gridY);

				if(gridX>-1&&gridY>-1 && gridX<101&&gridY<101)
				{   
					walk(gridX,gridY);
					//System.out.println("Position: "+posX+" , "+posY);
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
		imageW *= i;
		imageH *= i;
		
		repaint();
	}


	public void paintComponent(Graphics g){
		
		
		
		int startX=0, startY=0;
		g.setColor(new Color(191,239,255));
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.YELLOW);
		g.drawRect(0, 0, 100, 100);
		
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

		
		d.x = posX;
		d.y = posY;
		
		IcetizenLook look = new IcetizenLook();
		Point location = new Point();
		String type = "";
		String username ="";
		
		for(int i =0;i<tizeno.size();i++){
			
			username = tizeno.get(i).getUsername();
			look =tizeno.get(i).getIcetizenLook();
			
			if(!username.equals(test1.user)){
				location  = tizeno.get(i).position;
				
				g.setColor(Color.orange);
				g.fillOval(location.x, location.y, 5, 5);
				location = getGridCo(location.x,location.y);
				
				type = tizeno.get(i).gettype();
				
				hImage = tizeno.get(i).getHead();
				bImage = tizeno.get(i).getBody();
				wImage = tizeno.get(i).getWeapon();
				sImage = tizeno.get(i).getShirt();
			
				//System.out.println("Repaint"+i);
				if(type.equals("0")){
					showAlien(g,location);
				}else showCitizen(g,location,hImage,bImage,wImage,sImage,username,Color.red);	
				
			}else if(tizeno.get(i).getport().equals(251)){
				showAlien(g,location);
			}
			
			else{
				hImage = tizeno.get(i).getHead();
				bImage = tizeno.get(i).getBody();
				wImage = tizeno.get(i).getWeapon();
				sImage = tizeno.get(i).getShirt();

				
				
				if(count==0){
					location  = tizeno.get(i).position;
					
					g.setColor(Color.red);
					g.fillOval(location.x, location.y, 20, 20);
					
					location = getGridCo(location.x,location.y);
					
					posX = location.x;
					posY = location.y;
					
					userLook.gidB = look.gidB;
				    userLook.gidH = look.gidH;
				    userLook.gidS = look.gidS;
				    userLook.gidW = look.gidW;
				   
				    uhImage = hImage;
				    ubImage = bImage;
				    uwImage = wImage;
				    usImage = sImage;
				    
					count++;
				}else{
					Point pt = getGridCo(d.x,d.y);
					g.setColor(Color.blue);
					g.fillOval(pt.x, pt.y, 20, 20);
				
				}
				showCitizen(g,d,uhImage,ubImage,uwImage,usImage,username,Color.yellow);
				
			}
			 
			 
			try {
				ICEFrame.c.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			timer = new Timer();
		    
			if(isYelling==true){
				drawYell(g,g.getFont(),g.getColor());
				timer.schedule(new YellTask(), TALK_DURATION*1000);
				
			}
			
			if(isTalking==true){
				drawTalk(g,g.getFont(),g.getColor());
				timer.schedule(new TalkTask(), TALK_DURATION*1000);
			}
		
				for(int w =0;w<walkingo.size();w++){
					if(walkingo.get(w).getsuid().equals(tizeno.get(i).getuid())){
						walkk(tizeno.get(i),walkingo.get(w),g);
					}
				}
			
				System.out.println("Loop tizen");
				for(int t=0;t<talkingo.size()-1;t++){
					System.out.println("Loop Talk");
					if(talkingo.get(t).getsuid().equals(tizeno.get(t).getuid())){
						System.err.println("Talk UID: "+talkingo.get(t).getsuid());
						
						//String msg = talkingo.get(t).getdetail();
						
						String msg = "FINE";
						
						//tizeno.get(k).talk(msg,g,g.getFont(),g.getColor());
						talkk(tizeno.get(i),msg,g,g.getFont(),g.getColor());
					//}
				}
			
				for(int y=0;y<yellingo.size()-1;y++){
					System.out.println("Loop yell");
					if(yellingo.get(y).getsuid().equals(tizeno.get(i).getuid())){
						System.err.println("Yell UID: "+yellingo.get(y).getsuid());
						String msg = "Freak";
						
						//tizeno.get(k).yell(msg,g,g.getFont(),g.getColor());
						
						yelll(tizeno.get(i),msg,g,g.getFont(),g.getColor());
					}
				}
			}
		}
		}
	
	public void walkk(HuhuIcetizen a,actionn b,Graphics g){
		
	}
		
	public void talkk(HuhuIcetizen a,String msg,Graphics g,Font f,Color c){
		System.err.println("TALKINGGG");
		int fontSize = 12;
	    Point n = getGridCo(a.getPosition().x,a.getPosition().y);
		g.setColor(Color.yellow);
	    g.fillRoundRect(n.x-5, n.y-40, 50, 20, 10, 10);
	    
	    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	    g.setColor(Color.black);
	    
	    g.drawString(msg, n.x-3, n.y-30);
	    g.setFont(f);
		g.setColor(c);
		repaint();
	}
	
	
	public void yelll(HuhuIcetizen a,String msg,Graphics g,Font f,Color c){
	    Point n = getGridCo(a.getPosition().x,a.getPosition().y);
	    System.err.println("Yelling");
		g.setColor(Color.orange);
		g.fillRoundRect(n.x-5, n.y-60, 300, 40, 10, 10);
		
		
		g.setFont(new Font("Courier",Font.BOLD,50));
		g.setColor(Color.MAGENTA);
		g.drawString(msg, n.x-3, n.y-30);
		
		g.setFont(f);
		g.setColor(c);
		repaint();
	}
	
	private void drawYell(Graphics g,Font f,Color c) {
		 g.setColor(Color.orange);
		 g.fillRoundRect(d.x-5, d.y-200, 1200, 160, 20, 20);
		
		
		g.setFont(new Font("Courier",Font.BOLD,200));
		g.setColor(Color.MAGENTA);
		g.drawString(msg, d.x-3, d.y-100);
		
		g.setFont(f);
		g.setColor(c);
		
	}

	private void drawTalk(Graphics g,Font f,Color c) {
		//Dimension d = this.getPreferredSize();
	    int fontSize = 12;
	    g.setColor(Color.yellow);
	    g.fillRoundRect(d.x-5, d.y-40, 50, 20, 10, 10);
	    
	    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
	    g.setColor(Color.black);
	    
	    g.drawString(msg, d.x-3, d.y-30);
	    g.setFont(f);
		g.setColor(c);
		
	}

	private void showAlien(Graphics g, Point location) {
		Image  alienImage = Toolkit.getDefaultToolkit().getImage("alien3.gif");	
		//Image img = alienImage.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(alienImage,location.x,location.y,null);
		
	}

	public void showCitizen(Graphics g,Point p,Image head,Image body,Image weapon,Image shirt,String username,Color c){
		drawWeapon(g,weapon,p);
		drawHead(g,head,p);
		drawBody(g,body,p);
		drawShirt(g,shirt,p);
		
		
		g.setColor(c);
		g.drawString(username, p.x-3, p.y-5);
		


	}
	public Point getGridCo(int x,int y){
		int a = diaY*(x-y+100)/100;
		int b = diaY*(x+y)/200;
		return new Point(a,b);

	}
	public void walk(int x,int y){
		File sfx = new File("bounce.wav");
		sf = new SFX(sfx);
		
		test1.immigration.walk(x, y);

		posX = mouseX-imageW+diaX/200;
		posY = mouseY-imageH+diaY/200;
		
		repaint();



	}
	public void drawBody(Graphics g,Image body,Point location){
		BufferedImage bodyImg = null;
		bodyImg = (BufferedImage) body;
		Image a = bodyImg.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(a,location.x,location.y,null);


	}

	public void drawHead(Graphics g,Image head,Point location){
		BufferedImage headImg = null;
		headImg = (BufferedImage) head;
		Image b = headImg.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(b,location.x,location.y,null);



	}
	public void drawShirt(Graphics g,Image shirt,Point location){
		BufferedImage shirtImg = null;
		shirtImg = (BufferedImage) shirt;
		Image c = shirtImg.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(c,location.x,location.y,null);





	}


	public void drawWeapon(Graphics g,Image weapon,Point location){
		BufferedImage weaponImg = null;
		weaponImg = (BufferedImage) weapon;
		Image d = weaponImg.getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);
		g.drawImage(d,location.x,location.y,null);

	}

	public void talkAnimate(String msg) {
		this.msg = msg;
		isTalking = true;
		repaint();
		
		
		
	}

	public void yellAnimate(String msg) {
		this.msg = msg;
		isYelling = true;
		repaint();
	}
	
	
	public class TalkTask extends TimerTask {

		@Override
		public void run() {
			isTalking = false;
			repaint();

		}

	}public class YellTask extends TimerTask {

		@Override
		public void run() {
			isYelling = false;
			repaint();

		}

	}
	
	}
		
		

	
		
		
		
		
		

	




