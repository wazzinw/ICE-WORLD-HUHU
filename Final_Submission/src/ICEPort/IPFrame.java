package ICEPort;


	import javax.swing.JFrame;
	import java.awt.Color;
	import javax.swing.JTable;
	import java.awt.BorderLayout;
	import javax.swing.JLabel;
	import java.awt.Font;
	import java.util.LinkedList;
	import javax.swing.JTextPane;
	import Fetch.Fetching;
	import Fetch.HuhuIcetizen;
	import javax.swing.SpringLayout;
	import javax.swing.JTextArea;
	
	public class IPFrame extends JFrame {

	LinkedList<HuhuIcetizen> citizen = Fetching.tizen;
	String id="";
	String un="";
	String ip= "";

	public IPFrame() {
	setBackground(Color.PINK);
	setSize(400,500);
	setResizable(false);

	for(HuhuIcetizen a: citizen){
	printID(a.getuid(), a.getUsername(), a.getip()) ;
	}

	setGUI();
	//printID();

	}
	private void printID(String id , String un, String ip) {
	// TODO Auto-generated method stub id un ip

	this.id+=id+"\r\n";
	this.un+=un+"\r\n";
	this.ip+=ip+"\r\n";

	}

//		public static void main(String[]args){
//		 IPFrame a = new IPFrame();
//		 a.setVisible(true);
//		}
	public void setGUI(){
	SpringLayout springLayout = new SpringLayout();
	getContentPane().setLayout(springLayout);
	JLabel lblPortId = new JLabel("Port ID");
	springLayout.putConstraint(SpringLayout.NORTH, lblPortId, 36, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.WEST, lblPortId, 22, SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.SOUTH, lblPortId, 62, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, lblPortId, 92, SpringLayout.WEST, getContentPane());
	lblPortId.setFont(new Font("Courier New", Font.BOLD, 15));
	getContentPane().add(lblPortId);

	JLabel label = new JLabel("Username");
	springLayout.putConstraint(SpringLayout.NORTH, label, 33, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.WEST, label, 145, SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.SOUTH, label, 65, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, label, 224, SpringLayout.WEST, getContentPane());
	label.setFont(new Font("Courier New", Font.BOLD, 15));
	getContentPane().add(label);

	JLabel label_1 = new JLabel("IP");
	springLayout.putConstraint(SpringLayout.NORTH, label_1, 36, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.WEST, label_1, 283, SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.SOUTH, label_1, 62, SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, label_1, 323, SpringLayout.WEST, getContentPane());
	label_1.setFont(new Font("Courier New", Font.BOLD, 15));
	getContentPane().add(label_1);

	JTextArea id = new JTextArea();
	springLayout.putConstraint(SpringLayout.NORTH, id, 6, SpringLayout.SOUTH, label_1);
	springLayout.putConstraint(SpringLayout.WEST, id, 251, SpringLayout.WEST, getContentPane());
	id.setText(this.ip);
	springLayout.putConstraint(SpringLayout.SOUTH,id, 375, SpringLayout.SOUTH, label_1);
	springLayout.putConstraint(SpringLayout.EAST, id, -51, SpringLayout.EAST, getContentPane());
	getContentPane().add(id);

	JTextArea un = new JTextArea();
	un.setText(this.id);
	springLayout.putConstraint(SpringLayout.NORTH, un, 6, SpringLayout.SOUTH, lblPortId);
	springLayout.putConstraint(SpringLayout.WEST, un, 10, SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.SOUTH, un, 375, SpringLayout.SOUTH, lblPortId);
	springLayout.putConstraint(SpringLayout.EAST, un, 70, SpringLayout.WEST, getContentPane());
	getContentPane().add(un);

	JTextArea ip = new JTextArea();
	springLayout.putConstraint(SpringLayout.NORTH, ip, 3, SpringLayout.SOUTH, label);
	springLayout.putConstraint(SpringLayout.WEST, ip, 31, SpringLayout.EAST, un);
	springLayout.putConstraint(SpringLayout.EAST, ip, -24, SpringLayout.WEST, id);
	ip.setText(this.un);
	springLayout.putConstraint(SpringLayout.SOUTH, ip, 372, SpringLayout.SOUTH, label);
	getContentPane().add(ip);
	}
	}

