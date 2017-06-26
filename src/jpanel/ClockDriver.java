package jpanel;

public class ClockDriver extends Thread{
	ClockFieldObj drivenClock;
	public ClockDriver(ClockFieldObj clock){
		drivenClock = clock;
	}

	public void run(){

		while(true){
			drivenClock.repaint();
			try{
				sleep(10);
			}catch(InterruptedException e){

			}
		}
	}
}