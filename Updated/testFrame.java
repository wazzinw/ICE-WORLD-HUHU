package ICEPort;
import javax.swing.JFrame;
import javax.swing.JScrollPane;



public class testFrame extends JFrame {
	
	JScrollPane s = new JScrollPane(new GridProjection());
	testFrame(){
		
		this.add(s);
		
		setSize(500,500);
		setVisible(true);
	}

	public static void main(String[]a){
		testFrame p = new testFrame();
	}
}
