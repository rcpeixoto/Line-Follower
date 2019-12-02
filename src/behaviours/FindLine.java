package behaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class FindLine implements Behavior{
	
	private boolean isSupressed;
	private LightSensor lightSensor;
	private DifferentialPilot motor;
	
	public FindLine() {
		this.lightSensor = new LightSensor(SensorPort.S1);
		isSupressed = true;
		motor = new DifferentialPilot(56.0f, 145.0f, Motor.A, Motor.B);
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
		//Sets motor speed
		motor.setTravelSpeed(1.0f);
		//Flag whether the line is found
		boolean found = false;
		//Probing left side
		motor.rotate(90);
		while(motor.isMoving()) {
			lightValue = this.lightSensor.getNormalizedLightValue();
			if (lightValue > 440 && lightValue < 470) {
				motor.stop();
				found = true;
			}
		}
		if(!found) {
			//Probing Right side
			motor.rotate(-90);
			while(motor.isMoving()) {
				lightValue = this.lightSensor.getNormalizedLightValue();
				if (lightValue > 440 && lightValue < 470) {
					motor.stop();
					found = true;
				}
			}
		}
	}

	@Override
	public void suppress() {
		this.isSupressed = true;
	}

}
