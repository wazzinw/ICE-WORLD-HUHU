package ICEPort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

import Login.Customize;
import Login.test1;

import Weather.Snow;




public class ICEFrame extends JFrame implements MouseMotionListener,MouseListener{
	private JPanel functionPanel,chatPanel,zoomPanel,setPanel;
	private JTextField chatField;
	private JButton zoomIn,zoomOut,talk,yell,refresh,talkDuration,logOutB;
	private double i=1;
	private GridProjection iso = new GridProjection();
	private JScrollPane scroll = new JScrollPane();
	private JMenuBar menuBar;
	private JMenuItem newWindow,logOut,quit,soundSet,customIce,author,h;
	private JMenu file,edit,help;
	private MenuListener l = new MenuListener();
	private ButtonListener b = new ButtonListener();
	private TextFieldListener t = new TextFieldListener();
	private String msg = null;
	private JSlider talkSlider;
	private JLayeredPane layeredPane = new JLayeredPane();
	private TopPane top;
	private BottomPane bottom = new BottomPane();
	private BGM bg;
	JFrame bgf = new JFrame();
	
	public ICEFrame() {
		setSize(800,600);
		setGUI();
		
		addMouseMotionListener(this);
		addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	private void setGUI() {
		File bgm = new File("Track1.wav");
		//bg = new BGM(bgm);
		//bgf.add(bg);
		
		top = new TopPane("");
		//Scroll Pane
		scroll = new JScrollPane(iso);
		scroll.setWheelScrollingEnabled(true);
		
		//layeredPane.setBounds(0, 0, 800, 500);
		
		this.add(layeredPane,BorderLayout.CENTER);
		
		scroll.setBounds(0, 0, 800, 500);
		
		top.setBounds(0,0,800,500);
		bottom.setBounds(400, 100, 400, 400);
		layeredPane.add(bottom,new Integer(0),0);
		layeredPane.add(scroll,new Integer(1),0);
		layeredPane.add(top,new Integer(2),0);
		
	    
		functionPanel = new JPanel();
		getContentPane().add(functionPanel, BorderLayout.SOUTH);
		functionPanel.setLayout(new BorderLayout(0, 0));
		//functionPanel.add(soundPanel,BorderLayout.WEST);
		
		setPanel = new JPanel(new FlowLayout());
		refresh = new JButton("Refresh Interval");
		refresh.addActionListener(b);
		talkDuration = new JButton("Talk Duration");
		talkDuration.addActionListener(b);
		logOutB = new JButton("Log Out");
		logOutB.addActionListener(b);
		
		setPanel.add(refresh);
		setPanel.add(talkDuration);
		setPanel.add(logOutB);
		functionPanel.add(setPanel,BorderLayout.CENTER);
		
		chatPanel = new JPanel();
		functionPanel.add(chatPanel, BorderLayout.SOUTH);
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.X_AXIS));
		
		chatField = new JTextField();
		chatPanel.add(chatField);
		chatField.setText("text");
		chatField.setColumns(10);
		//chatField.addActionListener(t);
		

		talk = new JButton("Talk");
		talk.addActionListener(b);
		chatPanel.add(talk);
		
	    yell = new JButton("Yell");
	    yell.addActionListener(b);
		chatPanel.add(yell);
		
	    zoomPanel = new JPanel();
		functionPanel.add(zoomPanel, BorderLayout.EAST);
		zoomPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
	    zoomIn = new JButton("+");
		zoomPanel.add(zoomIn);
		zoomIn.addActionListener(b);

	    zoomOut = new JButton("-");
		zoomOut.addActionListener(b);
		zoomPanel.add(zoomOut);
	
		functionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		menu();
		///ShortCut for zoom functions
		KeyboardFocusManager.getCurrentKeyboardFocusManager()  
	    .addKeyEventDispatcher(new KeyEventDispatcher(){  
	       public boolean dispatchKeyEvent(KeyEvent e){  
	         if(e.getID() == KeyEvent.KEY_PRESSED)  
	         {  
	        	 if(e.isControlDown()){
	           if(e.getKeyCode() == KeyEvent.VK_MINUS ) zOut();  
	         }  else if(e.getKeyCode()== KeyEvent.VK_PLUS) zIn();
	        	 }
	         return false;}});  
		
	}
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	private void menu() {
		 menuBar = new JMenuBar();
	  		
	  		
  		 file = new JMenu("File");
  		 edit = new JMenu("Edit");
  		 help = new JMenu("Help");
  		
  		menuBar.add(file);
  		menuBar.add(edit);
  		menuBar.add(help);
  		
  		newWindow = new JMenuItem("New Window");
  		newWindow.setAccelerator( KeyStroke.getKeyStroke("control N"));
  		newWindow.setToolTipText("New port window");
  		newWindow.addActionListener(l);
  		
  		logOut = new JMenuItem("Log Out");
  		logOut.setAccelerator( KeyStroke.getKeyStroke("control W"));
  		logOut.setToolTipText("Log out");
  	    logOut.addActionListener(l);
  		
  		quit = new JMenuItem("Quit");
  		quit.setAccelerator( KeyStroke.getKeyStroke("control Q"));
  		quit.setToolTipText("Exit port");
  		quit.addActionListener(l);
  		
  		
  		
  		author = new JMenuItem("Author");
  		author.setToolTipText("HUHUHUHU");
  		author.addActionListener(l);
  		
  		customIce = new JMenuItem("Customize...");
  		customIce.setAccelerator( KeyStroke.getKeyStroke("control Z"));
  		customIce.addActionListener(l);
  		
  		h = new JMenuItem("Help");
  		h.setAccelerator( KeyStroke.getKeyStroke("F1"));
  		h.addActionListener(l);
  		
  		help.add(h);
  		
  		file.add(newWindow);
  		file.addSeparator();
  		file.add(author);
  		file.addSeparator();
  		file.add(logOut);
  		file.addSeparator();
  		file.add(quit);
  		
  		soundSet = new JMenuItem("Sound Setting");
  		soundSet.addActionListener(l);
  		
  		edit.add(customIce);
  		edit.add(soundSet);
  		
  		this.setJMenuBar(menuBar);
  		
  		
		
	}
	public static void main(String[]a){
		ICEFrame f = new ICEFrame();
		
	}
	public class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) e.getSource();
	   		if(source==quit){
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}	
	   			quitDialog();
	   		}else if(source==newWindow){
	   			test1 x = new test1("keyartihi (800x411).jpg");
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
	   			
	   		}else if(source==author){
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}	
	   			showAuthor();
	   		}else if(source==logOut){
	   			logOut();
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
	   		}else if(source==soundSet){
	   			soundSet();
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
	   		}else if(source==h){
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
	   			showHelpDialog();	
	   			System.out.println("hey");
	   		}else if(source==customIce){
	   			createCustomFrame();
	   			try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
	   		}
			
			
		}
	
		

		private void createCustomFrame() {
			try {
				Customize c = new Customize();
				c.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		private void showHelpDialog() {
			Help h;
			try {
				h = new Help();
				h.setVisible(true);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		private void soundSet() {
			
			bgf.setSize(300,100);
			bgf.setVisible(true);
			
		    
			
		}

		private void logOut() {
			test1.immigration.logout();
			dispose();
			test1 x = new test1("keyartihi (800x411).jpg");
			
			
			
		}

		private void showAuthor() {
			About a = new About();
	   		try {
				a.initUI();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		
	   		
		}

		private void quitDialog() {
			//test1.immigration.logout();
			dispose();
			System.exit(0);
			
		}
		
		
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			if(source==yell){
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("button-10.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl =
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
					clip.start();
					} catch (Exception error) {
					error.printStackTrace();
					}	
				yellMethod();
				//System.out.println(chatField.getText());
			}else if(source==talk){
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("button-10.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl =
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
					clip.start();
					} catch (Exception error) {
					error.printStackTrace();
					}	
				talkMethod();
				//System.out.println(chatField.getText());
			}else if(source==zoomIn){
				zIn();
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("button-10.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
					clip.start();
					} catch (Exception error) {
					error.printStackTrace();
					}
			}else if(source==zoomOut){
				zOut();
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("button-10.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl =
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
					clip.start();
					} catch (Exception error) {
					error.printStackTrace();
					}	
			}else if(source==refresh){
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("button-10.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl =
					(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
					clip.start();
					} catch (Exception error) {
					error.printStackTrace();
					}	
			}else if(source==talkDuration){
				try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
				createTalkSet();
				
			}else if(source==logOutB){
				try {
	   				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
	   				new File("button-10.wav"));
	   				Clip clip = AudioSystem.getClip();
	   				clip.open(audioInputStream);
	   				FloatControl gainControl =
	   				(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	   				/*gainControl.setValue(-10.0f);*/ // Reduce volume by 10 decibels.
	   				clip.start();
	   				} catch (Exception error) {
	   				error.printStackTrace();
	   				}
				test1.immigration.logout();
				dispose();
				test1 x = new test1("keyartihi (800x411).jpg");
			}
			
		}
		private void yellMethod() {
			
			msg = chatField.getText();
			if(msg.length()<10){
			test1.immigration.yell(msg);
			}else{
				msg = msg.substring(0, 10);
				chatField.setText(msg);
				test1.immigration.yell(msg);
				
			}
			
		}
		private void talkMethod() {
			msg = chatField.getText();
			if(msg.length()<100){
			test1.immigration.talk(msg);
			}else{
				msg = msg.substring(0, 100);
				chatField.setText(msg);
				test1.immigration.talk(msg);
				
			}
			
		}
		private void createTalkSet() {
			JDialog soundControl=new JDialog();
			soundControl.setLocation(270, 380);
			soundControl.setVisible(true);
			soundControl.setSize(400, 100);
			soundControl.setAlwaysOnTop(true);
			soundControl.setLayout(new GridLayout(2,3,10,10));
			talkSlider=new JSlider();
			JLabel BGM=new JLabel("Duration:");
			soundControl.add(BGM);
			soundControl.add(talkSlider);
			soundControl.setVisible(true);
		}
	}
	public class TextFieldListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField source = (JTextField) e.getSource();
			if(source==chatField){
				if(chatField.getText().length() > 10) 
				chatField.setText(chatField.getText().substring(0, 100 ));
				msg = chatField.getText();
				System.out.println(msg);
			}
			
		}
		
	}
	public void zIn(){
		if(i>3)	 return;
		i+= 0.1;
		System.out.println(i);
		iso.zoom(i);
	}
	public void zOut(){
		if(i<1) return;
		i-= 0.1;
		iso.zoom(i);
	}
	
  
	

	
	
}

