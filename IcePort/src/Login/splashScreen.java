package Login;
/* onti April 4 2013

*goal -> make an image appear when launched then another it disappers leaving a new layout  
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ICEPort.ICEFrame;


public class splashScreen extends JWindow implements Runnable{
	// put the image into the run 
	 
	// make the image ,since it set but make it changeable then make it an icon
	String picname = "v2DispicableMe1.jpg";
	Image  huhuImage = Toolkit.getDefaultToolkit().getImage(picname);
	ImageIcon huhuIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(picname));
	
	
	public splashScreen (){
	setSize(huhuIcon.getIconWidth(),huhuIcon.getIconHeight());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((screenSize.width-getSize().width)/2,(screenSize.height-getSize().height)/2);
	setVisible(true);
	}
	
	public void paint(Graphics g){
		
		g.drawImage(huhuImage,0,0,this);
	}
	
	public void run (){
		
		try
		{
			Thread.sleep(4000);
			dispose();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		ICEFrame f = new ICEFrame();
		f.setVisible(true);
		
		
		
	}

}
