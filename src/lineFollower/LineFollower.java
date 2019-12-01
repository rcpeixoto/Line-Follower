package lineFollower;
import behaviours.FollowLine;
import communication.BluetoothCon;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
	
	public static void main(String[] args) {
		Behavior b0 = new FollowLine();
		Behavior[] behaviors = {b0};
		Arbitrator arb = new Arbitrator(behaviors);
		Thread t1 = new Thread(new BluetoothCon());
		t1.start();
		arb.start();
	}
	
}
