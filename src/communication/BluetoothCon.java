package communication;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.NXTOutputStream;


public class BluetoothCon implements Runnable{

	
	@Override
	public void run() {
		//Estabele conexão com o NXT
        NXTConnection connection = Bluetooth.waitForConnection();
        DataOutputStream dos = connection.openDataOutputStream();
        while(true) { 
        	byte b1 = Byte.parseByte(Motor.A.getSpeed() + "");
        	byte b2 = Byte.parseByte(Motor.B.getSpeed() + "");
        	byte[] buffer = {b1,b2};
        	try {
				dos.write(buffer);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
        }
	}	
}
