import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

public class P2PTwitter {

	public static ArrayList<Profile> profiles;
	public static Profile localUser;

	public static void main(String[] args) {

		if (args[0] != null) {
			readParticipants(args[0]);

			Thread client = new Thread(new P2PClient());
			client.start();

			Thread server = new Thread(new P2PServer());
			server.start();
			
			Thread communication = new Thread(new Communication());
			communication.start();
		}
	}

	public static void readParticipants(String localUnikey) {
		try {
			profiles = new ArrayList<Profile>();

			Properties props = new Properties();
			FileReader file = new FileReader("participants.properties");
			props.load(file);

			String[] peers = props.getProperty("participants").split(",");

			for (int i = 0; i < peers.length; i++) {

				String ip = props.getProperty(peers[i] + ".ip");
				String pseudo = props.getProperty(peers[i] + ".pseudo");
				String unikey = props.getProperty(peers[i] + ".unikey");

				if (unikey.equals(localUnikey)) {
					localUser = new Profile(ip, pseudo, unikey);
				} else {
					profiles.add(new Profile(ip, pseudo, unikey));
				}
			}

			file.close();
			props.clear();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
