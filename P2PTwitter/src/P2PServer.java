import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2PServer implements Runnable {

	public void run() {
		try {
			DatagramSocket serverSocket = new DatagramSocket(9876);
			
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);
				
				String sentence = new String(receivePacket.getData());
				
				System.out.println("RECEIVED: " + sentence);
				
				InetAddress IPAddress = receivePacket.getAddress();
				
				int port = receivePacket.getPort();
				
				String capitalizedSentence = sentence.toUpperCase();
				
				sendData = capitalizedSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,
						sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				serverSocket.close();
			}
		} catch(Exception e) {
			
		}

	}

//	static void readParticipants() {
//		try {
//			BufferedReader input = new BufferedReader(new FileReader(
//					"participants.txt"));
//
//			String line = input.readLine();
//			String[] lineArray = line.split("=");
//			String list = lineArray[1];
//			String[] peers = list.split(",");
//
//			for (int i = 0; i < peers.length; i++) {
//
//				Participant onei = new Participant(list, list, list);
//			}
//
//			while ((line = input.readLine()) != null) {
//
//			}
//			input.readLine();
//			int num = Integer.parseInt(input.readLine());
//
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//
//	}
}
