package communication;

import java.io.DataOutputStream;
import java.io.IOException;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;


public class BluetoothCon implements Runnable{

	
	@Override
	public void run() {
		//Estabele conexão com o NXT
        NXTConnection connection = Bluetooth.waitForConnection();
        DataOutputStream dos = connection.openDataOutputStream();
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
