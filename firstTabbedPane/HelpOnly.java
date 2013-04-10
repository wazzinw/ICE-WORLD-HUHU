// ontiretse keipidile
import java.awt.*;

import javax.swing.*;

import java.awt.Dialog;
import java.net.MalformedURLException;

import javax.swing.JDialog;
//import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HelpOnly extends JDialog{
	
	
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	
	public HelpOnly()  throws MalformedURLException {
		
		JDialog dialog = new JDialog((Frame) null,"Help Dialog" );
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//JFrame helpFrame =new JFrame("Help");
		//helpFrame.setTitle("Tabbeb help window");
		
		// make the main panel to put all other in
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		// create each panel and its contents
		createAllPage();
		
		// make the tabbeb pane and add the content
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Control your ICE-tizen!",panel1);
		tabbedPane.addTab("Chatting with Friends!",panel2);
		tabbedPane.addTab("Exchange Files with your Friends!",panel3);
		tabbedPane.addTab("Customizes Your ICE-tizen!",panel4);
		tabbedPane.addTab("Experienced the Weather Changes!",panel5);
		tabbedPane.addTab("Log in as an Alien or ICE-tizen!",panel6);
		tabbedPane.addTab("Setting the Sound!",panel7);
		tabbedPane.addTab("Zoom Function!",panel8);
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		topPanel.add(tabbedPane,BorderLayout.CENTER);
		dialog.getContentPane().add(topPanel);
	
		dialog.setSize( 600, 500 );
		dialog.setBackground( Color.gray );
		dialog.setVisible(true);
	}
	public static void main(String[]a)  {
		//HelpOnly j = new HelpOnly();
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new HelpOnly();
                } catch ( MalformedURLException e) {
                   
                    e.printStackTrace();
                }
            }
        });
	}
public  void createAllPage(){
	// using html here always for pics and other things to be put into buttons
panel1 = new JPanel();
JButton b = new JButton("<html>You can control your <br> ICE-tizen by clicking on the area in<br>the ICE Port that you want your ICE-tizen to move to.</html>");
panel1.add(b);
// second panel
panel2 = new JPanel();
JButton b2 = new JButton("<html>You can chat with friends by<br> typing your messages on the Chat Box.<br>By using Yelling<br>everyone will definitely see your<br>message since it covers<br>50% of the screen!</html>");
panel2.add(b2);
// panel 3
panel3 = new JPanel();

JButton b3= new JButton("<html>**AS FOR THE SENDER**<br> You can send file to your friend by clicking<br>at" +
		" your friend’s ICE-tizen<br>,then the window to select file<br>will popup on the screen<br><br>**AS FOR " +
		"THE RECEIVER** <br>You will get the prompt popup whether<br>you want to accept file or not</html>");

panel3.add(b3);
// panel 4
panel4 = new JPanel();

JButton b4 = new JButton("<html>You can use the Customized Function<br> to change the looks of your ICE-tizen <br>" +
		"Try on all of Costumes we prepare for you!</html>");


panel4.add(b4);
// panel 5
 panel5 = new JPanel();
	JButton b1 = new JButton("<html>The Weather in the ICE World <br> can be either Sunny," +
			" Cloudy,<br> Raining, or Snowing.<br><br>Prepare to face all of them, Huhu!!</html>");
	panel5.add(b1);
// panel 6
	panel6 = new JPanel();
	JButton b6 = new JButton("<html>You can log in to the ICE-World without<br> Username & Password but you will be treated<br> as an Alien!<br><br>Log in to the " +
			"ICE-World with Username & Password<br>you will be treated as an Inhabitant<br> or in other word, ICE-tizen!</html>");
	panel6.add(b6);
// panel 7
	panel7 = new JPanel();
	JButton b7 = new JButton("<html>You can adjust the Volume of Background<br> Music (BGM) or Sound Effect (SFX) <br>through Sound Setting</html>");
	panel7.add(b7);
// panel 8
	panel8 = new JPanel();
	JButton b8 = new JButton("<html>You can use the zoom function by<br> pressing [Ctrl +] to Zoom In and<br> [Ctrl -] to " +
			"Zoom Out<br>Use “Zoom to Area” by dragging a <br>rectangular shape and<br> Zoom when mouse released.</html>");
	
	panel8.add(b8);
	
}

	}

