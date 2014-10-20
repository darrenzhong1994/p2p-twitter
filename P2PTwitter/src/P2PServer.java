import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

public class P2PServer implements Runnable {

	public void run() {
		try {

			// Establish a connection on port 7014
			DatagramSocket serverSocket = new DatagramSocket(7014);

			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];

			while (true) {

				//Get the packet from the port
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);

				// Get the message
				String sentence = new String(receivePacket.getData());
				String[] message = sentence.split(":");

				// Check to see that the unikey can be associated to an account
				for (Profile p : P2PTwitter.profiles) {
					if (message[0].equals(p.getUnikey())) {
						p.setDate(System.currentTimeMillis());
						p.setMessage(message[1]);
						break;
					}
				}
				

				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();

				String capitalizedSentence = sentence.toUpperCase();

				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				serverSocket.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
