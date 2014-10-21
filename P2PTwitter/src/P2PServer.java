import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class P2PServer implements Runnable {

	DatagramSocket serverSocket;

	public void run() {
		try {
			serverSocket = new DatagramSocket(7014);

			while (true) {
				byte[] receiveData = new byte[1024];

				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);

				String sentence = new String(receivePacket.getData());

				int index = sentence.indexOf(":");
				String unikey = sentence.substring(0, index);
				String status = sentence.substring(index + 1, sentence.length());
				status = status.replace("\\:", ":");

				for (Profile p : P2PTwitter.profiles) {
					if (unikey.compareTo(p.getUnikey()) == 0) {
						p.setStatus(status);
						p.setDate(System.currentTimeMillis());
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
	}

}
