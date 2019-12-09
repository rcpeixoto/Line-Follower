package behaviours;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class GasStation implements Behavior{

	private LightSensor light;
	private DataOutputStream dos;
	private final int lowerOffset;
	private final int upperOffset;
	
	public GasStation (DataOutputStream dos) {
		this.lowerOffset = 0;
		this.upperOffset = 0;
		this.dos = dos;	
		this.light = new LightSensor(SensorPort.S1);
	}
	
	@Override
	public boolean takeControl() {
		int lightValue = light.getNormalizedLightValue();
		return (lightValue > this.lowerOffset && lightValue < this.upperOffset);
	}

	@Override
	public void action() {
		try {
			dos.writeBytes("Estação Encontrada!");
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Motor.A.forward();
		Motor.B.forward();
		sleep(500);
		Motor.A.stop();
		Motor.B.stop();
	}
	
	
	
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
		}	
	}
	

	@Override
	public void suppress() {
		
	}
	
}
