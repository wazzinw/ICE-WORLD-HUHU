package ICEPortFrame;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ICEPort extends JFrame implements ActionListener{
	JMenuBar menuBar;
	JMenuItem newWindow,logOut,quit,soundSet,programSet,customIce,author,h;
	JMenu file,edit,help;
	
	public ICEPort(){
		
		setSize(800,600);
		menu();
		//setVisible(true);
	}
	
	public static void main(String[]args){
		//ICEPort a = new ICEPort();
		//a.setVisible(true);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }

			private void createAndShowGUI() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				ICEPort window = new ICEPort();
				window.setVisible(true);
				
			}
        });
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
		newWindow.addActionListener(this);
		
		logOut = new JMenuItem("Log Out");
		logOut.setAccelerator( KeyStroke.getKeyStroke("control W"));
		logOut.setToolTipText("Log out");
	    logOut.addActionListener(this);
		
		quit = new JMenuItem("Quit");
		quit.setAccelerator( KeyStroke.getKeyStroke("control Q"));
		quit.setToolTipText("Exit port");
		quit.addActionListener(this);
		
		
		soundSet = new JMenuItem("Sound Setting");
		soundSet.addActionListener(this);
        
        
		programSet = new JMenuItem("Program Settings");
		programSet.setToolTipText(":P");
		programSet.addActionListener(this);
		
		author = new JMenuItem("Author");
		author.setToolTipText("HUHUHUHU");
		author.addActionListener(this);
		
		customIce = new JMenuItem("Customize ICE-tizen");
		customIce.setAccelerator( KeyStroke.getKeyStroke("control Z"));
		customIce.addActionListener(this);
		
		h = new JMenuItem("Help");
		h.setAccelerator( KeyStroke.getKeyStroke("F1"));
		h.addActionListener(this);
		
		help.add(h);
		
		file.add(newWindow);
		file.addSeparator();
		file.add(logOut);
		file.addSeparator();
		file.add(author);
		file.add(quit);
		
		edit.add(soundSet);
		edit.add(programSet);
		edit.add(customIce);
		
		this.setJMenuBar(menuBar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();
		if(source==quit){
			//logOut();
			System.exit(0);
		}else if(source==newWindow){
			ICEPort a = new ICEPort();
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

	private void showAuthor() {
		// TODO Auto-generated method stub
		
	}

	private void logOut() {
		// TODO Auto-generated method stub
		
	}
}
