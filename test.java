import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.*;
import java.net.*;
import java.util.Scanner;

import java.io.*;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;



public class test {
	
	

	public void testURL() throws Exception {
		String strUrl = "http://iceworld.sls-atl.com/api/explore";

		try {
			System.out.println("..Checking the conection..");
			URL url = new URL(strUrl);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.connect();


			assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
			System.out.println("ICE WORLD can be reached.");
			sendHTTPrequest();
		}
		catch (MalformedURLException e){
			System.out.println("Invalid URL");
		} 
		catch (IOException e) {
			System.out.println("Error creating HTTP connection");
			//e.printStackTrace();
			//throw e;
		}
	}
	public static void main (String[]args){
		test a = new test();
		try {
			a.testURL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// public static void main(String[] args) throws Exception {
	public static /*String*/void sendHTTPrequest() throws IOException{
		boolean exit = false;
		while(!exit){
			System.out.println("Please selcet your request (1-6):\n<1> time\n<2> states\n<3> actions\n<4> gresources\n<5> gurl\n<6> exit");
			Scanner sc = new Scanner(System.in);
			int huhu = Integer.parseInt(sc.nextLine());
			String adder ="";

			switch (huhu) {
			case 1:  adder = "time";
			break;
			case 2:  adder = "states";
			break;
			case 3:  System.out.println("Please input your keyword:");
			sc = new Scanner(System.in);
			adder = "actions&from="+sc.nextLine();
			break;
			case 4:  System.out.println("Please input your keyword:");
			sc = new Scanner(System.in);
			adder = "gresources&uid="+sc.nextLine();
			break;
			case 5: System.out.println("Please input your keyword:");
			sc = new Scanner(System.in);
			adder = "gurl&gid="+sc.nextLine();
			break;
			case 6: exit = true;
			System.out.println("Program is terminated");
			System.exit(0);
			break;

			default: adder = "Invalid input";
			break;
			}

			URL myURL = new URL("http://iceworld.sls-atl.com/api/&cmd="+adder);
			URLConnection yc = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			String response = "";

			while ((inputLine = in.readLine()) != null) {
				response += inputLine+"\n";
			}

			System.out.println(response);

			in.close();
			//return response;
		}
	}

}

