package Fetch;

public class actionn {
	//{"aid":"50758","action_type":"2","uid":"35","timestamp":"1366133474","detail":"oiiii tookkon "},
	
	String aid,action_type,suid,stimestamp,detail;
	public actionn(String aid,String action_type,String suid,String stimestamp,String detail){
		this.aid = aid;
		this.action_type =action_type;
		this.suid = suid;
		this.stimestamp = stimestamp;
		this.detail = detail;
	}
	
	public String getaid(){
		return this.aid;
	}
	
	public String getaction_type(){
		return this.action_type;
	}
	
	public String getsuid(){
		return this.suid;
	}
	
	public String getstimestamp(){
		return this.stimestamp;
	}
	
	public String getdetail(){
		return this.detail;
	}
}
