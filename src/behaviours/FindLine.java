package behaviours;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class FindLine implements Behavior{
	
	private final int SPEED;
	private final int ANGLE;
	private LightSensor lightSensor;
	private boolean found;
	
	public FindLine() {
		this.lightSensor = new LightSensor(SensorPort.S1);
		found = false;
		SPEED = 150;
		ANGLE = 220;
	}
	
	
	@Override
	public boolean takeControl() {
		int counter = 0;
		while(lightSensor.getNormalizedLightValue() >= 500) {
			++counter;
			if(counter > 500) return true;
		}
		return false;
	}

	@Override
	public void action() {
		(new SearchLine()).start();;
		found = false;
		Motor.A.setSpeed(SPEED);
		Motor.B.setSpeed(SPEED);
		
		Motor.A.rotate(ANGLE, true);
		Motor.B.rotate(-(ANGLE), true);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (found) return;		
		Motor.A.rotate((-2)*ANGLE, true);
		Motor.B.rotate(2*ANGLE, true);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		Motor.A.stop();
		Motor.B.stop();
	}

	@Override
	public void suppress() {
	}
	
	private class SearchLine extends Thread{
		
		
		@Override
		public void run() {
			int light = 0;
			while(true) {
				light = lightSensor.getNormalizedLightValue();
				if(light > 440 && light < 480) {
					Motor.B.stop();
					Motor.A.stop();
					found = true;
					break;
				}
			}
		}
	}
}
