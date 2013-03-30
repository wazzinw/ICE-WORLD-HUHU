import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;


public class CustomizationWindow extends JFrame {

	private JPanel contentPane = new CustomPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomizationWindow frame = new CustomizationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomizationWindow() {
		setTitle("Customize ICE-tizen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,600);
		setContentPane(contentPane);
		//contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		
	}

}
