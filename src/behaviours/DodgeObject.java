package behaviours;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class DodgeObject implements Behavior{
	
	private UltrasonicSensor eye = new UltrasonicSensor(SensorPort.S2);
	private boolean isSupressed = true;
	
	
	@Override
	public boolean takeControl() {
		return eye.getDistance() < 20;
	}

	
	@Override
	public void action() {
		this.isSupressed = false;
		while(!this.isSupressed) {
		}
	}

	
	@Override
	public void suppress() {
		this.isSupressed = true;
	}

}
