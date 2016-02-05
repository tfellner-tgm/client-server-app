import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Basic Server to connect to the App.
 * Start the App first
 * 
 * @author Thomas Fellner
 *
 */

public class ServerClient {
	public static void main(String[] args) {
		try {
			String ipAdress = "127.0.0.1"; // Write the IP of the phone that is running the app here
			Socket s = new Socket(ipAdress, 12321);
			
			int r = 0;
			while(r != -1){
				byte[] b = new byte[2];
				r = s.getInputStream().read(b);

				System.out.println("Y:"+b[0]+"  X:"+b[1]);
			}
			System.out.println("Connection Lost");
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
