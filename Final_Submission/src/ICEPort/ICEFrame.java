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
import java.util.concurrent.CountDownLatch;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Fetch.FetchTask;
import Fetch.Fetching;
import Login.Customize;
import Login.test1;

import Weather.Snow;
import Fetch.*;




public class ICEFrame extends JFrame implements MouseMotionListener,MouseListener{
	private JPanel functionPanel,chatPanel,zoomPanel,setPanel;
	private JTextField chatField;
	private JButton zoomIn,zoomOut,talk,yell,refresh,talkDuration,logOutB;
	private double i=1;
	private GridProjection iso = new GridProjection();
	private JScrollPane scroll = new JScrollPane();
	private JMenuBar menuBar;
	private JMenuItem newWindow,logOut,quit,soundSet,customIce,author,h,ip;
	private JMenu file,edit,help;
	private MenuListener l = new MenuListener();
	private ButtonListener b = new ButtonListener();
	//private TextFieldListener t = new TextFieldListener();
	private String msg = null;
	private JSlider talkSlider;
	private JLayeredPane layeredPane = new JLayeredPane();
	private TopPane top;
	private BottomPane bottom = new BottomPane();
	private BGM bg;
	private SFX sf;
	JFrame bgf = new JFrame();
	public static int TALK_DURATION = 5;
	static CountDownLatch c = new CountDownLatch(1);
	public static Fetching s;
	Timer timer;
	
	public ICEFrame() {
		setSize(800,600);
		fetchState();
		//System.out.println(s.weather);
		setGUI();
		addMouseMotionListener(this);
		addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	private void fetchState() {
		CountDownLatch latch = new CountDownLatch(1);
		s = new Fetching(c);
		Thread c = new Thread(new FetchTask(s,2000,latch));
		c.start();
		
		try{
            latch.await();  //main thread is waiting on CountDownLatch to finish
            System.out.println("All services are up, Application is starting now");
       }catch(InterruptedException ie){
           System.out.println("Freak");
       }

}

	private void setGUI() {
		
		File bgm = new File("Track1.wav");
		bg = new BGM(bgm);
		bgf.add(bg);
		
		top = new TopPane(s.weather);
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
		//timer = new Timer();
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
  		
  		ip = new JMenuItem("IP Info");
  		//h.setAccelerator( KeyStroke.getKeyStroke("F1"));
  		ip.addActionListener(l);
  		
  		help.add(h);
  		help.add(ip);
  		
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
	   			File sfx = new File("button-10.wav");
	   			sf = new SFX(sfx);
	   			quitDialog();
	   			
	   		}else if(source==newWindow){
	   			File sfx = new File("button-1.wav");
	   			sf = new SFX(sfx);
	   			test1 x = new test1("keyartihi (800x411).jpg");
	   			
	   		}else if(source==author){
	   			File sfx = new File("button-1.wav");
	   			sf = new SFX(sfx);
	   			showAuthor();
	   		}else if(source==logOut){
	   			File sfx = new File("button-3.wav");
	   			sf = new SFX(sfx);
	   			logOut();
	   		}else if(source==soundSet){
	   			soundSet();
	   			File sfx = new File("button-1.wav");
	   			sf = new SFX(sfx);
	   		}else if(source==h){
	   			File sfx = new File("button-1.wav");
	   			sf = new SFX(sfx);
	   			showHelpDialog();
	   			System.out.println("hey");
	   		}else if(source==customIce){
	   			createCustomFrame();
	   			File sfx = new File("button-1.wav");
	   			sf = new SFX(sfx);
	   		}else if(source==ip){
	   			IPFrame f = new IPFrame();
	   			f.setVisible(true);
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

		
			
		}private void quitDialog() {
			int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "User Confirmation", JOptionPane.YES_NO_OPTION);
			if (confirmed == JOptionPane.YES_OPTION)
				{
				test1.immigration.logout();
			System.exit(0);
			dispose();
			//System.exit(0);	
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
		// TODO Auto-generated method stub
		
	}
	public class ButtonListener implements ActionListener,ChangeListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			if(source==yell){
				File sfx = new File("MSN.wav");
	   			sf = new SFX(sfx);
				yellMethod();
				
			}else if(source==talk){
				File sfx = new File("MSN.wav");
	   			sf = new SFX(sfx);
				talkMethod();
			}else if(source==zoomIn){
				zIn();
				File sfx = new File("button-9.wav");
	   			sf = new SFX(sfx);
			}else if(source==zoomOut){
				zOut();
				File sfx = new File("button-9.wav");
	   			sf = new SFX(sfx);	
			}else if(source==refresh){
				File sfx = new File("button-3.wav");
	   			sf = new SFX(sfx);		
			}else if(source==talkDuration){
				File sfx = new File("button-3.wav");
	   			sf = new SFX(sfx);	
				createTalkSet();
			}else if(source==logOutB){
				File sfx = new File("button-3.wav");
	   			sf = new SFX(sfx);
				test1.immigration.logout();
				dispose();
				test1 x = new test1("keyartihi (800x411).jpg");
			}
			
		}
		private void yellMethod() {
			
			msg = chatField.getText();
			if(msg.length()<10){
			test1.immigration.yell(msg);
			iso.yellAnimate(msg);
			
			}else{
				msg = msg.substring(0, 10);
				chatField.setText(msg);
				test1.immigration.yell(msg);
				iso.yellAnimate(msg);
			}
			
		}
		private void talkMethod() {
			msg = chatField.getText();
			if(msg.length()<100){
			test1.immigration.talk(msg);
			iso.talkAnimate(msg);
			}else{
				msg = msg.substring(0, 100);
				chatField.setText(msg);
				test1.immigration.talk(msg);
				
				iso.talkAnimate(msg);
			}
			
		}
		private void createTalkSet() {
			JDialog soundControl=new JDialog();
			soundControl.setLocation(270, 380);
			soundControl.setVisible(true);
			soundControl.setSize(400, 100);
			soundControl.setAlwaysOnTop(true);
			soundControl.setLayout(new GridLayout(2,3,10,10));
			talkSlider=new JSlider(JSlider.HORIZONTAL,
                    1, 10, 2);
			talkSlider.setMajorTickSpacing(2);
			talkSlider.addChangeListener(this);
			JLabel BGM=new JLabel("Duration:");
			soundControl.add(BGM);
			soundControl.add(talkSlider);
			soundControl.setVisible(true);
		}
		@Override
		public void stateChanged(ChangeEvent e) {

		    JSlider source = (JSlider)e.getSource();
		    if (source.getValueIsAdjusting()) {
		        TALK_DURATION = (int)source.getValue();
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
	
	
	
		        
		    
		

		
	}
	
  
	

	
	
}

