// Wasin.W 4218759
import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;

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
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;



public class test1 extends JFrame {

	/**
	 * Launch the application.
	 */
	JPasswordField passwordField;
	String password;
	static ICEWorldImmigration immigration;
	int numdat= 0;
	NameCheck[] check = new NameCheck[200];
	JScrollPane scrollPane;
	final JList list = new JList();
	String[]values;
	
	
	

	    
    
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

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	
	public test1(String r) {
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		


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

		final JTextField txtHuhuHu = new JTextField();
		txtHuhuHu.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				scrollPane.setVisible(true);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, txtHuhuHu, -3, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, txtHuhuHu, 26, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, txtHuhuHu, -546, SpringLayout.EAST, getContentPane());
		getContentPane().add(txtHuhuHu);
		txtHuhuHu.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, -3, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -546, SpringLayout.EAST, getContentPane());
		getContentPane().add(passwordField);

		JButton btnLogin1 = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin1, 18, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin1, 148, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLogin1, 70, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, btnLogin1, -572, SpringLayout.EAST, getContentPane());

		btnLogin1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i;
				boolean c = false;
				
				String user = txtHuhuHu.getText();
				String pass = new String(passwordField.getPassword());


				TestMyIcetizen tester = new TestMyIcetizen();

				// *** Strictly use the ICE Port ID assigned to your group

				tester.setIcePortID(251);
				tester.setUsername(user);



				tester.setListeningPort(200);
				IcetizenLook look = new IcetizenLook();
				look.gidB = "B001";
				look.gidS = "S001";
				look.gidH = "H001";
				look.gidW = "W001";


				immigration = new ICEWorldImmigration(tester);

				if (immigration.login(pass))
				{
					immigration.walk(15,88);
					System.out.println("Login OK");
					dispose();

					splashScreen sc = new splashScreen();
					Thread a = new Thread(sc);
					a.start();
					
					try {
					
						Scanner inn = new Scanner(new File("SuggesttedUser.txt"));
	
						BufferedWriter outt = new BufferedWriter(new FileWriter("SuggesttedUser.txt",true));
 
					    while(inn.hasNext()){
					    	//System.out.println(inn.nextLine());
					    	if(user.equals(inn.nextLine())){
				    		c = true;
					    	}
					    }
					    if(!c){
					    	outt.write(user);
					    	outt.newLine();
					  
					    }
					    
					    inn.close();
					    outt.close();
					    
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
					

				}else{
					System.out.println("huhu");
					for( i =0;i<numdat;i++){
						
						if(user.equals(check[i].name)){						
							check[i].cnt++;
							System.out.println(check[i].name + check[i].cnt );
							if(check[i].cnt==3){
								System.out.println("FUCK U!!");
							}
							break;
						}
					}
					if(i==numdat){
						//System.out.println("i = "+i);
						
						NameCheck a = new NameCheck(user);

						check[numdat]= a;
						System.out.println(check[numdat].name + check[numdat].cnt );
						numdat++;
						
						

					}
				}
			}


		});

		getContentPane().add(btnLogin1);


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
		
		
		try {
			int line=0;
			Scanner inn = new Scanner(new File("SuggesttedUser.txt"));

		    while(inn.hasNext()){
		    inn.nextLine();
		    line++;
		    }	    
		    inn.close();
		    
		    inn = new Scanner(new File("SuggesttedUser.txt"));
		    values = new String[line];
	    
		    for(int k =0;k<line;k++){
		    	values[k]= inn.nextLine();
		    }
			    inn.close();
		    
		   
		    
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {

		           String selectedItem = (String) list.getSelectedValue();	
		           txtHuhuHu.setText(selectedItem);

		         }
		    }
		};
		
		list.addMouseListener(mouseListener);
		
		list.setModel(new AbstractListModel() {
			
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane = new JScrollPane(list);
		
		
		springLayout.putConstraint(SpringLayout.NORTH,scrollPane, 0, SpringLayout.NORTH, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.EAST, txtHuhuHu);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 13, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 131, SpringLayout.EAST, txtHuhuHu);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);



	}
	
	
	public test1(String r,int i){
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(295, 180);

		String picname = r;
		BufferedImage myImage;

		try {
			myImage = ImageIO.read(new File(picname));
			this.setContentPane(/*this.*/new ImagePanel(myImage));
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
				//................


				TestMyIcetizen tester = new TestMyIcetizen();

				// *** Strictly use the ICE Port ID assigned to your group

				tester.setIcePortID(251);




				tester.setListeningPort(200);
				IcetizenLook look = new IcetizenLook();
				look.gidB = "B001";
				look.gidS = "S001";
				look.gidH = "H001";
				look.gidW = "W001";


				immigration = new ICEWorldImmigration(tester);

				if (immigration.loginAlien())
				{
					immigration.walk(15,88);
					System.out.println("Login OK");
					dispose();

					splashScreen sc = new splashScreen();
					Thread a = new Thread(sc);
					a.start();

				}



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
