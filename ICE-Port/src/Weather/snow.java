package Weather;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

import javax.swing.JFrame;

public class snow extends JFrame {
	private static int DELAY = 3, width = 500, height = 500 ;
	private static BufferedImage buffer;
	private static Random generator;
	private static int[] flakex = new int[width], flakey = new int[width], flakeshade = new int[width];

	public snow() {
		
	}
  
	public void paint(Graphics g) {
    //Graphics bufferG = buffer.getGraphics();

	int i;
	for (i=0; i< width; i++) {
		// Erase the old position of the flake (set it to black)
		buffer.setRGB(flakex[i],flakey[i],0xff000000);
		if (flakey[i]>=(height-1) || buffer.getRGB(flakex[i],++(flakey[i])) != 0xff000000) {
			// The flake bumped into something!
			if (flakex[i]>0 && flakey[i]<(height-1) && buffer.getRGB(flakex[i]-1,flakey[i])==0xff000000) {
				// Roll to the left
				flakex[i]--;
			} else if (flakex[i]<(width-1) && flakey[i]<(height-1) && buffer.getRGB(flakex[i]+1,flakey[i])==0xff000000) {
				// Roll to the right
				flakex[i]++;
			} else {
				// Nowhere for flake to go! It settles..
				//buffer.setRGB(flakex[i],flakey[i]-1,0xffffffff);
				// Send flake back to the top
				flakex[i]=i;
				flakey[i]=0;
				flakeshade[i] = generator.nextInt(512);
			}
		}
		// Draw the in-flight flake
		
		/*if (flakeshade[i] <= 255) {
			buffer.setRGB(flakex[i],flakey[i],0xff000000+flakeshade[i]);
		} */if(flakeshade[i] < 255){
			buffer.setRGB(flakex[i],flakey[i],0xff000000 + 255 + (flakeshade[i] << 8) + (flakeshade[i] << 16));
		}
	}

    g.drawImage(buffer, 0, 0, this);
  }

  public void go() {
	generator = new Random();
    buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics bufferG = buffer.getGraphics();
    bufferG.setColor(Color.black);
    bufferG.fillRect(0, 0, width, height);

   
	int i;
	for (i=0; i<width; i++) {
		flakex[i] = i;
		do { // Try to get the flake to start in some black space
			flakey[i] = generator.nextInt(height-2);
		} while (buffer.getRGB(flakex[i],flakey[i]) != 0xff000000);
		flakeshade[i] = generator.nextInt(512);
	}

	// Schedule the animation
    TimerTask task = new TimerTask() {
      public void run() {
        repaint();
      }
    };
    Timer timer = new Timer();
    timer.schedule(task, 0, DELAY);
  }

  public static void main(String args[]) {
    snow f = new snow();
    f.setSize(width, height);
    f.setTitle("Snow");
    f.setVisible(true);
    f.go();
  }
}