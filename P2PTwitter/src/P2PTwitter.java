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

			boolean localUserExists = false;
			
			for (int i = 0; i < peers.length; i++) {
				String ip = props.getProperty(peers[i] + ".ip");
				String pseudo = props.getProperty(peers[i] + ".pseudo");
				String unikey = props.getProperty(peers[i] + ".unikey");

				if (unikey.compareTo(localUnikey) == 0) {
					localUser = new Profile(ip, pseudo, unikey);
					localUserExists = true;
				} else {
					profiles.add(new Profile(ip, pseudo, unikey));
				}
			}
			
			if (!localUserExists) {
				System.err.println("Local user does not exist in 'participants.properties' file.");
				System.exit(1);
			}

			file.close();
			props.clear();

		} catch (Exception e) {
			System.err.print("File 'participants.properties' not found.");
			System.exit(1);
		}
	}
}
