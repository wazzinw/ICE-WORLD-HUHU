// ontiretse keipidile
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.awt.Dialog;
import java.net.MalformedURLException;

import javax.swing.JDialog;
//import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HelpOnly extends JDialog{
	
	JTabbedPane tabbedPane = new JTabbedPane();
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
 final String website = "http://iceworld.sls-atl.com/icetizen";
JButton bt = new JButton("click for more on ICE-tizen");
bt.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
            openWindow.openURL(website);
    }
});

 JLabel b = new JLabel( "<html>You can control your <br><a  href = http://iceworld.sls-atl.com/api/explore  target =_blank> ICE-tizen </a>  by clicking on the area in<br>the ICE Port that you want your ICE-tizen to move to.</html>");
 
panel1.add(b);
panel1.add(bt);

// second panel
panel2 = new JPanel();
JLabel b2 = new JLabel("<html>You can chat with friends by<br> typing your messages on the Chat Box.<br>By using Yelling<br>everyone will definitely see your<br>message since it covers<br>50% of the screen!</html>");
panel2.add(b2);
// panel 3
panel3 = new JPanel();

JLabel b3= new JLabel("<html>**AS FOR THE SENDER**<br> You can send file to your friend by clicking<br>at" +
		" your friend’s ICE-tizen<br>,then the window to select file<br>will popup on the screen<br><br>**AS FOR " +
		"THE RECEIVER** <br>You will get the prompt popup whether<br>you want to accept file or not</html>");

panel3.add(b3);
// panel 4
panel4 = new JPanel(new BorderLayout());
JButton bt4 = new JButton("go to Control ICE-tizen tab");
bt4.setSize(new Dimension (10,15));
bt4.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	  tabbedPane.setSelectedIndex(0);
    }
});

JLabel b4 = new JLabel("<html>You can use the Customized Function<br> to change the looks of" +
		" your ICE-tizen <br> </b>Try on all of Costumes we prepare for you! <br></br> Below is a uncuztomised ICE-tizen</html>");
ImageIcon icePic = new ImageIcon("ICEpale.png");
 JLabel b41 = new JLabel(icePic);

panel4.add(b4,BorderLayout.NORTH);
panel4.add(bt4, BorderLayout.SOUTH);
panel4.add(b41,BorderLayout.WEST);
// panel 5
 panel5 = new JPanel();
 final String web = "http://iceworld.sls-atl.com/iceworld";
 JButton bt5 = new JButton("click for more on ICE-world");
 bt5.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
             openWindow.openURL(web);
     }
 });
	JLabel b1 = new JLabel("<html>The Weather in the ICE World <br> can be either Sunny," +
			" Cloudy,<br> Raining, or Snowing.<br><br>Prepare to face all of them, Huhu!!</html>");
	
	panel5.add(b1);
	panel5.add(bt5);
// panel 6
	JButton bt6 = new JButton("go to Customization tab");
	bt6.setSize(new Dimension (10,15));
	bt6.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	  tabbedPane.setSelectedIndex(3);
	    }
	});

	panel6 = new JPanel();
	JLabel b6 = new JLabel("<html>You can log in to the ICE-World without<br> Username & Password but you will be treated<br> as an Alien!<br><br>Log in to the " +
			"ICE-World with Username & Password<br>you will be treated as an Inhabitant<br> or in other word, ICE-tizen!</html>");
	panel6.add(b6);
	panel6.add(bt6);
// panel 7
	panel7 = new JPanel();
	JLabel b7 = new JLabel("<html>You can adjust the Volume of Background<br> Music (BGM) or Sound Effect (SFX) <br>through Sound Setting</html>");
	panel7.add(b7);
// panel 8
	panel8 = new JPanel();
	JLabel b8 = new JLabel("<html>You can use the zoom function by<br> pressing [Ctrl +] to Zoom In and<br> [Ctrl -] to " +
			"Zoom Out<br>Use “Zoom to Area” by dragging a <br>rectangular shape and<br> Zoom when mouse released.</html>");
	
	panel8.add(b8);

}
}
class openWindow{
	
    public static void openURL(String url) {
    	
            String osName = System.getProperty("os.name");
            try {
                    if (osName.startsWith("Windows"))
                            Runtime.getRuntime().exec(
                                            "rundll32 url.dll,FileProtocolHandler " + url);
                    else {
                            String[] browsers = { "firefox", "opera", "konqueror",
                                            "epiphany", "mozilla", "netscape" };
                            String browser = null;
                            for (int count = 0; count < browsers.length && browser == null; count++)
                                    if (Runtime.getRuntime().exec(
                                                    new String[] { "which", browsers[count] })
                                                    .waitFor() == 0)
                                            browser = browsers[count];
                            Runtime.getRuntime().exec(new String[] { browser, url });
                    }
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error in opening browser"
                                    + ":\n" + e.getLocalizedMessage());
            }
    }
}
	

