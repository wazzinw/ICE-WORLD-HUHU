package ICEPort;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;

import javax.swing.*;



public class IsometricProjection extends JFrame {
	JButton zoomIn,zoomOut;
	double i=1;
	GridProjection iso;
	ZoomOut z = new ZoomOut();
	ZoomIn q = new ZoomIn();
	JScrollPane scroll;
	JMenuBar menuBar;
	JMenuItem newWindow,logOut,quit,soundSet,programSet,customIce,author,h;
	JMenu file,edit,help;
	ButtonListener l = new ButtonListener();
	JPanel functions;
	
	public static void main(String[] a){
		IsometricProjection mainFrame = new IsometricProjection();
	}
	public IsometricProjection(){
		iso = new GridProjection();
		//this.setContentPane(iso);
		getContentPane().setLayout(new BorderLayout());
		this.setSize(800,600);
		
		setButton();
		setScroll();
		menu();
		
		this.setVisible(true);
	}
	private void setScroll() {
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(functions,BorderLayout.SOUTH);
		//scroll = new JScrollPane(topPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scroll.setBounds(0, 20, this.getSize().width, this.getSize().height);
		//scroll.getViewport().setView(iso);
		//this.add(scroll,BorderLayout.CENTER);
		topPanel.add(iso,BorderLayout.CENTER);
		getContentPane().add(topPanel);
		//repaint();
		
	}
	
	public void menu() {
		
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
public void setButton(){
		functions = new JPanel(null);
		functions.setBackground(Color.MAGENTA);
		functions.setBounds(100,400, 200, 200);
		zoomIn = new JButton("+");
		zoomOut = new JButton("-");
		//getContentPane().setLayout(null);
		zoomIn.setBounds(700, 500, 40, 40);
		zoomOut.setBounds(750, 500, 40, 40);
		zoomIn.addActionListener(new ZoomIn());
		zoomOut.addActionListener(new ZoomOut());
		//zoomIn.setMnemonic(KeyStroke.getKeyStroke("control -"));
		//zoomOut.setMnemonic(KeyEvent.VK_MINUS);
		
		functions.add(zoomIn);
		functions.add(zoomOut);
		//this.add(functions,BorderLayout.SOUTH);
		
	}

	public JComponent makeUI() {
	    
	    JScrollPane scroll = new JScrollPane(iso);
	    JViewport vport = scroll.getViewport();
	    MouseAdapter ma = new HandScrollListener();
	    vport.addMouseMotionListener(ma);
	    vport.addMouseListener(ma);
	    return scroll;
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
	static class HandScrollListener extends MouseAdapter {
	    private final Point pp = new Point();
	     public void mouseDragged(MouseEvent e) {
	      JViewport vport = (JViewport)e.getSource();
	      JComponent label = (JComponent)vport.getView();
	      Point cp = e.getPoint();
	      Point vp = vport.getViewPosition();
	      vp.translate(pp.x-cp.x, pp.y-cp.y);
	      label.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
	      //vport.setViewPosition(vp);
	      pp.setLocation(cp);
	    }

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

}