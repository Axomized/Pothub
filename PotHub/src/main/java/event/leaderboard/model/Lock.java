package event.leaderboard.model;

public class Lock{
	private int read = 0;
	private int write = 0;
	private int writeRequest = 0;
	

	public synchronized void lockRead() throws InterruptedException{
	    while(write > 0 || writeRequest > 0){
	    	wait();
	    }
	    read++;
	}
	
	public synchronized void unlockRead(){
	    read--;
	    notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException{
	    writeRequest++;

	    while(read > 0 || write > 0){
	      wait();
	    }
	    writeRequest--;
	    write++;
	}

	public synchronized void unlockWrite() throws InterruptedException{
	    write--;
	    notifyAll();
	}
}