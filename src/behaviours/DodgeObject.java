package behaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class DodgeObject implements Behavior{
	

	private UltrasonicSensor eye;
	private final int SPEED;
	private LightSensor light;
	private boolean isSuppress;
	
	public DodgeObject() {
		this.SPEED = 150;
		light = new LightSensor(SensorPort.S1);
		isSuppress = true;
		eye = new UltrasonicSensor(SensorPort.S2);
	}
	
	@Override
	public boolean takeControl() {
		return eye.getDistance() < 20;
	}

	
	@Override
	public void action() {
		this.isSuppress = false;
		while(!this.isSuppress) {
			this.isSuppress = true;
			int lightValue = 0;
			int distance = 0;
			this.isSuppress = false;
			boolean out = false;
			//initial state
			Motor.A.setSpeed(SPEED);
			Motor.B.setSpeed(SPEED);
			Motor.A.rotate(-220, true);
			Motor.B.rotate(220, true);
			Motor.C.rotate(-90);
			sleep(3000);
			//Rotating the crate
			while(!this.isSuppress) {
				lightValue = light.getNormalizedLightValue();
				distance = eye.getDistance();
				Motor.A.forward();
				Motor.B.forward();
				if(lightValue > 400 && lightValue < 480) {
					sleep(1500);
					Motor.A.stop();
					Motor.B.stop();
					Motor.A.rotate(-220, true);
					Motor.B.rotate(220, true);
					sleep(1500);
					break;
				}else if(distance > 20 && out) {
					sleep(3000);
					Motor.A.stop();
					Motor.B.stop();
					Motor.A.rotate(220, true);
					Motor.B.rotate(-220, true);
					sleep(1500);
					out = false; 
				}else if (distance < 20) {
					out = true;		
				}
			}
			Motor.C.rotate(90, false);
			Motor.A.stop();
			Motor.B.stop();
		}
	}
	
		
	
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
		}	
	}
	
	
	@Override
	public void suppress() {
		this.isSuppress = true;	
	}

}
