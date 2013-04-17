package Login;


import iceworld.given.IcetizenLook;

public class TestMyIcetizen implements iceworld.given.MyIcetizen{
	int portID;
	IcetizenLook look;
	int listeningPort;
	String username;
	
	
	@Override
	public int getIcePortID() {
		
		return portID;
	}

	@Override
	public IcetizenLook getIcetizenLook() {
		
		return look;
	}

	@Override
	public int getListeningPort() {
		
		return listeningPort;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
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
	
	
}