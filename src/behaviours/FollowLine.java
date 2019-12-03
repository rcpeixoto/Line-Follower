package behaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class FollowLine implements Behavior{
	
	private final int STANDARD_SPEED;
	private boolean isSupressed;
	private LightSensor light;
	
	
	public FollowLine() {
		this.light = new LightSensor(SensorPort.S1);
		this.STANDARD_SPEED = 250;
		this.isSupressed = true;
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		int lightread = 0;
		Motor.A.forward();
		Motor.B.forward();
		this.isSupressed = false;
		
		while(!this.isSupressed) {
			lightread = this.light.getNormalizedLightValue();
			if(lightread > 440 && lightread < 485) {
				Motor.A.setSpeed(this.STANDARD_SPEED);
				Motor.B.setSpeed(this.STANDARD_SPEED);
			}
			//Adjust speed of motor when straying to the white board
			if(lightread > 400 && lightread < 440) {
				Motor.B.setSpeed((this.STANDARD_SPEED*3)/4);
				Motor.A.setSpeed(this.STANDARD_SPEED);
			}
			if(lightread < 400) {
				Motor.B.setSpeed(this.STANDARD_SPEED/4);
				Motor.A.setSpeed(this.STANDARD_SPEED);
			}
			//Adjust speed of motor when straying to the black stripe
			if(lightread > 485 && lightread < 515) {
				Motor.A.setSpeed((this.STANDARD_SPEED*3)/4);
				Motor.B.setSpeed(this.STANDARD_SPEED);
			}
			if(lightread > 515) {
				Motor.A.setSpeed(this.STANDARD_SPEED/4);
				Motor.B.setSpeed(this.STANDARD_SPEED);
			}
		}
		Motor.B.stop();
		Motor.A.stop();
	}

	@Override
	public void suppress() {
		this.isSupressed = true;
	}

}
