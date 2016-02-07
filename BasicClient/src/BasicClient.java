import java.io.IOException;
import java.net.Socket;

/**
 * Basic Client to connect to the App.
 * Start the App first
 * 
 * @author Thomas Fellner
 *
 */

public class ServerClient {
	public static void main(String[] args) {
		if(args.length != 1){
			System.err.println("Please pass the IP adress of the server as an argument");
			System.exit(1);
		}
		
		try {
			Socket s = new Socket(args[0], 10101);
			
			int r = 0;
			while(r != -1){
				byte[] b = new byte[2];
				r = s.getInputStream().read(b);

				System.out.println("Speed: "+b[0]+",  Direction: "+b[1]);
			}
			System.out.println("Connection lost");
			s.close();
		} catch (IOException e) {
			System.err.println("An error has occured please check if the IP adress is right and the port (10101) is availible\n"
						+ "Check if App already has a running connection. App must be started first");
		}
	}
}