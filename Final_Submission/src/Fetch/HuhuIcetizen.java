package Fetch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import ICEPort.GridProjection;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import iceworld.given.IcetizenLook;

public class HuhuIcetizen implements iceworld.given.MyIcetizen{
	
	IcetizenLook look = new IcetizenLook();
	int listeningPort;
	
	//action

	String uid;
	String username;
	String type;
	String ip;
	String port;
	int portID;
	public Point position;
	Image hImage ,sImage, wImage,bImage; 
	
	static JSONParser json = new JSONParser();
	 static ContainerFactory containerFactory = new ContainerFactory() {
		    public List creatArrayContainer() { return new LinkedList(); } 
		    public Map createObjectContainer() { return new LinkedHashMap(); }

	 };
	 
	
	
	

	public HuhuIcetizen(){
		
	}

	public HuhuIcetizen(String uid, String username, String type, String ip, String port, String portID,Point p) throws IOException, org.json.simple.parser.ParseException{
		this.uid=uid;
		this.username= username;
		this.type=type;
		this.ip=ip;
		this.port= port;
		this.portID = Integer.parseInt(portID);
		this.position=p;
	
				
		
		
		try {
			fetchLookCode();
			
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		hImage = getHeadImage(look.gidH);
		bImage = getBodyImage(look.gidB);
		sImage = getShirtImage(look.gidS);
		wImage = getWeaponImage(look.gidW);
		
	}

	public String getuid() {

		return uid;
	}

	@Override
	public String getUsername() {

		return username;
	}
	public String gettype() {

		return type;
	}
	public String getip() {

		return ip;
	}
	public String getport() {

		return port;
	}

	@Override
	public int getIcePortID() {

		return portID;
	}
	public Point getPosition() {

		return position;
	}


	public Image getHead(){
		return hImage;
	}
	public Image getWeapon(){
		return wImage;
	}
	public Image getBody(){
		return bImage;
	}
	public Image getShirt(){
		return sImage;
	}
	
	@Override
	public IcetizenLook getIcetizenLook() {
		
		return look;
	}

	@Override
	public int getListeningPort() {

		return listeningPort;
	}



	public void setIcePortID(int arg0) {

		portID = arg0;
	}

	@Override
	public void setIcetizenLook(IcetizenLook arg0) {
		look = arg0;

	}

	@Override
	public void setListeningPort(int arg0) {
		listeningPort = arg0;

	}

	@Override
	public void setUsername(String arg0) {
		username = arg0;

	}
	
	public void walk(int x,int y){
		
	}
	public void talk(String msg,Graphics g,Font f,Color c){
		//GridProjection.talkk()
	}
	
	public void yell(String msg,Graphics g,Font f,Color c){
		
	}
	
	public void fetchLookCode() throws org.json.simple.parser.ParseException{
		String inputLine = "";
		try
	    {
	      URL url = new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid="+uid);
	      URLConnection urlc = url.openConnection();
	      BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	      String buffs;
	      while ((buffs = in.readLine()) != null)
	      {
	        inputLine = inputLine + buffs;
	      }
	      in.close();
	    }
	    catch (Exception e)
	    {
	      System.out.println("Error");
	    }
		List<Map> jsonData = null;
		try {
			Map jsonMap = (Map)json.parse(inputLine, containerFactory);
			jsonData = (List<Map>)jsonMap.get("data");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		look.gidH = ((String) jsonData.get(0).get("H")==null) ? "H008": (String) jsonData.get(0).get("H");
		look.gidB = ((String) jsonData.get(0).get("B")==null) ? "B001": (String) jsonData.get(0).get("B");
		look.gidS = ((String) jsonData.get(0).get("S")==null) ? "S019": (String) jsonData.get(0).get("S");
		look.gidW = ((String) jsonData.get(0).get("W")==null) ? "W050": (String) jsonData.get(0).get("W");
	}
	
	
	public  Image getBodyImage(String i) throws IOException{
		
		URL url = null;
		String breq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+i;
		String bla, linkToImage ="";;
		url= new URL (breq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{

			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");

				linkToImage = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		Image a = null;
		URL blink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
		a = ImageIO.read(blink);

		
		return a;
		
	}
	

	
	public Image getShirtImage(String i) throws IOException{
		URL url = null;
		String sreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+i;
		String bla, linkToImage ="";;
		ImageIcon shirtimg;
		url= new URL (sreq);
		//{"status":1,"data":{"gid":"B001","location":"graphics\/body\/blue.png"}}
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{

			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");

				linkToImage = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		Image a = null;
		URL slink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
		a = ImageIO.read(slink);
		return a;
		

		
	}
	
	public Image getHeadImage(String i) throws IOException, org.json.simple.parser.ParseException{
		URL url = null;
		String hreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+i;
		String bla, linkToImage ="";;
		ImageIcon headimg;
		url= new URL (hreq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{

			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");

				linkToImage = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		Image image = null;
		URL hlink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
		image = ImageIO.read(hlink);
		return image;

		
	}
	
	public  Image getWeaponImage(String i) throws IOException{
		URL url = null;
		String wreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+i;
		String bla, linkToImage ="";;
		
		url= new URL (wreq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((bla = temp.readLine()) != null) 
		{

			try {
				Map jsonMap = (Map) json.parse(bla, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");

				linkToImage = (String) jsonData.get("location");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Image a = null;
		URL wlink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
		a = ImageIO.read(wlink);
		return a;
		
	}

	

}