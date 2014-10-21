import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class P2PClient implements Runnable {

	DatagramSocket clientSocket;

	public void run() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			clientSocket = new DatagramSocket();

			while (true) {
				System.out.print("Status: ");
				String status = input.readLine();

				if (status.isEmpty()) {
					System.err.println("Status is empty. Retry.");
				} else if (status.length() > 140) {
					System.err.println("Status is too long, 140 characters max. Retry");
				} else {
					P2PTwitter.localUser.setStatus(status);
					sendPacket();
					printMessages();
				}
			}
		} catch (Exception e) {

		}
	}

	public void printMessages() {
		System.out.println("### P2P tweets ###");
		System.out.print("# ");
		System.out.print(P2PTwitter.localUser.getPseudo());
		System.out.println(" (" + P2PTwitter.localUser.getUnikey() + ") : " + P2PTwitter.localUser.getStatus());

		for (Profile p : P2PTwitter.profiles) {
			if (p.getStatus() == null) {
				System.out.print("# [");
				System.out.print(p.getPseudo());
				System.out.println(" (" + p.getUnikey() + ") : not yet initialized]");
			} else if (System.currentTimeMillis() - p.getDate() <= 10000) {
				System.out.print("# ");
				System.out.print(p.getPseudo());
				System.out.println(" (" + p.getUnikey() + ") : " + p.getStatus());
			} else if (System.currentTimeMillis() - p.getDate() <= 20000) {
				System.out.print("# [");
				System.out.print(p.getPseudo());
				System.out.println(" (" + p.getUnikey() + ") : idle]");
			} 
		}
		System.out.println("### End tweets ###");
	}

	public void sendPacket() {
		try {
			String statusEncoded = P2PTwitter.localUser.getStatus().replace(":", "\\:");
			
			for (Profile p : P2PTwitter.profiles) {
				byte[] sendData = new byte[1024];
				InetAddress ip = InetAddress.getByName(p.getIp());
				
				String message = P2PTwitter.localUser.getUnikey() + ":" + statusEncoded;
				sendData = message.getBytes("ISO-8859-1");
				
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 7014);
				clientSocket.send(sendPacket);
			}
		} catch (Exception e) {
			
		}
	}
}
