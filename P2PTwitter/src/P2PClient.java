import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2PClient implements Runnable {

	private DatagramSocket clientSocket;

	public void run() {
		try {

			// Establish a connection
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			clientSocket = new DatagramSocket();

			String previousStatus = "";

			// TODO: Implement a random timer for sending packets
//			int rand = 0;

			while (true) {
				System.out.print("Status: ");

				// Reading in the message
				byte[] sendData = new byte[1024];
				byte[] receiveData = new byte[1024];

				String status = input.readLine();

				// Determine when to send to server
				if (status.equals(previousStatus)) {
//					System.out.println("The previous status is the same as the new status");
//					rand = (int) (Math.random() * (3000 - 1000)) + 1000;
				} else {
//					rand = 1000;
				}

				previousStatus = status;

				String message = P2PTwitter.localUser.getUnikey() + ":" + status;
				sendData = message.getBytes("ISO-8859-1");

					
				for (Profile p : P2PTwitter.profiles) {
					InetAddress ip = InetAddress.getByName(p.getIp());
					DatagramPacket sendPacket = new DatagramPacket(sendData,
							sendData.length, ip, 7014);

					clientSocket.send(sendPacket);
				}
				
				printMessages();
			}
		} catch (Exception e) {

		}
	}
	
	public void printMessages() {
		for (Profile p : P2PTwitter.profiles) {
			System.out.println(p.getUnikey() + ":" + p.getMessage());
		}
	}
}
