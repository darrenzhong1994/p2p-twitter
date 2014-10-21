import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class P2PServer implements Runnable {

	DatagramSocket serverSocket;
	public void run() {
		try {

			// Establish a connection on port 7014
			 serverSocket = new DatagramSocket(7014);

			while (true) {

				byte[] receiveData = new byte[1024];

				// Get the packet from the port
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);

				// Get the message
				String sentence = new String(receivePacket.getData());
				//System.out.println(sentence);

				String[] message = sentence.split(":");

				// Check to see that the unikey can be associated to an account
				for (Profile p : P2PTwitter.profiles) {
					if (message[0].equals(p.getUnikey())) {
						p.setStatus(message[1]);
						p.setDate(System.currentTimeMillis());
						break;
					} 
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
