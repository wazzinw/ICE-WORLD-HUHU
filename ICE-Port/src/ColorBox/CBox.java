package ColorBox;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CBox extends JPanel implements Runnable {
  private Thread t;

  private int pause;

  private static final Color[] colors = { Color.BLACK, Color.BLUE,
      Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
      Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
      Color.RED, Color.WHITE, Color.YELLOW };

  private static Random rand = new Random();

  private static final Color newColor() {
    return colors[rand.nextInt(colors.length)];
  }

  private Color cColor = newColor();

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(cColor);
    Dimension s = getSize();
    g.fillRect(0, 0, s.width, s.height);
  }

  public CBox(int pause) {
    this.pause = pause;
    t = new Thread(this);
    t.start();
  }

  public void run() {
    while (true) {
      cColor = newColor();
      repaint();
      try {
        t.sleep(pause);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

 