package Fetch;
import iceworld.given.IcetizenLook;

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.imageio.ImageIO;

import iceworld.given.ICEWorldImmigration;
import iceworld.given.IcetizenLook;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


public class Fetching extends Thread {

	static CountDownLatch cdl;
	URL myURL;
	String response;
	 public static LinkedList<HuhuIcetizen> tizen = new LinkedList<HuhuIcetizen>();
	 static LinkedList<actionn> walking = new LinkedList<actionn>();
	static LinkedList<actionn> talking = new LinkedList<actionn>();
	static LinkedList<actionn>	yelling = new LinkedList<actionn>();
	
	Set keys;
	Iterator i;
	Map uid;
	Map look;
	Map json;
	Map jsonData;
	Map user;
	Map last_known_destination;
	Map action;
	List<Map> a;
	
	
	
	String time;	
   public String weather = "";
	String username;
	String ip;
	String type;
	String port;
	String pid;
	Point position;
	int x,y;
	
	IcetizenLook looks =new IcetizenLook();
	
	Image Hpic=null,Bpic=null,Spic=null,Wpic=null;

	
	int count =0;
	JSONParser parser = new JSONParser();

	ContainerFactory containerFactory = new ContainerFactory() {
		public List creatArrayContainer() { return new LinkedList(); } 
		public Map createObjectContainer() { return new LinkedHashMap(); }

	};

	public Fetching (CountDownLatch c){
		cdl=c;
	}

	public void run(){
		//while(true){
		try
		{	tizen.clear();
			walking.clear();
			talking.clear();
			yelling.clear();
			System.out.println("Fetch number "+count++);
			System.out.println("=============================");
			
			time = (String)((Map)this.parser.parse(getto("time"), this.containerFactory)).get("data");
			System.out.println("Time: "+time);
			json = (Map)this.parser.parse(getto("states"), this.containerFactory);
			jsonData = (Map)json.get("data");
			//json.entrySet().iterator();
			weather = (((Map)jsonData.get("weather")).get("condition").toString());
			System.out.println("Weather: "+weather);
			System.out.println("=============================");
			
	
		
			action = (Map)this.parser.parse(getto("actions&from="+(Integer.parseInt(time)-20)), this.containerFactory);
			List<Map> jsonDatas = (List<Map>) action.get("data");
			for(Map a:jsonDatas){
				String a1 =a.get("aid").toString();
				String a2 =a.get("action_type").toString();
				String a3 =a.get("uid").toString();
				String a4 =a.get("timestamp").toString();
				String a5 =a.get("detail").toString();
				System.out.println("aid :"+a1);
				System.out.println("action_type :"+a2);
				System.out.println("uid :"+a3);
			
				System.out.println("timestamp :"+a4);
				System.out.println("detail :"+a5);
				System.out.println("************************");
				if(a2.equals("1")) walking.add(new actionn(a1,a2,a3,a4,a5));
				else if(a2.equals("2")) talking.add(new actionn(a1,a2,a3,a4,a5));
				else if(a2.equals("3")) yelling.add(new actionn(a1,a2,a3,a4,a5));
				
			}
			Map icet = (Map)jsonData.get("icetizen");
			keys = icet.keySet();
			Object[]keysArray = keys.toArray();
			i = keys.iterator();
			String uidS;
			
			while(i.hasNext()){
				
				uidS=(String)i.next();
				
				uid = (Map)icet.get(uidS);
				user = (Map)uid.get("user");
				last_known_destination = (Map)uid.get("last_known_destination");
				
				System.out.println(uidS);	
				
				
				
				
				username= user.get("username").toString();
				type= user.get("type").toString();
				ip= user.get("ip").toString().toString();
				port= user.get("port").toString();
				pid= user.get("pid").toString();
				
				String p;
				if(last_known_destination.get("position")==null) p = "(0,0)";
				else p = last_known_destination.get("position").toString();
				
				System.out.println(p);
				x = Integer.parseInt(p.substring(1, p.indexOf(',')));
				y = Integer.parseInt(p.substring(p.indexOf(',')+1,p.indexOf(')')));
				position = new Point(x,y);
				
				System.out.println("User: "+username);
				System.out.println("Type: "+type);
				System.out.println("ip: "+ip);
				System.out.println("Port: "+port);
				System.out.println("Pid: "+pid);
				System.out.println("Position: "+position);
				System.out.println("========================================");
				
				
				tizen.add(new HuhuIcetizen(uidS,username,type,ip,port,pid,position));
				
				
			}
			
			
			
			
			
			//Thread.sleep(1000);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		cdl.countDown();

	}
	//}
	public String getto(String adder){
		try {
			
			myURL = new URL("http://iceworld.sls-atl.com/api/&cmd="+adder);
			URLConnection yc = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			response = "";

			while ((inputLine = in.readLine()) != null) {
				response += inputLine+"\n";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	public static  LinkedList<HuhuIcetizen> getCitizen(){
		return tizen;
	}
	public static  LinkedList<actionn> getWalk(){
		return walking;
	}
	public static  LinkedList<actionn> getTalk(){
		return talking;
	}
	public static  LinkedList<actionn> getYell(){
		return yelling;
		
	}
	
	
	
	public static void main (String[]args){
		Fetching a = new Fetching(new CountDownLatch(1));
		Thread b = new Thread(a);
		b.start();
		//System.out.println(a.weather);
	}
	

}
