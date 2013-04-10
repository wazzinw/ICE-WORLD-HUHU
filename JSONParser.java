import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;

public class ICEWorldImmigration
{
  private MyIcetizen icetizen;
  private String accessToken;
  private int uid;
  JSONParser parser = new JSONParser();
  ContainerFactory containerFactory = new ContainerFactory() {
    public List creatArrayContainer() { return new LinkedList(); } 
    public Map createObjectContainer() { return new LinkedHashMap(); }

  };

  public ICEWorldImmigration(MyIcetizen icetizen)
  {
    this.icetizen = icetizen;
  }

  public boolean login(String upass)
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration?cmd=login&username=" + URLEncoder.encode(this.icetizen.getUsername(), "UTF-8") + "&code=" + URLEncoder.encode(upass, "UTF-8") + "&pid=" + this.icetizen.getIcePortID() + "&listeningport=" + this.icetizen.getListeningPort());
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      Map jsonData = (Map)json.get("data");
      json.entrySet().iterator();

      if (json.get("status").toString().equals("0"))
      {
        return false;
      }

      this.uid = Integer.parseInt(jsonData.get("uid").toString());
      this.accessToken = jsonData.get("access_token").toString();
      return true;
    }
    catch (Exception e) {
    }
    return false;
  }

  public boolean loginAlien()
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration?cmd=login&alien=true&pid=" + this.icetizen.getIcePortID());
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      Map jsonData = (Map)json.get("data");
      json.entrySet().iterator();

      if (json.get("status").toString().equals("0"))
      {
        return false;
      }

      this.uid = Integer.parseInt(jsonData.get("uid").toString());
      this.accessToken = jsonData.get("access_token").toString();
      return true;
    }
    catch (Exception e) {
    }
    return false;
  }

  public boolean logout()
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration&cmd=logout&uid=" + this.uid + "&access_token=" + URLEncoder.encode(this.accessToken, "UTF-8"));
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      if (json.get("status").toString().equals("1")) {
        return true;
      }

      System.out.println(json.get("msg").toString());
      return false;
    }
    catch (Exception e)
    {
    }
    return false;
  }

  public boolean walk(int x, int y)
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration&cmd=do&action_type=1&x=" + x + "&y=" + y + "&uid=" + this.uid + "&access_token=" + URLEncoder.encode(this.accessToken, "UTF-8"));
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      if (json.get("status").toString().equals("1")) {
        return true;
      }

      System.out.println(json.get("msg").toString());
      return false;
    }
    catch (Exception e)
    {
    }
    return false;
  }

  public boolean yell(String msg)
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration&cmd=do&action_type=3&msg=" + URLEncoder.encode(msg, "UTF-8") + "&uid=" + this.uid + "&access_token=" + URLEncoder.encode(this.accessToken, "UTF-8"));
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      if (json.get("status").toString().equals("1")) {
        return true;
      }

      System.out.println(json.get("msg").toString());
      return false;
    }
    catch (Exception e)
    {
    }
    return false;
  }

  public boolean talk(String msg)
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration&cmd=do&action_type=2&msg=" + URLEncoder.encode(msg, "UTF-8") + "&uid=" + this.uid + "&access_token=" + URLEncoder.encode(this.accessToken, "UTF-8"));
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      if (json.get("status").toString().equals("1")) {
        return true;
      }

      System.out.println(json.get("msg").toString());
      return false;
    }
    catch (Exception e)
    {
    }
    return false;
  }

  public boolean customization(IcetizenLook look)
  {
    String inputLine = "";
    try
    {
      URL url = new URL("http://iceworld.sls-atl.com/api/immigration&cmd=customize&gid_H=" + look.gidH + "&gid_W=" + look.gidW + "&gid_B=" + look.gidB + "&gid_S=" + look.gidS + "&uid=" + this.uid + "&access_token=" + URLEncoder.encode(this.accessToken, "UTF-8"));
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
      return false;
    }
    try
    {
      Map json = (Map)this.parser.parse(inputLine, this.containerFactory);
      if (json.get("status").toString().equals("1"))
      {
        this.icetizen.setIcetizenLook(look);
        return true;
      }

      System.out.println(json.get("msg").toString());
      return false;
    }
    catch (Exception e)
    {
    }
    return false;
  }
}
