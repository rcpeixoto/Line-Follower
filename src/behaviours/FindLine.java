package behaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class FindLine implements Behavior{
	
	private boolean isSupressed;
	private LightSensor lightSensor;
	
	public FindLine(LightSensor lightSensor) {
		this.lightSensor = lightSensor;
		isSupressed = true;
	}
	
	
	@Override
	public boolean takeControl() {
		int counter = 0;
		while(lightSensor.getNormalizedLightValue() >= 520) {
			++counter;
			if(counter > 500) return true;
		}
		return false;
	}

	@Override
	public void action() {
		this.isSupressed = false;
		int lightValue = 0;
		//Flag whether the line is found
		boolean found = false;
		
		//Probing left side
		Motor.A.rotate(360);
		Motor.B.rotate(-180);
		while(Motor.A.isMoving()) {
			lightValue = this.lightSensor.getNormalizedLightValue();
			if (lightValue > 440 && lightValue < 470) {
				found = true;
				Motor.A.stop();
				Motor.B.stop();
			}
		}
		if(!found) {
			//Probing Right side
			Motor.A.rotate(-720);
			Motor.B.rotate(360);
			while(Motor.A.isMoving()) {
				lightValue = this.lightSensor.getNormalizedLightValue();
				if (lightValue > 440 && lightValue < 470) {
					found = true;
					Motor.A.stop();
					Motor.B.stop();
				}
			}
		}
	}

	@Override
	public void suppress() {
		this.isSupressed = true;
	}

}
