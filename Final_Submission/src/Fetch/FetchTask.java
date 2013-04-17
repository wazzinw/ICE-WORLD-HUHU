package Fetch;

import java.util.concurrent.CountDownLatch;


public class FetchTask implements Runnable {
	Fetching  name;
	private final int timeToStart;
	private final CountDownLatch latch;
	
	public FetchTask(Fetching f,int time,CountDownLatch c){
		this.timeToStart = time;
		this.latch = c;
		this.name = f;
	}
	
	
	public void run() {
		try {
			new Thread(name).start();
			Thread.sleep(timeToStart);
            
        } catch (InterruptedException ex) {
            //Logger.getLogger(FetchTask.class.getName()).log(Level.SEVERE, null, ex);
        	System.err.println("Fudge");
        }
        System.out.println( name + " is Up");
        latch.countDown(); //reduce count of CountDownLatch by 1
    }


		

	}


