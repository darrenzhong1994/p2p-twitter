import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Communication extends Thread {

	DatagramSocket socket;

	public void run() {
		try {
			socket = new DatagramSocket();
			while (true) {
				Random rand = new Random();
				sleep(rand.nextInt(2000) + 1000);
				if (P2PTwitter.localUser.getStatus() != null) {
					sendPacket();
				}
			}
		} catch (Exception e) {
			
		}
	}

	public void sendPacket() {
		try {
			String statusEncoded = P2PTwitter.localUser.getStatus().replace(":", "\\:");
			
			for (Profile p : P2PTwitter.profiles) {
				InetAddress ip = InetAddress.getByName(p.getIp());
				byte[] sendData = new byte[1024];
				
				String message = P2PTwitter.localUser.getUnikey() + ":" + statusEncoded;
				sendData = message.getBytes("ISO-8859-1");
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 7014);
				socket.send(sendPacket);	
			}
		} catch (Exception e) {
			
		}
	}
}
