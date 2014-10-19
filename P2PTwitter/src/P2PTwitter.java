public class P2PTwitter {

	public static void main(String[] args) {
		Thread server = new Thread(new P2PServer());
		server.start();

		Thread client = new Thread(new P2PClient(args[0]));
		client.start();
	}
}
