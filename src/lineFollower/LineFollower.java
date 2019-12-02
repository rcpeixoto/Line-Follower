package lineFollower;
import behaviours.FindLine;
import behaviours.FollowLine;
import behaviours.TurnOff;
import communication.BluetoothCon;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
	
	public static void main(String[] args) {
		//Creates all behaviors
		Behavior bLeastPriority = new FollowLine();
		Behavior b0 = new FindLine();
		Behavior bHighestPriority = new TurnOff(); 
		
		
		Behavior[] behaviors = {bLeastPriority, b0, bHighestPriority};
		Arbitrator arb = new Arbitrator(behaviors);
		Thread t1 = new Thread(new BluetoothCon());
		t1.start();
		arb.start();
	}
	
}
