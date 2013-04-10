/* onti April 4 2013

*goal -> make an image appear when launched then another it disappers leaving a new layout  
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class splashScreen extends JWindow implements Runnable{
	// put the image into the run 
	 
	// make the image ,since it set but make it changeable then make it an icon
	String picname = "v2DispicableMe1.jpg";
	Image  huhuImage = Toolkit.getDefaultToolkit().getImage(picname);
	ImageIcon huhuIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(picname));
	//final int pauseTime ;
	public splashScreen (){
		
	 
	setSize(huhuIcon.getIconWidth(),huhuIcon.getIconHeight());
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((screenSize.width-getSize().width)/2,(screenSize.height-getSize().height)/2);
	setVisible(true);
	
	}
	
	public void paint(Graphics g){
		
	g.drawImage(huhuImage,0,0,this);
//	g.setColor(Color.white);
//	g.setFont();
//	g.drawString("ICE PORT LOADING.....",0,0);
	}


	
	
//	public static void main (String [] args){
//		splashScreen sc1 = new splashScreen();
//		try
//		{
//			Thread.sleep(4000);
//			sc1.dispose();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		JFrame frame=new JFrame("splash screen");  
//		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  		 
//		 frame.setSize(500,500);  
//		 frame.setVisible(true);
//		 frame.setResizable(false);
//			
//		
//	}
	
	public void run (){
		
		try
		{
			Thread.sleep(4000);
			dispose();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		final JFrame frame=new JFrame("splash screen");  
		
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  		 
		 frame.setSize(500,500);  
		 frame.setVisible(true);
		 frame.setResizable(false);
		 
		 JButton logout = new JButton("Logout");
		 logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					test1.immigration.logout();
					frame.dispose();

					
				}
			});
		 frame.add(logout);
			
		
	}
	
}
