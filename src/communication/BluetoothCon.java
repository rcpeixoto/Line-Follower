package communication;

import java.io.IOException;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.util.LogColumn;
import lejos.util.NXTDataLogger;


public class BluetoothCon implements Runnable{

	
	@Override
	public void run() {
		//Estabele conexão com o NXT
        NXTConnection connection = Bluetooth.waitForConnection();
        NXTDataLogger dataChannel = new NXTDataLogger();
        LogColumn[] colunas = new LogColumn[2];
	 	//Cria as colunas do log
		colunas[0] = new LogColumn("Velocidade_A");
		colunas[1] = new LogColumn("Velocidade_B");
		try{//Estabelece envio de dados para o pc
       			 dataChannel.startRealtimeLog(connection);
		} catch (IOException ioe){
       			 System.out.println("Error!");
		}
		dataChannel.setColumns(colunas);
		//Teste de escrita de dados
		while(true) {
			dataChannel.writeLog(Motor.A.getSpeed());
			dataChannel.writeLog(Motor.B.getSpeed());
			dataChannel.finishLine();
		}
		
	}	
}
