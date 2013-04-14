package ICEPort;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BottomPane extends JPanel {
	
	public BottomPane(){
		setSize(800,600);
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.red);
		g.drawRect(90, 90, 200, 200);
	}


}
