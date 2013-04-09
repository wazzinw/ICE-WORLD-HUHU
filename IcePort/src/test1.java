
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class test1 extends JFrame {

	/**
	 * Launch the application.
	 */
	
	
	public void changeToAlien(){
		this.setVisible(false);
		test1 a = new test1("venomapip (1024x476) (800x372).jpg",1);
		//this.setContentPane(a);
	}
	
	public void changeToInhab(){
		this.setVisible(false);
		test1 b = new test1("keyartihi (800x411).jpg");
		//this.setContentPane(b);
	}
	
	 public static void main(String [] args){
		  test1 mainFrame = new test1("keyartihi (800x411).jpg");
		  mainFrame.setVisible(true); 
		 }
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					test1 frame = new test1("keyartihi (800x411).jpg");					
//					frame.setVisible(true);
//					frame.setResizable(false);
//					frame.setLocation(450, 200);
//					} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	 public test1(String r,int i){
		 this.setVisible(true);
			this.setResizable(false);
			this.setLocation(295, 180);
			
			String picname = r;
			BufferedImage myImage;
			
				try {
					myImage = ImageIO.read(new File(picname));
					   this.setContentPane(this.new ImagePanel(myImage));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			
			
			
			//getContentPane().setBackground(new Color(176, 224, 230));
			SpringLayout springLayout = new SpringLayout();
			getContentPane().setLayout(springLayout);
			setSize(800,394);
			
			JButton btnLogin = new JButton("Login");
			springLayout.putConstraint(SpringLayout.WEST, btnLogin, 148, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, -112, SpringLayout.SOUTH, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, btnLogin, -572, SpringLayout.EAST, getContentPane());
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			getContentPane().add(btnLogin);
			
		
			
			final JComboBox comboBox = new JComboBox();
			springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 17, SpringLayout.SOUTH, comboBox);
			springLayout.putConstraint(SpringLayout.NORTH, comboBox, 168, SpringLayout.NORTH, getContentPane());
			springLayout.putConstraint(SpringLayout.WEST, comboBox, 117, SpringLayout.WEST, getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, comboBox, -546, SpringLayout.EAST, getContentPane());
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String loginas = (String) comboBox.getSelectedItem();
					if(loginas=="Alien"){
						changeToAlien();
					}

					if(loginas=="Inhabitant"){
						changeToInhab();
					}

					
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Alien", "Inhabitant"}));
			getContentPane().add(comboBox);
	 
	 }
	
	public test1(String r) {
		
						
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(295, 180);
		
		String picname = r;
		BufferedImage myImage;
		
			try {
				myImage = ImageIO.read(new File(picname));
				   this.setContentPane(this.new ImagePanel(myImage));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		
		
		
		//getContentPane().setBackground(new Color(176, 224, 230));
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		setSize(800,394);
		
		JLabel lblUsername = new JLabel("Username");
		getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 245, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -20, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 43, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		JTextField txtHuhuHu = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtHuhuHu, -3, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, txtHuhuHu, 26, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, txtHuhuHu, -546, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtHuhuHu);
		txtHuhuHu.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 210, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 28, SpringLayout.EAST, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin, 0, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -444, SpringLayout.EAST, getContentPane());
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		getContentPane().add(btnLogin);
		
		JPasswordField passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -546, SpringLayout.EAST, getContentPane());
		getContentPane().add(passwordField);
		
	
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String loginas = (String) comboBox.getSelectedItem();
				if(loginas=="Alien"){
					changeToAlien();
				}

				if(loginas=="Inhabitant"){
					changeToInhab();
				}

				
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.SOUTH, comboBox, -20, SpringLayout.NORTH, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -546, SpringLayout.EAST, getContentPane());
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Inhabitant","Alien" }));
		getContentPane().add(comboBox);
		
		
		
	}
	
	class ImagePanel extends JComponent {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, null);
	    }
	}
}
