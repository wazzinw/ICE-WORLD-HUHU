package ICEPort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;




public class Frame extends JFrame implements MouseMotionListener,MouseListener{
	private JTextField chatField;
	JButton zoomIn,zoomOut;
	double i=1;
	GridProjection iso = new GridProjection();
	ZoomOut z = new ZoomOut();
	ZoomIn q = new ZoomIn();
	JScrollPane scroll = new JScrollPane();
	JMenuBar menuBar;
	JMenuItem newWindow,logOut,quit,soundSet,programSet,customIce,author,h;
	JMenu file,edit,help;
	ButtonListener l = new ButtonListener();
	
	
	
	public Frame() {
		setSize(800,600);
		setGUI();
		addMouseMotionListener(this);
		addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	private void setGUI() {
		//Scroll Pane
		scroll = new JScrollPane(iso);
		scroll.setWheelScrollingEnabled(true);
		this.add(scroll,BorderLayout.CENTER);
		
		//User
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new URL("http://iceworld.sls-atl.com/graphics//body//blue.png"));
		} catch (IOException e) {
		}
		Image resize = getScaledImage(img,30,30);
		JLabel pic = new JLabel(new ImageIcon(resize));
		///////////////////////////////////////
		JPanel functionPanel = new JPanel();
		getContentPane().add(functionPanel, BorderLayout.SOUTH);
		functionPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel chatPanel = new JPanel();
		functionPanel.add(chatPanel, BorderLayout.SOUTH);
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.X_AXIS));
		
		chatField = new JTextField();
		chatPanel.add(chatField);
		chatField.setText("text");
		chatField.setColumns(10);
		
		JButton btnTalk = new JButton("Talk");
		chatPanel.add(btnTalk);
		
		JButton btnYell = new JButton("Yell");
		chatPanel.add(btnYell);
		
		JPanel zoomPanel = new JPanel();
		functionPanel.add(zoomPanel, BorderLayout.EAST);
		zoomPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button = new JButton("+");
		zoomPanel.add(button);
		button.addActionListener(q);

		JButton zoomOut = new JButton("-");
		zoomOut.addActionListener(z);
		zoomPanel.add(zoomOut);
	
		functionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		menu();
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
  		
  		
  		soundSet = new JMenuItem("Sound Setting");
  		soundSet.addActionListener(l);
          
          
  		programSet = new JMenuItem("Program Settings");
  		programSet.setToolTipText(":P");
  		programSet.addActionListener(l);
  		
  		author = new JMenuItem("Author");
  		author.setToolTipText("HUHUHUHU");
  		author.addActionListener(l);
  		
  		customIce = new JMenuItem("Customize ICE-tizen");
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
  		
  		edit.add(soundSet);
  		edit.addSeparator();
  		edit.add(programSet);
  		edit.addSeparator();
  		edit.add(customIce);
  		
  		this.setJMenuBar(menuBar);
  		
  		
		
	}
	public static void main(String[]a){
		Frame f = new Frame();
		
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem) e.getSource();

	   		if(source==quit){
	   			quitDialog();
	   		}else if(source==newWindow){
	   			IsometricProjection a = new IsometricProjection();
	   			a.setVisible(true);
	   		}else if(source==author){
	   			showAuthor();
	   		}else if(source==logOut){
	   			logOut();
	   		}else if(source==soundSet){
	   			soundSet();
	   		}else if(source==help){
	   			showHelpDialog();
	   		}else if(source==customIce){
	   			createCustomFrame();
	   		}else if (source == programSet){
	   			programSet();
	   		}
			
			
		}
		private void programSet() {
			// TODO Auto-generated method stub
			
		}

		private void createCustomFrame() {
			// TODO Auto-generated method stub
			
		}

		private void showHelpDialog() {
			// TODO Auto-generated method stub
			
		}

		private void soundSet() {
			// TODO Auto-generated method stub
			
		}

		private void logOut() {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}
		
		
		
	}
	class ZoomIn implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			zIn();
		}
		public void zIn(){
			if(i>3)	 return;
			i+=0.1;
			System.out.println(i);
			iso.zoom(i);
		}
	}
	
	public class ZoomOut implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			zOut();
		}
		public void zOut(){
			if(i<1) return;
			i-=0.1;
			iso.zoom(i);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
	

}
