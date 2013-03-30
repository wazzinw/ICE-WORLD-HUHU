import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;

import java.awt.Font;


import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

//import com.jgoodies.forms.factories.DefaultComponentFactory;





public class CustomPanel extends JPanel {

	
	private JPanel preview, selectionArea;
    JTextPane txtpnShirt,txtpnBody,txtpnHead,txtpnWeapon;
	JButton shirtUp,shirtDown,bodyUp,bodyDown,headUp,headDown,weaponUp,weaponDown;
	
	public CustomPanel(){
		setGui();
		 txtpnShirt = new JTextPane(); 
		 txtpnBody = new JTextPane();
		 txtpnWeapon = new JTextPane();
		 txtpnHead = new JTextPane();
		 
		 shirtUp = new JButton();
		 shirtDown = new JButton();
		 bodyUp = new JButton();
		 bodyDown = new JButton();
		 headUp = new JButton();
		 headDown = new JButton();
		 weaponUp = new JButton();
		 weaponDown = new JButton();
		 
	}
	
	private void setGui(){
		setLayout(new GridLayout(1,2,5,5));
		setPreview();
		setSelectionArea();
		
		this.add(preview);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(17, 46, 311, 409);
		preview.add(panel_5);
		panel_5.setLayout(null);
		this.add(selectionArea);
		
			JTextPane txtpnShirt_1 = new JTextPane();
			txtpnShirt_1.setForeground(Color.RED);
			txtpnShirt_1.setEditable(false);
			txtpnShirt_1.setFont(new Font("Chalkduster", Font.BOLD, 14));
			txtpnShirt_1.setBackground(Color.LIGHT_GRAY);
			txtpnShirt_1.setText("Shirt");
			txtpnShirt_1.setBounds(151, 25, 54, 16);
			selectionArea.add(txtpnShirt_1);
		
		
		JTextPane txtpnColor = new JTextPane();
		txtpnColor.setBackground(new Color(192, 192, 192));
		txtpnColor.setForeground(new Color(255, 0, 0));
		txtpnColor.setFont(new Font("Chalkduster", Font.BOLD, 14));
		txtpnColor.setText("Color");
		txtpnColor.setBounds(151, 142, 54, 16);
		selectionArea.add(txtpnColor);
		
		JTextPane txtpnHeadGear = new JTextPane();
		txtpnHeadGear.setForeground(new Color(255, 0, 0));
		txtpnHeadGear.setBackground(new Color(192, 192, 192));
		txtpnHeadGear.setFont(new Font("Chalkduster", Font.BOLD, 14));
		txtpnHeadGear.setText("Head Gear");
		txtpnHeadGear.setBounds(140, 268, 97, 16);
		selectionArea.add(txtpnHeadGear);
		
		JTextPane txtpnWeapon_1 = new JTextPane();
		txtpnWeapon_1.setFont(new Font("Chalkduster", Font.BOLD, 14));
		txtpnWeapon_1.setEditable(false);
		txtpnWeapon_1.setForeground(new Color(255, 0, 0));
		txtpnWeapon_1.setBackground(new Color(192, 192, 192));
		txtpnWeapon_1.setText("Weapon");
		txtpnWeapon_1.setBounds(140, 383, 85, 16);
		selectionArea.add(txtpnWeapon_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(125, 53, 112, 77);
		selectionArea.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(125, 170, 112, 74);
		selectionArea.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(126, 297, 111, 74);
		selectionArea.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(125, 411, 110, 74);
		selectionArea.add(panel_3);
		
		JButton button = new JButton(">>");
		button.setSelectedIcon(null);
		button.setBounds(264, 79, 30, 30);
		selectionArea.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setBounds(264, 189, 30, 30);
		selectionArea.add(button_1);
		
		JButton button_2 = new JButton(">>");
		button_2.setBounds(264, 322, 30, 30);
		selectionArea.add(button_2);
		
		JButton button_3 = new JButton(">>");
		button_3.setBounds(264, 432, 30, 30);
		selectionArea.add(button_3);
		
		JButton button_4 = new JButton("<<");
		button_4.setBounds(51, 79, 30, 30);
		selectionArea.add(button_4);
		
		JButton button_5 = new JButton("<<");
		button_5.setBounds(51, 189, 30, 30);
		selectionArea.add(button_5);
		
		JButton button_6 = new JButton("<<");
		button_6.setBounds(51, 322, 30, 30);
		selectionArea.add(button_6);
		Icon one = new ImageIcon(getClass().getResource("arrow.png"));

		
		JButton button_7 = new JButton("<<",one);
		button_7.setBounds(51, 432, 30, 30);
		selectionArea.add(button_7);
		
		
		//Icon one = new ImageIcon(getClass().getResource("arrow.png"));
	}
	private void setPreview() {
		preview = new JPanel();
		preview.setLayout(null);
		preview.setBackground(Color.WHITE);
		//preview.setLayout(new GridLayout(3,1,5,5));
		preview.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	private void setSelectionArea() {
		selectionArea = new JPanel();
		selectionArea.setBackground(Color.LIGHT_GRAY);
		selectionArea.setLayout(null);
		selectionArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
	
}
