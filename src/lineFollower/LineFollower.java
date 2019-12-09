package lineFollower;
import java.io.DataOutputStream;

import behaviours.DodgeObject;
import behaviours.FindLine;
import behaviours.FollowLine;
import behaviours.GasStation;
import behaviours.TurnOff;
import communication.BluetoothCon;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
	
	public static void main(String[] args) {
        NXTConnection connection = Bluetooth.waitForConnection();
        DataOutputStream dos = connection.openDataOutputStream();
		
		
		//Creates all behaviors
		Behavior bLeastPriority = new FollowLine();
		Behavior b0 = new FindLine();
		Behavior bHighestPriority = new TurnOff(); 
		Behavior b1 = new DodgeObject();
		Behavior b2 = new GasStation(dos);
		
		Behavior[] behaviors = {bLeastPriority, b0, b1, b2, bHighestPriority};
		Arbitrator arb = new Arbitrator(behaviors);
		Thread t1 = new Thread(new BluetoothCon(dos));
		t1.start();
		arb.start();
	}
	
}
