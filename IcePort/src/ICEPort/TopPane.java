package ICEPort;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class TopPane extends JPanel {
	
	public TopPane(){
		setSize(800,600);
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.red);
		g.drawRect(30, 30, 200, 200);
	}

}
