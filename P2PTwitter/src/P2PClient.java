import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2PClient implements Runnable {

	public String unikey;

	P2PClient(String unikey) {
		this.unikey = unikey;
	}

	public void run() {
		try {

			// Establish a connection
			BufferedReader input = new BufferedReader(
					new InputStreamReader(System.in));
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			
			String previousStatus = "";
			
			while (true) {
				System.out.print("Status: ");

				// Reading in the message
				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];
				
				String sentence = unikey + ":" + input.readLine();
				sendData = sentence.getBytes();

				// Creating a packet
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, 7014);

				// Sending the packet
				clientSocket.send(sendPacket);

				// Receiving a packet
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());

				// Outputting the data
				System.out.println("FROM SERVER:" + modifiedSentence);

			

				
				String status = "";
				int rand = 0;

				while (true) {
					status = input.readLine();
					if (status != null) {
						if (previousStatus.equals(status)) {
							rand = 1000;
							previousStatus = status;
						} else {
							rand = (int) (Math.random() * (3000 - 1000)) + 1000;
							previousStatus = status;
						}
					}

					byte[] buf = new byte[140];

					DatagramSocket socket = new DatagramSocket(7014);
					DatagramPacket packet = new DatagramPacket(buf, 150);

					// work with the semicolon
					String message = unikey + ":" + status;

					buf = message.getBytes("ISO-8859-1");
					socket.send(packet);

					long startTime = System.currentTimeMillis();
					long endTime = System.currentTimeMillis();

					if (endTime - startTime > rand) {
						socket.send(packet);
						rand = (int) (Math.random() * (3000 - 1000)) + 1000;
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
