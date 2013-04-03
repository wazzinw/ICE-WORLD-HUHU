package ColorBox;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JApplet;
import javax.swing.JFrame;



public class jj extends JApplet {
  private boolean isApplet = true;

  private int grid = 12;

  private int pause = 50;

  public void init() {
    // Get parameters from Web page:
    if (isApplet) {
      String gsize = getParameter("grid");
      if (gsize != null)
        grid = Integer.parseInt(gsize);
      String pse = getParameter("pause");
      if (pse != null)
        pause = Integer.parseInt(pse);
    }
    Container cp = getContentPane();
    cp.setLayout(new GridLayout(grid, grid));
    for (int i = 0; i < grid * grid; i++)
      cp.add(new CBox(pause));
  }

  public static void main(String[] args) {
    jj applet = new jj();
    applet.isApplet = false;
    if (args.length > 0)
      applet.grid = Integer.parseInt(args[0]);
    if (args.length > 1)
      applet.pause = Integer.parseInt(args[1]);
    run(applet, 500, 400);
  }

  public static void run(JApplet applet, int width, int height) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(applet);
    frame.setSize(width, height);
    applet.init();
    applet.start();
    frame.setVisible(true);
  }
}
