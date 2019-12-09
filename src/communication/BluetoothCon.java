package communication;

import java.io.DataOutputStream;
import java.io.IOException;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;


public class BluetoothCon implements Runnable{

	
	private DataOutputStream dos;
	
	public BluetoothCon(DataOutputStream dos) {
		this.dos = dos;
	}
	
	@Override
	public void run() {
		//Estabele conexão com o NXT
        while(true) { 
        	try {
        		dos.writeChars(Motor.A.getSpeed() + "-" + Motor.B.getSpeed());
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}	
}
