package behaviours;

import lejos.nxt.Button;
import lejos.nxt.NXT;
import lejos.robotics.subsumption.Behavior;

public class TurnOff implements Behavior{
	
	
	
	@Override
	public boolean takeControl() {
		return Button.ENTER.isDown();
	}

	@Override
	public void action() {
		NXT.shutDown();
	}

	@Override
	public void suppress() {
		
	}

}
